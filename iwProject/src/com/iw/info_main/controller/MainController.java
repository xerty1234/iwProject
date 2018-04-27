package com.iw.info_main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.util.Beans;



/**
 * Servlet implementation class MainController
 */
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 * get방식과 post 방식을 둘다 잡는다.
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(getClass().getName()+".doGet()");
		String command = Beans.getURI(request);
		String jsp = "";
		System.out.println(command);
		switch (command) {
		// main
		case "/main/main.do":
			// jsp 이름을 만들어 내고 밑에서 forward 시킨다.
			jsp = Beans.getJsp(command);
			System.out.println(jsp);
			break;
			
		default:
			System.out.println("존재하지 않는 자원을 요청");
			jsp="/WEB-INF/views/error/404.jsp";
			break;
		}
		// jsp쪽으로 이동한다.
		request.getRequestDispatcher(jsp).forward(request, response);

	}

}
