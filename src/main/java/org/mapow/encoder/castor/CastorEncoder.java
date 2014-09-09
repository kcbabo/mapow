/*
 * Copyright 2014 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mapow.encoder.castor;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import org.castor.xml.XMLProperties;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.XMLContext;
import org.mapow.Encoder;

public class CastorEncoder implements Encoder {
    
    private static final String ATTR_NODE_TYPE = "attribute";
    private static final String ELE_NODE_TYPE = "element";
    
    private Class<?> _decodedClass;
    private XMLContext _context;
    
    public CastorEncoder(Class<?> decodedClass) {
        _decodedClass = decodedClass;
        _context = new XMLContext();
    }

    @Override
    public Object encode(Object obj) {
        StringWriter sw = new StringWriter();
        try {
            Marshaller marsh = _context.createMarshaller();
            marsh.setWriter(sw);
            marsh.marshal(obj);
        } catch (Exception ex) {
            throw new RuntimeException("Castor encoding failed: " + ex.toString());
        }
        return sw.toString();
    }

    @Override
    public Object decode(Object obj) {
        Object decodedObj = null;
        Unmarshaller umarsh = _context.createUnmarshaller();
        umarsh.setClass(_decodedClass);
        try {
            if (obj instanceof InputStream) {
                decodedObj = umarsh.unmarshal(new InputStreamReader((InputStream)obj));
            } else if (obj instanceof String) {
                decodedObj = umarsh.unmarshal(new StringReader((String)obj));
            }
            return _decodedClass.cast(decodedObj);
        } catch (Exception ex) {
            throw new RuntimeException("Castor decoding failed: " + ex.toString());
        }
    }
    
    public CastorEncoder primitivesAsAttributes() {
        _context.setProperty(XMLProperties.PRIMITIVE_NODE_TYPE, ATTR_NODE_TYPE);
        return this;
    }
    
    public CastorEncoder primitivesAsElements() {
        _context.setProperty(XMLProperties.PRIMITIVE_NODE_TYPE, ELE_NODE_TYPE);
        return this;
    }
    
    public CastorEncoder addMapping(String path) throws Exception {
        Mapping mapping = new Mapping();
        mapping.loadMapping(path);
        _context.addMapping(mapping);
        return this;
    }
    
}
