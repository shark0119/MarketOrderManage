<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<script src="/SuperMarket/jsp/js/provider.js"></script>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>供应商管理页面 >> 供应商添加页面</span>
	</div>
	<div class="providerAdd">
		<form action="/SuperMarket/pro/${jumpPage }" method="post" id="proList"
		name="proDetail">
			<input type="hidden" name="operation" value="${operation }"/>
			<div>
				<label for="providerName">供应商名称：</label> <input type="text"
					name="providerName" id="providerName" value="${u_provider.name }"/> <span>*请输入供应商名称</span>
			</div>
			<div>
				<label for="people">联系人：</label> <input type="text" name="people"
					id="people" value="${u_provider.contact }"/> <span>*请输入联系人</span>
			</div>
			<div>
				<label for="phone">联系电话：</label> <input type="text" name="phone"
					id="phone" value="${u_provider.phone }"/> <span>*请输入联系电话</span>
			</div>
			<div>
				<label for="address">联系地址：</label> <input type="text" name="address"
					id="address" value="${u_provider.address }"/> <span></span>
			</div>
			<div>
				<label for="fax">传真：</label> <input type="text" name="fax" id="fax" value="${u_provider.fax }"/>
				<span></span>
			</div>
			<div>
				<label for="describe">描述：</label> <input type="text" name="describe"
					id="describe" value="${u_provider.desc }"/>
			</div>
			<div class="providerAddBtn">
				<input type="button" value="保存" onclick="check()" /> <input
					type="button" value="返回" onclick="history.back(-1)" />
			</div>
		</form>
	</div>
</div>
