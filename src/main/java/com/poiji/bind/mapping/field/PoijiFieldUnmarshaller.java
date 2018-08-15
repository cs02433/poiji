package com.poiji.bind.mapping.field;

import com.poiji.annotation.ExcelCellDeserialize;
import com.poiji.exception.IllegalCastException;
import com.poiji.util.Casting;

import java.lang.reflect.Field;

public interface PoijiFieldUnmarshaller {

     default <T> void  setFieldData(Field field, Object o, T instance) {
        try {
            field.setAccessible(true);
            Object deserialize = getDeserialize(field, o);
            field.set(instance, deserialize);
        } catch (IllegalAccessException e) {
            throw new IllegalCastException("Unexpected cast type {" + o + "} of field" + field.getName());
        }
    }

     default Object getDeserialize(Field field, Object o) {
        Class<? extends PoijiFieldValueParser> fieldDeserializerClass = PoijiFieldValueParser.None.class;
        ExcelCellDeserialize cellDeserialize = field.getAnnotation(ExcelCellDeserialize.class);
        if (cellDeserialize != null) {
            fieldDeserializerClass = cellDeserialize.value();
        }
        PoijiFieldValueParser fieldDeserializer = Casting.getInstance().newInstanceOf(fieldDeserializerClass);

        return fieldDeserializer.parse(o, field.getType());
    }

}
