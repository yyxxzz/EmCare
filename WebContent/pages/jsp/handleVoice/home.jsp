<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<title>Manager All Voice</title>
	<link href="${pageContext.request.contextPath}/css/all.css"rel="stylesheet">
</head>
	<frameset rows="15%,70%,15%" cols="100%" frameborder="no" >
		<frame scrolling="no" src="${pageContext.request.contextPath}/pages/jsp/handleVoice/top.jsp" /> 
		<frameset cols="15%,2.5%,*,1%">
			<frame src="${pageContext.request.contextPath}/pages/jsp/handleVoice/left.jsp"/>
			<frame />
			<frame style="overflow-x: hidden; overflow-y: auto; width: 80%" scrolling="no" name="mainFrame" src="${pageContext.request.contextPath}/admin/handleVoice"/>
			<frame />
		</frameset>
		<frame  scrolling="no" src="${pageContext.request.contextPath}/pages/jsp/handleVoice/footer.jsp" />
	</frameset>
</html>
