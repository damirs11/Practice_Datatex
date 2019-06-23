package com.company.controllers;

import com.company.factory.DocumentFactory;
import com.company.models.documents.Document;
import com.company.storage.DocumentsStorage;
import com.company.utils.DataGeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.DataBaseService;

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
     * Need to input data like Persons and
     * generate data for Documents
     */
    public MyApplication() {

        DataBaseService.init();

        int numberOfDocuments = 20;
        for (int i = 0; i < numberOfDocuments; i++) {
            Document doc = DocumentFactory.create(DataGeneratorUtils.takeRandomDocType());
            DocumentsStorage.getDocumentList().add(doc);
        }
    }
}
