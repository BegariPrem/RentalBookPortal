package com.alacriti.rentalbookportal.bo.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.dao.impl.BookSearchDao;
import com.alacriti.rentalbookportal.vo.BookVO;
import com.alacriti.rentalbookportal.vo.CategoryVO;
import com.alacriti.rentalbookportal.vo.PaginationResult;

public class BookSearchBo {
	Logger logger=Logger.getLogger(BookSearchBo.class);
	BookSearchDao bookSearchDao=new BookSearchDao();
	public String addBook(BookVO bookDetails,Connection con) 
	{
		logger.info("addbook() in BookSearchBo");
		return bookSearchDao.addBook(bookDetails,con);
		 
	}
	public List<CategoryVO> allCategories(Connection con) {
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		List<CategoryVO>categorySearch= bookSearchDao.getAllCategories(list,con);
		return categorySearch;
		
	}
	public List<BookVO> getAllBooksPagination(PaginationResult pagination,Connection con)
	{
	 
		ArrayList<BookVO> bookList=new ArrayList<BookVO>();
		List<BookVO> searchResult=bookSearchDao.getAllBooksPagination(pagination,bookList,con);
			return searchResult;	
				
	}
	public PaginationResult searchBookResult(BookVO bookSearch,Connection con)
	{
		ArrayList<BookVO> bookList=new ArrayList<BookVO>();
		PaginationResult pagination=bookSearchDao.searchBookResult(bookSearch, bookList, con);
			return pagination;
	}
	
	 
}
