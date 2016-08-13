package com.alacriti.rentalbookportal.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;

import com.alacriti.rentalbookportal.delegate.BookServiceDelegate;
import com.alacriti.rentalbookportal.delegate.PaginationService;
import com.alacriti.rentalbookportal.utilities.FtlProcessClass;
import com.alacriti.rentalbookportal.vo.BookVO;
import com.alacriti.rentalbookportal.vo.CategoryVO;
import com.alacriti.rentalbookportal.vo.PaginationResult;

@Path("books")
public class BooksResource 
{
	Logger logger=Logger.getLogger(BooksResource.class);
	PaginationService pagination=new PaginationService();
	BookServiceDelegate bookService=new BookServiceDelegate();
	List<BookVO> searchBookResult=new ArrayList<BookVO>();
	
 
	
	@GET
	@Path("/searchBookResultPagination")
	@Produces("text/html")
	public String getPagination(@QueryParam("unique_id") int unique_id,@QueryParam("offset") int start,@QueryParam("end") int end)
	{
			logger.info("pagination called");
			List<BookVO> list=bookService.getAllBooksCategory(new PaginationResult(unique_id,start,end));
			return FtlProcessClass.getAllResults(list);
	}
	
	@Path("/addbooks")
	@POST
	@Consumes("application/json")
	public String addBook(BookVO addBook) throws SQLException
	{
		 logger.info("add books resource called");
		return bookService.addBook(addBook);
	}
	
	@Path("/allCategories")
	@GET
	public String getAllCategories()
	{
		logger.info("get All categories method called");
		List<CategoryVO>list= bookService.getAllCategories();
		return FtlProcessClass.getAllCategories(list);
	}
	
	@Path("/bookSearch")
	@POST
	@Produces("application/json")
	 
	public PaginationResult searchBooks(BookVO bookSearch)
	{
		logger.info("new Method called");
		return bookService.searchBookResult(bookSearch);
	}
	 
}
