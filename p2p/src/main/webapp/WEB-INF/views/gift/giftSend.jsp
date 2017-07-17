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
  <title>礼品积分兑换</title>
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
              <h3 class="box-title">礼品积分兑换</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="addForm">
                <div class="form-group">
                  <label class="col-sm-2 control-label">客户姓名：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <select id="cust" name="cust" class="form-control">
                      <option value="">请选择客户</option>
						<c:forEach items="${custList}" var="cust">
	                      <option value="${cust.id}">${cust.name}-${cust.idNo}</option>
						</c:forEach>
                    </select>
                  </div>
                </div>
                
                
                <div class="form-group">
                  <label class="col-sm-2 control-label">拥有积分：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" readonly id="point" value="${cust.point}" name="point" placeholder="">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-2 control-label">礼品列表：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <select id="gift" name="gift" class="form-control">
                      <option value="">请选择礼品：</option>
						<c:forEach items="${giftList}" var="gift">
	                      <option value="${gift.id}">${gift.giftName}
		                      <%-- <c:if test="${proId} == ${pro.id}">cheaked</c:if> --%>
	                      </option>
						</c:forEach>
                    </select>
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-2 control-label">所需积分：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" readonly id="needPoint" name="needPoint" placeholder="">
                  </div>
                </div>
              <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-8">
                    <button type="button" id="addBtn" class="btn btn-primary">兑换</button>
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
<script src="/static/js/gift/giftList.js"></script>
<script>
  $(function(){
   
 });
</script>
</body>
</html>
