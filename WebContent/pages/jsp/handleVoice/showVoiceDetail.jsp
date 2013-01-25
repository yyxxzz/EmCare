<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/EmCare/css/all.css"rel="stylesheet">
 <link href="/EmCare/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	    <link href="/EmCare/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<title>Voice Detail</title>
</head>
<body>
	<div style="height: 100px">
		<img alt="" src="/EmCare/images/logo1.png" style="width: 200px; height: 80px" />
	</div>
	<div>
		<table class="table table-striped table-bordered">
			<tr>
				<td width="80px">id : </td>
				<td width="300px"><span>${voice.voice.voiceId}</span></td>
			</tr>
			<tr>
				<td>title : </td>
				<td id="title"><span>${voice.voice.title}</span></td>
			</tr>
			<tr height="100px">
				<td>Content : </td>
				<td id="content"><span>${voice.voice.content}</span></td>
			</tr>
			<tr>
				<td>Date : </td>
				<td><span id="form_date">${voice.voice.submitTime}</span></td>
			</tr>
			<tr>
				<td>Assign To</td>
				<td>
					${voice.voice.assignment.assignTo.username}
				</td>
			</tr>
			<tr>
				<td>Status</td>
				<td>
					<s:if test="voice.voice.status == 1">
						OPEN
					</s:if>
					<s:if test="voice.voice.status == 2">
						ASSIGNED
					</s:if>
					<s:if test="voice.voice.status == 3">
						PENDING
					</s:if>
					<s:if test="voice.voice.status == 4">
						RESOLVED
					</s:if>
					<s:if test="voice.voice.status == 5">
						REJECTED
					</s:if>
					<s:if test="voice.voice.status == 6">
						CLOSED
					</s:if>
				</td>
			</tr>
			<!-- tr>
				<td ><button type="submit" value="Save" width="100"></button></td>
				<td ><button value="close" width="100"></button></td>
			</tr-->
		</table>
	</div>
</body>
</html>