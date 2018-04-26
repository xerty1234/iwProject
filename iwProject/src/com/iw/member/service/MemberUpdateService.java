package com.iw.member.service;

import com.iw.member.dao.MemberDAO;
import com.iw.member.dto.MemberDTO;

import com.webjjang.util.ServiceInterface;

public class MemberUpdateService implements ServiceInterface{

	private MemberDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (MemberDAO) obj;
	}
	
	@Override
	public Integer excute(Object boardDTO) throws Exception{
		System.out.println("BoardUpdateService.process()");
		// 호출
		boardDAO.update((MemberDTO)boardDTO);
		return 1;
	}
}
