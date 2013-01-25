<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String basePath = request.getContextPath(); %>
<link href="<%=basePath %>/css/all.css"rel="stylesheet">
<script src="<%=basePath %>/js/jquery/jquery-1.8.2.js"></script>
 <script type="text/javascript" src="<%=basePath %>/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=basePath %>/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
	 <link href="/EmCare/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	    <link href="/EmCare/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	       <script type="text/javascript" src="/EmCare/js/bootstrap/js/bootstrap.js"></script>
<script>
	$(document).ready(function(){
		$("a[name='game_preview']").fancybox({
			//'width'				: '75%',
			'height'			: 360,
			'centerOnScroll'    :true,
			'autoScale'			: false,
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'type'				: 'iframe',
			'onClosed'			:	function(){
				
			}
		});
		
		<%-- $(".reject").click(function(){
			if(!confirm("Are you sure to reject this Voice?"))return;
			var voiceId = $(this).attr("id");
			$.get("<%=basePath%>/admin/rejectVoice",{voice:voiceId},function(data){
					if(data.result){
						alert("reject success");
						window.location = window.location.href;
					}
					//console.log($("#"+voiceId));
					//$("#"+voiceId).attr("disable",true);
			});
		}); --%>
		
	});
	function submitForm(formId){
		
		$("#"+formId).submit();
	}
	 
	function assignVoice(obj) {
		//alert($(obj).parent().parent().attr("id"));
		$("#dialog").dialog("open");
		$("#dialog").dialog('option', 'title', "Assgin Voice");
	}

	function saveAssignToPeople() {
		$("#dialog").dialog("close");
	}
</script>
<div id="notification" style="margin: 5px 5px 10px 50px;">
	<s:if test="voices.size() != 0">
		<span class="label label-important">
			You have <s:property value="newVoiceSize"/> new voices need to process 
	    </span>
	</s:if>
</div>
<div id="new_items" style="position: relative; left: 10px; top: 10px;">
	<table class="table table-striped table-bordered">
		<tr>
			<th width="5%">id</th>
			<th width="15%">Title</th>
			<!-- <th width="40%">Content</th> -->
			<th width="15%">Time</th>
			<th width="20%">Action</th>
		</tr>
		<s:iterator value="voices" var="voice">
			<tr>
				<td><s:property value="voice.voiceId"/></td>
				<td><s:property value="voice.title"/></td>
				<%-- <td><s:property value="voice.content"/></td> --%>
				<td><s:date name="voice.submitTime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
				<!-- 	<button onclick="javescript:assignVoice(this)">assign</button> -->
				<s:if test="voice.hasAssigned()">
					<button disabled>assign</button>
				</s:if>
				<s:else><a class="btn" name="game_preview" href="<%=basePath%>/admin/loadAssignPage?voiceId=<s:property value="voice.voiceId"/>" >
				assign
				</a></s:else>
				<a data-target="#modal_<s:property value='voice.voiceId'/>" role="button" class="btn" data-toggle="modal">Reject</a>
					<%-- <button class="reject btn" id="<s:property value="voice.voiceId"/>">reject</button> --%>
				</td>
		<div id="modal_<s:property value='voice.voiceId'/>" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Reject Voice</h3>
	<small><s:property value="voice.title"/></small>  
  </div>
  <div class="modal-body">
    <form action="<%=basePath%>/admin/rejectVoice" method="post" id="subForm_<s:property value="voice.voiceId"/>">
          <p><small>Please write down the reason for rejecting this Voice.</small></p>
          <div class="input">
          	<input type="hidden" name="voice" value="<s:property value="voice.voiceId"/>" />
            <textarea name="comment" id="Message" class="textarea" placeholder="Comments" rows="8" style="width:80%;"></textarea>
          </div>
        <%--   <input type="submit" value="Submit" onclick="return closeVoice(<s:property value="voice.voiceId"/>);" /> <a href="#" class="close">Cancel</a> --%>
        </form>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-primary" onclick="submitForm('subForm_<s:property value="voice.voiceId"/>')">Save changes</button>
  </div>
</div>
			</tr>
		</s:iterator>
	</table>
</div>
<div class="pageBar" style="width: 50%; margin: 0px 500px;" >
	<jsp:include page="pagination.jsp"></jsp:include>
</div>
