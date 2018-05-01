package com.iw.trendboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iw.trend.crawler.StockCrawler;
import com.iw.trendboard.dto.TrendBoardDTO;
import com.webjjang.util.DBUtil;
import com.webjjang.util.PageObject2;

public class TrendBoardDAO
{

	// 오라클에 접속할때 필요한 정보들
	// DBUtil에 다 선언함.

	// 글리스트를 가져오는 메서드
	public List<TrendBoardDTO> list(PageObject2 pageObject)
	{
		System.out.println("BoardDAO.list()");
		List<TrendBoardDTO> list =null;
		list =  StockCrawler.crawler();
		return list;
	}

	// 글번호에 맞는 글보기 데이터를 가져오는 메서드
	public TrendBoardDTO view(int no)
	{
		System.out.println("BoardDAO.view()");
		TrendBoardDTO boardDTO = null;
		
		return boardDTO;
	}

	// 게시판 글쓰기 처리.
	public void write(TrendBoardDTO boardDTO)
	{
		System.out.println("BoardDAO.write()");
		
	}

	// 조회수를 1증가 시키는 메서드 -> 글번호를 받아서 글번호에 맞는 조회수 증가.
	public void increase(int no)
	{
		
	}

	// 게시판 글수정 처리.
	public void update(TrendBoardDTO boardDTO)
	{
		System.out.println("BoardDAO.update()");
		
	}

	// 게시판 글삭제 처리
	public void delete(int no)
	{
		System.out.println("BoardDAO.delete()");
		// 사용한 객체 선언
		
	}

}
