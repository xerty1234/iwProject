package com.iw.member.service;

import java.util.ArrayList;

import com.iw.member.dao.MemberDAO;
import com.iw.member.dto.MemberDTO;
import com.webjjang.util.ServiceInterface;

public class MemberMasterPage implements ServiceInterface
{
	private MemberDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (MemberDAO) obj;
	}
	// ArrayList에 넣어서 받는다. get(0) - no(int), get(1) - isView(boolean)
	// update Form으로 갈때 실행해서 데이터를 가져간다. view할때 사용한다.
	public MemberDTO excute(Object objs)
	{
		System.out.println("BoardViewService.process()");
		String id = (String)objs; 
		//boolean isView = (boolean) list.get(1);
		MemberDTO boardDTO = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		//if(isView) boardDAO.increase(no); // 글보기일 때만 조회수 1증가
		boardDTO = boardDAO.mypage(id);
		return boardDTO;
	}

}
