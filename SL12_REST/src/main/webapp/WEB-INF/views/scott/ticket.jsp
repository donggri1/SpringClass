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
    ticket.jsp 
  </xmp>   
	tno : <input type="text" id="tno" value="123"><br>
  owner : <input type="text" id="owner" value="admin"><br>
  grade : <input type="text" id="grade" value="A"><br>
  
  <input type="button" id="btn1" value="ajax" />
<script>
     $("#btn1").on("click", function (event){
        const ticket = {
          tno : $("#tno").val()
          , owner : $("#owner").val()
          , grade  : $("#grade").val()
        };
        
        $.ajax({
           type: 'post'
           , url: '/scott/ticket'
           , data: JSON.stringify(ticket)
           , contentType: 'application/json; charset=UTF-8'
           , success: function (result, status, xhr){
             alert( result );
           }
           , error: function (xhr, staus, er){
              alert( er );
           }
        });
     });
  </script>
    
</div>

<script>
</script>

</body>
</html>