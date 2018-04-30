package com.iw.trendboard.service;


import com.iw.trendboard.dao.TrendReplyDAO;
import com.iw.trendboard.dto.TrendReplyDTO;
import com.webjjang.util.ServiceInterface;

public class ReplyWriteService implements ServiceInterface{
	
	private TrendReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (TrendReplyDAO) obj;
	}

	public Integer excute(Object replyDTO){
		System.out.println("ReplyWriteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		replyDAO.write((TrendReplyDTO) replyDTO);
		return 1;
	}
}
