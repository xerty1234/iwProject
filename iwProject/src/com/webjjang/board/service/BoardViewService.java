package com.webjjang.board.service;

import java.util.ArrayList;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.dto.BoardDTO;
import com.webjjang.util.ServiceInterface;

public class BoardViewService implements ServiceInterface{

	private BoardDAO boardDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.boardDAO = (BoardDAO) obj;
	}
	// ArrayList에 넣어서 받는다. get(0) - no(int), get(1) - isView(boolean)
	// update Form으로 갈때 실행해서 데이터를 가져간다. view할때 사용한다.
	public BoardDTO excute(Object objs){
		System.out.println("BoardViewService.process()");
		@SuppressWarnings("unchecked")
		ArrayList<Object> list = (ArrayList<Object>) objs;
		int no = (int) list.get(0);
		boolean isView = (boolean) list.get(1);
		BoardDTO boardDTO = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		if(isView) boardDAO.increase(no); // 글보기일 때만 조회수 1증가
		boardDTO = boardDAO.view(no);
		return boardDTO;
	}
}
