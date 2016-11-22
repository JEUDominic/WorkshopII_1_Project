<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.io.PrintWriter"
    pageEncoding="UTF-8"%>
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

    <title><%=session.getAttribute("title") %></title>
  
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/main.css" rel="stylesheet" >
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <script src="http://canvasjs.com/assets/script/canvasjs.min.js"></script>
    <script type="text/javascript" src="http://static.fusioncharts.com/code/latest/fusioncharts.js"></script>
	<script type="text/javascript" src="http://static.fusioncharts.com/code/latest/themes/fusioncharts.theme.fint.js?cacheBust=56"></script>

    <link rel="SHORTCUT ICON" href="img/iconb.ico"> 
    <style type="text/css">
	 body{
	background-image: url(img/197a1921213445edebd7bffc16c9b681.jpg);
	}
	.aaa{
		background-color:rgba(255,255,255,0.5); 
		
	}
	</style>
    <script type="text/javascript">

     //简化document.getElementById()方法
     function $(obj) {
       return document.getElementById(obj);
     }
     //删除行
     function delRow(e) {
         var evt = e || event;
         var targetTable = $("addTable");
         targetTable.deleteRow(~~evt.target.parentNode.parentNode.cells[0].innerHTML);
         for (var i = 0; i<targetTable.rows.length; i++) {
             if (i != 0)
             targetTable.rows[i].cells[0].innerHTML = i;
         }
     }
      
     function addRow() {
         var targetTable = $("addTable");
         var index = targetTable.rows.length;
         var newRow = targetTable.insertRow(index);
         newRow.innerHTML = '<tr class="input"><td><input style="display:none;" name="num_que" value="'+index+'" >' + index + '</td>'
        +'<td><textarea class="form-control" rows="6" placeholder="Question '+ index + ' Content" name=content'+index+'></textarea></td>'
        +'<td><input type="text" class="form-control" placeholder="10" name=score'+index+'></td>'
        +'	<td>'
        +'		<div class="checkbox">'
        +'			<label>'
        +'			<input type="checkbox" id="inlineCheckbox1" name=cilo'+index+' value="1"> 1'
        +'			</label>'
        +'	</div>'
        +'		<div class="checkbox">'
        +'			<label>'
        +'			<input type="checkbox" id="inlineCheckbox2" name=cilo'+index+' value="2"> 2'
        +'			</label>'
        +'	</div>'
        +'		<div class="checkbox">'
        +'			<label>'
        +'			<input type="checkbox" id="inlineCheckbox3" name=cilo'+index+' value="3"> 3'
        +'			</label>'
        +'	</div>'
        +'		<td>'
        +'		<div class="checkbox">'
        +'			<label>'
        +'			<input type="checkbox" id="inlineCheckbox1" name=level'+index+' value="Knowledge"> Knowledge'
        +'			</label>'
        +'	</div>'
        +'		<div class="checkbox">'
        +'			<label>'
        +'			<input type="checkbox" id="inlineCheckbox2" name=level'+index+' value="Analysis"> Analysis'
        +'			</label>'
        +'	</div>'
        +'		<div class="checkbox">'
        +'			<label>'
        +'			<input type="checkbox" id="inlineCheckbox3" name=level'+index+' value="Application"> Application'
        +'			</label>'
        +'	</div>'
        +'		<div class="checkbox">'
        +'			<label>'
        +'			<input type="checkbox" id="inlineCheckbox4" name=level'+index+' value="Comprehension"> Comprehension'
        +'			</label>'
        +'	</div>'
        +'		<div class="checkbox">'
        +'			<label>'
        +'			<input type="checkbox" id="inlineCheckbox5" name=level'+index+' value="Synthesis"> Synthesis'
        +'			</label>'
        +'	</div>'
        +'		<div class="checkbox">'
        +'			<label>'
        +'			<input type="checkbox" id="inlineCheckbox6" name=level'+index+' value="Evaluation"> Evaluation'
        +'			</label>'
        +'	</div>'
        +'		</td>'
        +'		<td><button type="button" class="btn btn-default" onclick="delRow(event)">'
        +'			<span class="glyphicon glyphicon-remove"></span></button></td>'
        +'</tr>';
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
  