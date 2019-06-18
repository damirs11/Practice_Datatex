package com.company.annotation;

import com.company.enumeration.randomTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomValue {
    randomTypes value();
    int upperBound() default 1000;
}
