package com.iw.trendboard.service;

import java.util.List;

import com.iw.trendboard.dao.TrendReplyDAO;
import com.iw.trendboard.dto.TrendReplyDTO;
import com.webjjang.util.ServiceInterface;

public class ReplyListService implements ServiceInterface{
	
	private TrendReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (TrendReplyDAO) obj;
	}
	
	@Override
	public List<TrendReplyDTO> excute(Object obj)  throws Exception{
		System.out.println("BoardListService.process()");
		List<TrendReplyDTO> list = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성 - 글번호에 대한 댓글 처리를 위해 글번호를 전달한다.
		// 호출
		list = replyDAO.list((Integer) obj);
		return list;
	}
}
