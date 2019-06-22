package com.company.utils;


import com.company.annotation.RandomValue;
import com.company.enumeration.DeliveryType;
import com.company.enumeration.DocTypes;
import org.apache.hadoop.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Class with utils
 */
public class DataGeneratorUtils {

    private static final int UPPER_BOUND = 1000;
    private static int idGenerator = 1;

    private static Random random = new Random();
    private static Logger logger = LoggerFactory.getLogger(DataGeneratorUtils.class);

    private DataGeneratorUtils() {
    }

    private static List<SimplePerson> persons = Arrays.asList(
            new SimplePerson("Иванов", "А", "Кузнецов"),
            new SimplePerson("Козлов", "Б", "Новиков"),
            new SimplePerson("Кузнецов", "В", "Соколов"),
            new SimplePerson("Морозов", "Г", "Козлов"),
            new SimplePerson("Новиков", "Д", "Петров"),
            new SimplePerson("Попов", "Е", "Морозов"),
            new SimplePerson("Смирнов", "Ж", "Лебедев"),
            new SimplePerson("Соколов", "З", "Иванов")
    );

    /**
     * Random value from enum
     *
     * @param clazz the clazz of enum
     * @return the value from enum
     */
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    /**
     * Take random person string.
     *
     * @return the string
     */
    public static Integer takeRandomPerson() {
        return random.nextInt(persons.size());
    }

    /**
     * Take random delivery type string.
     *
     * @return the string
     */
    public static String takeRandomDeliveryType() {
        return randomEnum(DeliveryType.class).getType();
    }

    /**
     * Take random doc type.
     *
     * @return the random doc type
     */
    public static DocTypes takeRandomDocType() {
        return randomEnum(DocTypes.class);
    }

    /**
     * Take random date.
     *
     * @return the date
     */
    public static Date takeRandomDate() {
        return new Date(System.currentTimeMillis() - random.nextInt(1000 * 3600 * 24 * 1000));
    }


    /**
     * View all items for @RandomValue annotation and put specific random value
     * taken from annotation.value()
     *
     * @param obj the target object
     */
    public static void generate(Object obj) throws IllegalAccessException {

        for (Field field : ReflectionUtils.getDeclaredFieldsIncludingInherited(obj.getClass()))
            if (field.isAnnotationPresent(RandomValue.class)) {

                Object value = null;
                switch (field.getAnnotation(RandomValue.class).value()) {
                    case INTEGER:
                        value = random.nextInt(UPPER_BOUND);
                        break;
                    case DATE:
                        value = DataGeneratorUtils.takeRandomDate();
                        break;
                    case PERSON_ID:
                        value = DataGeneratorUtils.takeRandomPerson();
                        break;
                    case UNIQUE_ID:
                        value = idGenerator++;
                        break;
                    case PERSON_MIDDLE_NAME:
                        value = persons.get(DataGeneratorUtils.takeRandomPerson()).getMiddleName();
                        break;
                    case PERSON_NAME:
                        value = persons.get(DataGeneratorUtils.takeRandomPerson()).getName();
                        break;
                    case PERSON_SECOND_NAME:
                        value = persons.get(DataGeneratorUtils.takeRandomPerson()).getSecondName();
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
