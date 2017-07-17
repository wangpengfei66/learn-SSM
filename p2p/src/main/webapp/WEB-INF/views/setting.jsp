<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>新增投资信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="include/css.jsp" %>

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

  <%@ include file="include/header.jsp" %>
  <%@ include file="include/silder.jsp" %>
  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
              
      <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">系统参数设置</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="setForm">
              <div class="box-body">
                
                <div class="form-group" >
                  <label class="col-sm-2 control-label">回报率：</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="interestRate" name="interestRate" placeholder="" value="${set.key1}">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">佣金率：</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="commissionRate" name="commissionRate" placeholder="" value="${set.value1}">
                  </div>
                </div>
              <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-8">
                    <button type="button" id="setBtn" class="btn btn-primary">修改</button>
                    <button type="submit" class="btn btn-default">取消</button>
                 </div>
              </div>
              <!-- /.box-footer -->
            </form>        
      </div>
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.3.8
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>
</div>
<!-- ./wrapper -->

<%@ include file="include/js.jsp" %>

</body>
<script>
  $(function(){
	  $("#setBtn").click(function(){
			$("#setForm").submit();
		});
		
		$("#setForm").validate({
			errorElement:'span',
			errorClass:'text-danger',
			rules:{
				interestRate:{
					required:true
				},
				commissionRate:{
					required:true,
				}
				
			},
			messages:{
				interestRate:{
					required:"请输入回报率"
				},
				commissionRate:{
					required:"请输入佣金率",
				}
			},
			submitHandler:function(){
				$.ajax({
					url:'/setting',
					type:'post',
					data:$("#setForm").serialize(),
					beforeSend:function(){
						$("#setBtn").text("修改中").attr("disabled","disabled");
					},
					success:function(data){
						if(data.state == "success"){
							layer.alert("修改成功",function() {
								window.history.go(0);
							});
						}else{
							layer.alert(data.message);
						}
					},
					error:function(){
						layer.alert("服务器异常");
					},
					complete:function(){
						$("#setBtn").text("修改").removeAttr("disabled");
					}
					
				});
			}
			
		});
    
  });


</script>
</html>
