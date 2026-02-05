package com.example.employeemanagement.resources;

import com.example.employeemanagement.models.Employee;
import com.example.employeemanagement.services.EmployeeService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.util.*;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @GET
    @RolesAllowed({"user", "admin"}) // Both roles can view all employees
    public Collection<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "admin"}) // Both roles can view a specific employee
    public Response getEmployee(@PathParam("id") int id) {
        Employee employee = employeeService.getById(id);
        if (employee == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(employee).build();
    }

    @POST
    @RolesAllowed("admin") // Only admins can add employees
    public Response addEmployee(@Context SecurityContext sc, Employee employee) {
        // Explicit check matching the pattern in coffeeshop-restapi
        if (!sc.isUserInRole("admin")) {
            return Response.status(Response.Status.FORBIDDEN).entity("Admin only").build();
        }
        Employee created = employeeService.create(employee);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin") // Only admins can update
    public Response updateEmployee(@PathParam("id") int id, @Context SecurityContext sc, Employee employee) {
        if (!sc.isUserInRole("admin")) {
            return Response.status(Response.Status.FORBIDDEN).entity("Admin only").build();
        }
        Employee updated = employeeService.update(id, employee);
        if (updated == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin") // Only admins can delete
    public Response deleteEmployee(@Context SecurityContext sc, @PathParam("id") int id) {
        if (!sc.isUserInRole("admin")) {
            return Response.status(Response.Status.FORBIDDEN).entity("Admin only").build();
        }
        boolean deleted = employeeService.delete(id);
        if (!deleted)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}