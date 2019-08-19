package com.company.controllers;

import com.company.models.staff.Department;
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
@Path("/dpt")
public class DepartmentController {

    private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    private static final String ZERO_DEPARTMENTS = "No Departments right now";

    /**
     * Return all existing employees
     *
     * @return employees in json format
     */
    @Path("/departments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesJSON() {
        try {
            return Response.ok().entity(new Gson().toJson(DataBaseService.readTable(Department.class))).build();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
