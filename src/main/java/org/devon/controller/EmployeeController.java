package org.devon.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.devon.bean.Employee;
import org.devon.service.EmployeeService;


@Path("/employees")
public class EmployeeController {
	
	EmployeeService employeeService=new EmployeeService();
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Employee> getEmployees()
	{
		
		List<Employee> listOfEmployees=employeeService.getAllEmployees();
		return listOfEmployees;
	}

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployeeById(@PathParam("id") int id)
	{
		return employeeService.getEmployee(id);
	}
   
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	public Employee addEmployee(Employee employee)
	{
		return employeeService.addEmployee(employee);
	}

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
	public Employee updateEmployee(Employee employee)
	{
		return employeeService.updateEmployee(employee);
		
	}
	
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
	public void deleteEmployee(@PathParam("id") int id)
	{
    	employeeService.deleteEmployee(id);
		
	}
	
}
