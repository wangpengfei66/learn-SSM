<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-销售机会详情</title>
    <%@ include file="../base/base-css.jsp"%>
    <link rel="stylesheet" href="/static/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <style>
        .td_title {
            font-weight: bold;
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
        <jsp:param name="active" value="home"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">
            <!--销售机会基本资料-->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">销售机会基本资料</h3>
                    <div class="box-tools">
                        <a href="/sales/chance" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                        <button id="delBtn" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 删除</button>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table">
                        <tr>
                            <td class="td_title">机会名称</td>
                            <td>${saleChance.name}</td>
                            <td class="td_title">价值</td>
                            <td><fmt:formatNumber value="${saleChance.worth}"/> </td>
                            <td class="td_title">当前进度</td>
                            <td>
                                ${saleChance.progress}
                                <button class="btn btn-xs btn-success" id="showChangeProgressModalBtn"><i class="fa fa-pencil"></i></button>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="box-footer">
                    <span style="color: #ccc" class="pull-right">
                        创建日期：<fmt:formatDate value="${saleChance.createTime}" pattern="yyyy-MM-dd HH:mm"/>
                    </span>
                </div>
            </div>

            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">关联客户资料</h3>
                </div>
                <div class="box-body no-padding">
                    <table class="table">
                        <tr>
                            <td class="td_title">姓名</td>
                            <td>${saleChance.customer.custName}</td>
                            <td class="td_title">职位</td>
                            <td>${saleChance.customer.job}</td>
                            <td class="td_title">联系电话</td>
                            <td>${saleChance.customer.tel}</td>
                        </tr>
                        <tr>
                            <td class="td_title">所属行业</td>
                            <td>${saleChance.customer.trade}</td>
                            <td class="td_title">客户来源</td>
                            <td>${saleChance.customer.source}</td>
                            <td class="td_title">级别</td>
                            <td class="star">${saleChance.customer.level}</td>
                        </tr>
                        <c:if test="${not empty saleChance.customer.address}">
                            <tr>
                                <td class="td_title">地址</td>
                                <td colspan="5">${saleChance.customer.address}</td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty saleChance.customer.mark}">
                            <tr>
                                <td class="td_title">备注</td>
                                <td colspan="5">${saleChance.customer.mark}</td>
                            </tr>
                        </c:if>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <h4>跟进记录
                        <small><button id="showRecordModalBtn" class="btn btn-success btn-xs"><i class="fa fa-plus"></i></button></small>
                    </h4>
                    <ul class="timeline">
                        <c:if test="${empty recordList}">
                            <li>
                                <!-- timeline icon -->
                                <i class="fa fa-circle-o bg-red"></i>
                                <div class="timeline-item">
                                    <div class="timeline-body">
                                        暂无跟进记录
                                    </div>
                                </div>
                            </li>
                        </c:if>
                        <c:forEach items="${recordList}" var="record">
                            <c:choose>
                                <c:when test="${record.content == '将当前进度修改为：[ 成交 ]'}">
                                    <li>
                                        <!-- timeline icon -->
                                        <i class="fa fa-check bg-green"></i>
                                        <div class="timeline-item">
                                            <span class="time"><i class="fa fa-clock-o"></i> <fmt:formatDate value="${record.createTime}"/></span>
                                            <div class="timeline-body">
                                                    ${record.content}
                                            </div>
                                        </div>
                                    </li>
                                </c:when>
                                <c:when test="${record.content == '将当前进度修改为：[ 暂时搁置 ]'}">
                                    <li>
                                        <!-- timeline icon -->
                                        <i class="fa fa-close bg-red"></i>
                                        <div class="timeline-item">
                                            <span class="time"><i class="fa fa-clock-o"></i> <fmt:formatDate value="${record.createTime}"/></span>
                                            <div class="timeline-body">
                                                    ${record.content}
                                            </div>
                                        </div>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <!-- timeline icon -->
                                        <i class="fa fa-info bg-blue"></i>
                                        <div class="timeline-item">
                                            <span class="time"><i class="fa fa-clock-o"></i> <fmt:formatDate value="${record.createTime}"/></span>
                                            <div class="timeline-body">
                                                    ${record.content}
                                            </div>
                                        </div>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
                <div class="col-md-4">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">待办事项</h3>
                            <div class="box-tools pull-right">
                                <a href="javascript:;" class="btn btn-box-tool" id="addTaskBtn">
                                    <i class="fa fa-plus"></i> 添加待办事项
                                </a>
                            </div>
                        </div>
                        <div class="box-body">
                            <c:if test="${empty taskList}">
                                <li class="list-group-item">暂无待办事项</li>
                            </c:if>
                            <ul class="todo-list">
                                <c:forEach items="${taskList}" var="task">
                                    <li class="${task.done ? 'done' : ''}">
                                        <input type="checkbox">
                                        <span class="text">${task.taskName}</span>
                                        <small class="label label-danger"><i class="fa fa-clock-o"></i> ${task.completeTime}</small>
                                        <div class="tools">
                                            <i class="fa fa-edit task-edit" rel="${task.id}"></i>
                                            <i class="fa fa-trash-o task-del" rel="${task.id}"></i>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">相关资料</h3>
                        </div>
                        <div class="box-body">

                        </div>
                    </div>
                </div>
            </div>

            <%--待办事项的模态框--%>
            <div class="modal fade" id="taskModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">新增待办事项</h4>
                        </div>
                        <div class="modal-body">
                            <form action="/task/my/${saleChance.id}/new" id="taskForm" method="post">
                                <input type="hidden" name="accountId" value="${sessionScope.currentUser.id}">
                                <input type="hidden" name="customerId" value="${saleChance.custId}">
                                <input type="hidden" name="saleId" value="${saleChance.id}">
                                <div class="form-group">
                                    <label>任务名称</label>
                                    <input type="text" class="form-control" name="taskName">
                                </div>
                                <div class="form-group">
                                    <label>完成日期</label>
                                    <input type="text" class="form-control" id="datepicker" name="completeTime">
                                </div>
                                <div class="form-group">
                                    <label>提醒时间</label>
                                    <input type="text" class="form-control" id="datepicker2" name="reminderTime">
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                            <button type="button" class="btn btn-primary" id="saveTaskBtn">保存</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->

            <%--添加跟进记录模态框--%>
            <div class="modal fade" id="recordModal">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加跟进记录</h4>
                  </div>
                  <div class="modal-body">
                      <form action="/sales/chance/record/new" id="recordForm" method="post">
                          <input type="hidden" name="saleId" value="${saleChance.id}">
                          <textarea id="recordContent"  class="form-control" name="content"></textarea>
                      </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="recordBtn">保存</button>
                  </div>
                </div><!-- /.modal-content -->
              </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            <%--更改进度模态框--%>
            <div class="modal fade" id="progressModal">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">更新当前进度</h4>
                  </div>
                  <div class="modal-body">
                      <form action="/sales/chance/progressUpdate/${saleChance.id}" method="post" id="progressForm">
                          <input type="hidden" name="id" value="${saleChance.id}">
                          <select name="progress" class="form-control">
                              <c:forEach items="${progressList}" var="progress">
                                  <option value="${progress}">${progress}</option>
                              </c:forEach>
                          </select>
                      </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" id="saveProgress" class="btn btn-primary">保存</button>
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
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script src="/static/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script>
    $(function () {
        //新增待办事项
        $("#addTaskBtn").click(function () {
            $("#taskModal").modal({
                show:true,
                backdrop:'static'
            });
        });
        //新增待办事项
        $("#saveTaskBtn").click(function () {
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
        //删除待办事项
        $(".task-del").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("确定要删除该待办事项",function () {
                window.location.href = "/task/my/del/" + id;
            })
        });
        //编辑待办事项
        $(".task-edit").click(function() {
            var id = $(this).attr("rel");
            window.location.href = "/task/my/edit/" + id;
            $("#taskModal").modal({
                show:true,
                backdrop:'static'
            });
        });

        var saleId = ${saleChance.id};
        //删除
        $("#delBtn").click(function () {
            layer.confirm("确定要删除改机会吗？",function () {
                window.location.href = "/sales/chance/delChance/" + saleId;
            })
        });
        //添加跟进记录
        $("#showRecordModalBtn").click(function () {
            $("#recordModal").modal({
                show:true,
                backdrop:'static'
            });
            $("#recordBtn").click(function () {
                if(!$("#recordContent").val()) {
                    layer.msg("请输入跟进内容")
                    return ;
                }
                $("#recordForm").submit();
            });
        });

        //显示进度模态框，并更新进度
        $("#showChangeProgressModalBtn").click(function () {
            $("#progressModal").modal({
                show:true,
                backdrop:'static'
            });
            $("#saveProgress").click(function () {
                $("#progressForm").submit();
            })
        });
    });
</script>

</body>
</html>
