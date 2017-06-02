/**
 * proList.jsp
 */
var proId;
var count = 0;
$(".alterOrdA").click(function() {
	var id = $(this).parent().parent().children().first().html();
	isAvail (function(){
		location.href = "/SuperMarket/order/UpdateOrder?id=" + id;
	});
});
$(".checkOrdA").click(function() {
	var id = $(this).parent().parent().children().first().html();
	location.href = "/SuperMarket/order/CheckOrder?id=" + id;
});
$('.removeOrdA').click(function() {
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
			url : "/SuperMarket/order/RemoveOrder",
			data : "id=" + proId,
			success : function(data) {
				var dataobj = JSON.parse(data);
				if (dataobj.success) {
					$("#orderList").submit();
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
	if (!$("#billNum").val().match (/^\d+$/)){
		alert ("产品数量必须为整数，请重新输入");
		$("#billNum").val("");
		$("#billNum").focus();
		return false;
	}else if ($("#billNum").val()<1){
		alert ("产品数量必须大于0，请重新输入");
		$("#billNum").val("");
		$("#billNum").focus();
		return false;
	}else if ($("#productId option:selected").val() == "-1"){
		alert ("该公司暂不提供产品");
		return false;
	}
	
	var form = document.orderDetail;
	$("#totalPrice").removeAttr("disabled");
	with (form) {
		submit();
		return true;
	}
}
function isEmpty(str) {
	if (str == null || str == "")
		return true;
	return false
}

/* ajax 动态刷新 */
var d;
$.ajax({
	type : "POST",
	url : "/SuperMarket/order/InitProductByAjaxId",
	data : "id=" + $("#proviId option:selected").val(),
	success : function(data) {
		d = eval(data);
	}
});
function onSelectChange() {
	var options = $("#proviId option:selected");
	$("#productId").attr("disabled", "disabled");
	$("#productId").empty();
	if (options.val() != "") {
		$("#productId").removeAttr("disabled");
		$.ajax({
			type : "POST",
			url : "/SuperMarket/order/InitProductByAjaxId",
			data : "id=" + options.val(),
			success : function(data) {
				d = eval(data);
				if (d.length == 0) {
					$("#productId").append(
							"<option value='-1'>该公司暂不提供产品</option>");
					return;
				}
				for (var i = 0; i < d.length; i++) {
					// alert (d[i]);
					d[i].__proto__.toString = function() {
						return this.id + this.name + this.unit;
					}
					console.log("ajax receive:");
					console.log(d[i]);
					$("#productId").append(
							"<option value='" + d[i].id + "'>" + d[i].name
									+ "</option>");

				}
				$("#productUnit").html(d[0].unit);
				f1(d[0].id, options.val());
			}
		});
	}
}

function onProChange() {
	var options = $("#productId option:selected");
	var productId = options.val();

	$("#billNum").val("");
	$("#totalPrice").val("");

	for (var i = 0; i < d.length; i++) {
		console.log("now is:");
		console.log(d[i]);
		if (d[i].id == productId) {
			$("#productUnit").html(d[i].unit);
			break;
		}
	}
	var providerId = $("#proviId option:selected").val();
	f1(productId, providerId);
}
function f1(productId, providerId) {
	$.ajax({
		type : "POST",
		url : "/SuperMarket/order/GetPrice",
		data : {
			'productId' : productId,
			'providerId' : providerId
		},
		success : function(data) {
			$('#singlePrice').html(data);
		}
	});
}
$("#billNum").blur(function() {
	var sp = $('#singlePrice').html();
	var count = this.value;
	if (count.match(/^\d+$/)) {
		var total = (sp - 0) * (count - 0);
		$("#totalPrice").val(total);
	} else {
		$("#billNum").next().html("**必须为整数");
	}
});
function search() {
	document.forms[0].submit();
}
