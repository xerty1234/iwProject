package com.iw.member.service;

import com.iw.member.dao.MemberDAO;
import com.webjjang.util.ServiceInterface;


public class MemberDeleteService implements ServiceInterface{
	
	private MemberDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (MemberDAO) obj;
	}
	
	public Integer excute(Object no){
		System.out.println("BoardDeleteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		boardDAO.delete((int) no);
		return 1;
	}
}
