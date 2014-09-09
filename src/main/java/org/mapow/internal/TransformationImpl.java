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
package org.mapow.internal;

import org.mapow.Encoder;
import org.mapow.Mapper;
import org.mapow.TransformInput;
import org.mapow.TransformOutput;
import org.mapow.Transformation;

public class TransformationImpl implements Transformation {
    
    private Mapper _mapper;
    private TransformInput _input;
    private TransformOutput _output;
    private Encoder _decoder;
    private Encoder _encoder;
    
    public TransformationImpl(
            TransformInput input,
            TransformOutput output,
            Encoder decoder,
            Encoder encoder,
            Mapper map) {
        
        _mapper = map;
        _input = input;
        _output = output;
        _decoder = decoder;
        _encoder = encoder;
    }

    @Override
    public void transform() {
        // Step 1 : read input to transform
        Object inputObj = _input.read();
        // Step 2 : decode into mapping model
        if (_decoder != null) {
            inputObj = _decoder.decode(inputObj);
        }
        // Step 3 : map
        Object outputObj = _mapper.map(inputObj);
        // Step 4 : encode from mapping model
        if (_encoder != null) {
            outputObj = _encoder.encode(outputObj);
        }
        // Step 5 : write output from transformation
        _output.write(outputObj);
    }

}
