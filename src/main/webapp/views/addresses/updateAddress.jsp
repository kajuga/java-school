<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Update address section</title>
</head>
<body>
<p>
    <a href="${pageContext.servletContext.contextPath}/index.jsp">Main page</a>
</p>
<div style="border:1px solid #D0D0D0;width:600px;padding:10px;">
    <form action="${pageContext.servletContext.contextPath}/editAddress?id=${id}" method="POST">
        <table>
            <tr>
                <td align="right" >id : </td>
                <td align="right" >${id} </td>
            </tr>
            <tr>
                <td align="right" >First name : </td>
                <td>
                    <input type="text" value="${name}" disabled>
                </td>
            </tr>
            <tr>
                <td align="right" >Last name : </td>
                <td>
                    <input type="text" value="${lastName}" disabled>
                </td>
            </tr>

            <tr>
                <td align="right" >Country : </td>
                <td>
                    <input type="text" value="${country}" name="country">
                </td>
            </tr>
            <tr>
                <td align="right" >City : </td>
                <td>
                    <input type="text" value="${city}" name="city">
                </td>
            </tr>
            <tr>
                <td align="right" >Postal code : </td>
                <td>
                    <input type="text" value="${postcode}" name="postcode">
                </td>
            </tr>
            <tr>
                <td align="right" >Street : </td>
                <td>
                    <input type="text" value="${street}" name="street">
                </td>
            </tr>

            <tr>
                <td align="right" >House number : </td>
                <td>
                    <input type="text" value="${houseNumber}" name="houseNumber">
                </td>
            </tr>

            <tr>
                <td align="right" >Apartment number : </td>
                <td>
                    <input type="text" value="${room}" name="room">
                </td>
            </tr>
            <tr>
                <td align="right" >tel. : </td>
                <td>
                    <input type="text" value ="${phone}" name="phone">
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