<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

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

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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

		
		BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
		// 第一步，创建document对象
		Rectangle rectPageSize = new Rectangle(PageSize.A4);
		
		//下面代码设置页面横置
		//rectPageSize = rectPageSize.rotate();
		
		//创建document对象并指定边距
		Document doc = new Document(rectPageSize,50,50,50,50);
		Document document = new Document();
		try
		{
		    // 第二步,将Document实例和文件输出流用PdfWriter类绑定在一起
		    //从而完成向Document写，即写入PDF文档
		    PdfWriter.getInstance(document,new FileOutputStream("/Users/apple/Desktop/Report.pdf"));
		    //第3步,打开文档
		    document.open();
		    //第3步,向文档添加文字. 文档由段组成
		    Paragraph par1=new Paragraph("Report of Course");
			par1.setAlignment(1);
		    document.add(par1);
		 
		 	Paragraph par2=new Paragraph("CILO alignment in final exam and course-work");
		    par2.setAlignment(1);
		    document.add(par2);
		 
		    document.add(new Paragraph("\n"));
		    document.add(new Paragraph("\n"));
		    Paragraph par3=new Paragraph("1.Course Intended Learning Outcomes(CILOs) and alignment to Program Intended Learning Outcomes(PILOs)");
		    document.add(par3);
		    
		    document.add(new Paragraph("\n"));
		    PdfPTable table = new PdfPTable(3);
		    int NO=1;
		    for(int i=0;i<4*cilo_num;i++)
		    {
		        if (i == 0)
		        {
		            PdfPCell cell = new PdfPCell();
		            Paragraph par4=new Paragraph("No.");
		            par4.setAlignment(1);
		            cell.addElement(par4);
		            table.addCell(cell);
		        }
		        else if(i==1){
		        	PdfPCell cell = new PdfPCell();
		            Paragraph par4=new Paragraph("CILOs");
		            par4.setAlignment(1);
		            cell.addElement(par4);
		            table.addCell(cell);
		        }
		        else if(i==2)
		        {
		            PdfPCell cell = new PdfPCell();
		            Paragraph par4=new Paragraph("Alignment to PILOS");
		            par4.setAlignment(1);
		            cell.addElement(par4);
		            table.addCell(cell);
		        }
		        else if(i%3==0)
		        {
		        		String str=String.valueOf(NO);
		        		PdfPCell cell = new PdfPCell();
			            Paragraph par6=new Paragraph(str);
			            par6.setAlignment(1);
			            cell.addElement(par6);
			            table.addCell(cell);
			            NO++;
		        }
		        else if(i%3==1)
		        {
		        	String str="";
	        		Statement stmt=conn.createStatement();
	        		   ResultSet rs=stmt.executeQuery("select content from course_cilo where course_id='"+course_id+"' and cilo_num='"+(NO-1)+"'");
	        		   while(rs.next())
	        		   {
	        			  str=rs.getString("content");
	        		   }
	        		   rs.close();
	        		   stmt.close();
	          		
	        		PdfPCell cell = new PdfPCell();
		            Paragraph par6=new Paragraph(str);
		            par6.setAlignment(1);
		            cell.addElement(par6);
		            table.addCell(cell);
		        }
		        else if(i%3==2)
		        {
		        	String str="";
	        		Statement stmt=conn.createStatement();
	        		   ResultSet rs=stmt.executeQuery("select pilo_num from course_cilo where course_id='"+course_id+"' and cilo_num='"+(NO-1)+"'");
	        		   while(rs.next())
	        		   {
	        			  str=rs.getString("pilo_num");
	        		   }
	        		   rs.close();
	        		   stmt.close();
		        	
	        		   PdfPCell cell = new PdfPCell();
			            Paragraph par7=new Paragraph(str);
			            par7.setAlignment(1);
			            cell.addElement(par7);
			            table.addCell(cell);
		        }
		        else
		        {
		        	PdfPCell cell = new PdfPCell();
		            cell.addElement(new Paragraph("12"));
		            table.addCell(cell);
		        }
		        
		    }
		    document.add(table);
		    
		    
		    document.add(new Paragraph("\n"));
		    document.add(new Paragraph("\n"));
		    
		    Paragraph par4=new Paragraph("2. Course assessment pattern");
		    document.add(par4);
		    
		    document.add(new Paragraph("\n"));
		    PdfPTable table2 = new PdfPTable(3);
		  
		    int course=0;;
		    for(int i=0;i<9;i++)
		    {
		    	if (i == 0)
		        {
		            PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph("Assessment");
		            par.setAlignment(1);
		            cell.addElement(par);
		            table2.addCell(cell);
		        }
		        else if(i==1){
		        	PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph("Pencertage");
		            par.setAlignment(1);
		            cell.addElement(par);
		            table2.addCell(cell);
		        }
		        else if(i==2)
		        {
		            PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph("Remarks");
		            par.setAlignment(1);
		            cell.addElement(par);
		            table2.addCell(cell);
		        }
		        else if(i==3)
		        {
		        	 PdfPCell cell = new PdfPCell();
			         Paragraph par=new Paragraph("Examination");
			         par.setAlignment(1);
			         cell.addElement(par);
			         table2.addCell(cell);
		        }
		        else if(i==4)
		        {
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
	        		   course=100-exam;
	        		   PdfPCell cell = new PdfPCell();
				       Paragraph par=new Paragraph(exam+"%");
				       par.setAlignment(1);
				       cell.addElement(par);
				       table2.addCell(cell);
		        }
		        else if(i==5)
		        {
		        	int mid=0;
		        	Statement stmt2=conn.createStatement();
	        		   ResultSet rs2=stmt2.executeQuery("select count(distinct num_que) from question where course_id='"+course_id+"' and type='midterm' and num_ass=1 ");
	        		   while(rs2.next())
	        		   {
	        			 mid=rs2.getInt(1);
	        		   }
	        		   rs2.close();
	        		   stmt2.close();
	        		   
	        		 int fin=0;
	        		   Statement stmt=conn.createStatement();
	        		   ResultSet rs=stmt.executeQuery("select count(distinct num_que) from question where course_id='"+course_id+"' and type='final' and num_ass=1");
	        		   while(rs.next())
	        		   {
	        			  fin=rs.getInt(1);
	        		   }
	        		   rs.close();
	        		   stmt.close();
		        	
		        	 PdfPCell cell = new PdfPCell();
				       Paragraph par=new Paragraph("Midterm has "+mid+" questions, total 100 marks - Answer all question "+"\n"+" final has "+fin+" questions, total 100 marks - Answer all question");
				       par.setAlignment(1);
				       cell.addElement(par);
				       table2.addCell(cell);
		        }
		        else if(i==6)
		        {
		        	PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph("Course-work");
		            par.setAlignment(1);
		            cell.addElement(par);
		            table2.addCell(cell);
		        }
		        else if(i==7)
		        {
		        	PdfPCell cell = new PdfPCell();
				       Paragraph par=new Paragraph(course+"%");
				       par.setAlignment(1);
				       cell.addElement(par);
				       table2.addCell(cell);
		        }
		        else if(i==8)
		        {
		        	int assignment=0;
		        	Statement stmt=conn.createStatement();
	        		   ResultSet rs=stmt.executeQuery("select assignment from course_info where course_id='"+course_id+"'");
	        		   while(rs.next())
	        		   {
	        			  assignment=rs.getInt(1);
	        		   }
	        		   rs.close();
	        		   stmt.close();
		        	
	        		   int quiz=0;
			        	Statement stmt2=conn.createStatement();
		        		   ResultSet rs2=stmt2.executeQuery("select quiz from course_info where course_id='"+course_id+"'");
		        		   while(rs2.next())
		        		   {
		        			  quiz=rs2.getInt(1);
		        		   }
		        		   rs2.close();
		        		   stmt2.close();
		        	
		        		   int test=0;
				        	Statement stmt3=conn.createStatement();
			        		   ResultSet rs5=stmt3.executeQuery("select test from course_info where course_id='"+course_id+"'");
			        		   while(rs5.next())
			        		   {
			        			  test=rs5.getInt(1);
			        		   }
			        		   rs5.close();
			        		   stmt3.close();
		        	
		        		PdfPCell cell = new PdfPCell();
				       Paragraph par=new Paragraph("Assignment : "+ assignment +"% "+"\n"+" Quiz : "+quiz+"% "+"\n"+"Test :"+test+"%");
				       par.setAlignment(1);
				       cell.addElement(par);
				       table2.addCell(cell);
		        }
		    	
		    }
		    document.add(table2);
		    
		    document.add(new Paragraph("\n"));
		    document.add(new Paragraph("\n"));
		    Paragraph par5=new Paragraph("3. CILO alignment in examination");
		    document.add(par5);
		    
			document.add(new Paragraph("\n"));
		    Paragraph par6=new Paragraph("(i). Coverage of different CILOs in the examination and student's achievement");
		    document.add(par6);
		    
//CHART
/*
		   	Image jpg = Image.getInstance("/Users/apple/Desktop/Exam_boxplot.png");
  			jpg.setAlignment(Image.ALIGN_CENTER);
   			document.add(jpg);
   			
   			Image jpg1 = Image.getInstance("/Users/apple/Desktop/Exam_piechart.png");
  			jpg1.setAlignment(Image.ALIGN_CENTER);
   			document.add(jpg1);
  */
	    
		    document.add(new Paragraph("\n"));
		    Paragraph par7=new Paragraph("(ii). Coverage of different cognitive levels in the examination and student's achievement");
		    document.add(par7);
		    
		    
 //CHART
 /*
		   	Image jpg2 = Image.getInstance("/Users/apple/Desktop/Level_boxplot.png");
  			jpg2.setAlignment(Image.ALIGN_CENTER);
   			document.add(jpg2);
   			
   			Image jpg3 = Image.getInstance("/Users/apple/Desktop/Level_piechart.png");
  			jpg3.setAlignment(Image.ALIGN_CENTER);
   			document.add(jpg3);
  */
		    
		    
		    
		    document.add(new Paragraph("\n"));
		    Paragraph par8=new Paragraph("4. CILO alignment in course-work");
		    document.add(par8);
		    
		    document.add(new Paragraph("\n"));
		    PdfPTable table3 = new PdfPTable(2);
		    for(int i=0;i<8;i++)
		    {
		    	
		    	if(i==0)
		    	{
		    		PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph("Course-work");
		            par.setAlignment(1);
		            cell.addElement(par);
		            table3.addCell(cell);
		    	}
		    	else if(i==1)
		    	{
		    		PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph("CILO alignment");
		            par.setAlignment(1);
		            cell.addElement(par);
		            table3.addCell(cell);
		    	}
		    	else if(i==2)
		    	{
		    		PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph("Quiz");
		            par.setAlignment(1);
		            cell.addElement(par);
		            table3.addCell(cell);
		    	}
		    	else if(i==3)
		    	{
		    		Statement stmt3=conn.createStatement();
	        		   ResultSet rs3=stmt3.executeQuery("select count(distinct cilo) from question_cilo where course_id='"+course_id+"' and type='quiz' ");
	        		  int num=0;
	        		   while(rs3.next())
	        		   {
	        			  num=rs3.getInt(1);
	        		   }
	        		   rs3.close();
	        		   stmt3.close();	
		    		
		    		String[] quiz=new String[num];
		    		Statement stmt=conn.createStatement();
	        		   ResultSet rs=stmt.executeQuery("select distinct cilo from question_cilo where course_id='"+course_id+"' and type='quiz' ");
	        		  int a=0;
	        		   while(rs.next())
	        		   {
	        			  quiz[a]=rs.getString("cilo");
	        			  a++;
	        		   }
	        		   rs.close();
	        		   stmt.close();		
	        		   
	        		   String str="CILO ";
	        		   for(int j=0;j<quiz.length;j++)
	        		   {
	        			   str+=quiz[j];
	        			   if(j!=quiz.length-1)
	        			   {
	        				   str+=", ";
	        			   }
	        		   }
		    		PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph(str);
		            par.setAlignment(1);
		            cell.addElement(par);
		            table3.addCell(cell);
		    	}
		    	else if(i==4)
		    	{
		    		PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph("Assignment");
		            par.setAlignment(1);
		            cell.addElement(par);
		            table3.addCell(cell);
		    	}
		    	else if(i==5)
		    	{
		    		Statement stmt3=conn.createStatement();
	        		   ResultSet rs3=stmt3.executeQuery("select count(distinct cilo) from question_cilo where course_id='"+course_id+"' and type='assignment' ");
	        		  int num=0;
	        		   while(rs3.next())
	        		   {
	        			  num=rs3.getInt(1);
	        		   }
	        		   rs3.close();
	        		   stmt3.close();	
		    		
		    		String[] assignment=new String[num];
		    		Statement stmt=conn.createStatement();
	        		   ResultSet rs=stmt.executeQuery("select distinct cilo from question_cilo where course_id='"+course_id+"' and type='assignment' ");
	        		  int a=0;
	        		   while(rs.next())
	        		   {
	        			  assignment[a]=rs.getString("cilo");
	        			  a++;
	        		   }
	        		   rs.close();
	        		   stmt.close();		
	        		   
	        		   String str="CILO ";
	        		   for(int j=0;j<assignment.length;j++)
	        		   {
	        			   str+=assignment[j];
	        			   if(j!=assignment.length-1)
	        			   {
	        				   str+=", ";
	        			   }
	        		   }
		    		PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph(str);
		            par.setAlignment(1);
		            cell.addElement(par);
		            table3.addCell(cell);
		    	}
		    	else if(i==6)
		    	{
		    		PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph("Test");
		            par.setAlignment(1);
		            cell.addElement(par);
		            table3.addCell(cell);
		    	}
		    	else if(i==7)
		    	{
		    		Statement stmt3=conn.createStatement();
	        		   ResultSet rs3=stmt3.executeQuery("select count(distinct cilo) from question_cilo where course_id='"+course_id+"' and type='test' ");
	        		  int num=0;
	        		   while(rs3.next())
	        		   {
	        			  num=rs3.getInt(1);
	        		   }
	        		   rs3.close();
	        		   stmt3.close();	
		    		
		    		String[] test=new String[num];
		    		Statement stmt=conn.createStatement();
	        		   ResultSet rs=stmt.executeQuery("select distinct cilo from question_cilo where course_id='"+course_id+"' and type='test' ");
	        		  int a=0;
	        		   while(rs.next())
	        		   {
	        			  test[a]=rs.getString("cilo");
	        			  a++;
	        		   }
	        		   rs.close();
	        		   stmt.close();		
	        		   
	        		   String str="CILO ";
	        		   for(int j=0;j<test.length;j++)
	        		   {
	        			   str+=test[j];
	        			   if(j!=test.length-1)
	        			   {
	        				   str+=", ";
	        			   }
	        		   }
		    		PdfPCell cell = new PdfPCell();
		            Paragraph par=new Paragraph(str);
		            par.setAlignment(1);
		            cell.addElement(par);
		            table3.addCell(cell);
		    	}
		    }
		    document.add(table3);
		    
		    
//CHART
/*
		   	Image jpg4 = Image.getInstance("/Users/apple/Desktop/Coursework_boxplot.png");
  			jpg4.setAlignment(Image.ALIGN_CENTER);
   			document.add(jpg4);
   			
   			Image jpg5 = Image.getInstance("/Users/apple/Desktop/Coursework_piechart.png");
  			jpg5.setAlignment(Image.ALIGN_CENTER);
   			document.add(jpg5);
  
	*/	    
		    document.add(new Paragraph("\n"));
		    Paragraph par9=new Paragraph("5. Overall achievement on different CILOs in the entire course");
		    document.add(par9);   
		    
		    document.add(new Paragraph("\n"));
		    
//CHART
/*
		   	Image jpg6 = Image.getInstance("/Users/apple/Desktop/Entire_boxplot.png");
  			jpg6.setAlignment(Image.ALIGN_CENTER);
   			document.add(jpg6);
   			
   			Image jpg7 = Image.getInstance("/Users/apple/Desktop/Entire_piechart.png");
  			jpg7.setAlignment(Image.ALIGN_CENTER);
   			document.add(jpg7);
 */
		    
		}
		catch (DocumentException de)
		{
		    System.err.println(de.getMessage());
		}
		catch (IOException ioe)
		{
		    System.err.println(ioe.getMessage());
		}
		//关闭数据库
		conn.close();
		//关闭document
		document.close();
		
		System.out.println("生成HelloPdf123成功！");
		String forward="";
		forward="course.jsp?id="+id;
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request,response);
				
	%> 
	
	 
</html>