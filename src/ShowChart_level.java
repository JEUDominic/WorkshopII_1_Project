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
@WebServlet("/ShowChart_level")
public class ShowChart_level extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MySQLAccess mySQLAccess = new MySQLAccess();
    public void init() throws ServletException {
		mySQLAccess = new MySQLAccess();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowChart_level() {
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
    	String num_ass="1";
    	String cilo_num="1";
    	String data1=null;//final and midterm boxplot
 
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
    		
    		data1=" [ {\"seriesname\": \"marks\",\"lowerboxcolor\": \"#008ee4\", \"upperboxcolor\": \"#6baa01\", \"data\": [  ";
    		for(int i=0;i<stu_id.length;i++){
    			data1+="{ \"value\" : \" ";
    			for(int j=0;j<m;j++)
    			{
	    			data1+=c[i][j];
	    			if(j!=m-1)
	    			{
	    				data1+=",";
	    			}
	    			else
	    			{
	    				data1+=" \" }";
	    			}
    			}
    			if(i!=stu_id.length-1)
    			{
    				data1+=",";
    			}
    			else
    			{
    				data1+="] }]";
    			}
    		}
    				
		}catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
    
    	/*
    	String course_id="1";
    	String num_ass="1";
    	String cilo_num="1";
    	String data=null;
    	*/
    	//final and midterm pie chart
    
    	String data2=null; //final and midterm pie chart
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
    		data2="[";
        	for(int i=0; i<6;i++){
        		data2+="{ y:"+c[i]+", indexLabel: \""+level[i]+"\" }";
        		if(i!=5)
        		{
        			data2+=", ";
        		}
        		else{
        			data2+=" ]";
        		}
        	}
		}catch (Exception e) {
			getServletContext().log("An exception occurred in writeComment ", e);
			throw new ServletException("An exception occurred in writeComment "
				+ e.getMessage());
		}
 
   
    
       System.out.println(data1);
       RequestDispatcher rd = request.getRequestDispatcher("Exam_Level_PieChart.jsp"); 
       //RequestDispatcher rd = request.getRequestDispatcher("Exam_Level_Boxplot.jsp");
        request.setAttribute("chartData2",data2);//存值  
        request.setAttribute("chartData1",data1);//存值 
        rd.forward(request,response);//开始跳转 

       // request.getRequestDispatcher("Exam_Level_PieChart.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
    

}