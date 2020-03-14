<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Clients section</title>
</head>
<body>

<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Главная страница</a>
</p>
<a href="${pageContext.servletContext.contextPath}/createClient">Add client</a>
<table border="1">
    <tr>
        <td>id</td>
        <td>Name</td>
        <td>Last name</td>
        <td>Birth date</td>
        <td>mail</td>
        <td>password</td>

    </tr>
    <c:forEach items="${clients}" var="client">
    <tr>
        <td>${client.id}</td>
        <td>${client.name}</td>
        <td>${client.lastName}</td>
        <td>${client.birthDate}</td>
        <td>${client.mail}</td>
        <td>${client.password}</td>
        <td><a href="${pageContext.servletContext.contextPath}/deleteClient?delete=${client.id}">Delete</a></td>
        <td><a href="${pageContext.servletContext.contextPath}/editClient?edit=${client.id}">Update</a></td>
    </tr>
    </c:forEach>

</body>
</html>