package com.alacriti.rentalbookportal.bo;

import java.sql.Connection;
import java.util.List;

import com.alacriti.rentalbookportal.vo.BookRentReturn;
import com.alacriti.rentalbookportal.vo.PaginationResult;
import com.alacriti.rentalbookportal.vo.UserVO;

public interface UserServiceBO {
	public String lentBook(BookRentReturn customerBook,Connection con);
	public String returnBook(BookRentReturn customerBook,Connection con);
	public PaginationResult viewLentBooks(int customerId,Connection con);
	public String getRegister(UserVO user_details,Connection con);
	public UserVO googleValidation(String tokenId,Connection con);
	public List<BookRentReturn> paginationResults(PaginationResult pagination,Connection con);
}
