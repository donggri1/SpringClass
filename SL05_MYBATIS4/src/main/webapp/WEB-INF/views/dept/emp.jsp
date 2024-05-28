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
			<a href="#" style="position: absolute; top: 30px;">youngjin HOme</a>
		</h1>
		<ul>
			<li><a href="#">로그인</a></li>
			<li><a href="#">회원가입</a></li>
		</ul>
	</header>
	<h3>
		<span class="material-symbols-outlined">view_list</span> jsp days00
	</h3>
	<div>
		<xmp class="code"> </xmp>
		<form action="/dept/emp" method="post">

			<c:forEach items="${desList}" var="desDto">
				<table id="tbl-emp-${desDto.deptno}">
					<caption>
						<h3>${desDto.deptno} / ${desDto.dname} / ${desDto.loc}</h3>
					</caption>
					<thead>
						<tr>
							<th>Eno</th>
							<th>Ename</th>
							<th>Job</th>
							<th>Hiredate</th>
							<th>Sal</th>
							<th>Gr</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty desDto.empList}">
								<c:forEach items="${ desDto.empList }" var="empDto">
									<tr>
										<td>${ empDto.empno }</td>
										<td>${ empDto.ename }</td>
										<td>${ empDto.job }</td>
										<td>${ empDto.hiredate }</td>
										<td>${ empDto.sal }</td>
										<td>${ empDto.salgradeDTO.grade }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6">no emp...</td>
								</tr>
							</c:otherwise>
						</c:choose>


					</tbody>
					<tfoot>
						<tr>
							<td colspan="9">
								<button id="home" class="home">HOme</button>
							</td>
						</tr>
					</tfoot>
				</table>
			</c:forEach>
		</form>
	</div>

	<script>
</script>

</body>
</html>