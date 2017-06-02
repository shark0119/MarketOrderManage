<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/SuperMarket/jsp/js/jquery.js"></script>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>账单管理页面</span>
	</div>
	<form action="/SuperMarket/order/InitOrder" method="get" id="orderList"
		name="dividePage">
		<div class="search">
			<span>商品名称：</span> 
			<select name="s_productId">
				<option value="-1">--请选择--</option>
				<c:forEach items="${productList }" var="pro">
					<option value="${pro.id }" 
					<c:if test="${productId eq pro.id }">selected</c:if>>
					${pro.name }</option>
				</c:forEach>
			</select> <span>供应商：</span>
			<select name="s_providerId">
				<option value="-1">--请选择--</option>
				<c:forEach items="${providerList }" var="pro">
					<option value="${pro.id }"
					<c:if test="${providerId eq pro.id }">selected</c:if>
					>${pro.name }</option>
				</c:forEach>
			</select> <span>是否付款：</span> 
			<select name="s_status">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${requestScope.status eq 1 }">selected</c:if>>未付款</option>
				<option value="2" <c:if test="${requestScope.status eq 2 }">selected</c:if>>已结算</option>
			</select> <input type="button" onclick="search()" value="查询" /> 
			<a href="javascript:add('/SuperMarket/order/AddOrder')">添加订单</a>
		</div>
		<table class="providerTable" cellpadding="0" cellspacing="0">
			<tr class="firstTr">
				<th width="10%">账单编码</th>
				<th width="20%">商品名称</th>
				<th width="10%">供应商</th>
				<th width="10%">账单金额</th>
				<th width="10%">是否付款</th>
				<th width="10%">创建时间</th>
				<th width="30%">操作</th>
			</tr>
			<c:forEach items="${oList }" var="order">
				<tr>
					<td>${order.id }</td>
					<td>${order.productName }</td>
					<td>${order.providerName }</td>
					<td>${order.money }</td>
					<td>${order.ispay == 1?"未付款":"已结算" }</td>
					<td>${order.ctime }</td>
					<td><a href="#" class="checkOrdA"><img
							src="/SuperMarket/jsp/img/read.png" alt="查看" title="查看" /></a> <a
						href="#" class="alterOrdA"><img
							src="/SuperMarket/jsp/img/xiugai.png" alt="修改" title="修改" /></a> <a
						href="#" class="removeOrdA"><img
							src="/SuperMarket/jsp/img/schu.png" alt="删除" title="删除" /></a></td>
				</tr>
			</c:forEach>
		</table>
		<%@include file="../common/page.jsp"%>
	</form>
</div>


<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
	<div class="removerChid">
		<h2>提示</h2>
		<div class="removeMain">
			<p>你确定要删除该订单吗？</p>
			<a href="#" id="yes">确定</a> <a href="#" id="no">取消</a>
		</div>
	</div>
</div>


<script src="/SuperMarket/jsp/js/order.js"></script>
<script src="/SuperMarket/jsp/js/authority.js"></script>