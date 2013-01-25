<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EmCare - company.com</title>
	<link href="<%=basePath %>/css/authorize_page.css" type="text/css" rel="stylesheet">
	<link href="<%=basePath %>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<script src="<%=basePath %>/js/jquery/jquery-1.8.2.js"></script>
	<script src="<%=basePath %>/js/bootstrap/js/bootstrap.min.js"></script>
		
	<style type="text/css">
	
		#sec{
			color: #005580;
		}
	
	</style>
		
	<script type="text/javascript">
	
		var   i=5;
    	function changepage(){       
        	i=i-1;
        	if(i==0){
        		window.location="<%=request.getContextPath() %>";
        	}else if(i <0){
        		i = 5;
        	}
        	$("#sec").html(i);
   		}  
    	
    	
	
    	$(document).ready(function(){
    		setInterval("changepage()",1000);
		}); 
    	
	</script>
		
</head>

<body style="background-color: #F5F5F5;">

<div style="height: 50px; background-color: black;">
	
</div>
 
<div class="jumbotron" style="height: 260px;">
	<div class="container" >
		<h1>EMCARE</h1>
		<p>
			Congratulations! Your account in EmCare has been authorized!
		</p>
		<div class="hint">
			<div id="hint">
				Navigate to home page in <span id="sec">5</span> seconds.
				Click <a href="<%=request.getContextPath() %>">here</a> to enter EmCare home page. 
			</div>
		</div>
	</div>
</div>

<div class="footWord">
	<div style="font-size: 38.5px; padding: 30px;">
		<b >Care For Our People.</b>
	</div>
	
	<p class="fw1" style="margin: 0xp;">We would like to hear your voice, happiness, complaints, ideas or suggestions.</p>
</div>

<div style="text-align: center; padding: 30px;">
        <p>Designed and built with all the love in the world by <a href="mailto:company@company.com" target="_blank">@ULTIMA TEAM</a>.</p>
        <p><a>&copy; Excellence R&D company.</a></p>
</div>


</body>
</html>