
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<%
	System.out.println("default_decorator.jsp:" + request.getContextPath());
	pageContext.setAttribute("absUri", request.getContextPath());
%>

<link rel="icon" href="../../favicon.ico">

<title>Carousel Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<!-- Custom styles for this template -->
<link href="carousel.css" rel="stylesheet">
</head>
<!-- NAVBAR
================================================== -->
<style>
.carousel-inner{
  width:100%;
  max-height: 200px !important;
}
.ellipsis { 
 display: inline-block;
 width: 200px; 
 white-space: nowrap;
  overflow: hidden;
text-overflow: ellipsis;
 }

</style>



<!-- Carousel
    ================================================== -->
<body>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
		</ol>
		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img class="first-slide"
					src="http://cichart.moneta.kr/pax/chart/lineAreaChart/V201724/paxLineAreaChartV201724Min01.jsp?abbrSymbol=001"
					alt="First slide" style="width:20%;">
				<div class="container">
					<div class="carousel-caption">
						<h1>정보게시판.</h1>
						<p>
							정보등을 공유하는 게시판
						</p>
<!-- 						<p> -->
<!-- 							<a class="btn btn-lg btn-primary" href="#" role="button">Sign -->
<!-- 								up today</a> -->
<!-- 						</p> -->
					</div>
				</div>
			</div>
			<div class="item">
				<img class="second-slide"
					src="http://baruninvest-blog.com/wp-content/uploads/2017/12/%EC%A3%BC%EC%8B%9D2.png"
					alt="Second slide" style="width:20%;">
				<div class="container">
					<div class="carousel-caption">
						<h1>자유게시판 </h1>
						<p>자유롭게 게시물을 올리는 게시판</p>
<!-- 						<p> -->
<!-- 							<a class="btn btn-lg btn-primary" href="#" role="button">Learn -->
<!-- 								more</a> -->
<!-- 						</p> -->
					</div>
				</div>
			</div>
			<div class="item">
				<img class="third-slide"
					src="http://imgnews.naver.com/image/5093/2016/01/19/news_1453169351_812492_main1_99_20160119112003.jpg"
					alt="Third slide" style="width:20%" style="h">
				<div class="container">
					<div class="carousel-caption">
						<h1>뉴스 게시판 </h1>
						<p>주요 뉴스등이 있는 게시판</p>
<!-- 						<p> -->
<!-- 							<a class="btn btn-lg btn-primary" href="#" role="button">Browse -->
<!-- 								gallery</a> -->
<!-- 						</p> -->
					</div>
				</div>
			</div>
			<div class="item">
				<img class="fourth-slide"
					src="http://imgnews.naver.com/image/5399/2018/04/30/0000004422_001_20180430093230940.jpg"
					alt="Fourth slide" style="width:20%;">
				<div class="container">
					<div class="carousel-caption">
						<h1>주가게시판 </h1>
						<p>주가정보, 등락률 등을 확인하는 게시판</p>
<!-- 						<p> -->
<!-- 							<a class="btn btn-lg btn-primary" href="#" role="button">Learn -->
<!-- 								more</a> -->
<!-- 						</p> -->
					</div>
				</div>
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" role="button"
			data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button"
			data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">Next</span>
		</a>
	</div>
	<!-- /.carousel -->


	<!-- Marketing messaging and featurettes
    ================================================== -->
	<!-- Wrap the rest of the page in another container to center all the content. -->

	<div class="container marketing">

		<!-- Three columns of text below the carousel -->
		<div class="row">
			<div class="col-lg-3">
				<img class="img-circle"
					src="http://www.boannews.com/media/upFiles/937042(500).jpg"
					alt="Generic placeholder image" width="100" height="100">
				<h2>정보게시판</h2>
				<p>주식 정보를 서로 주고 받는 게시판 </p>
				<p>
					<a class="btn btn-default" href="${absUri }/infoboard/list.do" role="button">정보게시판
						&raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-3">
				<img class="img-circle"
					src="http://outstanding.kr/wp-content/uploads/2018/04/%EC%9D%B4%EC%8A%88%EB%89%B4%EC%8A%A4-400x286.png"
					alt="Generic placeholder image" width="100" height="100">
				<h2>뉴스게시판</h2>
				<p>주식 뉴스등을 모아둔 게시판 
				</p>
				<p>
					<a class="btn btn-default" href="${absUri }/newsboard/list.do" role="button">뉴스게시판
						&raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-3">
				<img class="img-circle"
					src="http://imgnews.naver.com/image/030/2017/09/28/0002645258_001_20170928181123707.jpg"
					alt="Generic placeholder image" width="100" height="100">
				<h2>주가게시판</h2>
				<p>주가정보, 등락률 등을 확인하는 게시판</p>
				<p>
					<a class="btn btn-default" href="${absUri }/trendboard/list.do" role="button">주가게시판
						&raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-3">
				<img class="img-circle"
					src="http://mblogthumb2.phinf.naver.net/MjAxNzA0MjRfMjM4/MDAxNDkzMDE0MTIxNzU5.5BPRxmyT8AJ7ziUt-0FacLgl57QXWy7fG_7j80wSMwQg.hY-UTpF5IVyHSFSuyyR_kOBQL95XfQxxSRsaj5Lns9Mg.JPEG.buy2life/%EC%9E%90%EC%9C%A0.jpg?type=w800"
					alt="Generic placeholder image" width="100" height="100">
				<h2>자유게시판</h2>
				<p>자유롭게 게시물을 올려놓는 게시판 </p>
				<p>
					<a class="btn btn-default" href="${absUri }/freeboard/list.do" role="button">자유게시판
						&raquo;</a>
				</p>
			</div>
			<!-- /.col-lg-4 -->
		</div>
		<!-- /.row -->


		<!-- START THE FEATURETTES -->

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					${news.title } 
				</h2>
				<p class="ellipsis">${news.article }</p>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block"
					data-src="holder.js/500x500/auto" src="${news.imageLink }">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7 col-md-push-5">
				<h2 class="featurette-heading">
					${info.title } 
				</h2>
				<p>${info.content }</p>
			</div>
			<div class="col-md-5 col-md-pull-7">
				<img class="featurette-image img-responsive center-block"
					data-src="holder.js/500x500/auto" src="http://files.idg.co.kr/ciokr/image/electronic-jungle.jpg">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">
					And lastly, this one. <span class="text-muted">Checkmate.</span>
				</h2>
				<p class="lead">Donec ullamcorper nulla non metus auctor
					fringilla. Vestibulum id ligula porta felis euismod semper.
					Praesent commodo cursus magna, vel scelerisque nisl consectetur.
					Fusce dapibus, tellus ac cursus commodo.</p>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block"
					data-src="holder.js/500x500/auto" alt="Generic placeholder image">
			</div>
		</div>

		<hr class="featurette-divider">

		<!-- /END THE FEATURETTES -->


		<!-- FOOTER -->
		<footer>
			<p class="pull-right">
				<a href="#">Back to top</a>
			</p>
			<p>
				&copy; 2014 Company, Inc. &middot; <a href="#">Privacy</a> &middot;
				<a href="#">Terms</a>
			</p>
		</footer>

	</div>
	<!-- /.container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="../../assets/js/vendor/holder.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
