$(function($) {
	//弹出登录
	$("#btn_addFrequentContacts").hover()
	.on('click', function() {
		$("body").append("<div id='mask'></div>");
		$("#mask").addClass("mask").fadeIn("slow");
		$("#addBox").fadeIn("slow");
	});

	//文本框不允许为空---按钮触发
	$("#addPButton").on('click', function() {
		var userName = $("#userName").val();
		var Pwd = $("#Pwd").val();
		if(userName == "" || userName == undefined || userName == null) {
			if(Pwd == "" || Pwd == undefined || Pwd == null) {
				$(".warning").css({
					display: 'block'
				});
			} else {
				$("#warn").css({
					display: 'block'
				});
				$("#warn2").css({
					display: 'none'
				});
			}
		} else {
			if(Pwd == "" || Pwd == undefined || Pwd == null) {
				$("#warn").css({
					display: 'none'
				});
				$(".warn2").css({
					display: 'block'
				});
			} else {
				$(".warning").css({
					display: 'none'
				});
			}
		}
	});
	//文本框不允许为空---用户帐号/邮箱/手机号
	$("#userName").on('blur', function() {
		var userName = $("#userName").val();
		if(userName == "" || userName == undefined || userName == null) {
			$("#warn").css({
				display: 'block'
			});
		} else {
			$("#warn").css({
				display: 'none'
			});
		}
	});
	$("#userName").on('focus', function() {
		$("#warn").css({
			display: 'none'
		});
	});

	//关闭
	$(".close_btn").hover(function() {
		$(this).css({
			color: 'black'
		})
	}, function() {
		$(this).css({
			color: '#999'
		})
	}).on('click', function() {
		$("#addBox").fadeOut("fast");
		$("#modifyBox").fadeOut("fast");
		$("#mask").css({
			display: 'none'
		});
		$("#mask2").css({
			display: 'none'
		});
	});
	
});