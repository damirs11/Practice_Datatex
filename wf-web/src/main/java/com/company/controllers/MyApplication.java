package com.company.controllers;

import com.company.factory.DocumentFactory;
import com.company.models.documents.Document;
import com.company.models.staff.Person;
import com.company.storage.DocumentsStorage;
import com.company.storage.PersonsStorage;
import com.company.utils.DataGeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;

/**
 * Root of rest
 */
@ApplicationPath("/")
public class MyApplication extends javax.ws.rs.core.Application {

    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(MyApplication.class);

    /**
     * Instantiates a new My application.
     * <p>
     * Before application start work
     * Need to generate date like Persons and Documents
     */
    public MyApplication() {
        try {
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
        } catch (IllegalAccessException e) {
            logger.error("Error while try init MyApplication class " + e.getMessage());
        }
    }
}
