package com.company.utils;

import com.company.annotation.RandomValue;
import com.company.enumeration.randomTypes;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class RandomValueProcessor {

    private static Random random = new Random();

    public static void proccess(Object obj) throws IllegalAccessException {
        Class clazz = obj.getClass();
        Class superClazz = obj.getClass().getSuperclass();

        Collection<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        fields.addAll(Arrays.asList(superClazz.getDeclaredFields()));

        for(Field field: fields) {

            if(!field.isAnnotationPresent(RandomValue.class)) {
                continue;
            }

            randomTypes type = field.getAnnotation(RandomValue.class).value();
            int upperBound = field.getAnnotation(RandomValue.class).upperBound();

            Object value = null;
            switch (type) {
                case INTEGER:
                    value = random.nextInt(upperBound);
                    break;
                case DATE:
                    value = DataGeneratorUtils.takeRandomDate();
                    break;
                case PERSON:
                    value = DataGeneratorUtils.takeRandomPerson();
                    break;
                case TEXT:
                    value = field.getName();
                    break;
                case DELIVERY:
                    value = DataGeneratorUtils.takeRandomDeliveryType();
                    break;
            }

            field.setAccessible(true);
            field.set(obj, value);
        }
    }
}
