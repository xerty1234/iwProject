package com.iw.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iw.infoboard.dao.infoBoardDAO;
import com.iw.infoboard.dto.infoBoardDTO;
import com.iw.member.dto.MemberDTO;
import com.iw.news.board.dao.NewsBoardDAO;
import com.iw.news.board.dto.BoardDTO;
import com.iw.trendboard.dao.TrendBoardDAO;
import com.iw.trendboard.dto.TrendBoardDTO;
import com.webjjang.util.Beans;

/**
 * Servlet implementation class mainController
 */
@WebServlet("/mainController")
public class mainController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mainController()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String jsp="";
		String command = Beans.getURI(request);
		try
		{
			switch (command)
			{
			// 리스트
			case "/main/main.do":
				NewsBoardDAO news  = new NewsBoardDAO();
				com.iw.news.board.dto.BoardDTO  newsDTO = news.getMainHighlights();
				infoBoardDAO info = new infoBoardDAO();
				infoBoardDTO infoDTO = info.getMainHighlights();
				TrendBoardDAO trend = new TrendBoardDAO();
				TrendBoardDTO trendDTO = trend.getMainHighlights();
				
				
				request.setAttribute("news", newsDTO);
				request.setAttribute("info", infoDTO);
				request.setAttribute("trend", trendDTO);
				
				jsp = Beans.Member_getJsp(command);
				
				break;

			default:
				System.out.println("존재하지 않는 자원을 요청");
				jsp = "/WEB-INF/views/error/404.jsp";
				break;
			}
		} 
		catch (Exception e)
		{
			System.out.println(e);
			throw new ServletException(e);
		}
		
		if (jsp.indexOf("redirect:") == -1) 
		{// redirect: 존재하지 않는다.
			// jsp쪽으로 이동한다.
			request.getRequestDispatcher(jsp).forward(request, response);
		}
		else
		{// redirect: 존재한다.
			jsp = jsp.substring("redirect:".length());
			// uri쪽으로 이동한다.
			System.out.println("redirect 쪽");
			response.sendRedirect(jsp);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
