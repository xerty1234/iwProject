<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 리스트</title>
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<script>
$(document).ready(function(){
// 	alert("OK");
    $(".data").click(function(){
//         $(this).hide();
        var no = $(this).find("td:first").text();
// 		alert("click");
// 		alert(no);
		location = "view.do?no="+no
				+"&page=${pageObject.page}"
				+'&rowPerPage=${(empty param.rowPerPage)?"10":param.rowPerPage}'
				+'&searchKey=${param.searchKey}&searchWord=${param.searchWord}';
    });
    $("#write").click(function(){
    	location = "write.do";
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
<form class="form-inline">
	<input name="page" value="1" type="hidden" />
	<input name="rowPerPage" value='${(empty param.rowPerPage)?"10":param.rowPerPage}' type="hidden" />
  <div class="input-group">
  	<select class="form-control col-sm-3" name="searchKey" >
  		<option value="title" ${(param.searchKey == "title")?"selected=\"selected\"":"" } 
  		>제목</option>
  		<option value="content"  ${(param.searchKey == "content")?"selected=\"selected\"":"" }
  		>내용</option>
  		<option value="writer" ${(param.searchKey =="writer")?"selected=\"selected\"":""}
  		>작성자</option>
  		<option value="title,content" ${(param.searchKey =="title,content")?"selected=\"selected\"":""} 
  		>제목/내용</option>
  		<option value="title,writer" ${(param.searchKey =="title,writer")?"selected=\"selected\"":""} 
  		>제목/작성자</option>
  		<option value="content,writer" ${(param.searchKey =="content,writer")?"selected=\"selected\"":""} 
  		>내용/작성자</option>
  		<option value="title,content,writer" ${(param.searchKey =="title,content,writer")?"selected=\"selected\"":""} 
  		>제목/내용/작성자</option>
  	</select>
    <input type="text" class="form-control col-sm-8" placeholder="Search"
     name="searchWord" value="${param.searchWord }">
    <div class="input-group-btn">
      <button class="btn btn-default" type="submit">
        <i class="glyphicon glyphicon-search"></i>
      </button>
    </div> 
  </div>
</form> 
</div>
<div class="panel-body">
<table class="table">
<thead>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
</thead>
<tbody>
<!-- 데이터를 출력하는 반복 처리 -->
<c:forEach items="${list }" var="boardDTO">
	<tr class="data">
		<!-- boardDTO.getNo() 출력 -->
		<td>${boardDTO.no }</td>
		<td>${boardDTO.title }</td>
		<td>${boardDTO.writer }</td>
		<td>${boardDTO.writeDate }</td>
		<td>${boardDTO.hit }</td>
	</tr>
</c:forEach>
</tbody>
<tfoot>
	<tr>
		<td colspan="3">
			<ul class="pagination">
			<c:if test="${pageObject.startPage > 1 }">
			  <li>
			  	<a href='list.do?page=${pageObject.startPage - 1 }&rowPerPage=${(empty param.rowPerPage)?"10":param.rowPerPage}&searchKey=${param.searchKey}&searchWord=${param.searchWord}'>
			  	<i class="glyphicon glyphicon-step-backward"></i></a></li>
			 </c:if>
			<c:forEach begin="${pageObject.startPage }" end="${pageObject.endPage }"
			var="idx">
			  <li ${(pageObject.page == idx)?"class='active'":"" }>
			  	<a href='list.do?page=${idx }&rowPerPage=${(empty param.rowPerPage)?"10":param.rowPerPage}&searchKey=${param.searchKey}&searchWord=${param.searchWord}'>
			  	${idx }</a></li>
			</c:forEach>
			<c:if test="${pageObject.endPage != pageObject.totalPage }">
			  <li>
			  	<a href='list.do?page=${pageObject.endPage + 1 }&rowPerPage=${(empty param.rowPerPage)?"10":param.rowPerPage}&searchKey=${param.searchKey}&searchWord=${param.searchWord}'>
			  	<i class="glyphicon glyphicon-step-forward"></i></a></li>
			 </c:if>
			</ul> 
		</td>
		<td colspan="2">
			<div class="btn-group">
				<button id="write" class="btn btn-primary">글쓰기</button>
				<button id="reload" class="btn btn-info">새로고침</button>
			</div>
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