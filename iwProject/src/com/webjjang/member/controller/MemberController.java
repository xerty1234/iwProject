package com.webjjang.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webjjang.member.dto.MemberDTO;
import com.webjjang.util.Beans;
import com.webjjang.util.ServiceInterface;

/**
 * Servlet implementation class MemberController
 */
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(getClass().getName()+".doGet()");
		String command = Beans.getURI(request);
		String jsp = "";
		System.out.println(command);
		switch (command) {
		// 회원가입 폼
		case "/member/join.do":
			// jsp 이름을 만들어 내고 밑에서 forward 시킨다.
			jsp = Beans.getJsp(command);
			System.out.println(jsp);
			break;
		// 아이디 중복 체크한다. Ajax로 요청된다.
		case "/member/checkId.do":
			String id = request.getParameter("id");
			PrintWriter out = response.getWriter();
			try {
				if((boolean) Beans.getService(command).excute(id)) {
					out.print("<span style=\"color:blue\">사용가능한 아이디 입니다.</span>");
				}else {
					out.print("<span style=\"color:red\">중복된 아이디 입니다.<span>");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				throw new ServletException(e);
			}
			return;
			
		// 로그인 폼
		case "/member/login.do":
			// jsp 이름을 만들어 내고 밑에서 forward 시킨다.
			jsp = Beans.getJsp(command);
			System.out.println(jsp);
			break;
		// 로그인 폼
		case "/member/logout.do":
			// 로그아웃 처리
			request.getSession().invalidate();
			// jsp 이름을 만들어 내고 밑에서 forward 시킨다.
			jsp = "redirect:"+request.getContextPath()+"/main/main.do";
			System.out.println(jsp);
			break;

		default:
			System.out.println("존재하지 않는 자원을 요청");
			jsp="/WEB-INF/views/error/404.jsp";
			break;
		}
		if(jsp.indexOf("redirect:") == -1) // redirect: 존재하지 않는다.
			// jsp쪽으로 이동한다.
			request.getRequestDispatcher(jsp).forward(request, response);
		else {// redirect: 존재한다.
			jsp = jsp.substring("redirect:".length());
			// uri쪽으로 이동한다.
			response.sendRedirect(jsp);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(getClass().getName()+".doPost()");
		String command = Beans.getURI(request);
		String jsp = "";
		// 처리해야할 서비스 선언
		ServiceInterface service;
		System.out.println(command);
		try {
			switch (command) {
			// 회원가입처리
			case "/member/join.do":
				// 넘어오는 데이터를 MemberDTO에 담는다.
				MemberDTO memberDTO = new MemberDTO(
						request.getParameter("id"),
						request.getParameter("name"),
						request.getParameter("password"),
						null,1);
				service = Beans.getService(command);
				System.out.println(service);
				service.excute(memberDTO);
				jsp = request.getContextPath()+"/main/main.do";
				System.out.println(jsp);
				break;
				
			// 로그인 처리
			case "/member/login.do":
				service = Beans.getService(command);
				System.out.println(service);
				// 넘어오는 데이터를 MemberDTO에 담고 서비스를 실행한다.
				MemberDTO memberDTO2 = (MemberDTO) service.excute(new MemberDTO(
						request.getParameter("id"),
						null,
						request.getParameter("password"),
						null,0));
				// application -> session -> request -> page
				HttpSession session = request.getSession();
				if(memberDTO2 != null) { // 정상적인 아이디와 암호를 입력한 경우
					// id, name -> session : 로그인 처리
					session.setAttribute("id", memberDTO2.getId());
					session.setAttribute("name", memberDTO2.getName());
					session.setAttribute("grade", memberDTO2.getGrade());
					String reqURI = (String) session.getAttribute("reqURI");
					if(reqURI != null) { // 요청한 자원이 있는 경우 로그인으로 들어 왔으면 그쪽으로 이동
						jsp = request.getContextPath()+reqURI;
						session.removeAttribute("reqURI");
					}else {jsp = request.getContextPath()+"/main/main.do";}
				}else { // 아이디나 암호가 틀린 경우.
					jsp = request.getContextPath()+"/error/loginError.do";
				}
				System.out.println(jsp);
				break;
	
			default:
				System.out.println("존재하지 않는 자원을 요청");
				jsp="/WEB-INF/views/error/404.jsp";
				break;
			}
			// uri쪽으로 이동한다.
			response.sendRedirect(jsp);
		}catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}

}
