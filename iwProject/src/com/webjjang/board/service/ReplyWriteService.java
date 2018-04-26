package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.dao.ReplyDAO;
import com.webjjang.board.dto.BoardDTO;
import com.webjjang.board.dto.ReplyDTO;
import com.webjjang.util.ServiceInterface;

public class ReplyWriteService implements ServiceInterface{
	
	private ReplyDAO replyDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.replyDAO = (ReplyDAO) obj;
	}

	public Integer excute(Object replyDTO){
		System.out.println("ReplyWriteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		replyDAO.write((ReplyDTO) replyDTO);
		return 1;
	}
}
