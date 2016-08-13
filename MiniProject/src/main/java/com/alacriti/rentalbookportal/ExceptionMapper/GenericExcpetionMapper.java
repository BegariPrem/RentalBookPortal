package com.alacriti.rentalbookportal.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.alacriti.rentalbookportal.resources.AccessDeniedFtl;
 @Provider
public class GenericExcpetionMapper extends Throwable implements ExceptionMapper<Throwable>{
	 
	private static final long serialVersionUID = 5330300031885415030L;

	public Response toResponse(Throwable aa) {
		return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).entity(AccessDeniedFtl.AccessDenied()).type("text/html").build();
	}

}
