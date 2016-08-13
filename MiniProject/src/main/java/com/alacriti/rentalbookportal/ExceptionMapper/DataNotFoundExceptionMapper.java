package com.alacriti.rentalbookportal.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper  implements ExceptionMapper<DataNotFoundException>{

	public Response toResponse(DataNotFoundException arg0) {
		return null;
	}

}
