package com.company.controllers;

import com.company.models.documents.Document;
import com.company.models.staff.ListWrapper;
import com.company.parser.JaxbParser;
import com.company.storage.DocumentsStorage;
import com.company.storage.PersonsStorage;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Person controller
 * <p>
 * Can display all employees and exist documents
 * of every employee
 */
@Path("/ecm")
public class PersonController {

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    private static final String ZERO_DOCUMENTS = "Person hasn't documents";
    private static final String ZERO_PERSONS = "No Persons right time";
    private static final String PERSON_DONST_EXIST = "Person dons't exist";

    /**
     * Return all existing employees
     *
     * @return employees in json format
     */
    @Path("/employees")
    @GET
    @Produces("application/json")
    public Response getEmployeesJSON() {
        return createResponse(new Gson().toJson(PersonsStorage.getPersonList()), ZERO_PERSONS);
    }

    /**
     * Return all documents of employee by id
     *
     * @param id the id of employee
     * @return documents of employee in XML format
     */
    @Path("/employees/{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getEmployeeDocuments(@PathParam("id") Integer id) {
        try {
            ListWrapper<Document> documentListWrapper = new ListWrapper<>();

            PersonsStorage.getPersonList().stream().filter(person -> person.getId().equals(id)).forEach(person -> {
                DocumentsStorage.getDocumentList().forEach(document -> {
                    if (document.getAuthor().equals(person.getId())) {
                        documentListWrapper.getList().add(document);
                    }
                });
            });
            return createResponse(JaxbParser.listWrapperToStringXML(documentListWrapper), ZERO_DOCUMENTS);
        } catch (JAXBException | IOException e) {
            logger.error(e.getMessage());
        }
        return createResponse("", PERSON_DONST_EXIST);
    }

    private Response createResponse(String output, String errorOutput) {
        if (output != null && !output.isEmpty()) {
            return Response.ok().entity(output).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(errorOutput).build();
        }
    }
}
