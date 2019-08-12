package com.company.annotation;

import com.company.enumeration.DerbyTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Column definition
 * if value is empty = taking field name
 * if dataType is AUTO = taking data type of variable
 * if except is 0 = this column taking in SELECT query
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    String value() default "";

    DerbyTypes dataType() default DerbyTypes.AUTO;

    int except() default 0;
}


