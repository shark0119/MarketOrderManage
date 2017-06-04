<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>用户管理页面 >> 用户添加页面</span>
	</div>
	<div class="providerAdd">
		<form action="/SuperMarket/action/AddUser" method="post" name="userDetail">
		<input type="hidden" name="add" value="add"/>
		<input type="hidden" name="oldUserName" id="oldUserName" value="-1"/>
			<div>
				<label for="userName">用户名称：</label> 
				<input type="text" name="userName" id="userName" /> 
				<span>*请输入用户名称</span>
			</div>
			<div>
				<label for="userpassword">用户密码：</label> 
				<input type="password" name="password" id="password" /> 
				<span>*密码长度必须大于6位小于20位</span>
			</div>
			<div>
				<label for="userRemi">确认密码：</label> 
				<input type="password" name="userRemi" id="userRemi" /> 
				<span>*请输入确认密码</span>
			</div>
			<div>
				<label>用户性别：</label> 
				<select name="sex">
					<option value="男" selected>男</option>
					<option value="女">女</option>
				</select> 
				<span></span>
			</div>
			<div>
				<label for="data">出生日期：</label> 
				<input type="text" name="date" id="date" class="Wdate" 
				onClick="WdatePicker({
					dateFmt:'yyyy-MM-dd',
					minDate:'{%y-100}-%M-%d',
					maxDate:'{%y-10}-%M-%d'})"/> 
				<span>*</span>
			</div>
			<div>
				<label for="phone">用户电话：</label> 
				<input type="text" name="phone" id="phone" /> 
				<span>*</span>
			</div>
			<div>
				<label for="address">用户地址：</label> 
				<input type="text" name="address" id="address" />
			</div>
			<div>
				<label for="role">用户角色：</label> 
				<select name="role">
					<c:forEach var="role" varStatus="status" items="${requestScope.roleList }">
						<option value="${role.rid }">${role.rname }</option>				
					</c:forEach>
					</select>
			</div>
			<div class="providerAddBtn">
				<input type="button" value="保存" onclick="check()"/> 
				<input type="button" value="返回" onclick="history.back(-1)" />
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="/SuperMarket/jsp/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/SuperMarket/jsp/js/addUser.js"></script>

