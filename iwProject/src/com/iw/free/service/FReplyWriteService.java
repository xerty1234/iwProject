package com.iw.free.service;

import com.iw.free.dao.FreeDAO;
import com.iw.free.dao.FReplyDAO;
import com.iw.free.dto.FreeDTO;
import com.webjjang.util.ServiceInterface;
import com.iw.free.dto.FReplyDTO;


public class FReplyWriteService implements ServiceInterface{
	
	private FReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (FReplyDAO) obj;
	}

	public Integer excute(Object replyDTO){
		System.out.println("FReplyWriteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		replyDAO.write((FReplyDTO) replyDTO);
		return 1;
	}
}
