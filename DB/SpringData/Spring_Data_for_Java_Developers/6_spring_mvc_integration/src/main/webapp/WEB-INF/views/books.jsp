<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Books</title>
</head>
<body>
<h1>Books</h1>
<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Page Count</th>
        <th>Price</th>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><a href="<spring:url value="/books/${book.bookId}"/>">${book.title}</a></td>
            <td>${book.author.lastName}, ${book.author.firstName}</td>
            <td>${book.pageCount}</td>
            <td>${book.price}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
