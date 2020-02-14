package kr.co.dto;

import java.sql.Date;

public class AnswerDTO {
	boolean adop;
	int boardnum;
	String writer;
	String content;
	int reccount;
	Date date;
	
	public boolean isAdop() {
		return adop;
	}
	public void setAdop(boolean adop) {
		this.adop = adop;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReccount() {
		return reccount;
	}
	public void setReccount(int reccount) {
		this.reccount = reccount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
