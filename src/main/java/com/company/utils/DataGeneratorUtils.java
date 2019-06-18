package com.company.utils;


import com.company.enumeration.deliveryType;
import com.company.enumeration.docTypes;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataGeneratorUtils {

    private static Random random = new Random();

    private DataGeneratorUtils() {
    }

    private static List<String> persons = Arrays.asList(
            "А Иванов Кузнецов",
            "Б Козлов Новиков",
            "В Кузнецов Соколов",
            "Г Лебедев Козлов",
            "Д Морозов Петров",
            "Е Новиков Морозов",
            "Ж Попов Лебедев",
            "З Смирнов Иванов",
            "И Соколов Попов"
    );

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static String takeRandomPerson() {
        return persons.get(random.nextInt(persons.size()));
    }

    public static String takeRandomDeliveryType() {
        return randomEnum(deliveryType.class).getType();
    }

    public static docTypes getRandomDocType() {
        return randomEnum(docTypes.class);
    }

    public static Date takeRandomDate(){
        return new Date(System.currentTimeMillis() - random.nextInt(1000 * 3600 * 24 * 1000));
    }
}
