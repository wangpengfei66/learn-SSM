<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>修改分公司信息</title>
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
              <h3 class="box-title">添加分公司信息</h3>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" id="editComForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="col-sm-2 control-label">公司名称：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="name" value="${com.name}" name="name" placeholder="">
                  </div>
                </div>
                <input type="hidden" name="id" value="${com.id}"/><!-- 这个地方你没给值，怎么能获取到呢 -->
                 <div class="form-group">
                  <label class="col-sm-2 control-label">负责人：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="head" value="${com.head}" name="head" placeholder="">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-2 control-label">电话：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="tel" value="${com.tel}" name="tel" placeholder="">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-2 control-label">所属地域：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                       <select name="city" id="city" id="" class="form-control">
                          <option value="${city}">请选择所属区域</option>
	                      <c:forEach items="${comCityList}" var="city">
	                      	 <option value="${city}">${city}</option>
	                      </c:forEach>
                        </select>
                  </div>
                </div>
                 
                 <div class="form-group">
                  <label class="col-sm-2 control-label">详细地址：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="address" value="${com.address}" name="address" placeholder="">
                  </div>
                </div>
                 
                 <div class="form-group">
                  <label class="col-sm-2 control-label">备注：</label>

                  <div class="col-sm-8">
                    <textarea name="remark" id="remark" class="form-control" rows="6" value="${com.remark}"></textarea>
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
                    <button type="button" id="editComBtn" class="btn btn-primary">修改</button>
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
<script src="/static/js/com/edit.js"></script>
</body>
</html>
