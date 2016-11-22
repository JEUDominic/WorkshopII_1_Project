
<%session.setAttribute("title","CILO Homepage"); %>

<%@ include file="header.jsp" %> 
    
    
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="home.jsp">Home<span class="sr-only">(current)</span></a></li>
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
  	</div><!-- /.container-fluid -->
  
  
	</nav><!-- /.navar -->
	</div><!-- header -->
    
    <div class="row">
  		<div class="col-md-8">
  			<div class="aaa">
  			<div class="panel-heading">
  				<h3 class="panel-title">My Course</h3>
  				</div><!-- panel-heading -->
  			<div class="panel-body">
  				<%
  					String course_id[] = (String[])session.getAttribute("course_id");
    				String course_name[] = (String[])session.getAttribute("course_name");
    				String course_period[] = (String[])session.getAttribute("course_period");
    				String course_ta[]= (String[])session.getAttribute("course_ta");
    				String coursenum = (String)session.getAttribute("course_num");
    				int course_num = Integer.parseInt(coursenum);
    				//int course_num=(int)session.getAttribute("course_num");
  					for(int i=0;i<course_num;i++){
  					String coursejsp = "course.jsp?id=";
  				%>
  				
  				<div class="list-group">
  					<a href=<%=coursejsp+i%> class="list-group-item"><p class="text-muted">Course 
  					<%=course_id[i]%>&nbsp;&nbsp;&nbsp;<%=course_name[i] %>(Dr. <%=session.getAttribute("username") %>)&nbsp;&nbsp;&nbsp;<%=course_period[i] %>
  					</p></a>
				</div>
				<% } %>
				
				
				<div class="col-md-4"></div>
				<nav>
  				<ul class="pagination">
    				<li><a href="#">&laquo;</a></li>
        				<li><a href="#">1</a></li>
        				<li><a href="#">2</a></li>
        				<li><a href="#">3</a></li>
        				<li><a href="#">4</a></li>
        				<li><a href="#">5</a></li>
        				<li><a href="#">&raquo;</a></li>
      				</ul>
    			</nav>
    			<div class="col-md-4"></div>
  			</div><!-- panel-body-->
			</div><!-- panel panel-default -->
			
  		</div><!-- col-md-8 -->
  		<div class="col-md-4">
  			<div class="aaa">
  			<div class="panel-body">
      			<img src="img/touxiang.png" alt="..." class="img-thumbnail">
      			<div class="caption">
        		<h3><%=session.getAttribute("username") %></h3>
        		
        		<p>ID:<%=session.getAttribute("user_id") %></p>
        		<p>
        		<a href="addcourse.jsp" class="btn btn-primary" role="button">Add Course</a> 
        		<a href="#" class="btn btn-default" role="button">Button</a>
        		</p>
      			</div>
      		</div><!-- panel panel-default -->
			</div><!-- panel-body-->
  		</div><!-- col-md-4 -->
	</div><!-- row -->
    
    
    
</body>
</html>