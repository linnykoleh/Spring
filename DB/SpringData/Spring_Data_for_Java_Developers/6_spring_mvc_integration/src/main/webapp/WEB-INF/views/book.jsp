<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Book Manager</title>
</head>
<body>
    <h1>Books</h1>

    <label>Title</label>
    <span>${book.title}</span>
    <br/>
    <label>Author</label>
    <span>${book.author.lastName}, ${book.author.firstName}</span>
    <br/>
    <label>Page Count</label>
    <span>${book.pageCount}</span>
    <br/>
    <label>Price</label>
    <span>${book.price}</span>

    </body>
</html>
