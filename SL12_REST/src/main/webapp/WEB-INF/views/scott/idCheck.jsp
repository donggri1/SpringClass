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
    idCheck.jsp 
  </xmp>   
<div>

   <form action="" method="get">
      empno : <input type="text" id="empno" name="empno" value="7369">
      <input type="button" id="idCheck" value="ID중복체크">
      <br>
      ename : <input type="text" id="ename" name="ename"  value="hong">
      <br>
      <br>
      <br>
      <br>
        
   </form>

</div>

<script>
  $(function (){
    $("#idCheck").on("click", function (){
       var empno = $("#empno").val();
       console.log( `> empno = \${empno}` );
       $.ajax({
          url:"/scott/idCheck"   
          , method: "post"
          , data: { empno : empno }     // js Object
          , dataType: "json"
          , success: function ( data, callback, xhr ){
                   alert( data ); // 1  0
                  // alert( data ); object Object 
                  
                  // {"empno":"7369","ename":"홍길동","idCheckResult":1}                 
                  //alert( data.idCheckResult ); // 1  0
               } 
          , error: function ( xhr, errorType){
                  alert( errorType );
               } 
       });
    }); 
  });
</script> 
</div>

<script>
</script>

</body>
</html>