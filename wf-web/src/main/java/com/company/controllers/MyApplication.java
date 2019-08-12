package com.company.controllers;

import com.company.factory.DocumentFactory;
import com.company.models.staff.Person;
import com.company.services.DataBaseService;
import com.company.storage.PersonsAndDocumentsStorage;
import com.company.utils.DataGeneratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Set;

/**
 * Root of rest
 */
@ApplicationPath("/rest")
public class MyApplication extends Application {

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
        DataBaseService.readTable(Person.class).forEach(PersonsAndDocumentsStorage::addPerson);
        for (int i = 0; i < NUMBER_OF_DOCUMENTS; i++) {
            PersonsAndDocumentsStorage.addDocument(DocumentFactory.create(DataGeneratorUtils.getRandomDocType()));
        }
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(PersonController.class);
        classes.add(OrganizationController.class);
        classes.add(DepartmentController.class);
        return classes;
    }

    static Response createResponse(String output, String errorOutput) {
        if (!StringUtils.isEmpty(output)) {
            return Response.ok().entity(output).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(errorOutput).build();
        }
    }
}
