package com.iw.news.board.service;

import com.iw.news.board.dao.BoardDAO;
import com.iw.news.board.dto.BoardDTO;
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
