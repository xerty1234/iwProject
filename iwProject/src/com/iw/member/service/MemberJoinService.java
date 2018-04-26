package com.iw.member.service;

import com.iw.member.dao.MemberDAO;
import com.iw.member.dto.MemberDTO;
import com.webjjang.util.ServiceInterface;

public class MemberJoinService implements ServiceInterface{
	
	private MemberDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (MemberDAO) obj;
	}

	public Integer excute(Object boardDTO){
		System.out.println("BoardjoinService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		boardDAO.join((MemberDTO)boardDTO);
		
		return 1;
	}
}
