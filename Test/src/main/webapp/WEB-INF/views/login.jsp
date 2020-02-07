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
	  
  } );
  function IdCheck(){
	  
  }
</script>
</head>
<body>
<div class="center-block" align="center" style="width: 300px; height: 600px">
	<form method="post" action="/login/login" class="center-bloc">
	<div class="form-group">
		<input type="text" class="form-control" name="id" id="id" required="required" placeholder="아이디">
	</div>
	
	<div class="form-group">
		<input type="password" class="form-control" name="password" id="password" required="required" placeholder="비밀번호">
	</div>
	
	<div class="form-group">
		<button class="btn btn-primary btn-lg btn-block" type="submit">로그인</button>
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