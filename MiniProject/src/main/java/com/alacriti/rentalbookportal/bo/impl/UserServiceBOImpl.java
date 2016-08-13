package com.alacriti.rentalbookportal.bo.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.bo.UserServiceBO;
import com.alacriti.rentalbookportal.dao.impl.UserDao;
import com.alacriti.rentalbookportal.vo.BookRentReturn;
import com.alacriti.rentalbookportal.vo.PaginationResult;
import com.alacriti.rentalbookportal.vo.UserVO;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

public class UserServiceBOImpl implements UserServiceBO {
	
	private static final String CLIENT_ID="878089752133-mv37j73qssfhtnbl8g9lmi26ljntg3j4.apps.googleusercontent.com";
	private static final HttpTransport transport = new NetHttpTransport();
	private static final JsonFactory jsonFactory = new JacksonFactory();
	Logger logger=Logger.getLogger(UserServiceBOImpl.class);
	UserDao userDao=new UserDao();
	public String lentBook(BookRentReturn customerBook,Connection con)
	{
		
		return userDao.lentBook(customerBook,con);
	}

	public String returnBook(BookRentReturn customerBook,Connection con)
	{
		return userDao.rentBook(customerBook,con);
	}

	public PaginationResult viewLentBooks(int customerId,Connection con)
	{
		 
		return userDao.viewLentBooks(customerId,con);
	
	}
	public List<BookRentReturn> paginationResults(PaginationResult pagination,Connection con)
	{
		ArrayList<BookRentReturn> list=new ArrayList<BookRentReturn>();
		return userDao.getAllBooksPagination(pagination, list, con);
	}
	public String getRegister(UserVO userDetails,Connection con){
		int userId=0;
		try 
		{
			userId = userDao.getRegister(userDetails,con);
		} catch (SQLException e) 
		{
				logger.info("getRegister() in userService bo impl "+e.getMessage());
		}
		return "userId is "+userId;
	}

	public UserVO googleValidation(String tokenId,Connection con) 
	{
		UserVO userDetails=new UserVO();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
    .setAudience(Arrays.asList(CLIENT_ID)) .setIssuer("accounts.google.com")
    .build();
		try{
				GoogleIdToken idToken = verifier.verify(tokenId);
				if (idToken != null) 
				{
					  Payload payload = idToken.getPayload();
					  String userId = payload.getSubject();
					 logger.info("User ID: " + userId);
					  String email = payload.getEmail();
					  String name = (String) payload.get("name");
					  String familyName = (String) payload.get("family_name");
					  String givenName = (String) payload.get("given_name");
					  logger.info("name"+name+"\t"+email+"\t"+familyName+givenName);
					 userDetails=userDao.googleValidation(email,con);
					 if(userDetails.getRole()==1||userDetails.getRole()==2)
					 {
						 return userDetails;
					 }
					 else{
						 userDetails.setFirstName(givenName);
						 return userDetails;
					 }
				}
		}
		catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userDetails;
	}

	 
}
 
