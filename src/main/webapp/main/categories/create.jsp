<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create category</title>
</head>
<body>

<form action="${pageContext.servletContext.contextPath}/categories" method="POST">
    <table>
        <tr>
            <td align="right" >Title : </td>
            <td>
                <input type="text" name="title">
            </td>
        </tr>
        <tr>
            <td><input type="submit" align="center" value="Создать"/></td>
        </tr>
    </table>
</form>

</body>
</html>
