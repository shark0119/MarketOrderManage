<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="/SuperMarket/jsp/js/jquery.js"></script>

<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>账单管理页面 >> 订单添加页面</span>
	</div>
	<div class="providerAdd">
		<form action="/SuperMarket/order/${jumpPage }" method="post"
			id="ordList" name="orderDetail">
			<input type="hidden" name="operation" value="${operation }" />
			<input type="hidden" name="id" value="${u_order.id }">
			
			<div>
				<label>供应商：</label> 
				<select name="supplier" id="proviId" onchange="onSelectChange()" <c:if test="${u_order.ispay eq 2 }"> disabled="disabled" </c:if>>
					<option value="">--请选择相应的提供商--</option>
					<c:forEach items="${providerList }" var="p">
						<option value="${p.id }" 
							<c:if test="${u_providerId eq p.id}">selected</c:if>>
						${p.name }</option>
					</c:forEach>
				</select> 
				<span>*请选择供应商</span>
			</div>
			<div>
				<label for="orderName">商品名称：</label>
				<select name="productId" <c:if test="${empty u_productId }">disabled="disabled"	</c:if> 
					<c:if test="${u_order.ispay eq 2 }"> disabled="disabled" </c:if> id="productId" onchange="onProChange()">
					<c:forEach items="${u_productList }" var="p" >
						<option value="${p.id }" 
							<c:if test="${u_product.id eq p.id}">selected</c:if>>
						${p.name }</option>
					</c:forEach>
				</select>
				<span>*请输入商品名称</span>
			</div>
			<div>
				<label for="billCom">商品单位：</label> 
				<span id="productUnit">${u_product.unit }</span>
				<label for="SinglePrice">商品单价：</label> 
				<span id="singlePrice">${u_product.price }</span>
			</div>
			<div>
				<label for="billNum">商品数量：</label> 
				<input type="text" name="billNum" id="billNum" value="${u_order.count }" required 
					<c:if test="${u_order.ispay eq 2 }"> disabled="disabled" </c:if>/> 
				<span>*请输入大于0的正整数</span>
			</div>
			<div>
				<label for="money">总金额：</label>
				<input type="text" value="${u_order.money }" disabled="disabled" name="totalPrice" id="totalPrice">				
			</div>
			<div>
				<label>是否付款：</label> 
				<select name="status" <c:if test="${u_order.ispay eq 2 }"> disabled="disabled" </c:if>>
					<option value="1" <c:if test="${u_order.ispay eq 1 }">selected</c:if>>未付款</option>
					<option value="2" <c:if test="${u_order.ispay eq 2 }">selected</c:if>>已结算</option>
				</select>
			</div>
			<div>
				<label>订单描述：</label> 
				<input type="text" name="desc" value="${u_order.desc }" <c:if test="${u_order.ispay eq 2 }"> disabled="disabled" </c:if>>
			</div>
			<div class="providerAddBtn">
				<input type="button" value="保存" onclick="check()" /> <input
					type="button" value="返回" onclick="history.back(-1)" />
			</div>
		</form>
	</div>
</div>
<script src="/SuperMarket/jsp/js/order.js"></script>
