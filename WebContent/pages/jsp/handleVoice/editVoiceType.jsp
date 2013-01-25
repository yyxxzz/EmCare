<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String basePath = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Manage Manager</title>
<link rel="stylesheet" type="text/css"	href="<%=basePath %>/css/jira.webresources global-static.css"	media="screen" />
<script language="javascript" type="text/javascript"
	src="/EmCare/js/My97DatePicker/WdatePicker.js"></script>
<script src="/EmCare/js/jquery/jquery-1.8.2.js"></script>
<script src="/EmCare/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript">

function updateVoiceType() {
	
	 document.getElementById("updateVoiceTypeForm").submit();
	 
}

</script>

</head>
<body>



<form action="<%=basePath%>/admin/updateVoiceType" name="updateVoiceTypeForm" id="updateVoiceTypeForm" method="post">
<div class="content-body" style="max-height: 315px;">
		<table class="voiceTypeList" id="voiceTypeList" align="center">
						
						<s:iterator id="vtList" value="vtList" status="st" >
							<tr>
								<td>
									ID
								</td>
								<td>
									<input type="text" readonly="readonly" name="voiceType.id" value="<s:property value="id"/>"/>
								</td>
							</tr>
							<tr>
								<td>
									Type Name 
								</td>
								<td>
									<input type="text" name="voiceType.name" value="<s:property value="name"/>">									
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="2">
								<input type="button" value="Save" onclick="updateVoiceType()"/>
								<br>
								<s:property value="#request.typeError" />
							</td>
						</tr>
		</table>
</div>
</form>
</body>
</html>