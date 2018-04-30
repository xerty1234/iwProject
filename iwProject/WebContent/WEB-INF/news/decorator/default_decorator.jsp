<!-- sitemesh 사용을 위한 설정 파일 -->
<!-- 작성자 : 이영환 -->
<!-- 작성일 : 2018.04.19 -->
<!-- 1. web.xml에 filter를 지정해야 한다 -->
<!-- 2. sitemesh.xml 설정: parser, mapper를 지정 -->
<!-- 3. decorators.xml 설정: 꾸미기에 대한 설정 - 구체적인 uri 적용 -->
<!-- 4. default_decorator.jsp: decorators.xml 설정한 JSP를 만든다.(*) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	System.out.println("default_decorator.jsp:" + request.getContextPath());
	pageContext.setAttribute("absUri", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>iw<decorator:title /></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
header, footer {
	background: AntiqueWhite;
}

pre {
	background: white;
	border: 0px;
}

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

/* Add a gray background color and some padding to the footer */
footer {
	background-color: #f2f2f2;
	padding: 25px;
}

.carousel-inner img {
	width: 100%; /* Set width to 100% */
	margin: auto;
	min-height: 200px;
}

/* Hide the carousel text when the screen is less than 600 pixels wide */
@media ( max-width : 600px) {
	.carousel-caption {
		display: none;
	}
}

article {
	min-height: 795px;
}

#welcome {
	color: grey;
	margin: 0 auto;
}
</style>
<decorator:head />
</head>
<body>
	<header>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<div class="navbar-header">
						<a href="${absUri }/main/main.do" class="navbar-brand">주식게시판</a>
					</div>
					<ul class="nav navbar-nav">
						<li><a href="${absUri }/infoboard/list.do">정보게시판</a></li>
						<li><a href="${absUri }/newsboard/list.do">뉴스게시판</a></li>
						<li><a href="${absUri }/freeboard/list.do">자유게시판</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<c:if test="${ empty id }">
							<li><a href="../member/login.do"> <span
									class="glyphicon glyphicon-log-in"></span> 로그인
							</a></li>
							<li><a href="../member/join.do"> <span
									class="glyphicon glyphicon-user"></span> 회원가입
							</a></li>
						</c:if>
						<c:if test="${ !empty id }">
							<li><a><span class="glyphicon glyphicon-user"></span>
									${sessionScope.nickname}님 환영합니다.</a></li>
							<li><a href="../member/logout.do"> <span
									class="glyphicon glyphicon-log-out"> </span>로그아웃
							</a></li>
							<li><a href="#"> <span
									class="glyphicons glyphicons-notes-2"></span>마이페이지
							</a></li>
						</c:if>

					</ul>
				</div>
			</div>
		</nav>
	</header>
	<article>
		<decorator:body />
	</article>
</body>
</html>