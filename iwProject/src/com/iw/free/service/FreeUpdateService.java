package com.iw.free.service;

import com.iw.free.dao.FreeDAO;
import com.iw.free.dto.FreeDTO;
import com.webjjang.util.ServiceInterface;


public class FreeUpdateService implements ServiceInterface{

	private FreeDAO freeDAO;
	
	@Override
	public void setDAO(Object obj) {
		this.freeDAO = (FreeDAO) obj;
	}
	
	@Override
	public Integer excute(Object freeDTO) throws Exception{
		System.out.println("FreeUpdateService.process()");
		// 호출
		freeDAO.update((FreeDTO) freeDTO);
		return 1;
	}
}
