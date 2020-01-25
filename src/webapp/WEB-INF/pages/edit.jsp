<%--
  Created by IntelliJ IDEA.
  User: darkprado
  Date: 22.01.2020
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><c:out value="${title}" /></title>
</head>
<body>
<h1><c:out value="${h1}" /></h1>
<c:if test="${empty id}">
    <c:url value="/add" var="action"/>
</c:if>
<c:if test="${!empty id}">
    <c:url value="/edit" var="action"/>
</c:if>
<form action="${action}" method="POST">
    <c:if test="${!empty id}">
        <input type="hidden" name="id" value="${id}">
    </c:if>
    <label for="firstName">First name</label>
    <input type="text" name="firstName" id="firstName">
    <label for="lastName">last name</label>
    <input type="text" name="lastName" id="lastName">
    <label for="email">email</label>
    <input type="text" name="email" id="email">
    <input type="submit" value="<c:out value="${submit}" />">
</form>
</body>
</html>
