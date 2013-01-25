<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getContextPath();
%>
 <link href="/EmCare/js/bootstrap/css/bootstrap.css" rel="stylesheet">
	    <link href="/EmCare/js/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>/css/jira.webresources global-static.css"
	media="screen" />
<script type="text/javascript"
	src="<%=basePath%>/js/jquery/jquery-1.8.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#assign-issue-submit").click(function(event) {
			var assignee = $("#assignee").val();
			if (assignee == "") {
				alert("Please choose person to assigne!");
				event.preventDefault();
			}
			var comment = $("#comment").val();
			if(comment==""){
				alert("Please input comment!");
				event.preventDefault();
			}
		});
	$("#assign-issue-cancel").click(function(){
		parent.$.fancybox.close();
	});
	});
	//--
</script>
<div id="assign-dialog"
	class="aui-popup box-shadow aui-dialog-open popup-width-medium aui-dialog-content-ready">
	<h2 class="aui-popup-heading">
		Assign Voice<span style="display: none" class="header-separator">:&nbsp;</span>
	</h2>
	<div class="aui-popup-content">
		<div class="aui-dialog-content">
			<div class="content intform">

				<form action="<%=basePath%>/admin/assignVoice" class="aui"
					id="assign-issue" method="post">
					<div class="content-body" style="max-height: 315px;">
						<div class="hidden">
							<input name="voice" type="hidden"
								value="<s:property value="#parameters.voiceId" />" />
								
						</div>
						<fieldset>
							<div class="field-group">
								<label for="assignee">Assignee</label> <select class="select"
									id="assignee" name="assignee">
									<option value="">Unassigned</option>
									<s:iterator value="LMs">
										<option value="<s:property value="personId" />">
											<s:property value="username" />
										</option>
									</s:iterator>
								</select>
							</div>
							<br>
							<div class="field-group">
								<label for="voice_type"> Voice Category</label>
								<select class="select" name = "voiceTypeId">
									<s:iterator value="voiceCategories">
										<option value="<s:property value="id" />">
											<s:property value="name" />
										</option>
									</s:iterator>
								</select>
							</div>
							<br>
							<div class="field-group aui-field-wikiedit comment-input">
								<label for="comment">Comment</label>
								<div class="wiki-edit">
									<div id="comment-wiki-edit" class="wiki-edit-content">
										<textarea class="textarea long-field wiki-textfield" cols="60"
											id="comment" name="comment" rows="10" wrap="virtual"></textarea>
										<div class="content-inner"></div>
									</div>
								</div>

								<div class="security-level"></div>
							</div>
						</fieldset>
					</div>

					<div class="buttons-container content-footer">









						<div class="buttons">
							<span class="icon throbber"></span> <input accesskey="s"
								class="btn" id="assign-issue-submit" name="Assign"
								 type="submit"
								value="Assign"> <a  class="btn" href="#"
								id="assign-issue-cancel" title="Press Alt+` to cancel">Cancel</a>

						</div>
					</div>

				</form>
				<!-- // .aui #assign-issue -->

			</div>
		</div>
	</div>
</div>