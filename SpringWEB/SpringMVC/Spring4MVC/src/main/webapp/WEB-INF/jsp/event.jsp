<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Events</title>
</head>
<body>

    <h2>Event: ${event}</h2>

    <form:form commandName="event">
        <form:errors path="*" element="div"/>
        <label for="textinput1">Enter minutes: </label>
        <form:input path="name"/>
        <form:errors path="name"/>
        <br/>
        <input type="submit" value="Enter event"/>
    </form:form>

</body>
</html>
