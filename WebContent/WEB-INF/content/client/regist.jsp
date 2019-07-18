<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>main/css/z_reg.css" />
<script type="text/javascript" src="<%=basePath%>main/js/z_reg.js"></script>
<script type="text/javascript" src="<%= basePath %>assets/js/jquery-3.1.1.js" ></script>
<script>
	$(function(){
		if("${registerFailed}" == "failed"){
			alert("注册失败");
		}
	})
</script>
<title>Insert title here</title>
</head>
<body style="width: 82.4%; margin: 0 auto; min-width: 1500px;">
	<form action="<%=basePath%>client/register" onsubmit="return check();" method="post">
		<div id="reg_box">
			<p class="reg_titile" style="float:left;">注册</p>
			<a href="<%=basePath%>main/test" title="关闭窗口" class="close_btn" id="closeBtn" style="margin-left:437px; font-size:25px; text-decoration:none;">×</a>
			<ul class="regist_list">
				<li class="reg_info" style="clear:both;">
					<div class="reg_label" >姓&nbsp;&nbsp;&nbsp;&nbsp;名:</div>
					<div class="reg_label-content">
						<input placeholder="请输入姓名" type="text" name="userName" id="userName"  maxlength="10"
							size="30" onblur="checkname()" />
					</div>
					<div class="reg_tips">
						<span id="nameInfo"></span>
					</div>
				</li>
				<li class="reg_info">
					<div class="reg_label" >邮&nbsp;&nbsp;&nbsp;&nbsp;箱:</div>
					<div class="reg_label-content">
						<input placeholder="请输入邮箱" type="text" name="email" id="email" size="30"
							onblur="checkEmail()" />
					</div>
					<div class="reg_tips">
						<span id="emailInfo"></span>
					</div>
				</li>
				<li class="reg_info">
					<div class="reg_label" >手机号:</div>
					<div class="reg_label-content">
						<input placeholder="请输入手机号" type="text" name="telephone" id="telephone" size="30"
							onblur="checkphone()" />
					</div>
					<div class="reg_tips">
						<span id="telephoneInfo"></span>
					</div>
				</li>
				<!--证件类型可拓展-->
				<!--<li>
					<div class="reg_label"><span class="required">*</span> 证件类型：
					</div>
					<div class="r-txt">
						<select class="w200sel" name="card" id="cardType">
							<option value="1"><span>二代身份证</span></option>
							<option value="C"><span>港澳通行证</span></option>
							<option value="G"><span>台湾通行证</span></option>
							<option value="B"><span>护照</span></option>
						</select>
					</div>	
				</li>-->
				<li class="reg_info">
					<div class="reg_label">证件号码:</div>
					<div class="reg_label-content">
						<input placeholder="请输入身份证号码" type="text" id="cardId" name="cardId" size="30"
							onblur="checkcardid()" />
					</div>
					<div class="reg_tips">
						<span id="cardInfo"></span>
					</div>
				</li>
				<li class="reg_info">
					<div class="reg_label">密&nbsp;&nbsp;&nbsp;&nbsp;码:</div>
					<div class="reg_label-content">
						<input placeholder="请输入密码" type="password" id="psw" name="psw" size="30"
							onblur="checkpsw()" />
					</div>
					<div class="reg_tips">
						<span id="pswInfo"></span>
					</div>
				</li>
				<li class="reg_info">
					<div class="reg_label">确认密码:</div>
					<div class="reg_label-content">
						<input placeholder="请再次输入密码" type="password" id="Pwd2"  size="30"
							onblur="checkPwd2()" />
					</div>
					<div class="reg_tips">
						<span id="Pwd2Info"></span>
					</div>
				</li>
				<li class="reg_info">
					<div class="reg_label">性&nbsp;&nbsp;&nbsp;&nbsp;别:</div>
					<div class="reg_label-content">
						<input type="radio" name="gender" value="1" checked="">男
						<input type="radio" name="gender" value="0">女
					</div>
				</li>
				<li class="reg_info">
					<div class="reg_label">家庭住址:</div>
					<div class="reg_label-content">
						<input placeholder="选择填写" type="text" name="address" id="address" size="30" />
					</div>
				</li>
				<li class="reg_info">
					<div class="reg_label">出生日期:</div>
					<div class="reg_label-content">
						<input type="date" name="birth" id="birth" size="30"  required="required" />
					</div>
				</li>
				<li class="reg_info">
					<div class="reg_label">用户协议 :</div>
					<div class="reg_label-content"  style="top: 0px;">
						<label><input id="I_agree" type="checkbox"  style="width: 18px;height: 18px;" value="" required="required"/>我已阅读并同意遵守</label>
					</div>
					<div class="reg_tips">
						<a href="https://kyfw.12306.cn/otn/regist/rule" target='_BLANK'
							id="agreement" style="color: #FF7F00">《中国铁路客户服务中心网站服务条款》</a>
					</div>
				</li>
				<div class="reg_submit">
					<input id="reg_submit" type="submit" value="注册">
				</div>


			</ul>
		</div>
	</form>
</body>
</html>