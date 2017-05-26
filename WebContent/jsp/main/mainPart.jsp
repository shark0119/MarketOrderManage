<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>超市账单管理系统</title>
	<link rel="stylesheet" href="/SuperMarket/jsp/css/public.css" />
	<link rel="stylesheet" href="/SuperMarket/jsp/css/style.css" />
	<script src="/SuperMarket/jsp/js/time.js"></script>
</head>
<body>
	<!--头部-->
	<header class="publicHeader">
		<h1>超市账单管理系统</h1>
		<div class="publicHeaderR">
			<p>
				<span>下午好！</span><span style="color: #fff21b"> ${sessionScope.username }</span> , 欢迎你！
			</p>
			<a href="login.jsp">退出</a>
		</div>
	</header>
	<!--时间-->
	<section class="publicTime">
		<span id="time">2015年1月1日 11:11 星期一</span> <a href="#">温馨提示：为了能正常浏览，请使用高版本浏览器！（IE10+）</a>
	</section>
	<!--主体内容-->

	<section class="publicMian">
		<div class="left">
			<h2 class="leftH2">
				<span class="span1"></span>功能列表 <span></span>
			</h2>
			<nav>
				<ul class="list">
					<li><a href="billList.html">账单管理</a></li>
					<li><a href="providerList.html">供应商管理</a></li>
					<li><a href="/SuperMarket/action/UserManage">用户管理</a></li>
					<li><a href="password.html">密码修改</a></li>
					<li><a href="login.jsp">退出系统</a></li>
				</ul>
			</nav>
		</div>
		<jsp:include page="${sessionScope.contentPageName }"></jsp:include>
	</section>
	<footer class="footer"> 版权归北大青鸟 </footer>
</body>
</html>