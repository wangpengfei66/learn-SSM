<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-首页</title>
    <%@ include file="../base/base-css.jsp"%>
    <style>
        .name-avatar {
            display: inline-block;
            width: 50px;
            height: 50px;
            background-color: #ccc;
            border-radius: 50%;
            text-align: center;
            line-height: 50px;
            font-size: 24px;
            color: #FFF;
        }
        .table>tbody>tr:hover {
            cursor: pointer;
        }
        .table>tbody>tr>td {
            vertical-align: middle;
        }
        .star {
            font-size: 20px;
            color: #ff7400;
        }
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="../base/base-side.jsp">
        <jsp:param name="active" value="customerPublic"/>
    </jsp:include>
            
            <!-- 右侧内容部分 -->
            <div class="content-wrapper">
                <!-- Main content -->
                <section class="content">
                    <c:if test="${not empty message}">
                        <h3 class="alert alert-primary">${message}</h3>
                    </c:if>
                    <%--搜索框部分--%>
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">搜索</h3>
                        </div>
                        <div class="box-body">
                            <form action="" class="form-inline">
                                <input type="text" name="keyword" value="${keyword}" placeholder="姓名或者电话" class="form-control">
                                <button class="btn btn-default"><i class="fa fa-search"></i></button>
                            </form>
                        </div>
                    </div>

                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">我的客户</h3>
                            <div class="box-tools pull-right">
                                <a href="/customer/new" class="btn btn-success btn-sm><i class="fa fa-plus"></i>新增客户</a>
                                <a href="/customer/my/export" class="btn btn-primary btn-sm"><i class="fa fa-file-excel-o"></i>导出Excel</a>
                            </div>
                        </div>
                        <div class="box-body no-padding">
                            <table class="table table-hover">
                                <tbody>
                                <tr>
                                    <th width="80"></th>
                                    <th>姓名</th>
                                    <th>职位</th>
                                    <th>跟进时间</th>
                                    <th>级别</th>
                                    <th>联系方式</th>
                                </tr>
                                <c:forEach items="${pageInfo.list}" var="cust">
                                    <tr rel="${cust.id}" class="cust_row">
                                        <td><span class="name-avatar" style="background-color:${cust.sex == '先生' ? '#ccc' : 'pink'};">${fn:substring(cust.custName,0 ,1 )}</span></td>
                                        <td>${cust.custName}</td>
                                        <td>${cust.job}</td>
                                        <td><fmt:formatDate value="${cust.contactTime}" type="date"></fmt:formatDate></td>
                                        <td class="star">${cust.level}</td>
                                        <td><i class="fa fa-phone"></i> ${cust.tel}<br></td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                            <c:if test="${pageInfo.pages > 1}">
                                <ul id="pagination-demo" class="pagination-sm pull-right"></ul>
                            </c:if>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </section>
                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

    <%@ include file="../base/base-footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="../base/base-js.jsp"%>
<script src="/static/plugins/twbsPagination/jquery.twbsPagination.min.js"></script>
<script>
    $(function () {
        $('#pagination-demo').twbsPagination({
            totalPages: ${pageInfo.pages},
            visiblePages: 7,
            first:'首页',
            last:'末页',
            prev:'上一页',
            next:'下一页',
            href:"?p={{number}}&keyword=${keyword}"
        });
        $(".cust_row").click(function () {
           var id = $(this).attr("rel");
           window.location.href = "/customer/my/" + id;
        });
    });
</script>
</body>
</html>
