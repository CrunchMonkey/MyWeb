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
	  $('#boardmodify').click(function(){
		  if("${LoginUser}"==""){
			  alert("수정은 로그인 후 가능합니다.");
			  if(confirm("로그인 하시겠습니까?")){
				  location.href="/login";
			  }
		  }
		  else if("${SelectedBoard.num}"=="${LoginUser}"){
			  if(confirm("정말 수정하시겠습니까?")){
				  location.href = '/write?kind=qm&boardnum=${SelectedBoard.boardnum}';  
			  }
		  }
		  else{
			  alert("작성자만 수정이 가능합니다");
		  }
	  });
	  
	  $('#boarddelete').click(function(){
		  if("${LoginUser}"==""){
			  alert("삭제는 로그인 후 가능합니다.");
			  if(confirm("로그인 하시겠습니까?")){
				  location.href="/login";
			  }
		  }
		  else if("${SelectedBoard.num}"=="${LoginUser}"){
			  if(confirm("정말 삭제하시겠습니까?")){
				  alert("삭제되었습니다");
				  location.href = '/delete?boardnum=${SelectedBoard.boardnum}&topic=${SelectedBoard.topic}';  
			  }
		  }
		  else{
			  alert("작성자만 삭제가 가능합니다");
		  }
	  });
	  
	  $('#answermodity').click(function(){
		  if("${LoginUser}"==""){
			  if(confirm("답변 수정은 로그인 후 쓸 수 있습니다. 로그인 하시겠습니까?")){
				  location.href = '/login';
			  }
		  }
		  else if($('#answerwriternum').val()=="${LoginUser}"){
			  if(confirm("정말 수정하시겠습니까?")){
				  location.href = '/write?kind=am&boardnum=${SelectedBoard.boardnum}&num=' + $('#answernum').val();
			  }
		  }
		  else{
			  alert("작성자만 삭제가 가능합니다");
		  }
	  });
	  
	  $('#answerdelete').click(function(){
		  if("${LoginUser}"==""){
			  if(confirm("답변 삭제는 로그인 후 쓸 수 있습니다. 로그인 하시겠습니까?")){
				  location.href = '/login';
			  }
		  }
		  else if($('#answerwriternum').val()=="${LoginUser}"){
			  if(confirm("정말 삭제하시겠습니까?")){
				  location.href = '/answerdelete?boardnum=${SelectedBoard.boardnum}&num=' + $('#answernum').val();
			  }
		  }
		  else{
			  alert("작성자만 삭제가 가능합니다");
		  }
	  });
	  
	  $("button[name=adopbutton]").click(function(){
		  if("${LoginUser}"==""){
			  if(confirm("답변 채택은 로그인 후 쓸 수 있습니다. 로그인 하시겠습니까?")){
				  location.href = '/login';
			  }
		  }
		  else if("${LoginUser}"!="${SelectedBoard.num}"){
			  alert("질문 작성자만 채택할 수 있습니다");
		  }
		  else{
			  if("${SelectedBoard.end}"==false){
				  alert("이미");
			  }
			  else{
				  if("${SelectedBoard.end}"!='false'){
					  alert("이미 완료된 질문입니다");
				  }
				  else{
					  location.href = '/adop?boardnum=${SelectedBoard.boardnum}&num=' + $("input[name=answernum]").val(); 
				  }
			  }
			  
		  }
	  });
	  
	  $('#answerwritebutton').click(function(){
		  if("${LoginUser}"==""){
			  if(confirm("답변 작성은 로그인 후 쓸 수 있습니다. 로그인 하시겠습니까?")){
				  location.href = '/login';
			  }
		  }
		  else{
			  $('#answerwriteform').submit();
		  }
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
</script>
</head>
<body>
<div class="container" style="width: 1600px; height: 1600px; background: white" >
	<div class="container" style="width: 1200px; background: white">
		<div class="row">
			<!-- 로고 -->
			<div class="col-md-12" style="height: 100px; background: white">
				
			</div>
			<!-- --------------------------- -->
			<!-- -----------------------------------두번째줄----------------------------------- -->
			<!------------검색------------>
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
								<li><a href="/list?topic=컴퓨터공학&page=1">컴퓨터공학과</a></li>
								<li><a href="/list?topic=경역학&page=1">경영학과</a></li>
								<li><a href="/list?topic=정보통신공&page=1">정보통신공학과</a></li>
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
					</ul>
					<form class="navbar-form navbar-left" action="/search" method="post">
							<div class="form-group">
								<input type="text" class="form-control" placeholder="검색"  id="allsearch">
							</div>
							<button type="button" class="btn btn-default" id="allsearchbutton">검색</button>
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
				<div class="col-md-6 col-md-offset-3 panel panel-default" style="padding: 0px;">
					<!-- 이미지 -->
					<div class="col-md-2" style="height: 100px; padding: 0px;">
						<c:choose>
							<c:when test="${SelectedBoard.end eq false}">
								<img src="/resources/image/question.png" class="img-rounded">
							</c:when>
							<c:when test="${SelectedBoard.end eq true}">
								<img src="/resources/image/adq.png" class="img-rounded">
							</c:when>
						</c:choose>
						
					</div>
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
						<div class="col-md-1" style="padding: 0px">
							<button type="button" class="btn btn-primary btn-xs" style="margin: 10px 0px 10px 0px;" id="boardmodify">
								<span class="glyphicon glyphicon-wrench" aria-hidden="true" ></span>
								수정
							</button>
						</div>
						<!-- 삭제-->
						<div class="col-md-1" style="padding: 0px">
							<button type="button" class="btn btn-primary btn-xs" style="margin: 10px 0px 10px 0px;" id="boarddelete">
								<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
								삭제
							</button>
						</div>
						
						
					</div>
					
				</div>
			</div>



					<c:choose>
						<c:when test="${fn:length(SelectedBoardAnswerList) == 0}">
							<div class="col-md-12" style="padding: 0px;background: #EAEAEA">
								<div class="col-md-6 col-md-offset-3" style="background: white; padding: 0px; margin-top: 50px; margin-bottom: 50px;">
									<div class="col-md-12">
										<h4 align="center">[임시]답변이 없습니다</h4>
									</div>
								</div>
							</div>
						</c:when>
						<c:when test="${fn:length(SelectedBoardAnswerList) != 0}">
							<div class="col-md-12" style="padding: 0px;background: #EAEAEA">
								
								<div class="col-md-6 col-md-offset-3" style="background: white; padding: 0px; margin-top: 100px; margin-bottom: 0px;">
									<c:forEach items="${SelectedBoardAnswerList}" var="SelectedBoardAnswerList">
										<input type="hidden" value="${SelectedBoardAnswerList.num}" name="answernum">
										<input type="hidden" value="${SelectedBoardAnswerList.writernum}" id="answerwriternum">
										<!-- 이미지 -->
										<div class="col-md-2" style="height: 100px; padding: 0px;">
											<c:choose>
												<c:when test="${SelectedBoardAnswerList.adop eq false}">
													<img src="/resources/image/answer.png" class="img-rounded">
												</c:when>
												<c:when test="${SelectedBoardAnswerList.adop eq true}">
													<img src="/resources/image/adan.png" class="img-rounded">
												</c:when>
											</c:choose>
											
										</div>		
										<div class="col-md-10  panel panel-default" style="height: 100px;padding-bottom: 25px;">
											<!-- 작성자 -->
											<div class="col-md-12" style="height: 50px;margin: 0px">
												<h4 align="center">
													<span class="glyphicon glyphicon-ok-sign" aria-hidden="true"></span>
													[${SelectedBoard.title}]에 대한 답변
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
										
									</h4>
								</div>
								<!-- 수정 -->
								<div class="col-md-1" style="padding: 0px">
									<button type="button" class="btn btn-primary btn-xs" style="margin: 10px 0px 10px 0px;" id="answermodity">
										<span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
										수정
									</button>
								</div>
								<!-- 삭제-->
								<div class="col-md-1" style="padding: 0px">
									<button type="button" class="btn btn-primary btn-xs" style="margin: 10px 0px 10px 0px;" id="answerdelete">
										<span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
										삭제
									</button>
								</div>
								<div class="col-md-1" style="padding: 0px">
									<button type="button" class="btn btn-primary btn-xs" style="margin: 10px 0px 10px 0px;"  name="adopbutton">
										<span class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span>
										채택
									</button>
								</div>
							</div>
							<div class="col-md-12" style="height: 100px;padding: 0px;background: #EAEAEA"></div>
									</c:forEach>
								</div>		
							</div>
							
						</c:when>
					</c:choose>
					<div class="col-md-6 col-md-offset-3" style="padding: 0px; ">
						<form method="post" action="/answerwrite" id="answerwriteform">
							<input type="hidden" name="boardnum" value="${SelectedBoard.boardnum}">
								<div class="col-md-1" style="height: 50px; padding: 0px;">
									<img src="/resources/image/title.png" width="50px" height="50px" class="img-rounded">
								</div>	
								<div class="col-md-11" style="height: 50px; padding: 0px; background: white;">
									<h3 style="margin:15px 0px 15px 0px; text-align: center">[${SelectedBoard.title}] 에 대한 답변</h3>
								</div>
								<div class="col-md-12" style="padding: 0px;">
									<textarea  name="content" ></textarea>
								</div>
					<button class="btn btn-primary col-md-4 col-md-offset-1" style="margin-top: 25px;" type="button" id="answerwritebutton">확인</button>
					<button class="btn btn-primary col-md-4 col-md-offset-2"  style="margin-top: 25px;"type="button">목록보기</button>
				</form>
			</div>
					
					
					
			</div>
				<div style="height: 100px; background: #E4F7BA; padding-top: 50px">
					<p>만든이: 이승렬	-	이메일: dltmdfuf95@naver.com	-	사이트 이용약관	-	개인정보처리방침</p>
				</div>
	  </div>
	</div>
</body>
<script>
CKEDITOR.replace('content', {
	filebrowserUploadUrl: '/resources/image'
});
</script>
</html>