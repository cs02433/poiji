package com.poiji.bind.mapping.field;

public interface PoijiFieldValueParser<T> {

    T parse(Object cellValue, Class<T> fieldType);


    class None implements PoijiFieldValueParser {

        public None(){

        }

        @Override
        public Object parse(Object cellValue, Class fieldType) {

            return cellValue;
        }
    }
}
