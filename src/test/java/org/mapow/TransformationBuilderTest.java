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

import static org.mapow.ObjectInput.fromObject;
import static org.mapow.ObjectOutput.toObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransformationBuilderTest {
    
    private TransformationBuilder tb;
    
    @Before
    public void setUp() {
        tb = new TransformationBuilder();
    }
    
    @Test
    public void createFromNothing() {
        Transformation t = tb.build();
        t.transform();
    }
    
    @Test
    public void createWithInput() {
        tb.source(fromObject("Hello!"));
        Transformation t = tb.build();
        t.transform();
    }
    
    @Test
    public void createWithOutput() {
        ObjectOutput output = toObject();
        tb.sink(output);
        Transformation t = tb.build();
        t.transform();
        Assert.assertNotNull(output.getOutput());
    }
    
    @Test
    public void inputToOuputNoMap() {
        final String content = "ABC";
        ObjectOutput output = toObject();
        tb.source(fromObject(content));
        tb.sink(output);
        Transformation t = tb.build();
        t.transform();
        Assert.assertEquals(content, output.getOutput());
    }
}

