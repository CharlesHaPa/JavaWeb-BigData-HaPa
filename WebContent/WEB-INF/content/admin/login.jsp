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
    <title>HaPa管理员系统</title>
    <meta charset="utf-8">
    <!-- Bootstrap -->
    <link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=basePath%>admin/css/Charles_admin.css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath%>assets/js/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>assets/js/bootstrap.min.js"></script>




</head>

<script>
	function login(){
		$.ajax({
			type:"POST",
			url:"<%=basePath%>admin/on",
			data:{
				userName:$("#inputEmail3").val(),
				psw:$("#inputPassword3").val()
			},
			dataType : 'json',
			async:false,
			success:function(data){
				if(data.flag == "fail") {
					alert("帐号或密码错误");
				}else if (data.flag == "fail1") {

					window.location.href="<%=basePath%>admin/index";
				}
			}
		});
	}
</script>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal">
                <span class="heading">用户登录</span>
                <div class="form-group">
                    <input type="text" class="form-control" id="inputEmail3" placeholder="用户名">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="密　码">
                    <i class="fa fa-lock"></i>
                    <a href="#" class="fa fa-question-circle"></a>
                </div>
                <div class="form-group">
                    <input type="button" class="btn btn-default" onclick="login()" value="登录" />
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>