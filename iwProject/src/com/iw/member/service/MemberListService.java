package com.iw.member.service;

import java.util.List;
import com.iw.member.dao.MemberDAO;
import com.iw.member.dto.MemberDTO;
import com.webjjang.util.ServiceInterface;

public class MemberListService implements ServiceInterface{
	
	private MemberDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (MemberDAO) obj;
	}
	
	@Override
	public List<MemberDTO> excute(Object obj)  throws Exception{
		System.out.println("BoardListService.process()");
		List<MemberDTO> list = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		list = boardDAO.list();
		return list;
	}
}
