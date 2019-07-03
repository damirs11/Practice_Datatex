package com.company.controllers;

import com.company.models.documents.Document;
import com.company.models.staff.ListWrapper;
import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import com.company.services.DataBaseService;
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
import java.util.Map;

import static com.company.controllers.MyApplication.createResponse;
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

    private static final String ZERO_DOCUMENTS = "Person hasn't documents";
    private static final String ZERO_PERSONS = "No Persons right now";
    private static final String PERSON_DOEST_EXIST = "Person does't exist";

    /**
     * Return all existing employees
     *
     * @return employees in json format
     */
    @Path("/employees")
    @GET
    @Produces("application/json")
    public Response getEmployeesJSON() {
        return createResponse(new Gson().toJson(DataBaseService.readTable(Person.class)), ZERO_PERSONS);
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
            documentListWrapper.setList(getPersonsWithDocuments().entrySet().stream()
                    .filter(entry -> entry.getKey().getId().equals(id))
                    .findFirst()
                    .map(Map.Entry::getValue).orElse(null));

            return createResponse(JaxbParser.listWrapperToStringXML(documentListWrapper), ZERO_DOCUMENTS);
        } catch (JAXBException | IOException e) {
            logger.error(e.getMessage());
        }
        return createResponse("", PERSON_DOEST_EXIST);
    }
}
