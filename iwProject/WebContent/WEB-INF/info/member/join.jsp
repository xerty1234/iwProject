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
<!--   데이터를 넘기기 전에 유효성검사를 시행한다. -->
  <script type="text/javascript">
  	$(document).ready(function(){
  		$("#joinForm").submit(function(){
  			// 암호와 암호확인이 같아야한다..
  			if($("#password").val() != $("#confirmPassword").val()){
  				alert("암호와 암호확인은 같아야 합니다.");
  				$("#password").val("");
  				$("#confirmPassword").val("");
  				$("#password").focus();
  				return false; // submit을 무시 시킨다. 반드시 submit호출을한 함수에서 return false한다.
  			}
//   			alert($("#resCheck").text());
  			if($("#resCheck").text()=="중복된 아이디 입니다."){
  				alert("중복된 아이디 입니다. 다른 아이디를 선택하세요.");
  				$("#id").focus();
  				return false;
  			}
  		});
  		$("#id").keyup(function(){
//   			alert($(this).val());
			var data = $(this).val();
			if(data != "")
  				$("#resCheck").load("checkId.do?id="+data);
			else $("#resCheck").text("");
  		});
  	});
  </script>
<title>회원가입</title>
</head>
<body>
<div class="container">
  <div class="panel panel-primary">
	<div class="panel-heading">
	  <h2>회원가입</h2>
	  <p>안내:회원가입을 하면 회원전용 게시판등을 사용하실수 있습니다.</p>
	 </div>
	 <div class="panel-body">
	<!--   action 속성을 생략하면 같은 uri를 호출한다. 구분하기 위해 꼭 POST방식 전송하자. -->
	  <form method="post" id="joinForm">
	    <div class="form-group">
	      <label for="id">아이디</label>
	      <input type="text" class="form-control" id="id" name="id" required="required"
	      pattern="^[A-Za-z][A-Za-z0-9]{3,49}" placeholder="아이디 입력" />
	      <div id="resCheck"></div>
	    </div>
	    <div>
	      <label for="name">이름</label>
	      <input type="text" class="form-control" id="name" name="name" required="required"
	      	pattern="[A-Za-z가-힣]{2,20}">
	    </div>
	    <div class="form-group">
	      <label for="password">암호</label>
	      <input type="password" class="form-control" id="password" name="password"
	      required="required" pattern=".{4,10}" maxlength="10">
	    </div>
	    <div class="form-group">
	      <label for="confirmPassword">암호확인</label>
	      <input type="password" class="form-control" id="confirmPassword" name="comfirmPassword">
	    </div>
	    <button type="submit" class="btn btn-default">가입</button>
	  </form>
	 </div>
	</div>
</div>
</body>
</html>