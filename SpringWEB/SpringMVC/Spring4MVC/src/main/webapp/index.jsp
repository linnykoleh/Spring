 <%--
  Created by IntelliJ IDEA.
  User: olinnyk
  Date: 4/14/17
  Time: 10:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>

    <p>Hello ${event.name} !</p>
    <p>Hello ${attendee.name} | ${attendee.emailAddress} !</p>

    <a href="event"> Add event >> </a>
    </br>
    <a href="attendee"> Add attendee >> </a>
</body>
</html>
