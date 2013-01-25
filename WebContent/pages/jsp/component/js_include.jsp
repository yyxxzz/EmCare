<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getContextPath(); %>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<script type="text/javascript" src="<%=basePath %>/js/jquery/jquery-1.8.2.js"></script>
<script src="<%=basePath %>/js/jquery/jquery.cookie.js"></script>

<link rel="icon" type="image/x-icon" href="<%=basePath %>/images/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon" href="<%=basePath %>/images/favicon.ico" />

<script type="text/javascript">

$(document).ready(function(){
	var keyword = '<s:property value="q"/>';
	var init = false;
	if(keyword!=''){
		$("#search_box").val(keyword);
		init=true;
	}
	$("#search_box").click(function(){
		if(!init)
		$("#search_box").val("");
	}).keydown(function(event){
		if($("#search_box").val()==null||$.trim($("#search_box").val())=="")
			return;
		if(event.keyCode==13){
			window.location.href="<%=basePath%>/search?q="+$("#search_box").val();
		}
	});
	
	$("#search_Image").click(function(){
		window.location.href="<%=basePath%>/search?q="+$("#search_box").val();
	});
	/* // For IE users, authentication with windows login name
	if($.browser.msie){
		var logname = $.cookie("loginname");
		if(logname==null){
			 var wshell = new ActiveXObject("WScript.Shell");
	         var username=wshell.ExpandEnvironmentStrings("%USERNAME%");
	         $.cookie("loginname",username);
	         location.href = location.href;
		}
	} */
}
);

</script>