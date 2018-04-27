package com.iw.infoboard.service;


import com.iw.infoboard.dao.infoBoardDAO;
import com.iw.infoboard.dao.ReplyDAO;
import com.iw.infoboard.dto.infoBoardDTO;
import com.webjjang.util.ServiceInterface;
import com.iw.infoboard.dto.ReplyDTO;

public class ReplyWriteService implements ServiceInterface{
	
	private ReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (ReplyDAO) obj;
	}

	public Integer excute(Object replyDTO){
		System.out.println("ReplyWriteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		replyDAO.write((ReplyDTO) replyDTO);
		return 1;
	}
}
