<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String basePath = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Manage Manager</title>
<link rel="stylesheet" type="text/css"	href="<%=basePath %>/css/jira.webresources global-static.css"	media="screen" />
<link href="<%=basePath %>/js/bootstrap/css/bootstrap.css"	rel="stylesheet">
<script language="javascript" type="text/javascript"
	src="/EmCare/js/My97DatePicker/WdatePicker.js"></script>
<script src="/EmCare/js/jquery/jquery-1.8.2.js"></script>
<script src="/EmCare/js/jquery/jquery-ui-1.9.1.custom.js"></script>
<script type="text/javascript">

function updateUser() {
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
	 //$('#updateUserForm').submit();
	 document.getElementById("updateUserForm").submit();
}

</script>
<style type="text/css">
.content-body{
	position:relative;top:100px;
}
</style>
</head>
<body>


<form action="<%=basePath %>/admin/udpateUser" name="updateUserForm" id="updateUserForm" method="post">
<div class="content-body" >
		<table class="userList" id="userList" align="center">
						<s:iterator id="user" value="users" status="st" >
						<%-- <tr>
							<td> ID	</td>
							<td>
							<span class="label"><s:property value="personId"/></span>
								
							</td>
						</tr> --%>
						
						<input type="hidden" name="user.personId" value="<s:property value="personId"/>"/>
						<tr>
							<td> <span class="label">User ID	</span></td>
							<td>
							<s:property value="username"/>
								<input type="hidden" name="user.username" value="<s:property value="username"/>"/>
							</td>
						</tr>
						<tr>
							<td> <span class="label">User Name	</span></td>
							<td>
							<input type="text" id="user_username" name="user.realname"  value="<s:property value="realname"/>">
							</td>
						</tr>
						<tr>
							<td> <span class="label">Email	</span></td>
							<td>
							<input type="text" id="user_username" name="user.email"  value="<s:property value="email"/>">
							</td>
						</tr>
						
						<tr>
							<td > <span class="label">Password</span>	</td>
							<td >
							<input type="text" id="user_password" name="user.password"  value="<s:property value="password"/>">
							</td>
						</tr>
						
						<tr>
							<td ><span class="label"> Role</span>	</td>
							<td >
								<%-- <s:iterator id="personRoles" value="personRoles" status="st">
									<!--
										<s:if test="personRoles.value.size=3">
										</s:if>
									-->
											<s:if test="roleType==1">
											<input type="checkbox" name="user_role" value="1" <s:if test="roleType==1"> checked="checked" </s:if>/><label>SYS_ADMIN</label>&nbsp;&nbsp;
											</s:if>
											<s:if test="roleType==2">
											<input type="checkbox" name="user_role" value="2" <s:if test="roleType==2"> checked="checked" </s:if>/><label>SUM</label>&nbsp;&nbsp;
											</s:if>
											<s:if test="roleType==3">
											<input type="checkbox" name="user_role" value="3"  <s:if test="roleType==3"> checked="checked" </s:if>/><label>LM</label>&nbsp;&nbsp;
											</s:if>
								</s:iterator> --%>
								
						<input type="checkbox" name="user_role" value="1" <s:if test="#user.isInRoleOf('SA')"> checked="checked" </s:if>/>SYS_ADMIN&nbsp;
						<input type="checkbox" name="user_role" value="2" <s:if test="#user.isInRoleOf('S')"> checked="checked" </s:if>/>SUM&nbsp;
						<input type="checkbox" name="user_role" value="3"  <s:if test="#user.isInRoleOf('L')"> checked="checked" </s:if>/>LM&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="2">
							<input type="hidden" name="roles" id="roles"/>
							</td>
						</tr>
						</s:iterator>
						<tr>
							<td colspan="2">
								<input type="button" class="btn" value="Save" onclick="updateUser()"/>
							</td>
						</tr>
		</table>
</div>
</form>
</body>
</html>