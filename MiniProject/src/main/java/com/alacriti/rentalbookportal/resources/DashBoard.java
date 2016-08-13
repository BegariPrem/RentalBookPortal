package com.alacriti.rentalbookportal.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.alacriti.rentalbookportal.utilities.HomePageGenerator;

@Path("/home")
public class DashBoard {
	@Context HttpServletRequest httpServletRequest;
	@GET
	public Response getHomePage()
	{
		HomePageGenerator homePageGenerator=new HomePageGenerator();
		 return homePageGenerator.homePage(httpServletRequest);
	}
	
	@Path("/index")
	@GET
	public Response getUserHomePage()
	{
		HomePageGenerator homepage=new HomePageGenerator();
		return homepage.googleUserPage(httpServletRequest);
	}
	
	
}
