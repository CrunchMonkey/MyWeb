<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title><!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
<script>
  $( document ).ready( function() {
	  $('.nav-tabs').on('click', function (e) {
	        e.preventDefault();
	        $(this).tab('show');
	    }); 
  } );
  function IdCheck(){
	  
  }
</script>
</head>
<body>
<div class="container" style="width: 1600px; height: 3200px; background: #FFB2F5" >
	<div class="container" style="width: 1280px; background: #E4F7BA">
		<div class="row">
			<!-- 로고 -->
			<div class="col-md-12" style="height: 100px; background: white">
				
			</div>
			<!-- --------------------------- -->
			<!------------ 로그인,검색------------>
			<div class="col-md-3" style="height: 100px; background: red">
				
			</div>
			
			<div class="col-md-6" style="height: 100px; background: orange">

			</div>
			<div class="col-md-3" style="height: 100px; background: yellow">
				<c:choose>
					<c:when test="${LoginUser == null}">
						<button type="button" onclick="location.href='/login'">로그인</button>
						<button type="button" onclick="location.href='/join'">회원가입</button>
					</c:when>
					<c:when test="${LoginUser != null}">
						<button type="button" onclick="location.href='/main/loggout'">로그아웃</button>
					</c:when>
				</c:choose>
			</div>
			<!-- --------------------------- -->
			<!------------ 랜덤질문 ------------>
			<div class="col-md-3" style="height: 300px; background: green">
				
			</div>
			<!-- --------------------------- -->
			<!------------ 가장 많이 본 질문 ------------>
			<div class="col-md-3" style="height: 300px; background: blue">

			</div>
			<!-- --------------------------- -->
			<!------------ 답변 끝난 정보 ------------>
			<div class="col-md-3" style="height: 300px; background: indigo">

			</div>
			<!-- --------------------------- -->
			<!------------ 공란 ------------>
			<div class="col-md-3" style="height: 300px; background: purple">

			</div>
			<!-- --------------------------- -->
			<!------------ 학과별 질문 ------------>
			<div class="col-md-3" style="height: 300px; background: black">
				<table class="table table-bordered" style="margin-top: 20px">
					<thead >
						<tr class="info">
							<th>주제</th><th>제목</th><th>조회수</th>
						</tr>				
					</thead>
					<tbody>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
					</tbody>
				</table>
			</div>
			<!-- --------------------------- -->
			<!------------ 답변이 없는 질문 ------------>
			<div class="col-md-3" style="height: 300px; background: white">
				<table class="table table-bordered" style="margin-top: 20px">
					<thead >
						<tr class="info">
							<th>주제</th><th>제목</th><th>조회수</th>
						</tr>				
					</thead>
					<tbody>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
					</tbody>
				</table>
			</div>
			<!-- --------------------------- -->
			<!------------ 주제별 질문 ------------>
			<div class="col-md-3" style="height: 300px; background: brown">
				<table class="table table-bordered" style="margin-top: 20px">
					<thead >
						<tr class="info">
							<th>주제</th><th>제목</th><th>조회수</th>
						</tr>				
					</thead>
					<tbody>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
						<tr class="info">
							<td>주제입니다</td>	<td>제목입니다</td><td>조회수입니다</td>
						<tr>
					</tbody>
				</table>
			</div>
			<!-- --------------------------- -->
			<!------------ 학교소식? ------------>
			<div class="col-md-3" style="height: 300px; background: white">
						<ul class="nav nav-tabs">
							<li class="active"><a data-toggle="tab" href="#major">학과</a></li>
							<li><a data-toggle="tab" href="#free">자유</a></li>
							<li><a data-toggle="tab" href="#study">성적</a></li>	
							<li><a data-toggle="tab" href="#subject">과목</a></li>				
						</ul>
				<div class="tab-content">
					<div class="tab-pane fade in active" id="major" >
    					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc id ornare libero. Vivamus iaculis, justo vel mattis pharetra, nisi ligula varius nisl, sit amet mollis tortor ligula vitae nisi.</p>
  					</div>
  					<div class="tab-pane fade" id="free">
    					<h3>Menu 1</h3>
    					<p>Some content in menu 1.</p>
  					</div>
  					<div class="tab-pane fade" id="study">
    					<h3>Menu 3</h3>
    					<p>Some content in menu 1.</p>
  					</div>
  					<div class="tab-pane fade" id="subject">
    					<h3>Menu 4</h3>
    					<p>Some content in menu 1.</p>
  					</div>
				</div>
			</div> 
			
			<!-- --------------------------- -->
			<!------------ 공란 ------------>
			<!-- --------------------------- -->
			<!------------ 공란 ------------>
			<!-- --------------------------- -->
			
		</div>
	</div>
</div>
<button type="button" onclick="location.href='/list'">리스트보기</button>
</body>
</html>