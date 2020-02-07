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
	  var checknumber;
	  $('#passwordcheck').keyup(function() {
		  var password1 =  $('#password').val();
		  var password2 =  $('#passwordcheck').val();
		  var passwordcheckresult = $('#passwordcheckresult');
		  var pattern = /^[A-za-z0-9]{5,15}$/g;
		  //공백포함시
		  if(password1=="" || password2==""){
			  passwordcheckresult.text('비밀번호 사용불가');
		  }
		  else if(password1==password2){
			  passwordcheckresult.text('비밀번호 일치');
		  }
		  else if(password1!=password2){
			  passwordcheckresult.text('비밀번호 불일치');
		  }
		});
	  
	  $('#password').keyup(function() {
		  var password1 =  $('#password').val();
		  var password2 =  $('#passwordcheck').val();
		  var passwordcheckresult = $('#passwordcheckresult');
		  var pattern = /^[A-za-z0-9]{5,15}$/g;
		  //공백포함시
		  if(password1=="" || password2==""){
			  passwordcheckresult.text('비밀번호 사용불가');
		  }
		  if(!pattern.test(password1)){
			  passwordcheckresult.text('5~15사이로 입력해주세요');
		  }
		  else if(password1==password2){
			  passwordcheckresult.text('비밀번호 일치');
		  }
		  else if(password1!=password2){
			  passwordcheckresult.text('비밀번호 불일치');
		  }
		});
	  
	  
	  $('#id').keyup(function(){
		  var pattern =  /^[A-za-z0-9]{5,15}/g;
		  var joinid = $('#id').val();
		  if($('#id').val()==""){
			  $('#idcheckresult').text('사용 불가한 아이디입니다');
			  return;
		  }
		  else if(!pattern.test($('#id').val())){
			  $('#idcheckresult').text('영문+숫자,5~15사이로 입력해주세요');
			  return;
		  }
		  $.ajax({
			  url : "/idcheck",
			  type : "post",
			  data : {"joinid":joinid},
			  
			  success :  function(result){
				  
				  if(result=='1'){
					  $('#idcheckresult').text('사용불가한 아이디입니다');
				  }
				  else{
					  $('#idcheckresult').text('사용 가능한 아이디입니다');
				  }
				  
			  }
		  });
	  });
	    
	  $('#sendemail').click(function(){
		  var pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		  if(!pattern.test($('#email').val())){
			  alert("이메일이 올바르지않습니다");
			  return;
		  }
		  else{
			  alert('인증번호를 보냈습니다!');
		  }
		  $.ajax({
			  url : "/numbercheck",
			  type : "post",
			  data : {"email":$('#email').val()},
			  success : function(result){
				  checknumber=result;
			  }
		  });
	  });
	  
	  $('#submitbutton').click(function(){
		  if($('#nickname').val()==""){
			  alert('닉네임이 올바르지 않았습니다');
		  }else if($('#name').val()==""){
			  alert('이름이 올바르지 않았습니다');
		  }else if($('num').val()==""){
			  alert('학번이 올바르지 않았습니다');
		  }
		  else if($('#idcheckresult').text()=='사용 가능한 아이디입니다'&&$('#passwordcheckresult').text()=='비밀번호 일치'&&$('#checknumber').val()==checknumber){
			  $('#join').submit();
		  }
		  else{
			  alert('회원가입준비가 되지 않았습니다');
		  }
		  
	  });
	  
	  
  });

  
 
</script>
</head>
<body>
<h3>회원가입창입니다</h3>
<div class="container" style="width: 600px; height: 1200px">
	<form method="post" id="join" action="/join/join">
		<!------------ 아이디입력창 ------------>
		<div class="form-group">
			<input type="text" class="form-control" name="id" id="id" required="required" placeholder="아이디">
		</div>
		<!-- --------------------------- -->
		
		<!------------ 아이디상태창 ------------>
		<div class="row">
			<div class="col-md-12">
				<label id="idcheckresult">사용 불가한 아이디입니다</label>
			</div>
		</div>
		<!-- --------------------------- -->
		
		<!------------ 비밀번호입력창 ------------>
		<div class="form-group">
			<input type="password" class="form-control" name="password" id="password" required="required" placeholder="비밀번호">
		</div>
		<!-- --------------------------- -->
		
		<!------------ 비밀번호입력창 ------------>
		<div class="form-group">
			<input type="password" class="form-control" name="passwordcheck" id="passwordcheck" required="required" placeholder="비밀번호확인">
		</div>
		<!-- --------------------------- -->
		
		<!------------ 비밀번호 확인 텍스트 ------------>
		<div class="form-group">
			<label class="center-block" id="passwordcheckresult">비밀번호 사용불가</label>
		</div>
		<!-- --------------------------- -->
		
		<!------------ 학과선택창 ------------>
		<div class="form-group">
			<input type="text" class="form-control" name="nickname" id="nickname" required="required" placeholder="닉네임">
		</div>
		<!-- --------------------------- -->
		
		<!------------ 이름입력창 ------------>
		<div class="form-group">
			<input type="text" class="form-control" name="name" id="name" required="required" placeholder="이름">
		</div>
		<!-- --------------------------- -->
		
		
		<!------------ 학과선택창 ------------>
		<div class="form-group">
			<select class="form-control" name="major">
				<option>학과1</option>
				<option>학과2</option>
				<option>학과3</option>
				<option>학과4</option>
				<option>학과5</option>
				<option>학과6</option>
				<option>학과7</option>
			</select>
		</div>
		<!-- --------------------------- -->
		
		<!------------ 학번입력창 ------------>
		<div class="form-group">
			<input type="text" class="form-control" name="num" id="num" required="required" placeholder="학번">
		</div>
		<!-- --------------------------- -->
		
		<!------------ 이메일입력창 ------------>
		<div class="form-group">
			<input type="email" class="form-control" name="email" id="email" required="required" placeholder="이메일">
		</div>
		<!-- --------------------------- -->
		<!------------ 인증번호 입력창 ------------>
		<div class="form-group">
			<input type="number" class="form-control" name="checknumber" id="checknumber" required="required" placeholder="인증번호">
		</div>
		<!-- --------------------------- -->
		<!------------ 전송 ------------>
			<div class="form-group">
				<button class="btn btn-primary btn-lg btn-block" type="button" name="sendemail" id="sendemail">인증번호전송</button>
			</div>
		<!-- --------------------------- -->

		
		<div class="row">
		<!------------ 가입하기 ------------>
			<div class="col-md-6">
				<button class="btn btn-primary btn-lg btn-block" type="button" name="submitbutton" id="submitbutton">가입하기</button>
			</div>
		<!-- --------------------------- -->
		<!------------ 메인으로 돌아가기 ------------>
			<div class="col-md-6">
				<button class="btn btn-primary btn-lg btn-block" type="button" onclick="location.href='/'">돌아가기</button>
			</div>
		</div>
		
		
	</form>
</div>
</body>
</html>