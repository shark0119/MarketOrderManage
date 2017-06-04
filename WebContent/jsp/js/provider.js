/**
 * proList.jsp
 */
var proId;
var count = 0;
$(".alterProA").click(function() {
	var id = $(this).parent().parent().children().first().html();
	//isAvail(function() {
		location.href = "/SuperMarket/pro/UpdatePro?id=" + id;
	//});
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
//检查信息是否有效，并决定是否提交
var fNameAvail = false;
var fPhoneAvail = false;
var fFaxAvail = false;
function check() {
	if ($("#phone").val().match(/^\d{11}$/)){
		$("#phone").next().html("手机号可用");
		fPhoneAvail = true;
	}else{
		$("#phone").next().html("联系方式必须为11位整数");
		fPhoneAvail = false;
	}
	if ($("#fax").val().match(/^\d{7}$/)){
		$("#fax").next().html("");
		fFaxAvail = true;
	}else{
		$("#fax").next().html("传真必须为7位整数");
		fFaxAvail = false;
	}
	if ($("#providerName").val() == $("#h_providerName").val()){//更新操作则不进行ajax验证
		fNameAvail = true;
	}
	if (isEmpty($("#providerName").val())){
		alert ("公司名不能为空");
		return false;
	}
	if (!fNameAvail){
		alert ("该公司已经备案");
		return false;
	}else if (!fPhoneAvail){

		alert ("手机号码格式错误，请检查后再提交");
		return false;
	}else if (!fFaxAvail){
		alert ("传真格式错误，请确认后在提交");
		return false;
	}
	var form = document.proDetail;
	with (form) {
		submit();
		return true;
	}
}
//验空
function isEmpty(str) {
	if (str == null || str == "")
		return true;
	return false
}

function search() {
	if ( checkPage())
		document.forms[0].submit();
}
//检查数据库是否存在此供应商
$("#providerName").blur (function(){
	if (isEmpty($("#providerName").val())){ //供应商名不能为空
		fNameAvail = false;
		return;
	}
	if ($("#providerName").val() == $("#h_providerName").val()){//更新操作则不进行ajax验证
		fNameAvail = true;
		return;
	}
	$.ajax({
		type : "POST",
		url : "/SuperMarket/pro/NameAvail",
		data : "name=" + $("#providerName").val(),
		success : function(data) {
			var dataobj = JSON.parse(data);
			if (!dataobj.success) {//可注册
				$("#providerName").next().html("太棒了，您又为公司找到了新的货源");
				fNameAvail = true;
			} else {//公司名已被占用
				$("#providerName").next().html("该公司存在于数据库中！");
				fNameAvail -= false;
			}
		}
	});
});
//验证格式
/*(function (){
	takeVerify ("#phone", /^\d{11}$/, "手机号可用", "联系方式必须为11位整数");
	takeVerify("#fax", /^\d{7}$/, "", "传真必须为7位整数")
})();

function takeVerify (strId, reg, smsg, emsg){
	$(strId).blur (function(){
		if ($(strId).val().match(reg)){
			$(strId).next().html(smsg);
			$("#avail").val("true");
		}else{
			$(strId).next().html(emsg);
			$("#avail").val("false");
		}
	});
}*/
$("#phone").blur (function(){
	if ($("#phone").val().match(/^\d{11}$/)){
		$("#phone").next().html("手机号可用");
		fPhoneAvail = true;
	}else{
		$("#phone").next().html("联系方式必须为11位整数");
		fPhoneAvail = false;
	}
});

$("#fax").blur (function(){
	if ($("#fax").val().match(/^\d{7}$/)){
		$("#fax").next().html("");
		fFaxAvail = true;
	}else{
		$("#fax").next().html("传真必须为7位整数");
		fFaxAvail = false;
	}
});