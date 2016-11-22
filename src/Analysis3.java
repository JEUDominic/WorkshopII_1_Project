

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Servlet implementation class Analysis3
 */
@WebServlet("/Analysis3")
public class Analysis3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private MySQLAccess mySQLAccess = new MySQLAccess();
	    public void init() throws ServletException {
			mySQLAccess = new MySQLAccess();
		}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analysis3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
     
		String course_id="1";
    	String num_ass="1";
    	String cilo_num="1";
     	
    	//exam boxplot
    	String Exam_cilo_boxplot2=null;//final and midterm boxplot
    	String Exam_cilo_boxplot1=null;//final and midterm boxplot
    	try{
    		int m=0;
    		m=mySQLAccess.getCourse_cilo(course_id);//计算这门课cilo的数量   111
    		String[] stu_id=mySQLAccess.getStudentid(course_id);//获取学生数量及id
    		int percentage1=mySQLAccess.getPercentage(course_id,"midterm");
    		int percentage2=mySQLAccess.getPercentage(course_id,"final");
    		double[][] a=new double[stu_id.length][m];
    		double[][] b=new double[stu_id.length][m];
    		double[][] c=new double[stu_id.length][m];
    		for(int i=0;i<stu_id.length;i++)
    		{
    		a[i]=mySQLAccess.cilo_score(m,stu_id[i],course_id, "midterm", "1");//一个学生的各个cilo的得分数，a[0]=cilo1，midterm只有一个，num_ass=1
    		}
    		for(int i=0;i<stu_id.length;i++)
    		{
    		b[i]=mySQLAccess.cilo_score(m,stu_id[i],course_id, "final", "1");//一个学生的各个cilo的得分数，a[0]=cilo1，final只有一个，num_ass=1
    		}
    		for(int i=0;i<stu_id.length;i++)
    		{
    			for(int j=0;j<m;j++)
    			{
    				c[i][j]=(a[i][j]*percentage1/100)+(b[i][j]*percentage2/100);
    			}
    		}
    		
    		Exam_cilo_boxplot1=" [{ \"category\" :[";
    		for(int i=0;i<m;i++){
    			Exam_cilo_boxplot1+="{ \"label\" : \"cilo"+(i+1)+"\" }";
    			if(i!=m-1)
    			{
    				Exam_cilo_boxplot1+=",";
    			}
    			else
    			{
    				Exam_cilo_boxplot1+="] } ]";
    			}
    		}
    		Exam_cilo_boxplot2=" [ {\"seriesname\": \"marks\",\"lowerboxcolor\": \"#008ee4\", \"upperboxcolor\": \"#6baa01\", \"data\": [  ";
    		for(int i=0;i<stu_id.length;i++){
    			Exam_cilo_boxplot2+="{ \"value\" : \" ";
    			for(int j=0;j<m;j++)
    			{
    				Exam_cilo_boxplot2+=c[i][j];
	    			if(j!=m-1)
	    			{
	    				Exam_cilo_boxplot2+=",";
	    			}
	    			else
	    			{
	    				Exam_cilo_boxplot2+=" \" }";
	    			}
    			}
    			if(i!=stu_id.length-1)
    			{
    				Exam_cilo_boxplot2+=",";
    			}
    			else
    			{
    				Exam_cilo_boxplot2+="] }]";
    			}
    		}
    				
		}catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
    	

     	//exam level boxplot
     	
     	String Exam_level_boxplot1=null;//final and midterm boxplot
     	 
    	try{
    		int m=6;
    		String[] stu_id=mySQLAccess.getStudentid(course_id);//获取学生数量及id
    		int percentage1=mySQLAccess.getPercentage(course_id,"midterm");
    		int percentage2=mySQLAccess.getPercentage(course_id,"final");
    		double[][] a=new double[stu_id.length][m];
    		double[][] b=new double[stu_id.length][m];
    		double[][] c=new double[stu_id.length][m];
    		for(int i=0;i<stu_id.length;i++)
    		{
    		a[i]=mySQLAccess.level_score(m,stu_id[i],course_id, "midterm", "1");//一个学生的各个cilo的得分数，a[0]=cilo1，midterm只有一个，num_ass=1
    		}
    		for(int i=0;i<stu_id.length;i++)
    		{
    		b[i]=mySQLAccess.level_score(m,stu_id[i],course_id, "final", "1");//一个学生的各个cilo的得分数，a[0]=cilo1，final只有一个，num_ass=1
    		}
    		for(int i=0;i<stu_id.length;i++)
    		{
    			for(int j=0;j<m;j++)
    			{
    				c[i][j]=(a[i][j]*percentage1/100)+(b[i][j]*percentage2/100);
    			}
    		}
    		
    		Exam_level_boxplot1=" [ {\"seriesname\": \"marks\",\"lowerboxcolor\": \"#008ee4\", \"upperboxcolor\": \"#6baa01\", \"data\": [  ";
    		for(int i=0;i<stu_id.length;i++){
    			Exam_level_boxplot1+="{ \"value\" : \" ";
    			for(int j=0;j<m;j++)
    			{
    				Exam_level_boxplot1+=c[i][j];
	    			if(j!=m-1)
	    			{
	    				Exam_level_boxplot1+=",";
	    			}
	    			else
	    			{
	    				Exam_level_boxplot1+=" \" }";
	    			}
    			}
    			if(i!=stu_id.length-1)
    			{
    				Exam_level_boxplot1+=",";
    			}
    			else
    			{
    				Exam_level_boxplot1+="] }]";
    			}
    		}
    				
		}catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
    	
