package com.alacriti.rentalbookportal.resources;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.delegate.GenerateHomePageService;


public class MainResource {
	Logger logger=Logger.getLogger(MainResource.class);
	GenerateHomePageService generateHomePage=new GenerateHomePageService();	
	@GET 
	public Response generateHomePage()
	{
		logger.info(" resource called");
		String status=generateHomePage.generate();
		return Response.status(Status.CREATED).entity(status).build();
	}
}
