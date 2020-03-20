<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add address</title>

</head>
<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Main page</a>
</p>

<div style="border:1px solid #D0D0D0;width:600px;padding:10px;">
    <form action="${pageContext.servletContext.contextPath}/createAddress" method="POST">

        <table>
            <tr>
                <td align="right" >First name : </td>
                <td>
                    <input type="text" name="name">
                </td>
            </tr>
            <tr>
                <td align="right" >Last name : </td>
                <td>
                    <input type="text" name="lastName">
                </td>
            </tr>

            <tr>
                <td align="right" >Country : </td>
                <td>
                    <input type="text" name="country">
                </td>
            </tr>
            <tr>
                <td align="right" >City : </td>
                <td>
                    <input type="text" name="city">
                </td>
            </tr>
            <tr>
                <td align="right" >Postal code : </td>
                <td>
                    <input type="text" name="postcode">
                </td>
            </tr>
            <tr>
                <td align="right" >Street : </td>
                <td>
                    <input type="text" name="street">
                </td>
            </tr>

            <tr>
                <td align="right" >House number : </td>
                <td>
                    <input type="text" name="houseNumber">
                </td>
            </tr>

            <tr>
                <td align="right" >Apartment number : </td>
                <td>
                    <input type="text" name="room">
                </td>
            </tr>
            <tr>
                <td align="right" >tel. : </td>
                <td>
                    <input type="text" name="phone" pattern="+7[0-9]{3}-[0-9]{7}">
                </td>
            </tr>


            <tr>
                <td><input type="submit" align="center" value="Save address"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>