package com.iw.trendboard.dto;
/*
 * 게시판 데이터를 저장하는 객체
 * 2018.2.13
 * 이영환
 * 번호, 제목, 내용, 작성자, 작성일, 조횟수
 */
public class TrendBoardDTO 
{
	private int no;
	private String title;
	private String present;
	private String content;
	private String content2;
	private String content3;
	private String content4;
	private String content5;
	
	
	
	public TrendBoardDTO()
	{
		super();
	}


	
	

	public TrendBoardDTO(int no, String title, String present, String content, String content2, String content3,
			String content4, String content5)
	{
		super();
		this.no = no;
		this.title = title;
		this.present = present;
		this.content = content;
		this.content2 = content2;
		this.content3 = content3;
		this.content4 = content4;
		this.content5 = content5;
	}





	public int getNo()
	{
		return no;
	}



	public void setNo(int no)
	{
		this.no = no;
	}



	public String getTitle()
	{
		return title;
	}



	public void setTitle(String title)
	{
		this.title = title;
	}



	public String getPresent()
	{
		return present;
	}



	public void setPresent(String present)
	{
		this.present = present;
	}



	public String getContent()
	{
		return content;
	}



	public void setContent(String content)
	{
		this.content = content;
	}



	public String getContent2()
	{
		return content2;
	}



	public void setContent2(String content2)
	{
		this.content2 = content2;
	}



	public String getContent3()
	{
		return content3;
	}



	public void setContent3(String content3)
	{
		this.content3 = content3;
	}



	public String getContent4()
	{
		return content4;
	}



	public void setContent4(String content4)
	{
		this.content4 = content4;
	}



	public String getContent5()
	{
		return content5;
	}



	public void setContent5(String content5)
	{
		this.content5 = content5;
	}


	
	
	
	
	
	
	

	
	// 데이터를 꺼내고 넣는 getter, setter를 작성한다.
}
