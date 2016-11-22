<%@page import="JDBC.*" import="Table.*"%>
<% 
	int i = Integer.parseInt(request.getParameter("id"));
	String course_id[] = (String[])session.getAttribute("course_id");
	String course_name[] = (String[])session.getAttribute("course_name");
	String course_ta[]= (String[])session.getAttribute("course_ta");
	String ta_name = DatabaseOperation.getUser(Integer.parseInt(course_ta[i]));
	int[] percentage = DatabaseOperation.getAssPer(Integer.parseInt(course_id[i]));
%>

<%session.setAttribute("title","CILO Course "+course_id[i]+" "+course_name[i]); %>
<%@ include file="header.jsp" %> 

    <!-- Collect the nav links, forms, and other content for toggling -->
    
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="home.jsp">Home</a></li>
        <li class="active"><a href=<%="course.jsp?id="+i %>>Course <%=course_id[i] %> <%=course_name[i] %><span class="sr-only">(current)</span></a></li>
        </ul>
        <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
        <input type="text" class="form-control" placeholder="Course Name">
		</div>
        <button type="submit" class="btn btn-default">Search</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <a class="navbar-brand" ><img src="img/icon.png"></a>
        <li><a href="logout.jsp">Log Out</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">It Can Dropdown <span class="caret"></span></a>
        	<ul class="dropdown-menu">
            	<li><a href="home.html">But There Is</a></li>
            	<li><a href="course.html">No Link</a></li>
            	<li><a href="addcourse.html">Just Show It For</a></li>
            	<li><a href="addpaper.html">INTERESTING</a></li>
            	<li role="separator" class="divider"></li>
            	<li><a href="joincourse.html">23333</a></li>
          	</ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->


	</nav><!-- /.navar -->
	</div><!-- header-->
    
    <div class="row">
  		<div class="col-md-8">

  			<% 
  			if (percentage[0] !=0) {
					int assignment_num = DatabaseOperation.getAssNum("assignment", Integer.parseInt(course_id[i]));
					int j=1;
  				    for(;j<=assignment_num;j++){
  			
  			%>
  			<div class="aaa">
  				<div class="panel-heading">
  				<h3 class="panel-title">Assignment <%=j %></h3>
  				</div><!-- panel-heading -->
  				<div class="panel-body">
  				<div class="col-md-4">
   				<% String address1 = "down.jsp?action=DownloadPaper&type=assignment&id="; %>
  				<a href=<%=address1+i+"&num_ass="+j %> class="btn btn-primary" role="button">Download Paper</a> 
  				</div>
  				<div class="col-md-4">
  				<% String address2 = "down.jsp?action=DownloadGradeModel&type=assignment&id="; %>
    			<a href=<%=address2+i+"&num_ass="+j %> class="btn btn-default" role="button">Download Grade Model</a>
    			</div>
  				<div class="col-md-4">
  				<% String address3 = "down.jsp?action=UploadGradeFile&type=assignment&id="; %>
    			<a href=<%=address3+i+"&num_ass="+j %> class="btn btn-default" role="button">Upload Grade File(excel)</a>
    			</div>
  				</div><!-- panel-body -->
			</div>
			<%
  							}
  			%>
  			<div class="aaa">
  				<div class="panel-heading">
   				<h3 class="panel-title">Assignment <%=j %></h3>
  				</div><!-- panel-heading-->
  				<div class="panel-body">
  				<div class="col-md-4">
  				<%String addpaper = "addpaper.jsp?type=assignment&id="; %>
    			<a href=<%=addpaper+i+"&num_ass="+j%> class="btn btn-primary" role="button">Add Paper</a> 
    			</div>
  				</div><!-- panel-heading-->
			</div>
  			<%
					} 
			%> 
			
			
			<% 
  			if (percentage[1] !=0) {
					int quiz_num = DatabaseOperation.getAssNum("quiz", Integer.parseInt(course_id[i]));
					int j=1;
  				    for(;j<=quiz_num;j++){
  			
  			%>
  			<div class="aaa">
  				<div class="panel-heading">
  				<h3 class="panel-title">Quiz <%=j %></h3>
  				</div><!-- panel-heading -->
  				<div class="panel-body">
  				<div class="col-md-4">
   				<% String address1 = "down.jsp?action=DownloadPaper&type=quiz&id="; %>
  				<a href=<%=address1+i+"&num_ass="+j %> class="btn btn-primary" role="button">Download Paper</a> 
  				</div>
  				<div class="col-md-4">
  				<% String address2 = "down.jsp?action=DownloadGradeModel&type=quiz&id="; %>
    			<a href=<%=address2+i+"&num_ass="+j %> class="btn btn-default" role="button">Download Grade Model</a>
    			</div>
  				<div class="col-md-4">
  				<% String address3 = "down.jsp?action=UploadGradeFile&type=quiz&id="; %>
    			<a href=<%=address3+i+"&num_ass="+j %> class="btn btn-default" role="button">Upload Grade File(excel)</a>
    			</div>
  				</div><!-- panel-body -->
			</div>
			<%
  							}
  			%>
  			<div class="aaa">
  				<div class="panel-heading">
   				<h3 class="panel-title">Quiz <%=j %></h3>
  				</div><!-- panel-heading-->
  				<div class="panel-body">
  				<div class="col-md-4">
    			<%String addpaper = "addpaper.jsp?type=quiz&id="; %>
    			<a href=<%=addpaper+i+"&num_ass="+j%> class="btn btn-primary" role="button">Add Paper</a> 
    			</div>
  				</div><!-- panel-heading-->
			</div>
  			<%
					} 
			%> 
			
			
			<% 
  			if (percentage[2] !=0) {
					int test_num = DatabaseOperation.getAssNum("test", Integer.parseInt(course_id[i]));
					int j=1;
  				    for(;j<=test_num;j++){
  			
  			%>
  			<div class="aaa">
  				<div class="panel-heading">
  				<h3 class="panel-title">Test <%=j %></h3>
  				</div><!-- panel-heading -->
  				<div class="panel-body">
  				<div class="col-md-4">
   				<% String address1 = "down.jsp?action=DownloadPaper&type=test&id="; %>
  				<a href=<%=address1+i+"?num_ass="+j %> class="btn btn-primary" role="button">Download Paper</a> 
  				</div>
  				<div class="col-md-4">
  				<% String address2 = "down.jsp?action=DownloadGradeModel&type=test&id="; %>
    			<a href=<%=address2+i+"?num_ass="+j %> class="btn btn-default" role="button">Download Grade Model</a>
    			</div>
  				<div class="col-md-4">
  				<% String address3 = "down.jsp?action=UploadGradeFile&type=test&id="; %>
    			<a href=<%=address3+i+"?num_ass="+j %> class="btn btn-default" role="button">Upload Grade File(excel)</a>
    			</div>
  				</div><!-- panel-body -->
			</div>
			<%
  							}
  			%>
  			<div class="aaa">
  				<div class="panel-heading">
   				<h3 class="panel-title">Test <%=j %></h3>
  				</div><!-- panel-heading-->
  				<div class="panel-body">
  				<div class="col-md-4">
    			<%String addpaper = "addpaper.jsp?type=test&id="; %>
    			<a href=<%=addpaper+i+"&num_ass="+j%> class="btn btn-primary" role="button">Add Paper</a>
    			</div>
  				</div><!-- panel-heading-->
			</div>
  			<%
					} 
			%> 
			
			
			<% 
  			if (percentage[3] !=0) {
					int midterm_num = DatabaseOperation.getAssNum("midterm", Integer.parseInt(course_id[i]));
					if(midterm_num != 0){  			
  			%>
  			<div class="aaa">
  				<div class="panel-heading">
  				<h3 class="panel-title">Midterm</h3>
  				</div><!-- panel-heading -->
  				<div class="panel-body">
  				<div class="col-md-4">
   				<% String address1 = "down.jsp?action=DownloadPaper&type=midterm&id="; %>
  				<a href=<%=address1+i+"&num_ass=1" %> class="btn btn-primary" role="button">Download Paper</a> 
  				</div>
  				<div class="col-md-4">
  				<% String address2 = "down.jsp?action=DownloadGradeModel&type=midterm&id="; %>
    			<a href=<%=address2+i+"&num_ass=1" %> class="btn btn-default" role="button">Download Grade Model</a>
    			</div>
  				<div class="col-md-4">
  				<% String address3 = "down.jsp?action=UploadGradeFile&type=midterm&id="; %>
    			<a href=<%=address3+i+"&num_ass=1" %> class="btn btn-default" role="button">Upload Grade File(excel)</a>
    			</div>
  				</div><!-- panel-body -->
			</div>
			<%
  							}else{
  			%>
  			<div class="aaa">
  				<div class="panel-heading">
   				<h3 class="panel-title">Midterm</h3>
  				</div><!-- panel-heading-->
  				<div class="panel-body">
  				<div class="col-md-4">
    			<%String addpaper = "addpaper.jsp?type=midterm&id="; %>
    			<a href=<%=addpaper+i+"&num_ass=1"%> class="btn btn-primary" role="button">Add Paper</a> 
    			</div>
  				</div><!-- panel-heading-->
			</div>
  			<%
					} 
  				}
			%> 
			
			<% 
  			if (percentage[4] !=0) {
					int final_num = DatabaseOperation.getAssNum("final", Integer.parseInt(course_id[i]));
					if(final_num != 0){  			
  			%>
  			<div class="aaa">
  				<div class="panel-heading">
  				<h3 class="panel-title">Final</h3>
  				</div><!-- panel-heading -->
  				<div class="panel-body">
  				<div class="col-md-4">
   				<% String address1 = "down.jsp?action=DownloadPaper&type=final&id="; %>
  				<a href=<%=address1+i+"&num_ass=1" %> class="btn btn-primary" role="button">Download Paper</a> 
  				</div>
  				<div class="col-md-4">
  				<% String address2 = "down.jsp?action=DownloadGradeModel&type=final&id="; %>
    			<a href=<%=address2+i+"&num_ass=1" %> class="btn btn-default" role="button">Download Grade Model</a>
    			</div>
  				<div class="col-md-4">
  				<% String address3 = "down.jsp?action=UploadGradeFile&type=final&id="; %>
    			<a href=<%=address3+i+"&num_ass=1" %> class="btn btn-default" role="button">Upload Grade File(excel)</a>
    			</div>
  				</div><!-- panel-body -->
			</div>
			<%
  							}else{
  			%>
  			<div class="aaa">
  				<div class="panel-heading">
   				<h3 class="panel-title">Final</h3>
  				</div><!-- panel-heading-->
  				<div class="panel-body">
  				<div class="col-md-4">
    			<%String addpaper = "addpaper.jsp?type=final&id="; %>
    			<a href=<%=addpaper+i+"&num_ass=1"%> class="btn btn-primary" role="button">Add Paper</a> 
    			</div>
  				</div><!-- panel-heading-->
			</div>
  			<%
					} 
  				}
			%> 
			
			
  		</div><!-- col-md-8 -->
  		<div class="col-md-4">
  			<div class="aaa">
  			<div class="panel-body">
      			<img src="img/touxiang.png" alt="..." class="img-thumbnail">
      			<div class="caption">
        		<h3><%=session.getAttribute("username") %></h3>
        		
        		<p>ID:<%=session.getAttribute("user_id") %></p>
        		<form action="/ZWorkshopProject/Analysis" method="post">
        		
<% int courseid=Integer.parseInt(course_id[i]); %><input style="display:none;" name="courseid" value="<%=courseid%>" >
<input style="display:none;" name="url_id"  value="<%=i %>" >

        		<p>
      			<input class="btn btn-primary" type="submit" name="Analysis CILO" value="Analysis CILO">
        		<a href="#" class="btn btn-default" role="button">Button</a>
        		</p>
        		</form>
      			</div>
      		</div><!-- panel panel-default -->
			</div><!-- panel-body-->
			<div class="aaa">
  			<div class="panel-body">
      			<img src="img/touxiang.png" alt="..." class="img-thumbnail">
      			<div class="caption">
        		<h3><%=ta_name %></h3>
        		
        		<p><%=course_ta[i] %></p>
        		<p>
        		</p>
      			</div>
      		</div><!-- panel panel-default -->
			</div><!-- panel-body-->
  		</div><!-- col-md-4 -->
	</div><!-- row -->
    
    
    
</body>
</html>