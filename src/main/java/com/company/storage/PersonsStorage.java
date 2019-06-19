package com.company.storage;

import com.company.models.staff.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonsStorage {

    private PersonsStorage() {
    }

    private static List<Person> personList = new ArrayList<>();

    public static List<Person> getPersonList() {
        return personList;
    }

    public static void setPersonList(List<Person> personList) {
        PersonsStorage.personList = personList;
    }
}
