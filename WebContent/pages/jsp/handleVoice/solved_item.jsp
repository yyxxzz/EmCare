<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String basePath=request.getContextPath(); %>
<link href="/EmCare/css/all.css"rel="stylesheet">
<script src="/EmCare/js/jquery/jquery-1.8.2.js"></script>
 <script type="text/javascript" src="<%=basePath %>/js/fancybox/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="<%=basePath %>/js/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
 	<link rel='stylesheet' href='<%=basePath %>/js/popbox/popbox.css' type='text/css' media='screen'>
    <link href="/EmCare/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	   <link href="/EmCare/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet"> 
	   <script type="text/javascript" src="/EmCare/js/bootstrap/js/bootstrap.js"></script>
   <%-- <script type='text/javascript' charset='utf-8' src='<%=basePath %>/js/popbox/popbox.js'></script> --%>
<script>
	$(document).ready(function(){
		<%-- $(".closed_btn").click(function(){
			if(!confirm("Are you sure to close this Voice?"))return;
			var voiceId = $(this).attr("id");
			$.get("<%=basePath%>/admin/closeVoice",{voiceId:voiceId},function(data){
					if(data.result){
						alert("closed success");
						window.location = window.location.href;
					}
			});
		}); --%>
	    //  $('.popbox').popbox();
	}); 
	
	function closeVoice(voiceId){
		//console.log("trying to clase voice "+voiceId);
		//console.log($("#subForm_"+voiceId).serialize());
		var postData = $("#subForm_"+voiceId).serialize();
		$.post("<%=basePath%>/admin/closeVoice",postData,function(data){
			if(data.result){
				alert("closed success");
				window.location = window.location.href;
			}
		},"json");
		return false;
	}
	
	function submitForm(formId){
		$("#"+formId).submit();
	}
	
	
	

</script>
<div id="notification" style="margin: 5px 5px 10px 50px;">
	<s:if test="voices.size() != 0">
		<span class="label label-important">
			You have <s:property value="resolvedSize"/> resolved voices need to process 
		 </span>
	</s:if>
</div>
<div id="solved_items" style="position: relative; left: 10px; top: 10px;">
	<table class="table table-striped table-bordered">
		<tr>
			<th width="5%">id</th>
			<th width="15%">Title</th>
			 <th width="10%">Status</th> 
			<th width="15%">Time</th>
			<th width="20%">Action</th>
		</tr>
		<s:iterator value="voices" var="voice">
			<tr>
				<td><s:property value="voice.voiceId"/></td>
				<td><s:property value="voice.title"/></td>
				<td><s:property value="voice.getStatusString()"/></td> 
				<td><s:date name="voice.submitTime" format="yyyy-MM-dd HH:mm"/></td>
				<td>
	
<div id="modal_<s:property value='voice.voiceId'/>" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Close Voice</h3>
	<small><s:property value="voice.title"/></small>  
  </div>
  <div class="modal-body">
    <form action="<%=basePath%>/admin/closeVoice" method="post" id="subForm_<s:property value="voice.voiceId"/>">
          <p><small>Please write down the solution for the Voice.</small></p>
          <div class="input">
          	<input type="hidden" name="voice" value="<s:property value="voice.voiceId"/>" />
            <textarea name="comment" id="Message" placeholder="Comments"></textarea>
          </div>
        <%--   <input type="submit" value="Submit" onclick="return closeVoice(<s:property value="voice.voiceId"/>);" /> <a href="#" class="close">Cancel</a> --%>
        </form>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-primary" onclick="submitForm('subForm_<s:property value="voice.voiceId"/>')">Save changes</button>
  </div>
</div>
	<a data-target="#modal_<s:property value='voice.voiceId'/>" role="button" class="btn" data-toggle="modal">Close</a>
	<%-- <div class="popbox">
	<button class="closed_btn" id="<s:property value="voice.voiceId"/>">Close</button>
	<button id="<s:property value="voice.voiceId"/>" class='open' style="cursor:pointer;">Close</button>
	<div class='collapse'>
     <div class='box'>
     <div class='arrow'></div>
	 <div class='arrow-border'></div>
        <form action="<%=basePath%>/admin/closeVoice" method="post" id="subForm_<s:property value="voice.voiceId"/>">
          <p><small>Please write down the solution for the Voice.</small></p>
          <div class="input">
          	<input type="hidden" name="voice" value="<s:property value="voice.voiceId"/>" />
            <textarea name="comment" id="Message" placeholder="Comments"></textarea>
          </div>
          <input type="submit" value="Submit" onclick="return closeVoice(<s:property value="voice.voiceId"/>);" /> <a href="#" class="close">Cancel</a>
        </form>
      </div>
    </div>
    </div> --%>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
<div class="pageBar"  style="position: relative; left: 40%; top: 10px;">
	<jsp:include page="pagination.jsp"></jsp:include>
</div>
