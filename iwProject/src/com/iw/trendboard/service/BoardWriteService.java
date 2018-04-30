package com.iw.trendboard.service;


import com.iw.trendboard.dao.TrendBoardDAO;
import com.iw.trendboard.dto.TrendBoardDTO;
import com.webjjang.util.ServiceInterface;

public class BoardWriteService implements ServiceInterface{
	
	private TrendBoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (TrendBoardDAO) obj;
	}

	public Integer excute(Object boardDTO){
		System.out.println("BoardWriteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		boardDAO.write((TrendBoardDTO) boardDTO);
		return 1;
	}
}
