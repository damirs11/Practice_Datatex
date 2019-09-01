package com.company.controllers;

import com.company.models.documents.Document;
import com.company.models.staff.ListWrapper;
import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import com.company.services.DataBaseService;
import com.company.storage.PersonsAndDocumentsStorage;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static com.company.storage.PersonsAndDocumentsStorage.getPersonsWithDocuments;

/**
 * Person controller
 * <p>
 * Can display all employees and exist documents
 * of every employee
 */
@Path("/ecm")
public class PersonController {

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    /**
     * Return all existing employees
     *
     * @return employees in json format
     */
    @Path("/employees")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesJSON() {
        try {
            return Response.ok().entity(new Gson().toJson(DataBaseService.readTable(Person.class))).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Return all documents of employee by id
     *
     * @param id the id of employee
     * @return documents of employee in XML format
     */
    @Path("/employees/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") Integer id) {
        return Response.ok().entity(new Gson().toJson(DataBaseService.getById(Person.class, id))).build();
    }

    /**
     * Return all documents of employee by id
     *
     * @param id the id of employee
     * @return documents of employee in XML format
     */
    @Path("/employees/{id}/docs")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getEmployeeDocuments(@PathParam("id") Integer id) {
        try {
            ListWrapper<Document> documentListWrapper = new ListWrapper<>();
            documentListWrapper.setList(getPersonsWithDocuments().entrySet().stream()
                    .filter(entry -> entry.getKey().getId().equals(id))
                    .findFirst()
                    .map(Map.Entry::getValue).orElse(null));

            return Response.ok().entity(JaxbParser.listWrapperToStringXML(documentListWrapper)).build();
        } catch (JAXBException | IOException e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    /**
     * If delete was succeeded return OK
     * Otherwise return BAD_REQUEST
     *
     * @param id the id of employee
     */
    @Path("/employees/{id}")
    @DELETE
    public Response deleteEmployeeById(@PathParam("id") Integer id) {
        if (!DataBaseService.deleteEntityById(Person.class, id)) {
            PersonsAndDocumentsStorage.deletePersonWithDocumentById(id);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/employees/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployeeById(@PathParam("id") Integer id, Person person) {
        if (DataBaseService.updateEntityById(Person.class, id, person)) {
            PersonsAndDocumentsStorage.updatePerson(person);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/employees")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployeeBy(Person person) {
        person.setId(DataBaseService.getLastId(Person.class) + 1);
        if (DataBaseService.insertData(Person.class, Collections.singleton(person))) {
            PersonsAndDocumentsStorage.addPerson(person);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
