<%--
  Created by IntelliJ IDEA.
  User: kajuga
  Date: 20.03.20
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">На главную</a>
</p>
<div style="border:1px solid #D0D0D0;width:600px;padding:10px;">

Address successfull created!
now, you can continue shopping
    <p><a href="${pageContext.servletContext.contextPath}/viewProduct">View products</a></p>
    <p><a href="${pageContext.servletContext.contextPath}/editUser">Edit profile</a></p>
    <p><a href="${pageContext.servletContext.contextPath}/editAddress">Edit address</a></p>



</div>
</body>
</html>
