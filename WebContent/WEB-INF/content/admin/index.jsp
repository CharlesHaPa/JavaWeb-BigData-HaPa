<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<title>HaPa管理员系统</title>
		<link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet">
	    <link href="<%=basePath%>admin/css/Charles_admin.css" rel="stylesheet">
	    <script type="text/javascript" src="<%=basePath%>assets/js/jquery-3.1.1.js"></script>
	    <script type="text/javascript" src="<%=basePath%>assets/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>admin/js/echarts.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>admin/js/city.js"></script>
	</head>
<script type="text/javascript">
	function checkthelogin(){
		if("<%=session.getAttribute("flagUserName")%>" == "null"){
			alert("请您先登录！");
			window.location.href="<%=basePath%>admin/main";
		}
	}
	
	function getouthere(){
		$.ajax({
			type:"GET",
			url:"<%=basePath %>admin/logout",
			cache:false, 
			success:function(data){
				window.location.href="<%=basePath%>admin/main";
			}
		});
		
	}
</script>

<body onload="checkthelogin()">
	
	<!--nav bar -->
	<nav class="navbar navbar-inverse navbar-fixed-top" id="nav">
	    <div class="container-fluid">
	        <div class="navbar-header">
	            <a class="navbar-brand" href="#" id="project_name">HaPa管理员系统</a>
	        </div>
	
	        <div id="logout" class="navbar-collapse collapse">
	            <ul class="nav navbar-nav navbar-right">
	                <li><a href="login.html">注销</a></li>
	            </ul>
	        </div>
	
	    </div>
	
	</nav>


	<!-- sidebar -->
	<div class="col-sm-3 col-md-2 sidebar" id="menu">
	
	    <ul class="nav nav-sidebar">
	        <li><a href="<%=basePath%>admin/index">数据统计</a></li>
	        <li><a href="<%=basePath%>admin/cmd">数据管理</a></li>
	        <li><a href="<%=basePath%>map/railPressure">线路压力图</a></li>
	        <li><a href="<%=basePath%>map/cityPressure">城市压力图</a></li>
	    </ul>
	
	</div>


	<!-- main -->
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="main">
		
		<!-- 按钮组  -->
		<div class="btn-group" role="group" id="selectChart">
			<button type="button" class="btn btn-lg select_btn" value="0">用户订票分布图(散点图)</button>
			<button type="button" class="btn btn-lg select_btn" value="2">日订票量走势(折线图)</button>
			<button type="button" class="btn btn-lg select_btn" value="3">各类型车次订票量(柱状图)</button>
			<button type="button" class="btn btn-lg select_btn" value="1">各地点售票占比(饼图)</button>
		</div>
		
	<!-- 1 -->
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main chart_div" id="0" style="display:block">
	
		<div class="chart_table">
			<label>请选择数据年份</label>
			<select id="year_1">
				<option value ="2016">2016</option>
				<option value ="2017">2017</option>
			</select>
			<input class="btn btn-info" value="展示" id="genderBtn" onclick="queryGender()"/>
		</div>
	
	<div class="chart" id="chart_1" style="width: 1000px;height:800px;" 
	class="col-sm-9 col-sm-offset-4 col-md-10 col-md-offset-3 main">
		<script type="text/javascript">
		function queryGender(){
		var genderChart = echarts.init(document.getElementById('chart_1'));
		var	option_1 = {
			    title : {
			        text: '男女不同年龄购票量分布'
			    },
			    grid: {
			        left: '3%',
			        right: '7%',
			        bottom: '3%',
			        containLabel: true
			    },
			    tooltip : {
			        // trigger: 'axis',
			        showDelay : 0,
			        formatter : function (params) {
			            if (params.value.length > 1) {
			                return params.seriesName + ' :<br/>'
			                + params.value[0] + '岁 '
			                + params.value[1] + '张';
			            }
			            else {
			                return params.seriesName + ' :<br/>'
			                + params.name + ' : '
			                + params.value + 'kg ';
			            }
			        },
			        axisPointer:{
			            show: true,
			            type : 'cross',
			            lineStyle: {
			                type : 'dashed',
			                width : 1
			            }
			        }
			    },
			    toolbox: {
			        feature: {
			            dataZoom: {},
			            brush: {
			                type: ['rect', 'polygon', 'clear']
			            }
			        }
			    },
			    brush: {
			    },
			    legend: {
			        data: ['女性','男性'],
			        left: 'center'
			    },
			    xAxis : [
			        {
			            type : 'value',
			            scale:true,
			            axisLabel : {
			                formatter: '{value} 岁'
			            },
			            splitLine: {
			                show: false
			            }
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            scale:true,
			            axisLabel : {
			                formatter: '{value} '
			            },
			            splitLine: {
			                show: false
			            }
			        }
			    ],
			    series : [
			        {
			            name:'女性',
			            type:'scatter',
			            data: famale_data ,
			            markArea: {
			                silent: true,
			                itemStyle: {
			                    normal: {
			                        color: 'transparent',
			                        borderWidth: 1,
			                        borderType: 'dashed'
			                    }
			                },
			                data: [[{
			                    name: '女性分布区间',
			                    xAxis: 'min',
			                    yAxis: 'min'
			                }, {
			                    xAxis: 'max',
			                    yAxis: 'max'
			                }]]
			            },
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            markLine : {
			                lineStyle: {
			                    normal: {
			                        type: 'solid'
			                    }
			                },
			                data : [
			                    {type : 'average', name: '平均值'},
			                    { xAxis: 160 }
			                ]
			            }
			        },
			        {
			            name:'男性',
			            type:'scatter',
			            data: male_data ,
			            markArea: {
			                silent: true,
			                itemStyle: {
			                    normal: {
			                        color: 'transparent',
			                        borderWidth: 1,
			                        borderType: 'dashed'
			                    }
			                },
			                data: [[{
			                    name: '男性分布区间',
			                    xAxis: 'min',
			                    yAxis: 'min'
			                }, {
			                    xAxis: 'max',
			                    yAxis: 'max'
			                }]]
			            },
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            markLine : {
			                lineStyle: {
			                    normal: {
			                        type: 'solid'
			                    }
			                },
			                data : [
			                    {type : 'average', name: '平均值'},
			                    { xAxis: 170 }
			                ]
			            }
			        }
			    ]
			};
			genderChart.setOption(option_1);
			var male_data = [];
			var famale_data = [];

					$.ajax({
			             type : "post",
			             async : true,
			             dataType : "json",
			             url:"<%=basePath%>admin/male",
			             data:{
			            	 year:$('#year_1').val()
			             },
			             success : function(data) {
			                 
			                 $.each(data,function(idx,obj){
			                	 var tmp = [];
			                	 tmp.push(obj.age);
			                	 tmp.push(obj.count);
			                	 male_data.push(tmp);
			                 });
			                 option_1.series[0].data = male_data;
			                 genderChart.setOption(option_1,true);
			             }
	    	   		 });
					$.ajax({
			             type : "post",
			             async : true,
			             dataType : "json",
			             url:"<%=basePath%>admin/famale",
			             data:{
			            	 year:$('#year_1').val()
			             },
			             success : function(data) {
			                 
			                 $.each(data,function(idx,obj){
			                	 var tmp = [];
			                	 tmp.push(obj.age);
			                	 tmp.push(obj.count);
			                	 famale_data.push(tmp);
			                 });
			                 option_1.series[1].data = famale_data;
			                 genderChart.setOption(option_1,true);
			             }
	    	   		});

		}
		</script>
	</div>
	</div>
	<!-- 2 -->
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main chart_div" id="1"  style="display:none">
		<div class="chart_table">
			<label>请选择数据年份</label>
			<select id="year_2">
				<option value ="2016">2016</option>
				<option value ="2017">2017</option>
			</select>
			<input class="btn btn-info" value="展示" id="placeBtn" onclick="queryPlace()"/>
		</div>
	
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div class="chart" id="chart_2" style="width: 1000px;height:800px;" 
	class="col-sm-9 col-sm-offset-4 col-md-10 col-md-offset-3 main">
	</div>
	<script type="text/javascript">
	var cities = [];
	var numToCities = [];
	var year_2;
	function queryPlace(){
		year_2 = $('#year_2').val();
		
		$.ajax({
             type : "post",
             async : true,
             dataType : "json",
             url:"<%=basePath%>admin/showTicketsNumGroupByCity",
				data : {
					year:year_2
				},
				success : function(data) {
					cities.splice(0, cities.length);
					numToCities.splice(0, numToCities.length);
					$.each(data[0], function(idx, obj) {
						cities.push(idx);
						numToCities.push({
							value : obj,
							name : idx
						});
					});
					cities.push(data[1].city);
					numToCities.push({
						value : data[1].num,
						name : data[1].city
					});
					console.log(cities);
					console.log(numToCities);
					// 使用刚指定的配置项和数据显示图表。
					placeChart.setOption(option_2, true);
				},
				error : function(a, b, c) {
					console.log(a);
					console.log(b);
					console.log(c);
				}
			});
		}

		// 基于准备好的dom，初始化echarts实例
		var placeChart = echarts.init(document.getElementById('chart_2'));

		// 指定图表的配置项和数据
		var option_2 = {

			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : cities
			},
			series : [ {
				name : '访问来源',
				type : 'pie',
				radius : '80%',
				center : [ '50%', '50%' ],
				data : numToCities,
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			} ]
		};
	</script>
	</div>
	<!-- 3 -->
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main chart_div" id="2" style="display:none;">
		<div class="chart_table">
			<label>请选择数据年份</label>
			<select id="year_3">
		    	<option>2016</option>
		    	<option>2017</option>
		    </select>
		    <input class="btn btn-info" value="展示" id="dayBtn" onclick="selectCountYear()" />
	    </div>
		<div  id="chart_3" style="width: 1000px;height: 800px;" class="chart" ></div> 
	
		<script type="text/javascript">
		function selectCountYear(){
			//指定图标的配置和数据
	    	var base = +new Date(2017, 4, 1);
			var oneDay = 24 * 3600 * 1000;
			var date = [];
			var data = [Math.random() * 300];

			for (var i = 1; i < 200; i++) {
	    		var now = new Date(base += oneDay);
	   	   	 	date.push([now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'));
	    		data.push(Math.round((Math.random() - 0.5) * 20 + data[i - 1]));
			}
		    var dayChart = echarts.init(document.getElementById('chart_3')); 
			option_3 = {
	   			 tooltip: {
	       		 	trigger: 'axis',
	        	 	position: function (pt) {
	             		return [pt[0], '10%'];
	        		}
	   			 },
	    		title: {
	        		left: 'center',
	        		text: '每日订票量变化图',
	   			 },
	    		toolbox: {
	        		feature: {
	          		   dataZoom: {
	               	   yAxisIndex: 'none'
	           		 },
	           		 	restore: {},
	            		saveAsImage: {}
	        		}
	   			 },
	   			 xAxis: {
	       			 type: 'category',
	       			 boundaryGap: false,
	        		 data: date
	   			 },
	   			 yAxis: {
	       			 type: 'value',
	        		 boundaryGap: [0, '100%']
	    		},
	    		dataZoom: [{
	       			 type: 'inside',
	        		 start: 0,
	        		 end: 10
	   			 }, {
	        		 start: 0,
	        		 end: 10,
	       			 handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
	        		 handleSize: '80%',
	       			 handleStyle: {
		           		 color: '#fff',
		            	 shadowBlur: 3,
		            	 shadowColor: 'rgba(0, 0, 0, 0.6)',
		           		 shadowOffsetX: 2,
		            	 shadowOffsetY: 2
	       			}
	   			 }],
	    		series : [
	              {
	                  name:'订票量',
	                  type:'line',
	                  data:[],
	                  markPoint : {
	                      data : [
	                          {type : 'max', name: '最大值'}
	                      ]
	                  },
	                  markLine : {
	                      data : [
	                          {type : 'average', name: '平均值'}
	                      ]
	                  }
	              }
	          ]
			};   
		    //使用制定的配置项和数据显示图表
		    dayChart.setOption(option_3,true);
			$.ajax({
				url : "<%=basePath%>admin/count",
			    type : "POST",
			    data : {
			        countByYear : $('#year_3').val()
			     },
				success : function(result){
					option_3.series[0].data=result;
					dayChart.setOption(option_3,true);
			}

			})
		}
		
    	
    </script>
    </div>
	<!-- 4 -->	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main chart_div" id="3" style="display:none">
		<div class="chart_table" id="typeTable">
			<select id="departure" ></select>
			<select id="arrival" ></select>
			<input type="date" id="date">
			<input class="btn btn-info" value="展示" id="typeBtn" onclick="change()" />
		</div>
		<div id="chart_4" class="chart" style="width: 1000px; height: 800px;"></div>
		<script type="text/javascript">
		function GetDay(days)   
	    {   
	        var today=new Date($('#date').val().replace(/-/,"/")) ;      
	        var yesterday_milliseconds=today.getTime()-1000*60*60*24*days;     
	        var yesterday=new Date();      
	        yesterday.setTime(yesterday_milliseconds);                  
	        var strYear=yesterday.getFullYear(); 
	        var strDay=yesterday.getDate();   
	        var strMonth=yesterday.getMonth()+1; 
	   
	        if(strMonth<10)   
	        {   
	            strMonth="0"+strMonth;   
	        }   
	        var strYesterday=strMonth+"-"+strDay;   
	        return strYesterday;   
	    }
		function change(){
			// 基于准备好的dom，初始化echarts实例
			option_4 = {
				tooltip : {
					trigger : 'axis',
					axisPointer : { // 坐标轴指示器，坐标轴触发有效
						type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
					}
				},
				legend : {
					data : [ '高铁', '动车', '普快' ]
				},
				grid : {
					left : '3%',
					right : '4%',
					bottom : '3%',
					containLabel : true
				},
				xAxis : {
					type : 'value'
				},
				yAxis : {
					type : 'category',
					data : []
				},
				series : [ {
					name : '高铁',
					type : 'bar',
					stack : '总量',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : []
				}, {
					name : '动车',
					type : 'bar',
					stack : '总量',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : []
				}, {
					name : '普快',
					type : 'bar',
					stack : '总量',
					label : {
						normal : {
							show : true,
							position : 'insideRight'
						}
					},
					data : []
				}, ]
			};
			var typeChart = echarts.init(document.getElementById('chart_4'));
			typeChart.setOption(option_4);
				 $.ajax({
			         type : "post",
			         async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
			         url : "<%=basePath %>getSeatInfo",    //请求发送到TestServlet处
			         data : {"departure":$('#departure').val(),
			        	 	"arrival":$('#arrival').val(),
			        	 	"date":$('#date').val()
			        	 },
			         dataType : "json",        //返回数据形式为json
			         success : function(result) {
			             //请求成功时执行该函数内容，result即为服务器返回的json对象
			             for(var i=0;i<3;i++){
			            	 option_4.series[i].data=[];
			             }
			             $.each(result,function(index,content){
							/* console.log(index);
							console.log(content);
							console.log(data[index]); */
							typeChart.hideLoading();
							for(var i in content){	
								 option_4.series[index].data.push(content[i]);
							}
						}) 
						var yaxis=[];
			            option_4.yAxis.data=[];
			            for(var i=0;i<7;i++){
			            	option_4.yAxis.data.push(GetDay(i));
			            }
						
						typeChart.setOption(option_4);
			        },
			         error : function(errorMsg) {
			             //请求失败时执行该函数
			         alert("图表请求数据失败!");	        
			         }
			    })
	    }
		</script>

		<script>
			for(var i=0;i<citys.length;i++){
				$("#departure").append("<option value='"+citys[i]+"'>"+citys[i]+"</option>");
			}
			for(var i=0;i<citys.length;i++){
				$("#arrival").append("<option value='"+citys[i]+"'>"+citys[i]+"</option>");
			}
		</script>
	</div>

    
	<!-- 按钮控制 -->
	<script type="text/javascript">

		$('.select_btn').click(function(){
			var selected = this.value;
			for (var i=0;i<4;i++)
			{
				if(selected==i){
					document.getElementById(i).style.display="block";
				}
				else{
					document.getElementById(i).style.display="none";
				}
			}
		})

	</script>
	</div>
	</body>
</html>