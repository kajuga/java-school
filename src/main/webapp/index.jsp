<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body bgcolor="#d4d7dc">
<p><a href="${pageContext.servletContext.contextPath}/access/userLogin.jsp">Login</a></p>

<p><a href="${pageContext.servletContext.contextPath}/views/users/createUser.jsp">Sign in</a></p>
<p><a href="categories">View categories</a></p>
<p><a href="viewProduct">View products</a></p>
<p><a href="logout">Log out</a></p>




<hr>
<p><a href="editAddress">Edit delivery address</a></p>

<p><a href="OrdersSpecificUser">View orders current user</a></p>

<%--<p><a href="createUser">Sign in</a></p>--%>
<p><a href="editUser">Edit current profile</a></p>
<p></p>
<p></p>

<p><a href="viewUser">Users</a></p>
<p><a href="viewAddress">Addresses</a></p>

<p><a href="OrdersAllUsers">View all orders in database</a></p>

<p><a href="ServletAddCategory">Добавить тест категории</a></p>
<p><a href="ServletAddProduct">Добавить test продукты</a></p>






</body>
</html>
