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
	 <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
     <script src="<%=basePath %>/js/bootstrap/js/html5.js"></script>
    <![endif]-->
	<script type="text/javascript">
	
		$(document).ready(function(){
 			$("#uid").tooltip({
 				html:true,
 				placement:"right",
 				title:"<span style='font-size:16px;'>Input your 5 + 3 ID here...</span>"
 			}); 			
 			$("#submitButton").removeAttr("disabled");
 			
		}); 
		
		function showTips(){	
			
			var uid = $("#uid").val();
			$("#submitButton").attr("disabled","disabled");
			$("#hint").html($("#awaiting").html());
			$("#hint").show();
			
			$.ajax({
				type:"POST",
				url:"<%=basePath%>/postActiveRequest?username=" + uid,
				dataType:"json",
				success:function(data){
					if(data.error){
						$("#submitButton").removeAttr("disabled");
					}
					$("#hint").hide();
					$("#hint").html(data.msg);
					$("#hint").fadeIn("slow"); 
				}
			}); 			
		}
		
		function hideTips(){
			$("#hint").fadeOut("slow"); 
		}
	
	</script>
	
</head>

<body style="background-color: #F5F5F5;">

<div style="height: 50px; background-color: black;">
	
</div>
 
<div class="jumbotron" style="height: 260px;">
	<div class="container" >
		<h1>EMCARE</h1>
		<p>
			Please kindly type your 5 + 3 ID and click the below button to authorize your account.<br>
			A confirmation email will send to you soon.
		</p>
		<div id="awaiting" style="display: none;">
			<div class="progress progress-striped active" style="width: 226px; margin: 0 auto;">
    			<div class="bar" style="width: 100%;"></div>
    		</div>
		</div>

		<div class="hint">
			<div id="hint" style="display: none;"></div>
		</div>
		
		<input id="uid" type="text" style="padding: 10px; font-size: 20px; width: 203px; height: 20px;" ><br >
		<button id="submitButton" class="btn btn-large btn-primary" onclick="showTips();" >Authorize Your Account</button>
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