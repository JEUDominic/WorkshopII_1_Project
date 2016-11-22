<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%session.setAttribute("title","Analysis"); %>

<%@page import="JDBC.*" import="Table.*"%>
<% 
	int id = Integer.parseInt(request.getParameter("id"));
	String courseid[] = (String[])session.getAttribute("course_id");
	String course_name[] = (String[])session.getAttribute("course_name");
	String course_ta[]= (String[])session.getAttribute("course_ta");
	String ta_name = DatabaseOperation.getUser(Integer.parseInt(course_ta[id]));
	int[] percentage = DatabaseOperation.getAssPer(Integer.parseInt(courseid[id]));
	
	//int course_id=Integer.getInteger(courseid[id]);
	String course_id=courseid[id];
%>


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
  				<h3 class="panel-title">Analysis</h3>
  				</div><!-- panel-heading -->
  			<div class="aaa">
  			
 <%@  page import="java.io.*, java.awt.Color,com.lowagie.text.*,  com.lowagie.text.pdf.*"%> 
<%@  page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement,java.sql.ResultSet, java.sql.SQLException,java.sql.Statement,java.util.Date, javax.servlet.ServletException;"%>
 			
<%  
  
  		//连接数据库
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost/information?"
				+ "user=root&password=123456");
		
		int cilo_num=0;//计算cilo的数量
		Statement stmt1=conn.createStatement();
		   ResultSet rs1=stmt1.executeQuery("select count(DISTINCT cilo_num) from course_cilo where course_id='"+course_id+"' ");
		   while(rs1.next())
		   {
			  cilo_num=rs1.getInt(1);
		   }
		   rs1.close();
		   stmt1.close();
 			
		   try
			{
 %>
  			<h3 style="text-align: center">Report of Course</h3>
  			<h4 style="text-align: center">CILO alignment in final exam and course-work</h4>
  			<br/><br/>
  			<h4>1.Course Intended Learning Outcomes(CILOs) and alignment to Program Intended Learning Outcomes(PILOs)</h4>
  			<table border="1" style="text-align: center"  width="800">
	  			<tr>
	  				<td  width="200">
	  					No.
	  				</td>
	  				
	  				<td  width="200">
	  					CILOs
	  				</td>
	  				
	  				<td  width="200">
	  					Alignment to PLIOs
	  				</td>
	  			</tr>
	  	<%  int NO=1;
		    for(int i=0;i<cilo_num;i++){
		%>
	  			<tr>
	  				<td  width="200">
	  					<% 
	  					String str=String.valueOf(NO);
		        		out.println(str);
		        		NO++;
		        		%>
			            
	  				</td>
	  				
	  				<td  width="200">
	  					<%
	  					String str1="";
		        		Statement stmt=conn.createStatement();
		        		   ResultSet rs=stmt.executeQuery("select content from course_cilo where course_id='"+course_id+"' and cilo_num='"+(NO-1)+"'");
		        		   while(rs.next())
		        		   {
		        			  str1=rs.getString("content");
		        		   }
		        		   rs.close();
		        		   stmt.close();
		          		
		        		   out.println(str1);
			            %>
	  				</td>
	  				
	  				<td  width="200">
	  					<%
	  					String str2="";
		        		Statement stmt2=conn.createStatement();
		        		   ResultSet rs2=stmt2.executeQuery("select pilo_num from course_cilo where course_id='"+course_id+"' and cilo_num='"+(NO-1)+"'");
		        		   while(rs2.next())
		        		   {
		        			  str2=rs2.getString("pilo_num");
		        		   }
		        		   rs2.close();
		        		   stmt2.close();
		        		   
		        		   out.println(str2);
	  					%>
	  				</td>
	  			</tr>
	  	<%} %>
  			</table>
  			
  			<h4>2. Course assessment pattern</h4>
  			<table border="1" style="text-align: center"  width="800">
  				<tr>
	  				<td  width="200">
	  					Assessment
	  				</td>
	  				
	  				<td  width="200">
	  					Percentage
	  				</td>
	  				
	  				<td  width="200">
	  					Remarks
	  				</td>
	  			</tr>
	  			
	  			<tr>
	  				<td width="200">
	  				Examination
	  				</td>
	  				
	  				<td width="200">
	  				<% 
	  				int fin=0;
		        	int mid=0;
		        	Statement stmt=conn.createStatement();
	        		   ResultSet rs=stmt.executeQuery("select final from course_info where course_id='"+course_id+"'");
	        		   while(rs.next())
	        		   {
	        			  fin=rs.getInt(1);
	        		   }
	        		   rs.close();
	        		   stmt.close();
	        		   
	        		   Statement stmt2=conn.createStatement();
	        		   ResultSet rs2=stmt2.executeQuery("select midterm from course_info where course_id='"+course_id+"'");
	        		   while(rs2.next())
	        		   {
	        			 mid=rs2.getInt(1);
	        		   }
	        		   rs2.close();
	        		   stmt2.close();
	        		   
	        		   int exam=fin+mid;
	        		   int course=100-exam;
	        		 
				       out.println(exam+"%");
				       
	  				%>
	  				</td>
	  				
	  				<td width="200">
	  				<% 
	  				int mid2=0;
		        	Statement stmt3=conn.createStatement();
	        		   ResultSet rs3=stmt3.executeQuery("select count(distinct num_que) from question where course_id='"+course_id+"' and type='midterm' and num_ass=1 ");
	        		   while(rs3.next())
	        		   {
	        			 mid2=rs3.getInt(1);
	        		   }
	        		   rs3.close();
	        		   stmt3.close();
	        		   
	        		 int fin2=0;
	        		   Statement stmt4=conn.createStatement();
	        		   ResultSet rs4=stmt4.executeQuery("select count(distinct num_que) from question where course_id='"+course_id+"' and type='final' and num_ass=1");
	        		   while(rs4.next())
	        		   {
	        			  fin2=rs4.getInt(1);
	        		   }
	        		   rs4.close();
	        		   stmt4.close();
		        	
				      out.println("Midterm has "+mid2+" questions, total 100 marks - Answer all question");
				   
				     out.println("final has "+fin2+" questions, total 100 marks - Answer all question");
	  				%>
	  				</td>
	  			</tr>
	  			
	  			<tr>
	  				<td width="200">
	  				Course-work
	  				</td>
	  				
	  				<td width="200">
	  				<% out.println(course+"%");%>
	  				</td>
	  				
	  				<td width="200">
	  				<% 
	  					int assignment=0;
		        	Statement stmt5=conn.createStatement();
	        		   ResultSet rs5=stmt5.executeQuery("select assignment from course_info where course_id='"+course_id+"'");
	        		   while(rs5.next())
	        		   {
	        			  assignment=rs5.getInt(1);
	        		   }
	        		   rs5.close();
	        		   stmt5.close();
		        	
	        		   int quiz=0;
			        	Statement stmt6=conn.createStatement();
		        		   ResultSet rs6=stmt6.executeQuery("select quiz from course_info where course_id='"+course_id+"'");
		        		   while(rs6.next())
		        		   {
		        			  quiz=rs6.getInt(1);
		        		   }
		        		   rs6.close();
		        		   stmt6.close();
		        	
		        		   int test=0;
				        	Statement stmt7=conn.createStatement();
			        		   ResultSet rs7=stmt7.executeQuery("select test from course_info where course_id='"+course_id+"'");
			        		   while(rs7.next())
			        		   {
			        			  test=rs7.getInt(1);
			        		   }
			        		   rs7.close();
			        		   stmt7.close();
		        	
				       out.println("Assignment : "+ assignment +"% "+"\n"+" Quiz : "+quiz+"% "+"\n"+"Test :"+test+"%");
				     %>
	  				</td>
	  			</tr>
  			</table>
  			
  			<h4>3. CILO alignment in examination</h4>
  			<h5>(i). Coverage of different CILOs in the examination and student's achievement</h5>
  		
  				<div id="Exam_cilo_piechart" style="height: 400px; width: 40%;"></div>
  				
				<div id="Exam_cilo_boxplot" style="height: 400px; width: 30%;"></div>

			
  			<h5>(ii). Coverage of different cognitive levels in the examination and student's achievement</h5>
  				<div id="Exam_level_piechart" style="height: 400px; width: 40%;"></div>
  				
				<div id="Exam_level_boxplot" style="height: 400px; width: 30%;"></div>
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			
  			<h4>4. CILO alignment in course-work</h4>
  			<table border="1" style="text-align: center"  width="800">
  				<tr>
	  				<td  width="200">
	  					Course-work
	  				</td>
	  				
	  				<td  width="200">
	  					CILO Alignment
	  				</td>
	  				
	  			</tr>
	  			
	  			<tr>
	  				<td  width="200">
	  					Quiz
	  				</td>
	  				
	  				<td  width="200">
	  					<%
	  					Statement stmt8=conn.createStatement();
		        		   ResultSet rs8=stmt8.executeQuery("select count(distinct cilo) from question_cilo where course_id='"+course_id+"' and type='quiz' ");
		        		  int num=0;
		        		   while(rs8.next())
		        		   {
		        			  num=rs8.getInt(1);
		        		   }
		        		   rs8.close();
		        		   stmt8.close();	
			    		
			    		String[] quiz2=new String[num];
			    		Statement stmt9=conn.createStatement();
		        		   ResultSet rs9=stmt9.executeQuery("select distinct cilo from question_cilo where course_id='"+course_id+"' and type='quiz' ");
		        		  int a=0;
		        		   while(rs9.next())
		        		   {
		        			  quiz2[a]=rs9.getString("cilo");
		        			  a++;
		        		   }
		        		   rs9.close();
		        		   stmt9.close();		
		        		   
		        		   String s="CILO ";
		        		   for(int j=0;j<quiz2.length;j++)
		        		   {
		        			   s+=quiz2[j];
		        			   if(j!=quiz2.length-1)
		        			   {
		        				   s+=", ";
		        			   }
		        		   }
			    	
			            out.println(s);
			            
	  					%>
	  				</td>
	  				
	  			</tr>
	  			
	  			<tr>
	  				<td  width="200">
	  					Assignment
	  				</td>
	  				
	  				<td  width="200">
	  				<% 
	  					Statement stmt10=conn.createStatement();
	        		   ResultSet rs10=stmt10.executeQuery("select count(distinct cilo) from question_cilo where course_id='"+course_id+"' and type='assignment' ");
	        		  int num2=0;
	        		   while(rs10.next())
	        		   {
	        			  num2=rs10.getInt(1);
	        		   }
	        		   rs10.close();
	        		   stmt10.close();	
		    		
		    		String[] assignment2=new String[num];
		    		Statement stmt11=conn.createStatement();
	        		   ResultSet rs11=stmt11.executeQuery("select distinct cilo from question_cilo where course_id='"+course_id+"' and type='assignment' ");
	        		  int a1=0;
	        		   while(rs11.next())
	        		   {
	        			  assignment2[a1]=rs11.getString("cilo");
	        			  a1++;
	        		   }
	        		   rs11.close();
	        		   stmt11.close();		
	        		   
	        		   String str="CILO ";
	        		   for(int j=0;j<assignment2.length;j++)
	        		   {
	        			   str+=assignment2[j];
	        			   if(j!=assignment2.length-1)
	        			   {
	        				   str+=", ";
	        			   }
	        		   }
		            out.println(str);
		           %>
	  				</td>
	  				
	  			</tr>
	  			
	  			<tr>
	  				<td  width="200">
	  					Test
	  				</td>
	  				
	  				<td  width="200">
	  					<%
	  					Statement stmt12=conn.createStatement();
		        		   ResultSet rs12=stmt12.executeQuery("select count(distinct cilo) from question_cilo where course_id='"+course_id+"' and type='test' ");
		        		  int num3=0;
		        		   while(rs12.next())
		        		   {
		        			  num3=rs12.getInt(1);
		        		   }
		        		   rs12.close();
		        		   stmt12.close();	
			    		
			    		String[] test2=new String[num3];
			    		Statement stmt13=conn.createStatement();
		        		   ResultSet rs13=stmt13.executeQuery("select distinct cilo from question_cilo where course_id='"+course_id+"' and type='test' ");
		        		  int a2=0;
		        		   while(rs13.next())
		        		   {
		        			  test2[a2]=rs13.getString("cilo");
		        			  a2++;
		        		   }
		        		   rs13.close();
		        		   stmt13.close();		
		        		   
		        		   String str2="CILO ";
		        		   for(int j=0;j<test2.length;j++)
		        		   {
		        			   str2+=test2[j];
		        			   if(j!=test2.length-1)
		        			   {
		        				   str2+=", ";
		        			   }
		        		   }

			            out.println(str2);
			            
	  					%>
	  				</td>
	  				
	  			</tr>
  			
  			</table>
  			
  			
  			<div id="Coursework_cilo_piechart" style="height: 400px; width: 40%;"></div>
  				
			<div id="Coursework_cilo_boxplot" style="height: 400px; width: 30%;"></div>
  			
  			
  			
  			
  			
  			 <h4>5. Overall achievement on different CILOs in the entire course</h4>
  			
  			<div id="Entire_cilo_piechart" style="height: 400px; width: 40%;"></div>
  				
			<div id="Entire_cilo_boxplot" style="height: 400px; width: 30%;"></div>
  			
  			
  	<%			    
		
	   } catch (IOException ioe)
			{
		    System.err.println(ioe.getMessage());
		}
		//关闭数据库
		conn.close();
		//关闭document
		
				
	%> 
			</div><!-- panel-body-->
			</div><!-- panel panel-default -->
			
  		</div><!-- col-md-8 -->
  		<div class="col-md-4">
  			<div class="panel panel-default">
  			<div class="panel-body">
      			<img src="img/touxiang.png" alt="..." class="img-thumbnail">
      			<div class="caption">
        		<h3><%=session.getAttribute("username") %></h3>
        		
        		<p>ID:<%=session.getAttribute("user_id") %></p>
        		

        		<p>
        		<%String download="Output_Report.jsp?id="; %>
        		<a href=<%=download+id %> class="btn btn-primary" role="button">Download</a> 
        		<a href="#" class="btn btn-default" role="button">Button</a>
        		</p>
      			</div>
      		</div><!-- panel panel-default -->
			</div><!-- panel-body-->
  		</div><!-- col-md-4 -->
	</div><!-- row -->

