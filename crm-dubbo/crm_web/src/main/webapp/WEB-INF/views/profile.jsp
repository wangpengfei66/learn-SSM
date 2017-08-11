<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>凯盛软件CRM-首页</title>
    <%@ include file="base/base-css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
    <jsp:include page="base/base-side.jsp">
        <jsp:param name="active" value="home"/>
    </jsp:include>
    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

          <div class="box">
              <div class="box-header">
                  <h3 class="box-title">个人设置</h3>
              </div>
              <div class="box-body">
                  <form action="/profile" method="post" id="settingForm">
                      <div class="form-group">
                          <label>旧密码</label>
                          <input type="password" name="oldPassword" id="oldPassword" class="form-control">
                      </div>
                      <div class="form-group">
                          <label>新密码</label>
                          <input type="password" name="password" id="password" class="form-control">
                      </div>
                      <div class="form-group">
                          <label>确认密码</label>
                          <input type="password" name="rePassword" id="rePassword" class="form-control">
                      </div>
                  </form>
              </div>
              <div class="btn btn-primary" id="saveBtn">保存</div>
          </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <%@ include file="base/base-footer.jsp"%>

</div>
<!-- ./wrapper -->

<%@include file="base/base-js.jsp"%>
<script src="/static/plugins/validate/jquery.validate.min.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script>
    $(function () {
        $("#saveBtn").click(function () {
            $("#settingForm").submit();
        });
        $("#settingForm").validate({
            errorClass:"text-danger", //设置提示字体的样式
            errorElement:"span",
            rules:{
                //规则
                oldPassword:{
                    required:true
                },
                password:{
                    required:true
                },
                rePassword:{
                    required:true,
                    equalTo:"#password"
                }
            },
            messages:{
                //提示信息内容
                oldPassword:{
                    required:"请输入旧密码"
                },
                password:{
                    required:"请输入新密码"
                },
                rePassword:{
                    required:"请确认密码",
                    equalTo:"两次输入密码不一致"
                }
            },
            submitHandler:function () {
                //异步提交
                $.post("/profile",$("#settingForm").serialize()).done(function(data) {
                    if(data.state == "success") {
                        layer.alert("密码修改成功，请重新登录",function () {
                            window.location.href = "/";
                        })
                    }else{
                        layer.msg(data.message);
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
