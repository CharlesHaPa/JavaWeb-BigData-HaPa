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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>

</head>
<body>	
<script type="text/javascript">
$(window).ready(function(){
    if($("#isNull").attr('name').length == 2){
    	alert("去程无票");
    }
})
function buy(trainId,seatType,trainName,trainType,departure,arrival){
    //验证是否登陆
       if(isLogin==1){
       	location.href="<%=basePath %>selectSeat"+ 
			"?trainId="+trainId+"&arrival="+arrival+"&departure="+departure
			+"&seatType="+seatType+"&trainName="+trainName+"&trainType="+trainType;
       }
       else{
       	if(confirm("该操作需要您先登录，是否现在登录？")) {
				$("#LoginBox").fadeIn("slow");
			}
       }
} 
</script>

     	<!--单程搜索结果-->
     	<div id="isNull" name="${trains}">
     	</div>
		<c:forEach items="${trains}" var="train" varStatus="status">
				<div class="tbody" id="tbody-01-G13210">
		        <div class="railway_list">
			        <div class="w1">
				        <div><strong>${train.trainName}</strong></div>
				     
					</div>
			        <div class="w2">
				        <div>
					        <strong>${train.startDate}</strong>
				        </div>
					    <div>
						    <label>${train.endDate}</label>
					    </div>
			        </div>
			        <div class="w3">
				        <div>
					        <i class="shi_icon">始</i>
					        <span>${train.fromStation}</span>
				        </div>
				        <div>
						    <i class="zhong_icon">终</i>
						    <span>${train.toStation}</span>
				        </div>
			        </div>
			        <div class="w4">
				        <div>${train.intervalHour}小时${train.intervalMin}分</div>
			        </div>
	                <div class="w5 ">
	               		<c:forEach items="${train.availableSeat}" var="availableSeat" varStatus="status">
		                    <div class="SG1321-209">
						        <span>${availableSeat.type}</span>
						        <small>¥</small>
						        <b>${availableSeat.price}</b>
						        <strong class=""> 余${availableSeat.seatCount}张</strong>
					        </div>
					    </c:forEach>
				    </div>
	                <div class="w6">
	               		<c:forEach items="${train.availableSeat}" var="availableSeat" varStatus="status">
					        <div>
						        <a class="qp_btn2" href="javascript:void(0)" 
						        onclick="buy('${train.trainId}','${availableSeat.type}','${train.trainName}',
						        '${train.type}','${train.fromStation}','${train.toStation}')"
						        style="display:${availableSeat.seatCount==0  ?'none':''}">预订</a>
					        </div>
				        </c:forEach>
				    <div >
				 
	            </div>
	        </div>
       			
       		</div>
       		</div>
        </c:forEach>
    	
   

</body>
</html>