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

<div style="border:1px solid #D0D0D0;width:600px;padding:10px;">
<form action="${pageContext.servletContext.contextPath}/createUser" method="POST">

    <table>
        <tr>
            <td align="right" >Name : </td>
            <td>
                <input type="text" name="name" required>
            </td>
        </tr>
        <tr>
            <td align="right" >Last name : </td>
            <td>
                <input type="text" name="lastName" required>
            </td>
        </tr>
        <tr>
            <td align="right" >Birthdate : </td>
            <td>
                <input type="date" name="birthDate" required>
            </td>
        </tr>
        <tr>
            <td align="right" >Role : </td>
           <td>
                <input type="text" name="role" value="USER">
            </td>
        </tr>
<!--<tr>
        <td align="right">Role :</td>
    <td><select name="type" id="role" required>
            <option value="none" hidden="">Choose role</option>
            <option value="USER">customer</option>
            <option value="ADMIN">employee</option>
        </select>
        </td>
</tr>-->

        <tr>
            <td align="right" >eMail : </td>
            <td>
                <input type="text" name="mail" required>
            </td>
        </tr>
        <tr>
            <td align="right" >Password : </td>
            <td>
                <input type="text" name="password" required>
            </td>
        </tr>

        <tr>
            <td><input type="submit" align="center" value="Register"/></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>