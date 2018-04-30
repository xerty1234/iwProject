package com.iw.trendboard.service;


import com.iw.trendboard.dao.TrendReplyDAO;
import com.iw.trendboard.dto.TrendReplyDTO;
import com.webjjang.util.ServiceInterface;

public class ReplyUpdateService implements ServiceInterface{

	private TrendReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (TrendReplyDAO) obj;
	}
	
	@Override
	public Integer excute(Object replyDTO) throws Exception{
		System.out.println("BoardUpdateService.process()");
		// 호출
		replyDAO.update((TrendReplyDTO) replyDTO);
		return 1;
	}
}
