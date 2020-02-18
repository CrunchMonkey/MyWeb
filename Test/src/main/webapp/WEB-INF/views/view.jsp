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
	  
  } );
  function IdCheck(){
	  
  }
</script>
</head>
<body>
<div class="container" style="width: 1600px; height: 3200px; background: #655c56" >
	<div class="container" style="width: 1200px; background: #E4F7BA">
		<div class="row">
			<!-- 로고 -->
			<div class="col-md-12" style="height: 100px; background: white">
				
			</div>
			<!-- --------------------------- -->
			<!-- -----------------------------------두번째줄----------------------------------- -->
			<!------------검색------------>
			<!-- 글리스트이동 -->
			<div class="col-md-12" style="padding:0px;">
				<nav class="navbar navbar-inverse" style="margin-bottom: 50px">
					<div class="container-fluid">
						<div class="navbar-header">
							<a class="navbar-brand" href="/">메인</a>
						</div>
					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">학과별<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/list?topic=com&page=1">컴퓨터공학과</a></li>
								<li><a href="/list?topic=manage&page=1">경영학과</a></li>
								<li><a href="/list?topic=info&page=1">정보통신공학과</a></li>
								<li><a href="#">어둠의마법방어술학과</a></li>
							</ul>
						</li>
						
						<li class="dropdown">
							<a class="dropdown-toggle" data-toggle="dropdown" href="#">주제별<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="/list?topic=free&page=1">자유</a></li>
								<li><a href="/list?topic=subject&page=1">수업</a></li>
								<li><a href="/list?topic=study&page=1">공부</a></li>
								<li><a href="/list?topic=career&page=1">진로</a></li>
							</ul>
						</li>
						<li class="active"><a href="#">순위</a></li>
					</ul>
					<form class="navbar-form navbar-left" action="/search" method="post">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="검색">
							</div>
							<button type="submit" class="btn btn-default">검색</button>
					</form>
					<c:choose>
						<c:when test="${LoginUser == null}">
							<ul class="nav navbar-nav navbar-right">
      							<li><a href="/login"><span class="glyphicon glyphicon-user"></span>로그인</a></li>
      							<li><a href="/join"><span class="glyphicon glyphicon-log-in"></span>회원가입</a></li>
    						</ul>
						</c:when>
						<c:when test="${LoginUser != null}">
							<ul class="nav navbar-nav navbar-right">
      							<li><a href="/logout"><span class="glyphicon glyphicon-user"></span>로그아웃</a></li>
      							<li><a href="/myinfo"><span class="glyphicon glyphicon-log-in"></span>내정보</a></li>
    						</ul>
						</c:when>
					</c:choose>
					</div>
				</nav>
			</div>
			<!-- 글보기 배경-->
			<div class="col-md-12" style="padding: 0px;background: white">
				<div class="col-md-6 col-md-offset-3 panel panel-default" style="padding: 0px; margin-top: 25px; margin-bottom: 25px;">
					<!-- 이미지 -->
					<div class="col-md-2" style="height: 100px; padding: 0px;"><img src="/resources/image/question.png" class="img-rounded"></div>
					<!-- 글제목 -->
					<div class="col-md-10 panel panel-default" style="height: 100px; background: #fdfdfd; word-wrap: break-word;"><h4 style="margin: 10px">${SelectedBoard.title}</h4></div>
					<!-- 글내용 -->
					<div class="col-md-12" style="background: #fdfdfd; margin-top: 15px; margin-bottom: 15px;">${SelectedBoard.content}</div>
					
					
					<div class="col-md-12 panel panel-default" style="padding: 0px; margin-bottom: 0px	">
						<!-- 작성자 -->
						<div class="col-md-3">
							<h4 align="center">
								<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								${SelectedBoard.writer}
							</h4>
						</div>
						<!-- 작성일 -->
						<div class="col-md-3">
							<h4 align="center">
								<span class="glyphicon glyphicon-hourglass" aria-hidden="true"></span>
								${SelectedBoard.date}
							</h4>
						</div>
						<!-- 조회수 -->
						<div class="col-md-2">
							<h4 align="center">
								<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
								${SelectedBoard.viewcount}
							</h4>
						</div>
						<!-- 수정 -->
						<div class="col-md-2">
							<button type="button" class="btn btn-primary btn-sm" style="margin: 5px 0px 5px 0px;">
								<span class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span>
								수정
							</button>
						</div>
						
						<div class="col-md-2">
							<button type="button" class="btn btn-primary btn-sm" style="margin: 5px 0px 5px 0px;">
								<span class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span>
								삭제
							</button>
						</div>
					</div>
					
				</div>
			</div>



					<c:choose>
						<c:when test="${fn:length(SelectedBoardAnswerList) == 0}">
							<div class="col-md-12" style="padding: 0px;background: #EAEAEA">
								<div class="col-md-6 col-md-offset-3 panel panel-default" style="background: white; padding: 0px; margin-top: 25px; margin-bottom: 25px;">
									<div class="col-md-12">
										<h4 align="center">[임시]답변이 없습니다</h4>
									</div>
								</div>
							</div>
						</c:when>
						<c:when test="${fn:length(SelectedBoardAnswerList) != 0}">
							<div class="col-md-12" style="padding: 0px;background: #EAEAEA">
								<div class="col-md-6 col-md-offset-3 panel panel-default" style="background: white; padding: 0px; margin-top: 25px; margin-bottom: 25px;">
									<c:forEach items="${SelectedBoardAnswerList}" var="SelectedBoardAnswerList">
										<!-- 이미지 -->
										<div class="col-md-2" style="height: 100px; padding: 0px;"><img src="/resources/image/answer.png" class="img-rounded"></div>		
										<div class="col-md-10  panel panel-default" style="height: 100px;padding-bottom: 25px;">
											<!-- 작성자 -->
											<div class="col-md-12" style="height: 50px;margin: 0px">
												<h4 align="center">
													<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
													${SelectedBoardAnswerList.writer}님의 답변
												</h4>
											</div>	
										</div>
									<div class="col-md-12" style="margin-top: 15px; margin-bottom: 15px;">${SelectedBoardAnswerList.content}</div>
									
							<div class="col-md-12 panel panel-default" style="padding: 0px; margin-bottom: 0px">
								<!-- 작성자 -->
								<div class="col-md-3">
									<h4 align="center">
										<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
										${SelectedBoardAnswerList.writer}
									</h4>
								</div>
								<!-- 작성일 -->
								<div class="col-md-3">
									<h4 align="center">
										<span class="glyphicon glyphicon-hourglass" aria-hidden="true"></span>
										${SelectedBoardAnswerList.date}
									</h4>
								</div>
								<!-- 조회수 -->
								<div class="col-md-2">
									<h4 align="center">
										<span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
										${SelectedBoard.viewcount}
									</h4>
								</div>
								<!-- 수정 -->
								<div class="col-md-2">
									<button type="button" class="btn btn-primary btn-sm" style="margin: 5px 0px 5px 0px;">
										<span class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span>
										수정
									</button>
								</div>
						
								<div class="col-md-2">
									<button type="button" class="btn btn-primary btn-sm" style="margin: 5px 0px 5px 0px;">
										<span class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span>
										삭제
									</button>
								</div>
							</div>
							<div class="col-md-12" style="height: 100px;padding: 0px;background: #EAEAEA"></div>
									</c:forEach>
								</div>		
							</div>
							
						</c:when>
					</c:choose>
					
					
					
			</div>
	  </div>
	</div>
</body>
</html>