//Coursework cilo boxplot
     	
    	String Coursework_cilo_boxplot2=null;//final and midterm boxplot
    	String Coursework_cilo_boxplot1=null;//final and midterm boxplot
    	
    	try{
    		int m=0;
    		m=mySQLAccess.getCourse_cilo(course_id);//计算这门课cilo的数量   111
    		m=m+1;//二维数组多加一列存学号
    		String[] stu_id=mySQLAccess.getStudentid(course_id);
    		
    		//求数量
    		int num1=mySQLAccess.getCoursework_cilo(course_id, "assignment");//assignment 的数量?
    		String[] num_assignment=new String[num1];
    		for(int i=0;i<num1;i++)
    		{
    			num_assignment[i]=String.valueOf(i+1);
    		}
    		int num2=mySQLAccess.getCoursework_cilo(course_id, "quiz");//quiz 的数量
    		String[] num_quiz=new String[num2];
    		for(int i=0;i<num2;i++)
    		{
    			num_quiz[i]=String.valueOf(i+1);
    		}
    		int num3=mySQLAccess.getCoursework_cilo(course_id, "test");//test 的数量
    		String[] num_test=new String[num3];
    		for(int i=0;i<num3;i++)
    		{
    			num_test[i]=String.valueOf(i+1);
    		}
    		
    		//求assignment成绩
    		double[][] a=new double[stu_id.length*num1][m];
    		int s1=0;
    		for(int i=0;i<num1;i++){
    			for(int j=0;j<stu_id.length;j++){
    			a[s1]=mySQLAccess.cilo_score_coursework(m,stu_id[j], course_id, "assignment", num_assignment[i]);//求assignment的各个cilo的总分数，midterm只有一个，num_ass=1
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
	    			d[i][j]=(a1[i][(j+1)]*percentage1/100)+(b1[i][(j+1)]*percentage2/100)+(c1[i][(j+1)]*percentage3/100);
	    		}		    		
    		}
    		
    		Coursework_cilo_boxplot1=" [{ \"category\" :[";
    		for(int i=0;i<m-1;i++){
    			Coursework_cilo_boxplot1+="{ \"label\" : \"cilo"+(i+1)+"\" }";
    			if(i!=m-2)
    			{
    				Coursework_cilo_boxplot1+=",";
    			}
    			else
    			{
    				Coursework_cilo_boxplot1+="] } ]";
    			}
    		}
    		
    	
    		Coursework_cilo_boxplot2=" [ {\"seriesname\": \"marks\",\"lowerboxcolor\": \"#008ee4\", \"upperboxcolor\": \"#6baa01\", \"data\": [  ";
    		for(int i=0;i<m-1;i++){
    			Coursework_cilo_boxplot2+="{ \"value\" : \" ";
    			for(int j=0;j<stu_id.length;j++)
    			{
    				Coursework_cilo_boxplot2+=d[j][i];
	    			if(j!=stu_id.length-1)
	    			{
	    				Coursework_cilo_boxplot2+=",";
	    			}
	    			else
	    			{
	    				Coursework_cilo_boxplot2+=" \" }";
	    			}
    			}
    			if(i!=m-2)
    			{
    				Coursework_cilo_boxplot2+=",";
    			}
    			else
    			{
    				Coursework_cilo_boxplot2+="] }]";
    			}
    		}
    				
		}catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
    	
    	//entire cilo boxplot
    	String Entire_cilo_boxplot2=null;//final and midterm boxplot
    	String Entire_cilo_boxplot1=null;//final and midterm boxplot
    	
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
    		}
    		int num2=mySQLAccess.getCoursework_cilo(course_id, "quiz");//quiz 的数量
    		String[] num_quiz=new String[num2];
    		for(int i=0;i<num2;i++)
    		{
    			num_quiz[i]=String.valueOf(i+1);
    		}
    		int num3=mySQLAccess.getCoursework_cilo(course_id, "test");//test 的数量
    		String[] num_test=new String[num3];
    		for(int i=0;i<num3;i++)
    		{
    			num_test[i]=String.valueOf(i+1);
    		}
    		//求assignment成绩
    		double[][] a=new double[stu_id.length*num1][m];
    		int s1=0;
    		for(int i=0;i<num1;i++){
    			for(int j=0;j<stu_id.length;j++){
    			a[s1]=mySQLAccess.cilo_score_coursework(m,stu_id[j], course_id, "assignment", num_assignment[i]);//求assignment的各个cilo的总分数，midterm只有一个，num_ass=1
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
    		
    		Entire_cilo_boxplot1=" [{ \"category\" :[";
    		for(int i=0;i<m-1;i++){
    			Entire_cilo_boxplot1+="{ \"label\" : \"cilo"+(i+1)+"\" }";
    			if(i!=m-2)
    			{
    				Entire_cilo_boxplot1+=",";
    			}
    			else
    			{
    				Entire_cilo_boxplot1+="] } ]";
    			}
    		}
    		
    	
    		Entire_cilo_boxplot2=" [ {\"seriesname\": \"marks\",\"lowerboxcolor\": \"#008ee4\", \"upperboxcolor\": \"#6baa01\", \"data\": [  ";
    		for(int i=0;i<m-1;i++){
    			Entire_cilo_boxplot2+="{ \"value\" : \" ";
    			for(int j=0;j<stu_id.length;j++)
    			{
    				Entire_cilo_boxplot2+=d[j][i];
	    			if(j!=stu_id.length-1)
	    			{
	    				Entire_cilo_boxplot2+=",";
	    			}
	    			else
	    			{
	    				Entire_cilo_boxplot2+=" \" }";
	    			}
    			}
    			if(i!=m-2)
    			{
    				Entire_cilo_boxplot2+=",";
    			}
    			else
    			{
    				Entire_cilo_boxplot2+="] }]";
    			}
    		}
    				
		}catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
    	
    	 RequestDispatcher rd = request.getRequestDispatcher("Analysis.jsp");  

         request.setAttribute("Exam_cilo_boxplot1",Exam_cilo_boxplot1);//存值 
         request.setAttribute("Exam_cilo_boxplot2",Exam_cilo_boxplot2);//存值 
         

         request.setAttribute("Exam_level_boxplot1",Exam_level_boxplot1);//存值
         

         request.setAttribute("Coursework_cilo_boxplot1",Coursework_cilo_boxplot1);//存值
         request.setAttribute("Coursework_cilo_boxplot2",Coursework_cilo_boxplot2);//存值
         

         request.setAttribute("Entire_cilo_boxplot1",Entire_cilo_boxplot1);//存值
         request.setAttribute("Entire_cilo_boxplot2",Entire_cilo_boxplot2);//存值

         rd.forward(request,response);//开始跳转 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
