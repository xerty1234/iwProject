
<%@page import="com.iw.member.dto.MemberDTO"%>
<%@page import="com.iw.member.service.MemberViewService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글수정</title>
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
		$("#cancel").click(function() {
			history.go(-1);
		});
		$("#update").click(function() {
			location = "/member/update.do"
		})

	});
</script>
<link rel="stylesheet" href="../css/board.css" />
</head>
<body>
	<div class="container">
		<div class="panel_group">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h1>회원 수정</h1>
				</div>
				<div class="panel-body">
					<form method="post">
						<table>
							<tbody>

								<!-- 데이터를 입력하는 -->
								<tr>
									<th>아이디</th>
									<td><input name="id" size="60" maxlength="100"
										value="${boardDTO.id }" class="form-control" /></td>
								</tr>
								<tr>
									<th>비밀번호</th>
									<td><textarea cols="60" name="password"
											class="form-control">${boardDTO.passwrod }</textarea></td>
								<tr />
								<tr>
									<th>닉네임</th>
									<td><input name="nickname" size="10" maxlength="10"
										class="form-control" value="${boardDTO.nickname}" /></td>
								</tr>
								<tr>
									<th>등급</th>
									<td><div class="form-group" id="grade">
											<label for="grade">등급</label> <select name="grade">
												<option value="">직업선택</option>
												<option value="관리자">관리자</option>
												<option value="우수">우수</option>
												<option value="일반">일반</option>
											</select>
										</div></td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2">
										<button id="update" class="btn btn-primary">수정</button>
										<button type="reset" class="btn btn-info">새로고침</button>
										<button type="button" id="cancel" class="btn btn-warning">취소</button>
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