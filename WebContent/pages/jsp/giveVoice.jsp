<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<%String basePath=request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EmCare - company.com</title>
	<jsp:include page="../jsp/component/js_include.jsp"></jsp:include>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>/css/main.css">	
	<script charset="utf-8" src="<%=basePath %>/js/kindeditor/kindeditor-min.js"></script>
	<script src="<%=basePath %>/js/function/copy_tool.js"></script>
	<script src="<%=basePath %>/js/function/editor_generator.js"></script>
	
	<style type="text/css">	
		.leftC{
			width: 700px;
			float: left;
			background-image: url(<%=basePath %>/images/bg4.png);
			background-repeat: no-repeat;
			background-position: 300px 0px;
			margin-top: 20px;
		}	
		
		.rightContent{
			height: 650px;
		}
		
		
		#warmTips{
			font-size:13px;
			color: #C5D811;
		}
		
	</style>
		
	<script type="text/javascript">
	
		function checkEmpty(){

			var value =  $("#kind_editor").val();		
			var title = $("#title").val().trim();;
			var space = /&nbsp;|[<br/>]/g;
		
			value = value.replace(space,"");
			value = value.trim();
					
			if(value == "" || title == ""){
				$("#errorTips").html("Content or title can not be empty.");
				return false;
			}else{
				return true;
			}
		}	
		
		
	</script>
		
</head>

<body style="margin: 0px;">

<jsp:include page="component/head.jsp"></jsp:include>


<div class="middleContent ">
	<div class="leftC">
	  <form id="post-form" onsubmit="return checkEmpty();" class="post-form" action="<%=basePath %>/saveVoice" method="post">
		<div class="voiceForm">
			<table >		
				<tr >
					<td colspan="2" height="80">
						<span id="errorTips" class="errorTips" >
							<b id="warmTips">All the Voices and Comments are Anonymous.</b>
						</span>										
					</td>
				</tr>
				<tr >
					<td width="2">
						<b style="font-size: 18px; letter-spacing: 0.5px;">Title</b>&nbsp;&nbsp;
						<input id="title" type="text" name="voice.title" style="width: 180px; font-size: 17px;">
					</td>
				</tr>
				<tr ><td colspan="2" height="10"></td></tr>
				<tr >
					<td colspan="2">
						<textarea id="kind_editor"  name="voice.content" style="width: 660px; height: 450px; "></textarea>
					</td>
				</tr>
				<tr ><td colspan="2" height="10"></td></tr>
				<tr >
					<td width="2">
						<b style="font-size: 12px; ">Signature</b>&nbsp;&nbsp;
						<input name="voice.signature" type="text" style="width: 140px; font-size: 14px;">&nbsp;&nbsp;
						<b style="font-size: 11px;">(Optional)</b>
					</td>
				</tr>
			</table>
			
			<br /><br />
			<input type="submit" class="submitButton" value="Post Your Voice" />
		</div>
		</form>
	</div>
	<jsp:include page="component/right.jsp"></jsp:include>
</div>

<jsp:include page="component/foot.jsp"></jsp:include>

</body>
</html>