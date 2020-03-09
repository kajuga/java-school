<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of categories</title>
</head>

<body>

<a href="${pageContext.servletContext.contextPath}/main/categories/create.jsp">Добавить категорию</a>

<table border="1">
    <tr>
        <td>Id</td>
        <td>Title</td>
    </tr>
    <c:forEach items="${categories}" var="category">
    <tr>
        <td>${category.id}</td>
        <td>${category.title}</td>
        <td><a href="${pageContext.servletContext.contextPath}/categories?delete=${category.id}">Удалить</a></td>
    </tr>
    </c:forEach>

</body>
</html>

