package com.iw.news.board.service;

import com.iw.news.board.dao.BoardDAO;
import com.iw.news.board.dto.BoardDTO;
import com.webjjang.util.ServiceInterface;

public class BoardWriteService implements ServiceInterface{
	
	private BoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (BoardDAO) obj;
	}

	public Integer excute(Object boardDTO){
		System.out.println("BoardWriteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		boardDAO.write((BoardDTO) boardDTO);
		return 1;
	}
}
