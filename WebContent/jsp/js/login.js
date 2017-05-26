/**
 * 注册页面的js代码
 * 从服务器接收json代码格式是 { "success": true, "msg": "xxx" }
 */
$(document).ready (function (){
	document.forms[0].onsubmit = check;
});

function check (){
	$.post(
			"/SuperMarket/LoginVerify",
			{
				username:$("#user").val(),
				pwd: $("#mima").val()
			},
			function (data){
				var dataobj = JSON.parse (data);
				if (dataobj.success){
					document.forms[0].onsubmit = 
						(function(){return true;})();
					document.forms[0].submit();					
				}else{
					alert (dataobj.msg);
				}
			}
	).error(function(){alert ("服务器错误，请稍后再试")});
	return false;
}