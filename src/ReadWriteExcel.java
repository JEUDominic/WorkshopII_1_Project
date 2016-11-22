
//Database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReadWriteExcel {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 判断Excel的版本,获取Workbook
     * 
     * @param in
     * @param filename
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) { // Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     * 判断文件是否是excel
     * 
     * @throws Exception
     */
    public static void checkExcelVaild(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
            throw new Exception("文件不是Excel");
        }
    }

    /**
     * 读取Excel测试，兼容 Excel 2003/2007/2010
     * 
     * @throws FileNotFoundException
     * @throws Exception
     */

    @SuppressWarnings("finally")
    public static JSONArray readExcel(String path, String name, String course_id, String type, String num_ass) throws Exception {
        String sqlfile =path+ "//" + name.substring(0,name.lastIndexOf(".")) +".txt";
        System.out.println(sqlfile);
        //String sqlfile = path + "//" + name + "InsertSQL.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(sqlfile)));
        String filename = path + "//" + name;
        JSONArray array =null;
        try {
            // 同时支持Excel 2003、2007
            File excelFile = new File(filename); // 创建文件对象
            FileInputStream is = new FileInputStream(excelFile); // 文件流
            checkExcelVaild(excelFile);
            Workbook workbook = getWorkbok(is, excelFile);
            // Workbook workbook = WorkbookFactory.create(is); // 这种方式
            // Excel2003/2007/2010都是可以处理的

            int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
            /**
             * 设置当前excel中sheet的下标：0开始
             */
            Sheet sheet = workbook.getSheetAt(0); // 遍历第一个Sheet
            Row row;
            Cell cell1;
            int rows = sheet.getLastRowNum();
            // json数组
            array = new JSONArray();
            //用于存储excel表，第一行字段内容
            String[] tag = new String[20];
            int tagNum =0;
            JSONObject jsonObj = null;
            System.out.println("rows is "+rows);
           
         
            for (int icount = 0; icount <= rows; icount++) {

                jsonObj = new JSONObject();

                row = sheet.getRow(icount);
                int line = row.getPhysicalNumberOfCells();
                 System.out.println("line is "+line);

                for (int j = 0; j < line; j++) {
                    cell1 = row.getCell(j);
                    if (icount == 0) {
                        tagNum = line;    
                        tag[j] = cell1.toString();//excel第一行 icount=0 第一行（标题行）
                        System.out.println("123");
                        System.out.println("cell1 is "+cell1);
                    } 
                    else {
                    	if(j==line-1){//存total列
                        jsonObj.put(tag[j], cell1);

                    	}
                        else{
	                        	jsonObj.put(tag[j], cell1);//tag:存标题，cell存数值
                        }
                    }

                }
                if (icount != 0) {
                    array.put(jsonObj);
                }


            }

            //System.out.println(array.toString());
            String tableName="grade";
            writeSql(tag,array,bw,tagNum,tableName,course_id,type, num_ass);

            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bw.close();
            return array;
        }
    }

    /**
     * 
        * @Title: writeSql
        * @Description: TODO
        * @param @param tag
        * @param @param array
        * @param @param bw
        * @param @param tagNum
        * @param @param tableName    参数
        * @return void    返回类型
        * @throws
     */
    private static void writeSql(String[] tag, JSONArray array, BufferedWriter bw, int tagNum,String tableName, String course_id, String type, String num_ass) {
        // TODO Auto-generated method stub
        String sql = "";
        sql = "INSERT INTO `" + tableName + "`(`course_id`, `student_id`, `type`, `num_ass`, `num_que`, `grade_per` ";
        
        sql += ") VALUES";
        
        
        
        JSONObject jsonObj = null;
        for(int i=0;i<array.length();i++){
            jsonObj = (JSONObject) array.get(i);
            for(int j=0;j<tagNum-2;j++){
            	
                    sql += " ( ";
                    
                    sql += "'" +course_id+"'" + " ,";//course_id
                    
                    sql += "'" +jsonObj.get(tag[0])+"'" + " ,";//student_id
                    
                    sql += "'" +type+"'" + " ,";//type
                    
                    sql += "'" +num_ass+"'" + " ,";//num_ass
                    
                    sql += "'" +(j+1)+"'" + " ,";//num_que
                    
                    if(i == array.length()-1&&j==tagNum-3){
                    	sql += "'" +jsonObj.get(tag[j+1])+"'" +" )";
                    }
                    else{
                    	sql += "'" +jsonObj.get(tag[j+1])+"'" +" ),";
                    }
            }
          
        }

        System.out.print(sql);
        //执行sql语句，向数据库插入数据,学生每道题的成绩
        Database(sql);
        
        /*
        String sql1 = "";
        sql1 = "INSERT INTO `grade_total`(`course_id`, `student_id`, `type`, `num_ass`,`grade_sum` ";
        
        sql1 += ") VALUES";
        
        
        JSONObject jsonObj1 = null;
        for(int i=0;i<array.length();i++){
            jsonObj1 = (JSONObject) array.get(i);
                    sql1 += " ( ";
                    
                    sql1 += "'" +course_id+"'" + " ,";//course_id
                    
                    sql1 += "'" +jsonObj1.get(tag[0])+"'" + " ,";//student_id
                    
                    sql1 += "'" +type+"'" + " ,";//type
                    
                    sql1 += "'" +num_ass+"'" + " ,";//num_ass
                  
                    if(i == array.length()-1){
                    	sql1 += "'" +jsonObj1.get(tag[tagNum-1])+"'" +" )";
                    }
                    else{
                    	sql1 += "'" +jsonObj1.get(tag[tagNum-1])+"'" +" ),";
                    }
           
          
        }
        System.out.print(sql1);
        //执行sql1语句，向数据库插入数据,学生总成绩
        Database(sql1);
        */
        try {
            bw.write(sql);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
 // database connection address
	private final static String URL = "jdbc:mysql://localhost/information";//"information 是数据库的名称"
	// username
	public final static String USERNAME = "root";
	// password
	public final static String PASSWORD = "123456";
	// load driver program class(in the jar package imported)
	public final static String DRIVER = "com.mysql.jdbc.Driver";

    public static void Database(String sql){
 
    	// method: insert data
    		try{
    			// 1. load database driver program
    			Class.forName(DRIVER);
    			
    			// 2. obtain database connection
    			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    			// 3. create SQL sentences
    			//String sql = "INSERT INTO `user`(`id`, `name`, `password`, `email`, `state`) VALUES (1430003002 , 'Vision', 'fengjiayi', '530300865@qq.com', 1)";

    			// 4. construct a statement instance(used to send sql sentenses)
    			Statement state = connection.createStatement();
    			
    			// 5. execute sql sentences
    			state.executeUpdate(sql);
    			
    			// 6. close connection(release resources)
    			state.close();
    			connection.close();
    			
    			System.out.println("insert success");
    		} catch(ClassNotFoundException e) {
    			e.printStackTrace();
    		} catch(SQLException e) {
    			e.printStackTrace();
    		}
    	}

}
