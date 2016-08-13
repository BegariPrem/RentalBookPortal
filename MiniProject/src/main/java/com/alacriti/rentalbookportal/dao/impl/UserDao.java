package com.alacriti.rentalbookportal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.utilities.ConnectionHelper;
import com.alacriti.rentalbookportal.vo.BookRentReturn;
import com.alacriti.rentalbookportal.vo.BookVO;
import com.alacriti.rentalbookportal.vo.PaginationResult;
import com.alacriti.rentalbookportal.vo.UserVO;

public class UserDao {
	
	Logger logger=Logger.getLogger(UserDao.class);
	public String lentBook(BookRentReturn customerBook,Connection con){
			ResultSet rs=null;
			PreparedStatement ps=null;
			PreparedStatement ps2=null;
			ResultSet rs2=null;
			long bookId;
			long customerId;
			int bookCount=0;
			int price=0;
			try{
				
				bookId=customerBook.getBookId();
				customerId=customerBook.getCustomerId();
			 
				con.setAutoCommit(false);
				ps=con.prepareStatement("select * from bk_dtls_tbl where bookid=?;");
				ps.setLong(1, bookId);
				rs=ps.executeQuery();
				if(rs.next())
				{
					bookCount=rs.getInt(5);
					if(bookCount>0)
					{
						price=(rs.getInt(6))/2;
						ps2=con.prepareStatement("select * from user_dtls_tbl where userid=?;");
						ps2.setLong(1, customerId);
						rs2=ps2.executeQuery();
						if(rs2.next())
						{
							
							PreparedStatement ps3=con.prepareStatement("insert into issue_return_tbl(sno,userid,bookid,issuedate,amtpaid)values(null,?,?,?,?);");
							ps3.setLong(1, customerId);
							ps3.setLong(2, bookId);
							Calendar cal=Calendar.getInstance();
							java.util.Date currentDate=cal.getTime();
							java.sql.Date date=new java.sql.Date(currentDate.getTime());
							ps3.setDate(3,date);
							ps3.setLong(4,price);
							ps3.executeUpdate();
							PreparedStatement ps7=con.prepareStatement("update bk_dtls_tbl set availability=availability-1 where bookid=?;");
							ps7.setLong(1,bookId);
							ps7.executeUpdate();
							con.commit();
							
							return "Take "+price+" As caution Deposit";
						}
						else{
							
							return "customer Id not found";
						}
					}
					else{
						return "These Books are not available in store Now";
					}
				}
				else{
					return "book id not found";
				}
			}
			catch(SQLException e)
			{
				logger.info("sql error in userDao lent book method "+e.getMessage());
			}
			catch(Exception ex){
				logger.info("exception in userDao lent book method "+ex.getMessage());
			}
			finally{
				try{
					if(rs!=null)
					{
						rs.close();
					}
					if(rs2!=null)
					{
						rs2.close();
					}
					 
				}
				catch(SQLException ex1)
				{
					logger.info("error while closing sql statements in userDao book lent method "+ex1.getMessage());
				}
			}
		return "some thing went wrong";
	}

