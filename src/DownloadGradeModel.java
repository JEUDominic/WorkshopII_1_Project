
import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;



public class DownloadGradeModel {

    public final static String outputFile="/Users/apple/Desktop/GradeModel.xlsx";
    
    public final static String url="jdbc:mysql://localhost/information";

    public final static String user="root";

    public final static String password="123456";
    
    public static void Creat(String course_id,String type, String num_ass){
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection(url, user, password);
          //  System.out.println("connection successful");
            Statement stat = (Statement) conn.createStatement();
            ResultSet resultSet = stat.executeQuery("select count(*) from question where course_id='"+course_id+"' and type='"+type+"' and num_ass='"+num_ass+"' ;");
            int i =0;
			if(resultSet.next()){
				 i = resultSet.getInt(1);
			}
            
        	//MySQLAccess.readComment();
            XSSFWorkbook wb=new XSSFWorkbook();//创建一个webbook，对应一个excel文件
          //  System.out.println("connection successful234");
  
            XSSFSheet sheet=wb.createSheet("GradeModel");//在webbook中添加一个sheet，对应一个excel中的sheet
            XSSFRow row = sheet.createRow((short)0);// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
            XSSFCell cell=null;
        
            cell=row.createCell((short)0);//第零行，第零列
            String str="ID/No.que";
            cell.setCellValue(str);
            
            System.out.println(i);
            int j=1;
            while(j<i+1)
            {
            	 cell=row.createCell((short)j);//第零行，第i列
                 cell.setCellValue("num"+j);
                 j++;
            }
            cell=row.createCell((short)j);//第零行，第i列
            cell.setCellValue("total");
            /*     
            XSSFRow row2 = sheet.createRow((short)1);//第一行
            XSSFCell cell2=null;
            cell2=row2.createCell((short)0);//第一行，第零列
            cell2.setCellValue("StudentID");
           */ 
            FileOutputStream FOut = new FileOutputStream(outputFile);
            wb.write(FOut);
            FOut.flush();
            FOut.close();
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}