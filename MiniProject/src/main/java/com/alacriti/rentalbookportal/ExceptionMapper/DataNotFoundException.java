package com.alacriti.rentalbookportal.ExceptionMapper;

public class DataNotFoundException extends RuntimeException{
 
	private static final long serialVersionUID = 5594339473343692415L;
	public DataNotFoundException(String message)
	{
		super(message);
	}
}
