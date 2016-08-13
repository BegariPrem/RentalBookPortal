package com.alacriti.rentalbookportal.vo;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class BookVO {
	private String bookName;
	private String bookAuthor;
	private long bookCategory;
	private long bookId;
	private long bookAvailability;
	private long bookPrice;
	public BookVO()
	{
		
	}
	public BookVO(String bookName, String bookAuthor, long bookCategory, long bookPrice,long bookAvailability) {
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookCategory = bookCategory;
		this.bookPrice = bookPrice;
		this.bookAvailability=bookAvailability;
	}
	
	public BookVO(String bookName,String bookAuthor,long bookCategory)
	{
		this.bookName=bookName;
		this.bookAuthor=bookAuthor;
		this.bookCategory=bookCategory;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public long getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(long bookCategory) {
		this.bookCategory = bookCategory;
	}
	public long getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(long bookPrice) {
		this.bookPrice = bookPrice;
	}
	public long getBookAvailability() {
		return bookAvailability;
	}
	public void setBookAvailability(int bookAvailability) {
		this.bookAvailability=bookAvailability;
	}
	
	
}
