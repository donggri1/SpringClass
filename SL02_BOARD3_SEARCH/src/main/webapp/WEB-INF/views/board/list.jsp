<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="shortcut icon" href="/resources/images/SiSt.ico">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resources/cdn-main/example.css">
<script src="/resources/cdn-main/example.js"></script>
<style>
span.material-symbols-outlined {
	vertical-align: text-bottom;
}
</style>
</head>
<body>
	<header>
		<h1 class="main">
			<a href="#" style="position: absolute; top: 30px;">dch HOme</a>
		</h1>
		<ul>
			<li><a href="#">로그인</a></li>
			<li><a href="#">회원가입</a></li>
		</ul>
	</header>
	<h3>
		<span class="material-symbols-outlined">view_list</span> Spring days00
	</h3>
	<div>
		<xmp class="code"> List.jsp </xmp>
		<table>
			<caption style="text-align: right;">
				<a href="/board/register">글쓰기</a>
			</caption>
			<thead>
				<tr>
					<th>#번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>수정일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${ empty  list }">
						<tr>
							<td colspan="5">no board...</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${ list }" var="board">
							<tr>
								<td><c:out value="${ board.bno }" /></td>
								<%-- <td><a href="/board/get?bno=${ board.bno }"><c:out value="${ board.title }" /></a></td> --%>
								<%-- <td><a href="/board/get/${ board.bno }"><c:out value="${ board.title }" /></a></td> --%>
								<td><a class="move" href="${ board.bno }"><c:out
											value="${ board.title }" /></a></td>
								<td><c:out value="${ board.writer }" /></td>
								<td><fmt:formatDate value="${ board.regdate }"
										pattern="yyyy-MM-dd" /></td>
								<td><fmt:formatDate value="${ board.updatedate }"
										pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>

			<!-- 검색 부분  -->
			<tr>
				<td colspan="5" align="center">
					<form id="searchForm" action="/board/list" method="get">
						<select id="type" name="type">
							<option value="T">제목</option>
							<option value="C">내용</option>
							<option value="W">작성자</option>
							<option value="TC">제목 or 내용</option>
							<option value="TW">제목 or 작성자</option>
							<option value="TWC">제목 or 작성자 or 내용</option>
						</select>
						 <input type="text" name="keyword" style="padding: 7px" 
						 	value='<c:out value="${pageMaker.criteria.keyword }"/>'> 
						 <input type="hidden" name="pageNum"	value="${pageMaker.criteria.pageNum}"> 
						 <input type="hidden" name="amount" value="${pageMaker.criteria.amount}">
						<button type="button"  style="min-height: 32px">Search</button>
					</form>
				</td>
			</tr>


			<tfoot>
				<tr>
					<td colspan="5">
						<div class="center">
							<div class="pagination">
								<c:if test="${pageMaker.prev}">
									<a href="${pageMaker.startPage-1}">&laquo;</a>
								</c:if>
								<c:forEach begin="${pageMaker.startPage }"
									end="${pageMaker.endPage}" step="1" var="num">
									<a href="${num}"
										class='${num eq pageMaker.criteria.pageNum?"active":""}'>${num}</a>
								</c:forEach>
								<c:if test="${pageMaker.next}">
									<a href="${pageMaker.endPage+1}">&raquo;</a>
								</c:if>
							</div>
							<!-- pagination -->
						</div>
						<!-- center -->
					</td>
				</tr>

			</tfoot>
		</table>

		<form id="actionForm" action="/board/list" method="get">
			<input type="hidden" name="pageNum"	value="${pageMaker.criteria.pageNum}"> 
			<input type="hidden" name="amount" value="${pageMaker.criteria.amount}">
			<!-- 검색 번호 유지  -->
			<input type="hidden" name="type" value="${ pageMaker.criteria.type }">
	        <input type="hidden" name="keyword" value='<c:out value="${ pageMaker.criteria.keyword }"/>'>
			<!-- 검색 조건 , 검색어  -->
		</form>

	</div>

	<script>
	//rttr.addFlashAttribute("result", boardVO.getBno());
	$(function(){ 
		var result = '<c:out value="${result}"/>';
		checkModal(result);
		//뒤로가기
		history.replaceState({}, null, null);
		
		function checkModal(result){
			if(result==''||history.state)  return;	
			if(  parseInt(  result  ) > 0 ) alert( `\${result} 번이 등록되었습니다.` );
		}//checkModal
		
		// 1. 제목을 클릭하면 상세보기 페이지로 이동
		// href="3" 링크를 번호만준 이유
		// /board/get?bno=&pageNum=&amount&type=&keyword=등등
		var actionForm = $("#actionForm");
		$("a.move").on("click",function(event){
			event.preventDefault();
			
			actionForm
				.attr("action","/board/get")
				.append("<input type='hidden' name='bno' value='" + $(this).attr("href") +"'>")
				.submit();
			
		});//click
		
		// 2. 페이징 번호 클릭 이동.
		$(".pagination a").on("click",function(event){
			event.preventDefault();
						// 클릭한 링크태그
			let pageNum = $(this).attr("href");
						
			//$("#actionForm :hidden[name=pageNum]").val(pageNum);			
			
			actionForm
				.find(":hidden[name='pageNum']").val(pageNum)
					.end()
				.submit();
			
		});//a.번호클릭
		
		// 3. 검색 버튼 클릭 이벤트 처리
	      var searchForm = $("#searchForm");
	      $("#searchForm button").on("click", function(){
	         if (!searchForm.find("input[name='keyword']").val()) {
	            alert("키워드를 입력하세요.")
	            return false;
	         }
	         searchForm.find(":hidden[name='pageNum']").val(1);
	         event.preventDefault();
	         searchForm.submit();
	      });
	      
	      
	      
	        $("#type").val('${empty param.type ? "T" : param.type }'); 
	        /* $("#type").val('$_{criteria.type}'); */ 
	      
	      
	});//fu
</script>

</body>
</html>




















