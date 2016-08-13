package com.alacriti.rentalbookportal.delegate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.rentalbookportal.bo.UserServiceBO;
import com.alacriti.rentalbookportal.bo.impl.UserServiceBOImpl;
import com.alacriti.rentalbookportal.utilities.ConnectionHelper;
import com.alacriti.rentalbookportal.vo.BookRentReturn;
import com.alacriti.rentalbookportal.vo.PaginationResult;
import com.alacriti.rentalbookportal.vo.UserVO;

import freemarker.template.TemplateException;

public class UserServiceDelegate {
	UserServiceBO userServiceBo=new UserServiceBOImpl();
	
	public String lentBook(BookRentReturn customerBook)throws SQLException
	{	
		Connection con=ConnectionHelper.getConnection();
		String status=userServiceBo.lentBook(customerBook,con);
		ConnectionHelper.colseConnection(con);
		return status;
	}
	
	public String returnBook(BookRentReturn customerBook)throws SQLException
	{	
		Connection con=ConnectionHelper.getConnection();
		String status=userServiceBo.returnBook(customerBook,con);
		ConnectionHelper.colseConnection(con);
		return status;
	}
	
	public PaginationResult viewLentBooks(int customerId)throws SQLException, IOException, TemplateException 
	{
		
		Connection con=ConnectionHelper.getConnection();
		PaginationResult pagination=userServiceBo.viewLentBooks(customerId,con);
		ConnectionHelper.colseConnection(con);
		return pagination;
	}
	public List<BookRentReturn> paginationCustomerResults(PaginationResult pagination)throws SQLException, IOException, TemplateException 
	{
		
		Connection con=ConnectionHelper.getConnection();
		List<BookRentReturn> list=userServiceBo.paginationResults(pagination, con);
		ConnectionHelper.colseConnection(con);
		return list;
	}
	
	public String getRegister(UserVO user_details) throws SQLException
	{	Connection con=ConnectionHelper.getConnection();
		String status= userServiceBo.getRegister(user_details,con);
		ConnectionHelper.colseConnection(con);
		return status;
	}
	 
	public UserVO googleValidation(String tokenId) 
	{
		Connection con=ConnectionHelper.getConnection();
		UserVO userDetails= userServiceBo.googleValidation(tokenId,con);
		ConnectionHelper.colseConnection(con);
		return userDetails;
	}
}
