<%@page import="com.iw.news.board.service.BoardViewService"%>
<%@page import="com.iw.news.board.dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글보기</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
// 	alert("OK");
	$("#replyUpdateDiv").hide();
	// 버튼 이벤트 처리
	$("#update").click(function(){
// 		location="update.do?no="+$("#td_no").text();
		$("#dataForm").attr("action","update.do");
		$("#dataForm").submit();
	});
	$("#delete").click(function(){
		if(confirm("정말 삭제하시겠습니까?"))
			location="delete.do?no="+$("#td_no").text();
	});
	$("#list").click(function(){
// 		location="list.do";
		$("#dataForm input[name='no']").attr("disabled","disabled")
		$("#dataForm").attr("action","list.do");
		$("#dataForm").submit();
	});
	
	// 댓글에 대한 이벤트 처리
	$("#replyWriteBtn").click(function(){
		$("#replyForm").attr("action","replyWrite.do").submit();
	});
	$("#replyUpdateBtn").click(function(){
		$("#replyForm").attr("action","replyUpdate.do").submit();
	});
	// 댓글 목록에 포함이 되어 있는 수정과 삭제 버튼
	$(".repUpdateBtn").click(function(){
		// 클릭한 td를 선택한다. td -> span -> button
		//              parent() parent() this   
		var tdObj = $(this).parent().parent();
		// rno 입력 항목을 사용하도록 지정한다.
		$("#replyForm input[name='rno']").removeAttr("disabled");
		// 클릭한 td 안에 있는 rno를 가져와서 폼안에 rno에 넣어준다.
		$("#replyForm input[name='rno']")
		.val($(tdObj).find("#rno").text());
		$("#replyForm textarea[name='content']")
		.val($(tdObj).find("#content").text());
		$("#replyForm input[name='writer']")
		.val($(tdObj).find("#writer").text());
		// 버튼을 바꿔준다.
		$("#replyWriteDiv, #replyUpdateDiv").toggle(); // hide <-> show
		$("#replyForm textarea[name='content']").focus();
	});
	$("#replyCanCelBtn").click(function(){
		// 내용을 지운다. rno를 disabled로 바꾼다. 등록버튼으로 바꾼다.
		$("#replyForm input[name='rno']").val("");
		$("#replyForm textarea[name='content']").val("");
		$("#replyForm input[name='writer']").val("");
		$("#replyForm input[name='rno']").attr("disabled","disabled");
		// 버튼을 바꿔준다.
		$("#replyWriteDiv, #replyUpdateDiv").toggle(); // hide <-> show
	});
});
</script>
</head>
<body>
<!-- 넘어온 데이터를 저장해 놓는 form tag 작성. 모든 input tag는 type 속성을 hidden -->
<form id="dataForm">
	<input type="hidden" name="no" value="${boardDTO.no }" />
	<input type="hidden" name="page" value="${param.page }" />
	<input type="hidden" name="rowPerPage" value="${param.rowPerPage }" />
	<input type="hidden" name="searchKey" value="${param.searchKey }" />
	<input type="hidden" name="searchWord" value="${param.searchWord }" />
</form>
<div class="container">
<h1>게시판 글보기</h1>
<table class="table">
<tbody>
<!-- 데이터를 출력한다. -->
	<tr>
		<th>번호</th>
		<td id="td_no">${boardDTO.no}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${boardDTO.title}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><pre>${boardDTO.content}</pre></td>
	<tr/>
	<tr>
		<th>작성자</th>
		<td>${boardDTO.writer}</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${boardDTO.writeDate}</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${boardDTO.hit}</td>
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
	<!-- 댓글 쓰기 폼 -->
	<tr>
		<td colspan="2">
			<h3>댓글 쓰기</h3>
			<!-- action 속성은 jquery를 이용해서 속성을 셋팅한다. -->
			<form method="post" id="replyForm">
				<input type="hidden" name="no" value="${param.no }" />
				<!-- 댓글을 수정하게 되면 rno가 필요하므로 수정처리를 위해 넣어 둔다. -->
				<input type="hidden" name="rno" disabled="disabled" />
				<input type="hidden" name="page" value="${param.page }" />
				<input type="hidden" name="rowPerPage" value="${param.rowPerPage }" />
				내용:<textarea rows="3" name="content" class="form-control"></textarea>
				작성자:<input name="writer" pattern="^.{2,10}$" class="form-control">
				<div id="replyWriteDiv">
					<button type="button" id="replyWriteBtn">등록</button>
				</div>
				<div id="replyUpdateDiv">
					<button type="button" id="replyUpdateBtn">수정</button>
					<button type="button" id="replyCanCelBtn">수정취소</button>
				</div>
			</form>
		</td>
	</tr>
	<!-- 댓글 보여주기 -->
	<c:forEach items="${replyList }" var="replyDTO">
	<tr>
		<td colspan="2">
				<span id="rno">${replyDTO.rno } </span>
				<span id="content">${replyDTO.content }</span><br/>
				(<span>${replyDTO.writeDate } - </span>
				<span id="writer">${replyDTO.writer }</span>)
				<span>
					<button class="repUpdateBtn">수정</button>
					<button class="repDeleteBtn">삭제</button>
				</span>
		</td>
	</tr>
	</c:forEach>
</tfoot>
</table>
</div>
</body>
</html>