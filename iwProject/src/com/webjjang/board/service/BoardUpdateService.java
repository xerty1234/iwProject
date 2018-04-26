package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.dto.BoardDTO;
import com.webjjang.util.ServiceInterface;

public class BoardUpdateService implements ServiceInterface{

	private BoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (BoardDAO) obj;
	}
	
	@Override
	public Integer excute(Object boardDTO) throws Exception{
		System.out.println("BoardUpdateService.process()");
		// 호출
		boardDAO.update((BoardDTO) boardDTO);
		return 1;
	}
}
