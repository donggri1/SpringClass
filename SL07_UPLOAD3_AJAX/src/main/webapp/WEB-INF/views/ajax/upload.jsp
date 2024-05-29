<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="shortcut icon" href="/resources/images/SiSt.ico">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
 span.material-symbols-outlined{
    vertical-align: text-bottom;
 }
</style>
</head>
<body>
<header>
  <h1 class="main"><a href="#" style="position: absolute;top:30px;">dch HOme</a></h1>
  <ul>
    <li><a href="#">로그인</a></li>
    <li><a href="#">회원가입</a></li>
  </ul>
</header>
<h3>
  <span class="material-symbols-outlined">view_list</span> Spring days00
</h3>
<div>
  <xmp class="code">  
    WEB-INF/views/ajax/upload.jsp
  </xmp>   
  
  <form action="" method="post" enctype="multipart/form-data">
  	<div><input type="text" name="output" value="hello World!"></div>

  	
  	<div>
  		<input type="file" name="attach"  multiple="multiple">
  		<button id="btnUpload" type="button">ajax upload</button>
  		<script type="text/javascript">
  			$(function () {
				$("#btnUpload").on("click",function(event){
					//1단계 시작
					var formData = new FormData();// ajax 로 담는 클래스
					var inputFile = $(":file[name='attach']");
					var files = inputFile[0].files;
					console.log(files);
					//1단계 끝
					//2단계 시작
					for (var i = 0; i < files.length; i++) {
						formData.append("attachList",files[i]);
					}//for
					//2단계 끝
					//3단계 시작
					$.ajax({
						url:'uploadAjax'
						,processData:false
						,contentType:false
						,data:formData
						,type:'POST'
						,success :function(result){
							alert("ajax fileUploaded");
						}//성공
					});//ajax
					//3단계 끝
					
				});//click
			});
  		</script>
  	</div>
  	
  	<div><input type="text" name="wirter" value="admin"></div>
  	<div><input type="text" name="email" value="admin@naver.com"></div>
  	
  	<div><input type="submit" ></div>
  	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  </form>
  
</div>

<script>
	
</script>

</body>
</html>

