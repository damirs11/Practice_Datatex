package com.company.storage;

import com.company.models.documents.Document;
import com.company.models.staff.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Store all existing Documents
 */
public class PersonsAndDocumentsStorage {

    private PersonsAndDocumentsStorage() {
    }

    private static List<Document> documents = new ArrayList<>();
    private static List<Person> persons = new ArrayList<>();

    public static List<Document> getDocuments() {
        return documents;
    }

    public static void addDocument(Document document) {
        documents.add(document);
    }

    public static List<Person> getPersons() {
        return persons;
    }

    public static void addPerson(Person person) {
        persons.add(person);
    }

    public static Map<Person, List<Document>> getPersonsWithDocuments() {
        Map<Person, List<Document>> map = new TreeMap<>();
        for (Person person : persons) {
            for (Document document : documents) {
                if (document.getAuthor().equals(person.getId())) {
                    map.computeIfAbsent(person, key -> new ArrayList<>()).add(document);
                }
            }
        }
        return map;
    }
}
