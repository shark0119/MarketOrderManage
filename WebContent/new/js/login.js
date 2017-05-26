/**
 * 注册页面的js代码
 */
$(document).ready (function (){
	document.forms[0].onsubmit = check;
});

function check (){
	$.post(
			"/SuperMarket/action/LoginVerify",
			{
				username:$("#user").val(),
				pwd: $("#mima").val()
			},
			function (data){
				/*if (data === "true"){
					document.forms[0].onsubmit = 
						(function(){return true;})();
					document.forms[0].submit();
				}else{
					alert ("用户名或密码错误");
				}*/
				var dataobj = JSON.parse (data);
				if (dataobj.success){
					document.forms[0].onsubmit = 
						(function(){return true;})();
					document.forms[0].submit();					
				}else{
					alert (dataobj.msg);
				}
			}
	);
	return false;
}