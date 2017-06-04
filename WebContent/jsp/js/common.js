/**
 * 分页验证函数
 */
function checkPage() {
	if (!checkPage2)
		return false;
	var $hidden = $(":input:hidden");
	var page = $("#page").val();
	if (page.match(/^\d+$/)) {
		var i = $("#totalPage").text();
		if (page > i) {
			alert("输入无效，必须小于最大页数");
			$("#page").focus();
			return false;
		}
	} else {
		if (page == '')
			return true;
		alert("页数必须为整数");
		return false;

	}
	return true;
}
function checkPage2 (){
	var sps = $("#setPageSize").val();
	if (!sps.match(/^\d+$/)) {
		if (sps == null || sps == "")
			return true
		alert("页数必须为正整数");
		return false;
	} else if (sps.match(/^0+$/)) {
		alert("页数必须大于0");
		return false;
	}
	return true;
}