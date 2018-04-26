package com.iw.news.board.service;

import com.iw.news.board.dao.BoardDAO;
import com.webjjang.util.ServiceInterface;

public class ReplyDeleteService implements ServiceInterface{
	
	private BoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (BoardDAO) obj;
	}
	
	public Integer excute(Object no){
		System.out.println("BoardDeleteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		boardDAO.delete((int) no);
		return 1;
	}
}
