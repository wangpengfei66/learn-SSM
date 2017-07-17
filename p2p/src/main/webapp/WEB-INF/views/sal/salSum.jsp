<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>员工薪酬信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="../include/css.jsp" %>

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
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
          <h3 class="box-title">搜索</h3>
        </div>
        <div class="box-body">
          <form action="/sal/sum" class="form-inline">
              <select name="key" id="key" class="form-control">
                <option value="">--请选择查询条件--</option>
                <option value="realname">员工姓名</option>
                <option value="tel">手机号码</option>
              </select>
            <input type="text" class="form-control" name="value" id="value" placeholder="关键字"/>
            <button class="btn btn-primary">搜索</button>
          </form>
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         投资信息列表
        </div>
        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
            	<tr>
						<th>员工姓名</th>
						<th>电话</th>
						<th>佣金金额</th>
						<th>月份</th>
							
				</tr>
            </thead>
            <tbody>
            	<c:choose>
            		<c:when test="${empty page.items}">
            			<tr>
							<td>暂无数据</td>
						</tr>
            		</c:when>
            		<c:otherwise>
            			<c:forEach items="${page.items}" var="sal">
							<tr>
								<td>${sal.realName}</td>
								<td>${sal.tel}</td>
								<td>${sal.sal}</td>
								<td>${sal.date}</td>
							</tr>
            			</c:forEach>
            		</c:otherwise>
            	</c:choose>
            </tbody>
          </table>
          <ul id="pagination" class="pagination"></ul>
        </div>
        <!-- /.box-body -->
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
 	 $("#key").val("${key}");
 	 $("#value").val("${value}");
	 $('#pagination').twbsPagination({
		totalPages:"${page.lastPage}",
        visiblePages:3,
		href:"/emp/list?page={{number}}&key=${key}&value=" + encodeURIComponent("${value}"),//bug需修复
		first: "首页",
		prev: "上一页",
		next:"下一页",
		last:"末页"
	 });
</script>
</body>
</html>
