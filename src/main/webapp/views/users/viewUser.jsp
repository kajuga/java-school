<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Users section</title>
</head>
<body>

<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Главная страница</a>
</p>
<a href="${pageContext.servletContext.contextPath}/createUser">Add user</a>
<table border="1">
    <tr>
        <td>id</td>
        <td>Name</td>
        <td>Last name</td>
        <td>Birth date</td>
        <td>mail</td>
        <td>password</td>

    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.lastName}</td>
        <td>${user.birthDate}</td>
        <td>${user.mail}</td>
        <td>${user.password}</td>
        <td><a href="${pageContext.servletContext.contextPath}/deleteUser?delete=${user.id}">Delete</a></td>
        <td><a href="${pageContext.servletContext.contextPath}/editUser?edit=${user.id}">Update</a></td>
    </tr>
    </c:forEach>

</body>
</html>