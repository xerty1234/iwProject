package com.iw.trendboard.service;


import com.iw.trendboard.dao.TrendBoardDAO;
import com.iw.trendboard.dto.TrendBoardDTO;
import com.webjjang.util.ServiceInterface;

public class BoardUpdateService implements ServiceInterface{

	private TrendBoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (TrendBoardDAO) obj;
	}
	
	@Override
	public Integer excute(Object boardDTO) throws Exception{
		System.out.println("BoardUpdateService.process()");
		// 호출
		boardDAO.update((TrendBoardDTO) boardDTO);
		return 1;
	}
}