<script type="text/javascript">
		<% 
			String Exam_cilo_piechart = (String)request.getAttribute("Exam_cilo_piechart");  
		%>
		<% 
			String Exam_cilo_boxplot2 = (String)request.getAttribute("Exam_cilo_boxplot2");  
			String Exam_cilo_boxplot1 = (String)request.getAttribute("Exam_cilo_boxplot1");
		%>
		
		<% 
			String Exam_level_piechart = (String)request.getAttribute("Exam_level_piechart");  
		%>
		<%  
			String Exam_level_boxplot1 = (String)request.getAttribute("Exam_level_boxplot1");
		%>
		
		<% 
			String Coursework_cilo_piechart = (String)request.getAttribute("Coursework_cilo_piechart");  
		%>
		<%  
			String Coursework_cilo_boxplot1 = (String)request.getAttribute("Coursework_cilo_boxplot1");
			String Coursework_cilo_boxplot2 = (String)request.getAttribute("Coursework_cilo_boxplot2");
		%>
		
		<% 
			String Entire_cilo_piechart = (String)request.getAttribute("Entire_cilo_piechart");  
		%>
		<%  
			String Entire_cilo_boxplot1 = (String)request.getAttribute("Entire_cilo_boxplot1");
			String Entire_cilo_boxplot2 = (String)request.getAttribute("Entire_cilo_boxplot2");
		%>
		
		window.onload=function(){
			Exam_cilo_piechart();
			Exam_level_piechart();
			Coursework_cilo_piechart();
			Entire_cilo_piechart();
		}
			//exam cilo pie chart		
			
						 function Exam_cilo_piechart() {
							    var chart = new CanvasJS.Chart("Exam_cilo_piechart",
							    {
							        theme: "theme2",
							        title:{
							            text: "Coverage of different CILOs in Examination"
							        },  
							        data: [
							            {       
							                type: "pie",
							                showInLegend: true,
							                indexLabel: "{name}: <strong>#percent %</strong>",
							                legendText: "{indexLabel}",
							                indexLabel: "#percent%", 
							               	toolTipContent: "{y} -- #percent %",
							                dataPoints:<%out.print(Exam_cilo_piechart);%>
							            }
							        ]
							    });
							    chart.render();
							
							}
							
			//exam cilo boxplot
														
							FusionCharts.ready(function(){
							    var fusioncharts = new FusionCharts({
							    type: 'boxandwhisker2d',
							    renderAt: 'Exam_cilo_boxplot',
							    width: '500',
							    height: '350',
							    dataFormat: 'json',
							    dataSource: {
							        "chart": {
							            "caption": "Coverage of difference cognitive levels in exam paper",
							            "subcaption": "",
							            "xAxisName": "Cognitive",
							            "YAxisName": "Percentage of marks",
							            "numberPrefix": "",
							            "theme": "fint",
							            "usePlotGradientColor": "",
							            "showValues": "0",
							            "showMean": "1"
							        },
							        "categories": <%out.print(Exam_cilo_boxplot1);%>,
							        "dataset":<%out.print(Exam_cilo_boxplot2);%>
							    }
							}
							);
							    fusioncharts.render();
							});
							
		//exam level pie chart
		
							 function Exam_level_piechart() {
								    var chart = new CanvasJS.Chart("Exam_level_piechart",
								    {
								        theme: "theme2",
								        title:{
								            text: "Gaming Consoles Sold in 2012"
								        },        
								        data: [
								            {       
								                type: "pie",
								                showInLegend: true,
								                toolTipContent: "{y} - #percent %",
								                yValueFormatString: "#,##0,,.## Million",
								                legendText: "{indexLabel}",
								                dataPoints:<%out.print(Exam_level_piechart);%>
								            }
								        ]
								    });
								    chart.render();
								}
		//exam level boxplot
							 FusionCharts.ready(function(){
								    var fusioncharts = new FusionCharts({
								    type: 'boxandwhisker2d',
								    renderAt: 'Exam_level_boxplot',
								    width: '500',
								    height: '350',
								    dataFormat: 'json',
								    dataSource: {
								        "chart": {
								            "caption": "Coverage of difference cognitive levels in exam paper",
								            "subcaption": "",
								            "xAxisName": "Cognitive",
								            "YAxisName": "Percentage of marks",
								            "numberPrefix": "",
								            "theme": "fint",
								            "usePlotGradientColor": "",
								            "showValues": "0",
								            "showMean": "1"
								        },
								        "categories": [{
								            "category": [{
								                "label": "Knowledge"
								            }, {
								                "label": "Comprehension"
								            }, {
								                "label": "Application"
								            }, {
								                "label": "Analysis"
								            }, {
								                "label": "Synthesis"
								            }, {
								                "label": "Education"
								            }]
								        }],
								        "dataset":<%out.print(Exam_level_boxplot1);%>
								    }
								}
								);
								    fusioncharts.render();
								});
		
		//coursework cilo pirchart
							  function Coursework_cilo_piechart() {
					             var chart = new CanvasJS.Chart("Coursework_cilo_piechart",
					               {
					               		theme: "theme2",
					                    title:{
					                        text: "Coverage of different CILOs in course work"
					                    },  
					                    data: [
					                        {       
					                            type: "pie",
					                            showInLegend: true,
					                            indexLabel: "{name}: <strong>#percent %</strong>",
					                            legendText: "{indexLabel}",
					                            indexLabel: "#percent%", 
					                           	toolTipContent: "{y} -- #percent %",
					                            dataPoints:<%out.print(Coursework_cilo_piechart);%>
					                        }
					                    ]
					                });
					                chart.render();
					           }
		//coursework cilo boxplot			 
							 FusionCharts.ready(function(){
					                var chart = new FusionCharts({
					                type: 'boxandwhisker2d',
					                renderAt: 'Coursework_cilo_boxplot',
					                dataFormat: 'json',
					                dataSource: {
					                    "chart": {
					                        "caption": "Coverage of difference cognitive levels in exam paper",
					                        "xAxisName": "Cognitive",
					                        "YAxisName": "Percentage of marks",
					                        "numberPrefix": "",
					                        "theme": "fint",
					                        "usePlotGradientColor": "",
					                        "showValues": "0",
					                        "showMean": "1"
					                    },
					                    "categories": <%out.print(Coursework_cilo_boxplot1);%>,
					                    "dataset":<%out.print(Coursework_cilo_boxplot2);%>

					                }
					            }
					            );
					                chart.render();
					            })
					            
		//entire cilo boxplot
							 function Entire_cilo_piechart() {
								    var chart = new CanvasJS.Chart("Entire_cilo_piechart",
								    {
								        theme: "theme2",
								        title:{
								            text: "Overall coverage of different CILOs"
								        },        
								        data: [
								            {       
								            	type: "pie",
								                showInLegend: true,
								                indexLabel: "{name}: <strong>#percent %</strong>",
								                legendText: "{indexLabel}",
								                indexLabel: "#percent%", 
								               	toolTipContent: "{y} -- #percent %",
								                dataPoints:<%out.print(Entire_cilo_piechart);%>
								            }
								        ]
								    });
								    chart.render();
								
								}
		
		
		//entire cilo boxplot
						FusionCharts.ready(function(){
							    var fusioncharts = new FusionCharts({
							    type: 'boxandwhisker2d',
							    renderAt: 'Entire_cilo_boxplot',
							    width: '500',
							    height: '350',
							    dataFormat: 'json',
							    dataSource: {
							        "chart": {
							            "caption": "Coverage of difference cognitive levels in exam paper",
							            "subcaption": "",
							            "xAxisName": "Cognitive",
							            "YAxisName": "Percentage of marks",
							            "numberPrefix": "",
							            "theme": "fint",
							            "usePlotGradientColor": "",
							            "showValues": "0",
							            "showMean": "1"
							        },
							        "categories": <%out.print(Entire_cilo_boxplot1);%>,
							        "dataset":<%out.print(Entire_cilo_boxplot2);%>
							    }
							}
							);
							    fusioncharts.render();
							});
		
		</script>
</body>


</html>