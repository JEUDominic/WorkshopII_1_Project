//package cn.hejing.servlet;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONArray;

//import cn.hejing.util.ReadWriteExcel;

/**
 * Servlet implementation class ImportData
 */
@WebServlet("/ImportData")
public class ImportData extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportData() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String course_id=request.getParameter("course_id");
		String type1=request.getParameter("type");
		String num_ass=request.getParameter("num_ass");
    	String i = request.getParameter("id");
    	
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //allow file types
        String[] allowTypes = new String[] { "xls", "xlsx" };

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        // file store path
        String path = "/Users/apple/Desktop";

        JSONArray array = null;

        try {
            List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
            Iterator itr = items.iterator();
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();
                if (item.isFormField()) {

                } else {
                    // check the upload file
                    if (item.getName() != null && !item.getName().equals("")) {
                        String name = item.getName();
                        name = name.substring(name.lastIndexOf("/") + 1);
                        System.out.println(name);
                        String type = name.substring(name.lastIndexOf('.') + 1);
                        boolean flag = false;
                        for (String at : allowTypes) {
                            if (at.equals(type)) {
                                flag = true;
                            }
                        }

                        // file type not match
                        if (flag == false) {
                            System.out.println("文件格式不支持");
                            request.setAttribute("message", "文件格式不支持");

                            RequestDispatcher rd = request.getRequestDispatcher("UploadGradeFile.jsp"+"?id="+i+"&type="+type1);
                            rd.forward(request, response);
                        } else {
                            int start = name.lastIndexOf("/");
                            String filename = name.substring(start + 1);


                            // save the upload file into target directory
                            File file = new File(path + "/" + filename);
                            item.write(file);
                            request.setAttribute("course", name);


                        // 调用ReadWriteExcel的静态方法 readExcel()去处理excel文件 
                        //    String course_id=request.getParameter("course_id");
        				//	String type1=request.getParameter("type");
                          //		String num_ass=request.getParameter("num_ass");
        					
        			
        					System.out.println("course_id is "+course_id);
                            array = ReadWriteExcel.readExcel(path, name,course_id,type1,num_ass);

                            //获取 JSONArray 传递给resultdata.jsp页面
                            RequestDispatcher rd = request.getRequestDispatcher("resultdata.jsp"+"?id="+i+"&type="+type1);
                            request.setAttribute("resultData", array);
                            rd.forward(request, response);

                        }
                    } else {
                        System.out.println("请选择待上传文件");
                        request.setAttribute("message", "请选择待上传文件");
                        RequestDispatcher rd = request.getRequestDispatcher("UploadGradeFile.jsp"+"?id="+i+"&type="+type1);
                        rd.forward(request, response);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "文件上传失败");
        }



    }



    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}