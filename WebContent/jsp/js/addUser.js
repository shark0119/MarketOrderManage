function check() {
	var form = document.userDetail;
	with (form) {
		if (isEmpty(userName.value)) {
			alert("用户名不能为空");
			userName.focus();
			return false;
		}
		if (isEmpty(password.value)) {
			alert("密码不能为空");
			password.focus();
			return false;
		}else if (password.value.match(/[\w]{6,12}/i)) {
			if (password.value.match(/^[a-z]+$/i)) {
				alert("密码不能为纯字母，必须为6-12位的数字和字母组合");
				password.focus();
				return false;
			}else if (password.value.match(/^[\d]+$/)){
				alert("密码不能为纯数字，必须为6-12位的数字和字母组合");
				password.focus();
				return false;
			}
		} else {
			alert("密码必须为6-12位的数字和字母组合");
			password.focus();
			return false;
		}
		if (isEmpty(userRemi.value)){
			alert("请确认密码");
			userRemi.focus();
			return false;
		}else if (userRemi.value != password.value){
			alert("两次密码输入不一致，请确认后重新输入");
			userRemi.focus();
			return false;
		}
		if (isEmpty(date.value)){
			alert ("请输入出生日期");
			date.focus();
			return false;
		}else if (!date.value.match(/\d{4}-\d\d-\d\d/)){
			alert("出生日期格式不正确，请按照yyyy-MM-dd输入");
			date.focus();
			return false;
		}
		if (isEmpty(phone.value)){
			alert("电话不能为空");
			phone.focus();
			return false;
		} else if (!phone.value.match (/\d{11}/)){
			alert ("联系方式必须为11位纯数字");
			phone.focus();
			return false;
		}
		if (isEmpty(address.value)){
			alert ("住址不能为空");
			add.focus();
			return false;
		}
		submit();
		return true;		
	}
}
function isEmpty(str) {
	if (str == null || str == "")
		return true;
	return false
}