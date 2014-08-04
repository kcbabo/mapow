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
package org.mapow.example.order;

import static org.mapow.ObjectInput.fromObject;
import static org.mapow.StreamInput.fromFile;

import org.mapow.PrintOutput;
import org.mapow.Transformation;
import org.mapow.TransformationBuilder;
import org.mapow.encoder.castor.CastorEncoder;
import org.mapow.encoder.jackson.JacksonEncoder;
import org.mapow.example.order.abc.ABCOrder;
import org.mapow.example.order.abc.OrderHeader;
import org.mapow.example.order.abc.OrderItem;
import org.mapow.example.order.abc.Status;
import org.mapow.example.order.xyz.XYZOrder;
import org.mapow.mapper.dozer.DozerMapper;

/**
 * An example driver which demonstrates various mapping use cases for 
 * Order data.
 */
public class OrderMapping {
    
    private static final String ABC_ORDER_XML_PATH = 
            "target/test-classes/org/mapow/example/order/abc-order.xml";

    private static final String ABC_ORDER_JSON_PATH = 
            "target/test-classes/org/mapow/example/order/abc-order.json";

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            usage();
            System.exit(-1);
        } 
        OrderMapping.class.getMethod(args[0]).invoke(null);
    }
    
    public static void javaToJava() {
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromObject(createABCOrder()))
            .map(DozerMapper.newMap("dozerOrderMapping.xml").target(XYZOrder.class))
            .sink(new PrintOutput());
        Transformation t = tb.build();
        t.transform();
    }
    
    public static void javaToJSON() {
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromObject(createABCOrder()))
            .map(DozerMapper.newMap("dozerOrderMapping.xml").target(XYZOrder.class))
            .encode(new JacksonEncoder())
            .sink(new PrintOutput());
        Transformation t = tb.build();
        t.transform();
    }
    
    public static void javaToXML() {
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromObject(createABCOrder()))
            .map(DozerMapper.newMap("dozerOrderMapping.xml").target(XYZOrder.class))
            .encode(new CastorEncoder(XYZOrder.class))
            .sink(new PrintOutput());
        Transformation t = tb.build();
        t.transform();
    }
    
    public static void xmlToJava() throws Exception {
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromFile(ABC_ORDER_XML_PATH))
            .decode(new CastorEncoder(ABCOrder.class))
            .map(DozerMapper.newMap("dozerOrderMapping.xml").target(XYZOrder.class))
            .sink(new PrintOutput());
        Transformation t = tb.build();
        t.transform();
    }
    
    public static void xmlToXml() throws Exception {
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromFile(ABC_ORDER_XML_PATH))
            .decode(new CastorEncoder(ABCOrder.class))
            .map(DozerMapper.newMap("dozerOrderMapping.xml").target(XYZOrder.class))
            .encode(new CastorEncoder(XYZOrder.class))
            .sink(new PrintOutput());
        Transformation t = tb.build();
        t.transform();
    }
    
    public static void jsonToJava() throws Exception {
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromFile(ABC_ORDER_JSON_PATH))
            .decode(new JacksonEncoder(ABCOrder.class))
            .map(DozerMapper.newMap("dozerOrderMapping.xml").target(XYZOrder.class))
            .sink(new PrintOutput());
        Transformation t = tb.build();
        t.transform();
    }
    
    public static void jsonToJson() throws Exception {
        TransformationBuilder tb = new TransformationBuilder()
            .source(fromFile(ABC_ORDER_JSON_PATH))
            .decode(new JacksonEncoder(ABCOrder.class))
            .map(DozerMapper.newMap("dozerOrderMapping.xml").target(XYZOrder.class))
            .encode(new JacksonEncoder())
            .sink(new PrintOutput());
        Transformation t = tb.build();
        t.transform();
    }
    
    private static ABCOrder createABCOrder() {
        ABCOrder abc = new ABCOrder();
        abc.setHeader(new OrderHeader()
            .setCustomerNum("ACME-123")
            .setOrderNum("ORDER1")
            .setStatus(Status.GOLD));
        abc.addOrderItem(new OrderItem()
            .setId("PICKLE")
            .setPrice(2.25)
            .setQuantity(1000));
        return abc;
    }
    
    private static void usage() {
        System.err.println(
                "\n#############"
              + "\n## You must pass method name as argument. Available options:"
              + "\n##\tjavaToJava"
              + "\n##\tjavaToJSON"
              + "\n##\tjavaToXML"
              + "\n##\txmlToJava"
              + "\n##\tjsonToJava"
              + "\n##\txmlToXml"
              + "\n##\tjsonToJson"
              + "\n##"
              + "\n## An example from Maven:"
              + "\n##\t mvn -Prun -Dexec.args=javaToXML"
              + "\n#############");
    }
}
