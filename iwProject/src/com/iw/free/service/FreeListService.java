package com.iw.free.service;

import java.util.List;

import com.iw.free.dao.FreeDAO;
import com.iw.free.dto.FreeDTO;
import com.webjjang.util.PageObject2;
import com.webjjang.util.ServiceInterface;


public class FreeListService implements ServiceInterface {

private FreeDAO freeDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.freeDAO = (FreeDAO) obj;
	}
	
	@Override
	public List<FreeDTO> excute(Object obj)  throws Exception{
		System.out.println("FreeListService.process()");
		List<FreeDTO> list = null;
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		list = freeDAO.list((PageObject2) obj);
		return list;
	}
}
