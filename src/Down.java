

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Down
 */
@WebServlet("/Down")
public class Down extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COURSE="course.jsp";
	private static final String UPLOAD="UploadGradeFile.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Down() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward="";
		String button=request.getParameter("button");
		String url_id=request.getParameter("url_id");
		String course_id=request.getParameter("course_id");
		String type=request.getParameter("type");
		String course_name=request.getParameter("course_name");
		String num_ass=request.getParameter("num_ass");
		
		System.out.println(button);
		if (button.equals("DownloadGradeModel")){
				try{
					DownloadGradeModel.Creat(course_id,type,num_ass);
					forward=COURSE+"?id="+url_id;
					RequestDispatcher view = request.getRequestDispatcher(forward);
					view.forward(request,response);
					System.out.println("DownloadGradeModel successful");
				}catch (Exception e) {
					getServletContext().log("An exception occurred in writeComment ", e);
					throw new ServletException("An exception occurred in writeComment "
						+ e.getMessage());
				}
			}
		
		else if(button.equals("DownloadPaper")){
			try{
				
				CreatePaper cr = new CreatePaper();
		        cr.CreatePaperDoc(course_id,course_name, type, num_ass);
		        
		        System.out.println("Download paper successfully !!!");
				forward=COURSE+"?id="+url_id;
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request,response);
			}catch (Exception e) {
				getServletContext().log("An exception occurred in writeComment ", e);
				throw new ServletException("An exception occurred in writeComment "
					+ e.getMessage());
			}
		}
		else if(button.equals("UploadGradeFile")){
				try{
				forward=UPLOAD+"?id="+url_id+"&type="+type;
				System.out.println(forward);
				RequestDispatcher view = request.getRequestDispatcher(forward);
				view.forward(request,response);
			}catch (Exception e) {
				getServletContext().log("An exception occurred in writeComment ", e);
				throw new ServletException("An exception occurred in writeComment "
					+ e.getMessage());
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
