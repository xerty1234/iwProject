package com.iw.news.board.dto;
/*
 * 게시판 데이터를 저장하는 객체
 * 2018.2.13
 * 이영환
 * 번호, 제목, 내용, 작성자, 작성일, 조횟수
 */
public class BoardDTO {

	private int no;
	private String title;
	private String article;
	private String offerer;
	private String writeDate;
	private String imageLink;
	private int hit;
	
	public BoardDTO() {

	}
	// 생성자 선언
	// 생성을 먼저하고 값은 나중에 넣겠다.
	//기본생성자를 직접 만들었다. new BoardDTO()

	// 데이터를 넣을 때 데이터가 준비가 다 되어 있는 경우. -> 게시판 글보기 사용
	public BoardDTO(int no, String title, String article, String offerer, String writeDate, String imageLink, int hit) {
		this.no = no;
		this.title = title;
		this.article = article;
		this.offerer = offerer;
		this.writeDate = writeDate;
		this.imageLink = imageLink;
		this.hit = hit;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getOfferer() {
		return offerer;
	}
	public void setOfferer(String offerer) {
		this.offerer = offerer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "BoardDTO [no=" + no + ", title=" + title + ", article=" + article + ", offerer=" + offerer
				+ ", writeDate=" + writeDate + ", imageLink=" + imageLink + ", hit=" + hit + "]";
	}
	
	

	// 데이터를 꺼내고 넣는 getter, setter를 작성한다.
	
}
