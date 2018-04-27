package com.iw.infoboard.service;


import com.iw.infoboard.dao.infoBoardDAO;
import com.webjjang.util.ServiceInterface;

public class BoardDeleteService implements ServiceInterface{
	
	private infoBoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (infoBoardDAO) obj;
	}
	
	public Integer excute(Object no){
		System.out.println("BoardDeleteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		boardDAO.delete((int) no);
		return 1;
	}
}
