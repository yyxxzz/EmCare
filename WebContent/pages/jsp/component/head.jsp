<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath(); %>

<div class="userInfo">
	<div class="toCenter">
		<div class="searchTop" id="searchTop">
			<input id="search_Image" type="image" src="<%=basePath %>/images/search.png" height="17" width="17"  />
			<input id="search_box" type="text"  value="search..." class="searchInput" />
		</div>
		<div class="welcome">Welcome</div>
	</div>
</div>

<div class="navigator toCenter">
	<img src="<%=basePath %>/images/logo1.png" width="219" height="52" class="logo">
		<a href="<%=basePath%>/voicelist/all.htm" <s:if test="category=='all' || category==null">style="background-color:#FF9900;"</s:if> >All Voices</a>
		<a href="<%=basePath%>/voicelist/open.htm" <s:if test="category=='open'">style="background-color:#FF9900;"</s:if> >Open</a>
		<a href="<%=basePath%>/voicelist/closed.htm" <s:if test="category=='closed'">style="background-color:#FF9900;"</s:if> >Closed</a>
		<a class="giveVoice" href="<%=basePath%>/newVoice/new.htm" <s:if test="category=='new'">style="background-color:#FF9900;"</s:if> >New Voice</a>
</div>