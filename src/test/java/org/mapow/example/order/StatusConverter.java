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

import org.dozer.DozerConverter;
import org.mapow.example.order.abc.Status;
import org.mapow.example.order.xyz.Priority;


public class StatusConverter extends DozerConverter<Status, Priority> {
    
    public StatusConverter() {
        super(Status.class, Priority.class);
    }

    @Override
    public Priority convertTo(
            Status source,
            Priority destination) {
        switch (source) {
        case GOLD :
            return Priority.HIGH;
        case NORMAL :
            return Priority.MEDIUM;
        case VALUE :
            return Priority.LOW;
        default :
            return null;
        }
    }

    @Override
    public Status convertFrom (
            Priority source, 
            Status destination) {
        
        return null;
    }

}
