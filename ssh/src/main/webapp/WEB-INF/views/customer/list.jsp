<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                搜索
            </div>
            <div class="panel-body">
                <form class="form-inline" method="get">
                    <input type="text" placeholder="客户名称 或 联系方式" class="form-control" name="q_s_like_custName_or_tel" value="${q_s_like_custName}">
                    <input type="text" placeholder="客户Id" class="form-control" name="q_i_eq_id" value="${q_i_eq_id}">
                    <input type="text" placeholder="账号Id" class="form-control" name="q_i_eq_account.id" value="${requestScope['q_i_eq_account.id']}">
                    <input type="text" placeholder="账号名称" class="form-control" name="q_s_eq_a.userName" value="${requestScope['q_s_eq_a.userName']}">
                    <button class="btn btn-primary">
                        搜索
                    </button>
                </form>
            </div>
        </div>

        <table class="table">
            <thead>
            <tr>
                <th>客户名称</th>
                <th>级别</th>
                <th>联系电话</th>
                <th>地址</th>
                <th>所属员工</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.items}" var="cust">
                <tr>
                    <td>${cust.custName}</td>
                    <td>${cust.level}</td>
                    <td>${cust.tel}</td>
                    <td>${cust.address}</td>
                    <td>${cust.account.userName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <ul id="pagination-demo" class="pagination-sm"></ul>
    </div>
    <script src="/static/js/jquery.js"></script>
    <script src="/static/layer/layer.js"></script>
    <script src="/static/js/jquery.twbsPagination.min.js"></script>
    <script>
        $(function () {
            //分页
            $('#pagination-demo').twbsPagination({
                totalPages: ${page.totalPageSize},
                visiblePages: 4,
                first:'首页',
                last:'末页',
                prev:'上一页',
                next:'下一页',
                href:"?p={{number}}&q_s_eq_a.userName=" + encodeURIComponent("${requestScope['q_s_eq_a.userName']}")
            });

        });

    </script>
</body>
</html>
