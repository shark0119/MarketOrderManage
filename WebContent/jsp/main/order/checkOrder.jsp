<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>账单管理页面 >> 信息查看</span>
	</div>
	<div class="providerView">
		<p>
			<strong>订单编号：</strong><span>${c_order.id }</span>
		</p>		
		<p>
			<strong>供应商名称：</strong><span>${provider.name }</span>
		</p>
		<p>
			<strong>商品名称：</strong><span>${product.name }</span>
		</p>
		<p>
			<strong>商品单位：</strong><span>${product.unit }</span>
		</p>
		<p>
			<strong>商品数量：</strong><span>${c_order.count }</span>
		</p>
		<p>
			<strong>总金额：</strong><span>${c_order.money }</span>
		</p>
		<p>
			<strong>供应商：</strong><span>${c_order.desc }</span>
		</p>
		<p>
			<strong>是否付款：</strong><span>${c_order.ispay eq 1?"未付款":"已结算" }</span>
		</p>

		<a href="javascript:history.back(-1)">返回</a>
	</div>
</div>
