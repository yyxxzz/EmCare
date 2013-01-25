<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/js/kandytabs/kandytabs.css" />
 <link href="/EmCare/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	    <link href="/EmCare/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="/EmCare/js/jquery/jquery-1.8.2.js" type="text/javascript"></script>
<script src="/EmCare/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/hightcharts/highcharts.src.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/kandytabs/kandytabs.pack.js" type="text/javascript"></script>
<title>Voice Report</title>
<script type="text/javascript">
	$(document).ready(function(){
		Highcharts.getOptions().colors = $.map(Highcharts.getOptions().colors, function(color) {
		    return {
		        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
		        stops: [
		            [0, color],
		            [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
		        ]
		    };
		});
		$("#barChart").KandyTabs({
			action:"slide",
			trigger:"click"
		});
	});
	
	function validate(){
		return validTime($("#startTime").val(),$("#endTime").val());
	}
	
	function validTime(startTime,endTime){
		var start = new Date(startTime);
		var end = new Date(endTime);
		if(start.getTime() > end.getTime()) { 
		  return false;
		}else{
		  return true;
		}
		return false;
	};
	
	function generateReport(){	
		if(validate()){
			generateBarChartByState();
			generatePieChartByState();
			generateBarChartByType();
			generatePieChartByType();
			//generateAreaChartByState();
		}else{
			alert("wrong time!");
			return;
		}
	};
	
	function generateAreaChartByState(){
		$.ajax({
			url:"${pageContext.request.contextPath}/admin/generateAreaChart",
			dataType:"json",
			type:"post",
			data:{
				start:$("#startTime").val(),
				end:$("#endTime").val(),
				type:"state"
			},
			success:function(json){
				var option = {
						chart: {
			                renderTo: 'stateAreaChart',
			                type: 'area'
			            },
			            title: {
			                text: 'Voice State Report'
			            },
			            xAxis: {
			                type: 'datetime',
			                tickInterval: 7 * 24 * 3600 * 1000, // one week
			                tickWidth: 0,
			                gridLineWidth: 1,
			                labels: {
			                    align: 'left',
			                    x: 3,
			                    y: -3
			                }
			            },
			           /*  plotOptions: {
			                area: {
			                    pointStart: 1940,
			                    marker: {
			                        enabled: false,
			                        symbol: 'circle',
			                        radius: 2,
			                        states: {
			                            hover: {
			                                enabled: true
			                            }
			                        }
			                    }
			                }
			            }, */
			            tooltip: {
			                formatter: function() {
			                    return ''+
			                        this.x +': '+ this.y;
			                }
			            },
			            credits: {
			                enabled: false
			            },
			            series: []
				};
				var cate = getXCategories();
				for(var i = 0; i < cate.length; i++){
					option.xAxis.categories.push(cate[i]);
				}
				for(var i=0; i < json.length; i++){
					option.series.push({
						name:json[i].name,
						data:json[i].data
					});
				}
				var barChartByState = new Highcharts.Chart(option);
			}
			});
	}
	
	function generateBarChartByState(){
		$.ajax({
			url:"${pageContext.request.contextPath}/admin/generatBarChart",
			dataType:"json",
			type:"post",
			data:{
				start:$("#startTime").val(),
				end:$("#endTime").val(),
				type:"state"
			},
			success:function(json){				
				var option = {
						chart: {
			                renderTo: 'stateBarChart',
			                type: 'column'
			            },
			            title: {
			                text: 'Voice State Report'
			            },
			            xAxis: {
			            	categories: []
			            },
			            
			            legend: {
			            	backgroundColor: '#FFFFFF',
			                reversed: true
			            },
			            tooltip: {
			                formatter: function() {
			                    return ''+
			                        this.x +': '+ this.y;
			                }
			            },
			            credits: {
			                enabled: false
			            },
			            series: []
				};
				var cate = getXCategories();
				for(var i = 0; i < cate.length; i++){
					option.xAxis.categories.push(cate[i]);
				}
				for(var i=0; i < json.length; i++){
					option.series.push({
						name:json[i].name,
						data:json[i].data
					});
				}
				var barChartByState = new Highcharts.Chart(option);
			}
		});
	};
	function getXCategories(){
		var categories = [];
		var start = $("#startTime").val();
		var end = $("#endTime").val();
		if(start.substring(0,4) == end.substring(0,4)){
			categories = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
		}else{
			var startYear = parseInt(start.substring(0,4));
			var endYear = parseInt(end.substring(0,4));
			for(var i = 0; i <= endYear - startYear; i++){
				categories.push((startYear + i).toString());
			}
		}
		return categories;
	};
	function generateBarChartByType(){
		$.ajax({
			url:"${pageContext.request.contextPath}/admin/generatBarChart",
			dataType:"json",
			type:"post",
			data:{
				start:$("#startTime").val(),
				end:$("#endTime").val(),
				type:"type"
			},
			success:function(json){				
				var option = {
						chart: {
			                renderTo: 'typeBarChart',
			                type: 'column'
			            },
			            title: {
			                text: 'Voice Type Report'
			            },
			            xAxis: {
			            	categories: []
			            },
			            
			            legend: {
			            	backgroundColor: '#FFFFFF',
			                reversed: true
			            },
			            tooltip: {
			                formatter: function() {
			                    return ''+
			                        this.x +': '+ this.y;
			                }
			            },
			            credits: {
			                enabled: false
			            },
			            series: []
				};
				var cate = getXCategories();
				for(var i = 0; i < cate.length; i++){
					option.xAxis.categories.push(cate[i]);
				}
				for(var i=0; i < json.length; i++){
					option.series.push({
						name:json[i].name,
						data:json[i].data
					});
				}
				var barChartByType = new Highcharts.Chart(option);
			}
		});
	};
	function generatePieChartByState(){
		$.ajax({
			url:"${pageContext.request.contextPath}/admin/generatPieChart",
			dataType:"json",
			type:"post",
			data:{
				start:$("#startTime").val(),
				end:$("#endTime").val(),
				type:"state"
			},
			success:function(json){				
				var option = {
						chart: {
			                renderTo: 'statePieChart',
			                plotBackgroundColor: null,
			                plotBorderWidth: null,
			                plotShadow: false
			            },
			            title: {
			                text: 'Voice State Share'
			            },			            
			            tooltip: {
			            	pointFormat: '{series.name}: <b>{point.percentage}%</b>',
			            	percentageDecimals: 1
			            },
			            plotOptions: {
			                pie: {
			                    allowPointSelect: true,
			                    cursor: 'pointer',
			                    dataLabels: {
			                        enabled: true,
			                        color: '#000000',
			                        connectorColor: '#000000',
			                        formatter: function() {
			                            return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
			                        }
			                    }
			                }
			            },
			            series: []
				};				
				var series = {
						type:json.type,
						name:json.name,
						data:[]
				};
				for(var i=0; i < json.data.length; i++){
					if(json.data[i].name != undefined){
						series.data.push({
							name:json.data[i].name,
							y:json.data[i].y,
							sliced:json.data[i].sliced,
							selected:json.data[i].selected
						});
					}else{
						series.data.push(json.data[i]);	
					}					
				}
				option.series.push(series);
				var statePieChart = new Highcharts.Chart(option);
			}
		});
	};
	function generatePieChartByType(){
		$.ajax({
			url:"${pageContext.request.contextPath}/admin/generatPieChart",
			dataType:"json",
			type:"post",
			data:{
				start:$("#startTime").val(),
				end:$("#endTime").val(),
				type:"type"
			},
			success:function(json){				
				var option = {
						chart: {
			                renderTo: 'typePieChart',
			                plotBackgroundColor: null,
			                plotBorderWidth: null,
			                plotShadow: false
			            },
			            title: {
			                text: 'Voice Type Share'
			            },			            
			            tooltip: {
			            	pointFormat: '{series.name}: <b>{point.percentage}%</b>',
			            	percentageDecimals: 1
			            },
			            plotOptions: {
			                pie: {
			                    allowPointSelect: true,
			                    cursor: 'pointer',
			                    dataLabels: {
			                        enabled: true,
			                        color: '#000000',
			                        connectorColor: '#000000',
			                        formatter: function() {
			                            return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
			                        }
			                    }
			                }
			            },
			            series: []
				};				
				var series = {
						type:json.type,
						name:json.name,
						data:[]
				};
				for(var i=0; i < json.data.length; i++){
					if(json.data[i].name != undefined){
						series.data.push({
							name:json.data[i].name,
							y:json.data[i].y,
							sliced:json.data[i].sliced,
							selected:json.data[i].selected
						});
					}else{
						series.data.push(json.data[i]);	
					}					
				}
				option.series.push(series);
				var typePieChart = new Highcharts.Chart(option);
			}
		});
	};
</script>
</head>
<body>
	<div id="filter">
		<span class="label">Start Date: </span>&nbsp;<input id="startTime" type="text" class="Wdate" onClick="javascript:WdatePicker()"> 
		<span class="label">End Date:</span>&nbsp;<input id="endTime" type="text" class="Wdate" onClick="javascript:WdatePicker()"> 
		<input id="btnReport" class="btn btn-success" type="button" value="report" onclick="javascript:generateReport();">
	</div>
	<div id="barChart">
		<h3>state bar chart</h3>
		<div id="stateBarChart"></div>
		<h3>category bar chart</h3>
		<div id="typeBarChart"></div>
		<h3>state pie chart</h3>
		<div id="statePieChart"></div>
		<h3>category pie chart</h3>
		<div id="typePieChart"></div>
		<!-- <h3>state area chart</h3>
		<div id="stateAreaChart"></div> -->
	</div> 
	<!--<dl id="barChart">
		<dt>state bar chart</dt>
		<dd id="stateBarChart"></dd>
		<dt>type bar chart</dt>
		<dd id="typeBarChart"></dd>
		<dt>state pie chart</dt>
		<dd id="statePieChart"></dd>
		<dt>state pie chart</dt>
		<dd id="typePieChart"></dd>
	</dl>-->
</body>
</html>