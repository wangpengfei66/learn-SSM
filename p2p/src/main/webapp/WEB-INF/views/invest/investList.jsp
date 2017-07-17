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
  <title>查询投资信息</title>
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
          <form action="" class="form-inline">
             <select name="key" id="key" class="form-control">
                <option value="">--请选择查询条件--</option>
                <option value="name">客户姓名</option>
                <option value="projectName">项目名称</option>
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
						  <th>客户名称</th>
							<th>项目名称</th>
							<th>投资金额</th>
							<th>回报率</th>
							<th>期限</th>
							<th>签订日期</th>
							<th>到期日期</th>
							<th>业务员</th>
							<th>状态</th>
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
            			<c:forEach items="${page.items}" var="invest">
							<tr>
								<td>${invest.name}</td>
								<td>${invest.projectName}</td>
								<td>${invest.investMoney}</td>
								<td>${invest.rate}</td>
								<td>${invest.month}</td>
								<td>${invest.signDate}</td>
								<td>${invest.endDate}</td>
								<td>${invest.realName}</td>
								<c:choose>
									<c:when test="${invest.state == 1}"><td>正常</td></c:when>
									<c:when test="${invest.state == 2}"><td>已解约</td></c:when>
									<c:otherwise>
										<td>已结束</td>
									</c:otherwise>
								</c:choose>
								<!-- 根据当前状态进行操作 -->
								<c:if test="${invest.state == 1}">
									<td>
										<a href="javaScript:;" rel="${invest.id}" class="del" id="del">删除</a>
										<a href="javaScript:;" rel="${invest.id}" class="unuse" id="unuse">解约</a>
										<a href="javaScript:;" rel="${invest.id}" class="renew" id="renew">续约</a>
									</td>
								</c:if>
								<c:if test="${invest.state == 2}">
									<td>--</td>
								</c:if>
								
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
  <div class="modal fade" id="renewModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">续约</h4>
        </div>
        <div class="modal-body">
          <form class="form-horizontal" id="addForm">
          
                <div class="form-group">
                  <label class="col-sm-3 control-label">客户名称：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="hidden" class="form-control" readonly id="cust" value="${pro.restMoney}" name="cust" placeholder="">
                    <input type="text" class="form-control" readonly id="name" value="${pro.restMoney}" name="name" placeholder="">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-3 control-label">项目名称：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                     <input type="hidden" class="form-control" readonly id="pro"  name="pro" placeholder="">
                     <input type="text" class="form-control" readonly id="projectName"  name="projectName" placeholder="">
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-sm-3 control-label">剩余金额：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" readonly id="restMoney" value="${pro.restMoney}" name="restMoney" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">投资金额：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" readonly id="investMoney" name="investMoney" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">回报率：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                      <input type="text" class="form-control" readonly id="rate" name="rate" placeholder="">
                  </div>
                </div>
                   <div class="form-group">
                  <label class="col-sm-3 control-label">期限：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="month" readonly name="month" placeholder="">
                  </div>
                </div>
                 <div class="form-group">
                  <label class="col-sm-3 control-label">签订日期：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" readonly id="signDate" name="signDate" placeholder="">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-3 control-label">截至日期：(<span><strong class="text-danger">*</strong></span>)</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" readonly id="endDate" name="endDate" placeholder="">
                  </div>
                </div>
              <div class="box-footer">
                 <div class="col-sm-offset-2 col-sm-8">
                    <button type="button" id="addBtn" class="btn btn-primary">续约</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                 </div>
              </div>
              <!-- /.box-footer -->
            </form> 
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
 </div><!-- /.modal -->
	
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
<script src="/static/plugins/moment/momentjs.js"></script>
<script>
  $(function(){
	 $("#key").val("${key}");
  	 $("#value").val("${value}");
	 $('#pagination').twbsPagination({
		totalPages:"${page.lastPage}",
        visiblePages:3,
		href:"/invest/list?page={{number}}&key=${key}&value=" + encodeURIComponent("${value}"),//bug需修复
		first: "首页",
		prev: "上一页",
		next:"下一页",
		last:"末页"
	 });
	 //点击后模态框显示
	 $(".renew").click(function(){
		 
		  $("#renewModal").modal({
	           show:true,
	           backdrop:'static'
	       });
		  
	  });
	
  });
</script>
<script src="/static/js/invest/investList.js"></script>
</body>
</html>
