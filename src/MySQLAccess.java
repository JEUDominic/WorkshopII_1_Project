
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;



public class MySQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	 //驱动程序就是之前在classpath中配置的JDBC的驱动程序的JAR 包中  
	   public static final String DBDRIVER = "com.mysql.jdbc.Driver";  
	   //连接地址是由各个数据库生产商单独提供的，所以需要单独记住  
	   public static final String DBURL = "jdbc:mysql://localhost/information";  
	   //连接数据库的用户名  
	   public static final String DBUSER = "root";  
	   //连接数据库的密码  
	   public static final String DBPASS = "123456"; 

	   Connection conn = null; //表示数据库的连接对象 
	//生成图标的demo
	public Connection getConnection() {  
		
	       try {
	           Class.forName(DBDRIVER);
	       } catch (ClassNotFoundException e1) {
	           // TODO Auto-generated catch block
	           e1.printStackTrace();
	       } //1、使用CLASS 类加载驱动程序  
	       try {  
	           // 获取连接  
	           conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);  
	       } catch (SQLException e) {  
	           System.out.println(e.getMessage());  
	       }  
	       return conn;  
	   } 
	
	
	//create paper
	
	//create paper
 	public int getNum(String course_id, String type, String num_ass) {
    	ResultSet rs=null;
    	int i=0;
     	// method: insert data
     		try{
     			// 1. load database driver program
     			Class.forName(DBDRIVER);
     			
     			// 2. obtain database connection
     			Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
     			// 3. create SQL sentences
     			//String sql = "INSERT INTO `user`(`id`, `name`, `password`, `email`, `state`) VALUES (1430003003 , 'Vision', 'fengjiayi', '530300865@qq.com', 1)";
     			String sql="SELECT count(*) FROM `question` WHERE  course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"'";
     			// 4. construct a statement instance(used to send sql sentenses)
     			Statement state = connection.createStatement();
     			rs = state.executeQuery(sql);
     			
     			 if(rs.next()){
     				i = rs.getInt(1);
     			 }
     			// 5. execute sql sentences
     			//state.executeUpdate(sql);
     			
     			// 6. close connection(release resources)
     			state.close();
     			connection.close();
     			
     		} catch(ClassNotFoundException e) {
     			e.printStackTrace();
  
     		} catch(SQLException e) {
     			e.printStackTrace();
     		}
     		return i;
     	}
 	//create paper
    public  String[] getquestion_que(String course_id, String type, String num_ass) {
    	ResultSet rs=null;
    	String result="";
    	int len=getNum(course_id,type, num_ass);
    	String[] str=new String[len];
     	// method: insert data
     		try{
     			// 1. load database driver program
     			Class.forName(DBDRIVER);
     			
     			// 2. obtain database connection
     			Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
     			// 3. create SQL sentences
     			//String sql = "INSERT INTO `user`(`id`, `name`, `password`, `email`, `state`) VALUES (1430003003 , 'Vision', 'fengjiayi', '530300865@qq.com', 1)";
     			String sql="SELECT `type`, `num_ass`, `num_que`, `content` FROM `question` WHERE  course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"'";
     		
     			// 4. construct a statement instance(used to send sql sentenses)
     			Statement state = connection.createStatement();
     			rs = state.executeQuery(sql);
     			
     			int i=0;
     			 while(rs.next()){
     				 result=rs.getString("num_que")+".";
     				 str[i]=result;
     				 i++;
     			 }
     			// 5. execute sql sentences
     			//state.executeUpdate(sql);
     			
     			// 6. close connection(release resources)
     			state.close();
     			connection.close();
     			
     		} catch(ClassNotFoundException e) {
     			e.printStackTrace();
  
     		} catch(SQLException e) {
     			e.printStackTrace();
     		}
     		return str;
     	}
  //create paper
    public  String[] getcontent(String course_id, String type, String num_ass) {
    	ResultSet rs=null;
    	String result="";
    	int len=getNum(course_id,type, num_ass);
    	String[] str=new String[len];
     	// method: insert data
     		try{
     			// 1. load database driver program
     			Class.forName(DBDRIVER);
     			
     			// 2. obtain database connection
     			Connection connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
     			// 3. create SQL sentences
     			//String sql = "INSERT INTO `user`(`id`, `name`, `password`, `email`, `state`) VALUES (1430003003 , 'Vision', 'fengjiayi', '530300865@qq.com', 1)";
     			String sql="SELECT `type`, `num_ass`, `num_que`, `content` FROM `question` WHERE course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"'";
     			// 4. construct a statement instance(used to send sql sentenses)
     			Statement state = connection.createStatement();
     			rs = state.executeQuery(sql);
     			
     			int i=0;
     			 while(rs.next()){
     				 result=rs.getString("content");
     				 str[i]=result;
     				 i++;
     			 }
     			// 5. execute sql sentences
     			//state.executeUpdate(sql);
     			
     			// 6. close connection(release resources)
     			state.close();
     			connection.close();
     			
     		} catch(ClassNotFoundException e) {
     			e.printStackTrace();
  
     		} catch(SQLException e) {
     			e.printStackTrace();
     		}
     		return str;
     	}
    
	
	
	public String readComment() throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/feedback?"
							+ "user=root&password=123456");
			
			preparedStatement = connect
					.prepareStatement("SELECT COMMENTS from FEEDBACK.COMMENTS");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				String comment = resultSet.getString("comments");
			
			return comment;
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
		return "";

	}
	
	public void InsertNewQuestion(String course_id, String type, String num_ass, String num_que, String content, String score) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			
			preparedStatement = connect.prepareStatement("INSERT INTO `question`(`course_id`, `type`, `num_ass`, `num_que`, `content`, `score`) VALUES ('"+course_id+"','"+type+"','"+num_ass+"','"+num_que+"','"+content+"','"+score+"')");
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}

	public void InsertNewQuestion_cilo(String course_id, String type, String num_ass, String num_que, String cilo) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			
			preparedStatement = connect.prepareStatement("INSERT INTO `question_cilo`(`course_id`, `type`, `num_ass`, `num_que`, `cilo`) VALUES ('"+course_id+"','"+type+"','"+num_ass+"','"+num_que+"','"+cilo+"')");
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}

	public void InsertNewQuestion_level(String course_id, String type, String num_ass, String num_que, String level) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			
			preparedStatement = connect.prepareStatement("INSERT INTO `question_level`(`course_id`, `type`, `num_ass`, `num_que`, `level`) VALUES ('"+course_id+"','"+type+"','"+num_ass+"','"+num_que+"','"+level+"')");
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	public void InsertNewCourse(String course_name, String course_period, String teacher_id, String ta_id) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			
			preparedStatement = connect.prepareStatement("INSERT INTO `course`(`course_name`, `course_period`, `teacher_id`, `ta_id`) VALUES ('"+course_name+"','"+course_period+"','"+teacher_id+"','"+ta_id+"')");
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	public void InsertNewCourse_info(String course_id, String assignment, String quiz, String test, String midterm, String final_, int num_cilo) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			
			preparedStatement = connect.prepareStatement("INSERT INTO `course_info`(`course_id`, `assignment`, `quiz`, `test`, `midterm`, `final`, `cilo_num`) VALUES ('"+course_id+"','"+assignment+"','"+quiz+"','"+test+"','"+midterm+"','"+final_+"','"+num_cilo+"')");
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	public void InsertNewCourse_cilo(String course_id, String cilo_num, String content, String pilo_num) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			
			preparedStatement = connect.prepareStatement("INSERT INTO `course_cilo`(`course_id`, `cilo_num`, `content`, `pilo_num`) VALUES ('"+course_id+"','"+cilo_num+"','"+content+"','"+pilo_num+"')");
			
			preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	
	public String getCourse_id(String course_name, String course_period) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			
			preparedStatement = connect
					.prepareStatement("SELECT `course_id`FROM `course` WHERE course_name='"+course_name+"' and course_period='"+course_period+"'");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				String id = resultSet.getString("course_id");
			
			return id;
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
		return "";

	}
	
