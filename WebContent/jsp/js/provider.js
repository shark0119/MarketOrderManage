/**
 * proList.jsp
 */
var proId;
var count = 0;
$(".alterProA").click(function() {
	var id = $(this).parent().parent().children().first().html();
	isAvail(function() {
		location.href = "/SuperMarket/pro/UpdatePro?id=" + id;
	});
});
$(".checkProA").click(function() {
	var id = $(this).parent().parent().children().first().html();
	location.href = "/SuperMarket/pro/CheckPro?id=" + id;
});
$('.removeProA').click(function() {
	proId = $(this).parent().parent().children().first().html();
	isAvail(function() {
		$('.zhezhao').css('display', 'block');
		$('#removeUse').fadeIn();
	});
});
$(function() {
	$('#no').click(function() {
		$('.zhezhao').css('display', 'none');
		$('#removeUse').hide();
	});
});
$(function() {
	$('#yes').click(function() {
		count++;
		if (count % 2 === 0)
			return;
		$('.zhezhao').css('display', 'none');
		$('#removeUse').hide();
		$.ajax({
			type : "POST",
			url : "/SuperMarket/pro/RemovePro",
			data : "id=" + proId,
			success : function(data) {
				var dataobj = JSON.parse(data);
				if (dataobj.success) {
					$("#proList").submit();
				} else {
					alert(dataobj.msg);
				}
			}
		});
	});
});

/**
 * proDetail.jsp
 */
function check() {
	var form = document.proDetail;
	with (form) {
		if ($("#avail").val("true") == "false"){
			alter ("请确认信息后再提交");
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

function search() {
	document.forms[0].submit();
}

$("#providerName").blur (function(){
	$.ajax({
		type : "POST",
		url : "/SuperMarket/pro/NameAvail",
		data : "name=" + $("#providerName").val(),
		success : function(data) {
			var dataobj = JSON.parse(data);
			if (dataobj.success) {//可注册
				$("#providerName").next().html("太棒了，您又为公司找到了新的货源");
				$("#avail").val("true");
			} else {//公司名已被占用
				$("#providerName").next().html("该公司已添加入数据库中！");
				$("#avail").val("false");
			}
		}
	});
});
(function (){
	takeVerify ("#phone", /^\d{11}$/, "手机号可用", "联系方式必须为11位整数");
	takeVerify("#fax", /^\d{7}$/, "", "传真必须为7位整数")
})();

function takeVerify (strId, reg, smsg, emsg){
	$(strId).blur (function(){
		if ($(strId).val().match(reg)){
			$(strId).next().val(smsg);
			$("#avail").val("true");
		}else{
			$(strId).next().val(emsg);
			$("#avail").val("true");
		}
	});
}

