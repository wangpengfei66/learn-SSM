<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-编辑待办事项</title>
    <%@ include file="../base/base-css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <c:if test="${not empty message}">
        <h3>${message}</h3>
    </c:if>
    <jsp:include page="../base/base-side.jsp">
        <jsp:param name="active" value="taskAdd"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增待办任务</h3>

                    <div class="box-tools pull-right">
                        <a href="/task/my" type="button" class="btn btn-primary">
                            <i class="fa fa-list"></i> 返回列表
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <form id="taskForm" action="/task/my/edit" method="post">
                        <input type="hidden" name="id" value="${currentTask.id}">
                        <input type="hidden" name="accountId" value="${currentTask.accountId}">
                        <div class="form-group">
                            <label>任务名称</label>
                            <input type="text" class="form-control" name="taskName" value="${currentTask.taskName}">
                        </div>
                        <div class="form-group">
                            <label>完成日期</label>
                            <input type="text" class="form-control" id="datepicker" name="completeTime" value="${currentTask.completeTime}">
                        </div>
                        <div class="form-group">
                            <label>提醒时间</label>
                            <input type="text" class="form-control" id="datepicker2" name="reminderTime" value="${currentTask.reminderTime}">
                        </div>
                    </form>
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <button class="btn btn-primary" id="taskBtn">保存</button>
                </div>
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
<script src="/static/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/static/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        //新增待办事项
        $("#taskBtn").click(function () {
            $("#taskForm").submit();
        });
        $("#taskForm").validate({
            errorClass:"text-danger", //设置提示字体的样式
            errorElement:"span",
            rules:{
                //规则
                taskName:{
                  required:true
                },
                completeTime:{
                    required:true
                }
            },
            messages:{
                //提示信息内容
                taskName:{
                    required:"请输入待办事项名称"
                },
                completeTime:{
                    required:"请输入要完成的时间"
                }
            }
        });

        var picker = $('#datepicker').datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true,
            startDate:moment().format("yyyy-MM-dd")
        });
        picker.on("changeDate",function (e) {
            var today = moment().format("YYYY-MM-DD");
            $('#datepicker2').datetimepicker('setStartDate',today);
            $('#datepicker2').datetimepicker('setEndDate', e.format('yyyy-mm-dd'));
        });

        var timepicker = $('#datepicker2').datetimepicker({
            format: "yyyy-mm-dd hh:ii",
            language: "zh-CN",
            autoclose: true,
            todayHighlight: true
        });

    });
</script>

</body>
</html>
