package com.iw.free.service;


import com.iw.free.dao.FreeDAO;
import com.webjjang.util.ServiceInterface;

public class FreeDeleteService implements ServiceInterface{

private FreeDAO freeDAO;
	//
	@Override
	public void setDAO(Object obj) {
		this.freeDAO = (FreeDAO) obj;
	}
	
	public Integer excute(Object no){
		System.out.println("FreeDeleteService.process()");
		// list에 데이터를 가져와서 채우는 프로그램 작성
		// 호출
		freeDAO.delete((int) no);
		return 1;
	}
}
