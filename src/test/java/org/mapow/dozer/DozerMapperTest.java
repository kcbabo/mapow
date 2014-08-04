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
package org.mapow.dozer;

import static org.mapow.ObjectInput.fromObject;
import static org.mapow.ObjectOutput.toObject;
import junit.framework.Assert;

import org.junit.Test;
import org.mapow.ObjectOutput;
import org.mapow.Transformation;
import org.mapow.TransformationBuilder;
import org.mapow.mapper.dozer.DozerMapper;

public class DozerMapperTest {

    @Test
    public void basicDozerMap() {
        final String orderNum = "ABC-123";
        OrderA orderA = new OrderA();
        orderA.setOrderNum(orderNum);

        ObjectOutput orderOutput = toObject();
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromObject(orderA))
            .map(DozerMapper.newMap("dozerBasicMapping.xml").target(OrderB.class))
            .sink(orderOutput);
        Transformation t = tb.build();
        t.transform();
        
        Assert.assertEquals(orderNum, orderOutput.getOutput(OrderB.class).getOrderNo());
    }
}
