<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List" import="java.util.Date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据管理</title>


<script type="text/javascript" src="<%=basePath%>assets/js/jquery-3.1.1.js"></script>
<script src="<%=basePath%>assets/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=basePath%>assets/css/bootstrap.min.css">
<script type="text/javascript" src="<%=basePath%>admin/js/spin.min.js" ></script>


</head>
<script>

function update(list,id){
	$("#"+id).empty();
	for(var i=0;i<list.length;i++){
		$("#"+id).append("<input type='checkbox' name='"+id+
				"' value='"+list[i]+"'>"+list[i]+"<br/>");
	}
}
$(function(){
	
	var pres = []; //直接声明Array
	var opts = {
            lines: 9, // The number of lines to draw
            length: 0, // The length of each line
            width: 10, // The line thickness
            radius: 15, // The radius of the inner circle
            corners: 1, // Corner roundness (0..1)
            rotate: 0, // The rotation offset
            color: '#000', // #rgb or #rrggbb
            speed: 1, // Rounds per second
            trail: 60, // Afterglow percentage
            shadow: false, // Whether to render a shadow
            hwaccel: false, // Whether to use hardware acceleration
            className: 'spinner', // The CSS class to assign to the spinner
            zIndex: 2e9, // The z-index (defaults to 2000000000)
            top: 'auto', // Top position relative to parent in px
            left: 'auto' // Left position relative to parent in px
        };
    var target1 = document.getElementById('hiveWait');
    var spinner = new Spinner(opts).spin(target1);
    var target2 = document.getElementById('localWait');
    var spinner = new Spinner(opts).spin(target2);
    var target3 = document.getElementById('sqlWait');
    var spinner = new Spinner(opts).spin(target3);
    change("local");
    $("#hiveWait").hide();
    $("#sqlWait").hide();
	$.ajax({
		type : "post",
		async : false,
		url : "<%=basePath %>getDbs", 
	    		dataType : "json",//请求发送到TestServlet处
	            success : function(result) {
	            	$.each(result,function(index,content){
	            		pres.push(content);
		             	})
		      
	            },
	            error : function(errorMsg) {
	              alert("失败!");	        
	            }
	    	})
	     //设置一个省的公共下标
	    var pIndex = -1;
	    var preEle = document.getElementById("pre");
	     //先设置省的值
	    for (var i = 0; i < pres.length; i++) {
	        var op = new Option(pres[i], i);
	        //添加
	        preEle.options.add(op);
	    }
	   
	
})
function change(db){
	  $("#"+db+"Wait").show();
		$.ajax({
			 type : "post",
	         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
	         url : "<%=basePath %>getTable",    //请求发送到TestServlet处
	         data : {
	        	 "location":db,
	        	 "db":$("#pre").find("option:selected").text(),
	        	 },
	         dataType : "json",
	         success : function(result) {
	        	  var tables=[];
	              $.each(result,function(index,content){
						tables.push(content);
	             })
	             update(tables,db);
	              $("#"+db+"Wait").hide();
	         },
	         error : function(errorMsg) {
	            $("#"+db+"Wait").hide();
	         alert("图表请求数据失败!");	        
	         }
		})
	}
function chg(obj) {   
	if($("#pre").find("option:selected").text()==""||$("#pre").find("option:selected").val()==-1){
		return;
	}
	var cityEle = document.getElementById("city");
    if (obj.value == -1) {
        cityEle.options.length = 0;
    }
    //获取值
    var val = obj.value;
    pIndex = obj.value;
    //获取ctiry
    cs=[];
    $.ajax({
		type : "post",
		async : false,
		url : "<%=basePath %>getTable",
		data : {
			"location":"hive",
			"db":$("#pre").find("option:selected").text(),
			},
		dataType : "json",//请求发送到TestServlet处
        success : function(result) {      	
        	$.each(result,function(index,content){
        		cs.push(content);
             	})
           	 change("hive");
        },
        error : function(errorMsg) {
          alert("失败!");	        
        }
	})

    //获取默认区
    $("#city").empty();
    for (var i = 0; i < cs.length; i++) {    	
        var op = new Option(cs[i], i);
        cityEle.options.add(op);
    }

}
function chg2(obj){
	var data=[];
	var db =$("#pre").find("option:selected").text();
	var table = $("#city").find("option:selected").text();
	 $.ajax({
 		type : "post",
 		async : false,
 		url : "<%=basePath %>getStructure",
 		data : {
 			"table":table,
 			"db":db,
 			},
 		dataType : "json",//请求发送到TestServlet处
         success : function(result) {
        	 
         	$.each(result,function(index,content){
         		data.push(content);
             	})
              $("#dbtable").empty();
         },
         error : function(errorMsg) {
           alert("失败!");	        
         }
 	})
 	for(var i=0;i<data.length;i+=2){
 		$("#dbtable").append("<tr><td>"+data[i]+"</td>&nbsp&nbsp&nbsp<td>"+data[i+1]+"</td></tr>");
 	}

}
function jqchk(name){ //jquery获取复选框值
		var chk_value ="";
		$('input:checkbox[name="'+name+'"]:checked').each(function(){
			chk_value+=$(this).val()+",";
		});
		return chk_value;
	} 
