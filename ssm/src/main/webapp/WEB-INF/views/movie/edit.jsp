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
        <form action="/movie/edit" method="post">
            <input type="hidden" name="id" value="${movie.id}">
            <div class="form-group">
                <label>电影名称</label>
                <input type="text" class="form-control" name="title" value="${movie.title}">
            </div>
            <div class="form-group">
                <label>导演</label>
                <input type="text" class="form-control" name="daoyan" value="${movie.daoyan}">
            </div>
            <div class="form-group">
                <label>上映时间</label>
                <input type="text" class="form-control" name="releaseyear" value="${movie.releaseyear}">
            </div>
            <div class="form-group">
                <label>发行时间</label>
                <input type="text" class="form-control" name="sendtime" value="${movie.sendtime}">
            </div>
            <div class="form-group">
                <label>评分</label>
                <input type="text" class="form-control" name="rate" value="${movie.rate}">
            </div>
            <button class="btn btn-primary">保存</button>
        </form>
    </div>
</body>
</html>
