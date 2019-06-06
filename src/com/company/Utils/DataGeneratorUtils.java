package com.company.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataGeneratorUtils {

    private Random random = new Random();

    public DataGeneratorUtils() {
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

    public List getPersons() {
        return persons;
    }

    private static List<String> delivetyType = Arrays.asList(
            "Способ доставки 1",
            "Способ доставки 2",
            "Способ доставки 3",
            "Способ доставки 4"
    );

    public String takeRandomPerson() {
        return persons.get(random.nextInt(persons.size()));
    }

    public String takeRandomDelivetyType() {
        return delivetyType.get(random.nextInt(persons.size()));
    }
}
