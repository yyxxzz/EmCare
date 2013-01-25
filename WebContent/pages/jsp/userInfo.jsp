<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EmCare - company.com</title>
	<script type="text/javascript" src="<%=basePath %>/js/jquery/jquery-1.8.2.js"></script>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/css/main.css">	
	
	<style type="text/css">	
		
		.rightContent{
			height: 650px;
		}
		
		.userInfoTitle{
			font-size: 30px;
		}
		
		.voiceContent{
			font-family:serif;
			font-size: 16px;
			line-height: 20px;
		}
		
	</style>
	
</head>

<body style="margin: 0px;">

<jsp:include page="component/head.jsp"></jsp:include>


<div class="middleContent ">
	<div class="leftC">
		<div class="title userInfoTitle"><b>Reset Password</b></div>
		<br><br>
		<div>
			&nbsp;&nbsp;&nbsp;<b style="font-size: 22px;">Old Password&nbsp;:&nbsp;</b><input type="text" style="font-size: 22px;"><br><br><br>
			<b style="font-size: 22px;">New Password&nbsp;:&nbsp;</b><input type="text" style="font-size: 22px;">
			
			<br/><br/><br/><br/>
			<input type="button" class="submitButton" value="Change Password">
		</div>
	</div>
	<jsp:include page="component/right.jsp"></jsp:include>
</div>

<jsp:include page="component/foot.jsp"></jsp:include>

</body>
</html>