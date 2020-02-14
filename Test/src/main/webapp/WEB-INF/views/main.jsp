<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
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
	<div class="container" style="width: 1200px; background: #E4F7BA">
		<div class="row">
			<!-- -----------------------------------첫번째줄----------------------------------- -->
			<!-- 로고 -->
			<div class="col-md-12" style="height: 100px; background: black">
				
			</div>
			<!-- -----------------------------------두번째줄----------------------------------- -->
			<!------------ 로그인,검색------------>
			<div class="col-md-3" style="height: 50px; background: red">
				
			</div>
			
			<div class="col-md-6" style="height: 50px; background: orange">

			</div>
			<div class="col-md-3" style="padding:0px; height: 50px; background: white">
				<c:choose>
					<c:when test="${LoginUser == null}">
						<button class="btn btn-primary col-md-4 col-md-offset-1" type="button" onclick="location.href='/login'" style="margin-top: 10px;">로그인</button>
						<button class="btn btn-primary col-md-4 col-md-offset-2" type="button" onclick="location.href='/join'" style="margin-top: 10px;">회원가입</button>
					</c:when>
					<c:when test="${LoginUser != null}">
						<button class="btn btn-primary  col-md-4 col-md-offset-1" type="button" onclick="location.href='/main/loggout'" style="margin-top: 10px;">로그아웃</button>
						<button class="btn btn-info  col-md-4 col-md-offset-2" type="button" onclick="location.href='/join'" style="margin-top: 10px;">내정보</button>
					</c:when>
				</c:choose>
			</div>
			<!-- -----------------------------------세번째줄----------------------------------- -->
			<!-- 글리스트이동 -->
			<div class="col-md-12" style="padding:0px;">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<div class="navbar-header">
							<a class="navbar-brand" href="#">메인</a>
						</div>
					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">학과별<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/list?topic=com">컴퓨터공학과</a></li>
								<li><a href="/list?topic=manage">경영학과</a></li>
								<li><a href="/list?topic=info">정보통신공학과</a></li>
								<li><a href="#">어둠의마법방어술학과</a></li>
							</ul>
						</li>
						
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">주제별<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/list?topic=free">자유</a></li>
								<li><a href="/list?topic=subject">수업</a></li>
								<li><a href="/list?topic=study">공부</a></li>
								<li><a href="/list?topic=career">진로</a></li>
							</ul>
						</li>
					</ul>
					</div>
				</nav>
			</div>
			<!-- -----------------------------------네번째줄----------------------------------- -->
			<!------------ 랜덤질문 ------------>
			<div class="col-md-3" style="height: 300px; padding: 0px;">
				<a href="#"><img src="/resources/image/A.png" class="img-rounded"></a>
			</div>
			<!-- --------------------------- -->
			<!------------ 가장 많이 본 질문 ------------>
			<div class="col-md-3" style="height: 300px; padding: 0px;">
				<a href="#"><img src="/resources/image/B.png" class="img-rounded"></a>
			</div>
			<!-- --------------------------- -->
			<!------------ 답변 끝난 정보 ------------>
			<div class="col-md-3" style="height: 300px; padding: 0px;">
				<a href="#"><img src="/resources/image/C.png" class="img-rounded"></a>
			</div>
			<!-- --------------------------- -->
			<!------------ 공란 ------------>
			<div class="col-md-3" style="height: 300px; padding: 0px;">
				<a href="#"><img src="/resources/image/D.png" class="img-rounded"></a>
			</div>
			<!-- -----------------------------------세번째줄----------------------------------- -->
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
					<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#free">자유</a>
					<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#study">공부</a>
					<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#subject">수업</a>	
					<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#career">진로</a>
				</ul>
				<div class="tab-content">
				<!-- 자유 주제 받아오기 -->
					<div class="tab-pane fade active in" id="free">
						<table class="table table-striped table-hover" style="table-layout:fixed">
							<thead>
								<tr class="row"><th class="col-md-3">주제</th><th class="col-md-6">제목</th><th class="col-md-3">조회수</th></tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(freeboard) <= 5 }">
											<c:forEach items="${freeboard}" var="freeboard">
												<tr class="row"><td class="col-md-1">${freeboard.topic}</td><td class="col-md-10"><a href="/view?boardnum=${freeboard.boardnum}">${freeboard.title}</a></td><td class="col-md-1">${freeboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(freeboard) > 5 }">
										<c:forEach items="${freeboard}" var="freeboard" begin="0" end="4">
											<tr class="row"><td class="col-md-1">${freeboard.topic}</td><td class="col-md-10"><a href="/view?boardnum=${freeboard.boardnum}">${freeboard.title}</a></td><td class="col-md-1">${freeboard.viewcount}</td></tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
					<!-- 공부주제 받아오기 -->
					<div class="tab-pane fade" id="study">
						<table class="table table-striped table-hover" style="table-layout:fixed">
							<thead>
								<tr class="row"><th class="col-md-3">주제</th><th class="col-md-6">제목</th><th class="col-md-3">조회수</th></tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(studyboard) <= 5 }">
											<c:forEach items="${studyboard}" var="studyboard">
												<tr class="row"><td class="col-md-1">${studyboard.topic}</td><td class="col-md-10"><a href="/view?boardnum=${studyboard.boardnum}">${studyboard.title}</a></td><td class="col-md-1">${studyboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(studyboard) > 5 }">
										<c:forEach items="${studyboard}" var="studyboard" begin="0" end="4">
											<tr class="row"><td class="col-md-1">${studyboard.topic}</td><td class="col-md-10"><a href="/view?boardnum=${studyboard.boardnum}">${studyboard.title}</a></td><td class="col-md-1">${studyboard.viewcount}</td></tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
					
					<!-- 진로 주제 받아오기 -->
					<div class="tab-pane fade" id="subject">
					<table class="table table-striped table-hover" style="table-layout:fixed">
							<thead>
								<tr class="row"><th class="col-md-3">주제</th><th class="col-md-6">제목</th><th class="col-md-3">조회수</th></tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(subjectboard) <= 5 }">
											<c:forEach items="${subjectboard}" var="subjectboard">
												<tr class="row"><td class="col-md-1">${subjectboard.topic}</td><td class="col-md-10"><a href="/view?boardnum=${subjectboard.boardnum}">${subjectboard.title}</a></td><td class="col-md-1">${subjectboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(subjectboard) > 5 }">
										<c:forEach items="${subjectboard}" var="subjectboard" begin="0" end="4">
											<tr class="row"><td class="col-md-1">${subjectboard.topic}</td><td class="col-md-10"><a href="/view?boardnum=${subjectboard.boardnum}">${subjectboard.title}</a></td><td class="col-md-1">${subjectboard.viewcount}</td></tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
					<!-- 수업 주제 받아오기 -->
					<div class="tab-pane fade" id="career">
						<table class="table table-striped table-hover" style="table-layout:fixed">
							<thead>
								<tr class="row"><th class="col-md-3">주제</th><th class="col-md-6">제목</th><th class="col-md-3">조회수</th></tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(careerboard) <= 5 }">
											<c:forEach items="${careerboard}" var="careerboard">
												<tr class="row"><td class="col-md-1">${careerboard.topic}</td><td class="col-md-10"><a href="/view?boardnum=${careerboard.boardnum}">${careerboard.title}</a></td><td class="col-md-1">${careerboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(careerboard) > 5 }">
										<c:forEach items="${careerboard}" var="careerboard" begin="0" end="4">
											<tr class="row"><td class="col-md-1">${careerboard.topic}</td><td class="col-md-10"><a href="/view?boardnum=${careerboard.boardnum}">${careerboard.title}</a></td><td class="col-md-1">${careerboard.viewcount}</td></tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div> 
			<!-- -----------------------------------네번째줄----------------------------------- -->
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