<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Page Not Found</title>

<style type="text/css">
	.tipClass{
		margin: 0 auto;
		width: 700px; 
		text-align: center;
		margin-top: -80px;
	}
	.backLink{
		margin: 0 auto;
		width: 700px; 
		text-align: center;
		margin-top: 50px;
	}
	
	.backLink a{
		color: black;
		text-decoration: none;
		font-size: 22px;
		font-weight: bold;
	}
	
	.backLink a:hover {
		text-decoration: underline;
		color: #747474;
		
	}
		
</style>

</head>
<body style="background-color:#BFBFBF; ">
<img src="<%=basePath %>/images/404.gif" width="319" height="234" />
<div class="tipClass" style="">
	<embed width="500" height="300" align="middle"  type="application/x-shockwave-flash"  bgcolor="#BFBFBF" quality="high" src="<%=basePath %>/images/err.swf?err=404&amp;subtext=THE PAGE YOU ARE LOOKING FOR DOES NOT EXIST. SORRY :'(">
</div>
<div class="backLink">
	<a href="<%=basePath %>">Back to EmCare</a>
</div>

</body>
</html>