//生成midterm还有final的piechart用到的函数
	
	//get cilo_num from question_cilo
	public String[] getCilo_num( String course_id,String type, String num_ass, int cilo_num) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			
			//计算题目的数量
			preparedStatement = connect
					.prepareStatement("select count(num_que) from question_cilo where course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"' and cilo='"+cilo_num+"' ");
			resultSet = preparedStatement.executeQuery();
			int num=0;
			if (resultSet.next()){
					num = resultSet.getInt(1);
			}
			
			String sql="select num_que from question_cilo where course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"' and cilo='"+cilo_num+"' ";

			preparedStatement = connect
					.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			String[] comment=new String[num];
			int i=0;
			while (resultSet.next()){
				 comment[i] = resultSet.getString("num_que");
				 i++; 
			}
			return comment;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	//get the score of each cilo() in question, 取到的只是每道题的分数，在后台求和，num_que="comment[i]"(上个函数)
	public int getCilo_score( String course_id,String type, String num_ass, int cilo_num, String num_que) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			String sql="select score from question where course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"' and num_que='"+num_que+"' ";
			
			preparedStatement = connect
					.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int comment=0;
			if (resultSet.next()){
				 comment = resultSet.getInt("score");
			}
			return comment;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	
//取到这个type的asscessment占这门课总成绩的百分比
	public int getPercentage( String course_id,String type) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			String sql="select "+type+" from course_info where course_id='"+course_id+"'";
			preparedStatement = connect
					.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			int comment=0;
			if (resultSet.next()){
				 comment = resultSet.getInt(type);
			}
			return comment;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	//获取这门课的学生学号，用来计算每个学生每道题的得分
	public String[] getStudentid(String course_id) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			//计算学生数量
			preparedStatement = connect
					.prepareStatement("select count(DISTINCT student_id) from grade where course_id='"+course_id+"' ");
			resultSet = preparedStatement.executeQuery();
			int num=0;
			if(resultSet.next()){
				num=resultSet.getInt(1);
			}

			preparedStatement = connect
					.prepareStatement("select DISTINCT student_id from grade where course_id='"+course_id+"' ");
			resultSet = preparedStatement.executeQuery();
			String[] comment=new String[num];
			int i=0;
			while (resultSet.next()){
				 comment[i] = resultSet.getString("student_id");
				 i++;
			}
			return comment;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	//获取每个学生的每道题的得分student_id=comment[i](getStudentid()), num_que=comment[j](int student_id,)
	public int getStudent_score( String course_id,String student_id, String type, String num_ass,  String num_que) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			String sql="select grade_per from grade where course_id='"+course_id+"' and student_id='"+student_id+"' and type='"+type+"' and num_ass='"+num_ass+"' and num_que='"+num_que+"' ";

			preparedStatement = connect
					.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int comment=0;
			if (resultSet.next()){
				 comment = resultSet.getInt("grade_per");
			}
			return comment;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	//计算cilo的数量对于一门课
	public int getCourse_cilo( String course_id) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			
			preparedStatement = connect
					.prepareStatement("select count(*) from course_cilo where course_id='"+course_id+"' ");
			resultSet = preparedStatement.executeQuery();
			int comment=0;
			if (resultSet.next()){
				 comment = resultSet.getInt(1);
			}
			return comment;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	//计算每个assessment的各个cilo1，2，3，4...的总分数
	public double[] cilo_total(int m,String course_id, String type, String num_ass){
		double total=0;
		int len=0;
		double[] b=new double[m];//存每个cilo的从成绩
		
		try{
		while(len<m){
		String[] a=getCilo_num(course_id, type, num_ass, (len+1));//cilo(len+1) 对应的题号        
		
		
        	int i=0;
        	double[] c=new double[a.length];
        	
        	while(i<a.length){
        	int question_num=getquestion_cilo_num(course_id,type,num_ass,a[i]);//一道题对应的cilo数量
        	c[i]=getCilo_score(course_id, type, num_ass, (len+1), a[i]);//计算cilo1对应的总分, b[1]-cilo1; b[2]-cilo2...
        	total+=c[i]/question_num;
        	i++;	
        	}
        	b[len]=total;//计算cilo1,2,3,...对应的总分, b[1]-cilo1; b[2]-cilo2...
        	total=0;
        	i=0;
 
        	len++;	
		}
		}
		catch (Exception e) {
		}
		return b;
	}
	//计算每个course_work assessment的各个cilo1，2，3，4...的实际得分
	public double[] cilo_score_coursework(int m,String stu_id,String course_id, String type, String num_ass){
		double total=0;
		int len=1;
		double[] b=new double[m];//存每个cilo的从成绩
		b[0]=Integer.valueOf(stu_id);
		try{	
			int i=0;
		while(len<m){
			String[] a=getCilo_num(course_id, type, num_ass, len);//cilo(len+1) 对应的题号    a[]存题号 
			
			while(i<a.length){
	        	double[] c=new double[a.length];
	        	int question_num=getquestion_cilo_num(course_id,type,num_ass,a[i]);//一道题对应的cilo数量	
	        	c[i]=getStudent_score(course_id,stu_id,type, num_ass, a[i]);//计算cilo1对应的得分, b[1]-cilo1; b[2]-cilo2...
	        	total+=c[i]/question_num;//一个学生cilo（i）的得分

	        	i++;	
	        	}
	        	b[len]=total;//计算cilo1,2,3,...对应的总分, b[1]-cilo1; b[2]-cilo2...
	        	i=0;
        	total=0;
        	len++;	
		}
			for(int a=0;a<m;a++){

			}
		}
		catch (Exception e) {
		}
		return b;
	}
	
	//计算每个level, final midterm assessment的各个cilo1，2，3，4...的实际得分
		public double[] cilo_score(int m,String stu_id,String course_id, String type, String num_ass){
			double total=0;
			int len=0;
			double[] b=new double[m];//存每个cilo的从成绩
			
			try{	
				int i=0;
			while(len<m){
				String[] a=getCilo_num(course_id, type, num_ass, (len+1));//cilo(len+1) 对应的题号    a[]存题号 
				
				
		        	while(i<a.length){
		        	int[] c=new int[a.length];
		        	int question_num=getquestion_cilo_num(course_id,type,num_ass,a[i]);//一道题对应的cilo数量
		        	c[i]=getStudent_score(course_id,stu_id,type, num_ass, a[i]);//计算cilo1对应的得分, b[1]-cilo1; b[2]-cilo2...
		        	total+=c[i]/question_num;//一个学生cilo（i）的得分

		        	i++;	
		        	}
		        	b[len]=total;//计算cilo1,2,3,...对应的总分, b[1]-cilo1; b[2]-cilo2...
		        	i=0;
	        	total=0;
	        	len++;	
			}
			}
			catch (Exception e) {
			}
			return b;
		}

	
	//获取level的题号
	public String[] getlevel_num( String course_id,String type, String num_ass, String level) throws Exception{
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/information?"
							+ "user=root&password=123456");
			
			//计算题目的数量
			preparedStatement = connect
					.prepareStatement("select count(num_que) from question_level where course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"' and level='"+level+"' ");
			resultSet = preparedStatement.executeQuery();
			int num=0;
			if (resultSet.next()){
					num = resultSet.getInt(1);
			}
			
			String sql="select num_que from question_level where course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"' and level='"+level+"' ";

			preparedStatement = connect
					.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			String[] comment=new String[num];
			int i=0;
			while (resultSet.next()){
				 comment[i] = resultSet.getString("num_que");
				 i++; 
			}
			return comment;
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}	
	}
	
	//计算每个level的总分数
		public double[] level_total(String course_id, String type, String num_ass){
			double total=0;
			int len=0;
			double[] b=new double[6];//存每个level的从成绩
			String[] level={"Knowledge","Comprehension","Application","Analysis","Synthesis","Evaluation"};
			try{
			while(len<6){
			String[] a=getlevel_num(course_id, type, num_ass, level[len]);//level(len+1) 对应的题号 
	        	int i=0;
	        	double[] c=new double[a.length];
	        	while(i<a.length){
	        		int level_num=getquestion_level_num( course_id, type,num_ass,a[i]);
	        	c[i]=getCilo_score(course_id, type, num_ass, (len+1), a[i]);//计算cilo1对应的总分, b[1]-cilo1; b[2]-cilo2...
	        	total+=c[i]/level_num;
	        	i++;	
	        	}
	        	b[len]=total;//计算cilo1,2,3,...对应的总分, b[1]-cilo1; b[2]-cilo2...
	        	total=0;
	        	i=0;
	        	len++;	
			}
			
			}
			catch (Exception e) {
			}
			return b;
		}
		
		//计算每个assessment的各levelcilo1，2，3，4...的实际得分
		public double[] level_score(int m,String stu_id,String course_id, String type, String num_ass){
			double total=0;
			int len=0;
			double[] b=new double[m];//存每个cilo的从成绩
			String[] level={"Knowledge","Comprehension","Application","Analysis","Synthesis","Evaluation"};
			try{	
				int i=0;
			while(len<m){
				String[] a=getlevel_num(course_id, type, num_ass, level[len]);//level(len+1) 对应的题号 

		        	while(i<a.length){
		        	double[] c=new double[a.length];
		        	int level_num=getquestion_level_num( course_id, type,num_ass,a[i]);
		        	
		        	c[i]=getStudent_score(course_id,stu_id,type, num_ass, a[i]);//计算cilo1对应的得分, b[1]-cilo1; b[2]-cilo2...
		        	total+=c[i]/level_num;//一个学生cilo（i）的得分
		        	i++;	
		        	}
		        	b[len]=total;//计算cilo1,2,3,...对应的总分, b[1]-cilo1; b[2]-cilo2...
		        	i=0;
	        	total=0;
	        	len++;	
			}
			}
			catch (Exception e) {
			}
			return b;
		}
		
		//计算各个course_work的数量，assignment，quiz，test的数量
		public int getCoursework_cilo( String course_id,String type) throws Exception{
			try {
				// This will load the MySQL driver, each DB has its own driver
				Class.forName("com.mysql.jdbc.Driver");
				// Setup the connection with the DB
				connect = DriverManager
						.getConnection("jdbc:mysql://localhost/information?"
								+ "user=root&password=123456");
				String sql="select count(distinct num_ass) from question where course_id='"+course_id+"' and type='"+type+"'";

				preparedStatement = connect
						.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				int comment=0;
				if (resultSet.next()){
					 comment = resultSet.getInt(1);
				}
				return comment;
			} catch (Exception e) {
				throw e;
			} finally {
				close();
			}	
		}
		//计算一道题对应的cilo数量
		public int getquestion_cilo_num( String course_id,String type,String num_ass, String num_que) throws Exception{
			try {
				// This will load the MySQL driver, each DB has its own driver
				Class.forName("com.mysql.jdbc.Driver");
				// Setup the connection with the DB
				connect = DriverManager
						.getConnection("jdbc:mysql://localhost/information?"
								+ "user=root&password=123456");
				String sql="select count(distinct num_que) from question_cilo where course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"' and num_que='"+num_que+"' ";
				preparedStatement = connect
						.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				int comment=0;
				if (resultSet.next()){
					 comment = resultSet.getInt(1);
				}
				return comment;
			} catch (Exception e) {
				throw e;
			} finally {
				close();
			}	
		}
		
		
		//计算一道题对应的level数量
				public int getquestion_level_num( String course_id,String type,String num_ass, String num_que) throws Exception{
					try {
						// This will load the MySQL driver, each DB has its own driver
						Class.forName("com.mysql.jdbc.Driver");
						// Setup the connection with the DB
						connect = DriverManager
								.getConnection("jdbc:mysql://localhost/information?"
										+ "user=root&password=123456");
						String sql="select count(distinct level) from question_level where course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"' and num_que='"+num_que+"' ";
						preparedStatement = connect
								.prepareStatement(sql);
						resultSet = preparedStatement.executeQuery();
						int comment=0;
						if (resultSet.next()){
							 comment = resultSet.getInt(1);
						}
						return comment;
					} catch (Exception e) {
						throw e;
					} finally {
						close();
					}	
				}
		
	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}


}
