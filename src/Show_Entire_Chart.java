import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Servlet implementation class ShowChart
 */
@WebServlet("/Show_Entire_Chart")
public class Show_Entire_Chart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MySQLAccess mySQLAccess = new MySQLAccess();
    public void init() throws ServletException {
		mySQLAccess = new MySQLAccess();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Show_Entire_Chart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    	//计算一个assessment的cilo分数  cilo1,2,3,...对应的总分, b[1]-cilo1; b[2]-cilo2...
    	//final and midterm boxplot
    	
    	String course_id="1";
    	String cilo_num="1";
    	String num_ass="1";
    	String data=null;//final and midterm boxplot
    	String data1=null;//final and midterm boxplot
    	
    	try{
    		int m=0;
    		m=mySQLAccess.getCourse_cilo(course_id);//计算这门课cilo的数量   111
    		String[] stu_id=mySQLAccess.getStudentid(course_id);
    		
    		int percentage1_midterm=mySQLAccess.getPercentage(course_id,"midterm");
    		int percentage2_final=mySQLAccess.getPercentage(course_id,"final");
    		double[][] a_midterm=new double[stu_id.length][m];
    		double[][] b_final=new double[stu_id.length][m];
    		double[][] c_exam=new double[stu_id.length][m];
    		for(int i=0;i<stu_id.length;i++)
    		{
    			a_midterm[i]=mySQLAccess.cilo_score(m,stu_id[i],course_id, "midterm", "1");//一个学生的各个cilo的得分数，a[0]=cilo1，midterm只有一个，num_ass=1
    		}
    		for(int i=0;i<stu_id.length;i++)
    		{
    			b_final[i]=mySQLAccess.cilo_score(m,stu_id[i],course_id, "final", "1");//一个学生的各个cilo的得分数，a[0]=cilo1，final只有一个，num_ass=1
    		}
    		for(int i=0;i<stu_id.length;i++)
    		{
    			for(int j=0;j<m;j++)
    			{
    				c_exam[i][j]=(a_midterm[i][j]*percentage1_midterm/100)+(b_final[i][j]*percentage2_final/100);
    			}
    		}
    		
    		//求数量
    		m=m+1;//二维数组多加一列存学号
    		int num1=mySQLAccess.getCoursework_cilo(course_id, "assignment");//assignment 的数量?
    		String[] num_assignment=new String[num1];
    		for(int i=0;i<num1;i++)
    		{
    			num_assignment[i]=String.valueOf(i+1);
    			System.out.println("num_assignment["+i+"] is "+ num_assignment[i]);
    		}
    		System.out.println("num1 is "+num1);
    		int num2=mySQLAccess.getCoursework_cilo(course_id, "quiz");//quiz 的数量
    		String[] num_quiz=new String[num2];
    		for(int i=0;i<num2;i++)
    		{
    			num_quiz[i]=String.valueOf(i+1);
    		}
    		System.out.println("num2 is "+num2);
    		int num3=mySQLAccess.getCoursework_cilo(course_id, "test");//test 的数量
    		String[] num_test=new String[num3];
    		for(int i=0;i<num3;i++)
    		{
    			num_test[i]=String.valueOf(i+1);
    		}
    		System.out.println("num3 is "+num3);
    		
    		//求assignment成绩
    		double[][] a=new double[stu_id.length*num1][m];
    		int s1=0;
    		for(int i=0;i<num1;i++){
    			for(int j=0;j<stu_id.length;j++){
    			a[s1]=mySQLAccess.cilo_score_coursework(m,stu_id[j], course_id, "assignment", num_assignment[i]);//求assignment的各个cilo的总分数，midterm只有一个，num_ass=1
    		//	System.out.println("a["+s+"][0] is "+a[s][0]+"a["+s+"][1] is "+a[s][1]+"a["+s+"][2] is "+a[s][2]+"a["+s+"][3] is "+a[s][3]);
    			s1++;
    			}
    		}
    	
    		double[][] a1=new double[stu_id.length][m];//a[][],第一列是学号，第二列是cilo1，第三列是cilo2。。。；以学生数量为单位，0-18是assignment1，19-37是assignment2。。。
    		
    		for(int i=0;i<stu_id.length;i++)
    		{	
    			
    			for(int q=0;q<m;q++){
    				if(q==0)
    				{
    					a1[i][q]=Integer.valueOf(stu_id[i]);
    				}
    				else
    				{
	    				int sum=0;
						int p=i;
	    				for(int j=0;j<num1;j++)
	    				{
	    					sum+=a[p][q];
	    					p=p+stu_id.length;
	    				}
	    				a1[i][q]=sum;
    				}
    				//System.out.println("a1["+i+"]["+q+"] is "+a1[i][q]);
    			}
    		}
    			
    		int percentage1=mySQLAccess.getPercentage(course_id,"assignment");
    		
    		//quiz
    		double[][] b=new double[stu_id.length*num2][m];
    		int s2=0;
    		for(int i=0;i<num2;i++){
    			for(int j=0;j<stu_id.length;j++){
    			b[s2]=mySQLAccess.cilo_score_coursework(m,stu_id[j], course_id, "quiz", num_quiz[i]);//求assignment的各个cilo的总分数，midterm只有一个，num_ass=1
    			s2++;
    			}
    		}
    		double[][] b1=new double[stu_id.length][m];//a[][],第一列是学号，第二列是cilo1，第三列是cilo2。。。；以学生数量为单位，0-18是assignment1，19-37是assignment2。。。
    		
    		for(int i=0;i<stu_id.length;i++)
    		{	
    			
    			for(int q=0;q<m;q++){
    				if(q==0)
    				{
    					b1[i][q]=Integer.valueOf(stu_id[i]);
    				}
    				else
    				{
	    				int sum=0;
						int p=i;
	    				for(int j=0;j<num2;j++)
	    				{
	    					sum+=b[p][q];
	    					p=p+stu_id.length;
	    				}
	    				b1[i][q]=sum;
    				}
    				//System.out.println("a1["+i+"]["+q+"] is "+a1[i][q]);
    			}
    		}
    			
    		int percentage2=mySQLAccess.getPercentage(course_id,"quiz");
    		//test
    		double[][] c=new double[stu_id.length*num3][m];
    		int s3=0;
    		for(int i=0;i<num3;i++){
    			for(int j=0;j<stu_id.length;j++){
    			c[s3]=mySQLAccess.cilo_score_coursework(m,stu_id[j], course_id, "test", num_test[i]);//求assignment的各个cilo的总分数，midterm只有一个，num_ass=1
    			s3++;
    			}
    		}
    		
    		double[][] c1=new double[stu_id.length][m];//a[][],第一列是学号，第二列是cilo1，第三列是cilo2。。。；以学生数量为单位，0-18是assignment1，19-37是assignment2。。。
    		
    		for(int i=0;i<stu_id.length;i++)
    		{	
    			
    			for(int q=0;q<m;q++){
    				if(q==0)
    				{
    					c1[i][q]=Integer.valueOf(stu_id[i]);
    				}
    				else
    				{
	    				int sum=0;
						int p=i;
	    				for(int j=0;j<num3;j++)
	    				{
	    					sum+=c[p][q];
	    					p=p+stu_id.length;
	    				}
	    				c1[i][q]=sum;
    				}
    			}
    		}
    			
    		int percentage3=mySQLAccess.getPercentage(course_id,"test");
    		//求总和
    		double[][] d=new double[stu_id.length][m-1];
    		for(int i=0;i<stu_id.length;i++)
    		{
	    		for(int j=0;j<m-1;j++)
	    		{
	    			d[i][j]=c_exam[i][j]+(a1[i][(j+1)]*percentage1/100)+(b1[i][(j+1)]*percentage2/100)+(c1[i][(j+1)]*percentage3/100);
	    		}		    		
    		}
    		
    		data1=" [{ \"category\" :[";
    		for(int i=0;i<m-1;i++){
    			data1+="{ \"label\" : \"cilo"+(i+1)+"\" }";
    			if(i!=m-2)
    			{
    				data1+=",";
    			}
    			else
    			{
    				data1+="] } ]";
    			}
    		}
    		
    	
    		data=" [ {\"seriesname\": \"marks\",\"lowerboxcolor\": \"#008ee4\", \"upperboxcolor\": \"#6baa01\", \"data\": [  ";
    		for(int i=0;i<m-1;i++){
    			data+="{ \"value\" : \" ";
    			for(int j=0;j<stu_id.length;j++)
    			{
	    			data+=d[j][i];
	    			if(j!=stu_id.length-1)
	    			{
	    				data+=",";
	    			}
	    			else
	    			{
	    				data+=" \" }";
	    			}
    			}
    			if(i!=m-2)
    			{
    				data+=",";
    			}
    			else
    			{
    				data+="] }]";
    			}
    		}
    				
		}catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
    	
    	
    	
    	//final and midterm pie chart
    	
    
    	String data2=null; //final and midterm pie chart
     	
    	try{
    		int m=0;
    		m=mySQLAccess.getCourse_cilo(course_id);//计算这门课cilo的数量   111
    		
    		double[] a_midterm=mySQLAccess.cilo_total(m, course_id, "midterm", "1");//求midterm的各个cilo的总分数，midterm只有一个，num_ass=1
    		double[] b_final=mySQLAccess.cilo_total(m, course_id, "final", "1");//求final的各个cilo的总分数，final只有一个，num_ass=1
    		int percentage1_midterm=mySQLAccess.getPercentage(course_id,"midterm");
    		int percentage2_final=mySQLAccess.getPercentage(course_id,"final");
    		double[] c_exam=new double[m];
    		for(int i=0;i<m;i++){
    			c_exam[i]=(a_midterm[i]*percentage1_midterm/100)+(b_final[i]*percentage2_final/100);
    		}		
    		//Assignment, quiz, test
    		int num1=mySQLAccess.getCoursework_cilo(course_id, "assignment");//assignment 的数量?
    		String[] num_assignment=new String[num1];
    		for(int i=0;i<num1;i++)
    		{
    			num_assignment[i]=String.valueOf(i+1);
    			System.out.println("num_assignment["+i+"] is "+ num_assignment[i]);
    		}
    		System.out.println("num1 is "+num1);
    		int num2=mySQLAccess.getCoursework_cilo(course_id, "quiz");//quiz 的数量
    		String[] num_quiz=new String[num2];
    		for(int i=0;i<num2;i++)
    		{
    			num_quiz[i]=String.valueOf(i+1);
    		}
    		System.out.println("num2 is "+num2);
    		int num3=mySQLAccess.getCoursework_cilo(course_id, "test");//test 的数量
    		String[] num_test=new String[num3];
    		for(int i=0;i<num3;i++)
    		{
    			num_test[i]=String.valueOf(i+1);
    		}
    		System.out.println("num3 is "+num3);
    		
    		double[][] a=new double[num1][m];
    		for(int i=0;i<num1;i++){
    			a[i]=mySQLAccess.cilo_total(m, course_id, "assignment", num_assignment[i]);//求assignment的各个cilo的总分数，midterm只有一个，num_ass=1
    			System.out.println("a["+i+"][0] is "+a[i][0]+"a["+i+"][1] is "+a[i][1]+"a["+i+"][2] is "+a[i][2]);
    		}
    		double[][] a1=new double[1][m];
    		for(int i=0;i<m;i++)
    		{	
    			
    			for(int j=0;j<num1;j++)
    			{
    			a1[0][i]+=a[j][i];
    			//a1[0][0]+=a[i][0];
    			//a1[0][1]+=a[i][1];
    			//a1[0][2]+=a[i][2];j
    			}
    		}
    			
    		int percentage1=mySQLAccess.getPercentage(course_id,"assignment");
    		
    		double[][] b=new double[num2][m];
    		for(int i=0;i<num2;i++){
        			b[i]=mySQLAccess.cilo_total(m, course_id, "quiz", num_quiz[i]);//求quiz的各个cilo的总分数，midterm只有一个，num_ass=1
        			System.out.println("b[i][0] is "+b[i][0]+"b[i][1] is "+b[i][1]+"b[i][2] is "+b[i][2]);
    		}
    		int percentage2=mySQLAccess.getPercentage(course_id,"quiz");
    		
    		double[][] b1=new double[1][m];
    		for(int i=0;i<m;i++)
    		{	
    			for(int j=0;j<num2;j++)
    			{
    			b1[0][i]+=b[j][i];
    			//a1[0][0]+=a[i][0];
    			//a1[0][1]+=a[i][1];
    			//a1[0][2]+=a[i][2]; 
    			}
    		}
    		
    		double[][] c=new double[num3][m];
    		for(int i=0;i<num3;i++){
        			c[i]=mySQLAccess.cilo_total(m, course_id, "test", num_test[i]);//求test的各个cilo的总分数，midterm只有一个，num_ass=1
        			System.out.println("c[i][0] is "+c[i][0]+"c[i][1] is "+c[i][1]+"c[i][2] is "+c[i][2]);
    		}
    		int percentage3=mySQLAccess.getPercentage(course_id,"test");
    		
    		double[][] c1=new double[1][m];
    		for(int i=0;i<m;i++)
    		{	
    			for(int j=0;j<num3;j++)
    			{
    			c1[0][i]+=c[j][i];
    			//a1[0][0]+=a[i][0];
    			//a1[0][1]+=a[i][1];
    			//a1[0][2]+=a[i][2]; 
    			}
    		}
    		
    		double[][] d=new double[1][m];
   
	    		for(int j=0;j<m;j++){
	    			//d[j][i]=(a[j][i]*percentage1/100)+(b[j][i]*percentage2/100)+(c[j][i]*percentage3/100);
	    			d[0][j]+=c_exam[j]+(a1[0][j]*percentage1/100)+(b1[0][j]*percentage2/100)+(c1[0][j]*percentage3/100);
	    		}	
    		
    		data2="[";
        	for(int i=0; i<m;i++){
        		data2+="{ y:"+d[0][i]+", indexLabel: \"cilo"+(i+1)+"\" }";
        		if(i!=m-1)
        		{
        			data2+=", ";
        		}
        		else{
        			data2+=" ]";
        		}
        	}
		}
    	
    	catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
		
 
    //	String data3="[{ \"category\": [{ \"label\": \"cilo1\"}, { \"label\": \"cilo2\" }, {\"label\": \"cilo3\"}] }]";
     
        System.out.println(data1);
        System.out.println(data);
       RequestDispatcher rd = request.getRequestDispatcher("Entire_Course_PieChart.jsp");  
      //  RequestDispatcher rd = request.getRequestDispatcher("Entire_Course_Boxplot.jsp"); 
        request.setAttribute("chartData2",data2);//存值  
        request.setAttribute("chartData1",data1);//存值 
        request.setAttribute("chartData",data);//存值 
        rd.forward(request,response);//开始跳转 


        //request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
    

}