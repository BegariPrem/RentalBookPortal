package com.alacriti.rentalbookportal.bo.impl;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.dao.impl.LoginValidationDao;
import com.alacriti.rentalbookportal.vo.UserVO;

public class LoginValidationBo {
	
	public UserVO loginStatus(String email,String password,Connection con) 
	{
		Logger logger=Logger.getLogger(LoginValidationBo.class);
		LoginValidationDao loginValidationDao=new LoginValidationDao();
		UserVO userDetails= loginValidationDao.validationStatus(email,password,con);
		logger.info(userDetails.getFirstName()+" "+userDetails.getRole());
		return userDetails;
	}
}
