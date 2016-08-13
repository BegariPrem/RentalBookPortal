package com.alacriti.rentalbookportal.resources;

import java.net.URI;
 


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.delegate.LoginServiceDelegate;
import com.alacriti.rentalbookportal.utilities.HomePageGenerator;
import com.alacriti.rentalbookportal.vo.UserVO;

@Path("login")
@Produces("text/html")
public class LoginResource {
	LoginServiceDelegate loginServiceDelegate=new LoginServiceDelegate();
	Logger logger=Logger.getLogger(LoginResource.class);
	@Context HttpServletRequest httpServletRequest;
	@POST
	public Response loginValidate(@FormParam("userEmail") String email1,@FormParam("userPassword") String password1)throws Exception
	{
		logger.info(email1+" "+password1);
		UserVO userDetails=loginServiceDelegate.loginValidate(email1,password1);
		if(userDetails.getRole()==1||userDetails.getRole()==2)
		{
			HttpSession session=httpServletRequest.getSession(true);
			session.setAttribute("userName",  userDetails.getFirstName());
			session.setAttribute("customerId",  userDetails.getUserId());
			session.setAttribute("role",  userDetails.getRole());
			return Response.seeOther(new URI("/home")).build();
		}
		else{
			HomePageGenerator homepage=new HomePageGenerator();
			return homepage.generateLoginPage();
		}
	}
	@GET
	public Response getLoginPage()
	{
		HomePageGenerator homepage=new HomePageGenerator();
		return homepage.generateLoginPage();
	}
}