function del(){
	 $("#hiveWait").show();
	$.ajax({
		type : "post",
		async : true,
		url : "<%=basePath %>delTable",    //请求发送到TestServlet处
        data : {
        	"del":jqchk("hive"),
        	"db":$("#pre").find("option:selected").text()
        	},
      
        success : function(result) {
            change("hive");
        },
        error : function(errorMsg) {
          alert("失败!");	 
          $("#hiveWait").show();
        }
	})
}
function add(){	
	if($("#pre").find("option:selected").text()==""||$("#pre").find("option:selected").val()==-1){
		alert("请选择数据库")
	}else{	
		var dir = prompt("请输入路径", "");
		$("#hiveWait").show();
		$.ajax({
			type : "post",
			async :true,
			url : "<%=basePath %>addTable",    //请求发送到TestServlet处
	        data : {
	        	"add":jqchk("local"),
	        	"dir": dir,
	        	"db":$("#pre").find("option:selected").text()
	        	},
	        success : function(result) {
	            change("hive");
	        },
	        error : function(errorMsg) {
	          alert("失败!");	        
	        }
		})
	}
}
function cmd(){
	var dir = prompt("请输入路径", "");
	if($("#pre").find("option:selected").text()==""||$("#pre").find("option:selected").val()==-1){
		alert("请选择数据库");
	}else if($("#cmd").val()==""||$("#tableName").val()==""){
		alert("输入不能为空");
	}else{
	$("#sqlWait").show();
		$.ajax({
			type : "post",
			async : true,
			url : "<%=basePath %>runCmd",    //请求发送到TestServlet处
	        data : {
	        	"cmd":$("#cmd").val(),
	        	"tableName":$("#tableName").val(),
	        	"dir": dir,
	        	"db":$("#pre").find("option:selected").text()
	        	},
	        success : function(result) {
	        	 $("#sqlWait").hide();
	            change("hive");
	           
	        },
	        error : function(errorMsg) {
	         $("#sqlWait").hide();
	          alert("失败!");	        
	        }	
		})
	}
}
</script>
<body>	
	<div class="row">

		<div class="col-md-2"><h3>查询Hive表结构</h3>
			<label>数据库</label>
				<select class="form-control"  id="pre" onchange="chg(this)">
				<option value="-1">请选择</option>
				</select>		
			<label>表名</label>
				<select class="form-control"  id="city" onchange="chg2(this)">
					<option value="-1"></option>
				</select>
			<div class="table-responsive">
				<h3>结构</h3>
				<table class="table table-striped" id="dbtable">
				</table>
			</div>
		</div>

	<div  class="col-md-2">
		<div id="local"></div>
		<input class="btn btn-default" type="button" onclick="add()" value="添加到Hive">
		<div id="localWait" ></div>
	</div>

	<div class="col-md-2" >	
	    <div id="hive"></div>
		<input class="btn btn-default" type="button" onclick="del()" value="删除">
		<div id="hiveWait" ></div>
	</div>
		<div class="col-md-2">
			<label>嵌入SQl到sqoop命令中：</label>
			<input type="text" class="form-control" id="cmd"" placeholder="SQL">
			<label>设定表名</label>
			<input type="text" class="form-control" id="tableName" placeholder="FormName">
			<button class="btn btn-success" type="button" onclick="cmd()">执行</button>
			<div id="sqlWait" ></div>
		</div>	
		<div class="col-md-2"></div>
	</div>
	
	
</body>
</html>