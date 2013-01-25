<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String basePath=request.getContextPath(); %>
<link href="/EmCare/css/all.css"rel="stylesheet">
<script src="/EmCare/js/jquery/jquery-1.8.2.js"></script>
 <script type="text/javascript" src="<%=basePath %>/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=basePath %>/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	    <link href="/EmCare/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	    <link href="/EmCare/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script>
	$(document).ready(function(){
		 $(".progress_btn").click(function(){
			if(!confirm("Are you sure to start processing this Voice?"))return;
			var voiceId = $(this).attr("id");
			$.get("<%=basePath%>/admin/startVoiceProgress",{voice:voiceId},function(data){
					if(data.success){
						window.location = window.location.href;
					}
			});
		});
		 
		 $(".resolve_btn").click(function(){
			 if(!confirm("Are you sure to resolve this Voice?"))return;
				var voiceId = $(this).attr("id");
				$.get("<%=basePath%>/admin/resolveVoice",{voice:voiceId},function(data){
						if(data.success){
							window.location = window.location.href;
						}
				});
		 });
		 
	});
	
	
	
</script>
<div id="notification" style="margin: 5px 5px 10px 50px;">
	<s:if test="voices.size() != 0">
		<span class="label label-important">
			You have <s:property value="resolvedSize"/> resolved voices need to process 
		 </span>
	</s:if>
</div>
<div id="solved_items" style="position: relative; left: 10px; top: 10px;">
	<table class="table table-striped table-bordered" style="margin:0 auto;">
		<tr>
			<th width="5%">Id</th>
			<th width="15%">Title</th>
			 <th width="10%">Status</th> 
			<th width="15%">Time</th>
			<th width="10%">Action</th>
		</tr>
		<s:iterator value="voices" var="voice">
			<tr>
				<td><s:property value="voice.voiceId" /></td>
				<td><s:property value="voice.title"/></td>
				<td><s:property value="voice.getStatusString()"/></td> 
				<td><s:date name="voice.submitTime" format="yyyy-MM-dd HH:mm"/></td>
				<td>
					<s:if test="voice.status<=2">
					<button class="progress_btn btn" id="<s:property value="voice.voiceId"/>">Start Progress</button>
					</s:if>
					<s:elseif test="voice.status<4">
					<button class="resolve_btn btn" id="<s:property value="voice.voiceId"/>">Resolve</button>
					</s:elseif>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
<div class="pageBar"  style="position: relative; left: 40%; top: 10px;">
	<jsp:include page="pagination.jsp"></jsp:include>
</div>
