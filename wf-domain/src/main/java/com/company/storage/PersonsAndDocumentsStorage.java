package com.company.storage;

import com.company.exception.DocumentExistsException;
import com.company.models.documents.Document;
import com.company.models.staff.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Store all existing Documents
 */
public class PersonsAndDocumentsStorage {

    private PersonsAndDocumentsStorage() {
    }

    private static Collection<Document> documents = new ArrayList<>();
    private static Collection<Person> persons = new ArrayList<>();

    public static Collection<Document> getDocuments() {
        return documents;
    }

    /**
     * Add Document with DocumentExistsException check
     *
     * @param doc the Document
     * @throws DocumentExistsException the document exists exception
     */
    public static void addDocument(Document doc) throws DocumentExistsException {
        if (documents.contains(doc)) {
            throw new DocumentExistsException(doc);
        } else {
            documents.add(doc);
        }
    }

    public static Collection<Person> getPersons() {
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

    public static void deletePersonWithDocumentById(Integer id) {
        persons.removeIf(person -> person.getId().equals(id));
        documents.removeIf(document -> document.getAuthor().equals(id));
    }

    public static void updatePerson(Person updatedPerson) {
        persons.forEach(person -> {
            if (person.getId().equals(updatedPerson.getId())) {
                person = updatedPerson;
            }
        });
    }
}
