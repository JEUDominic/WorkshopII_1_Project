<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>

<%
	if(session == null)
	{
		response.sendRedirect("login.jsp");
	}else if(session.getAttribute("username") == null)
	{
		response.sendRedirect("login.jsp");
	}
	

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
        <li class="active"><a href="resultdata.jsp">Assignment 1 Grade<span class="sr-only">(current)</span></li>
      </ul>
      <form class="navbar-form" role="search">
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
  		<div class="col-md-12">
  			<div class="aaa">
  				<div class="panel-heading">
    			<h2><i> </i> ${course}</h2>
				</div><!-- panel-heading -->
				<div class="panel-body">
            <%
                Object result = request.getAttribute("resultData");
                //out.print("result is "+result);
                JSONArray array = (JSONArray) result;
                JSONObject jsonObj = null;
            
                
                int a=0;
                jsonObj = (JSONObject) array.get(a);
                int len=jsonObj.length();//获取问题数量(excel的总列数)
                //int total=0;//计算每个学生的总分
                
            %>
            
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID/No.que</th>
                        <%
                        	for(int m=0;m<len-2;m++){
                        %>
                        <th><% out.print("num"+(m+1)); %></th>
                        <%} %>
                        <th>total</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (int i = 0; i < array.length(); i++) {
                            jsonObj = (JSONObject) array.get(i);
                            
                    %>

                     <tr>
                        <td>
               
                            <%
                               out.print(jsonObj.get("ID/No.que"));
                            %>
 
                        </td>
                        <%
                            for(int j = 0; j<len-2; j++){  

                        %>
                            
                        <td>
                            <%
                               out.print(jsonObj.get("num"+(j+1)));
                            
                            %>
                        </td>
                      <% }%>
                        <td>
                            <%
                            out.print(jsonObj.get("total"));
                            %>
                        </td>
                    </tr>

                    <%
                        }
                    %>

                </tbody>
            </table>
       		</div><!-- panel-body-->
		</div><!-- panel panel-default -->	
  	</div><!-- col-md-12 -->
        
    
	</div><!-- row -->


</body>
</html>