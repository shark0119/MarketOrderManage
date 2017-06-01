/**
 * Created by yaling.he on 2015/11/17.
 */
console.log('js.js');
// 供应商管理页面上点击删除按钮弹出删除框(providerList.html)
$(function() {
	$('.removeProvider').click(function() {
		$('.zhezhao').css('display', 'block');
		$('#removeProv').fadeIn();
	});
});

$(function() {
	$('#no').click(function() {
		$('.zhezhao').css('display', 'none');
		$('#removeProv').fadeOut();
	});
});

// 订单管理页面上点击删除按钮弹出删除框(billList.html)
$(function() {
	$('.removeBill').click(function() {
		$('.zhezhao').css('display', 'block');
		$('#removeBi').fadeIn();
	});
});

$(function() {
	$('#no').click(function() {
		$('.zhezhao').css('display', 'none');
		$('#removeBi').fadeOut();
	});
});

//修改密码的检查
function alterPwdCheck() {
	var form = document.alterPwd;

	with (form) {
		if (isEmpty(oldPassword.value)) {
			alert("旧密码不能为空");
			oldPassword.focus();
			return;
		} else if (oldPassword.value.match(/[\w]{6,12}/i)) {
			if (oldPassword.value.match(/^[a-z]+$/i)) {
				alert("旧密码不能为纯字母，必须为6-12位的数字和字母组合");
				oldPassword.focus();
				return;
			} else if (oldPassword.value.match(/^[\d]+$/)) {
				alert("旧密码不能为纯数字，必须为6-12位的数字和字母组合");
				oldPassword.focus();
				return ;
			}
		} else {
			alert("旧密码必须为6-12位的数字和字母组合");
			oldPassword.focus();
			return ;
		}
		if (isEmpty(newPassword.value)) {
			alert("密码不能为空");
			newPassword.focus();
			return ;
		} else if (newPassword.value.match(/[\w]{6,12}/i)) {
			if (newPassword.value.match(/^[a-z]+$/i)) {
				alert("密码不能为纯字母，必须为6-12位的数字和字母组合");
				newPassword.focus();
				return ;
			} else if (newPassword.value.match(/^[\d]+$/)) {
				alert("密码不能为纯数字，必须为6-12位的数字和字母组合");
				newPassword.focus();
				return ;
			}
		} else {
			alert("密码必须为6-12位的数字和字母组合");
			newPassword.focus();
			return ;
		}
		if (isEmpty(reNewPassword.value)) {
			alert("请确认密码");
			reNewPassword.focus();
			return ;
		} else if (reNewPassword.value != newPassword.value) {
			alert("两次密码输入不一致，请确认后重新输入");
			reNewPassword.focus();
			return ;
		}
		
		var usname = uname.value;
		var pwd = oldPassword.value;
		var newPwd = newPassword.value;
		if (pwd == newPwd){
			alert('密码未做任何改动。');
			return;
		}
		// 验证通过后，通过ajax检查旧密码是否正确
		$.post("/SuperMarket/LoginVerify", 
				{
					username : usname,
					pwd : pwd
				}, 
				function(data) {
					var dataobj = JSON.parse(data);
					if (dataobj.success) {
						//如果验证成功，且旧密码正确，进行ajax提交来修改密码
						$.post("/SuperMarket/action/AlterPwd", 
								{
									newPassword: newPwd,
									alterPwd:'alterPwd'
								}, 
								function(data) {
									var dataobj = JSON.parse(data);
									if (dataobj.success) {
										location.href="/SuperMarket/jsp/main/mainPart.jsp";
									} else {
										alert("旧密码有误");
									}
								});
					} else {
						alert(dataobj.msg);
					}
		}).error(function() {
			alert("服务器错误，请稍后再试")
		});
	}
	return ;
}
function isEmpty(str) {
	if (str == null || str == "")
		return true;
	return false
}
