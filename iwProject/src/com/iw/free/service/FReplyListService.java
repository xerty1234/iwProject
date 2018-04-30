package com.iw.free.service;

import java.util.List;

import com.iw.free.dao.FReplyDAO;
import com.iw.free.dto.FReplyDTO;
import com.webjjang.util.ServiceInterface;


public class FReplyListService implements ServiceInterface{
	
	private FReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (FReplyDAO) obj;
	}
	
	@Override
	public List<FReplyDTO> excute(Object obj)  throws Exception{
		System.out.println("FreeListService.process()");
		List<FReplyDTO> list = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성 - 글번호에 대한 댓글 처리를 위해 글번호를 전달한다.
		// 호출
		list = replyDAO.list((Integer) obj);
		return list;
	}
}
