package JDBC;

public class Operation {
	public static String transDate(int date){
		if(date%10 == 1){
			return "[Semester 2 of "+Integer.toString(date/10-1)+"-"+ Integer.toString(date/10)+"]";
		}else{
			return "[Semester 2  of "+Integer.toString(date/10)+"-"+ Integer.toString(date/10+1)+"]";
		}
	}
}
