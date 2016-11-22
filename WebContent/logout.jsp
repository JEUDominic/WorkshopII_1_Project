<%
	
	session.removeAttribute("username");
	session.removeAttribute("email");
	session.removeAttribute("course_id");
	session.removeAttribute("course_name");
	session.removeAttribute("course_num");
	session.removeAttribute("error");
	session.invalidate();

%>
<%
	response.sendRedirect("login.jsp");
%>