package com.alacriti.rentalbookportal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.utilities.ConnectionHelper;
import com.alacriti.rentalbookportal.vo.UserVO;

public class LoginValidationDao 
{
	public UserVO  validationStatus(String email,String password,Connection con)
	{
		UserVO userDetails=new UserVO();
		PreparedStatement stmt=null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		String dbPassword="",name="";
		Long role=0L,userId=0L;
	
		Logger logger=Logger.getLogger(LoginValidationDao.class);
		String sql="select userid,firstname,password,roleid from user_dtls_tbl where emailid=?;";
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			stmt.setString(1,email);
			rs = stmt.executeQuery();
			logger.info("Inside validation Dao " +email);
			while(rs.next())
			{
				 dbPassword=rs.getString("password");
				 name=rs.getString("firstName");
				 role=rs.getLong("roleid");
				 logger.info("checking "+dbPassword+name+role);
				 userId=rs.getLong("userid");
				 logger.info("db password is \t"+dbPassword+"  foundme");
				 if(dbPassword.equals(""))
					{
						String sql1="update user_dtls_tbl set password=? where emailid=?;";
						statement=con.prepareStatement(sql1);
						statement.setString(1,password);
						statement.setString(2, email);
						statement.executeUpdate();
						con.commit();
						logger.info("password updated");
						userDetails=new UserVO(name, role);
						userDetails.setUserId(userId);
						
					}
					else if(password.equals(dbPassword))
					{
						userDetails=new UserVO(name, role);
						userDetails.setUserId(userId);
					}
			}
			
			return userDetails;
			
		} catch (SQLException e) {
			logger.info("Exception in Login Validation Dao "+e.getMessage());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.info("Exception in Dao "+ex.getMessage());
		}
		finally{
			
			try{
				if(rs!=null)
				{
					rs.close();
				}
				if(stmt!=null)
				{
					stmt.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
			}
			catch(SQLException sqlException)
			{
				logger.error("Error while closing sql statements "+sqlException.getMessage());
			}
			
		}
		return userDetails;
	}
		

}
