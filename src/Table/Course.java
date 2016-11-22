package Table;

public class Course {
	private int course_id;
	private String course_name;
	private int course_period;
	private int teacher_id;
	private int ta_id;
	
	public Course(int course_id,String course_name,int course_period,int teacher_id,int ta_id){
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_period = course_period;
		this.teacher_id = teacher_id;
		this.ta_id = ta_id;
	}
	
	public void setCourse_id(int course_id){
		this.course_id = course_id;	
	}
	public void setName(String course_name){
		this.course_name = course_name;
	}
	public void setCourse_period(int course_period){
		this.course_period = course_period;

	}
	public void setTeacher_id(int teacher_id){
		this.teacher_id = teacher_id;
	}
	public void setTa_id(int ta_id){
		this.ta_id = ta_id;
	}
	
	public int getCourse_id(){
		return course_id;	
	}
	public String getName(){
		return course_name;
	}
	public int getCourse_period(){
		return course_period;

	}
	public int getTeacher_id(){
		return teacher_id;
	}
	public int getTa_id(){
		return ta_id;
	}
}
