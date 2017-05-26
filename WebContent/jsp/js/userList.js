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
			}else{
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