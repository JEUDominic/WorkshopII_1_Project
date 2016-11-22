

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatePaper{
	
    //word文档模版
   	private XWPFDocument doc;
    public void CreatePaperDoc(String course_id,String course_name, String type, String num_ass) throws Exception {
    	MySQLAccess database=new MySQLAccess();
    	
        doc = new XWPFDocument();
        // 创建一个段落
        XWPFParagraph para = doc.createParagraph();
        // 设置对齐方式
        para.setAlignment(ParagraphAlignment.CENTER);

        // 一个XWPFRun代表具有相同属性的一个区域。
        XWPFRun run = para.createRun();
        // 加粗
        run.setBold(true);
        // 设置内容
        
        run.setText(course_name+" "+course_id);//从数据库获取标题
        // 设置字体大小
        run.setFontSize(25);
        // 换行
        run.addBreak();
        
        run.setText(type+" "+num_ass);//从数据库获取标题
        // 设置字体大小
        run.setFontSize(23);
        // 换行
        run.addBreak();

        para = doc.createParagraph();
        para.setAlignment(ParagraphAlignment.LEFT);

        run = para.createRun();
        run.setBold(true);
        run.setText("Question");
        run.setFontSize(22);

        run.addBreak();

        para = doc.createParagraph();
        para.setAlignment(ParagraphAlignment.BOTH);

        run = para.createRun();
        run.setBold(false);
        
        int len=database.getNum(course_id,type,num_ass);
        String[] str1=new String[len];
        String[] str2=new String[len]; 
        str1=database.getquestion_que(course_id,type, num_ass);
        str2=database.getcontent(course_id,type, num_ass);
        int i=0;
        while(i<len){
        run.setFontSize(20);
        run.setText(str1[i]+str2[i]);
        i++;
        run.addBreak();
        }
        
        OutputStream os = new FileOutputStream("/Users/apple/Desktop/"+course_name+course_id+"-"+type+"-"+num_ass+".docx");
        // 把doc输出到输出流
        doc.write(os);
        this.close(os);
    }
   
    private void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
}
