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

import static org.mapow.ObjectInput.fromObject;
import static org.mapow.ObjectOutput.toObject;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;
import org.mapow.Item;
import org.mapow.ObjectOutput;
import org.mapow.OrderA;
import org.mapow.Transformation;
import org.mapow.TransformationBuilder;
import org.mapow.util.XMLHelper;

public class CastorEncoderTest {

    @Test
    public void primitivesAsElements() throws Exception {
        OrderA orderA = new OrderA();
        orderA.setAmount(2.51);
        orderA.setOrderNum("123");
        ObjectOutput output = toObject();
        
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromObject(orderA))
            .encode(new CastorEncoder(OrderA.class).primitivesAsElements())
            .sink(output);
        Transformation t = tb.build();
        t.transform();
        
        
        Assert.assertEquals("2.51", XMLHelper.xpath(
                output.getOutput(String.class), "//order-a/amount"));
    }
    
    @Test
    public void primitivesAsAttributes() throws Exception {
        OrderA orderA = new OrderA();
        orderA.setAmount(2.51);
        orderA.setOrderNum("123");
        ObjectOutput output = toObject();
        
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromObject(orderA))
            .encode(new CastorEncoder(OrderA.class).primitivesAsAttributes())
            .sink(output);
        Transformation t = tb.build();
        t.transform();
        Assert.assertEquals("2.51", XMLHelper.xpath(
                output.getOutput(String.class), "//order-a/@amount"));
    }
    
    @Test
    public void listsAsContainers() throws Exception {
        OrderA orderA = new OrderA();
        orderA.setAmount(2.51);
        orderA.setOrderNum("123");
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item().setName("item1").setQuantity(10));
        items.add(new Item().setName("item2").setQuantity(100));
        orderA.setItems(items);
        ObjectOutput output = toObject();
        
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromObject(orderA))
            .encode(new CastorEncoder(OrderA.class).addMapping("target/test-classes/castor-orderA-mapping.xml"))
            .sink(output);
        Transformation t = tb.build();
        t.transform();
        
        Assert.assertEquals("10", XMLHelper.xpath(
                output.getOutput(String.class), "//order-a/items/item/@quantity"));
    }
    
}
