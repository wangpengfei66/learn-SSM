<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 格式化金额要用到以上的 -->
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>查询项目信息</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@include file="../include/css.jsp" %>
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
          <form action="/pro/list" class="form-inline">
            
             <select name="key" id="key" class="form-control">
                <option value="">--请选择查询条件--</option>
                <option value="project_name">项目名称</option>
                <option value="project_no">项目编号</option>
              </select>
            <input type="text" class="form-control" name="value" id="value" value="${value}" placeholder="关键字"/>
            <button class="btn btn-primary">搜索</button>
          </form>
        </div>

      </div>
      <!-- Default box -->
      <div class="box box-solid box-primary">
        <div class="box-header with-border">
         <h5 class="pull-left">查询项目信息</h5>
         <a href="javascript:;" class="btn btn-success pull-right" id="addPro">新增项目</a>
        </div>
        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
            <tr>
							<th>项目名称</th>
							<th>总金额</th>
							<th>剩余金额</th>
							<th>项目编号</th>
							<th>周期</th>
							<th>回报率</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
            </thead>
            <tbody>
            	<c:choose>
            		<c:when test="${empty page.items}">
            			<tr>
            				<td colspan="7">
            					<h4>暂无数据</h4>
            				</td>
            			</tr>
            		</c:when>
            		<c:otherwise>
            			<c:forEach items="${page.items}" var="pro">
           					<tr>
							<td>${pro.projectName}</td>
							
							<td><fmt:formatNumber value="${pro.projectMoney}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber> </td>
							<td><fmt:formatNumber value="${pro.restMoney}" pattern="#.##" minFractionDigits="2" > </fmt:formatNumber> </td>
							<td>${pro.projectNo}</td>
							<td>${pro.month}</td>
							<td>${pro.rate}</td>
							<td>${pro.signDate}</td>
							<td>${pro.endDate}</td>
							<td>${pro.state == 1 ? "正常" :"已完成"}</td>
							<td>
							<c:if test="${pro.state == 1 }">
								<a href="/invest/add?proId=${pro.id}">投资</a>
							</c:if>
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

<div class="modal fade" id="addModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">新增项目信息</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" id="proForm">
              <div class="box-body">
                
                <div class="form-group">
                  <label class="col-sm-3 control-label">项目名称：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="projectName" name="projectName" value="${pro.projectName}" placeholder="">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-3 control-label">项目编号：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="projectNo" name="projectNo" value="${pro.projectName}" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">投资金额：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="money" name="money" value="${pro.money}" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">回报率：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                      <input type="text" class="form-control" id="rate" name="rate" value="${pro.rate}"placeholder="">
                  </div>
                </div>
                   <div class="form-group">
                  <label class="col-sm-3 control-label">期限(月)：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="month" name="month" value="${pro.month}" placeholder="">
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-3 control-label">签订日期：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="signDate" name="signDate" value="${pro.signDate}" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">截至日期：(<span class="text-danger"><strong>*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="endDate" name="endDate" value="${pro.endDate}" placeholder="">
                  </div>
                </div>
      </div>
      <div class="modal-footer">
        <div class="col-sm-offset-2 col-sm-8">
            <button type="button" id="proBtn" class="btn btn-primary">新增</button>
            <button type="submit" class="btn btn-default">重置</button>
         </div>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</div>
<!-- ./wrapper -->
<%@ include file="../include/js.jsp" %>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/moment/momentjs.js"></script>
<script src="/static/js/pro/proAdd.js"></script>
<script>
$(function(){
	
	  $("#pagination").twbsPagination({
		 totalPages:"${page.totalNo}",
		 visiblePages:3,
		 href:"/pro/list?page={{number}}&key=${key}&value=" + encodeURIComponent("${value}"),
		 first: "首页",
		 prev: "上一页",
		 next:"下一页",
		 last:"末页"
	 });  
	  $("#addPro").click(function(){
			 
		  $("#addModal").modal({
	           show:true,
	           backdrop:'static'
	       });
		  
	  });
	
});
</script>

</body>
</html>
