package com.company.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RandomValueProcessor {

    public static void initRandomValueProcessor(Object obj) throws IllegalAccessException {
        Class<? extends Object> clazz = obj.getClass();

        Field[] fields = clazz.getSuperclass().getDeclaredFields();

        for(Field field: fields) {
            if(!field.isAnnotationPresent(RandomValue.class)) {
                continue;
            }

            Object value = 99999;
            try {
                Method setterMethod = clazz.getMethod(
                        setterName(field.getName()), field.getType());
                setterMethod.invoke(obj, value);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                // Since setter injection failed, lets change permissions on field to accessible
                field.setAccessible(true);
                field.set(obj, value);
            }
        }
    }

    private static String setterName(String fieldName) {
        char[] ca = fieldName.toCharArray();
        ca[0] = Character.toUpperCase(ca[0]);
        return "set" + new String(ca);
    }
}
