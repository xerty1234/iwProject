<%@page import="com.webjjang.util.DBUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
try(Connection con = DBUtil.getConnection();
		PreparedStatement pstmt = con.prepareStatement("ter");
		ResultSet rs = null){
	out.println("커넥션 연결 성공");
}catch(Exception e){
	out.println("커넥션 연결 실패");
}
%>
</body>
</html>