<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="JDBC.*" import="Table.*"%>

<%
	if(session == null)
	{
		response.sendRedirect("login.jsp");
	}else if(session.getAttribute("username") == null)
	{
		response.sendRedirect("login.jsp");
	}
%>
<%
int i = Integer.parseInt(request.getParameter("id"));
String action = (String)request.getParameter("action");
String courseid[] = (String[])session.getAttribute("course_id");
String course_name[] = (String[])session.getAttribute("course_name");
String course_ta[]= (String[])session.getAttribute("course_ta");
String ta_name = DatabaseOperation.getUser(Integer.parseInt(course_ta[i]));

String type=(String)request.getParameter("type"); 
String servlet="/ZWorkshopProject/ImportData"+"?id="+i+"&type="+type+"&course_id="+courseid[i]+"&num_ass="+(i+1);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<!-- <link rel="stylesheet" type="text/css" href="asset/semantic.min.css"> -->
	<script src="asset/jquery.min.js"></script>
	<script src="asset/semantic.min.js"></script>

    <title>Impot Data</title>
  
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/main.css" rel="stylesheet" >
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    
    <link rel="SHORTCUT ICON" href="img/iconb.ico"> 
  </head>
<body style="margin:10px">
	
	
    <div class="header">

	<!-- NAVIGATION -->
	<nav class="navbar navbar-inverse navbar-default navbar-fixed-top">
	
	<div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    	<div class="navbar-header">
      		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
       	 	<span class="sr-only">Toggle navigation</span>
        	<span class="icon-bar"></span>
        	<span class="icon-bar"></span>
        	<span class="icon-bar"></span>
      	</button>
      	<a class="navbar-brand" href="#">CILO SYSTEM</a>
    	</div>
    	<!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="home.jsp">Home</a></li>
        <li ><a href="course.jsp">Course 1</li>
       
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
  				<div class="panel-heading">
    				<h2 ><i ></i> Import Data Though Excel</h2>
				</div><!-- panel-heading -->
  			<div class="panel-body">
   		 	<div>


                <form class="ui form" enctype="multipart/form-data" action=<%=servlet %>  method="post">
            <!--  //enctype="multipart/form-data" 用于文件上传，有了这个，request传值不可用 -->  
            

                    <div class="col-md-4">
                        <label>Import Excel File</label> 
                    </div>
                    <div class="col-md-4">
                        <input type="file" name="file" placeholder="">
                        <input type="hidden" name="action" value="importAction">    
                    </div>

                    <div class="col-md-4">
 <!-- tianjia -->   <button class="btn btn-primary"  type="submit">Submit</button>
                    </div>
              </form>
        
            <div class="column">${message}</div>

    		</div>
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
        		<a href="addcourse.html" class="btn btn-primary" role="button">Add Course</a> 
        		<a href="#" class="btn btn-default" role="button">Button</a>
        		</p>
      			</div>
      		</div><!-- panel panel-default -->
			</div><!-- panel-body-->
  		</div><!-- col-md-4 -->
	</div><!-- row -->


</body>
</html>