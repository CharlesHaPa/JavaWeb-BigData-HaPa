function checkname(){
	var name=document.getElementById("userName").value.trim();
	var c_name=/[\u4e00-\u9fa5]{2,10}$/;
	if(!c_name.test(name)){
		document.getElementById("nameInfo").innerHTML="不能为空或一个字且请输入中文";
		return false;
	}else{
		document.getElementById("nameInfo").innerHTML="";
		return true;
	}
}

function checkEmail () {
	var email=document.getElementById("email").value.trim();
	var c_email= /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	if(!c_email.test(email)){
		document.getElementById("emailInfo").innerHTML="不能为空且请输入正确的邮箱格式";
		return false;
	}else{
		document.getElementById("emailInfo").innerHTML="";
		return true;
	}
}

function checkphone(){
	var phone=document.getElementById("telephone").value.trim();
	var c_phone=/0?(13|14|15|18|17)[0-9]{9}/;
	if(!c_phone.test(phone)){
		document.getElementById("telephoneInfo").innerHTML="输入正确的号码";
		return false;
	}else{
		document.getElementById("telephoneInfo").innerHTML="";
		return true;
	}
}

function checkcardid(){
	var cardId=document.getElementById("cardId").value.trim();
	var c_cardId=/(^\d{15}$)|(^\d{17}(\d|X)$)/;
	if(!c_cardId.test(cardId)){
		document.getElementById("cardInfo").innerHTML="输入正确的身份证号码(X请大写)";
		return false;
	}else{
		document.getElementById("cardInfo").innerHTML="";
		return true;
	}
}
function checkpsw(){
	var password=document.getElementById("psw").value.trim();
	var c_password=/^[a-zA-Z0-9]{6,20}$/;
	if(!c_password.test(password)){
		document.getElementById("pswInfo").innerHTML="密码请输入六位以上的密码（限数字字母）";
		return false;
	}else{
		document.getElementById("pswInfo").innerHTML="";
		return true;
	}
}
function checkPwd2(){
	var password=document.getElementById("psw").value.trim();
	var Pwd2=document.getElementById("Pwd2").value.trim();
	if(Pwd2!=password){
		document.getElementById("Pwd2Info").innerHTML="密码前后不一致";
		return false;
	}else{
		document.getElementById("Pwd2Info").innerHTML="";
		return true;
	}
}
function check(){
	if(checkcardid()&&checkEmail()&&checkname()&&checkphone()&&checkPwd2())
		{
			return true;
		}
	else{
		alert("请完善信息");
		return false;
	}
}
//解决方案二：在所有必填项目（input）内加入required="required"属性，备注或许仅仅支持h5