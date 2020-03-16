<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products section</title>
</head>

<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Главная страница</a>
</p>
<a href="${pageContext.servletContext.contextPath}/createProduct">Add product</a>

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
    <c:forEach items="${products}" var="product">
    <tr>
        <td>${product.id}</td>
        <td>${product.title}</td>
        <td>${product.category.title}</td>
        <td>${product.brand}</td>
        <td>${product.color}</td>
        <td>${product.weight}</td>
        <td>${product.price}</td>
        <td>${product.description}</td>
        <td>${product.count}</td>
        <td><a href="${pageContext.servletContext.contextPath}/deleteProduct?delete=${product.id}">Delete</a></td>
        <td><a href="${pageContext.servletContext.contextPath}/editProduct?edit=${product.id}">Update</a></td>

        <td><form action="${pageContext.servletContext.contextPath}/shoppingCart" method="post">
            <input type="number" id="quantity" name="quantity" min="1" max=${product.count} size="5">
            <input name="id" value="${product.id}" hidden>
            <input type="submit" value="add to cart"></form></td>
    </tr>
    </c:forEach>

</body>
</html>

