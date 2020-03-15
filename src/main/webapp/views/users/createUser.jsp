<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add user</title>

</head>
<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Main page</a>
</p>

<form action="${pageContext.servletContext.contextPath}/createUser" method="POST">
    <table>
        <tr>
            <td align="right" >Name : </td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td align="right" >Last name : </td>
            <td>
                <input type="text" name="lastName">
            </td>
        </tr>
        <tr>
            <td align="right" >Birthdate : </td>
            <td>
                <input type="date" name="birthDate">
            </td>
        </tr>
        <tr>
            <td align="right" >Role : </td>
            <td>
                <input type="text" name="role">
            </td>
        </tr>
        <tr>
            <td align="right" >eMail : </td>
            <td>
                <input type="text" name="mail">
            </td>
        </tr>
        <tr>
            <td align="right" >Password : </td>
            <td>
                <input type="text" name="password">
            </td>
        </tr>

        <tr>
            <td><input type="submit" align="center" value="Register"/></td>
        </tr>
    </table>
</form>

</body>
</html>