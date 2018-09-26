<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Attendee</title>
</head>
<body>

    <p>
        <a href="?language=en">English</a>
        <a href="?language=es">Spanish</a>
    </p>

    <p>
        <form:form commandName="attendee">
            <p>
                <form:errors path="*" cssClass="error" element="div"/>
            </p>

            <label for="textinput1"><spring:message code="attendee.name"/> : </label>
            <br/>
            <form:input path="name" cssErrorClass="error"/>
            <form:errors path="name" cssClass="error"/>
            <br/>

            <label for="textinput2"><spring:message code="attendee.email.address"/> : </label>
            <br/>
            <form:input path="emailAddress" cssErrorClass="error"/>
            <form:errors path="emailAddress" cssClass="error"/>
            <br/>

            <label for="textinput3"><spring:message code="attendee.phone"/> : </label>
            <br/>
            <form:input path="phone" cssErrorClass="error"/>
            <form:errors path="phone" cssClass="error"/>

            <br/>
            <input type="submit" class="btn" value="Enter attendee "/>
        </form:form>
    </p>

</body>
</html>
