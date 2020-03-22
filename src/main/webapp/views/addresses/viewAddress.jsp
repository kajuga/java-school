<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Address section</title>
</head>
<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Main page</a>
</p>
<p>
    <a href="${pageContext.servletContext.contextPath}/createAddress">Add delivery address</a>
</p>
<div style="border:1px solid #D0D0D0;width:600px;padding:10px;">
<%--    <c:if test="${empty address.user.name}">--%>
    <hr>
        <p>
            <strong> ${address.user.lastName}, ${address.user.name} </strong>
        </p>
        <p>
            <strong>  ${address.street}, ${address.houseNumber}, ${address.room} </strong>
        </p>
        <p>
            <strong>    ${address.city}, ${address.postcode} </strong>
        </p>
        <p>
            <strong>${address.country}</strong>
        </p>
        <p>
            <strong>${address.phone}</strong>
        </p>
        <hr>
        <a href="${pageContext.servletContext.contextPath}/deleteAddress?delete=${address.id}">Delete address</a>
        <p>
        <a href="${pageContext.servletContext.contextPath}/editAddress">Update delivery address</a>
        </p>
    <hr>
<%--    </c:if>--%>
</div>
</body>
</html>
