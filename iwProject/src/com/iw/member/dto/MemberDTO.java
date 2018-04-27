package com.iw.member.dto;

/*
 * 게시판 데이터를 저장하는 객체
 * 2018.2.13
 * 이영환
 * 번호, 제목, 내용, 작성자, 작성일, 조횟수
 */
public class MemberDTO
{

	private int no;
	private String id;
	private String password;
	private String nickname;
	private String writedate;
	private String connectiondate;
	private String grade;
	
	
	
	public MemberDTO()
	{
	}


	public MemberDTO(String id, String password)
	{
		this.id = id;
		this.password = password;
	}


	public MemberDTO(String id, String password, String nickname, String grade)
	{
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.grade = grade;
	}
	
	
	
	
	public MemberDTO(int no, String id, String password, String nickname, String writedate, String connectiondate,
			String grade)
	{
		this.no = no;
		this.id = id;
		this.password = password;
		this.nickname = nickname;
		this.writedate = writedate;
		this.connectiondate = connectiondate;
		this.grade = grade;
	}


	public MemberDTO(String id,  String nickname, String writedate, String connectiondate, String grade)
	{
		
		this.id = id;
		this.nickname = nickname;
		this.writedate = writedate;
		this.connectiondate = connectiondate;
		this.grade = grade;
	}

	
	



	public int getNo()
	{
		return no;
	}
	public void setNo(int no)
	{
		this.no = no;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public String getWritedate()
	{
		return writedate;
	}
	public void setWritedate(String writedate)
	{
		this.writedate = writedate;
	}
	public String getConnectiondate()
	{
		return connectiondate;
	}
	public void setConnectiondate(String connectiondate)
	{
		this.connectiondate = connectiondate;
	}
	public String getGrade()
	{
		return grade;
	}
	public void setGrade(String grade)
	{
		this.grade = grade;
	}


	@Override
	public String toString()
	{
		return "BoardDTO [no=" + no + ", id=" + id + ", password=" + password + ", nickname=" + nickname
				+ ", writedate=" + writedate + ", connectiondate=" + connectiondate + ", grade=" + grade + "]";
	}
	
	
	
	

}
