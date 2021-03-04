package org.devon.controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.devon.bean.ExceptionMessage;
import org.devon.exception.EmployeeNotFoundException;

@Provider
public class EmployeeNotFoundExceptionMapper implements ExceptionMapper<EmployeeNotFoundException>{

	@Override
	public Response toResponse(EmployeeNotFoundException cnfe) {
		
		ExceptionMessage exceptionMessage= new ExceptionMessage(cnfe.getExceptionMessage(),"404");
		return Response.status(Status.NOT_FOUND).entity(exceptionMessage).build();
	}

	
}
