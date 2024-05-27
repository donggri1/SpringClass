<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world! - ${ user } 
</h1>

<P>  The time on the server is ${serverTime}. </P>

<h3>
  <a href="/board/list">/board/list</a>
  <a href="/product/view?productid=3000000000001">/product/view</a>
</h3>

</body>
</html>
S