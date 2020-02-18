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
<script src="https://cdn.ckeditor.com/4.9.2/basic/ckeditor.js"></script>
<script>
  $( document ).ready( function() {
	  
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
			<!-- --------------------------- -->
			<!-- -----------------------------------두번째줄----------------------------------- -->
			<!-- 글리스트이동 -->
			<div class="col-md-12" style="padding:0px;">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<div class="navbar-header">
							<a class="navbar-brand" href="/">메인</a>
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
      							<li><a href="/myinfo"><span class="glyphicon glyphicon-log-in"></span>내정ㅂ</a></li>
    						</ul>
						</c:when>
					</c:choose>
					</div>
				</nav>
			</div>
			<!-- -----------------------------------세번째줄----------------------------------- -->
			<div class="col-md-6 col-md-offset-3" style="height: 800px; background: black; margin-top: 25px; padding: 0px;">
				<form method="post" action="/boardwrite">
					<div class="col-md-2" style="height: 50px; padding: 0px; background: red;"></div>
					<div class="col-md-10" style="height: 50px; padding: 0px;"><input type="text" placeholder="제목" style="height: 50px; width: 	500px" name="title"></div>
					
					<div class="col-md-2" style="height: 50px; padding: 0px; background: orange;">주제</div>
					<div class="col-md-3" style="height: 50px; padding: 0px; background: red;">
						<select class="form-control" name="topic">
							<option>자유</option>
							<option>공부</option>
							<option>수업</option>
							<option>진로</option>
						</select>
					</div>
					
					<div class="col-md-2" style="height: 50px; padding: 0px; background: orange;">학과</div>
					<div class="col-md-3" style="height: 50px; padding: 0px; background: red;">
						<select class="form-control" name="major">
							<option>컴퓨터공학과</option>
							<option>경영학과</option>
							<option>정보통신학과</option>
							<option>마법방어술학과</option>
						</select>
					</div>
					
					<div class="col-md-2" style="height: 50px; padding: 0px; background: orange;"></div>
					
					<div class="col-md-12" style="padding: 0px;">
						<textarea  name="content" ></textarea>
					</div>
					<button class="btn btn-primary col-md-4 col-md-offset-1" style="margin-top: 25px;" type="submit">확인</button>
					<button class="btn btn-primary col-md-4 col-md-offset-2"  style="margin-top: 25px;"type="button">목록보기</button>
				</form>
			</div>
			
			<!-- -----------------------------------네번째줄----------------------------------- -->
			<!-- -----------------------------------다섯번째줄----------------------------------- -->
			
			
			
			
		</div>
	</div>
</div>
<script>
CKEDITOR.replace('content', {
	
});
</script>
</body>
</html>