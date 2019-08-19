package com.company.controllers;

import com.company.models.staff.Organization;
import com.company.services.DataBaseService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Person controller
 * <p>
 * Can display all employees and exist documents
 * of every employee
 */
@Path("/org")
public class OrganizationController {

    private static Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    /**
     * Return all existing organizations
     *
     * @return organizations in json format
     */
    @Path("/organizations")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesJSON() {
        try {
            return Response.ok().entity(new Gson().toJson(DataBaseService.readTable(Organization.class))).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
