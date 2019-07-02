package com.company.controllers;

import com.company.factory.DocumentFactory;
import com.company.services.DataBaseService;
import com.company.storage.DocumentsStorage;
import com.company.utils.DataGeneratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Response;

/**
 * Root of rest
 */
@ApplicationPath("/")
public class MyApplication extends javax.ws.rs.core.Application {

    /**
     * The Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(MyApplication.class);

    private static final int NUMBER_OF_DOCUMENTS = 20;

    /**
     * Instantiates a new My application.
     */
    public MyApplication() {

        DataBaseService.init();

        for (int i = 0; i < NUMBER_OF_DOCUMENTS; i++) {
            DocumentsStorage.getDocumentList().add(DocumentFactory.create(DataGeneratorUtils.getRandomDocType()));
        }
    }

    static Response createResponse(String output, String errorOutput) {
        if (!StringUtils.isEmpty(output)) {
            return Response.ok().entity(output).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(errorOutput).build();
        }
    }
}
