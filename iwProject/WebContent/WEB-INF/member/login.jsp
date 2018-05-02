<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
  <div class="panel panel-primary">
	<div class="panel-heading">
	  <h2>로그인</h2>
	 </div>
	 <div class="panel-body">
	<!--   action 속성을 생략하면 같은 uri를 호출한다. 구분하기 위해 꼭 POST방식 전송하자. -->
	  <form method="post" id="loginForm">
	    <div class="form-group">
	      <label for="id">아이디</label>
	      <input type="text" class="form-control" id="id" name="id" required="required"
	      pattern="^[A-Za-z][A-Za-z0-9]{3,49}" placeholder="아이디 입력" />
	    </div>
	    <div class="form-group">
	      <label for="password">암호</label>
	      <input type="password" class="form-control" id="password" name="password"
	      required="required" pattern=".{4,10}" maxlength="10" placeholder="비밀번호 입력">
	    </div>
	    <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
	  </form>
	 </div>
	</div>
</div>

</body>
</html>