<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath(); %>
<link href="/EmCare/js/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="/EmCare/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="<%=basePath %>/css/admin.css">

<div class="userInfo"></div>

<div class="navigator">
	<div style="float: left;margin-left: 10px;">
		<img src="<%=basePath %>/images/logo1.png" width="219" height="52" class="logo">
	</div>
	
	<div style="float: right;margin-top: 30px; margin-right: 30px;">
		<img alt="current user" src="/EmCare/images/clients.gif">
		${userInfo.username} | 
		<img alt="Logout" src="/EmCare/images/logout.gif"> <a href="javascript:window.parent.location.href='${pageContext.request.contextPath}/login!logout'">logout</a>
	</div>
</div>















	