<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%String basePath = request.getContextPath(); %>

<link href="<%=basePath %>/js/bootstrap/css/bootstrap.css"	rel="stylesheet">
<link href="<%=basePath %>/css/side_nav.css" rel="stylesheet">
<script type="text/javascript" src="<%=basePath %>/js/bootstrap/js/html5.js"></script>
<div style="position: relative; top: 13px;">

	<ul class="nav nav-list bs-docs-sidenav">
		<s:if test="#session.userInfo.person.isInRoleOf('S')">
			<li><a href="<%=basePath %>/admin/handleVoice"
				target="mainFrame"><i class="icon-chevron-right"></i> New Items</a>
			</li>
		</s:if>
		<s:if test="#session.userInfo.person.isInRoleOf('S')">
			<li><a href="<%=basePath %>/admin/handleVoice!listSolvedVoices"
				target="mainFrame"><i class="icon-chevron-right"></i> Solved
					Items</a></li>
		</s:if>
		<s:if test="#session.userInfo.person.isInRoleOf('S')">
			<li><a href="<%=basePath %>/admin/handleVoice!listClosedVoices"
				target="mainFrame"><i class="icon-chevron-right"></i> Closed
					Items</a></li>
		</s:if>
		<s:if test="#session.userInfo.person.isInRoleOf('S')">
			<li><a href="<%=basePath %>/admin/handleVoice!searchVoices"
				target="mainFrame"><i class="icon-chevron-right"></i> Search</a></li>
		</s:if>

		<li><a href="<%=basePath %>/admin/assignedToMe"
			target="mainFrame"><i class="icon-chevron-right"></i> Assigned To
				Me</a></li>

		<s:if test="#session.userInfo.person.isInRoleOf('S')">
			<li><a href="<%=basePath %>/pages/report.jsp" target="mainFrame"><i
					class="icon-chevron-right"></i> Report</a></li>
			<li><a href="<%=basePath %>/admin/handleVoiceType"
				target="mainFrame"><i class="icon-chevron-right"></i> Voice Type</a>
			</li>
			<li><a href="<%=basePath %>/admin/getAllUser" target="mainFrame"><i
					class="icon-chevron-right"></i> Users</a></li>
		</s:if>
		<li>
		<a href="<%= basePath%>/admin/changePassword"  target="mainFrame"><i
					class="icon-chevron-right"></i>Change Password</a>
		</li>
	</ul>

</div>
