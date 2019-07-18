<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>returnConfirm</title>
	<link rel="stylesheet" href="../item/css/zpp.css" />
	<script type="text/javascript" src="../item/js/zpp.js" ></script>
</head>
<body>
	<div>
	<% float price = Float.parseFloat(request.getParameter("price"));
	   float returnAmount = Float.parseFloat(request.getParameter("returnAmount"));
	   float deductAmount = Float.parseFloat(request.getParameter("deductAmount"));
	   float rate = Float.parseFloat(request.getParameter("rate"));
	   %>
		<span style="font-size: 18px;"><b>您确定要退票吗？<br>共计退款：</b></span>
			<span style="font-size: 18px;color: #FF8000;"><b><%=returnAmount %>  元</b></span>
		    <span style="font-size: 14px;color: #666666;"><br>车票票款：<b><%=price %> 元</b>，退票费：当前时间按</span>
		    <span style="font-size: 14px;color: #FF8000;"><%=rate*100 %>%</span>
		    <span style="font-size: 14px;color: #666666;">核收，计为 <b><%=deductAmount %> 元</b><br>应退票款：</span>
		    <span style="font-size: 15px;color: #FF8000;"><b><%=returnAmount %> 元</b></span>
		    <span style="font-size: 14px;color: #666666;"><br>实际核收退票费及应退票款将按最终交易时间计算<br></span>
		    <p>
		    	<input type="button" value="取消" class="re_close" style="margin-top: 10px;width: 90px;height: 30px;
		    	             margin-left: 45px;border-radius: 5px;margin-right: 60px;cursor: pointer;"/>
		    	<input type="submit" value="确定" onclick="returnConfirm()" style="margin-top: 10px;width: 90px;height: 30px;color: white;
		    		         background-color: #FF8000; border-radius: 5px;cursor: pointer;"/>
		    </p>
	</div>
</body>
</html>