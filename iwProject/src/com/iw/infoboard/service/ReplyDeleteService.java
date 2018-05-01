package com.iw.infoboard.service;


import com.iw.infoboard.dao.ReplyDAO;
import com.iw.infoboard.dao.infoBoardDAO;
import com.webjjang.util.ServiceInterface;

public class ReplyDeleteService implements ServiceInterface{
	
	private ReplyDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (ReplyDAO) obj;
	}
	
	public Integer excute(Object no){
		System.out.println("BoardDeleteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		boardDAO.delete((int) no);
		return 1;
	}
}
