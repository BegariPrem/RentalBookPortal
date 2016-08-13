package com.alacriti.rentalbookportal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.vo.BookVO;
import com.alacriti.rentalbookportal.vo.CategoryVO;
import com.alacriti.rentalbookportal.vo.PaginationResult;

public class BookSearchDao {
	Logger logger=Logger.getLogger(BookSearchDao.class);
	public String addBook(BookVO bookDetails,Connection con) 
	{
		Statement statement = null;
		ResultSet set=null;
		ResultSet bookValues=null;
		try {
			statement = con.createStatement();
		
			logger.info("book categoryId "+bookDetails.getBookCategory());
			String sql="select * from bk_catg_tbl where catgid="+bookDetails.getBookCategory()+";";
			logger.info(sql);
			set = statement.executeQuery(sql);
			if(set.next())
			{
				String bookInsertStatement="insert into bk_dtls_tbl(bookid,catg,bookname,author,availability,price)values(null,?,?,?,?,?);";
				PreparedStatement ps=con.prepareStatement(bookInsertStatement);
				ps.setLong(1,bookDetails.getBookCategory());
				ps.setString(2,bookDetails.getBookName());
				ps.setString(3,bookDetails.getBookAuthor());
				ps.setLong(4,bookDetails.getBookAvailability());
				ps.setLong(5, bookDetails.getBookPrice());
				ps.executeUpdate();
				String sql2="select * from bk_dtls_tbl where bookname='"+bookDetails.getBookName()+"';";
				String bookId="Book is created with id ";
				bookValues=statement.executeQuery(sql2);
				if(bookValues.next())
				{
					bookId=bookId+bookValues.getLong(1);
				}

				return bookId;
			}
			else{
				return "not added successfully";
			}
		
		} catch (SQLException e) {
			logger.info("error in booksearch dao add book method "+e.getMessage());
		}
		catch(Exception ex)
		{
			logger.info("error found in booksearch dao add book method "+ex.getMessage());
		}
		finally{
			 
					try {
						if(set!=null)
						{
							set.close();
						}
						
						if(bookValues!=null)
						{
							bookValues.close();
						}
					} catch (SQLException e) {
						 logger.info("exception while closing connection in bookSearchDao " +e.getMessage());
					}
					catch(Exception ex)
					{
						logger.info("booksearchDao error");
					}
			
			}
		return "something went wrong";
		}
	
	public List<CategoryVO> getAllCategories(ArrayList<CategoryVO> list,Connection con) 
	{
		ResultSet rs=null;
		try{
			Statement stmt=con.createStatement();
			String sql="select * from bk_catg_tbl;";
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
					CategoryVO cate=new CategoryVO(rs.getInt(1), rs.getString(2));
					list.add(cate);
			}
			return list;
		}
		catch(SQLException e)
		{
			logger.info("sql exception while getting categories "+e.getMessage());
		}
		catch(Exception ex)
		{
			logger.info("exception in booksearchDao getAllCategories() "+ex.getMessage());
		}
		finally{
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					logger.info("result set closing error "+e.getMessage());
				}
				catch(Exception ex1)
				{
					logger.info("exception in finally "+ex1.getMessage());
				}
			}
		}
		return list;
	}
	
	
	public List<BookVO> getAllBooksPagination(PaginationResult result,ArrayList<BookVO> bookList,Connection con)
	{
		PreparedStatement statement = null;
		ResultSet set = null;
		try {
			statement = con.prepareStatement(" SELECT bookname,author,price,bookid,availability FROM pagination_book_result where unique_id=? limit ?,?");
			 statement.setInt(1, result.getUnique_id());
			 statement.setInt(2, result.getStart());
			 statement.setInt(3,result.getEnd());
			 set=statement.executeQuery();
			while(set.next())
			{
				BookVO bookDetails=new BookVO(set.getString(1), set.getString(2),0, set.getLong(3),set.getLong(5));
				bookDetails.setBookId(set.getLong(4));
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
	 
	public PaginationResult searchBookResult(BookVO bookSearch,List<BookVO> bookList,Connection con)
	{
		Statement statement = null;
		Statement statement1 = null;
		ResultSet set = null;
		ResultSet rs2=null;
		PreparedStatement psmt;
		PaginationResult pagination=new PaginationResult();
		int unique_id=0;
		int i=0;
		try {
				statement = con.createStatement();
				statement1 = con.createStatement();
				con.setAutoCommit(false);
				String sql1="select max(unique_id) as unique_id from pagination_book_result;";
				rs2=statement1.executeQuery(sql1);
				if(rs2!=null&&rs2.next())
				{
					unique_id=rs2.getInt("unique_id")+1;
					
				}
				else{
					unique_id=1000;
				}
				String sql="select * from bk_dtls_tbl where catg="+bookSearch.getBookCategory()+" and (bookname like '%"+bookSearch.getBookName()+"%' or author like '%"+bookSearch.getBookAuthor()+"%');";
				set = statement.executeQuery(sql);
				while(set.next())
				{
					
					 
						psmt=con.prepareStatement("insert into pagination_book_result values(?,?,?,?,?,?,?);");
						psmt.setLong(1, unique_id);
						psmt.setLong(2, i);
						psmt.setString(3,set.getString("bookname"));
						psmt.setString(4, set.getString("author"));
						psmt.setLong(5, set.getLong("price"));
						psmt.setInt(6, set.getInt("bookid"));
						psmt.setInt(7,set.getInt("availability"));
						psmt.executeUpdate();
						i++;
					 
				}
				con.commit();
					pagination.setUnique_id(unique_id);
					pagination.setCount(i+1);
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
					if(statement!=null)
					{
						statement.close();
					}
					 
				} catch (SQLException e) {
					 logger.info("error while closing connection "+e.getMessage());
				}
		}
		return pagination;
	}
}
