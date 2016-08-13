package com.alacriti.rentalbookportal.delegate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.alacriti.rentalbookportal.bo.impl.BookSearchBo;
import com.alacriti.rentalbookportal.utilities.ConnectionHelper;
import com.alacriti.rentalbookportal.vo.BookVO;
import com.alacriti.rentalbookportal.vo.CategoryVO;
import com.alacriti.rentalbookportal.vo.PaginationResult;

public class BookServiceDelegate {
	BookSearchBo bookSearchBo=new BookSearchBo();
	public String addBook(BookVO bookDetails)throws SQLException
	{
		Connection con=ConnectionHelper.getConnection();
		String status=bookSearchBo.addBook(bookDetails,con);
		ConnectionHelper.colseConnection(con);
		return status;
	}
	public List<CategoryVO> getAllCategories() 
	{
		Connection con=ConnectionHelper.getConnection();
		List<CategoryVO> list=bookSearchBo.allCategories(con);
		ConnectionHelper.colseConnection(con);
		return list;
	}
	public List<BookVO>  getAllBooksCategory(PaginationResult pagination) 
	{
		Connection con=ConnectionHelper.getConnection();
		List<BookVO> status=bookSearchBo.getAllBooksPagination(pagination,con);
		ConnectionHelper.colseConnection(con);
		return status;
	}
	public PaginationResult searchBookResult(BookVO bookSearch)
	{
		Connection con=ConnectionHelper.getConnection();
		PaginationResult pagination=bookSearchBo.searchBookResult(bookSearch, con);
		ConnectionHelper.colseConnection(con);
		return pagination;
	}
	
	
}
