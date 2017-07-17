<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>新增投资信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<%@include file="../include/css.jsp" %>
	<link rel="stylesheet" href="/static/plugins/select2/select2.css" />

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

  <%@ include file="../include/header.jsp"%>
  <%@ include file="../include/silder.jsp"%>

  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
              
      <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">新增投资信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="addForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-2 control-label">客户名称：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <select id="cust" name="cust" class="form-control">
                      <option value="">请选择客户</option>
						<c:forEach items="${custList}" var="cust">
	                      <option value="${cust.id}">${cust.name}-${cust.tel}</option>
						</c:forEach>
                    </select>
                  </div>
                </div>
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-2 control-label">项目名称：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <select id="pro" name="pro" class="form-control">
                      <option value="">请选择项目：</option>
						<c:forEach items="${proList}" var="pro">
	                      <option value="${pro.id}">${pro.projectName}
		                      <%-- <c:if test="${proId} == ${pro.id}">cheaked</c:if> --%>
	                      </option>
						</c:forEach>
                    </select>
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-2 control-label">剩余金额：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" readonly id="restMoney" value="${pro.restMoney}" name="restMoney" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">投资金额：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="investMoney" name="investMoney" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">回报率：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                      <input type="text" class="form-control" value="${pro.rate}" readonly id="rate" name="rate" placeholder="">
                  </div>
                </div>
                   <div class="form-group">
                  <label class="col-sm-2 control-label">期限：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="month" readonly name="month" value="${pro.month}" placeholder="">
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-2 control-label">签订日期：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="signDate" name="signDate" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">截至日期：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="endDate" name="endDate" placeholder="">
                  </div>
                </div>
              <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-8">
                    <button type="button" id="addBtn" class="btn btn-primary">新增</button>
                    <button type="reset" class="btn btn-default">重置</button>
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
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/moment/momentjs.js"></script>
<script src="/static/plugins/select2/select2.min.js"></script>
<script src="/static/js/invest/add.js"></script>
<script>
  $(function(){
	$("#pro").val("${proId}");
    $("#signDate").datepicker({
           format: "yyyy-mm-dd",
           language: "zh-CN",
           autoclose: true,
           startDate:moment().format("YYYY-MM-DD")
       }).on('changeDate',function(e) {
    		//1.获得周期控件的值
    		var month = $("#month").val();
    		//2.根据开始日期和周期获取截止日期的值
    		var date = moment(e.format("yyyy-mm-dd")).add(month,'month').format("YYYY-MM-DD");
    		//3.将截止日期添加到框内
    		$("#endDate").val(date)
       });
    var flag = true;
   
});
</script>
</body>
</html>
