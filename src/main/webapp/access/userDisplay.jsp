<%--
  Created by IntelliJ IDEA.
  User: kajuga
  Date: 15.03.20
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Main page</a>
</p>
<p>
<td><a href="${pageContext.servletContext.contextPath}/viewProduct">Перейти в список товаров</a></td>
</p>

<div style="border:1px solid #D0D0D0;width:600px;padding:10px;">
Welcome! You are Logged in
<p>
<tr>
    <td><a href="${pageContext.servletContext.contextPath}/editUser">Edit profile</a></td>
</tr>
</p>

</div>
</body>
</html>