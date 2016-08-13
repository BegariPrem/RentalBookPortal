package com.alacriti.rentalbookportal.utilities;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.resources.AccessDeniedFtl;
import com.alacriti.rentalbookportal.vo.UserVO;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class HomePageGenerator {
	Logger logger=Logger.getLogger(HomePageGenerator.class);
	HashMap<String,Object> input=new HashMap<String, Object>();
	StringWriter str=new StringWriter();
	Configuration configuration=new Configuration();
	Template tmp=null;
	public Response homePage(@Context HttpServletRequest request)
	{
		
	
		
		configuration.setClassForTemplateLoading(HomePageGenerator.class,"/templates");
		
		HttpSession session=request.getSession(false);
		
		Long role=(Long)session.getAttribute("role");
		if(role==null)
		{
			return Response.status(Status.BAD_REQUEST).entity("You are not registred customer").build();
		}

		if(role==1)
		{
		
			try {
				tmp=configuration.getTemplate("UserHomePage.ftl");
				input.put("userName",(String)(session.getAttribute("userName")));
				input.put("customerId",(Long)(session.getAttribute("customerId")));
				tmp.process(input, str);
				 
				return Response.status(Response.Status.ACCEPTED).entity(str.toString()).build();
				
				
			}
			catch(IOException e)
			{
				logger.info("User Ftl not found "+e.getMessage());
			} catch (TemplateException e) {
				logger.info("Template Exception  found in user "+e.getMessage());
			}
			catch(Exception e)
			{
				logger.info("exception in user "+e.getMessage());
			}
			
		}
		if (role==2)
		{

			try {
				tmp=configuration.getTemplate("AdminHomePage.ftl");
				input.put("userName",(String)(session.getAttribute("userName")));
				input.put("customerId",(Long)(session.getAttribute("customerId")));
				tmp.process(input, str);
				return Response.status(Response.Status.ACCEPTED).entity(str.toString()).build();
				
				
			}
			catch(IOException e)
			{
				logger.info("User Ftl not found "+e.getMessage());
			} catch (TemplateException e) {
				logger.info("Template Exception  found in user "+e.getMessage());
			}
			catch(Exception e)
			{
				logger.info("exception in user "+e.getMessage());
			}
			return Response.status(Response.Status.ACCEPTED).entity("something went wrong").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity("something went wrong").build();
	}
	
		public Response generateLoginPage()
		{
			
		try {
			configuration.setClassForTemplateLoading(HomePageGenerator.class,"/templates");
				tmp=configuration.getTemplate("login.ftl");
				input.put("loginMessage", "please provide Correct Credentials");
				tmp.process(input, str);
				return Response.status(200).entity(str.toString()).build();
				
				
			}
			catch(IOException e)
			{
				logger.info("User Ftl not found "+e.getMessage());
			} catch (TemplateException e) {
				logger.info("Template Exception  found in user "+e.getMessage());

			}
			catch(Exception e)
			{
				logger.info("exception in user "+e.getMessage());
			}
		return Response.status(Status.CREATED).entity("something went wrong in userHomePage").build();
		}
		
		public Response googleUserPage(@Context HttpServletRequest request)
		{
			
		try {
			
				HttpSession session=request.getSession(false);
				Long role=(Long)session.getAttribute("role");
				if(role!=null)
				{
					return Response.status(Status.BAD_REQUEST).entity(AccessDeniedFtl.notAllowed()).build();
				}
				else{
				configuration.setClassForTemplateLoading(HomePageGenerator.class,"/templates");
				tmp=configuration.getTemplate("GoogleUser.ftl");
				input.put("userName",(String)(session.getAttribute("userName")));
				tmp.process(input, str);
				return Response.status(200).entity(str.toString()).build();
				}
				
			}
			catch(IOException e)
			{
				logger.info("User Ftl not found "+e.getMessage());
			} catch (TemplateException e) {
				logger.info("Template Exception  found in user "+e.getMessage());

			}
			catch(Exception e)
			{
				logger.info("exception in user "+e.getMessage());
			}
		return Response.status(Status.CREATED).entity("something went wrong in userHomePage").build();
		}
		
	}


