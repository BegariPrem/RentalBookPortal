package com.alacriti.rentalbookportal.vo;

public class PaginationResult {
	private int unique_id;
	private int count;
	private int start;
	private int end;
	
	 public PaginationResult(){
		 
	 }
	public PaginationResult(int unique_id, int start, int end) {
		 
		this.unique_id = unique_id;
		this.start = start;
		this.end = end;
	}
	public int getUnique_id() {
		return unique_id;
	}
	public void setUnique_id(int unique_id) {
		this.unique_id = unique_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
}
