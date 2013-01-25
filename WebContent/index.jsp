<%
	//request.getRequestDispatcher(request.getContextPath()+"viewVoices").forward(request, response);
response.sendRedirect(request.getContextPath()+"/viewVoices");
%> 