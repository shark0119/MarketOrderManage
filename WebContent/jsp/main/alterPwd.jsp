<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="right">
	<div class="location">
		<strong>你现在所在的位置是:</strong> <span>密码修改页面</span>
	</div>
	<div class="providerAdd">
		<form action="#" method="post" name="alterPwd">
			<input type="hidden" value="alterPwd" name="alterPwd"/>
			<input type="hidden" id="uname" value="${sessionScope.username }" name="uname"/>
			<div class="">
				<label for="oldPassword">旧密码：</label> <input type="password"
					name="oldPassword" id="oldPassword" required /> 
					<span>*请输入原密码</span>
			</div>
			<div>
				<label for="newPassword">新密码：</label> <input type="password"
					name="newPassword" id="newPassword" required /> <span>*请输入新密码</span>
			</div>
			<div>
				<label for="reNewPassword">确认新密码：</label> 
				<input type="password" name="reNewPassword" id="reNewPassword" required /> 
				<span>*请输入新确认密码，保证和新密码一致</span>
			</div>
			<div class="providerAddBtn">
				<input type="button" value="保存" onclick="alterPwdCheck()"/>
			</div>
		</form>
	</div>
</div>
<script src="/SuperMarket/jsp/js/jquery.js"></script>
<script type="text/javascript" src="/SuperMarket/jsp/js/js.js"></script>
