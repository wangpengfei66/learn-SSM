<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-客户详情</title>
    <%@ include file="../base/base-css.jsp"%>
    <style>
        .td_title {
            font-weight: bold;
        }
        .star {
            font-size: 20px;
            color: #ff7400;
        }
    </style>
    <link rel="stylesheet" href="/static/plugins/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="../base/base-side.jsp">
        <jsp:param name="active" value="customerInfo"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">客户资料</h3>
                    <div class="box-tools">
                        <a href="/customer/my" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                        <a href="/customer/my/edit/${customer.id}" class="btn bg-purple btn-sm"><i class="fa fa-pencil"></i> 编辑</a>
                        <button class="btn bg-orange btn-sm" id="transferBtn"><i class="fa fa-exchange"></i> 转交他人</button>
                        <button class="btn bg-maroon btn-sm" id="sharePublicBtn"><i class="fa fa-recycle"></i> 放入公海</button>
                        <button id="delBtn" rel="${customer.id}" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i> 删除</button>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table">
                        <tr>
                            <td class="td_title">姓名</td>
                            <td>${customer.custName}</td>
                            <td class="td_title">职位</td>
                            <td>${customer.job}</td>
                            <td class="td_title">联系电话</td>
                            <td>${customer.tel}</td>
                        </tr>
                        <tr>
                            <td class="td_title">所属行业</td>
                            <td>${customer.trade}</td>
                            <td class="td_title">客户来源</td>
                            <td>${customer.source}</td>
                            <td class="td_title">级别</td>
                            <td class="star">${customer.level}</td>
                        </tr>
                        <c:if test="${not empty customer.address}">
                            <tr>
                                <td class="td_title">地址</td>
                                <td colspan="5">${customer.address}</td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty customer.mark}">
                            <tr>
                                <td class="td_title">备注</td>
                                <td colspan="5">${customer.mark}</td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <div class="box-footer">
                    <span style="color: #ccc" class="pull-right">
                        创建日期：<fmt:formatDate value="${customer.createTime}" pattern="yyyy-MM-dd HH:mm"/> &nbsp;&nbsp;&nbsp;&nbsp;
                        <c:if test="${not empty customer.updateTime}">
                            最后修改日期：<fmt:formatDate value="${customer.updateTime}" pattern="yyyy-MM-dd HH:mm"/>
                        </c:if>
                    </span>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">销售机会</h3>
                            <div class="box-tools pull-right">
                                <a href="javascript:;" class="btn btn-box-tool" id="addChanceBtn">
                                    <i class="fa fa-plus"></i> 添加机会
                                </a>
                            </div>
                        </div>
                        <div class="box-body">
                        <c:if test="${empty chanceList}">
                                <li class="list-group-item">暂无销售机会</li>
                            </c:if>
                            <ul class="list-group">
                                <c:forEach items="${chanceList}" var="chance">
                                    <li class="list-group-item"><a href="/sales/chance/${chance.id}" target="_blank">${chance.name}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <%--二维码展示--%>
                    <div class="box">
                        <div class="box-body" style="text-align: center">
                            <img src="/customer/my/qrcode/${customer.id}" alt="">
                        </div>
                    </div>
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
                                            <i class="fa fa-edit"></i>
                                            <i class="fa fa-trash-o"></i>
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


        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <%--添加机会的模态框--%>
    <div class="modal fade" id="chanceModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">添加销售机会</h4>
          </div>
          <div class="modal-body">
              <form action="/sales/chance/new/${customer.id}" method="post" id="chanceForm">
                  <input type="hidden" name="accountId" value="${sessionScope.currentUser.id}">
                  <input type="hidden" name="custId" value="${customer.id}">
                  <div class="form-group">
                      <label>机会名称</label>
                      <input type="text" class="form-control" name="name">
                  </div>
                  <div class="form-group">
                      <label>关联客户</label>
                      <input type="text" class="form-control" value="${customer.custName}" disabled>
                  </div>
                  <div class="form-group">
                      <label>机会价值</label>
                      <input type="text" class="form-control" name="worth">
                  </div>
                  <div class="form-group">
                      <label>当前进度</label>
                      <select name="progress" class="form-control">
                          <option value=""></option>
                          <c:forEach items="${progressList}" var="progress">
                              <option value="${progress}">${progress}</option>
                          </c:forEach>
                      </select>
                  </div>
                  <div class="form-group">
                      <label>详细内容</label>
                      <textarea name="content" class="form-control"></textarea>
                  </div>
              </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary" id="saveChanceBtn">保存</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


    <%--待办事项的模态框--%>
    <div class="modal fade" id="taskModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">新增待办事项</h4>
          </div>
          <div class="modal-body">
              <form action="/task/my/new/${customer.id}" id="taskForm" method="post">
                  <input type="hidden" name="accountId" value="${sessionScope.currentUser.id}">
                  <input type="hidden" name="customerId" value="${customer.id}">
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



    <%--转移客户模态框--%>
    <div class="modal fade" id="accountModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">请选择转入员工</h4>
          </div>
          <div class="modal-body">
             <select id="accountId" class="form-control">
                <option value=""></option>
                <c:forEach items="${accountList}" var="account">
                    <option value="${account.id}">${account.userName}(${account.mobile})</option>
                </c:forEach>
             </select>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <button type="button" class="btn btn-primary" id="transferBtnOk">转交</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

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
        var custId = ${customer.id};
        $("#delBtn").click(function () {
            var id = $(this).attr("rel");
            layer.confirm("删除客户会自动删除相关数据，确定吗?",function(){
                window.location.href = "/customer/my/del/"+id+"";
            });
        });
        $("#sharePublicBtn").click(function () {
            layer.confirm("确定将客户放入公海？",function () {
                window.location.href = "/customer/my/sharePublic/"+custId+"";
            })
        });
        $("#transferBtn").click(function () {
            $("#accountModal").modal({
                show:true,
                backdrop:'static'
            });
        });
        $("#transferBtnOk").click(function () {
           var accountId = $("#accountId").val();
           if(!accountId) {
               layer.msg("请选择转入账号");
               return ;
           }
           window.location.href = "/customer/my/"+custId+"/transfer/"+accountId+"";
        });

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
        //添加销售机会
        $("#addChanceBtn").click(function () {
            $("#chanceModal").modal({
                show:true,
                backdrop:'static'
            });
            $("#saveChanceBtn").click(function () {
                $("#chanceForm").submit()
            });
            $("#chanceForm").validate({
                errorClass:"text-danger", //设置提示字体的样式
                errorElement:"span",
                rules:{
                    //规则
                    name:{
                        required:true
                    },
                    custId:{
                        required:true
                    },
                    worth:{
                        required:true
                    },
                    progress:{
                        required:true
                    }
                },
                messages:{
                    //提示信息内容
                    name:{
                        required:"请输入机会名称"
                    },
                    custId:{
                        required:"请输入关联的客户"
                    },
                    worth:{
                        required:"请输入价值"
                    },
                    progress:{
                        required:"请输入进度"
                    }
                }
            });
        });
        
    })
</script>

</body>
</html>
