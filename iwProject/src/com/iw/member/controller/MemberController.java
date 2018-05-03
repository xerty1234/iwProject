package com.iw.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iw.member.dto.MemberDTO;
import com.webjjang.util.Beans;
import com.webjjang.util.DBUtil;
import com.webjjang.util.PageObject2;
import com.webjjang.util.ServiceInterface;

/**
 * Servlet implementation class BoardController
 */
public class MemberController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println(getClass().getName() + ".doGet()");
		String command = Beans.getURI(request);
		// 기본으로는 forward 시킨 jsp 파일명을 저장한다. 앞에 redirect:이라고 붙이면 redirect 시킨
		// uri를 저장한다.
		String jsp = "";
		ServiceInterface service = null;
		System.out.println(command);

		try
		{
			switch (command)
			{
			// 리스트
			case "/member/list.do":
				service = Beans.getService(command);
				// 처리를해서 DB에 있는 데이터를 바아와서 request에 담아둔다.
				request.setAttribute("list", service.excute(null));
				jsp = Beans.Member_getJsp(command);
				System.out.println(jsp);
				break;

			// 로그인 폼
			case "/member/join.do":
				service = Beans.getService(command);
				// service.excute(memberDTO);
				// System.out.println(request.getParameter("no"));
				// jsp = "redirect:" + request.getContextPath() + "/member/view.do";
				jsp = Beans.Member_getJsp(command);
				System.out.println(jsp);
				break;

			case "/member/login.do":
				service = Beans.getService(command);
				jsp = Beans.Member_getJsp(command);
				System.out.println(jsp);
				break;
			case "/member/logout.do":
				request.getSession().invalidate();
				jsp = "redirect:" + request.getContextPath() + "/main/main.do";
				break;
			// 글쓰기
			case "/member/view.do":
				// System.out.println(request.getParameter("no"));
				int no = Integer.parseInt(request.getParameter("no"));
				// int no = Integer.parseInt(request.getParameter("no"));
				service = Beans.getService(command);
				ArrayList<Object> temp = new ArrayList<>();
				temp.add(no);
				temp.add(Boolean.TRUE);

				request.setAttribute("memberDTO", service.excute(temp));
				request.getSession().invalidate();
				// jsp 이름을 만들어 내고 밑에서 forward 시킨다.
				jsp = "redirect:" + request.getContextPath() + "/member/view.do";
				System.out.println(jsp);
				break;
			case "/member/masterpage.do":
				System.out.println("do_get()");
				int no4 = Integer.parseInt(request.getParameter("no"));
				service = Beans.getService("/member/view.do");
				request.setAttribute("memberDTO",service.excute(no4));
				jsp = Beans.Member_getJsp(command);
				System.out.println(jsp);
				break;
			case "/member/update.do":
				int no2 = Integer.parseInt(request.getParameter("no"));
				service = Beans.getService("/member/view.do");
				request.setAttribute("memberDTO", service.excute(no2));
				jsp = Beans.Member_getJsp(command);
				System.out.println(jsp);
				break;
			case "/member/delete.do":
				HttpSession session3 = request.getSession();
				service = Beans.getService(command);
				// 처리를해서 DB에 있는 데이터를 바아와서 request에 담아둔다.
				// int no3 = (int)session3.getAttribute("no");
				int no3 = Integer.parseInt(request.getParameter("no"));
				service.excute(no3);
				String tempgrade =(String)session3.getAttribute("grade");
				if ( tempgrade.equals("관리자"))
				{
					jsp = "list.do";
				}
				else
				{
					request.getSession().invalidate();
					//jsp = request.getContextPath() + "/main/main.jsp";
					jsp = "redirect:" + request.getContextPath() + "/main/main.do";
					//jsp = "/main/main.do";
					//jsp = Beans.Member_getJsp(command);
				}
				System.out.println(jsp);
				break;
			case "/member/mypage.do":
				HttpSession session = request.getSession();
				service = Beans.getService(command);
				if (session.getAttribute("id") != null)
				{
					MemberDTO memberDTO = new MemberDTO();
					memberDTO = (MemberDTO) service.excute(session.getAttribute("id"));
					request.setAttribute("memberDTO", service.excute(session.getAttribute("id")));
					System.out.println(memberDTO);
				}
				jsp = Beans.Member_getJsp(command);
				System.out.println(jsp);
				break;
			default:
				System.out.println("존재하지 않는 자원을 요청");
				jsp = "/WEB-INF/views/error/404.jsp";
				break;
			}

			if (jsp.indexOf("redirect:") == -1)
			{// redirect: 존재하지 않는다.
				// jsp쪽으로 이동한다.
				System.out.println(jsp);
				request.getRequestDispatcher(jsp).forward(request, response);
			} else
			{// redirect: 존재한다.
				jsp = jsp.substring("redirect:".length());
				// uri쪽으로 이동한다.
				System.out.println("redirect 쪽");
				response.sendRedirect(jsp);
			}
		} catch (Exception e)
		{
			System.out.println(e);
			throw new ServletException(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		System.out.println(getClass().getName() + ".doPost()");
		String command = Beans.getURI(request);
		String jsp = "";
		// 처리해야할 서비스 선언
		ServiceInterface service;
		System.out.println(command);
		try
		{
			switch (command)
			{
			// 글쓰기 처리
			case "/member/join.do":
				// 넘어오는 데이터를 BoardDTO에 담는다.
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setId(request.getParameter("id"));
				memberDTO.setPassword(request.getParameter("password"));
				memberDTO.setNickname(request.getParameter("nickname"));
				memberDTO.setGrade(request.getParameter("grade"));
				// 처리할 서비스를 받아온다. - BoardWriteService
				service = Beans.getService(command);
				System.out.println(service);
				service.excute(memberDTO);
				HttpSession session2 = request.getSession();
				session2.setAttribute("id", memberDTO.getId());
				session2.setAttribute("nickname", memberDTO.getNickname());
				session2.setAttribute("no", memberDTO.getNo());
				session2.setAttribute("grade", memberDTO.getGrade());
				
				jsp = request.getContextPath() + "/main/main.do";
				System.out.println(jsp);
				break;

			// 글수정 처리
			case "/member/update.do":
				// 넘어오는 데이터를 BoardDTO에 담는다.
				//HttpSession session2 = request.getSession();
				//int no = (int) session2.getAttribute("no");
				int no2 = Integer.parseInt(request.getParameter("no"));
				service = Beans.getService("/member/view.do");
				
				MemberDTO boardDTO2 = new MemberDTO();
				boardDTO2 = (MemberDTO) service.excute(no2);
				HttpSession session3 = request.getSession();
				String temp = (String)session3.getAttribute("grade");
				
				if (temp.equals("관리자"))
				{
					boardDTO2.setPassword(request.getParameter("id"));
				}
				
				boardDTO2.setPassword(request.getParameter("password"));
				boardDTO2.setNickname(request.getParameter("nickname"));
				boardDTO2.setGrade(request.getParameter("grade"));
				// service - BoardUpdateService
				//boardDTO2.setNo(no);
				System.out.println(boardDTO2);
				service = Beans.getService(command);
				// 실행해서 수정처리
				service.excute(boardDTO2);
				// 글보기로 이동시키는데 글번호와 함께 이동시킨다.
				// jsp = "view.do?no="+boardDTO2.getNo();
				jsp = request.getContextPath() + "/main/main.do";
				break;
			case "/member/login.do":
				service = Beans.getService(command);
				MemberDTO obj = new MemberDTO(request.getParameter("id"), request.getParameter("password"));
				HttpSession session = request.getSession();
				MemberDTO memberDTO2 = (MemberDTO)service.excute(obj);

				if (memberDTO2 != null)
				{
					session.setAttribute("id", memberDTO2.getId());
					session.setAttribute("nickname", memberDTO2.getNickname());
					session.setAttribute("no", memberDTO2.getNo());
					session.setAttribute("grade", memberDTO2.getGrade());

					jsp = request.getContextPath() + "/main/main.do";
				} else
				{
					jsp = request.getContextPath() + "/member/Error.do";
				}
				break;
//			case "/member/delete.do":
//				HttpSession session3 = request.getSession();
//				service = Beans.getService(command);
//				// 처리를해서 DB에 있는 데이터를 바아와서 request에 담아둔다.
//				// int no3 = (int)session3.getAttribute("no");
//
//				if (request.getParameter("no") == null)
//				{
//					int no3 = (int) session3.getAttribute("no");
//					service.excute(no3);
//				} else
//				{
//					service.excute(Integer.parseInt(request.getParameter("no")));
//				}
//
//				jsp = request.getContextPath() + "/main/main.do";
//				System.out.println(jsp);
//				break;
				
			default:
				System.out.println("존재하지 않는 자원을 요청");
				jsp = "/WEB-INF/views/error/404.jsp";
				break;
			}
			// uri쪽으로 이동한다.
			response.sendRedirect(jsp);
		} catch (Exception e)
		{
			// TODO: handle exception
			throw new ServletException(e);
		}
	}

}
