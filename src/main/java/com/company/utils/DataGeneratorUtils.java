package com.company.utils;


import com.company.annotation.RandomValue;
import com.company.enumeration.deliveryType;
import com.company.enumeration.docTypes;
import org.apache.hadoop.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Class with utils
 */
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

    /**
     * Random value from enum
     *
     * @param <T>   the type of enum
     * @param clazz the clazz of enum
     * @return the value from enum
     */
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    /**
     * Take random person string.
     *
     * @return the string
     */
    public static String takeRandomPerson() {
        return persons.get(random.nextInt(persons.size()));
    }

    /**
     * Take random delivery type string.
     *
     * @return the string
     */
    public static String takeRandomDeliveryType() {
        return randomEnum(deliveryType.class).getType();
    }

    /**
     * Gets random doc type.
     *
     * @return the random doc type
     */
    public static docTypes getRandomDocType() {
        return randomEnum(docTypes.class);
    }

    /**
     * Take random date.
     *
     * @return the date
     */
    public static Date takeRandomDate(){
        return new Date(System.currentTimeMillis() - random.nextInt(1000 * 3600 * 24 * 1000));
    }

    /**
     * View all items for @RandomValue annotation and put specific random value
     * taken from annotation.value()
     *
     * @param obj the target object
     * @throws IllegalAccessException the illegal access exception
     */
    public static void process(Object obj) throws IllegalAccessException {

        Class clazz = obj.getClass();
        Collection<Field> fields = ReflectionUtils.getDeclaredFieldsIncludingInherited(clazz);

        for (Field field : fields)
            if (field.isAnnotationPresent(RandomValue.class)) {

                Object value = null;
                switch (field.getAnnotation(RandomValue.class).value()) {
                    case INTEGER:
                        value = random.nextInt(1000);
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
