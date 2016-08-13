package com.alacriti.rentalbookportal.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionHelper {
	Logger logger=Logger.getLogger(ConnectionHelper.class);
	public static Connection getConnection(){
		Connection con=null;
		Logger logger=Logger.getLogger(ConnectionHelper.class);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://192.168.35.70:3306/bookrent_dev","bookrent_dev","bookrent_dev");
			return con;
		} 
		 catch (ClassNotFoundException e) {
			 logger.error("Driver class not found" +e.getMessage());
			}
		catch (SQLException ex) {
			logger.error("Connection not found "+ex.getMessage());
		}
		return con;
	}
	public static void colseConnection(Connection con)
	{
		Logger logger1=Logger.getLogger(ConnectionHelper.class);
		if(con!=null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				logger1.error("connection can not be closed" +e.getMessage());
			}
			
		}
		
	}
}
