package com.iw.news.board.service;

import com.iw.news.board.dao.NewsBoardDAO;
import com.iw.news.board.dao.NewsReplyDAO;
import com.iw.news.board.dto.BoardDTO;
import com.iw.news.board.dto.ReplyDTO;
import com.webjjang.util.ServiceInterface;

public class ReplyWriteService implements ServiceInterface{
	
	private NewsReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (NewsReplyDAO) obj;
	}

	public Integer excute(Object replyDTO){
		System.out.println("ReplyWriteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		replyDAO.write((ReplyDTO) replyDTO);
		return 1;
	}
}
