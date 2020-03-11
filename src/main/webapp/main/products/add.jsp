<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add product</title>

</head>
<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Главная страница</a>
</p>

<form action="${pageContext.servletContext.contextPath}/products" method="POST">
    <table>
        <tr>
            <td align="right" >Title : </td>
            <td>
                <input type="text" name="title">
            </td>
        </tr>
        <tr>
            <td align="right" >Category : </td>
            <td>
                <select name="category">
                    <option value="${selected.getId()}" selected>${selected.getTitle()}</option>
                    <c:forEach items="${categories}" var="category">
                        <c:if test="${category != selected}">
                            <option value="${category.getId()}">${category.getTitle()}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td align="right" >Brand : </td>
            <td>
                <input type="text" name="brand">
            </td>
        </tr>
        <tr>
            <td align="right" >Color : </td>
            <td>
                <input type="text" name="color">
            </td>
        </tr>
        <tr>
            <td align="right" >Weight : </td>
            <td>
                <input type="text" name="weight">
            </td>
        </tr>
        <tr>
            <td align="right" >Price : </td>
            <td>
                <input type="text" name="price">
            </td>
        </tr>
        <tr>
            <td align="right" >Description : </td>
            <td>
                <input type="text" name="description">
            </td>
        </tr>
        <tr>
            <td align="right" >Count : </td>
            <td>
                <input type="text" name="count">
            </td>
        </tr>

        <tr>
            <td><input type="submit" align="center" value="Create"/></td>
        </tr>
    </table>
</form>

</body>
</html>
