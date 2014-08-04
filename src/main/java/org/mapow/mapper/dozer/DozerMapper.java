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
package org.mapow.mapper.dozer;

import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.mapow.Mapper;

public class DozerMapper implements Mapper {
    
    private DozerBeanMapper _mapper = new DozerBeanMapper();
    private Class<?> _targetClass = Object.class;
    
    private DozerMapper(String ... mapPaths) {
        if (mapPaths != null && mapPaths.length > 0) {
            List<String> list = Arrays.asList(mapPaths);
            _mapper.setMappingFiles(list);
        }
    }
    
    public static DozerMapper newMap(String ... mapPaths) {
        return new DozerMapper(mapPaths);
    }
    
    public DozerMapper target(Class<?> target) {
        _targetClass = target;
        return this;
    }

    @Override
    public Object map(Object o) {
        return _mapper.map(o, _targetClass);
    }
}
