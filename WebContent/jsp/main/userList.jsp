<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>用户管理页面</span>
	</div>
	<form action="/SuperMarket/action/UserManage" method="get"
		name="dividePage">
		<div class="search">
			<span>用户名：</span> <input type="text" placeholder="请输入用户名" /> <input
				type="button" value="查询" /> <a href="userAdd.html">添加用户</a>
		</div>
		<!--用户-->
		<table class="providerTable" cellpadding="0" cellspacing="0">
			<tr class="firstTr">
				<th width="10%">用户编码</th>
				<th width="20%">用户名称</th>
				<th width="10%">状态</th>
				<th width="10%">创建时间</th>
				<th width="20%">用户类型</th>
				<th width="30%">操作</th>
			</tr>
			<c:forEach var="user" items="${requestScope.users }">
				<tr>
					<td>${user.id }</td>
					<td>${user.username }</td>
					<td>${user.status }</td>
					<td>${user.createTime }</td>
					<td>${user.role }</td>
					<td><a href="userView.html"><img
							src="/SuperMarket/jsp/img/read.png" alt="查看" title="查看" /></a> <a
						href="userUpdate.html"><img
							src="/SuperMarket/jsp/img/xiugai.png" alt="修改" title="修改" /></a> <a
						href="#" class="removeUser"><img
							src="/SuperMarket/jsp/img/schu.png" alt="删除" title="删除" /></a></td>
				</tr>
			</c:forEach>
		</table>
		<%@include file="common/page.jsp" %>
	</form>
</div>
<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
	<div class="removerChid">
		<h2>提示</h2>
		<div class="removeMain">
			<p>你确定要删除该用户吗？</p>
			<a href="#" id="yes">确定</a> <a href="#" id="no">取消</a>
		</div>
	</div>
</div>

<script src="/SuperMarket/jsp/js/jquery.js"></script>
<script src="/SuperMarket/jsp/js/js.js"></script>
<script src="/SuperMarket/jsp/js/time.js"></script>