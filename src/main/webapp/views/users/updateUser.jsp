<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Update user section</title>
</head>

<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Главная страница</a>
</p>
<div style="border:1px solid #D0D0D0;width:600px;padding:10px;">
    <c:if test="${role=='ADMIN'}">
<form action="${pageContext.servletContext.contextPath}/editUser?id=${id}" method="POST">
    </c:if>

    <%--    <input type="hidden" name="id" value="${id}">--%>
    <table>
        <tr>
            <td align="right" hidden>id : </td>
            <td align="right" hidden>${id} </td>
        </tr>
        <tr>
            <td align="right" >Name : </td>
            <td>
                <input type="text" name="name" value="${name}">
            </td>
        </tr>
        <tr>
            <td align="right" >Last name : </td>
            <td>
                <input type="text" name="lastName" value="${lastName}">
            </td>
        </tr>
        <tr>
            <td align="right" >Birthdate : </td>
            <td>
                <input type="date" name="birthDate" value="${birthDate}">
            </td>
        </tr>
        <tr>
            <td align="right" >Role : </td>
            <td>
                <input type="text" name="role" value="${role}" readonly>
            </td>
        </tr>
        <tr>
            <td align="right" >eMail : </td>
            <td>
                <input type="text" name="mail" value="${mail}">
            </td>
        </tr>
        <tr>
            <td align="right" >Password : </td>
            <td>
                <input type="text" name="password" value="${password}">
            </td>
        </tr>

        <tr>
            <td><input type="submit" align="center" value="Save"/></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>