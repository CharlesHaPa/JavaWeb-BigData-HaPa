
function fun_return(itemId,clientId,price,rate){
	window.itemId = itemId;
	var deductAmount = price*rate;
	var returnAmount = price - deductAmount;
	    $('.open_rec_interface').click(function(){
			$('.returnTicket_cover').fadeIn(20);
			$('.returnTicket').slideDown(20);
		})
		$('.re_close').click(function(){
			$('.returnTicket_cover').fadeOut(20);
			$('.returnTicket').slideUp(20);
		})

	$('.returnTicket_cover').fadeIn(20);
	$('.returnTicket').slideDown(20);
	//退票窗口基于浏览器居中
	inTheMiddle($(".returnTicket"));
	$(".re_body_right").load("../item/returnConfirm","price="+price+"&returnAmount="+returnAmount+"&deductAmount="+deductAmount+"&rate="+rate);
}

$(document).ready(function(){ 
		
	$("#selectItemType").change(function() {
		
		if (this.value == "notTravel" || this.value == "Traveled") {
			document.getElementById("fromDate").disabled = true;
			document.getElementById("toDate").disabled = true;
			document.getElementById("fromDate").value = "";
			document.getElementById("toDate").value = "";
		} else {
			document.getElementById("fromDate").disabled = false;
			document.getElementById("toDate").disabled = false;
			document.getElementById("fromDate").value = "";
			document.getElementById("toDate").value = "";
		}
	});
	$("#toDate").change(function() {
		if (document.getElementById("fromDate").value != "" && this.value <= document.getElementById("fromDate").value) {
			alert("日期应该大于起始日期");
			this.value = "";
		}
	});
	$("#fromDate").change(function() {
		if (document.getElementById("toDate").value != "" && this.value >= document.getElementById("toDate").value) {
			alert("日期应该小于截止日期");
			this.value = "";
		}
	});
});

function returnConfirm(){
	var itemId = window.itemId;
	$.ajax({ 
	    type : "POST", 
	    url : "/HaPa/returnTicket",
	    data: {
	    	itemId:itemId
	    },
	    success : function(result) { 
	    	if(result == true){
	    		$(".re_body").load("../item/success");
	    	}
	    	else{
	    		$(".re_body").load("../item/failure");
	    	}
	    } 
	
	}); 
}

$(document).ready(function btnrec(){
	changeBtnStatus();
	$('.re_close').click(function(){
		$('.returnTicket_cover').fadeOut(0);
		$('.returnTicket').slideUp(0);
		returnSubmit(itemType,startDate,endDate);
	})

})

function changeBtnStatus(){
		$(".btnnotTravel").attr("disabled",false);
		$(".btnnotTravel").css("background-color","#FF8000");
		$(".btntraveled").attr("disabled",true);
		$(".btntraveled").css("background-color","#cccccc");
		$(".btnreturned").attr("disabled",true);
		$(".btnreturned").css("background-color","#cccccc");
}

function commit(){
    window.itemType = document.getElementById("selectItemType").options[document.getElementById("selectItemType").selectedIndex].value;
    window.startDate= document.getElementById("fromDate").value;
    window.endDate= document.getElementById("toDate").value;
    
    /* window.location="/HaPa/item/queryDetails?itemType="+itemType+"&option="+option+"&fromDate="+startDate+"&toDate="+endDate; */
	/* window.location="/HaPa/item/queryDetails?itemType="+itemType+"&fromDate="+startDate+"&toDate="+endDate; */
    returnSubmit(itemType,startDate,endDate);
}

function returnSubmit(itemType,fromDate,toDate){

    	$.ajax({ 
    	    type : "POST", 
    	    url : "/HaPa/item/queryDetails",
    	    data: {
    	    	itemType: itemType,
    	    	fromDate: fromDate,
    	    	toDate: toDate,
    	    },
    	   
           	success : function(data) {
           		
       	    	$("#content").html(data);
       	    	/*一定要将changeBtnStatus()写在success中，才能改变当前页面按钮属性值*/
       	    	changeBtnStatus();	
       			if (document.getElementById("selectItemType").value == "notTravel" || document.getElementById("selectItemType").value == "Traveled") {
       				document.getElementById("fromDate").disabled = true;
       				document.getElementById("toDate").disabled = true;
       				document.getElementById("fromDate").value = "";
       				document.getElementById("toDate").value = "";
       			} else {
       				document.getElementById("fromDate").disabled = false;
       				document.getElementById("toDate").disabled = false;
       				document.getElementById("fromDate").value = "";
       				document.getElementById("toDate").value = "";
       			}
     		} 
    	}); 
}

function inTheMiddle(obj) { 
     var screenWidth = $(window).width(), screenHeight = $(window).height();  //当前浏览器窗口的 宽高
	 var scrolltop1 = $(document).scrollTop();//获取当前窗口距离页面顶部高度
	 var objLeft = (screenWidth - obj.width())/2 ;
     var objTop = (screenHeight - obj.height())/2 + scrolltop1-300;
    //浏览器窗口大小改变时
   $(window).resize(function() {
        screenWidth = $(window).width();
        screenHeight = $(window).height();
       scrolltop1 = $(document).scrollTop();
      
        objLeft = (screenWidth - obj.width())/2 ;
        objTop = (screenHeight - obj.height())/2 + scrolltop1;
       
        obj.css({left: objLeft + 'px', top: objTop + 'px'});
       
    });
    //浏览器有滚动条时的操作、
    $(window).scroll(function() {
        screenWidth = $(window).width();
        screenHeight = $(window).height();
       scrolltop1 = $(document).scrollTop();
       
        objLeft = (screenWidth - obj.width())/2 ;
        objTop = (screenHeight - obj.height())/2 + scrolltop1-300;
      
       obj.css({left: objLeft + 'px', top: objTop + 'px'});
    });
}

function endorse(endorseItemId){
	/*	$.get("<>/ticket/endorse",{isEndorse : "true",endorseItemId : endorseItemId},function(data){
			$(".itemContent").html(data);
		});*/
		$.ajax({ 
		    type : "POST", 
		    url : "/HaPa/ticket/endorse",
		    async: true,
		    data: {
		    	isEndorse : "true",
		    	endorseItemId : endorseItemId
		    },
	       	success : function(data) {
	       		$('#content').html(data);	
	 		} 
		}); 
 }