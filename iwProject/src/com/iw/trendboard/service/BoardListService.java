package com.iw.trendboard.service;

import java.util.List;


import com.iw.trendboard.dao.TrendBoardDAO;
import com.iw.trendboard.dto.TrendBoardDTO;
import com.webjjang.util.PageObject2;
import com.webjjang.util.ServiceInterface;

public class BoardListService implements ServiceInterface{
	
	private TrendBoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (TrendBoardDAO) obj;
	}
	
	@Override
	public List<TrendBoardDTO> excute(Object obj)  throws Exception{
		System.out.println("BoardListService.process()");
		List<TrendBoardDTO> list = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		list = boardDAO.list((PageObject2) obj);
		return list;
	}
}
