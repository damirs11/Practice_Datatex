package com.company.controllers;

import com.company.models.documents.Document;
import com.company.models.staff.ListWrapper;
import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import com.company.storage.DocumentsStorage;
import com.company.storage.PersonsStorage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

@Path("/ecm")
public class PersonController {

    @Path("/employees")
    @GET
    @Produces("application/json")
    public Response getEmployeesJSON() {
        if (!PersonsStorage.getPersonList().isEmpty()) {
            return Response.ok(PersonsStorage.getPersonList()).build();
        } else {
            return Response.noContent().entity("No Persons right time").build();
        }
    }

    @Path("/employees/{id}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getEmployeeDocuments(@PathParam("id") Integer id) throws JAXBException {
        try {
            Person person = PersonsStorage.getPersonList().get(id);

            ListWrapper<Document> documentListWrapper = new ListWrapper<>();

            DocumentsStorage.getDocumentList().forEach(document -> {
                if (document.getAuthor().equals(person.getId())) {
                    documentListWrapper.getList().add(document);
                }
            });

            String output = "Person hasn't documents";
            if (!documentListWrapper.getList().isEmpty()) {
                output = JaxbParser.listWrapperToStringXML(documentListWrapper);
                return Response.ok().entity(output).build();
            } else {
                return Response.ok().entity(output).build();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }
}
