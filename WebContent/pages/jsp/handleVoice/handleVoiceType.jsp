<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String basePath = request.getContextPath(); %>
<link href="/EmCare/css/ui-lightness/jquery-ui-1.9.1.custom.css"
	rel="stylesheet">
	 <link href="/EmCare/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	    <link href="/EmCare/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script language="javascript" type="text/javascript"
	src="/EmCare/js/My97DatePicker/WdatePicker.js"></script>
<script src="/EmCare/js/jquery/jquery-1.8.2.js"></script>
<script src="/EmCare/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<script>


jQuery(function() {
	jQuery("#voiceTypeDialog").dialog({
		autoOpen: false,
		width: 400
	}); 
});


function addVoiceType(){
	$( "#voiceTypeDialog" ).dialog( "open" );
	$( "#voiceTypeDialog" ).dialog('option', 'title', "Add Voice Type" );
		
}

function saveVoiceType(){
	document.getElementById("saveVoiceTypeForm").submit();
}


function deleteVoiceType(id){
	
	
	if(confirm("Are you sure to delete?")){
		$.ajax({
			type:"POST",
			url:"<%=basePath%>/admin/deleteVoiceType?voiceType.id="+id,
			dataType:"json",
			success:function(data){
				//window.location.reload(true);
				
				if(data.msg){
					alert(data.msg);
				}else{
					window.location.reload(true);
				}
			}
		});
	}
	

}

</script>

<div id="voice_type" style="position: relative; left: 10px; top: 10px;">
	<table class="list">
		<tr>
			<td colspan="4" align="left">
				<button class="btn btn-primary" onclick="javescript:addVoiceType()">Add Voice Type</button>
			</td>
		</tr>
		<tr>
			<th width="10%">id</th>
			<th width="40%">Type Name</th>
		</tr>
		
		<s:iterator id="vtList" value="vtList" status="st" >
			<tr>
				<td>
					<s:property value="id"/>
				</td>
				<td>
					<s:property value="name"/>
				</td>
				<td>
					<a class="btn"  href="/EmCare/admin/viewVoiceType?voiceType.id=<s:property value="id"/>" >Edit</a>
					<a class="btn"  href="javascript:" onclick="deleteVoiceType(<s:property value="id"/>)" >Delete</a>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>


<div id="voiceTypeDialog">
	
		<form action="/EmCare/admin/saveVoiceType" name="saveVoiceTypeForm" id="saveVoiceTypeForm" method="post">
		<table class="list">
			<tr>
				<td width="80px">
					Type Name
				</td>
				<td width="300px">
					<input type="text" name="voiceType.name" id="voiceType_userName" />
				</td>
			</tr>
		</table>
	</form>	
	<div style="margin: 5px 5px 10px 100px; " id="save_user" >
			<button onclick="saveVoiceType()">Save</button>
			<button onclick="javescript:jQuery('#dialog').dialog('close' );">Close</button>
	</div>
	</div>
