document.onload = function() {
	/**
	 * proList.jsp
	 */
	var proId;
	var count = 0;
	$(".alterProA").click(function() {
		var id = $(this).parent().parent().children().first().html();
		location.href = "/SuperMarket/pro/UpdatePro?id=" + id;
	});
	$(".checkProA").click(function() {
		var id = $(this).parent().parent().children().first().html();
		location.href = "/SuperMarket/pro/CheckPro?id=" + id;
	});
	$('.removeProA').click(function() {
		proId = $(this).parent().parent().children().first().html();
		$('.zhezhao').css('display', 'block');
		$('#removeUse').fadeIn();
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
				url : "/SuperMarket/pro/RemovePro",
				data : "id=" + proId,
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

	/**
	 * proDetail.jsp
	 */
	function check() {
		var form = document.proDetail;
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

}