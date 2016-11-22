package JDBC;
import Table.*;
import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String WEL_JSP = "/ZWorkshopProject/home.jsp";
	private static String LOGIN_JSP = "/ZWorkshopProject/login.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward="";
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		// Get a map of the request parameters
		Map<String, String[]> parameters = request.getParameterMap();
		if (parameters.containsKey("login")){
			//int state = DatabaseOperation.Login(request.getParameter("email"),request.getParameter("password"));
			if (DatabaseOperation.Login(email,password) == 1){
				User loginUser = DatabaseOperation.getUser(email);
				//String[] course_id = {"01","02","03"};
				//int course_num = DatabaseOperation.getCourseNum(loginUser);
				String course_num = Integer.toString(DatabaseOperation.getCourseNum(loginUser));
				Course[] course = DatabaseOperation.getCourse(loginUser);
				String[] course_id = new String[Integer.parseInt(course_num)];
				String[] course_name = new String[Integer.parseInt(course_num)];
				String[] course_period = new String[Integer.parseInt(course_num)];
				String[] course_ta = new String[Integer.parseInt(course_num)];
				for(int i =0;i<Integer.parseInt(course_num);i++){
					course_id[i] = Integer.toString(course[i].getCourse_id());
					course_name[i] = course[i].getName();
					course_ta[i] = Integer.toString(course[i].getTa_id());
					course_period[i] = Operation.transDate(course[i].getCourse_period());
				}
				//request.getSession().setAttribute("user_id", "1");
				request.getSession().setAttribute("user_id", Integer.toString(loginUser.getID()));
				//request.getSession().setAttribute("username", "Gigi");
				request.getSession().setAttribute("username", loginUser.getName());
				request.getSession().setAttribute("email",email);
				request.getSession().setAttribute("course_id", course_id);
				request.getSession().setAttribute("course_name", course_name);
				request.getSession().setAttribute("course_ta", course_ta);
				request.getSession().setAttribute("course_period", course_period);
				request.getSession().setAttribute("course_num", course_num);
				forward = WEL_JSP;
			}else{
				request.getSession().setAttribute("error","Log in failed");
				forward = LOGIN_JSP;
			}
		}
		else
		{
			forward = LOGIN_JSP;
		}
		response.sendRedirect(forward);
		//request.getRequestDispatcher(forward).forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
