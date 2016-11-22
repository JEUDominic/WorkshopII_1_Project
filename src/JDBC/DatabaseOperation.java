package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import Table.*;

public class DatabaseOperation {
	private static String url="jdbc:mysql://localhost/information";
	private static String username="root";
	private static String password="123456";
	private static String Driver="com.mysql.jdbc.Driver";
	private static ResultSet result;
	private static Statement statement;
	private static Connection connection;
	
	// Pass in user email and userpassword.
	// This function do the comparation,
	// If correct, reeturn 1
	// If incorrect, return -1
	// If no such user, return 0
	// If SQL problem, return -2 or -3
	public static int Login(String email,String userpassword){
		try{
			Class.forName(Driver);
			connection = DriverManager.getConnection(url, username, password);
			String sql="SELECT * FROM `user` WHERE `email` = '"+email+"'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeQuery();
			if( !result.next() ) {
				preparedStatement.close();
				connection.close();
				return 0;
			}
			if(result.getString("password").equals(userpassword)) return 1;
			else return -1;
		
		}catch(ClassNotFoundException e) {
		
		e.printStackTrace();
	
		return -2;
	
		} catch(SQLException e) {
	
		e.printStackTrace();
		e.getMessage();
		return -3;
	
		}finally{
			close();
		}
		
	}
	
	// Pass in user email,
	// return the instance of User,
	// if failed, return null.
	public static User getUser(String email){
		try{
			Class.forName(Driver);
			connection = DriverManager.getConnection(url, username, password);
			
			String sql="SELECT * FROM `user` WHERE `email` = '"+email+"'";
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			if( result.next() ) {
				User loginUser = new User(result.getInt("id"),result.getString("name"),result.getString("email"),result.getInt("state"));
				return loginUser;
			}
			statement.close();
			connection.close();
			return null;
		}catch(ClassNotFoundException e) {
		
		e.printStackTrace();
	
		return null;
	
		} catch(SQLException e) {
	
		e.printStackTrace();
		e.getMessage();
		return null;
	
		}finally{
			close();
		}
		
	}
	
	// Pass in user id,
	// return the name of User,
	// if failed, return null.
	public static String getUser(int id){
		try{
			System.out.println(id);
			Class.forName(Driver);
			
			connection = DriverManager.getConnection(url, username, password);
			String sql="SELECT * FROM `user` WHERE `id` = '"+id+"'";
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			if( result.next() ) {
				String name =  result.getString("name");
				return name;
			}
			
			statement.close();
			connection.close();
			return null;
		}catch(ClassNotFoundException e) {
		
			e.printStackTrace();
	
			return null;
	
		} catch(SQLException e) {
	
			e.printStackTrace();
			e.getMessage();
			return null;
		
		}finally{
			close();
		}
			
	}
	
	// Pass in a User
	// Return the course number he/she taught
	// If there is no such course, return 0
	// If SQL problem, return -2 or -3
	public static int getCourseNum(User loginUser){
		try{
			Class.forName(Driver);
			connection = DriverManager.getConnection(url, username, password);
			String sql="SELECT * FROM `course` WHERE ";
			if(loginUser.getState() == 1){
				sql = sql + "`teacher_id` = '" + loginUser.getID() +"'";
			}else{
				sql = sql + "`ta_id` = '" + loginUser.getID() +"'";
			}
			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery(sql);
			if( !resultset.next() ) {
				statement.close();
				connection.close();
				return 0;
			}
			ResultSet r = statement.executeQuery(sql);
			r.last();
			int row = r.getRow();
			
			return row;
		}catch(ClassNotFoundException e) {
		
		e.printStackTrace();
	
		return -2;
	
		} catch(SQLException e) {
	
		e.printStackTrace();
		e.getMessage();
		return -3;
	
		}finally{
			close();	
		}
	}
	
	// Pass in course_id and assessment type,
	// return the number of this assessment
	// if SQL problem return -1 or -2
	public static int getAssNum(String type,int course_id){
		try{
			Class.forName(Driver);
			connection = DriverManager.getConnection(url, username, password);
			
			String sql= "SELECT count(distinct `num_ass`) as num FROM (SELECT * FROM `question` WHERE `course_id` ="+course_id+" AND `type` LIKE '"+type+"') as A";
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			result.next();
			int num;
			num = result.getInt("num");
			return num;
		
			
		}catch(ClassNotFoundException e) {
		
		e.printStackTrace();
	
		return -1;
	
		} catch(SQLException e) {

		e.printStackTrace();
		e.getMessage();
		return -2;
		
		}finally{
			close();	
		}
			
	}
	
	// Pass in User
	// Return an array of course he/she taught
	// If there is no such course, return 0
	// If SQL problem, return null..
	public static Course[] getCourse(User loginUser){
		try{
			Class.forName(Driver);
			connection = DriverManager.getConnection(url, username, password);
			String sql="SELECT * FROM `course` WHERE ";
			if(loginUser.getState() == 1){
				sql = sql + "`teacher_id` = '" + loginUser.getID() +"'";
			}else{
				sql = sql + "`ta_id` = '" + loginUser.getID() +"'";
			}
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			if( !result.next() ) {
				statement.close();
				connection.close();
				System.out.println("Closed");
				return null;
			}
			int row = getCourseNum(loginUser);
			Course course[] = new Course[row];
			int i = 0;
			do{
				course[i] = new Course(result.getInt("course_id"),result.getString("course_name"),result.getInt("course_period"),result.getInt("teacher_id"),result.getInt("ta_id"));
				i++;
			}while(result.next());
			
			statement.close();
			connection.close();
			return course;
		}catch(ClassNotFoundException e) {
		
		e.printStackTrace();
	
		return null;
	
		} catch(SQLException e) {
	
		e.printStackTrace();
		e.getMessage();
		return null;
	
		}finally{
			close();
		}
		
	}
	
	// Pass in a course_id
	// Return an array of percentage of assignment(0) quiz(1) test(2) midterm(3) final(4)
	// If failed, return null
	public static int[] getAssPer(int course_id){
		try{
			Class.forName(Driver);
			connection = DriverManager.getConnection(url, username, password);
			String sql="SELECT * FROM `course_info` WHERE `course_id` =" +course_id;
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			int[] percentage = new int[5];
			if( result.next() ) {
				percentage[0] = result.getInt("assignment");
				percentage[1] = result.getInt("quiz");				
				percentage[2] = result.getInt("test");
				percentage[3] = result.getInt("midterm");
				percentage[4] = result.getInt("final");
				
				return percentage;
			}
			
			
			return null;
		}catch(ClassNotFoundException e) {
		
		e.printStackTrace();
	
		return null;
	
		} catch(SQLException e) {
	
		e.printStackTrace();
		e.getMessage();
		return null;
		
		}finally{
			close();	
		}
	}
	
	
	private static void close() {
		try {
			if (result != null) {
				result.close();
			}
			if(statement != null){
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {

		}
	}
}
