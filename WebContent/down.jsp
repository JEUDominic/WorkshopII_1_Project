<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="JDBC.*" import="Table.*"%>

<html>

<script type="text/javascript">
function autoSubmit(){
	document.getElementById("myForm").submit();
}
</script>
<body onload="autoSubmit()">

<%
int i = Integer.parseInt(request.getParameter("id"));
String action = (String)request.getParameter("action");
String courseid[] = (String[])session.getAttribute("course_id");
String coursename[] = (String[])session.getAttribute("course_name");
String course_ta[]= (String[])session.getAttribute("course_ta");
String numass=(String)request.getParameter("num_ass");
String ta_name = DatabaseOperation.getUser(Integer.parseInt(course_ta[i]));
%>
<form   id="myForm" action="/ZWorkshopProject/Down" method="post">
<% int course_id=Integer.parseInt(courseid[i]); %><input style="display:none;" name="course_id" value="<%=course_id%>" >

<% String course_name=coursename[i]; %><input style="display:none;" name="course_name" value="<%=course_name%>" >

<% String type=(String)request.getParameter("type"); %><input style="display:none;" name="type" value="<%=type%>" >
<% int num_ass=Integer.parseInt(numass); %><input style="display:none;" name="num_ass" value="<%=num_ass%>" >
<input style="display:none;" name="url_id"  value="<%=i %>" >
<input style="display:none;" name="button"  value="<%=action%>" >
</form>
</body>
</html>