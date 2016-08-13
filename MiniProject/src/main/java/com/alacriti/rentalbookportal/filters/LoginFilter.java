package com.alacriti.rentalbookportal.filters;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.resources.AccessDeniedFtl;

 @Provider
public class LoginFilter implements ContainerRequestFilter{
	private static final CharSequence LOGIN_PATH = "login";
	private static final CharSequence LOGOUT_PATH = "logout";
	private static final CharSequence GOOGLELOGIN_PATH = "user/googleLogin";
	private static final CharSequence check = "check";
	@Context HttpServletRequest httpServletRequest;
	Logger logger=Logger.getLogger(LoginFilter.class);
	public void filter(ContainerRequestContext context) throws IOException {
		logger.info("in filter");
		HttpSession session=httpServletRequest.getSession(false);
		
		if(session!=null){
			 System.out.println("session");
				if(context.getUriInfo().getPath().contains(LOGIN_PATH))
				{
						Long role=(Long)session.getAttribute("role");
						System.out.println(role);
					try {
							if(role!=null &&(role==1||role==2))
							{
								context.abortWith(Response.seeOther(new URI("home")).build());
							}
							else{

								
									context.abortWith(Response.seeOther(new URI("home/index")).build());
								
							}
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
				else{
			 logger.info(context.getUriInfo().getAbsolutePath());
			logger.info("Session exists");
			return ;
				}
	
	}
		
		if(context.getUriInfo().getPath().contains(LOGIN_PATH)||context.getUriInfo().getPath().contains(LOGOUT_PATH)||context.getUriInfo().getPath().contains(GOOGLELOGIN_PATH)||context.getUriInfo().getPath().contains(check)){
			logger.info("in path excluded");
			return ;
		}
		
		else{
				Response forbidden=Response.status(Response.Status.FORBIDDEN)
				.entity(AccessDeniedFtl.notAllowed())
				.build();
				context.abortWith(forbidden);
		}
	}
}