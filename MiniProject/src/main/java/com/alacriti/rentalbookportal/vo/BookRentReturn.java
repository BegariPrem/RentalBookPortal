package com.alacriti.rentalbookportal.vo;

import java.sql.Date;

public class BookRentReturn {
	private long customerId;
	private String bookName;
	private long bookId;
	private Date issueDate;
	private long amountPaid;
	private long amountReturned;
	private Date returnDate;
	private long categoryId;
	private String category;
	
	public BookRentReturn()
	{
		
		
	}
	public BookRentReturn(long customerId,long bookId)
	{
		this.customerId=customerId;
		this.bookId=bookId;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BookRentReturn(String bookName,long categoryId,long bookId,Date issueDate)
	{
		this.bookName=bookName;
		this.categoryId=categoryId;
		this.bookId=bookId;
		this.issueDate=issueDate;
	}
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public long getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(long amountPaid) {
		this.amountPaid = amountPaid;
	}
	public long getAmountReturned() {
		return amountReturned;
	}
	public void setAmountReturned(long amountReturned) {
		this.amountReturned = amountReturned;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
 
	
}
