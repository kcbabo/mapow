package org.mapow;

public class PassthroughMap implements Mapper {

    @Override
    public Object map(Object o) {
        return o;
    }

}
