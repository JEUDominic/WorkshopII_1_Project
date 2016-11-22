package Table;

public class User {
	private int id;
	private String name;
	private String email;
	private int state;
	
	public User(int id,String name,String email,int state){
		this.id = id;
		this.name = name;
		this.email = email;
		this.state = state;
	}
	
	public void setID(int id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setState(int state){
		this.state = state;
	}
	
	public int getID(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getEmail(){
		return email;
	}
	public int getState(){
		return state;
	}
}
