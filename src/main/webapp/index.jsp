<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body bgcolor="#d4d7dc">

<p><a href="categories">Categories</a></p>
<p><a href="viewProduct">Products</a></p>
<p><a href="viewUser">Users</a></p>
<p><a href="createUser">Sign in</a></p>
<p><a href="editUser">Edit profile</a></p>
<p><a href="/ishop/access/userLogin.jsp">Login</a></p>
<p><a href="logout">Log out</a></p>
<p></p>
<p></p>

<p><a href="ServletAddCategory">Добавить тест категории</a></p>
<p><a href="ServletAddProduct">Добавить test продукты</a></p>

<br>
<form action="">
    <label for="files">Select files:</label>
    <input type="file" id="files" name="files" multiple><br><br>
    <input type="submit" value="Submit">
</form>




</body>
</html>
