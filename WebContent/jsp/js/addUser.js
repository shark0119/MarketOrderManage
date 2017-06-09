var fNameAvail = false;
function check() {
	if ($("#oldUserName").val() == $("#userName").val()){
		fNameAvail =true;
	}
	var form = document.userDetail;
	with (form) {
		if (isEmpty(userName.value)) {
			alert("用户名不能为空");
			userName.focus();
			return false;
		}
		if (!fNameAvail){
			alert("用户名不合法，请确认后再提交");
			userName.focus();
			return false;
		}			
		if ($("#operation").val() != "update"){//增加用户
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
		$(":disabled").removeAttr ("disabled");
		submit();
		return true;		
	}
}
function isEmpty(str) {
	if (str == null || str == "")
		return true;
	return false
}
$("#userName").blur (function(){
	if ($("#oldUserName").val() == $("#userName").val()){
		fNameAvail =true;
		return;
	}
	if (isEmpty($("#userName").val()))
		return;
	$.ajax({
		type : "POST",
		url : "/SuperMarket/ajax/UserNameAvail",
		data : "name=" + $("#userName").val(),
		success : function(data) {
			var dataobj = JSON.parse(data);
			if (dataobj.success) {// 可注册
				$("#userName").next().html("恭喜您，该用户名尚未注册");
				fNameAvail = true;
			} else {// 公司名已被占用
				$("#userName").next().html("该用户名已被占用");
				fNameAvail -= false;
			}
		}
	});
});

