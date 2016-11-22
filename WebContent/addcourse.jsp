<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html xmlns:wb="http://open.weibo.com/wb">
<%
	if(session == null)
	{
		response.sendRedirect("login.jsp");
	}else if(session.getAttribute("username") == null)
	{
		response.sendRedirect("login.jsp");
	}
%>

<head>

	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>CILO Add Course</title>
  
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/main.css" rel="stylesheet" >
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    
    <link rel="SHORTCUT ICON" href="img/iconb.ico"> 
    <style type="text/css">
	 body{
	background-image: url(img/197a1921213445edebd7bffc16c9b681.jpg);
	}
	.aaa{
		background-color:rgba(255,255,255,0.5); 
		
	}
	</style>
<!-- 添加--> 
      <script type="text/javascript">

     //简化document.getElementById()方法
     function $(obj) {
       return document.getElementById(obj);
     }
     //删除行
     function delRow(e) {
         var evt = e || event;
         var targetTable = $("addTable1");
         targetTable.deleteRow(~~evt.target.parentNode.parentNode.cells[0].innerHTML);
         for (var i = 0; i<targetTable.rows.length; i++) {
             if (i != 0)
             targetTable.rows[i].cells[0].innerHTML = i;
         }
     }
      
     function addRow() {
         var targetTable = $("addTable1");
         var index = targetTable.rows.length;
         var newRow = targetTable.insertRow(index);
         newRow.innerHTML = '<tr class="input"><td><input style="display:none;" name="cilo_num" value="'+index+'" >'+index+'</td>'
    		+	'<td><textarea class="form-control" rows="3" placeholder="Cilo '+index+' Content" name="content'+index+'"></textarea></td>'
    		+	'<td><input type="text" class="form-control" placeholder="Alignment to Pilo" name="pilo_num'+index+'"></td>'
       		+	'<td><button type="button" class="btn btn-default" onclick="delRow(event)">'
  			+	'<span class="glyphicon glyphicon-remove"></span></button></td>'
  			+   '</tr>'; 
     }
        </script>
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
        <li  class="active"><a href="addcourse.jsp">Add Course<span class="sr-only">(current)</span></a></li>
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
  			<form class="form-horizontal" role="form"  action="/ZWorkshopProject/InsertNewCourse" method="post">		

<!-- 添加 teacher_id-->  <%! int teacher_id=1430003002; %><input style="display:none;" name="teacher_id" value="<%=teacher_id%>" >
						
<!-- 添加 ta_id-->  		<%! int ta_id=1430003003; %><input style="display:none;" name="ta_id" value="<%=ta_id%>" >
  				<div class="form-group">
      				<label for="inputCourseName3" class="col-sm-3 control-label">Course Name:</label>
      				<div class="col-sm-8">
        				<input type="coursename" class="form-control" id="inputCourseName3" placeholder="Course Name" name="course_name">
      				</div>
    			</div>
    				
    			<div class="form-group">
      				<label for="inputCoursePeriod3" class="col-sm-3 control-label">Course Period:</label>
      				<div class="col-sm-4">
						<select class="form-control" name="course_period">
  								<option value="20162">2016～2017上学期</option>
  								<option value="20171">2016～2017下学期</option>
  								<option value="20172">2017～2018上学期</option>
  								<option value="20181">2017～2018下学期</option>
						</select>      				
					</div>
    			</div>
    				
    			<div class="form-group">
    				<div class="row">
    				<div class="col-md-1"></div>
    				<div class="col-md-10">
    				<table class="table table-hover" id="addTable1">
  					<thead>
  						<th>NO.</th>
  						<th>Cilo Content</th>
  						<th style="width:160px">Alignment to Pilo<th>
  					</thead>
 <!-- 添加--> 		<tr>
 <!-- 添加--> 			<td><input style="display:none;" name="cilo_num" value="1" >1</td>
  <!-- 添加-->			<td><textarea class="form-control" rows="3" placeholder="Cilo 1 Content" name="content1"></textarea></td>
 <!-- 添加--> 			<td><input type="text" class="form-control" placeholder="Alignment to Pilo" name="pilo_num1"></td>
<!-- 添加-->  		</tr>

 <!-- 添加--> 		<tr>
 <!-- 添加--> 			<td><input style="display:none;" name="cilo_num" value="2" >2</td>
  <!-- 添加-->			<td><textarea class="form-control" rows="3" placeholder="Cilo 2 Content" name="content2"></textarea></td>
 <!-- 添加--> 			<td><input type="text" class="form-control" placeholder="Alignment to Pilo" name="pilo_num2"></td>
<!-- 添加-->  			<td><button type="button" class="btn btn-default" onclick="delRow(event)">
  							<span class="glyphicon glyphicon-remove"></span></button></td>
  							
 <!-- 添加-->		</tr>
 <!-- 添加--> 		<tr>
 <!-- 添加--> 			<td><input style="display:none;" name="cilo_num" value="3" >3</td>
  <!-- 添加-->			<td><textarea class="form-control" rows="3" placeholder="Cilo 3 Content" name="content3"></textarea></td>
 <!-- 添加--> 			<td><input type="text" class="form-control" placeholder="Alignment to Pilo" name="pilo_num3"></td>
<!-- 添加-->  			<td><button type="button" class="btn btn-default" onclick="delRow(event)">
  							<span class="glyphicon glyphicon-remove"></span></button></td>
  					</tr>
  <!--删除-->					
  					</table>
  					
  					<button type="button" class="btn btn-default" onclick="addRow()">
  					<span class="glyphicon glyphicon-plus"></span> Add </button>
  					
  					</div>
  					<div class="col-md-1"></div>
  					</div>
  	
  					
  				</div>
  				
  				<div class="form-group">
    				<div class="row">
    				<div class="col-md-3"></div>
    				<div class="col-md-6">
    				<table class="table table-hover">
  					<thead>
  						<th>No.</th>
  						<th>Assessment</th>
  						<th style="width: 200px">Weight<th>
  					</thead>
  					<tbody>
  						<td>1</td>
  						<td>Test</td>
  						<td><input type="text" placeholder="100 in total" name="test">%</td>
  					</tbody>
  					<tbody>
  						<td>2</td>
  						<td>Quiz</td>
  						<td><input type="text" placeholder="100 in total" name="quiz">%</td>
  					</tbody>
  					<tbody>
  						<td>3</td>
  						<td>Assignment</td>
  						<td><input type="text" placeholder="100 in total" name="assignment">%</td>
  					</tbody>
  					<tbody>
  						<td>4</td>
  						<td>Midterm Exam</td>
  						<td><input type="text" placeholder="100 in total" name="midterm">%</td>
  					</tbody>
  					<tbody>
  						<td>5</td>
  						<td>Final Exam</td>
  						<td><input type="text" placeholder="100 in total" name="final">%</td>
  					</tbody>
  					</table>
  					</div>
  					<div class="col-md-3"></div>
  					</div>
  				</div>
  					
  					
    			<div class="form-group">
      				<div class="col-sm-offset-5 col-sm-10">
 <!-- 添加-->       		<input class="btn btn-success" type="submit" name="Save" value="Save">
        				<input class="btn btn-default" type="reset" value="Reset">
  				    </div>
    			</div>
  			</form>
  				
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