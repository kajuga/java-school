<%--
  Created by IntelliJ IDEA.
  User: kajuga
  Date: 23.03.20
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Main page</a>
</p>
<div style="border:1px solid #D0D0D0;width:750px;padding:10px;">

    <table border="1">
        <tr>
            <td>Title</td>
            <td>Category</td>
            <td>Brand</td>
            <td>Color</td>
            <td>Weight</td>
            <td>Price</td>
            <td>Description</td>
            <td>Count</td>
            <td>OrderStatus</td>
            <td>PaymentState</td>
        </tr>

        <c:forEach items="${orders}" var="order">
            <tr>
                <td colspan="10" style="background-color: rgba(180,180,172,0.87)">Заказ №${order.id}</td>
            </tr>
            <c:forEach items="${order.products}" var="entry">
                <tr>
                    <td>${entry.key.title}</td>
                    <td>${entry.key.category.title}</td>
                    <td>${entry.key.brand}</td>
                    <td>${entry.key.color}</td>
                    <td>${entry.key.weight}</td>
                    <td>${entry.key.price}</td>
                    <td>${entry.key.description}</td>
                    <td>${entry.value}</td>
                    <td>${order.orderStatus}</td>
                    <td>${order.paymentState}</td>
                </tr>

            </c:forEach>
            <tr>
            <td align="center" colspan="2">
                <form id="fillOrder${order.id}" action="${pageContext.servletContext.contextPath}/shoppingCart">
                       <input type="order_id" id="order_id${order.id}" name="order_id${order.id}" value="${order.id}"hidden>
                      <button type="submit" form="fillOrder${order.id}" style="width: 100%" value="Submit">Repeat this order</button>
                </form>
            </td>
            </tr>

            <tr>
                <td colspan="9" style="background-color: rgba(180,180,172,0.87)"><strong>Сумма заказа :</strong></td>
                <td style="background-color: rgba(180,180,172,0.87)">${order.orderCost}</td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
