<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Left side column. contains the sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
     
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
       <c:if test="${sessionScope.emp.deptName == '销售部' or sessionScope.emp.deptName == '经理办公室'}">
        <li class="treeview">
          <a href="#">
            <i class="fa fa-address-book "></i> <span>客户管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="/cust/add"><i class="fa fa-plus"></i> 新增客户信息</a></li>
            <li><a href="/cust/edit"><i class="fa fa-edit"></i> 修改客户信息</a></li>
            <li><a href="/cust/list"><i class="fa fa-search"></i> 查询客户信息</a></li>            
            <li><a href="/cust/bir"><i class="fa fa-heart-o"></i> 客户生日提醒</a></li>
          </ul>
        </li>
       </c:if>
        <c:if test="${sessionScope.emp.deptName == '销售部' or sessionScope.emp.deptName == '经理办公室' or sessionScope.emp.deptName == '财务'}">
	        <li class="treeview">
	          <a href="#">
	            <i class="fa fa-rmb"></i> <span>投资管理</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	           <li><a href="/invest/add"><i class="fa fa-plus"></i> 新增投资信息</a></li>
	            <li><a href="/invest/list"><i class="fa fa-edit"></i> 修改投资信息</a></li>
	            <li><a href="/invest/list"><i class="fa fa-search"></i> 查询投资信息</a></li>            
	            <li><a href="/inte/list"><i class="fa fa-rmb"></i> 已付息查询</a></li>
	          </ul>
	        </li>
        </c:if>
        <c:if test="${sessionScope.emp.deptName == '经理办公室'}">
	        <li class="treeview">
	          <a href="#">
	            <i class="fa fa-rmb"></i> <span>项目管理</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	           <li><a href="/pro/add"><i class="fa fa-plus"></i> 新增项目</a></li>
	            <li><a href="/pro/list"><i class="fa fa-edit"></i>项目列表</a></li>
	          </ul>
	        </li>
        </c:if>
        <c:if test="${sessionScope.emp.deptName == '运营部' or sessionScope.emp.deptName == '经理办公室'}">
	        <li class="treeview">
	          <a href="#">
	            <i class="fa fa-gift"></i> <span>积分管理</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	             <li><a href="/gift/add"><i class="fa fa-gift"></i> 新增礼品</a></li>
	            <li><a href="/gift/list"><i class="fa fa-gift"></i> 礼品库存</a></li>
	            <li><a href="/gift/send"><i class="fa fa-exchange"></i> 积分兑换</a></li>
			    <li><a href="/gift/send/query"><i class="fa fa-search"></i> 兑换记录查询</a></li>   
	          </ul>
	        </li>
        </c:if>
        <c:if test="${sessionScope.emp.deptName == '财务部' or sessionScope.emp.deptName == '经理办公室'}">
	        <li class="treeview">
	          <a href="#">
	            <i class="fa fa-rmb"></i> <span>薪酬管理</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	            <li><a href="/sal/list"><i class="fa fa-search"></i> 查询薪酬信息</a></li>            
				      <li><a href="/sal/sum"><i class="fa fa-bar-chart"></i> 结算个人薪酬</a></li>
	          </ul>
	        </li>
        </c:if>
        <c:if test="${sessionScope.emp.deptName == '经理办公室'}">
	         <li class="treeview">
	          <a href="#">
	            <i class="fa fa-cog"></i> <span>系统管理</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	            <li><a href="/setting"><i class="fa fa-cog"></i> 参数设置</a></li>
	            <li><a href="/com/list"><i class="fa fa-users"></i> 公司管理</a></li>
	          </ul>
	        </li>
        </c:if>
        <c:if test="${sessionScope.emp.deptName == '人事部' or sessionScope.emp.deptName == '经理办公室'}">
	         <li class="treeview">
	          <a href="#">
	            <i class="fa fa-cog"></i> <span>员工管理</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	            <li><a href="/emp/add"><i class="fa fa-user-o"></i>添加员工 </a></li>
	            <li><a href="/emp/list"><i class="fa fa-user-o"></i>员工管理 </a></li>
	          </ul>
	        </li>
        </c:if>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
