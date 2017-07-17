<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.List" %>
<%@ page import="com.kaishengit.entity.Employee" %>


<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>查询员工信息</title>
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

  <!-- =============================================== -->

  <!-- Left side column. contains the sidebar -->
 <%@ include file="../include/silder.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Main content -->
    <section class="content">
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
          <h3 class="box-title">搜索</h3>
        </div>
        <div class="box-body">
          <form action="" class="form-inline">
            <select name="key" id="key" class="form-control">
              	<option value="">--请选择查询条件--</option>
                <option value="real_name">姓名</option>
                <option value="tel">电话</option>
                <option value="inNo">身份证号</option>
                <option value="company_id">公司</option>
                <option value="dept_name">部门</option>
              </select>
            <input type="text" class="form-control" name="value" id="value" placeholder="关键字"/>
            <button class="btn btn-primary">搜索</button>
          </form>
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         <h5 class="pull-left">查询员工信息</h5>
         <a href="/emp/add" class="btn btn-success pull-right">新增员工</a>
        </div>
        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
            <tr>
							<th>姓名</th>
							<th>电话号码</th>
							<th>所属公司</th>
							<th>所属部门</th>
							<th>最后登录系统时间</th>
							<th>最后登录IP地址</th>
							<th>员工状态</th>
							<th>操作</th>
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
						<c:forEach items="${page.items}" var="emp">
							<tr>
								<td>${emp.realName}</td>
								<td>${emp.tel}</td>
								<td>${emp.company.name}</td>
								<td>${emp.deptName}</td>
								<td>${fn:substringBefore(emp.lastLoginTime,'.0')}</td>
								<td>${emp.lastLoginIp}</td>
								<td>${emp.state}</td>
								<td>
									<a href="/emp/edit?id=${emp.id}">编辑</a>
									<a href="javaScript:;" rel="${emp.id}" class="del" id="del">删除</a>
								</td>
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
  
  });
</script>
<script src="/static/js/emp/list.js"></script>
</body>
</html>
