<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	  $('#loginbutton').click(function(){
		 if( $('#num').val()==''){
			 alert("학번을 제대로");
			 return;
		 }
		  $.ajax({
			  url : "/logindo",
			  type : "post",
			  data : {"num":$('#num').val(),"password":$('#password').val()},
			  success : function(result){
				  if(result=="1"){
					  location.href = '/login';
				  }
				  else{
					  alert('아이디와 비밀번호를 확인하세요'); 
				  }
				  
			  }
		  });
  		});
  } );
</script>
</head>
<body>
<div class="center-block" align="center" style="width: 300px; height: 600px">
	<form method="post" action="/logindo" class="center-bloc">
	<div class="form-group">
		<input type="text" class="form-control" name="num" id="num" required="required" placeholder="학번">
	</div>
	
	<div class="form-group">
		<input type="password" class="form-control" name="password" id="password" required="required" placeholder="비밀번호">
	</div>
	
	<div class="form-group">
		<button class="btn btn-primary btn-lg btn-block" type="button" name="loginbutton" id="loginbutton">로그인</button>
	</div>
	
	<div class="form-group">
		<button class="btn btn-primary btn-lg btn-block" type="button" onclick="location.href='/join'">회원가입</button>
	</div>
	
	<div class="form-group">
		<button class="btn btn-primary btn-lg btn-block" type="button" onclick="location.href='/'">메인</button>
	</div>
	</form>
</div>
</body>
</html>