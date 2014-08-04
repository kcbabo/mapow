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
package org.mapow;

import org.mapow.internal.TransformationImpl;

public class TransformationBuilder {
    
    private TransformInput _source;
    private TransformOutput _sink;
    private Encoder _encoder;
    private Encoder _decoder;
    private Mapper _map;

    public TransformationBuilder source(TransformInput input) {
        _source = input;
        return this;
    }
    
    public TransformationBuilder sink(TransformOutput output) {
        _sink = output;
        return this;
    }
    
    public TransformationBuilder decode(Encoder decoder) {
        _decoder = decoder;
        return this;
    }
    
    public TransformationBuilder encode(Encoder encoder) {
        _encoder = encoder;
        return this;
    }
    
    public TransformationBuilder map(Mapper map) {
        _map = map;
        return this;
    }
    
    public Transformation build() {
        // set reasonable defaults if not specified
        if (_source == null) {
            _source = new EmptyInput();
        }
        if (_sink == null) {
            _sink = new PrintOutput();
        }
        if (_map == null) {
            _map = new PassthroughMap();
        }

        return new TransformationImpl(_source, _sink, _decoder, _encoder, _map);
    }
}
