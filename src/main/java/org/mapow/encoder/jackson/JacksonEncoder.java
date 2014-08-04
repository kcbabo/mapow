package org.mapow.encoder.jackson;

import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.mapow.Encoder;

public class JacksonEncoder implements Encoder {
    
    private ObjectMapper _jsonMapper;
    private Class<?> _decodedClass;
    
    public JacksonEncoder() {
        this(null);
    }
    
    public JacksonEncoder(Class<?> decodedClass) {
        _decodedClass = decodedClass;
        _jsonMapper = new ObjectMapper();
        _jsonMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
    }

    @Override
    public Object encode(Object obj) {
        try {
            return _jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception ex) {
            throw new RuntimeException("Encoding failed: " + ex.toString());
        }
    }

    @Override
    public Object decode(Object obj) {
        Object decodedObj = null;
        try {
            if (obj instanceof String) {
                decodedObj = _jsonMapper.readValue((String)obj, _decodedClass);
            } else if (obj instanceof InputStream) {
                decodedObj = _jsonMapper.readValue((InputStream)obj, _decodedClass);
            }
            return decodedObj;
        } catch (Exception ex) {
            throw new RuntimeException("Encoding failed: " + ex.toString());
        }
    }

}
