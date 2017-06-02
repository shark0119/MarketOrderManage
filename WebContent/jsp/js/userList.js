/**
 * 
 */

// 用户管理页面上点击删除按钮弹出删除框(userList.html)
var userid;
var count = 0;
$(function() {
	$('.removeUser').click(function() {
		userid = $(this).parent().parent().children().first().html();
		isAvail(function() {
			$('.zhezhao').css('display', 'block');
			$('#removeUse').fadeIn();
		});
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
	isAvail(function() {
		location.href = "/SuperMarket/action/UpdateUser?id=" + id;
	});
});
$(".checkUserA").click(function() {
	var id = $(this).parent().parent().children().first().html();
	location.href = "/SuperMarket/action/CheckUser?id=" + id;
});
function search() {
	document.forms[0].submit();
}