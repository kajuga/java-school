<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of categories</title>
</head>
<body>

<!--<a href="${pageContext.servletContext.contextPath}/journal/create">Добавить запись</a>

<form name="myform" action="handle-data.php" method="post">
    <label for="query">Search:</label>
    <input type="text" name="query" id="query"/>
    <button>Search</button>
</form>

<script>
    var button = document.querySelector('form[name="myform"] > button');
    button.addEventListener(function() {
        document.querySelector("form[name="myform"]").submit();
    });
</script>-->

<table border="1">
    <tr>
        <td>Id</td>
        <td>Title</td>
    </tr>
    <c:forEach items="${categories}" var="person">
    <tr>
        <td>${person.id}</td>
        <td>${person.title}</td>
    </tr>
    </c:forEach>

</body>
</html>
