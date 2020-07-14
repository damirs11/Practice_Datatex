package com.company.controllers;

import com.company.models.staff.Organization;
import com.company.services.DataBaseService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static com.company.controllers.MyApplication.createResponse;

/**
 * Person controller
 * <p>
 * Can display all employees and exist documents
 * of every employee
 */
@Path("/org")
public class OrganizationController {

    private static Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    private static final String ZERO_ORGANIZATIONS = "No Organizations right now";

    /**
     * Return all existing organizations
     *
     * @return organizations in json format
     */
    @Path("/organizations")
    @GET
    @Produces("application/json")
    public Response getEmployeesJSON() {
        return createResponse(new Gson().toJson(DataBaseService.readTable(Organization.class)), ZERO_ORGANIZATIONS);
    }
}
