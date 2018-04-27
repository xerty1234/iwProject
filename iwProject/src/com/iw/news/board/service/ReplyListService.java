package com.iw.news.board.service;

import java.util.List;

import com.iw.news.board.dao.NewsReplyDAO;
import com.iw.news.board.dto.ReplyDTO;
import com.webjjang.util.ServiceInterface;

public class ReplyListService implements ServiceInterface{
	
	private NewsReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (NewsReplyDAO) obj;
	}
	
	@Override
	public List<ReplyDTO> excute(Object obj)  throws Exception{
		System.out.println("BoardListService.process()");
		List<ReplyDTO> list = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성 - 글번호에 대한 댓글 처리를 위해 글번호를 전달한다.
		// 호출
		list = replyDAO.list((Integer) obj);
		if(list != null) {
			return list;			
		}else {
			System.out.println("널입니다.");
			return null;
		}
	}
}
