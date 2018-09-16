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
                <th><a href="<spring:url value="/books/page=0&sort=title"/>">Title</a></th>
                <th><a href="<spring:url value="/books/page=0&sort=author.lastName"/>">Author</a></th>
                <th><a href="<spring:url value="/books/page=0&sort=pageCount"/>">Page Count</a></th>
                <th><a href="<spring:url value="/books/page=0&sort=price"/>">Price</a></th>
            </tr>

            <c:forEach items="${page.content}" var="book">
                <tr>
                    <td><a href="<spring:url value="/books/${book.bookId}"/>">${book.title}</a></td>
                    <td>${book.author.lastName}, ${book.author.firstName}</td>
                    <td>${book.pageCount}</td>
                    <td>${book.price}</td>
                </tr>
            </c:forEach>

        </table>

        <a href="<spring:url value="/books?page=${page.number - 1}&sort=${sort}"/>" > Previous </a>
        <a href="<spring:url value="/books?page=${page.number + 1}&sort=${sort}"/>" > Next </a>

    </body>
</html>
