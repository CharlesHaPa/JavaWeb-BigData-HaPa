<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>success</title>
	<script type="text/javascript" src="../item/js/zpp.js" ></script>
	<script language="javascript" type="text/javascript"> 
			var i = 3; 
			var intervalid; 
			intervalid = setInterval("fun()", 1000); 
			function fun() { 
				if (i == 0) { 
					$.get("/HaPa/item/personalItem",function(data){
						$(".itemContent").html(data);
						changeBtnStatus();
					});
					clearInterval(intervalid); 
				} 
				document.getElementById("mes").innerHTML = i; 
				i--; 
			} 
</script> 
</head>
<body>
	<div id="return-success"> 
		<span style="text-align: center;display: block; line-height: 180px; margin: auto; font-size: 40px;color: blue;"><b>退票成功！</b></span>
		<p style="font-size: 25px; text-align: center;">将在 <span id="mes">3</span> 秒钟后跳转...</p> 
	</div> 
</body>
</html>