<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Manage Manager</title>
<style type="text/css">
body {
	padding: 0;
	font: 12px;
}

.userList thead td {
	background: none repeat scroll 0 0 #DFDBDB;
	color: #5D5C5C;
	font-weight: bold;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 8px;
}

.userList td {
	color: #515050;
	height: 30px;
	padding: 0 4px;
	text-align: center;
}

.manage_manager {
	height: 480px;
	margin-left: 24px;
}
</style>
<link href="<%=basePath %>/css/all.css"rel="stylesheet">
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
		jQuery("#dialog").dialog({
			autoOpen: false,
			width: 400
		}); 
	});
	
	
	function addUser(){
		$( "#dialog" ).dialog( "open" );
		$( "#dialog" ).dialog('option', 'title', "Add Manager" );
			
	}
	
	function saveUser() {
		var userName = document.getElementById("new_userName").value;
		var userPassword = document.getElementById("new_Password").value;
		var roles = document.getElementsByName("user_role");
		var personRoles = new Array();
		 for(var i=0;i<roles.length;i++)
         {
             if(roles[i].checked==true)
             {         
            	 personRoles.push(roles[i].value);
             }
         }
		 document.getElementById("roles").value = personRoles.join().toString();
		 document.getElementById("saveUserForm").submit();
	}
	
	function searchUsers(){
		
		var queryString = $("#queryStr").val();
		window.location = "<%=basePath %>/admin/getAllUser?query=" + queryString;
	}
</script>

</head>
<body>
	<div style="padding-bottom:5px;left:0px;position:relative;">
		<input id="queryStr" type="text" value="<s:property value="query"/>">
		<button onclick="searchUsers();" class="btn btn-primary" style="position: relative; top: -5px;">Search</button>
	</div>

	<div id="manage_manager" class="manage_manager" >
		<table class="table table-striped table-bordered" id="userList">
						
		
						<tr>
							<td width="15%"> User ID	</td>
							<td width="15%"> User Name	</td>
							<td width="20%"> Role	</td>
							<td width="20%"> Action	</td>
						</tr>
						<s:iterator id="users" value="users" status="st" >
						
						<tr>
							<td width="12%">
								<s:property value="username"/>
							</td>
							<td width="15%">
								<s:property value="realname"/>
							</td>
							<td width="15%">
								<s:iterator id="personRoles" value="personRoles" status="r_st">
									<s:property value="roleName"/><s:if test="!#r_st.last">,</s:if>
								</s:iterator>
							</td>
							<td width="15%">
							
							<a  href="/EmCare/admin/deleteUser?user.personId=<s:property value="personId"/>"  onClick="return confirm('Would you like to delete the manager?')" >Delete</a>
								&nbsp;&nbsp;
								
							<a  href="/EmCare/admin/viewUser?user.personId=<s:property value="personId"/>"  >Edit</a>&nbsp;&nbsp;
							</td>
						</tr>
						</s:iterator>
				</table>
	</div>
	
	<div class="pageBar" style="position: relative; left: 40%; top: 10px;" >
		<jsp:include page="pagination.jsp"></jsp:include>
	</div>
	
	
	<div id="dialog">
	
		<form action="/EmCare/admin/saveUser" name="saveUserForm" id="saveUserForm" method="post">
		<table class="list">
			<tr>
				<td width="80px">
					User Name
				</td>
				<td width="300px">
					<input type="text" name="user.username" id="new_userName" />
				</td>
			</tr>
			<tr>
				<td>
					Password
				</td>
				<td>
					<input type="text" name="user.password" id="new_Password" />
				</td>
			</tr>
			<tr>
				<td>
					Role
				</td>
				<td>
					<input type="checkbox" name="user_role" value="1"/><label>SYS_ADMIN</label>
					<input type="checkbox" name="user_role" value="2"/><label>SUM</label>
					<input type="checkbox" name="user_role" value="3"/><label>LM</label>
				</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" name="roles" id="roles">
				</td>
			</tr>
			
		</table>
		<!-- 
			margin:10px 5px 15px 20px;
			上外边距是 10px
			右外边距是 5px
			下外边距是 15px
			左外边距是 20px
		 -->
	</form>	
	<div style="margin: 5px 5px 10px 100px; " id="save_user" >
			<button onclick="saveUser()">Save</button>
			<button onclick="javescript:jQuery('#dialog').dialog('close' );">Close</button>
	</div>
	</div>
	
</body>
</html>
