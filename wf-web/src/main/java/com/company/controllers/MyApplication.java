package com.company.controllers;

import com.company.factory.DocumentFactory;
import com.company.models.documents.Document;
import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import com.company.storage.DocumentsStorage;
import com.company.storage.PersonsStorage;
import com.company.utils.DataGeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Objects;

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
     * Need to input data like Persons and
     * generate data for Documents
     */
    public MyApplication() {
        try {
            File PERSON_INPUT = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("InputXML/InputPerson.xml")).getFile());

            PersonsStorage.setPersonList(
                    JaxbParser.getObject(PERSON_INPUT, Person.class).getList());

            int numberOfDocuments = 20;
            for (int i = 0; i < numberOfDocuments; i++) {
                Document doc = DocumentFactory.create(DataGeneratorUtils.takeRandomDocType());
                DocumentsStorage.getDocumentList().add(doc);
            }
        } catch (JAXBException e) {
            logger.error("Error while try init MyApplication class " + e.getMessage());
        }
    }
}
