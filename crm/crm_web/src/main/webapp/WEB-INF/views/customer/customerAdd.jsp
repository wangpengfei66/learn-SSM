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
        <jsp:param name="active" value="customerAdd"/>
    </jsp:include>

            <!-- 右侧内容部分 -->
            <div class="content-wrapper">
                <!-- Main content -->
                <section class="content">

                    <!-- Default box -->
                    <div class="box">
                        <div class="box-header with-border">
                            <h3 class="box-title">新增客户</h3>
                            <div class="box-tools pull-right">
                                <a href="/customer/my" class="btn btn-primary btn-sm"><i class="fa fa-arrow-left"></i> 返回列表</a>
                            </div>
                        </div>
                        <div class="box-body">
                            <form action="/customer/new" method="post" id="saveForm">
                                <div class="form-group">
                                    <label>姓名<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" name="custName" id="custName">
                                </div>
                                <div class="form-group">
                                    <label>性别</label>
                                    <div>
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" value="先生" checked> 先生
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="sex" value="女士">女士
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label>职位</label>
                                    <input type="text" class="form-control" name="job">
                                </div>
                                <div class="form-group">
                                    <label>联系方式<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" name="tel" id="tel">
                                </div>
                                <div class="form-group">
                                    <label>地址</label>
                                    <input type="text" class="form-control" name="address">
                                </div>
                                <div class="form-group">
                                    <label>所属行业</label>
                                    <select class="form-control" name="trade">
                                        <option value=""></option>
                                        <c:forEach items="${tradeList}" var="trade">
                                            <option value="${trade}">${trade}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>客户来源</label>
                                    <select name="source" class="form-control">
                                        <option value=""></option>
                                        <c:forEach items="${sourceList}" var="source">
                                            <option value="${source}">${source}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>级别</label>
                                    <select class="form-control" name="level">
                                        <option value=""></option>
                                        <option value="★">★</option>
                                        <option value="★★">★★</option>
                                        <option value="★★★">★★★</option>
                                        <option value="★★★★">★★★★</option>
                                        <option value="★★★★★">★★★★★</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>备注</label>
                                    <input type="text" class="form-control" name="mark">
                                </div>
                            </form>
                        </div>
                        <div class="box-footer">
                            <button class="btn btn-primary" id="saveBtn">保存</button>
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
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#saveForm").submit();
        })
        $("#saveForm").validate({
            errorClass:"text-danger", //设置提示字体的样式
            errorElement:"span",
            rules:{
                //规则
                custName:{
                    required:true
                },
                tel:{
                    required:true
                }
            },
            messages:{
                //提示信息内容
                custName:{
                    required:"请输入姓名"
                },
                tel:{
                    required:"请输入联系电话"
                }
            },
            submitHandler:function () {
                //异步提交
                $.post("/customer/new",$("#saveForm").serialize()).done(function(data) {
                    if(data.state == "success") {
                        layer.msg("添加成功",function () {
                            window.location.href = "/customer/my"
                        });
                    }else{
                        layer.alert(data.message);
                    }
                }).error(function() {
                    layer.msg("服务器异常");
                });
            }
        });
    });
</script>

</body>
</html>
