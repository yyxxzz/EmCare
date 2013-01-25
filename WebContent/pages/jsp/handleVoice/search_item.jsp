<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String basePath = request.getContextPath(); %>
<link href="/EmCare/css/ui-lightness/jquery-ui-1.9.1.custom.css"rel="stylesheet">
<link href="/EmCare/css/all.css"rel="stylesheet">
<script language="javascript" type="text/javascript" src="/EmCare/js/My97DatePicker/WdatePicker.js"></script>
<script src="/EmCare/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
 <link href="/EmCare/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	    <link href="/EmCare/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script>
$(document).ready(function() {
	$("a[name='voiceView']").fancybox({
		'width'				: '50%',
		'height'			: 500,
		'centerOnScroll'    :true,
		'autoScale'			: false,
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'type'				: 'iframe'
	});
});
</script>
<div id="searchBar">
	<form action="<%=basePath%>/admin/handleVoice!searchVoices" method="post" >
		<table class="table table-striped table-bordered">
		<tr>
			<td>
				Id : 
			</td>
			<td>
				<input type="text" name="searchOption.voiceId" value="${searchOption.voiceId}"/>
			</td>
			<td>
				Start Date : 
			</td>
			<td>
				<input type="text" name="searchOption.startTime" class="Wdate" onClick="javascript:WdatePicker()" value="${searchOption.startTime}" /> 
			</td>
			<td>
				End Date: 
			</td>
			<td>
				<input type="text" name="searchOption.endTime" class="Wdate" onClick="javascript:WdatePicker()" value="${searchOption.endTime}" />
			<td>
				Title : 
			</td>
			<td>
				<input type="text" name="searchOption.title"  value="${searchOption.title}"/>
			</td>
		</tr>
		<tr>
			<td>
				Status :
			</td>
			<td>
				<select name="searchOption.status" >
					<option></option>
					<s:if test="searchOption.status ==1">
						<option value="1" selected="selected">OPEN</option>
					</s:if>
					<s:else>
						<option value="1">OPEN</option>
					</s:else>
					<s:if test="searchOption.status ==2">
						<option value="2" selected="selected">ASSIGNED</option>
					</s:if>
					<s:else>
						<option value="2">ASSIGNED</option>
					</s:else>
					<s:if test="searchOption.status ==3">
						<option value="3" selected="selected">PENDING</option>
					</s:if>
					<s:else>
						<option value="3">PENDING</option>
					</s:else>
					<s:if test="searchOption.status ==4">
						<option value="4" selected="selected">RESOLVED</option>
					</s:if>
					<s:else>
						<option value="4">RESOLVED</option>
					</s:else>
					<s:if test="searchOption.status ==5">
						<option value="5" selected="selected">REJECTED</option>
					</s:if>
					<s:else>
						<option value="5">REJECTED</option>
					</s:else>
					<s:if test="searchOption.status ==6">
						<option value="6" selected="selected">CLOSED</option>
					</s:if>
					<s:else>
						<option value="6">CLOSED</option>
					</s:else>
				</select>
			</td>
			<td>
				Type : 
			</td>
			<td>
				
				<select name="searchOption.type">
					<option></option>
					<s:iterator value="voiceTypes" var="type">
						<s:if test="searchOption.type == type.id">
							<option value="${type.id}" selected="selected">${type.name}</option>
						</s:if>
						<s:else>
							<option value="${type.id}">${type.name}</option>
						</s:else>
						
					</s:iterator>
				</select>
			</td>
			<td>
				Assign To:
			</td>
			<td>
				<select name="searchOption.assignTo" >
					<option></option>
					<s:iterator value="personLMs" var="person">
						<s:if test="searchOption.assignTo == person.personId">
							<option value="${person.personId}" selected="selected">${person.username}</option>
						</s:if>
						<s:else>
							<option value="${person.personId}">${person.username}</option>
						</s:else>
					</s:iterator>
				</select>
			</td>
			<td>
			</td>
			<td>
				<input class="btn" type="submit" value="Search">
			</td>
		</tr>
		</table>
	</form>
</div>
<hr/>
<div id="search_items" style="position: relative; left: 10px; top: 10px;">
	<table class="table table-striped table-bordered">
		<tr>
			<th width="5%">id</th>
			<th>Title</th>
			<!-- <th width="40%">Content</th> -->
			<th width="15%">Time</th>
			<th width="5%">Assign To</th>
			<th width="5%">Status</th>
			<th width="5%">type</th>
			<th width="10%">Action</th>
			
		</tr>
		<s:iterator value="voices" var="voice">
			<tr class="" id="<s:property value="voice.voiceId"/>">
				<td><s:property value="voice.voiceId"/></td>
				<td><s:property value="voice.title"/></td>
				<%-- <td><s:property value="voice.content"/></td> --%>
				<td><s:date name="voice.submitTime" format="yyyy-MM-dd HH:mm"/></td>
				<td><s:property value="voice.assignment.assignTo.username"/></td>
				<%-- <td><s:property value="voice.status"/></td> --%>
				<td>
					<s:if test="voice.status == 1">
						OPEN
					</s:if>
					<s:if test="voice.status == 2">
						ASSIGNED
					</s:if>
					<s:if test="voice.status == 3">
						PENDING
					</s:if>
					<s:if test="voice.status == 4">
						RESOLVED
					</s:if>
					<s:if test="voice.status == 5">
						REJECTED
					</s:if>
					<s:if test="voice.status == 6">
						CLOSED
					</s:if>
				</td>
				<th><s:property value="voice.type.name"/></th>
				<td>
					<a class="btn" name="voiceView" href="<%=basePath%>/admin/handleVoice!showVoiceDetail?searchOption.voiceId=<s:property value="voice.voiceId"/>" >
					View
					</a>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
<div class="pageBar" style="position: relative; left: 40%; top: 10px;">
	<jsp:include page="pagination.jsp"></jsp:include>
</div>

