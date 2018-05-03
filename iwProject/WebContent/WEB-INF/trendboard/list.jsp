
<%@page import="com.iw.trendboard.dto.TrendBoardDTO"%>
<%@page import="com.iw.trend.crawler.StockCrawler"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.iw.member.dto.MemberDTO"%>
<%@page import="com.iw.member.service.MemberListService"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	//StockCrawler sc = new StockCrawler();
	ArrayList<TrendBoardDTO> temp = (ArrayList<TrendBoardDTO>) StockCrawler.crawler();

	System.out.println(temp);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 리스트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>




<script>
$(document).ready(function(){
	 $('#table_id').DataTable();

    $("#write").click(function(){
    	var no = 2;
    	location = "join.do";
    });
	$("#update").click(function() {
		location = "update.do?no=" + $("#td_no").text();
	});
	

});
</script>

<script type="text/javascript">


	
		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawVisualization);
	
		function drawVisualization() { 
			

	
			var data = google.visualization.arrayToDataTable([
					['주식명', '퍼센트', 'Ecuador', 'Madagascar', 'Papua New Guinea', 'Rwanda', 'Average'],
					[ '삼성전자',  present, 1, 2, 3,  4,  5]
								]);
			
			//data = data+list;
			var options = {
		title : '다리 갯수', // 제목
		width : 600, // 가로 px
		height : 400, // 세로 px
		bar : {
			groupWidth : '80%' // 그래프 너비 설정 %
		},
		legend : {
			position : 'none' // 항목 표시 여부 (현재 설정은 안함)
		}
	};
	
			var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
			chart.draw(data, options);
		}
	</script>


</head>
<body>



	<p align="middle">
	   <iframe src="http://192.168.137.65:7776/" style="width:70%" height="600" scrolling="no" align="middle" frameborder="0">
	   </iframe>
	</p>


<!-- 	<div id="chart_div" style="width: 900px; height: 500px;" align="center"></div> -->

	<div class="container">
		<div class="panel_group">
			<div class="panel panel-primary">
				<div class="panel-heading">


					<br /> <br />
					<h1>주가 리스트</h1>
				</div>
				<div class="panel-body">
					<table class="table" id="table_id">
						<thead>
							<tr>
								<th>종목명</th>
								<th>현재가</th>
								<th>전일비</th>
								<th>등락률</th>
								<th>시가총액</th>
								<th>상장주식수</th>
								<th>외국인비율</th>
							</tr>
						</thead>
						<tbody>
							<!-- 데이터를 출력하는 반복 처리 -->
							<c:forEach items="${list }" var="DTO">
								<tr class="data">
									<td>${DTO.title }</td>
									<td>${DTO.present }</td>
									<td>${DTO.content }</td>
									<td>${DTO.content2 }</td>
									<td>${DTO.content3 }</td>
									<td>${DTO.content4 }</td>
									<td>${DTO.content5 }</td>

								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>