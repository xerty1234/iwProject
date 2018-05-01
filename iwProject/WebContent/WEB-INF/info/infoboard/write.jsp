<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글쓰기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
// 	alert("OK");
	// 버튼 이벤트 처리
	$("#cancel").click(function(){
		history.back();
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
<h1>게시판 글쓰기</h1>
</div>
<div class="panel-body">
<form method="post">
<table >
<tbody>
<!-- 데이터를 입력하는 -->
	
	<tr>
		<th>제목</th>
		<td><input name="title" size="60" maxlength="100" class="form-control"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="10" cols="60" name="content" class="form-control"/></textarea></td>
	<tr/>
	<tr>
		<th>작성자</th>
		<td><input name="writer" size="60" maxlength="10" class="form-control"/></td>
	</tr>
</tbody>

<tfoot>
	<tr>
		<td colspan="2">
			<button class="btn btn-primary">등록</button>
			<button type="reset"  class="btn btn-success">새로고침</button>
			<button type="button" class="btn btn-danger" id="cancel">취소</button>
		</td>
	</tr>
</tfoot>
</table>
</form>
</div>
</div>
</div>
</div>
</body>
</html>