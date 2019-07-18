<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>铁路路线压力图</title>
		<script type="text/javascript" src="<%=basePath%>admin/js/echarts.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>admin/js/china.js" charset="utf-8"></script>
		<script type="text/javascript" src="<%=basePath%>assets/js/jquery-3.1.1.js"></script>
	</head>

	<body>
		<h1>路线的颜色反应该线路的压力大小</h1>
		<div id="main" style="height: 800px; width:800px;"></div>
		<script type="text/javascript">
			var myChart = echarts.init(document.getElementById('main'));
			var geoCoordMap = {
					'格尔木': [94.928484,36.406404],
					'济南': [117.119999,36.651216],
					'昆明': [102.832891,24.880095],
					'徐州': [117.284124,34.205768],
					'包头': [109.840347,40.657449],
					'株洲': [113.134002,27.827550],
					'喀什': [75.989755,39.470400],
					'重庆': [106.551556,29.563009],
					'石家庄': [114.514862,38.042309],
					'贵阳': [106.630153,26.647661],
					'长春': [125.323544,43.817071],
					'哈尔滨': [126.534967,45.803775],
					'九龙': [114.188950,22.308180],
					'吐鲁番': [89.189651,42.951382],
					'天津': [117.200983,39.084158],
					'合肥': [117.227239,31.820586],
					'襄樊': [112.122550,32.009000,],
					'大连': [121.614682,38.914003],
					'杭州': [120.155070,30.274084],
					'南昌': [115.858197,28.682892],
					'成都': [104.066541,30.572269],
					'兰州': [103.834303,36.061089],
					'上海': [121.473701,31.230416],
					'沈阳': [123.431474,41.805698],
					'长沙': [112.938814,28.228209],
					'乌鲁木齐': [87.616848,43.825592],
					'武汉': [114.305392,30.593098],
					'宝鸡': [107.237974,34.361979],
					'呼和浩特': [111.749180,40.842585],
					'西宁': [101.778228,36.617144],
					'拉萨': [91.140856,29.645554],
					'广州': [113.264434,23.129162],
					'大理': [100.267638,25.606486],
					'银川': [106.230909,38.487193],
					'连云港': [119.221611,34.596653],
					'西安': [108.940174,34.341568],
					'郑州': [113.625368,34.746599],
					'库尔勒': [86.174633,41.725892],
					'北京': [116.407526,39.904030],
					'南京': [118.796877,32.060255],
			};

			<c:forEach items="${items}" var="item" varStatus="status">
				var data${status.index} = ${item};
			</c:forEach>
			
			var planePath = 'path://M1705.06';

			var convertData = function(data) {
				var res = [];
				for(var i = 0; i < data.length; i++) {
					var dataItem = data[i];
					var fromCoord = geoCoordMap[dataItem[0].name];
					var toCoord = geoCoordMap[dataItem[1].name];
					if(fromCoord && toCoord) {
						res.push({
							fromName: dataItem[0].name,
							toName: dataItem[1].name,
							coords: [fromCoord, toCoord]
						});
					}
				}
				return res;
			}
			
			/*获得当前路线的样式参数*/
			var getOption = function(data) {
				var options = [];
				options.push(data[2].color);
				options.push(data[2].width);
				return options;
			}
			var series = [];
			[
				<c:forEach items="${dataNames}" var="name" varStatus="status">
					["压力图",data${status.index}],
				</c:forEach>
				
			].forEach(function(item, i) {
				series.push({
					name: item[0],
					type: 'lines',
					zlevel: 1,
					effect: {
						show: true,
						period: 6,
						trailLength: 0.7,
						color: '#fff',
						/*运动点的大小（宽度）*/
						symbolSize: getOption(item[1][0])[1] / 2
					},
					lineStyle: {
						normal: {
							width: 0,
							curveness: 0.2
						}
					},
					data: convertData(item[1])
				}, {
					name: item[0],
					type: 'lines',
					zlevel: 2,
					symbol: ['none', 'arrow'],
					symbolSize: 10,
					effect: {
						show: true,
						period: 6,
						trailLength: 0,
						symbol: planePath,
						symbolSize: 15
					},
					/*两地点间实线的宽度及颜色*/
					lineStyle: {
						normal: {
							width: getOption(item[1][0])[1],
							opacity: 0.6,
							curveness: 0.2,
							color: getOption(item[1][0])[0]
						}
					},
					data: convertData(item[1])
				}, {
					name: item[0],
					type: 'effectScatter',
					coordinateSystem: 'geo',
					zlevel: 2,
					rippleEffect: {
						brushType: 'stroke'
					},
					/*地图上地点文字的样式*/
					label: {
						normal: {
							show: true,
							position: 'bottom',
							formatter: '{b}',
							textStyle: {
								fontSize: 14,
								color: 'white',
							}
						}
					},
					symbolSize: 3,
					data: item[1].map(function(dataItem) {
						return {
							name: dataItem[1].name,
							value: geoCoordMap[dataItem[1].name]
						};
					})
				});
			});
			option = {
				backgroundColor: '#404a59',
				title: {
					text: '铁路线路压力图',
					left: 'center',
					textStyle: {
						color: '#fff'
					}
				},
				geo: {
					map: 'china',
					label: {
						emphasis: {
							show: false
						}
					},
					/*是否启用鼠标滚轮缩放和拖拽操作 */
					roam: true,
					/*地图放大级别*/
					zoom: 1.0,
					/*图形的中心点，用经纬度定位*/
					//center: [118.8013, 32.0654],
					itemStyle: {
						normal: {
							areaColor: '#323c48',
							borderColor: '#404a59'
						},
						emphasis: {
							areaColor: '#2a333d'
						}
					}
				},
				series: series
			};
			myChart.setOption(option);
		</script>
	</body>

</html>