<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>供应商管理页面 >> 信息查看</span>
	</div>
	<div class="providerView">
		<p>
			<strong>供应商编码：</strong><span>${c_provider.id }</span>
		</p>
		<p>
			<strong>供应商名称：</strong><span>${c_provider.name }</span>
		</p>
		<p>
			<strong>联系人：</strong><span>${c_provider.contact }</span>
		</p>
		<p>
			<strong>联系电话：</strong><span>${c_provider.phone }</span>
		</p>
		<p>
			<strong>传真：</strong><span>${c_provider.fax }</span>
		</p>
		<p>
			<strong>描述：</strong><span>${c_provider.desc }</span>
		</p>
		<p>
			<strong>创建时间：</strong><span>${c_provider.cdate }</span>
		</p>
		<p>
			<strong>地址：</strong><span>${c_provider.address }</span>
		</p>
		<a href="/SuperMarket/pro/InitPro">返回</a>
	</div>
</div>
