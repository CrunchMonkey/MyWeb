package kr.co.dto;

import java.sql.Date;

public class AnswerDTO {
	boolean adop;
	int boardnum;
	String writer;
	String content;
	Date date;
	int num;
	String writernum;
	
	public String getWriternum() {
		return writernum;
	}
	public void setWriternum(String writernum) {
		this.writernum = writernum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
