<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>用户管理页面 >> 用户信息查看页面</span>
	</div>
	<div class="providerView">
		<p>
			<strong>用户编号：</strong><span>${userDetail.id }</span>
		</p>
		<p>
			<strong>用户名称：</strong><span>${userDetail.name }</span>
		</p>
		<p>
			<strong>用户性别：</strong><span>${userDetail.sex }</span>
		</p>
		<p>
			<strong>出生日期：</strong><span><fmt:formatDate
					pattern="yyyy-MM-dd" value="${userDetail.birth}" /></span>
		</p>
		<p>
			<strong>用户电话：</strong><span>${userDetail.mobile }</span>
		</p>
		<p>
			<strong>用户地址：</strong><span>${userDetail.address }</span>
		</p>
		<p>
			<strong>用户类别：</strong><span>${userDetail.rname }</span>
		</p>

		<a href="/SuperMarket/action/InitUser">返回</a>
	</div>
</div>