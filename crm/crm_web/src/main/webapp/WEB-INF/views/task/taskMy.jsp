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
        <jsp:param name="active" value="home"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">计划任务</h3>

                    <div class="box-tools pull-right">
                        <a href="/task/my/new" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增任务</a>
                        <a href="/task/my" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示所有任务</a>
                    </div>
                </div>
                <div class="box-body">

                    <ul class="todo-list">
                        <li class="done">
                            <input type="checkbox">
                            <span class="text">给张三打电话联系</span>
                            <a href=""><i class="fa fa-user-o"></i> 张三</a>
                            <small class="label label-danger"><i class="fa fa-clock-o"></i> 7月15日</small>
                            <div class="tools">
                                <i class="fa fa-edit"></i>
                                <i class="fa fa-trash-o"></i>
                            </div>
                        </li>
                        <li>
                            <input type="checkbox">
                            <span class="text">给张三打电话联系</span>
                            <a href=""><i class="fa fa-money"></i> 9号楼23#</a>
                            <small class="label label-danger"><i class="fa fa-clock-o"></i> 8月3日</small>
                            <div class="tools">
                                <i class="fa fa-edit"></i>
                                <i class="fa fa-trash-o"></i>
                            </div>
                        </li>
                        <li>
                            <input type="checkbox">
                            <span class="text">给张三打电话联系</span>
                            <small class="label label-danger"><i class="fa fa-clock-o"></i> 8月5日</small>
                            <div class="tools">
                                <i class="fa fa-edit"></i>
                                <i class="fa fa-trash-o"></i>
                            </div>
                        </li>
                    </ul>
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


</body>
</html>
