<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath(); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EmCare - company.com</title>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/css/main.css">
	<jsp:include page="../jsp/component/js_include.jsp"></jsp:include>
</head>

<body>

<jsp:include page="component/head.jsp"></jsp:include>


<div class="middleContent ">

<div class="leftC">

<div class="title">
	<div style="float: left;"><b>All Voices</b></div>
	
	<div class="pageBar" style="margin-top: 0px; float: right;position: relative;top: 5px;">
		<jsp:include page="./component/pagination.jsp"></jsp:include>
	</div>
</div>



<br>

<s:iterator value="voices" id="voice">
<div class="data">
	<table >
		<tr >
			<td>
				<div class="voteInfo">
					<b><s:property value="voice.agreeCount + voice.disagreeCount" /></b><br >
					<span >votes</span>
					<s:if test="voice.status != 6">
						<div style="background-color: orange;">
							<b>OPEN</b>			
						</div>
					</s:if>
					<s:else>
						<div>
							<b>CLOSE</b>			
						</div>
					</s:else>					
				</div>
				<div class="viewNum"><s:property value="voice.viewCount" /> views</div>
			</td>
			<td valign="top">
				<div class="voiceDetailTitle">
					<b> <a href="<%=basePath %>/showVoicesDetail/<s:property value="voice.voiceId" />.htm"><s:property value="voice.shortTitle" escape="false" /></a></b><br>
					<div class="v_content" style="height: 30px;overflow: hidden;"> <s:property value="voice.content" escape="false"/>
					</div>
				</div>		
				<br >		
				<div>		
					<div class="authorInfo">			
						<table >
							<tr>
								<td rowspan="2"><img src="<%=basePath %>/images/ano.png" width="32" height="32"></td>
								<td style="font-size: 12px; color: #999999;">
									<s:if test="voice.signature != null && !voice.signature.trim().equals('')">
										<s:property value="voice.signature" />
									</s:if>
									<s:else>
										anonymous									
									</s:else>
								
								</td>
							</tr>
							<tr >
								<td style="font-size: 12px; color: #999999;">
									<b><s:date name="voice.submitTime" format="  MMM dd h:mm a"/></b>
								</td>
							</tr>
						</table>	
					</div>				
				</div>				
				<div class="voiceTypeInfo">
					<a class="someType"><s:property value="voice.type.name" /></a>
				</div>				
			</td>
		</tr>
	</table>
</div>
</s:iterator>

<div class="footPageBar">
	<div class="pageBar" style="margin-top: 0px; float: right;position: relative;top: 5px;">
		<jsp:include page="./component/pagination.jsp"></jsp:include>
	</div>
</div>
<br><br><br>

</div>

<jsp:include page="component/right.jsp"></jsp:include>
</div>

<jsp:include page="component/foot.jsp"></jsp:include>

</body>
</html>