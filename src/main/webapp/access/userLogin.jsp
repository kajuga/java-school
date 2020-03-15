<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Main page</a>
</p>
<form action="${pageContext.servletContext.contextPath}/loginCheck" method="POST">
    <table>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="mail" required></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" required></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="login"></td>
        </tr>
    </table>
</form>
</body>
</html>
