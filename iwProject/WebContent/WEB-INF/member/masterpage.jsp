
<%@page import="com.iw.member.service.MemberViewService" %>
<%@page import="com.iw.member.dto.MemberDTO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	System.out.println("default_decorator.jsp:" + request.getContextPath());
	pageContext.setAttribute("absUri", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글보기</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {
		
		$("#update").click(function() {
			 var no = ${memberDTO.no};
			location = "update.do?no="+ no;
		});
		$("#delete").click(function() {
			if (confirm("정말 삭제하시겠습니까?"))
				 var no = ${memberDTO.no};
				location = "delete.do?no="+no;
		});
		$("#list").click(function() {
			location = "${absUri }/main/main.do";
		});
	});
</script>
</head>
<body>
	<div class="container">
		<h1>회원관리페이지</h1>
		<table class="table">
			<tbody>
				<!-- 데이터를 출력한다. -->
				<c:if test="${sessionScope.grade ne '관리자'}"> 
				<tr>
					<th>아이디</th>
					<td id="td_no">${memberDTO.id}</td>
				</tr>
				</c:if>
				<tr>
					<th>비밀번호</th>
					<td><pre>${memberDTO.password}</pre></td>
				<tr />
				<tr>
					<th>닉네임</th>
					<td>${memberDTO.nickname}</td>
				</tr>
				<tr>
					<th>등급</th>
					<td>${memberDTO.grade}</td>
				</tr>
				<tr>
					<th>가입일</th>
					<td>${memberDTO.writedate}</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<button id="update" class="btn btn-primary">수정</button>
						<button id="delete" class="btn btn-danger">탈퇴</button>
						<button id="list" class="btn btn-success" >리스트</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>