<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE div PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%String basePath = request.getContextPath(); %>
<html>
<head>
    <link href="<%=basePath %>/js/bootstrap/css/bootstrap.css" rel="stylesheet">
   <link href="<%=basePath %>/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script type="text/javascript">
	
</script>
<title>Login page</title>

<style type="text/css">
<!--
html, body

	{
	margin: 0;
	padding: 0;
	background: #fff;
	font-family: Tahoma, Verdana, Arial, Helvetica, sans-serif;
	}

table.face td

	{
	text-align: center;
	font-family: Tahoma, Verdana, Arial, Helvetica, sans-serif;
	font-size: 40px;
	color: #555;
	}

table.face a:link, table.face a:visited

	{
	text-decoration: none;
	color: #333;
	}

table.face a:hover

	{
	text-decoration: none;
	color: #ff0000;
	}

table.blocked td

	{
	font-size: 11px;
	}

table.blocked td fieldset

	{
	font-size: 14px;
	font-weight: bold;
	color: #333;
	width: 360px;
	}

table.blocked td legend

	{
	font-size: 14px;
	font-weight: bold;
	color: #333;
	}

table.blocked1

	{
	border: solid 1px #808080;
	border-collapse: collapse;
	}

table.blocked1 th

	{
	font-size: 11px;
	text-transform: uppercase;
	border: solid 1px #808080;
	background: #eee;
	}

table.blocked1 td

	{
	border: solid 1px #808080;
	font-size: 11px;
	/*background: #FFF5F5;*/
	}
	.traffic
	{
	border: solid 1px #808080;
	font-size: 14px;
	font-weight: bold;
	color: #cc0000;
	background: #fff;
	}

table.blocked2

	{
	border: solid 1px #808080;
	border-collapse: collapse;
	}

table.blocked2 th

	{
	font-size: 11px;
	text-transform: uppercase;
	border: solid 1px #808080;
	background: #eee;
	}

table.blocked2 td

	{
	border: solid 1px #808080;
	font-size: 11px;
	/*background: #F5F5FF;*/
	}

table.blocked2 td.multi

	{
	border: solid 1px #808080;
	font-size: 14px;
	font-weight: bold;
	color: #0000cc;
	background: #fff;
	}

table.blocked3

	{
	border: solid 1px #808080;
	border-collapse: collapse;
	}

table.blocked3 th

	{
	font-size: 11px;
	text-transform: uppercase;
	border: solid 1px #808080;
	background: #eee;
	}

table.blocked3 td

	{
	border: solid 1px #808080;
	font-size: 11px;
	/*background: #F5FFF5;*/
	}

table.blocked3 td.unblockable

	{
	border: solid 1px #808080;
	font-size: 14px;
	font-weight: bold;
	color: #00cc00;
	background: #fff;
	}

tr.search

	{
	background: #2E353A;
	font-weight: bold;
	color: #fff;
	}

tr.search td

	{
	border-bottom: solid 2px #1F2329;
	white-space: nowrap;
	}

fieldset.face input

	{
	border: solid 1px #999;
	font-weight: bold;
	background: #ddd;
	}

img

	{
	border: solid 1px #999;
	}

h1

	{
	font-size: 24px;
	color: #333;
	margin: 0;
	padding: 0;
	}
.fieldError {
    font-family: sans-serif;
    font-size: 12px;
    color: #d00000;
}

-->
</style>
</head>

<body >

<BR>
<div align=center><b>&nbsp;</b></div>
<BR>
<form name="auto" method="post" action="login">
<center><div class="page-header">
<h1>EmCare Admin Login Page</h1>
<p class="gray">We care about your voice </p>
</div>
</center>
<table cellspacing=0 cellpadding=10 width=100%>
	<tr>
		<td>
		<table cellspacing=0 cellpadding=8 width=500 class=blocked1 align="center">
		
		<%-- 	<tr>
				<td colspan=2 class=traffic style="font-size: 16px">
				<div style="color:red;"><s:fielderror fieldName="logintips"/></div>
				</td>
			</tr> --%>
			<tr>
				<th colspan="2" style="font-size: 13px; font-weight: bold">Please enter your authorization information:</th>
			</tr>
			<tr>
			  <td width="250" align="center"><span style="font-size: 13px;">Username:</span></td>
			  <td width="250" align="center">
			          <input type="text" name="person.username" style="width: 200px;">
			   </td>
		  </tr>
			<tr>
			  <td align="center"><span style="font-size: 13px;">Password:</span></td>
			  <td align="center">
		          <input name="person.password" type="password" style="width: 200px;">
			  </td>
		  </tr>
			<!-- <tr>
				<th colspan="2" style="font-size: 13px; font-weight: bold">Enter a code specified on image:</th>
			</tr>
			<tr>
			  <td align="center"><img width="200" height="50" border="0" src="http://tech.assteenmouth.com/images/452a77726cbb0.jpeg" alt=""></td>
			  <td align="center">
		          <input name="code" type="text" style="width: 200px;">
			  </td>
		  </tr> -->
			<tr>
			  <th colspan="2" align="center">
			  <input type="submit" name="Login" value="Login" class="btn" >
			  </th>
		  </tr>
		  
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>

