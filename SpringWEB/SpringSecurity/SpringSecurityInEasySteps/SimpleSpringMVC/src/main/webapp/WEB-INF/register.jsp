<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
.errors{
color:red;
}
</style>
<body>
<p class="errors">${error}</p>
<form:form method="POST" action="register" enctype="text" modelAttribute="user">
    <form:errors path="userName" cssClass="errors" element="div" />

    <form:label path="userName" >Please Enter Unique UserName : </form:label>
    <form:input type="text"	path="userName" />
    <br />
    <br />

    <form:errors path="password" cssClass="errors" element="div" />

    <form:label path="password">Please Enter Password : </form:label>
    <form:input type="password"	path="password" />
    <br />
    <br />

    <form:errors path="phone" cssClass="errors" element="div" />

    <form:label path="phone">Please Enter Phone</form:label>
    <form:input type="text"	path="phone" />
    <br/>
    <br />

    <form:errors path="address" cssClass="errors" element="div" />

    <form:label path="address">Please Enter Address : </form:label>
    <form:input type="text"	path="address" />
    <br />
    <br />
    <input type="submit" value="Sign Up" />
</form:form>
</body>
</html>