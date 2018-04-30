package com.iw.free.service;

import com.iw.free.dao.FReplyDAO;
import com.iw.free.dto.FReplyDTO;
import com.webjjang.util.ServiceInterface;


public class FReplyUpdateService implements ServiceInterface{

	private FReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (FReplyDAO) obj;
	}
	
	@Override
	public Integer excute(Object replyDTO) throws Exception{
		System.out.println("FreeUpdateService.process()");
		// 호출
		replyDAO.update((FReplyDTO) replyDTO);
		return 1;
	}
}
