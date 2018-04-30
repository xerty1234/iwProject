package com.iw.free.dto;

/*
 * 게시판 데이터를 저장하는 객체
 * 2018.2.13
 * 이영환
 * 번호, 제목, 내용, 작성자, 작성일, 조횟수
 */
public class FreeDTO {

	private int no;
	private String title;
	private String content;
	private String writer;
	private String writeDate;
	private int hit;

	// {
	// // 초기화 블럭 - 생성될 자동으로 한번 실행된다. 기본값을 셋팅
	// System.out.println("기본 초기화 블럭");
	// no = 100;
	// }
	// static {
	// System.out.println("static 초기화 블럭");
	//// no = 50;
	// }

	// 생성자 선언
	// 생성을 먼저하고 값은 나중에 넣겠다.
	// 기본생성자를 직접 만들었다. new BoardDTO()
	public FreeDTO() {
		// System.out.println("기본 생성자 실행");
		// no = 50;
	}

	// 데이터를 넣을 때 데이터가 준비가 다 되어 있는 경우. -> 게시판 글보기 사용
	public FreeDTO(int no, String title, String content, String writer, String writeDate, int hit) {
		// super(); // 부모 클래스를 생성한다.
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writeDate = writeDate;
		this.hit = hit;
	}

	// 데이터를 넣을 때 데이터가 준비가 다 되어 있는 경우.
	// 게시판 글쓰기 - 제목,내용,작성자만 입력
	public FreeDTO(String title, String content,
			String writer) {
		this(0, title, content, writer, null, 0);
		// 위에 모든데이터를 받아서 처리하는 부분에서 중복이 된다.
//		this.title = title;
//		this.content = content;
//		this.writer = writer;
	}
	// 데이터를 꺼내고 넣는 getter, setter를 작성한다.
	public int getNo() {return no;}
	public void setNo(int no) {this.no=no;}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "BoardDTO [no=" + no + ", title=" + title
		+ ", content=" + content + ", writer=" + writer
		+ ", writeDate=" + writeDate + ", hit=" + hit + "]";
	}
}
