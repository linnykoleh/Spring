<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>

<html>
<head>
<title>Sign Up</title>
</head>
<style>
.errors {
	color: red;
}
</style>
<body>
<p class="errors">${error}</p>
	<form:form name="form" modelAttribute="userTo" method="POST" action="signup">
		<table>
			<tr>
				<td><label for="username">Please Enter Unique UserName :</label></td> 
				<td><form:input type="text"	path="username" /></td>
			</tr>
			<tr>
				<td><label for="password">Please Enter Password : </label></td>
				<td><form:input type="password" path="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sign Up"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>