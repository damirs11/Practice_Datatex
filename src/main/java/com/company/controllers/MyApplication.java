package com.company.controllers;

import com.company.exception.DocumentExistsException;
import com.company.factory.DocumentFactory;
import com.company.models.documents.Document;
import com.company.models.staff.Person;
import com.company.storage.DocumentsStorage;
import com.company.storage.PersonsStorage;
import com.company.utils.DataGeneratorUtils;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class MyApplication extends javax.ws.rs.core.Application {

    public MyApplication() throws DocumentExistsException {

        int numberOfPersons = 10;
        for (int i = 0; i < numberOfPersons; i++) {
            Person person = new Person();
            DataGeneratorUtils.generate(person);
            PersonsStorage.getPersonList().add(person);
        }

        int numberOfDocuments = 20;
        for (int i = 0; i < numberOfDocuments; i++) {
            Document doc = DocumentFactory.create(DataGeneratorUtils.takeRandomDocType());
            DocumentsStorage.getDocumentList().add(doc);
        }
    }
}
