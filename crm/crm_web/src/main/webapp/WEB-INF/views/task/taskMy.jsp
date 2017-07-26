<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-首页</title>
    <%@ include file="../base/base-css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="../base/base-side.jsp">
        <jsp:param name="active" value="taskMy"/>
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
                        <c:choose>
                            <c:when test="${not (param.show == 'all')}">
                                <a href="/task/my?show=all" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示所有任务</a>
                            </c:when>
                            <c:otherwise>
                                <a href="/task/my" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示未完成的任务</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="box-body">
                    <ul class="todo-list">
                        <c:forEach items="${taskList}" var="task">
                            <li class="${task.done ? 'done' : ''}">
                                <input type="checkbox" class="task_checkbox" ${task.done ? 'checked' : ''} value="${task.id}">
                                <span class="text">${task.taskName}</span>
                                <c:choose>
                                    <c:when test="${not empty task.customer and not empty task.customer.id}">
                                        <a href="/customer/my/${task.customer.id}"><i class="fa fa-user-o"></i> ${task.customer.custName}</a>
                                    </c:when>
                                    <c:when test="${not empty task.saleChance and not empty task.saleChance.id}">
                                        <a href="/sales/chance/${task.saleChance.id}"><i class="fa fa-money"></i> ${task.saleChance.name}</a>
                                    </c:when>
                                </c:choose>
                                <small class="label label-danger"><i class="fa fa-clock-o"></i>${task.completeTime}</small>
                                <div class="tools">
                                    <i class="fa fa-edit edit_task" rel="${task.id}"></i>
                                    <i class="fa fa-trash-o del_task" rel="${task.id}"></i>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        <%--编辑待办事项模态框--%>
            <div class="modal fade" id="taskModal">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">修改待办事项</h4>
                  </div>
                  <div class="modal-body">
                      <form id="taskForm" action="/task/my/edit/${currentTask.id}" method="post">
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
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="saveTask">保存修改</button>
                  </div>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
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
        $(".del_task").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该待办事项",function () {
                window.location.href = "/task/my/del/" + id;
            })
        });
        //修改状态
        $(".task_checkbox").click(function () {
            var id = $(this).val();
            var checked = $(this)[0].checked;
            if(checked) {
                window.location.href = "/task/my/" + id + "/state/done";
            }else{
                window.location.href = "/task/my/" + id + "/state/undone";
            }
        });
        $(".edit_task").click(function() {
            var id = $(this).attr("rel");
            window.location.href = "/task/my/edit/" + id;
            $("#taskModal").modal({
                show:true,
                backdrop:'static'
            });
        });
        $("#saveTask").click(function () {
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
