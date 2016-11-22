<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@  page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement,java.sql.ResultSet, 
java.sql.SQLException,java.sql.Statement,java.util.Date, javax.servlet.ServletException"%>
<% 		
int id = Integer.parseInt(request.getParameter("id"));
String courseid[] = (String[])session.getAttribute("course_id");
String coursename[] = (String[])session.getAttribute("course_name");
String course_ta[]= (String[])session.getAttribute("course_ta");
String numass=(String)request.getParameter("num_ass");

  		//连接数据库
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/information?"
				+ "user=root&password=123456");
		
		int cilo_num=0;//计算cilo的数量
		Statement stmt1=conn.createStatement();
		   ResultSet rs1=stmt1.executeQuery("select count(DISTINCT cilo_num) from course_cilo where course_id='"+courseid[id]+"' ");
		   while(rs1.next())
		   {
			  cilo_num=rs1.getInt(1);
		   }
		   rs1.close();
		   stmt1.close();
		   conn.close();
%>
    
<% session.setAttribute("title","CILO Add Paper "); %>
<%@ include file="header.jsp" %> 

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="home.jsp">Home</a></li>
        <li class="active"><a href=<%="addpaper.jsp?type="+request.getParameter("type")+"&id="+request.getParameter("id")+"&num_ass="+request.getParameter("num_ass")%>>Add Paper<span class="sr-only">(current)</span></a></li>
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
	</div><!-- header-->
    
     <div class="row">
  		<div class="col-md-8">
  			<div class="aaa">
  			<div class="panel-body">
  			<form class="form-horizontal" role="form" action="/ZWorkshopProject/InsertNewPaper" method="post">	
  			<div class="form-group">
  			</div>
  			<div class="form-group">
  				<div class="row">
    				<div class="col-md-1"></div>
    				<div class="col-md-10">
    				
    				<table class="table table-hover" id="addTable">
  					<thead>
  						<th>NO.</th>
  						<th>Question Content                </th>
  						<th style="width: 8px">Weight</th>
  						<th>Applied Cilo</th>
  						<th>Cognitive levels</th>
  					</thead>
<!-- 添加-->   		<tr>
<input style="display:none;" name="url" value="<%=id%>" >
<!-- 添加 course_id-->  		<% int course_id=Integer.parseInt(courseid[id]); %><input style="display:none;" name="course_id" value="<%=course_id%>" >
<!-- 添加 type--> 			<% String type=request.getParameter("type"); %><input style="display:none;" name="type" value="<%=type%>" >
<!-- 添加 num_ass--> 		<% int num_ass=Integer.parseInt(numass); %><input style="display:none;" name="num_ass" value="<%=num_ass%>" >
				<% for(int i=1;i<3;i++)  {%>
<!-- 添加 num_que-->  	<td><input style="display:none;" name="num_que" value="<%=i %>" ><%out.print(i); %></td>
<!-- 添加 content-->  	<td><textarea class="form-control" rows="6" placeholder="Question <%=i %> Content" name="content<%=i %>"></textarea></td>
<!-- 添加 score-->  		<td><input type="text" class="form-control" placeholder="10" name="score<%=i %>"></td>
  						<td>
  						<%for(int j=1;j<cilo_num+1;j++) {%>
  						<div class="checkbox">
  							<label>
<!-- 添加 cilo-->  		<input type="checkbox" name="cilo<%=i %>" value="<%=j %>"> <%out.print(j); %>
  							</label>
						</div>
  						<%} %>
						</div>
  						</td>
  						<td>
  						<div class="checkbox">
  							<label>
<!-- 添加 level-->  		<input type="checkbox" name="level<%=i %>" value="Knowledge"> Knowledge
  							</label>
						</div>
  						<div class="checkbox">
  							<label>
 <!-- 添加 level-->		<input type="checkbox" name="level<%=i %>" value="Analysis"> Analysis
  							</label>
						</div>
  						<div class="checkbox">
  							<label>
 <!-- 添加 level--> 		<input type="checkbox" name="level<%=i %>" value="Application"> Application
  							</label>
						</div>
  						<div class="checkbox">
  							<label>
 <!-- 添加 level--> 		<input type="checkbox" name="level<%=i %>" value="Comprehension"> Comprehension
  							</label>
						</div>
  						<div class="checkbox">
  							<label>
<!-- 添加 level-->  		<input type="checkbox" name="level<%=i %>" value="Synthesis"> Synthesis
  							</label>
						</div>
				 		<div class="checkbox">
  							<label>
<!-- 添加 level--> 		<input type="checkbox" name="level<%=i %>" value="Evaluation"> Evaluation
  							</label>
						</div>
  						</td>
  						
  						<td><button type="button" class="btn btn-default" onclick="delRow(event)">
  							<span class="glyphicon glyphicon-remove"></span></button></td>
  						</td>
			 		
<!-- 添加--> 		</tr><!-- The first line -->
  					<% }%>
  			
 
  					
  					</table>
  					<button type="button" class="btn btn-default" onclick="addRow()">
  					<span class="glyphicon glyphicon-plus"></span> Add </button>
  					
  					   
  					</div>
  					<div class="col-md-1"></div>
  				</div>
  			</div>	
  			<div class="form-group">
      			<div class="col-sm-offset-5 col-sm-10">
 <!-- 添加-->        <input class="btn btn-success" type="submit" name="Save" value="Save">
        			<input class="btn btn-default" type="reset" value="Reset">
  				  </div>
    		</div>
  			
  			</form><!-- form -->
  			</div><!-- panel-body-->
			</div><!-- panel panel-default -->
			
  		</div><!-- col-md-8 -->
  		<div class="col-md-4">
  			<div class="aaa">
  			<div class="panel-body">
      			<img src="img/touxiang.png" alt="..." class="img-thumbnail">
      			<div class="caption">
        		<h3>Teacher</h3>
        		<p>...</p>
        		<p>
        		<a href="#" class="btn btn-primary" role="button">Button</a> 
        		<a href="#" class="btn btn-default" role="button">Button</a>
        		</p>
      			</div>
      		</div><!-- panel panel-default -->
			</div><!-- panel-body-->
			<div class="aaa">
  		<div class="panel-body">
  			<div class="row">
    				<div class="col-md-1"></div>
    				<div class="col-md-10">
    				<table class="table table-hover">
  					<thead>
  						<th>NO.</th>
  						<th>Cilo Content</th>
  					</thead>
  					<tbody>
  						<td>1</td>
  						<td><p>Cilo 1 Content</p></td>
  					</tbody>
  					<tbody>
  						<td>2</td>
  						<td><p>Cilo 2 Content</p></td>
  					</tbody>
  					<tbody>
  						<td>3</td>
  						<td><p>Cilo 3 Content</p></td>
  					</tbody>
  					</table>
  					</div>
  					<div class="col-md-1"></div>
  			</div>
  		</div><!-- panel panel-default -->
		</div><!-- panel-body-->
  		</div><!-- col-md-4 -->
  		
  		
  		
	</div><!-- row -->
    
    
</body>
</html>