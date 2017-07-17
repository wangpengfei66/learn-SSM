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
              <h3 class="box-title">新增投资信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="proForm">
              <div class="box-body">
                
                <div class="form-group">
                  <label class="col-sm-2 control-label">项目名称：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="projectName" name="projectName" value="${pro.projectName}" placeholder="">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-2 control-label">项目编号：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="projectNo" name="projectNo" value="${pro.projectName}" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">投资金额：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="money" name="money" value="${pro.money}" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">回报率：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                      <input type="text" class="form-control" id="rate" name="rate" value="${pro.rate}"placeholder="">
                  </div>
                </div>
                   <div class="form-group">
                  <label class="col-sm-2 control-label">期限(月)：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="month" name="month" value="${pro.month}" placeholder="">
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-2 control-label">签订日期：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="signDate" name="signDate" value="${pro.signDate}" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">截至日期：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="endDate" name="endDate" value="${pro.endDate}" placeholder="">
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
                    <button type="button" id="proBtn" class="btn btn-primary">新增</button>
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
<script src="/static/plugins/moment/momentjs.js"></script>
<script src="/static/js/pro/proAdd.js"></script>

</body>

</html>
