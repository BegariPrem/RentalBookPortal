package com.alacriti.rentalbookportal.resources;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.alacriti.rentalbookportal.utilities.AdminPage;

@Path("admin")
public class AdminResource {
	@Context HttpServletRequest request;
	@GET
	public Response adminHome() throws URISyntaxException
	{
		AdminPage adminPage=new AdminPage();
		return adminPage.admin(request);
	}
}
