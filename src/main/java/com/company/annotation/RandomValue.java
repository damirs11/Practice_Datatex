package com.company.annotation;

import com.company.enumeration.RandomTypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Random value.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomValue {
    /**
     *
     * @return the random value of Type
     */
    RandomTypes value();
}
