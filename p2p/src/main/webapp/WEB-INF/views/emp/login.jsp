<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>员工登录</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
 <%@include file="../include/css.jsp" %>

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <style type="text/css">
		body{
			background-image:url(/static/img/body-bg.png);
			padding-top: 120px;
		}
	
	</style>
</head>
<body class="hold-transition skin-blue sidebar-mini  col-sm-offset-4 col-sm-4">

  
    <!-- Main content -->
    <section class="content">
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         <h3 class="text-center"><span class="logo-lg">中宏昌盛投资控股集团</span></h3>
        </div>
        <div class="box-body">
            <!-- /.box-header -->
            
             <c:if test="${not empty param.callback}">
            	<div class="alert alert-danger">请先登录后访问</div>
             </c:if>
            
             <c:if test="${not empty message}">
            	<div class="alert alert-danger">${message}</div>
             </c:if>
            
            <!-- form start -->
            <form class="form-horizontal" id="subForm">
              <div class="box-body">
                <div class="form-group">
                  <label class="control-label col-sm-3">用户名：</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="userName" name="userName" value="${userName}" placeholder="帐号">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputPassword3" class="control-label col-sm-3">密码：</label>
				
				<input type="hidden" value="${param.callback}" name="callback"/>	
					
                  <div class="col-sm-8">
                    <input type="password" class="form-control" id="password" name="password" placeholder="密码">
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-3 col-sm-6">
                    <div class="checkbox">
                      <label>
                        <input type="checkbox" id="rememberMe" name="rememberMe"> 记住此帐号
                      </label>
                    </div>
                  </div>
                </div>
              </div>
              <!-- /.box-body -->
              <div class="box-footer col-sm-offset-3 col-sm-6">
                <button type="button" class="btn btn-primary" id="login">登录</button>
                <button type="reset" style="margin-left: 20px" class="btn btn-default">重置</button>
              </div>
              <!-- /.box-footer -->
            </form>
        </div>
        </div>
    </section>
  


<%@ include file="../include/js.jsp" %>
<!-- AdminLTE for demo purposes -->
<script src="/static/dist/js/demo.js"></script>
<script src="/static/js/cryptojs/rollups/md5.js"></script>
<script src="/static/js/emp/login.js"></script>


</body>
</html>

