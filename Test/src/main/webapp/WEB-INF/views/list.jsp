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
	  
	  $('#searchbutton').click(function(){

		  if($('#search').val()==''){
			  alert('검색어를 입력해주세요');
		  }
		  else if($('#selectoption').val()=='제목'){
			  location.href = '/list?topic=${topic}&title=' + $('#search').val() + '&page=1';
		  }
		  else if($('#selectoption').val()=='작성자'){
			  location.href = '/list?topic=${topic}&writer=' + $('#search').val() + '&page=1';
		  }
	  });
	  
	  $('#writebutton').click(function(){
		  alert( + "글쓰기버튼입니다");
	  });
  } );
</script>
</head>
<body>
<div class="container" style="width: 1600px; height: 800px; background: #FFB2F5" >
	<div class="container" style="width: 1200px; background: #E4F7BA">
		<div class="row">
			<!-- -----------------------------------첫번째줄----------------------------------- -->
			<!-- 로고 -->
			<div class="col-md-12" style="height: 100px; background: black">
				
			</div>
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
								<li><a href="/list?topic=컴퓨터공학과&page=1">컴퓨터공학과</a></li>
								<li><a href="/list?topic=경영학과&page=1">경영학과</a></li>
								<li><a href="/list?topic=정보통신공학과&page=1">정보통신공학과</a></li>
								<li><a href="#">어둠의마법방어술학과</a></li>
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
			
			<div class="col-md-6 col-md-offset-3" style="background: white; padding: 0px;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th class="col-md-2">카테고리</th>
							<th class="col-md-3">글제목</th>
							<th class="col-md-2">작성자</th>
							<th class="col-md-2">작성일</th>
							<th class="col-md-2">조회수</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${selectboard}" var="selectboard" begin="0">
								<tr>
									<td class="col-md-3">${selectboard.getTopic()}</td>
									<td class="col-md-3"><a href="/view?boardnum=${selectboard.getBoardnum()}">${selectboard.getTitle()}</a></td>
									<td class="col-md-2">${selectboard.getWriter()}</td>
									<td class="col-md-2">${selectboard.getDate()}</td>
									<td class="col-md-2">${selectboard.getViewcount()}</td>
								</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="col-md-12" style="background: white; padding: 0px">
					<nav class="col-md-6" style="padding: 0px;">
						<ul class="pagination" style="padding: 0px; margin: 0px">
							<c:if test="${startpage > 1}">
								<li><a href="/list?topic=${topic}&page=${startpage-1}" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
							</c:if>
							
							<c:forEach var="i" begin="${startpage}" end="${endpage}">
								<li><a href="/list?topic=${topic}&writer=${writer}&page=${i}">${i}</a></li>
							</c:forEach>
							
							<c:if test="${maxpage > endpage}">
								<li><a href="/list?topic=${topic}&page=${startpage+5}" aria-label="Next"><span aria-hidden="true">»</span></a></li>
							</c:if>
						</ul>
					</nav>
					
					<div  class="col-md-6" style="padding: 0px;">
						<div class="col-md-4" style="padding: 0px;">
							<select class="form-control" name="selectoption" id="selectoption" style="height: 35px;">
								<option>제목</option>
								<option>작성자</option>
							</select>
						</div>
						
						<div class="col-md-4" style="padding: 0px;">
							<input type="text" class="form-control" name="search" id="search" style="height: 35px;">
						</div>
						
						<div class="col-md-2" style="padding: 0px;">
							<button type="submit" class="btn btn-success" name="searchbutton" id="searchbutton" style="height: 35px; width: 50px">
								<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							</button>
						</div>
						<div class="col-md-2" style="padding: 0px;">
							<button type="button" class="btn btn-success" name="writebutton" id="writebutton" style="height: 35px; width: 50px">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
							</button>
						</div>
					</div>
					
					<div class="col-md-12" style="padding: 0px; height: 50px; background: black">
					</div>
					
					<div>
					</div>
						
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>