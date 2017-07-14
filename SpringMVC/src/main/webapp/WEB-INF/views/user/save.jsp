<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <h1>user save</h1>
    <c:if test="${not empty message}">
        <h1>${message}</h1>
    </c:if>
    <form action="/user/save" method="post">
        <input type="text" name="userName">
        <input type="text" name="address">
        <input type="text" name="zipCode">
        <button>保存</button>
    </form>
</body>
</html>
