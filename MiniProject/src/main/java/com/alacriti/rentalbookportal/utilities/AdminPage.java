package com.alacriti.rentalbookportal.utilities;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class AdminPage {
	
	public Response admin(@Context HttpServletRequest request)
	{
		Configuration configuration=new Configuration();
		
		HashMap<String,Object> input=new HashMap<String, Object>();
		StringWriter str=new StringWriter();
		configuration.setClassForTemplateLoading(HomePageGenerator.class,"/templates");
		Template tmp=null;
		HttpSession session=request.getSession(false);
		if(session!=null)
		{
			String name=(String) session.getAttribute("userName");
			Long userId=(Long) session.getAttribute("customerId");
			Long roleId=(Long) session.getAttribute("role");
			if(roleId==2)
			{
				try {
					tmp=configuration.getTemplate("AdminFunctions.ftl");
					input.put("userName", name);
					input.put("customerId", userId);
					input.put("userRole",roleId);
					tmp.process(input, str);
					return Response.status(200).entity(str.toString()).build();
				}
				 catch (TemplateException e) {
					 
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}
		return Response.status(Response.Status.ACCEPTED).entity("someError").build();
	}
}
