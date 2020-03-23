<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products section</title>
</head>

<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Main page</a>
</p>

<div style="border:1px solid #D0D0D0;width:750px;padding:10px;">

<%--<p></p>--%>
<%--<a href="${pageContext.servletContext.contextPath}/createProduct">Add product</a>--%>
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
        <td>In stock</td>
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

        <c:if test="${role=='ADMIN'}">

        <td><a href="${pageContext.servletContext.contextPath}/deleteProduct?delete=${product.id}">Delete</a></td>
        <td><a href="${pageContext.servletContext.contextPath}/editProduct?edit=${product.id}">Update</a></td>
        </c:if>

        <c:if test="${role=='USER'}">
            <td><form id="fillCart${product.id}" action="${pageContext.servletContext.contextPath}/shoppingCart" method="post">
                <input type="number" id="count${product.id}" name="count${product.id}" min="0" max=${product.count} size="5">
                <input name="id" value="${product.id}" hidden>
                <input name="title" value="${product.title}" hidden>
                <input name="category" value="${product.category.id}" hidden>
                <input name="brand" value="${product.brand}" hidden>
                <input name="color" value="${product.color}" hidden>
                <input name="weight" value="${product.weight}" hidden>
                <input name="price" value="${product.price}" hidden>
                <input name="description" value="${product.description}" hidden>
                <button type="submit" form="fillCart${product.id}" value="Submit">add to cart</button>
            </form></td>
        </c:if>

    </tr>
    </c:forEach>
    <p></p>
</table>
<c:if test="${role=='USER'}">
    <a href="${pageContext.servletContext.contextPath}/views/shoppingCart/viewShoppingCart.jsp">Shopping cart</a>
</c:if>
</div>
</body>
</html>

