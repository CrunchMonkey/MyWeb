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
	  
	  $('#allsearchbutton').click(function(){

		  if($('#allsearch').val()==''){
			  alert('검색어를 입력해주세요');
		  }
		  else{
			  location.href = '/list?major=%&topic=%&title=' + $('#allsearch').val() + '&page=1';
		  }
	  });
  } );
  function IdCheck(){
	  
  }
</script>
</head>
<body>
<div class="container" style="width: 1600px; height: 1000px; background: white" >
	<div class="container" style="width: 1200px; background: white">
		<div class="row">
			<!-- -----------------------------------첫번째줄----------------------------------- -->
			<!-- 로고 -->
			<div class="col-md-12" style="height: 100px; background: white">
				
			</div>
			<!-- -----------------------------------두번째줄----------------------------------- -->
			<!-- 글리스트이동 -->
			<div class="col-md-12" style="padding:0px;">
				<nav class="navbar navbar-inverse" style="margin: 0px">
					<div class="container-fluid">
						<div class="navbar-header">
							<a class="navbar-brand" href="/">메인</a>
						</div>
					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">학과별<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/list?major=컴퓨터공학&page=1">컴퓨터공학</a></li>
								<li><a href="/list?major=경영학&page=1">경영학</a></li>
								<li><a href="/list?major=정보통신공학&page=1">정보통신공학</a></li>
								<li><a href="#">어둠의마법방어술학</a></li>
							</ul>
						</li>
						
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">주제별<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/list?topic=자유&page=1">자유</a></li>
								<li><a href="/list?topic=수업&page=1">수업</a></li>
								<li><a href="/list?topic=공부&page=1">공부</a></li>
								<li><a href="/list?topic=진로&page=1">진로</a></li>
							</ul>
						</li>

					</ul>
					<form class="navbar-form navbar-left" action="/search" method="post">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="검색" id="allsearch">
							</div>
							<button type="button" class="btn btn-default" id="allsearchbutton">검색</button>
					</form>
					<c:choose>
						<c:when test="${LoginUser == null}">
							<ul class="nav navbar-nav navbar-right">
      							<li><a href="/login"><span class="glyphicon glyphicon-log-in"></span>로그인</a></li>
      							<li><a href="/join"><span class="glyphicon glyphicon-user"></span>회원가입</a></li>
    						</ul>
						</c:when>
						<c:when test="${LoginUser != null}">
							<ul class="nav navbar-nav navbar-right">
      							<li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span>로그아웃</a></li>
      							<li><a href="/myinfo"><span class="glyphicon glyphicon-user"></span>내정보</a></li>
    						</ul>
						</c:when>
					</c:choose>
					</div>
				</nav>
			</div>
			<!-- -----------------------------------세번째줄----------------------------------- -->
			
			<!------------ 랜덤질문 ------------>
			<div class="col-md-3" style="height: 300px; padding: 0px;">
				<a href="/mainoption?option=m"><img src="/resources/image/A.png" width="300px" height="300px" class="img-thumbnail"></a>
			</div>
			<!-- --------------------------- -->
			<!------------ 가장 많이 본 질문 ------------>
			<div class="col-md-3" style="height: 300px; padding: 0px;">
				<a href="/mainoption?option=r"><img src="/resources/image/B.png" width="300px" height="300px" class="img-thumbnail"></a>
			</div>
			<!-- --------------------------- -->
			<!------------ 답변 끝난 정보 ------------>
			<div class="col-md-3" style="height: 300px; padding: 0px;">
				<a href="f"><img src="/resources/image/D.png" width="300px" height="300px" class="img-thumbnail"></a>
			</div>
			<!-- --------------------------- -->
			<!------------ 공란 ------------>
			<div class="col-md-3" style="height: 300px; padding: 0px;">
				<a href="#"><img src="/resources/image/C.png" width="300px" height="300px" class="img-thumbnail"></a>
			</div>
			<!-- -----------------------------------세번째줄----------------------------------- -->
			<!------------ 사진 ------------>
			<div class="col-md-4 container" style="padding: 0px">
  				<div id="myCarousel" class="carousel slide" data-ride="carousel">
    				<ol class="carousel-indicators">
     					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      					<li data-target="#myCarousel" data-slide-to="1"></li>
      					<li data-target="#myCarousel" data-slide-to="2"></li>
   					 </ol>

    				<div class="carousel-inner"  style="height: 300px">
      					<div class="item active">
       						 <img src="/resources/image/ss.jpg" alt="Los Angeles" style="width:100%;">
      					</div>

      					<div class="item">
        					<img src="/resources/image/zz.jpg" alt="Chicago" style="width:100%;">
      					</div>
    
      					<div class="item">
        					<img src="/resources/image/dd.jpg" alt="New york" style="width:100%;">
      					</div>
    				</div>

    				<a class="left carousel-control" href="#myCarousel" data-slide="prev">
      					<span class="glyphicon glyphicon-chevron-left"></span>
      					<span class="sr-only">Previous</span>
    				</a>
    				<a class="right carousel-control" href="#myCarousel" data-slide="next">
      					<span class="glyphicon glyphicon-chevron-right"></span>
      					<span class="sr-only">Next</span>
    				</a>
  				</div>
			</div>
			<!-- --------------------------- -->
			<!------------ 학과별 최신 글 ------------>
			<div class="col-md-4 panel panel-default" style="height: 300px; background: white; padding: 0px">
				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#com">컴퓨터</a>
					<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#info">정보통신</a>
					<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#manage">경영통신</a>	
				</ul>
				<div class="tab-content">
				<!-- 컴공과 받아오기 -->
					<div class="tab-pane fade active in" id="com">
						<table class="table table-striped table-hover" style="table-layout:fixed">
							<thead>
								<tr class="row">
									<th class="text-center col-md-3">학과</th>
									<th class="text-center col-md-6">제목</th>
									<th class="text-center col-md-3">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(comboard) <= 5 }">
											<c:forEach items="${comboard}" var="comboard">
												<tr class="row"><td align="center" align=center class="col-md-1">${comboard.major}</td><td align="center" align=center class="col-md-10"><a href="/view?boardnum=${comboard.boardnum}">${comboard.title}</a></td><td align="center" align=center class="col-md-1">${comboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(comboard) > 5 }">
										<c:forEach items="${comboard}" var="comboard" begin="0" end="4">
											<tr class="row"><td align="center" align=center class="col-md-1">${comboard.major}</td><td align="center" align=center class="col-md-10"><a href="/view?boardnum=${comboard.boardnum}">${comboard.title}</a></td><td align="center" align=center class="col-md-1">${comboard.viewcount}</td></tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
					<!-- 경영 받아오기 -->
					<div class="tab-pane fade" id="manage">
						<table class="table table-striped table-hover" style="table-layout:fixed">
							<thead>
								<tr class="row"><th class="text-center col-md-3">주제</th><th class="text-center col-md-6">제목</th><th class="text-center col-md-3">조회수</th></tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(manageboard) <= 5 }">
											<c:forEach items="${manageboard}" var="manageboard">
												<tr class="row"><td align="center" class="col-md-1">${manageboard.major}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${manageboard.boardnum}">${manageboard.title}</a></td><td align="center" class="col-md-1">${manageboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(manageboard) > 5 }">
										<c:forEach items="${manageboard}" var="manageboard" begin="0" end="4">
											<tr class="row"><td align="center" class="col-md-1">${manageboard.major}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${manageboard.boardnum}">${manageboard.title}</a></td><td align="center" class="col-md-1">${manageboard.viewcount}</td></tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
					
					<!-- 정통 주제 받아오기 -->
					<div class="tab-pane fade" id="info">
					<table class="table table-striped table-hover" style="table-layout:fixed">
							<thead>
								<tr class="row"><th class="text-center col-md-3">주제</th><th class="text-center col-md-6">제목</th><th class="text-center col-md-3">조회수</th></tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(infoboard) <= 5 }">
											<c:forEach items="${infoboard}" var="infoboard">
												<tr class="row"><td align="center" class="col-md-1">${infoboard.major}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${infoboard.boardnum}">${infoboard.title}</a></td><td align="center" class="col-md-1">${infoboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(infoboard) > 5 }">
										<c:forEach items="${infoboard}" var="infoboard" begin="0" end="4">
											<tr class="row"><td align="center" class="col-md-1">${infoboard.major}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${infoboard.boardnum}">${infoboard.title}</a></td><td align="center" class="col-md-1">${infoboard.viewcount}</td></tr>
										</c:forEach>
									</c:when>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div> 
			<!------------ 주제별 최신글 ------------>
			<div class="col-md-4 panel panel-default" style="height: 300px; background: white; padding: 0px">
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
								<tr class="row"><th class="text-center col-md-3">주제</th><th class="text-center col-md-6">제목</th><th class="text-center col-md-3">조회수</th></tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(freeboard) <= 5 }">
											<c:forEach items="${freeboard}" var="freeboard">
												<tr class="row"><td align="center" class="col-md-1">${freeboard.topic}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${freeboard.boardnum}">${freeboard.title}</a></td><td align="center" class="col-md-1">${freeboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(freeboard) > 5 }">
										<c:forEach items="${freeboard}" var="freeboard" begin="0" end="4">
											<tr class="row"><td align="center" class="col-md-1">${freeboard.topic}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${freeboard.boardnum}">${freeboard.title}</a></td><td align="center" class="col-md-1">${freeboard.viewcount}</td></tr>
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
								<tr class="row"><th class="text-center col-md-3">주제</th><th class="text-center col-md-6">제목</th><th class="text-center col-md-3">조회수</th></tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(studyboard) <= 5 }">
											<c:forEach items="${studyboard}" var="studyboard">
												<tr class="row"><td align="center" class="col-md-1">${studyboard.topic}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${studyboard.boardnum}">${studyboard.title}</a></td><td align="center" class="col-md-1">${studyboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(studyboard) > 5 }">
										<c:forEach items="${studyboard}" var="studyboard" begin="0" end="4">
											<tr class="row"><td align="center" class="col-md-1">${studyboard.topic}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${studyboard.boardnum}">${studyboard.title}</a></td><td align="center" class="col-md-1">${studyboard.viewcount}</td></tr>
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
								<tr class="row"><th class="text-center col-md-3">주제</th><th class="text-center col-md-6">제목</th><th class="text-center col-md-3">조회수</th></tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(subjectboard) <= 5 }">
											<c:forEach items="${subjectboard}" var="subjectboard">
												<tr class="row"><td align="center" class="col-md-1">${subjectboard.topic}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${subjectboard.boardnum}">${subjectboard.title}</a></td><td align="center" class="col-md-1">${subjectboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(subjectboard) > 5 }">
										<c:forEach items="${subjectboard}" var="subjectboard" begin="0" end="4">
											<tr class="row"><td align="center" class="col-md-1">${subjectboard.topic}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${subjectboard.boardnum}">${subjectboard.title}</a></td><td align="center" class="col-md-1">${subjectboard.viewcount}</td></tr>
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
								<tr class="row"><th class="text-center col-md-3">주제</th><th class="text-center col-md-6">제목</th><th class="text-center col-md-3">조회수</th></tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${fn:length(careerboard) <= 5 }">
											<c:forEach items="${careerboard}" var="careerboard">
												<tr class="row"><td align="center" class="col-md-1">${careerboard.topic}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${careerboard.boardnum}">${careerboard.title}</a></td><td align="center" class="col-md-1">${careerboard.viewcount}</td></tr>
											</c:forEach>
										</c:when>
									<c:when test="${fn:length(careerboard) > 5 }">
										<c:forEach items="${careerboard}" var="careerboard" begin="0" end="4">
											<tr class="row"><td align="center" class="col-md-1">${careerboard.topic}</td><td align="center" class="col-md-10"><a href="/view?boardnum=${careerboard.boardnum}">${careerboard.title}</a></td><td align="center" class="col-md-1">${careerboard.viewcount}</td></tr>
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
		
		<div style="height: 100px; background: #E4F7BA; padding-top: 50px">
			<h4 align="center">만든이: 이승렬	-	이메일: dltmdfuf95@naver.com	-	사이트 이용약관	-	개인정보처리방침</h4>
		</div>
	</div>
</div>
</body>
</html>