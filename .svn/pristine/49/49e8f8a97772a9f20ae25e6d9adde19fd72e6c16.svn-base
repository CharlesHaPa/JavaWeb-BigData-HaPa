$(function($) {
	//弹出登录
	$("#login").hover(
/*	function() {$(this).stop().animate({opacity: '1'}, 600);}, 
	function() {$(this).stop().animate({opacity: '0.6'}, 1000);}*/)
	.on('click', function() {
		$("body").append("<div id='mask'></div>");
		$("#mask").addClass("mask").fadeIn("slow");
		$("#LoginBox").fadeIn("slow");
	});
	//
	//按钮的透明度
	$("#loginbtn").hover(function() {
		$(this).stop().animate({
			opacity: '1'
		}, 600);
	}, function() {
		$(this).stop().animate({
			opacity: '0.8'
		}, 1000);
	});
	//文本框不允许为空---按钮触发
	$("#loginbtn").on('click', function() {
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
	//密码
	$("#Pwd").on('blur', function() {
		var userName = $("#Pwd").val();
		if(userName == "" || userName == undefined || userName == null) {
			$("#warn2").css({
				display: 'block'
			});
		} else {
			$("#warn2").css({
				display: 'none'
			});
		}
	});
	$("#Pwd").on('focus', function() {
		$("#warn2").css({
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
		$("#LoginBox").fadeOut("fast");
		$("#mask").css({
			display: 'none'
		});
	});
});