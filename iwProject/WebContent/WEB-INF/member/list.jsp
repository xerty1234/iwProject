
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
<script>
$(document).ready(function(){
// 	alert("OK");
    $(".data").click(function(){
 
        var no = $(this).find("td:first").text();
        alert(no);
		location = "view.do?no="+no;
		
    });
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
<div class="container">
<div class="panel_group">
<div class="panel panel-primary">
<div class="panel-heading">
<h1>게시판 리스트</h1>
</div>
<div class="panel-body">
<table class="table">
<thead>
	<tr>
		<th>번호</th>
		<th>아이디</th>
		<th>닉네임</th>
		<th>등급</th>
	</tr>
</thead>
<tbody>
<!-- 데이터를 출력하는 반복 처리 -->
<c:forEach items="${list }" var="memberDTO">
	<tr class="data">
		<td>${memberDTO.no }</td>
		<td>${memberDTO.id }</td>
		<td>${memberDTO.nickname }</td>
		<td>${memberDTO.grade }</td>
	</tr>
</c:forEach>
</tbody>
<tfoot>
	<tr>
		
		<td colspan="1">
			<button id="write" class="btn btn-primary">회원가입</button>
		</td>
	</tr>
</tfoot>
</table>
</div>
</div>
</div>
</div>
</body>
</html>