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
    get.jsp
  </xmp>   
  <form action="/board/register" method="post">
     <table>  
       <tbody>
         <tr>
           <th>글번호</th>
           <td><input type="text" name="bno" class="full"  readonly="readonly"  value="${ boardVO.bno }"></td>        
         </tr> 
         <tr>
           <th>제목</th>
           <td><input type="text" name="title" class="full"  readonly="readonly"  value="${ boardVO.title }"></td>        
         </tr> 
         <tr>
           <th>내용</th>
           <td><textarea  name="content" class="full" readonly="readonly"><c:out value="${ boardVO.content }"></c:out></textarea></td>        
         </tr> 
         <tr>
           <th>작성자</th>
           <td><input type="text" name="writer" class="short" readonly="readonly" value="${ boardVO.writer }"></td>        
         </tr>  
       </tbody> 
       <tfoot>
         <tr>
           <td colspan="2">
             <button type="button"  data-oper="modify" class="edit">Modify</button>
             <button type="button"  data-oper="remove" class="delete">Delete</button>
             <button type="button" data-oper="list"  class="list"> List</button>
           </td>
         </tr>
       </tfoot>
     </table>
     <input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
       		<input type="hidden" name="pageNum" value="${param.pageNum}" >
  			<input type="hidden" name="amount" value="${param.amount}" >
  		<!-- 검색 번호 유지  -->
			<input type="hidden" name="type" value="${ param.type }">
	        <input type="hidden" name="keyword" value='<c:out value="${ param.keyword }"/>'>

			
  </form>    
</div>
<script>

$(function(){
	var formObj = $("form");
	
	$("tfoot button").on("click",function(event){
		var operation = $(this).data("oper");
			
		if (operation ==='modify') {
			// location.href="/board/modify?bno=2"
				formObj
					.attr({
						"action":"/board/modify",
						"method":"get"
					})
				.submit();
		}else if (operation ==='remove') {
			// location.href="/board/remove?bno=5"
					if (confirm("정말 삭제할거냐? ")) {
						formObj
						.attr({
							"action":"/board/remove",
							"method":"get"
						})
					.submit();		
					}
		}else if (operation ==='list') {
			// location.href="/board/list"
				let pageNumTag = $(":hidden[name='pageNum']").clone();
				let amountTag= $(":hidden[name='amount']").clone();
				let type= $(":hidden[name='type']").clone();
				let keyword= $(":hidden[name='keyword']").clone();
						formObj
						.attr({
							"action":"/board/list",
							"method":"get"
						})
						.empty()
						.append(pageNumTag)
						.append(amountTag)
						.append(type)
						.append(keyword)
						//.append("<input type='hidden' name='pageNum' value='" + ${param.pageNum}+"'>")
						//.append("<input type='hidden' name='amount' value='" + ${param.amount}+"'>")	
					.submit();
		}//if
		
	});// click
	
	$(function(){ 
		var result = '<c:out value="${result}"/>';
		checkModal(result);
		//뒤로가기
		history.replaceState({}, null, null);
		
		function checkModal(result){
			if(  result == "success"  ) alert( result );
		}
	});//fu
	
});// //document.ready



</script>

</body>
</html>