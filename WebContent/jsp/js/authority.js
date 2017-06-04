/**
 * 
 */

function isAvail(doSomething){
	$.post(
			"/SuperMarket/AuthorityVerify",
			"",
			function (data){
				var dataobj = JSON.parse (data);
				if (dataobj.success){
					doSomething();			
				}else{
					alert (dataobj.msg);
				}
			}
	).error(function(){alert ("服务器错误，请稍后再试")});
}
function add (url){
	//isAvail (function(){
		location.href =url;
	//});
}