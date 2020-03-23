<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of categories</title>
</head>

<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Главная страница</a>
</p>

<table border="1">
    <tr>
        <td>Id</td>
        <td>Title</td>
    </tr>
    <c:forEach items="${categories}" var="category">
    <tr>
        <td>${category.id}</td>
        <td>${category.title}</td>
        <c:if test="${role=='ADMIN'}">
        <td><a href="${pageContext.servletContext.contextPath}/categories?delete=${category.id}">Удалить</a></td>
        </c:if>
    </tr>
    </c:forEach>
<c:if test="${role=='ADMIN'}">
    <a href="${pageContext.servletContext.contextPath}/views/categories/createCategory.jsp">Добавить категорию</a>

</c:if>

</body>
</html>

