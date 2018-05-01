package com.iw.trendboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iw.trendboard.dto.TrendReplyDTO;
import com.iw.trendboard.dto.TrendBoardDTO;
import com.webjjang.util.Beans;
import com.webjjang.util.DBUtil;
import com.webjjang.util.PageObject2;
import com.webjjang.util.ServiceInterface;

/**
 * Servlet implementation class BoardController
 */
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(getClass().getName()+".doGet()");
		String command = Beans.getURI(request);
		// 기본으로는 forward 시킨 jsp 파일명을 저장한다. 앞에 redirect:이라고 붙이면 redirect 시킨 uri를 저장한다.
		String jsp = "";
		// 실행할 Service를 담는 객체선언
		ServiceInterface service = null;
		System.out.println(command);
		try {
			switch (command) {
			// 리스트
			case "/trendboard/list.do":
				// 리스트에 뿌릴 데이터를 가져오자. - BoardListService가 필요하다.
				// 이미 생성해서 저장해 놓은 곳에 가져오기. - BoardListService
				//System.out.println("주식스");
				service = Beans.getService(command);
				
				// 처리를해서 DB에 있는 데이터를 받아와서 request에 담아 둔다.
				//ArrayList<TrendBoardDTO> list = null;
				request.setAttribute("list", service.excute(null));

				jsp = Beans.trend_getJsp(command);
				System.out.println(jsp);
				break;
			// 글쓰기 폼 - get
			case "/trendboard/write.do":
				// jsp 이름을 만들어 내고 밑에서 forward 시킨다.
				jsp = Beans.trend_getJsp(command);
				System.out.println(jsp);
				break;
			// 글보기 - get
			case "/trendboard/view.do":
				int no = Integer.parseInt(request.getParameter("no"));
				service = Beans.getService(command); // BoardViewService
				// service를 실행해서 DB에서 BoardDTO를 가져와서 request에 담는다.
				// 넘길때 ArrayList로 캐스팅해서 사용하므로 0-no(int), 1-isViews(boolean)
				ArrayList<Object> list2 = new ArrayList<>();
				list2.add(no);
				list2.add(true); // 조회수 1 증가 시킨다.
				request.setAttribute("boardDTO", service.excute(list2));
				request.setAttribute("replyList",
						Beans.getService("/trendboard/replyList.do").excute(no));
				// jsp 이름을 만들어 내고 밑에서 forward 시킨다.
				jsp = Beans.info_getJsp(command);
				System.out.println(jsp);
				break;
			// 글수정 폼 - get
			case "/trendboard/update.do":
				int no2 = Integer.parseInt(request.getParameter("no"));
				service = Beans.getService("/trendboard/view.do");//BoardViewService
				//service를 실행해서 DB에서 BoardDTO를 가져와서 request에 담는다.
				//ArryaList를 넘겨야 - 0:no, 1:isViews(boolean)
				ArrayList<Object> list3 = new ArrayList<>();
				list3.add(no2);
				list3.add(false); // 조회수 1증가를 시키지 않는다.
				request.setAttribute("trendboardDTO", service.excute(list3));				// viewService에서
				// jsp 이름을 만들어 내고 밑에서 forward 시킨다.
				jsp = Beans.trend_getJsp(command);
				System.out.println(jsp);
				break;
	
			// 글삭제
			case "/trendboard/delete.do":
				// 삭제 처리할 서비스를 가져오자. - BoardDeleteService가 필요하다.
				service = Beans.getService(command);
				// 글번호를 받아서 삭제 처리를 한다.
				service.excute(Integer.parseInt(request.getParameter("no")));
				// jsp 이름을 만들어 내고 밑에서 forward 시킨다.
				jsp = "redirect:list.do";
				System.out.println(jsp);
				break;
			default:
				System.out.println("존재하지 않는 자원을 요청");
				jsp="/WEB-INF/trendboard/error/404.jsp";
				break;
			}
			if(jsp.indexOf("redirect:") == -1) // redirect: 존재하지 않는다.
				// jsp쪽으로 이동한다.
				request.getRequestDispatcher(jsp).forward(request, response);
			else {// redirect: 존재한다.
				// 앞에 붙는 redirect: 을 없앤다.
				jsp = jsp.substring("redirect:".length());
				// uri쪽으로 이동한다.
				response.sendRedirect(jsp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
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
			// 글쓰기 처리
			case "/infoboard/write.do":
				// 넘어오는 데이터를 BoardDTO에 담는다.
				
				System.out.println(jsp);
				break;
				
			// 글수정 처리
			case "/trendboard/update.do":
				// 넘어오는 데이터를 BoardDTO에 담는다.
				
				service = Beans.getService(command);
				// 실행해서 수정처리
				break;

				// 댓글쓰기 처리
				case "/trendboard/replyWrite.do":
					// 넘어오는 데이터를 BoardDTO에 담는다.
					TrendReplyDTO replyDTO = new TrendReplyDTO(
							Integer.parseInt(request.getParameter("no")),
							request.getParameter("content"),
							request.getParameter("writer"));
					System.out.println("BoardController.doGet().replyDTO:"+replyDTO);
					// 처리할 서비스를 받아온다. - BoardWriteService
					service = Beans.getService(command);
					System.out.println(service);
					service.excute(replyDTO);
					jsp = "view.do?no="+replyDTO.getNo()
							+"&page="+request.getParameter("page")
							+"&rowPerPage="+request.getParameter("rowPerPage");
					System.out.println(jsp);
					break;
					
					// 댓글수정 처리
				case "/trendboard/replyUpdate.do":
					// 넘어오는 데이터를 BoardDTO에 담는다.
					TrendReplyDTO replyDTO2 = new TrendReplyDTO(
							Integer.parseInt(request.getParameter("rno")), 0,
							request.getParameter("content"),
							request.getParameter("writer"), null);
					System.out.println("BoardController.doGet().replyDTO:"+replyDTO2);
					// 처리할 서비스를 받아온다. - BoardWriteService
					service = Beans.getService(command);
					System.out.println(service);
					service.excute(replyDTO2);
					jsp = "view.do?no="+request.getParameter("no")
					+"&page="+request.getParameter("page")
					+"&rowPerPage="+request.getParameter("rowPerPage");
					System.out.println(jsp);
					break;
					
				
			default:
				System.out.println("존재하지 않는 자원을 요청");
				jsp="/WEB-INF/trendboard/error/404.jsp";
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
