<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Log In</title>
</head>
<style>
.errors{
color:red;
}
</style>
<body>
<p class="errors">${error}</p>

<form:form method="POST" action="login" enctype="text" modelAttribute="user">

		<form:errors path="userName" cssClass="errors" element="div" />

		<form:label path="userName" >Please Enter UserName : </form:label>
		<form:input type="text"	path="userName" />
		<br />
		<br />

		<form:errors path="password" cssClass="errors" element="div" />

		<form:label path="password">Please Enter Password : </form:label>
		<form:input type="password"	path="password" />
		<br />
		<br />

		<input type="submit" value="Log In" />
	</form:form>
</body>
</html>