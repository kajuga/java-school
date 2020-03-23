<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Order confirmation</title>

    <style>

        th {
            background-color: rgba(198, 199, 194, 0.77);
        }
    </style>

</head>
<body>
<p><a href="${pageContext.servletContext.contextPath}/index.jsp">Главная страница</a></p>
<p><a href="${pageContext.servletContext.contextPath}/viewProduct">Catalog of products</a></p>
<div style="border:1px solid #D0D0D0;width:650px;padding:10px;">
    <br>
    <form action="${pageContext.servletContext.contextPath}/createOrder" method="post">


        Delivery address:
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
        Choose delivery method:
        <br>
        <input list="deliveryMethods" name="deliveryMethod" id="deliveryMethod" required>
        <datalist id="deliveryMethods">
            <option value="POST">
            <option value="PICKUP">
            <option value="COURIER">
        </datalist>
        <input type="submit" value="Submit" hidden>

        <hr>
        Choose payment method:
        <br>
        <input list="paymentMethods" name="paymentMethod" id="paymentMethod" required>
        <datalist id="paymentMethods">
            <option value="CASH">
            <option value="CARD_PAYMENT">
        </datalist>
        <input type="submit" value="Submit" hidden>
        <hr>
        <p>
        <strong>Состав заказа: </strong>
        </p>
        <hr>
        <table>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Category</th>
                <th>Brand</th>
                <th>Color</th>
                <th>Weight</th>
                <th>Count</th>
                <th>Description</th>
                <th>Price</th>

            </tr>

            <c:set var="countTotal" value="${0}"/>
            <c:forEach items="${shoppingCart}" var="entry">
                <c:set var="countTotal" value="${countTotal + entry.key.price * entry.value}"/>
                <tr>
                    <td>${entry.key.id}</td>
                    <td>${entry.key.title}</td>
                    <td>${entry.key.category.title}</td>
                    <td>${entry.key.brand}</td>
                    <td>${entry.key.color}</td>
                    <td>${entry.key.weight}</td>
                    <td>${entry.value}</td>
                    <td>${entry.key.description}</td>
                    <td>${entry.key.price}</td>

                </tr>
            </c:forEach>
            <tr>
                <td colspan="8" style="background-color: rgba(180,180,172,0.87)"><strong>Cумма к оплате:</strong></td>
                <td style="background-color: rgba(180,180,172,0.87)">
                    <input type="text" readonly value="${countTotal}" name="countTotal" />
<%--                <td style="background-color: rgba(180,180,172,0.87)">${countTotal} </td>--%>
                </td>
            </tr>
            <br>
        </table>
        <hr>
<%--        <p><a href="${pageContext.servletContext.contextPath}/createOrder">Оплатить и завершить</a></p>--%>
        <tr>
            <td><input type="submit" align="center" value="Оплатить и завершить"/></td>
        </tr>
    </form>
</div>

</body>
</html>
