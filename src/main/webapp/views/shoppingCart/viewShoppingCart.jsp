<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Shopping cart</title>
</head>
<body>
<p><a href="${pageContext.servletContext.contextPath}/index.jsp">Главная страница</a></p>
<p><a href="${pageContext.servletContext.contextPath}/viewProduct">Catalog of products</a></p>

<table border="1">
    <tr>
        <td>Id</td>
        <td>Title</td>
        <td>Category</td>
        <td>Brand</td>
        <td>Color</td>
        <td>Weight</td>
        <td>Price</td>
        <td>Description</td>
        <td>Count</td>
    </tr>


    <c:forEach items="${shoppingCart}" var="entry">
    <tr>
        <td>${entry.key.id}</td>
        <td>${entry.key.title}</td>
        <td>${entry.key.category.title}</td>
        <td>${entry.key.brand}</td>
        <td>${entry.key.color}</td>
        <td>${entry.key.weight}</td>
        <td>${entry.key.price}</td>
        <td>${entry.key.description}</td>
        <td><form id="fillCart${entry.key.id}" action="${pageContext.servletContext.contextPath}/shoppingCartRefresh" method="post">
            <input type="number" id="count${entry.key.id}" name="count${entry.key.id}" value="${entry.value}">
            <input name="id" value="${entry.key.id}" hidden>
            <input name="title" value="${entry.key.title}" hidden>
            <input name="category" value="${entry.key.category.id}" hidden>
            <input name="brand" value="${entry.key.brand}" hidden>
            <input name="color" value="${entry.key.color}" hidden>
            <input name="weight" value="${entry.key.weight}" hidden>
            <input name="price" value="${entry.key.price}" hidden>
            <input name="description" value="${entry.key.description}" hidden>
            <button type="submit" form="fillCart${entry.key.id}" value="Submit">Check out</button>
        </form></td>
    </tr>
    </c:forEach>
</table>

</body>
</html>
