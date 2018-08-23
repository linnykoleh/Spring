<%--
  Created by IntelliJ IDEA.
  User: olinnyk
  Date: 4/14/17
  Time: 1:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Add Minutes Page</title>
</head>
<body>

    <h1>Add minutes exercised</h1>

    <form:form commandName="exercise">
        <table>
            <tr>
                <td>Minutes exercise fro today: <addMinutes/td>
                <td><form:input path="minutes"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Enter exercise"/>
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
