package com.iw.infoboard.service;

import java.util.List;


import com.iw.infoboard.dao.ReplyDAO;
import com.iw.infoboard.dto.ReplyDTO;
import com.webjjang.util.ServiceInterface;

public class ReplyListService implements ServiceInterface{
	
	private ReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (ReplyDAO) obj;
	}
	
	@Override
	public List<ReplyDTO> excute(Object obj)  throws Exception{
		System.out.println("BoardListService.process()");
		List<ReplyDTO> list = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성 - 글번호에 대한 댓글 처리를 위해 글번호를 전달한다.
		// 호출
		list = replyDAO.list((Integer) obj);
		return list;
	}
}
