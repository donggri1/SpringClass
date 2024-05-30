<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<article>
	<h1>Main page! - ${user }</h1>

	<P>The time on the server is ${serverTime}.</P>

	<h3>
		<a href="/time">/time</a>
	</h3>
</article>