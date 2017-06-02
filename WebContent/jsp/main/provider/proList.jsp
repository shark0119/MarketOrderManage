<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/SuperMarket/jsp/js/provider.js"></script>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>供应商管理页面</span>
	</div>
	<form action="/SuperMarket/pro/InitPro" method="get" id="proList"
		name="dividePage">
		<div class="search">
			<span>供应商名称：</span> <input type="text" value="${condition.name }" name="s_proName" placeholder="请输入供应商的名称" /> <input
				type="submit" value="查询" /> <a href="/SuperMarket/pro/AddPro">添加供应商</a>
		</div>
		<!--供应商操作表格-->
		<table class="providerTable" cellpadding="0" cellspacing="0">
			<tr class="firstTr">
				<th width="10%">供应商编码</th>
				<th width="20%">供应商名称</th>
				<th width="10%">联系人</th>
				<th width="10%">联系电话</th>
				<th width="10%">传真</th>
				<th width="10%">创建时间</th>
				<th width="30%">操作</th>
			</tr>
			<c:forEach items="${pList }" var="provider">
				<tr>
					<td>${provider.id }</td>
					<td>${provider.name }</td>
					<td>${provider.contact }</td>
					<td>${provider.phone }</td>
					<td>${provider.fax }</td>
					<td>${provider.cdate }</td>
					<td><a href="#" class="checkProA"><img
							src="/SuperMarket/jsp/img/read.png" alt="查看" title="查看" /></a>  
							<a href="#" class="alterProA"><img
							src="/SuperMarket/jsp/img/xiugai.png" alt="修改" title="修改" /></a> 
							<a href="#" class="removeProA"><img
							src="/SuperMarket/jsp/img/schu.png" alt="删除" title="删除" /></a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@include file="../common/page.jsp"%>
	</form>
</div>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
	<div class="removerChid">
		<h2>提示</h2>
		<div class="removeMain">
			<p>你确定要删除该供应商吗？</p>
			<a href="#" id="yes">确定</a> <a href="#" id="no">取消</a>
		</div>
	</div>
</div>
