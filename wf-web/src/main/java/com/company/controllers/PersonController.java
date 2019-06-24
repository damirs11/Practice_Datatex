package com.company.controllers;

import com.company.models.documents.Document;
import com.company.models.staff.ListWrapper;
import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import com.company.storage.DocumentsStorage;
import com.company.storage.PersonsStorage;
import com.google.gson.Gson;

import javax.ws.rs.*;
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

    private static final String ZERO_DOCUMENTS = "Person hasn't documents";
    private static final String ZERO_PERSONS = "No Persons right time";
    private static final String PERSON_DONT_EXIST = "Person don't exist";

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
            for (Person person : PersonsStorage.getPersonList()) {
                if (person.getId().equals(id)) {
                    ListWrapper<Document> documentListWrapper = new ListWrapper<>();

                    DocumentsStorage.getDocumentList().forEach(document -> {
                        if (document.getAuthor().equals(person.getId())) {
                            documentListWrapper.getList().add(document);
                        }
                    });

                    return createResponse(JaxbParser.listWrapperToStringXML(documentListWrapper), ZERO_DOCUMENTS);
                }
            }
            return createResponse(null, PERSON_DONT_EXIST);
        } catch (IndexOutOfBoundsException | JAXBException | IOException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    private Response createResponse(String output, String errorOutput) {
        if (!(output == null || output.isEmpty())) {
            return Response.ok().entity(output).build();
        } else {
            return Response.ok().entity(errorOutput).build();
        }
    }
}
