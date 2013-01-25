<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath(); %>

<style>

.visitNum{
	font-size: 15px; 
	font-family: 	Arial,​Liberation Sans,​DejaVu Sans,​sans-serif;
	margin-left: 0px;
	color: 	#0080C6;
}

.visit{
	font-size: 16px; 
	font-family: 	Arial,​Liberation Sans,​DejaVu Sans,​sans-serif;
	margin-left: 20px;
}

</style>

<div style="display: none;">
	<s:property value="generateVoiceType()" />
</div>

<div class="rightContent">
	<table >
		<tr >
			<td align="center"><b class="summarycount"><s:property value="getVoiceTotal(category)" /></b></td>
		</tr>
		<tr >
			<td align="center">
				<span class="rsp">Voices 
					<s:if test="category == 'open'">
						<b>open</b>
					</s:if>
					<s:elseif test="category == 'closed'">
						<b>closed</b>
					</s:elseif>
					<s:else>
						<b>&nbsp;</b>
					</s:else>					
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<br>
				<img src="<%=basePath %>/images/company_logo.gif" style="position: relative; left: 25px;" />
			</td>
		</tr>
		<tr ><td height="10"></td></tr>
		<tr >
			<td align="left">
				<br>
				<span class="visit">visited</span>
				<span class="visitNum"><s:property value="#application.visitorAmount" /></span>
				
			</td>
		</tr>
		<tr ><td height="20"></td></tr>
		<tr >
			<td >
				<b class="voiceTypeTitle">Voice Type</b><br>
				<s:iterator value="voiceTypes">
					<br><a class="someType"><s:property value="name" /></a>&nbsp;x&nbsp;<b class="typeNum"><s:property value="voices.size()" /></b><br>
				</s:iterator>
			</td>
		</tr>
	</table>	
</div>