package com.webjjang.board.service;

import java.util.List;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.dto.BoardDTO;
import com.webjjang.util.PageObject2;
import com.webjjang.util.ServiceInterface;

public class BoardListService implements ServiceInterface{
	
	private BoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (BoardDAO) obj;
	}
	
	@Override
	public List<BoardDTO> excute(Object obj)  throws Exception{
		System.out.println("BoardListService.process()");
		List<BoardDTO> list = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		list = boardDAO.list((PageObject2) obj);
		return list;
	}
}
