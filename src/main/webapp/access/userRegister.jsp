<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>To register</title>
</head>
<body>

<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Главная страница</a>
</p>
<form action="/register" method="post">
    <table>
        <tr>
            <td>User name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>User lastname</td>
            <td><input type="text" name="lastName"></td>
        </tr>
        <tr>
            <td>Birthdate</td>
            <td><input type="date" name="birthDate"></td>
        </tr>
        <tr>
            <td align="right" >Role : </td>
            <td>
                <input type="text" name="role">
            </td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="mail"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="register"></td>
        </tr>

    </table>

</form>
</body>
</html>
