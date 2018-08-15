package com.poiji.deserialize.model;

import com.poiji.bind.mapping.field.PoijiFieldValueParser;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CommaSeparatedOwnersName implements PoijiFieldValueParser {
    @Override
    public Object parse(Object cellValue, Class fieldType) {
        String values = cellValue.toString();
        return Arrays.stream(values.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
