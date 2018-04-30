<%@page import="com.sun.org.apache.xml.internal.security.utils.Base64"%>
<%@page import="com.iw.rjava.rJavaCon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Test</h1>
<%
  rJavaCon con = new rJavaCon();
%>
<img src="data:image/jpg;base64, <%=Base64.encode(con.returnRImg())%>"/>
</body>
</html>