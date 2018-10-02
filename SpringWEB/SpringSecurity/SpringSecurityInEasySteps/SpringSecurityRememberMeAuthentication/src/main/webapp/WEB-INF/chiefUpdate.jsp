<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simple Spring Security</title>
</head>
<body>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
	<h1>This is chief Profile update page</h1>
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="${pageContext.request.contextPath}/logout"> Logout</a>				
 		</h2>
		<p>Your Session id is: "${pageContext.request.session.id}"</p>
		
		<sec:authorize access="isRememberMe()">
		<p>Remember Me Login</p>
		</sec:authorize>

		<sec:authorize access="isFullyAuthenticated()">
		<p>Full Authentication</p>
		</sec:authorize>
	
	</c:if>

</body>
</html>