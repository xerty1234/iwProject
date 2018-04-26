package com.webjjang.member.dto;

import java.util.Date;

public class MemberDTO {

	private String id;
	private String name;
	private String password;
	private Date regDate;
	private int grade;
	
	
	public MemberDTO() {}
	
	public MemberDTO(String id, String name, String password, Date regDate, int grade) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
		this.grade = grade;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", name=" + name + ", password=" + password + ", regDate=" + regDate + ", grade="
				+ grade + "]";
	}
}
