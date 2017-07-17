<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>查询分公司信息</title>
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
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
          <h3 class="box-title">搜索</h3>
        </div>
        <div class="box-body">
          <form action="" class="form-inline">
             <select name="key" id="key" class="form-control">
                <option value="">--请选择查询条件--</option>
                <option value="name">公司名称</option>
                <option value="tel">电话</option>
                <option value="city">区域</option>
                <option value="head">负责人</option>
              </select>
            <input type="text" class="form-control" name="value" id="value" placeholder="关键字"/>
            <button class="btn btn-primary">搜索</button>
          </form>
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         查询公司信息
        </div>
        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
            <tr>
							<th>公司名称</th>
							<th>电话</th>
							<th>负责人</th>
							<th>区域</th>
							<th>备注</th>
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
            		<c:otherwise >
            			<c:forEach items="${page.items}" var="com">
							<tr>
								<td>${com.name}</td>
								<td>${com.head}</td>
								<td>${com.tel}</td>
								<td>${com.city}</td>
								<td>${com.address}</td>
								<td>${com.remark}</td>
								<td>
									<a href="/com/edit?id=${com.id}">编辑</a>
									<a href="javaScript:;" rel="${com.id}" id="del" class="del">删除</a>
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


  <div class="modal fade" id="newModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">Modal title</h4>
        </div>
        <div class="modal-body">
          <p>One fine body&hellip;</p>
          <p>One fine body&hellip;</p>
          <p>One fine body&hellip;</p>
          <p>One fine body&hellip;</p>
          <p>One fine body&hellip;</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</div>
<!-- ./wrapper -->

<%@ include file="../include/js.jsp" %>

<script>
  $(function(){
	$("#key").val("${key}");
	$("#value").val("${value}");
	$("#pagination").twbsPagination({
		totalPages:"${page.lastPage}",
		visiblePages:3,
		href:"/com/list?page={{number}}&key=${key}&value=" + encodeURIComponent("${value}"),
		first:'首页',
		prev: "上一页",
		next:"下一页",
		last:"末页"
	});
	
  });
</script>
<script src="/static/js/com/list.js"></script>
</body>
</html>
