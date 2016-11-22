

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
 * Servlet implementation class Analysis2
 */
@WebServlet("/Analysis2")
public class Analysis2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private MySQLAccess mySQLAccess = new MySQLAccess();
	    public void init() throws ServletException {
			mySQLAccess = new MySQLAccess();
		}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analysis2() {
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
     	
    	//final and midterm pie chart
    	String Exam_cilo_piechart=null; //final and midterm pie chart
     	try{
    		int m=0;
    		m=mySQLAccess.getCourse_cilo(course_id);//计算这门课cilo的数量   111
    		
    		double[] a=mySQLAccess.cilo_total(m, course_id, "midterm", "1");//求midterm的各个cilo的总分数，midterm只有一个，num_ass=1
    		double[] b=mySQLAccess.cilo_total(m, course_id, "final", "1");//求final的各个cilo的总分数，final只有一个，num_ass=1
    		int percentage1=mySQLAccess.getPercentage(course_id,"midterm");
    		int percentage2=mySQLAccess.getPercentage(course_id,"final");
    		double[] c=new double[m];
    		for(int i=0;i<m;i++){
    			c[i]=(a[i]*percentage1/100)+(b[i]*percentage2/100);
    		}		
    		Exam_cilo_piechart="[";
        	for(int i=0; i<m;i++){
        		Exam_cilo_piechart+="{ y:"+c[i]+", indexLabel: \"cilo"+(i+1)+"\" }";
        		if(i!=m-1)
        		{
        			Exam_cilo_piechart+=", ";
        		}
        		else{
        			Exam_cilo_piechart+=" ]";
        		}
        	}
		}catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
     	
     	//level
     	
     	String Exam_level_piechart=null; //final and midterm pie chart
     	try{
    		double[] a=mySQLAccess.level_total(course_id, "midterm", "1");//求midterm的各个cilo的总分数，midterm只有一个，num_ass=1
    		double[] b=mySQLAccess.level_total(course_id, "final", "1");//求final的各个cilo的总分数，final只有一个，num_ass=1
    		int percentage1=mySQLAccess.getPercentage(course_id,"midterm");
    		int percentage2=mySQLAccess.getPercentage(course_id,"final");
    		String[] level={"Knowledge","Comprehension","Application","Analysis","Synthesis","Evaluation"};
    		double[] c=new double[6];
    		for(int i=0;i<6;i++){
    			c[i]=(a[i]*percentage1/100)+(b[i]*percentage2/100);
    		}		
    		Exam_level_piechart="[";
        	for(int i=0; i<6;i++){
        		Exam_level_piechart+="{ y:"+c[i]+", indexLabel: \""+level[i]+"\" }";
        		if(i!=5)
        		{
        			Exam_level_piechart+=", ";
        		}
        		else{
        			Exam_level_piechart+=" ]";
        		}
        	}
		}catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
     	
    	//Coursework cilo piechart
    	String Coursework_cilo_piechart=null; //final and midterm pie chart
    	try{
    		int m=0;
    		m=mySQLAccess.getCourse_cilo(course_id);//计算这门课cilo的数量   111
    		
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
    		
    		double[][] a=new double[num1][m];
    		for(int i=0;i<num1;i++){
    			a[i]=mySQLAccess.cilo_total(m, course_id, "assignment", num_assignment[i]);//求assignment的各个cilo的总分数，midterm只有一个，num_ass=1
    		}
    		double[][] a1=new double[1][m];
    		for(int i=0;i<m;i++)
    		{	
    			
    			for(int j=0;j<num1;j++)
    			{
    			a1[0][i]+=a[j][i];
    			}
    		}
    			
    		int percentage1=mySQLAccess.getPercentage(course_id,"assignment");
    		
    		double[][] b=new double[num2][m];
    		for(int i=0;i<num2;i++){
        			b[i]=mySQLAccess.cilo_total(m, course_id, "quiz", num_quiz[i]);//求quiz的各个cilo的总分数，midterm只有一个，num_ass=1
    		}
    		int percentage2=mySQLAccess.getPercentage(course_id,"quiz");
    		
    		double[][] b1=new double[1][m];
    		for(int i=0;i<m;i++)
    		{	
    			for(int j=0;j<num2;j++)
    			{
    			b1[0][i]+=b[j][i];

    			}
    		}
    		
    		double[][] c=new double[num3][m];
    		for(int i=0;i<num3;i++){
        			c[i]=mySQLAccess.cilo_total(m, course_id, "test", num_test[i]);//求test的各个cilo的总分数，midterm只有一个，num_ass=1
    		}
    		int percentage3=mySQLAccess.getPercentage(course_id,"test");
    		
    		double[][] c1=new double[1][m];
    		for(int i=0;i<m;i++)
    		{	
    			for(int j=0;j<num3;j++)
    			{
    			c1[0][i]+=c[j][i];

    			}
    		}
    		
    		double[][] d=new double[1][m];
   
	    		for(int j=0;j<m;j++){

	    			d[0][j]+=(a1[0][j]*percentage1/100)+(b1[0][j]*percentage2/100)+(c1[0][j]*percentage3/100);
	    		}	
    		
	    		Coursework_cilo_piechart="[";
        	for(int i=0; i<m;i++){
        		Coursework_cilo_piechart+="{ y:"+d[0][i]+", indexLabel: \"cilo"+(i+1)+"\" }";
        		if(i!=m-1)
        		{
        			Coursework_cilo_piechart+=", ";
        		}
        		else{
        			Coursework_cilo_piechart+=" ]";
        		}
        	}
		}
    	
    	catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
    	
    	//entire cilo pirchart
    	String Entire_cilo_piechart=null; //final and midterm pie chart
     	
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

    		
    		double[][] a=new double[num1][m];
    		for(int i=0;i<num1;i++){
    			a[i]=mySQLAccess.cilo_total(m, course_id, "assignment", num_assignment[i]);//求assignment的各个cilo的总分数，midterm只有一个，num_ass=1

    		}
    		double[][] a1=new double[1][m];
    		for(int i=0;i<m;i++)
    		{	
    			
    			for(int j=0;j<num1;j++)
    			{
    			a1[0][i]+=a[j][i];

    			}
    		}
    			
    		int percentage1=mySQLAccess.getPercentage(course_id,"assignment");
    		
    		double[][] b=new double[num2][m];
    		for(int i=0;i<num2;i++){
        			b[i]=mySQLAccess.cilo_total(m, course_id, "quiz", num_quiz[i]);//求quiz的各个cilo的总分数，midterm只有一个，num_ass=1
    		}
    		int percentage2=mySQLAccess.getPercentage(course_id,"quiz");
    		
    		double[][] b1=new double[1][m];
    		for(int i=0;i<m;i++)
    		{	
    			for(int j=0;j<num2;j++)
    			{
    			b1[0][i]+=b[j][i];
    			}
    		}
    		
    		double[][] c=new double[num3][m];
    		for(int i=0;i<num3;i++){
        			c[i]=mySQLAccess.cilo_total(m, course_id, "test", num_test[i]);//求test的各个cilo的总分数，midterm只有一个，num_ass=1
    		}
    		int percentage3=mySQLAccess.getPercentage(course_id,"test");
    		
    		double[][] c1=new double[1][m];
    		for(int i=0;i<m;i++)
    		{	
    			for(int j=0;j<num3;j++)
    			{
    			c1[0][i]+=c[j][i];

    			}
    		}
    		
    		double[][] d=new double[1][m];
   
	    		for(int j=0;j<m;j++){
	    			d[0][j]+=c_exam[j]+(a1[0][j]*percentage1/100)+(b1[0][j]*percentage2/100)+(c1[0][j]*percentage3/100);
	    		}	
    		
	    		Entire_cilo_piechart="[";
        	for(int i=0; i<m;i++){
        		Entire_cilo_piechart+="{ y:"+d[0][i]+", indexLabel: \"cilo"+(i+1)+"\" }";
        		if(i!=m-1)
        		{
        			Entire_cilo_piechart+=", ";
        		}
        		else{
        			Entire_cilo_piechart+=" ]";
        		}
        	}
		}
    	
    	catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
    	
    	 RequestDispatcher rd = request.getRequestDispatcher("Analysis.jsp");  
         
    	 request.setAttribute("Exam_cilo_piechart",Exam_cilo_piechart);//存值  
    
  
         request.setAttribute("Exam_level_piechart",Exam_level_piechart);//存值
         
         request.setAttribute("Coursework_cilo_piechart",Coursework_cilo_piechart);//存值

         
         request.setAttribute("Entire_cilo_piechart",Entire_cilo_piechart);//存值
         
         
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
