<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <jsp:param name="active" value="chanceAdd"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">我的销售机会</h3>

                    <div class="box-tools pull-right">
                        <a href="/sales/chance" type="button" class="btn btn-primary">
                            <i class="fa fa-align-left"></i> 返回列表
                        </a>
                    </div>
                </div>
                <div class="box-body">
                    <form action="/sales/chance/new" method="post" id="chanceForm">
                        <input type="hidden" name="accountId" value="${sessionScope.currentUser.id}">
                        <div class="form-group">
                            <label>机会名称</label>
                            <input type="text" class="form-control" name="name">
                        </div>
                        <div class="form-group">
                            <label>关联客户</label>
                            <select name="custId" id="" class="form-control">
                                <option value=""></option>
                                <c:forEach items="${customerList}" var="customer">
                                    <option value="${customer.id}">${customer.custName}</option>
                                </c:forEach>
                                <option value="${currentCustomer.id}" disabled>${currentCustomer.custName}</option>
                            </select>
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
                <!-- /.box-body -->
                <div class="box-footer">
                    <button class="btn btn-primary" id="saveBtn">保存</button>
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
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
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
</script>

</body>
</html>
