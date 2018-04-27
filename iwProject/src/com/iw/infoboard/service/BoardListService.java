package com.iw.infoboard.service;

import java.util.List;


import com.iw.infoboard.dao.infoBoardDAO;
import com.iw.infoboard.dto.infoBoardDTO;
import com.webjjang.util.PageObject2;
import com.webjjang.util.ServiceInterface;

public class BoardListService implements ServiceInterface{
	
	private infoBoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (infoBoardDAO) obj;
	}
	
	@Override
	public List<infoBoardDTO> excute(Object obj)  throws Exception{
		System.out.println("BoardListService.process()");
		List<infoBoardDTO> list = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		list = boardDAO.list((PageObject2) obj);
		return list;
	}
}
