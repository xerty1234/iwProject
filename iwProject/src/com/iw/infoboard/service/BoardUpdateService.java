package com.iw.infoboard.service;


import com.iw.infoboard.dao.infoBoardDAO;
import com.iw.infoboard.dto.infoBoardDTO;
import com.webjjang.util.ServiceInterface;

public class BoardUpdateService implements ServiceInterface{

	private infoBoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (infoBoardDAO) obj;
	}
	
	@Override
	public Integer excute(Object boardDTO) throws Exception{
		System.out.println("BoardUpdateService.process()");
		// 호출
		boardDAO.update((infoBoardDTO) boardDTO);
		return 1;
	}
}
