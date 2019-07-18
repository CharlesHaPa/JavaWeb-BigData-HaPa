<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>HaPa</title>
		<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	</head>
	<link rel="stylesheet" href="<%=basePath%>client/css/fws.css" />

	<script type="text/javascript" src="<%=basePath%>assets/js/jquery-3.1.1.js"></script>
	<script>
		var edit;
		var i;
		function editInfo(){
			$('#email').removeAttr("readonly");
			$('#address').removeAttr("readonly");
			$('#telephone').removeAttr("readonly");
			$('#email').css("background-color","white");
			$('#address').css("background-color","white");
			$('#telephone').css("background-color","white");
		}
		function modify(){
			var phone=document.getElementById("telephone").value.trim();
			var c_phone=/0?(13|14|15|18|17)[0-9]{9}/;
			var email=document.getElementById("email").value.trim();
			var c_email= /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
			if(!c_phone.test(phone) || !c_email.test(email)){
				alert("请正确填写信息！");
			}else{
				var userName=$("#user_Name").val();
				var gender=$("#gender").data("sex");
				var cardId=$("#Id").val();
				var email=$("#email").val();
				var telephone=$("#telephone").val();
				var address=$("#address").val();
				$.ajax({
		        	type: "POST",
		            url: "/HaPa/modify",
		            data: {userName:userName, gender:gender,cardId:cardId,email:email,telephone:telephone,address:address},
		            dataType: "json",
		            /* contentType : 'application/json;charset=utf-8', */
		            success: function(data){
		            	if(data.result == "yes"){
		            		alert("保存成功");
		            		$('#email').attr("readonly","true");
		    				$('#address').attr("readonly","true");
		    				$('#telephone').attr("readonly","true");
		    				$('#email').css("background-color","#BBBBBB");
		    				$('#address').css("background-color","#BBBBBB");
		    				$('#telephone').css("background-color","#BBBBBB");
		            	}else if(data.result == "no"){
		            		alert("邮箱、手机或者身份证号已存在！");
		            	}else{
		            		alert("1");
		            	}
		            	
		            }
		        });
				
			}
			
		}
	</script>
	
	<script>
		function checkphone(){
			var phone=document.getElementById("telephone").value.trim();
			var c_phone=/0?(13|14|15|18|17)[0-9]{9}/;
			if(!c_phone.test(phone)){
				document.getElementById("telephoneInfo").innerHTML="输入正确的号码";
			}else{
				document.getElementById("telephoneInfo").innerHTML="";
				
				return true;
			}
		}
		
		function checkEmail () {
			var email=document.getElementById("email").value.trim();
			var c_email= /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
			if(!c_email.test(email)){
				document.getElementById("emailInfo").innerHTML="不能为空且请输入正确的邮箱格式";
			}else{
				document.getElementById("emailInfo").innerHTML="";
				
				return true;
			}
		}
	</script>
	
	<body >

			<!--个人信息-->
  			<div class="lay-hd">个人信息</div>
					<div class="informationBar">
						<p class="text">姓名:</p><input type="text" id="user_Name" class="textBar" readonly="true" value='${CurrentClient.userName }'>
					</div>
					<div class="informationBar">
						<p class="text">性别:</p><input type="text" id="gender" class="textBar" readonly="true" data-sex="${CurrentClient.gender}" value='${CurrentClient.gender eq 0?'女':'男' }'>
					</div>
					<div class="informationBar">
						<p class="text">身份证号:</p><input type="text" id="Id" class="textBar" readonly="true" value='${CurrentClient.cardId}'>
					</div>
					<div class="informationBar">
						<p class="text">邮箱:</p><input type="text" id="email" class="textBar" readonly="true" onblur="checkEmail()" value="${CurrentClient.email}">
						<div class="reg_tips"><span id="emailInfo" ></span></div>
					</div>
					<div class="informationBar">
						<p class="text">手机:</p><input type="text" id="telephone" class="textBar" readonly="true" onblur="checkphone()" value="${CurrentClient.telephone}">
						<div class="reg_tips"><span id="telephoneInfo"></span></div>
					</div>
					<div class="informationBar">
						<p class="text">家庭住址:</p><input type="text" id="address" class="textBar" readonly="true" value="${CurrentClient.address}">
					</div>
					<div class="informationBar">
						<p class="text">出生日期:</p><input type="text" id="birth" class="textBar" readonly="true" value="<fmt:formatDate value="${CurrentClient.birth}" pattern="yyyy-MM-dd" /> ">
					</div>
					<div class="button">
						<button class="clientInformationButton" type="button" onclick="editInfo()" name="editButton">编辑信息</button>
						<button class="clientInformationButton" type="submit" onclick="modify()" name="editButton">保存信息</button>
					</div>


	</body>

</html>