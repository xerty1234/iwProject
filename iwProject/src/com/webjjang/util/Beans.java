package com.webjjang.util;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.iw.infoboard.dao.ReplyDAO;
import com.iw.infoboard.dao.infoBoardDAO;
import com.iw.member.dao.MemberDAO;
import com.iw.news.board.dao.NewsBoardDAO;
import com.iw.news.board.dao.NewsReplyDAO;
import com.iw.trendboard.dao.TrendBoardDAO;
import com.iw.trendboard.dao.TrendReplyDAO;


/**
 * Servlet implementation class Beans
 */
public class Beans extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 실행해야할 모든 서비스객체를 저장해 놓는 Map
	public static Map<String, ServiceInterface> beans = new HashMap<>();

	// 실행해야할 모든 DAO객체를 저장해 놓는 Map
	public static Map<String, Object> daoBeans = new HashMap<>();

	// 실행할 서비스를 받아가는 메서드
	public static ServiceInterface getService(String uri) {
		return beans.get(uri);
	}
	
	// 실행할 DAO를 받아가는 메서드 
	public static Object getDAO(String uri) {
		return daoBeans.get(uri);
	}
	
	// request받아서 URI를 리턴하는 프로그램 작성
	// 모든 Controller에서는 따로 작성하지 않고 받아가서 처리한다.
	public static String getURI(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri.substring(uri.indexOf(request.getServletPath()));
	}

	public static String pre = "/WEB-INF/news";
	public static String memberPre = "/WEB-INF";
	public static String infopre = "/WEB-INF/info";
	public static String trendpre = "/WEB-INF/trend";
	public static String suf = ".jsp";
	
	// 정제된 URI 넣으면 foward할 jsp로 만들어주는 메서드
	public static String getJsp(String uri) {
		return pre+uri.substring(0, uri.lastIndexOf("."))+suf;
	}
	public static String Member_getJsp(String uri)
	{
		String temp = memberPre+uri.substring(0, uri.lastIndexOf("."))+suf;
		System.out.println(temp);
		return temp;
	}
	public static String info_getJsp(String uri) {
		return infopre+uri.substring(0, uri.lastIndexOf("."))+suf;
	}
	public static String trend_getJsp(String uri) {
		return infopre+uri.substring(0, uri.lastIndexOf("."))+suf;
	}
	
	/**
	 * @see Servlet#init(ServletConfig)
	 * 여기가 제일 먼저 실행이 되서 모든 객체를 생성한다.
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub

		// =========== dao 생성해서 저장하는 처리문. - 모든 DAO 프로그램을 다 생성해 놓는다. ================ //
		
		daoBeans.put("NewsBoardDAO", new NewsBoardDAO());
		//daoBeans.put("boardReplyDAO", new com.iw.news.board.dao.ReplyDAO());
		daoBeans.put("memberDAO", new MemberDAO());
		daoBeans.put("infoboardDAO", new infoBoardDAO());
		daoBeans.put("trendboardDAO", new TrendBoardDAO());
		daoBeans.put("boardReplyDAO", new ReplyDAO());
		daoBeans.put("NewsReplyDAO", new NewsReplyDAO() );
		daoBeans.put("trendboardReplyDAO", new TrendReplyDAO() );
		
		// ======== service를 생성해서 저장하는 프로그램 작성 =============
		// web.xml에 servlet 태그 안에 init-param 태그로 정의되어 있는 정보를 받는다.
		String configFile = getInitParameter("configFile");
		System.out.println(configFile);
		// 컴퓨터 절대 위치
		String configFilePath = getServletContext().getRealPath(configFile);
		// key(String) = value(String) 값을 받아올 수 있는 객체 Properties를 이용해서 문자열로 받아낸다.
		Properties prop = new Properties();
		// try(자원 선언) -> close() 생략가능. 자동닫힘
		try (FileReader fis = new FileReader(configFilePath)) {
			// properties 파일에서부터 k=v 형식으로 저장된 모든 정보를 읽어온다.
			// key: String, value: String -> 지네릭스를 안한다.
			prop.load(fis);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("읽기 오류");
		}

		/** Properties에 있는 내용으로 객체를 자동생성하게 하자. **/
		// prop 객체에서 키-> set -> iterator : 모든 객체를 생성
		// properties 객체는 map과 유사하여 순서가 없다. key&value 형태로 운영.
		Iterator<Object> keyIter = prop.keySet().iterator();
		// prop에 키를 입력해서 생성해야 할 객체이름을 꺼낸다.
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName[] = ((String) prop.get(command)).split(":");
			// 클래스 이름을 알아내면 자동으로 객체를 생성할 수 있다.
			try {
				if(!handlerClassName[0].equals("noService")) {
					Class<?> handlerClass = Class.forName(handlerClassName[0]);
					// 자동으로 객체를 생성해서 저장한다.
					ServiceInterface handlerInstance 
					= (ServiceInterface) handlerClass.newInstance();
					// key : command value : handlerInstance- 생성된 객체
					// Map에 저장
					beans.put(command, handlerInstance);
					// 의존성 주입 - 사용하는 프로그램을 넣어준다.(setter, 생성자)
					// 생성이된 service(handlerInstance)에 필요한 DAO 가져와서 넣는다.
					handlerInstance.setDAO(daoBeans.get(handlerClassName[1]));
				}				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // end of while
		System.out.println("객체생성과 연결, 권한 자료 로딩 완료!");
	}// end of init()

	

}
