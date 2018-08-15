package com.poiji.deserialize.model;

import com.poiji.bind.mapping.field.PoijiFieldValueParser;

public class CarType implements PoijiFieldValueParser {
    @Override
    public Object parse(Object cellValue, Class fieldType) {
        int val  = Integer.valueOf(cellValue.toString());
        if (val > 19 ) {
            return "Adult";
        } else if(val >= 13) {
            return "TEEN";
        } else {
            return "Child";
        }

    }
}
