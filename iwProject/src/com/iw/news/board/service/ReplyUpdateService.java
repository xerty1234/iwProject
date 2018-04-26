package com.iw.news.board.service;

import com.iw.news.board.dao.ReplyDAO;
import com.iw.news.board.dto.ReplyDTO;
import com.webjjang.util.ServiceInterface;

public class ReplyUpdateService implements ServiceInterface{

	private ReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (ReplyDAO) obj;
	}
	
	@Override
	public Integer excute(Object replyDTO) throws Exception{
		System.out.println("BoardUpdateService.process()");
		// 호출
		replyDAO.update((ReplyDTO) replyDTO);
		return 1;
	}
}
