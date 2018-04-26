package com.webjjang.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webjjang.member.controller.Auth;
import com.webjjang.util.Beans;

/**
 * Servlet Filter implementation class AuthLoginCheckFilter
 */
public class AuthLoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AuthLoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println(getClass().getName()+".doFilter()");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = Beans.getURI(req);
		Integer grade 
		= (Integer) req.getSession().getAttribute("grade");
		// 1. 로그인 체크
		if(Auth.isGoLogin(uri, grade)) { // 로그인이 필요해서
			// 뒤에 넘어오는 데이터들을 붙인다.
			String query = req.getQueryString();
			if(query != null && !query.equals("")) uri += "?"+query;
			// 요청한 URI를 저장해 둔다.
			req.getSession().setAttribute("reqURI", uri);
			// 로그인이 필요하다.
			res.sendRedirect("../member/login.do");
		} else if(Auth.isAuth(uri, grade)) { // 권한이 있다.
			// 2. 권한 체크
			// pass the request along the filter chain
			chain.doFilter(request, response);
		} else { // 권한 없는 오류 페이지 처리
			req.getRequestDispatcher("/WEB-INF/views/error/auth.jsp")
			.forward(req, res);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
