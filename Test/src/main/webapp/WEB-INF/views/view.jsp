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
			<!-- 글보기 배경-->
			<div class="col-md-12" style="padding: 0px;background: white">
				<div class="col-md-6 col-md-offset-3" style="padding: 0px; margin-top: 25px; margin-bottom: 25px;">
					<!-- 이미지 -->
					<div class="col-md-2" style="height: 100px; padding: 0px;"><img src="/resources/image/question.png" class="img-rounded"></div>
					<!-- 글제목 -->
					<div class="col-md-10" style="height: 100px; background: #fdfdfd; word-wrap: break-word;"><h4 style="margin: 10px">제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다제목입니다</h4></div>
					<!-- 글내용 -->
					<div class="col-md-12" style="background: #fdfdfd; margin-top: 15px; margin-bottom: 15px;">${SelectedBoard.content}</div>
					<!-- 작성자 -->
					<div class="col-md-3" style="background: #9d0b0b; height: 40px;"><h4>${SelectedBoard.writer}</h4></div>
					<!-- 작성일 -->
					<div class="col-md-3" style="background: #da2d2d; height: 40px;"><h4>${SelectedBoard.date}</h4></div>
					<!-- 조회수 -->
					<div class="col-md-3" style="background: #eb8242; height: 40px;"><h4>${SelectedBoard.viewcount}</h4></div>
					<!-- 댓글 -->
					<div class="col-md-3" style="background: indigo;"><button type="button" class="btn btn-primary btn-sm" style="margin: 5px 0px 5px 0px;">댓글</button></div>
				</div>
			</div>
			
			<div class="col-md-12" style="padding: 0px;background: #EAEAEA">
				
					<c:choose>
						<c:when test="${fn:length(SelectedBoardAnswerList) == 0}">
							<div class="col-md-6 col-md-offset-3" style="background: white; padding: 0px; margin-top: 25px; margin-bottom: 25px;">
								<div class="col-md-12">
									<h3>[임시]답변이 없습니다</h3>
								</div>
							</div>
						</c:when>
						<c:when test="${fn:length(SelectedBoardAnswerList) != 0}">
							<div class="col-md-6 col-md-offset-3" style="background: white; padding: 0px; margin-top: 25px; margin-bottom: 25px;">
								<c:forEach items="${SelectedBoardAnswerList}" var="SelectedBoardAnswerList">
									<!-- 이미지 -->
									<div class="col-md-2" style="height: 100px; padding: 0px;"><img src="/resources/image/answer.png" class="img-rounded"></div>		
									<div class="col-md-10" style="height: 100px; background: #9d0b0b; padding: 0px;">
									<!-- 작성자 -->
									<div class="col-md-12" style="height: 50px; background: brown;"><h4>${SelectedBoardAnswerList.writer}님의 답변</h4></div>
									<!-- 작성일 -->
									<div class="col-md-3" style="height: 50px; background: black;"><h4>${SelectedBoardAnswerList.date}</h4></div>
									<!-- 추천수 -->
									<div class="col-md-3" style="height: 50px; background: indio;"><h4>${SelectedBoardAnswerList.reccount}</h4></div>
									<!-- 댓글,추천하기 -->
									<div class="col-md-6" style="height: 50px; background: pink;">
										<button type="button" class="btn btn-primary btn-sm" style="margin: 5px 0px 5px 0px;">댓글</button>
										<button type="button" class="btn btn-primary btn-sm" style="margin: 5px 0px 5px 0px;">추천</button>
									</div>
									</div>
									<div class="col-md-12" style="background: brown; margin-top: 15px; margin-bottom: 15px;">${SelectedBoardAnswerList.content}</div>
								</c:forEach>
							</div>			
						</c:when>
					</c:choose>
			</div>
	  </div>
	</div>
</div>

</body>
</html>