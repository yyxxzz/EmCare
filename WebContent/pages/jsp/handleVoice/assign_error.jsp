<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Assigned Error</title>
</head>
<body>
 Voice <s:property value="voice.title"/> <br>
 had been Assigned to <s:property value="voice.assignment.assignTo.realname"/> ! <br>
 At this moment, re-assignment is not supported!
</body>
</html>