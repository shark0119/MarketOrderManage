/**
 * 
 */
$(document).ready(function() {
	$("#GO").click(function() {
		var $hidden = $(":input:hidden");
		var page = $("#page").val();
		if (page == null || page === "") {
			alert("页数不能为空");
		} else if (page.match(/^\d+$/)) {
			var i = $("#totalPage").text();
			if (page > i) {
				alert("输入无效，必须小于最大页数");
				$("#page").focus();
			} else {
				jump(page, $hidden[1].value);
			}
		} else
			alert("页数必须为整数");
	});
});
function jump(p, s) {
	var form = document.dividePage;
	with (form) {
		pageIndex.value = p;
		pageSize.value = s;
		submit();
	}
}

// 用户管理页面上点击删除按钮弹出删除框(userList.html)
var userid;
var count = 0;
$(function() {
	$('.removeUser').click(function() {
		userid = $(this).parent().parent().children().first().html();
		$('.zhezhao').css('display', 'block');
		$('#removeUse').fadeIn();
	});
});

$(function() {
	$('#no').click(function() {
		$('.zhezhao').css('display', 'none');
		$('#removeUse').fadeOut();
	});
});
$(function() {
	$('#yes').click(function() {
		count++;
		if (count % 2 === 0)
			return;
		$('.zhezhao').css('display', 'none');
		$('#removeUse').fadeOut();
		$.ajax({
			type : "POST",
			url : "/SuperMarket/action/DeleteUser",
			data : "id=" + userid,
			success : function(data) {
				var dataobj = JSON.parse(data);
				if (dataobj.success) {
					$("#userList").submit();
				} else {
					alert(dataobj.msg);
				}
			}
		});
	});
});

$(".updateUserA").click(function() {
	var id = $(this).parent().parent().children().first().html();
	location.href = "/SuperMarket/action/UpdateUser?id=" + id;
});
$(".checkUserA").click(function() {
	var id = $(this).parent().parent().children().first().html();
	location.href = "/SuperMarket/action/CheckUser?id=" + id;
});
