

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertNewPaper
 */
@WebServlet("/InsertNewCourse")
public class InsertNewCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Home="home.jsp";
	
	private MySQLAccess mySQLAccess;
	public void init() throws ServletException {
		mySQLAccess = new MySQLAccess();
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNewCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String forward="";
		Map<String, String[]> parameters = request.getParameterMap();
		if (parameters.containsKey("Save")){
				
				String course_name = request.getParameter("course_name");
				
				String course_period = request.getParameter("course_period");
				
				String teacher_id = request.getParameter("teacher_id");
				
				String ta_id = request.getParameter("ta_id");
			
				String assignment = request.getParameter("assignment");
				
				String quiz = request.getParameter("quiz");
				
				String test = request.getParameter("test");
				
				String midterm = request.getParameter("midterm");
				
				String final_ = request.getParameter("final");
				
				String[] cilo_num = request.getParameterValues("cilo_num");
				
				
				try{
					System.out.println("teacher_id is "+teacher_id);
					mySQLAccess.InsertNewCourse(course_name, course_period, teacher_id, ta_id);
					//System.out.println("Insert 1 successful");
					
					String course_id=mySQLAccess.getCourse_id(course_name,course_period);
					
					int len=cilo_num.length;
			
					mySQLAccess.InsertNewCourse_info(course_id, assignment, quiz, test, midterm, final_, len);
					//System.out.println("Insert 2 successful");
					int i=0;
					while(i<cilo_num.length){
					
						String content = request.getParameter("content"+(i+1));
						String pilo_num=request.getParameter("pilo_num"+(i+1));
						
					mySQLAccess.InsertNewCourse_cilo(course_id,cilo_num[i],content,pilo_num);
					i++;
					}
					//System.out.println("Insert 3 successful");
					
					forward=Home;
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
