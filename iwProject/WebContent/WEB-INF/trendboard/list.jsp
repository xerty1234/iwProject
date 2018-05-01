
<%@page import="java.util.List"%>
<%@page import="com.iw.member.dto.MemberDTO" %>
<%@page import="com.iw.member.service.MemberListService" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 리스트</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
  
<script>
$(document).ready(function(){
	 $('#table_id').DataTable();

    $("#write").click(function(){
    	var no = 2;
    	location = "join.do";
    });
	$("#update").click(function() {
		location = "update.do?no=" + $("#td_no").text();
	});
});
</script>
<link rel="stylesheet" href="../css/board.css" />
</head>
<body>

<p align="middle">
   <iframe src="http://127.0.0.1:7776/" style="width:70%" height="600" scrolling="no" align="middle" frameborder="0">
   </iframe>
</p>


<div class="container">
<div class="panel_group">
<div class="panel panel-primary">
<div class="panel-heading">


<h1>주가 리스트</h1>
</div>
<div class="panel-body">
<table class="table" id="table_id">
<thead>
	<tr>
		<th>종목명</th>
		<th>현재가</th>
		<th>전일비</th>
		<th>등락률</th>
		<th>시가총액</th>
		<th>상장주식수</th>
		<th>외국인비율</th>
	</tr>
</thead>
<tbody>
<!-- 데이터를 출력하는 반복 처리 -->
<c:forEach items="${list }" var="DTO">
	<tr class="data">
		<td>${DTO.title }</td>
		<td>${DTO.present }</td>
		<td>${DTO.content }</td>
		<td>${DTO.content2 }</td>
		<td>${DTO.content3 }</td>
		<td>${DTO.content4 }</td>
		<td>${DTO.content5 }</td>
		
	</tr>
</c:forEach>
</tbody>
<tfoot>
</tfoot>
</table>
</div>
</div>
</div>
</div>
</body>
</html>