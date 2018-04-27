package com.iw.infoboard.service;


import com.iw.infoboard.dao.infoBoardDAO;
import com.iw.infoboard.dto.infoBoardDTO;
import com.webjjang.util.ServiceInterface;

public class BoardWriteService implements ServiceInterface{
	
	private infoBoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (infoBoardDAO) obj;
	}

	public Integer excute(Object boardDTO){
		System.out.println("BoardWriteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		boardDAO.write((infoBoardDTO) boardDTO);
		return 1;
	}
}