	public String rentBook(BookRentReturn customerBook,Connection con){
		int noOfDays=0;
		long price=0;
		long rent=0;
		long bookId=0;
		long customerId=0;
		ResultSet rs=null;		
		ResultSet rs2=null;
		java.sql.Date issueDate = null;
		
		try{
			bookId=customerBook.getBookId();
			customerId=customerBook.getCustomerId();
			 
			con.setAutoCommit(false);
			PreparedStatement ps=con.prepareStatement("select * from issue_return_tbl where bookid=?;");
			ps.setLong(1, bookId);
			rs=ps.executeQuery();
			if(rs.next())
			{
				price=(rs.getInt(5)*2);
				PreparedStatement ps2=con.prepareStatement("select * from issue_return_tbl where userid=? and bookid=? and returndate is null");
				ps2.setLong(1, customerId);
				ps2.setLong(2, bookId);
				rs2=ps2.executeQuery();
				
				if(rs2.next())
				{
					issueDate=rs2.getDate(4);
					Calendar cal1=Calendar.getInstance();
					java.util.Date currentDate=cal1.getTime();
					java.sql.Date returnDate=new java.sql.Date(currentDate.getTime());
					PreparedStatement ps4=con.prepareStatement("SELECT DATEDIFF(?,?) as noOfDays;");
					ps4.setDate(1, returnDate);
					ps4.setDate(2, issueDate);
					
					ResultSet rs4=ps4.executeQuery();
					while(rs4.next())
					{
						noOfDays=rs4.getInt("noOfDays");
					}
					float rentVariable=(float)((2*price)/100);
					rent=(long)(rentVariable*noOfDays);
					logger.info(price+"   "+(price/2)+"  "+issueDate+"   "+returnDate+" "+noOfDays+" rent "+rent);
					
						if((price/2)<rent)
						{
							PreparedStatement ps5=con.prepareStatement("update issue_return_tbl set returndate=?,amtretd=?,amtpaid=? where userid=? and issuedate=? and bookid=? and returndate is null order by sno asc limit 1;");
							ps5.setDate(1,returnDate);
							ps5.setLong(2,0);
							ps5.setLong(3,(price/2)+rent);
							ps5.setLong(4,customerId);
							ps5.setDate(5,issueDate);
							ps5.setLong(6, bookId);
							ps5.executeUpdate();
							PreparedStatement ps7=con.prepareStatement("update bk_dtls_tbl set availability=availability+1 where bookid=?;");
							ps7.setLong(1,bookId);
							ps7.executeUpdate();
							con.commit();
							return "Please take "+((rent)-(price/2))+" Rs as extra Rent";
						}
						else if((price/2)>rent){
							
							PreparedStatement ps3=con.prepareStatement("update issue_return_tbl set returndate=?,amtretd=? where userid=? and issuedate=? and bookid=? and returndate is null order by sno asc limit 1;");
							ps3.setDate(1,returnDate);
							ps3.setLong(2,(price/2)-rent);
							ps3.setLong(3,customerId);
							ps3.setDate(4,issueDate);
							ps3.setLong(5, bookId);
							ps3.executeUpdate();
							PreparedStatement ps7=con.prepareStatement("update bk_dtls_tbl set availability=availability+1 where bookid=?;");
							ps7.setLong(1,bookId);
							ps7.executeUpdate();
							con.commit();
							return "Please return "+((price/2)-rent)+" Rs  to customer" ;
						}
						else{
							PreparedStatement ps6=con.prepareStatement("update issue_return_tbl set returndate=?,amtretd=? where userid=? and issuedate=? and bookid=? and returndate is null order by sno asc limit 1;");
							ps6.setDate(1,returnDate);
							ps6.setLong(2,(price/2)-rent);
							ps6.setLong(3,customerId);
							ps6.setDate(4,issueDate);
							ps6.setLong(5, bookId);
							ps6.executeUpdate();
							PreparedStatement ps8=con.prepareStatement("update bk_dtls_tbl set availability=availability+1 where bookid=?;");
							ps8.setLong(1,bookId);
							ps8.executeUpdate();
							con.commit();
							return "Rent is Equal to Caution Desposit" ;

							
						}
			}
			else{
					return "customer does not has to return any books";
				}
		}
		else{
				return "book id not found";
			}
		}
		catch(SQLException e)
		{
			logger.info("sql exception in userDao rent book "+e.getMessage());
		}
		catch(Exception ex)
		{
			logger.info("exception in userDao "+ex.getMessage());
		}
		finally
		{
			try{
				if(rs!=null)
				{
					rs.close();
				}
				if(rs2!=null)
				{
					rs2.close();
				}
			 
			}
			catch(SQLException e)
			{
				logger.info("error in userDao rent Book "+e.getMessage());
			}
			catch(Exception ex)
			{
				logger.info("exception in userDao rent book() "+ex.getMessage());
			}
		}
		return "something went wrong";
	}
	
	
	public List<BookRentReturn> getAllBooksPagination(PaginationResult result,ArrayList<BookRentReturn> bookList,Connection con)
	{
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			statement = con.prepareStatement(" SELECT bookid,bookname,issueddate,category,amtpaid FROM pagination_customerbook_result where unique_id=? limit ?,?");
			 statement.setInt(1, result.getUnique_id());
			 statement.setInt(2, result.getStart());
			 statement.setInt(3,result.getEnd());
			 set=statement.executeQuery();
			while(set.next())
			{
				BookRentReturn bookDetails=new BookRentReturn(set.getString("bookname"),0,set.getLong("bookid"),set.getDate("issueddate"));
				
				bookDetails.setAmountPaid(set.getLong("amtpaid"));
				bookDetails.setCategory(set.getString("category"));
				bookList.add(bookDetails);
			}
		}
		catch(SQLException e)
		{
				logger.info("exception in bookSearch dao "+e.getMessage());
		}
		finally{
			try {
				if(set!=null)
				{
					set.close();
				}
				if(statement!=null)
				{
					statement.close();
				}
			} catch (SQLException e) {
				 logger.info("error while closing connection "+e.getMessage());
			}
		}
		return bookList;
	}
	
	
	public PaginationResult viewLentBooks(int customerId,Connection con) 
	{
		Statement statement = null;
		Statement statement1 = null;
		ResultSet rs3=null;
		ResultSet set = null;
		ResultSet rs2=null;
		PreparedStatement psmt;
		PreparedStatement psmt1;
		PaginationResult pagination=new PaginationResult();
		int unique_id=0;
		int i=0;
		try {
				statement = con.createStatement();
				statement1 = con.createStatement();
				con.setAutoCommit(false);
				String sql="select max(unique_id) as unique_id from pagination_customerbook_result;";
				set=statement.executeQuery(sql);
				if(set!=null&&set.next())
				{
					unique_id=set.getInt("unique_id")+1;
					
				}
				else{
					unique_id=1000;
				}
				psmt=con.prepareStatement("select a.bookname, a.catg, a.bookid,b.issuedate,b.amtpaid from bk_dtls_tbl a, issue_return_tbl b where a.bookid=b.bookid and returndate is null and userid=?;");
				psmt.setLong(1,customerId);
				rs2= psmt.executeQuery();
				while(rs2.next())
				{
					System.out.println(rs2.getLong(2));
					String sql2="select catg from bk_catg_tbl where catgid="+rs2.getLong(2)+";";
					 rs3=statement1.executeQuery(sql2);
					String category="";
					if(rs3.next())
					{
						category=rs3.getString("catg");
					}
					psmt1=con.prepareStatement("insert into pagination_customerbook_result values(?,?,?,?,?,?,?,?);");
					psmt1.setInt(1, unique_id);
					psmt1.setInt(2, i);
					psmt1.setInt(3,customerId);
					psmt1.setLong(4,rs2.getLong("bookid"));
					psmt1.setString(5, rs2.getString("bookname"));
					psmt1.setDate(6, rs2.getDate("issuedate"));
					psmt1.setString(7, category);
					psmt1.setInt(8,rs2.getInt("amtpaid"));
					psmt1.executeUpdate();
					i++;
					 
				}
				con.commit();
					pagination.setUnique_id(unique_id);
					pagination.setCount(i);
					System.out.println("pagination results" +pagination.getUnique_id()+" "+pagination.getCount());
					return pagination;
				 
		}
		catch(SQLException e)
		{
				logger.info("exception in bookSearch dao "+e.getMessage());
				e.printStackTrace();
		}
		finally{
				try {
					if(set!=null)
					{
						set.close();
					}
					if(rs2!=null)
					{
						rs2.close();
					}
					if(rs3!=null)
					{
						rs3.close();
					}
					if(statement!=null)
					{
						statement.close();
					}
					if(statement1!=null)
					{
						statement1.close();
					}
					 
				} catch (SQLException e) {
					 logger.info("error while closing connection "+e.getMessage());
				}
		}
		return pagination;
		
		
		
	}

	
	public int getRegister(UserVO userDetails,Connection con)throws SQLException
	{
	 
		ResultSet rs1=null;
		int userid=0;
		try{
			
			con.setAutoCommit(false);
			String sql="insert into user_dtls_tbl(userid,firstname,lastname,emailid,password,contactnum) values(null,?,?,?,?,?);";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,userDetails.getFirstName());
			ps.setString(2, userDetails.getLastName());
			ps.setString(3,userDetails.getEmailId());
			ps.setString(4,"");
			ps.setLong(5, userDetails.getPhoneNumber());
			ps.executeUpdate();
			
			String sql1="select * from user_dtls_tbl where emailid='"+userDetails.getEmailId()+"';";
			Statement stmt=con.createStatement();
			rs1=stmt.executeQuery(sql1);
			
			while(rs1.next())
			{
				userid=rs1.getInt(1);
			}
			con.commit();
			return userid;
			
		}
		catch(SQLException e)
		{
			logger.info("sql exception while adding a customer " +e.getMessage());
		}
		finally{
			try{
				if(rs1!=null)
				{
					rs1.close();
				}
			}
			catch(SQLException ex)
			{
				logger.info("sql exception while closing userDao getRegister() "+ex.getMessage());
			}
			catch(Exception e)
			{
				logger.info("exception in userDao getRegister()");
			}
		}
		return userid;
	}

	public UserVO googleValidation(String email,Connection con) {
		UserVO userDetails=new UserVO();
		PreparedStatement stmt=null;
		 
		PreparedStatement statement=null;
		ResultSet rs=null;
		String dbPassword="",name="";
		Long role=0L,userId=0L;
	
		String sql="select userid,firstname,password,roleid from user_dtls_tbl where emailid=?;";
		try {
			 
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			stmt.setString(1,email);
			rs = stmt.executeQuery();
			logger.info("Inside validation Dao " +email);
			if(rs.next())
			{
				 
				 name=rs.getString("firstName");
				 role=rs.getLong("roleid");
				 logger.info("checking "+dbPassword+name+role);
				 userId=rs.getLong("userid");
				 logger.info("db password is \t"+dbPassword+"  foundme");
				  
				
						userDetails=new UserVO(name, role);
						userDetails.setUserId(userId);
					return userDetails;
			}
			else{
				userDetails.setEmailId(email);
				return userDetails;
			}
		
			
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
