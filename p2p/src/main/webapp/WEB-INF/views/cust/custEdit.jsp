<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>编辑客户信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 
  <%@ include file="../include/css.jsp" %>
 
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

  <%@ include file="../include/header.jsp" %>
  <%@ include file="../include/silder.jsp" %>

  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
              
      <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">添加客户信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="addForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-2 control-label">姓名：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="name" name="name" value="${cust.name}" placeholder="">
                  </div>
                  	<input type="hidden" name="id" value="${cust.id}"/>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">电话：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="tel" name="tel" value="${cust.tel}" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">身份证号：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="idNo" name="idNo" value="${cust.idNo}" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">银行卡号：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="bankNo" name="bankNo" value="${cust.bankNo}" placeholder="">
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-2 control-label">开户行：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="bankName" name="bankName" value="${cust.bankName}" placeholder="">
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-2 control-label">生日：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="birthday" name="birthday" value="${cust.birthday}" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">地址：</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="address" name="address" value="${cust.address}" placeholder="">
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-2 control-label">备注：</label>

                  <div class="col-sm-8">
                    <textarea name="" id="remark" name="remark"  class="form-control" rows="6"></textarea>
                  </div>
                </div>

                <!-- <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-8">
                    <div class="checkbox">
                      <label>
                        <input type="checkbox"> Remember me
                      ：</label>
                    </div>
                  </div>
                </div> -->
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-8">
                    <button type="button" id="addBtn" class="btn btn-primary">新增</button>
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

<%@ include file="../include/js.jsp" %>
<!-- date -->
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
</body>
<script>
  $(function(){
	 
    $("#birthday").datepicker({
            format: "yyyy-mm-dd",
            language: "zh-CN",
            autoclose: true,
            defaultViewDate: {
                year: 2000,
                month: 01,
                day: 01
            }
        });
    
    $(function(){
    	$("#addBtn").click(function(){
    		$("#addForm").submit();
    	});
    	  
    	$("#addForm").validate({
    		errorElement:'span',
    		errorClass:'text-danger',
    		rules:{
    			name:{
    				required:true
    			},
    			tel:{
    				required:true,
    			},
    			idNo:{
    				required:true,
    			},
    			bankNo:{
    				required:true
    			},
    			bankName : {
    				required : true,
    			},
    			birthday : {
    				required : true,
    			},
    			address : {
    				required : true,
    			}
    		},
    		messages:{
    			userName:{
    				required:"请输入用户名"
    			},
    			tel:{
    				required:"请输入电话号码",
    			},
    			idNo:{
    				required:"请输入身份证号码",
    			},
    			bankNo:{
    				required:"请输入银行卡账户"
    			},
    			bankName : {
    				required : "请输入银行姓名",
    			},
    			birthday : {
    				required : "请输入生日",
    			},
    			address : {
    				required : "请输入地址",
    			}
    		},
    		submitHandler:function(){
    			$.ajax({
    				url:"/cust/edit",
    				type:"post",
    				data:$("#addForm").serialize(),
    				beforeSend:function(){
    					$("#addBtn").text("修改中").attr("disabled","disabled");
    				},
    				success:function(data){
    					if(data.state == 'success'){
    						layer.alert("修改成功",function(){
    							window.location.href = "/cust/list";
    						});
    					}
    				},
    				error:function(data){
    					alert(data.message);
    				},
    				complete:function(){
    					var btn = $("#addBtn");
    					btn.removeAttr("disabled");
    					btn.text("修改");
    				}
    			});
    		}
    		
    	});  
    	  
      });

  });

</script>
</html>
