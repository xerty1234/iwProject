<%@page import="java.util.ArrayList"%>
<%@page import="com.iw.trend.crawler.StockCrawler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	StockCrawler crawl = new StockCrawler(); 
	ArrayList<String> ar = new ArrayList<>();
	ar = crawl.crawler();
	
%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
for(int i =0; i < ar.size(); i++){
%>
	
<%= ar.get(i) %><br/>	
	
<%	
}
%>


</body>
</html>