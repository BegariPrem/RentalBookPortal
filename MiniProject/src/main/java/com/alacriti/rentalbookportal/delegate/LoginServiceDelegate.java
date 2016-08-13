package com.alacriti.rentalbookportal.delegate;

import java.sql.Connection;

import com.alacriti.rentalbookportal.bo.impl.LoginValidationBo;
import com.alacriti.rentalbookportal.utilities.ConnectionHelper;
import com.alacriti.rentalbookportal.vo.UserVO;


public class LoginServiceDelegate {
	Connection con=ConnectionHelper.getConnection();
	public UserVO  loginValidate(String email,String password) 
	
	{	 
		 LoginValidationBo loginValidationBo=new LoginValidationBo();
		 UserVO userDetails=loginValidationBo.loginStatus(email,password,con);
		 ConnectionHelper.colseConnection(con);
		 return userDetails;
		 
	}
	
	
}
