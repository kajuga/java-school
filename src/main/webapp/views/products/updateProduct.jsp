<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Update section</title>
</head>

<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Главная страница</a>
</p>

<form action="${pageContext.servletContext.contextPath}/editProduct?id=${id}" method="POST">
<%--    <input type="hidden" name="id" value="${id}">--%>
    <table>
        <tr>
            <td align="right" >Id : </td>
            <td align="right" >${id} </td>
        </tr>
        <tr>
            <td align="right" >Title : </td>
            <td>
                <input type="text" name="title" value="${title}">
            </td>
        </tr>
        <tr>
            <td align="right" >Category : </td>
            <td>
                <select name="category">
                    <option value="${category.getId()}" selected>${category.getTitle()}</option>
                    <c:forEach items="${categories}" var="item">
                        <c:if test="${item != selected}">
                            <option value="${item.getId()}">${item.getTitle()}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td align="right" >Brand : </td>
            <td>
                <input type="text" name="brand" value="${brand}">
            </td>
        </tr>
        <tr>
            <td align="right" >Color : </td>
            <td>
                <input type="text" name="color" value="${color}">
            </td>
        </tr>
        <tr>
            <td align="right" >Weight : </td>
            <td>
                <input type="text" name="weight" value="${weight}">
            </td>
        </tr>
        <tr>
            <td align="right" >Price : </td>
            <td>
                <input type="text" name="price" value="${price}">
            </td>
        </tr>
        <tr>
            <td align="right" >Description : </td>
            <td>
                <input type="text" name="description" value="${description}">
            </td>
        </tr>
        <tr>
            <td align="right" >Count : </td>
            <td>
                <input type="text" name="count" value="${count}">
            </td>
        </tr>

        <tr>
            <td><input type="submit" align="center" value="Update product"/></td>
        </tr>
    </table>
</form>

</body>
</html>