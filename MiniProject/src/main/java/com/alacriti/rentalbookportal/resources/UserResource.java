package com.alacriti.rentalbookportal.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.delegate.UserServiceDelegate;
import com.alacriti.rentalbookportal.utilities.CustomerLentBooks;
import com.alacriti.rentalbookportal.utilities.FtlProcessClass;
import com.alacriti.rentalbookportal.vo.BookRentReturn;
import com.alacriti.rentalbookportal.vo.BookVO;
import com.alacriti.rentalbookportal.vo.PaginationResult;
import com.alacriti.rentalbookportal.vo.UserVO;

import freemarker.template.TemplateException;

@Path("user")
public class UserResource {
	@Context HttpServletRequest request;
	UserServiceDelegate userService=new UserServiceDelegate();
	Logger logger=Logger.getLogger(UserResource.class);
	
	@Path("/lentBook")
	@POST
	@Consumes("application/json")
	public String lentBook(BookRentReturn customerBook)throws SQLException
	{
		return userService.lentBook(customerBook);
	}
	
	@Path("/googleLogin")
	@POST
	public Response checkDetails(@FormParam("tokenId") String tokenId)
	{
		logger.info("checkDetails");
		 UserVO userDetails=userService.googleValidation(tokenId);
		 if(userDetails.getRole()==1||userDetails.getRole()==2)
		 {
			 HttpSession session=request.getSession(true);
			 logger.info("session created");
			 session.setAttribute("userName",  userDetails.getFirstName());
			 session.setAttribute("customerId",  userDetails.getUserId());
			 session.setAttribute("role",  userDetails.getRole());
			 try {
				return Response.seeOther(new URI("/home")).build();
			} catch (URISyntaxException e) {
			 
				e.printStackTrace();
			}
			 return Response.status(Status.NO_CONTENT).entity("some Error").build();
		 }
		 else{
			 try {
				 HttpSession session=request.getSession(true);
				 session.setAttribute("userName",userDetails.getFirstName());
				return Response.seeOther(new URI("/home/index")).build();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			 return Response.status(Status.NO_CONTENT).entity("some Error").build();
		 }
	}
	
	@Path("/rentBook")
	@POST
	@Consumes("application/json")
	public String returnBook(BookRentReturn customerBook)throws SQLException
	{
		return userService.returnBook(customerBook);
	}
	
	
	@Path("/customer/{customerId}")
	@Produces("application/json")
	@GET
	public PaginationResult viewBooks(@PathParam("customerId") int customerId)throws SQLException, IOException, TemplateException
	{
		logger.info("called me in view books resource, "+customerId);
		PaginationResult pagination= userService.viewLentBooks(customerId);
		System.out.println("process done in pagination"+pagination.getCount()+" "+pagination.getUnique_id());
		return pagination;
	}
	
	@Path("/register")
	@Produces("text/html")
	@POST
	public String addCustomer(UserVO user_details) throws SQLException{
	
		return userService.getRegister(user_details);
	
	}
	
	
	@GET
	@Path("/customerPagination")
	@Produces("text/html")
	public String getPagination(@QueryParam("unique_id") int unique_id,@QueryParam("offset") int start,@QueryParam("end") int end) throws SQLException, IOException, TemplateException
	{
			logger.info("pagination called");
			List<BookRentReturn> list=userService.paginationCustomerResults(new PaginationResult(unique_id,start,end));
			return CustomerLentBooks.processFtl(list);
	}
	
	
	@Path("/logout")
	@GET
	public Response logOut()
	{
		HttpSession session=request.getSession(false);
		if(session!=null)
		{
		
		session.invalidate();
		try {
			logger.info("session invalidated");
			return Response.temporaryRedirect(new URI("/login")).build();
		} catch (URISyntaxException e) {
			 
			e.printStackTrace();
		}
		}
		try {
			return Response.temporaryRedirect(new URI("/login")).build();
		} catch (URISyntaxException e) {
		 
			e.printStackTrace();
		}
		return Response.status(Status.CREATED).build();
	}
	
	
}
