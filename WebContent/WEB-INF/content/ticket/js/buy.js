var passengerId = 1;
var passengerNumber=1;
var passengerObj;
function addPassenger() {
	passengerId += 1;
	passengerNumber+=1;
	var html = " <div id=\"divPassengerAdult" + passengerId + "\" style=\"display: block;\" class=\"single_passenger\">"+
	"<div> <strong class=\"title\"></strong> <input type=\"button\" value=\"删除\" onclick=\"deletePassenger(this)\" class=\"button button-pill button-primary button-small\"> "+
	"<a href=\"javascript:void(0)\" onclick=\"\" class=\" button button-raised button-primary button-pill button-small btn_zhongtu\" style=\"\">从常用联系人中添加</a> 	"+
	"	</div> 	<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"tb_form\"> 	"
	+"	<tbody> "+
	"    <tr> 	<th>姓名</th> <td> <div> 	"+
	"	<input type=\"text\" class=\"input_name\" style=\"color: #999;width: 100px;\" onfocus=\"\"> 	</div> 	"+
	"	</td> 	</tr> 		<tr> 		<th>证件号</th>	<td> 	<div> 	"+
	"<input type=\"text\" class=\"input_idCard\" style=\"color: #999;width: 308px;\" onfocus=\"\"> 		"+
	"<tr><th>性别</th><td><input type=\"radio\" name=\"gender"+passengerId+"\" value=\"1\" class=\"gender\"  checked=\"checked\"/>男&nbsp;&nbsp;&nbsp;"+
	"<input type=\"radio\" name=\"gender"+passengerId+"\" value=\"0\" class=\"gender\" />女</td></tr><tr><th>学生票</th>"+
	"<td><input type=\"radio\" name=\"isStudent"+passengerId+"\" value=\"1\" class=\"isStudent\" onclick=\"caculate()\"/>是&nbsp;&nbsp;&nbsp;"+
	"<input type=\"radio\" name=\"isStudent"+passengerId+"\" value=\"0\" class=\"isStudent\" onclick=\"caculate()\"  checked=\"checked\"/>否</td></tr>"+
	"	</div> 	</td> 	</tr> </tbody> 	</table> </div> ";
	
	$("#passengerInfo").append(html);
	var i=1;
	$(".title").each(function(){
		var content="第"+i+"位乘客";
    	$(this).html(content);
    	i++;
    });
    caculate();
    $('.btn_zhongtu').click(function() {
		$('.mask').css({
			'display': 'block'
		});
		center($('.mess'));
		passengerObj=$(this);
	});
}

function deletePassenger(a) {
	var psgid = $(a).parent().parent().remove();
	passengerNumber-=1;
	var i=1;	
	$(".title").each(function(){
		var content="第"+i+"位乘客";
    	$(this).html(content);
    	i++;
   });
   caculate();
}
function caculate(){
	var singlePrice =Number($("#spprice").text());
	$(".passengers").html(passengerNumber);
	$("input[name='insurance']:checkbox:checked").each(function(){
		singlePrice+=Number($(this).val());
	});
	var result = singlePrice*passengerNumber;
	$("input[class='isStudent']:checked").each(function(){
		if($(this).val()==1){
			result -=Number($("#spprice").text())*0.5;
		}
	})
	$(".totalprice").html(result.toFixed(2));
}

	// 居中
function center(obj) {
	var screenWidth = $(window).width();
	screenHeight = $(window).height(); //当前浏览器窗口的 宽高
	var scrolltop = $(document).scrollTop(); //获取当前窗口距离页面顶部高度
	var objLeft = (screenWidth - obj.width()) / 2;
	var objTop = (screenHeight - obj.height()) /3.2 + scrolltop;
	obj.css({
		left: objLeft + 'px',
		top: objTop + 'px',
		'display': 'block'
	});
	//浏览器窗口大小改变时
	$(window).resize(function() {
		screenWidth = $(window).width();
		screenHeight = $(window).height();
		scrolltop = $(document).scrollTop();
		objLeft = (screenWidth - obj.width()) / 2;
		objTop = (screenHeight - obj.height()) / 3.2 + scrolltop;
		obj.css({
			left: objLeft + 'px',
			top: objTop + 'px',
			'display': 'block'
		});
	});
	//浏览器有滚动条时的操作、
	$(window).scroll(function() {
		screenWidth = $(window).width();
		screenHeight = $(window).height();
		scrolltop = $(document).scrollTop();
		objLeft = (screenWidth - obj.width()) / 2;
		objTop = (screenHeight - obj.height()) / 3.2 + scrolltop;
		obj.css({
			left: objLeft + 'px',
			top: objTop + 'px',
			'display': 'block'
		});
	});
	return false;
}
//确定取消的操作

function confirm(){
    var name= $("input[name='choosePassenger']:checked").next(".name").text();
	var idCard=$("input[name='choosePassenger']:checked").next("span").next("span").text();
	var gender=1-$("input[name='choosePassenger']:checked").next("span").next("span").next("span").attr("value");
	var isStudent=1-$("input[name='choosePassenger']:checked").next("span").next("span").next("span").next("span").attr("value");
	$(passengerObj).parent().parent().find(".input_name").val(name);
	$(passengerObj).parent().parent().find(".input_idCard").val(idCard);
	$(passengerObj).parent().parent().find(".gender").eq(gender).attr("checked",'checked');
	$(passengerObj).parent().parent().find(".isStudent").eq(isStudent).attr("checked",'checked');
	$(passengerObj).parent().parent().find(".gender").attr("disabled",true);
	$(passengerObj).parent().parent().find(".isStudent").attr("disabled",true);
	$(passengerObj).parent().parent().find(".input_name").attr("disabled",true);
	$(passengerObj).parent().parent().find(".input_idCard").attr("disabled",true);
	closed($('.mask'), $('.mess'));
	$(window).unbind('scroll');
	$(window).unbind('resize');
	 caculate();
}
function cancel(){
	closed($('.mask'), $('.mess'));	
	$(window).unbind('scroll');
	$(window).unbind('resize');
}
// 隐藏 的操作
function closed(obj1, obj2) {
	obj1.hide();
	obj2.hide();
}

$(document).ready(function() {
	caculate();
	$('.btn_zhongtu').click(function() {
		$('.mask').css({
			'display': 'block'
		});
		center($('.mess'));
		passengerObj=$(this);
	});
	var singlePrice =Number($("#spprice").text());
	$("#spprice").html(singlePrice.toFixed(2));
	
});