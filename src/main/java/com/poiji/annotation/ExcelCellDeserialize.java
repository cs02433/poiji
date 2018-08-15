package com.poiji.annotation;

import com.poiji.bind.mapping.field.PoijiFieldValueParser;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelCellDeserialize {

    Class<? extends PoijiFieldValueParser> value()
    default PoijiFieldValueParser.None.class;

}
