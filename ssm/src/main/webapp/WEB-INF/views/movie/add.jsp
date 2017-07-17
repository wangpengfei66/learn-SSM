<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>add</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <form action="/movie/add" method="post">
            <div class="form-group">
                <label>电影名称</label>
                <input type="text" class="form-control" name="title" value="${}">
            </div>
            <div class="form-group">
                <label>导演</label>
                <input type="text" class="form-control" name="daoyan">
            </div>
            <div class="form-group">
                <label>上映时间</label>
                <input type="text" class="form-control" name="releaseyear">
            </div>
            <div class="form-group">
                <label>发行时间</label>
                <input type="text" class="form-control" name="sendtime">
            </div>
            <div class="form-group">
                <label>评分</label>
                <input type="text" class="form-control" name="rate">
            </div>
            <button class="btn btn-primary">保存</button>
        </form>
    </div>
</body>
</html>
