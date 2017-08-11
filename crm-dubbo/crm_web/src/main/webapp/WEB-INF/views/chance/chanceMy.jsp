<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-首页</title>
    <%@ include file="../base/base-css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="../base/base-side.jsp">
        <jsp:param name="active" value="chanceMy"/>
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
                        <input type="text" name="keyword" value="${keyword}" placeholder="客户姓名" class="form-control">
                        <button class="btn btn-default"><i class="fa fa-search"></i></button>
                    </form>
                </div>
            </div>
            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">我的销售机会</h3>

                    <div class="box-tools pull-right">
                        <a href="/sales/chance/new" class="btn btn-box-tool">
                            <i class="fa fa-plus"></i> 添加机会
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table table-hover">
                        <thead>
                        <tr width="80">
                            <td>关联客户</td>
                            <td>机会名称</td>
                            <td>机会价值</td>
                            <td>当前进度</td>
                            <td>最后跟进时间</td>
                            <td>
                                #
                            </td>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${pageInfo.list}" var="chance">
                                <tr rel="${chance.id}" class="chance_row">
                                    <td>${chance.customer.custName}</td>
                                    <td>${chance.name}</td>
                                    <td><fmt:formatNumber value="${chance.worth}"/> </td>
                                    <td>${chance.progress}</td>
                                    <td><fmt:formatDate value="${chance.lastTime}" type="date"></fmt:formatDate></td>
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
        $(".chance_row").click(function () {
            var id = $(this).attr("rel");
            window.location.href = "/sales/chance/" + id;
        });
    });
</script>

</body>
</html>
