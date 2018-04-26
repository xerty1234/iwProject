
<%@page import="com.iw.member.service.MemberViewService" %>
<%@page import="com.iw.member.dto.MemberDTO" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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
		// 	alert("OK");
		// 버튼 이벤트 처리
		$("#update").click(function() {
			location = "update.do?no=" + $("#td_no").text();
		});
		$("#delete").click(function() {
			if (confirm("정말 삭제하시겠습니까?"))
				location = "delete.do?no=" + $("#td_no").text();
		});
		$("#list").click(function() {
			location = "list.do";
		});
	});
</script>
</head>
<body>
	<div class="container">
		<h1>게시판 글보기</h1>
		<table class="table">
			<tbody>
				<!-- 데이터를 출력한다. -->
				<tr>
					<th>번호</th>
					<td id="td_no">${memberDTO.no}</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>${memberDTO.id}</td>
				</tr>
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
						<button id="delete" class="btn btn-danger">삭제</button>
						<button id="list" class="btn btn-success">리스트</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>