package com.webjjang.board.dto;

import java.util.Date;

/*
 * 게시판 댓글의 데이터를 저장하는 객체
 * 댓글번호,게시글 번호,댓글내용, 작성자, 작성일
 * ReplyDTO(데이터 전달) = ReplyBeans(객체를 저장) = ReplyVO(변수값 저장) = Reply(순수객체)
 */
public class ReplyDTO {
	
	private int rno;
	private int no;
	private String content;
	private String writer;
	private Date writeDate;
	
	// 댓글 쓰기 용 생성자
	public ReplyDTO(int no, String content, String writer) {
		this.no = no;
		this.content = content;
		this.writer = writer;
	}

	// 댓글의 화면에 표시할 용 생성자
	public ReplyDTO(int rno, int no, String content, String writer, Date writeDate) {
		this(no, content, writer);
		this.rno = rno;
		this.writeDate = writeDate;
	}

	public ReplyDTO() {} // Spring 프레임워크에서 찾는다.

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "ReplyDTO [rno=" + rno + ", no=" + no + ", content=" + content + ", writer=" + writer + ", writeDate="
				+ writeDate + "]";
	}
	
}
