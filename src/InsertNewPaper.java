

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
@WebServlet("/InsertNewPaper")
public class InsertNewPaper extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Course="course.jsp";
	
	private MySQLAccess mySQLAccess;
	public void init() throws ServletException {
		mySQLAccess = new MySQLAccess();
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNewPaper() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Map<String, String[]> parameters = request.getParameterMap();
		String forward="";
		if (parameters.containsKey("Save")){
				
				String course_id = request.getParameter("course_id");
				
				String url_id=request.getParameter("url");
				
				String type = request.getParameter("type");
				
				String num_ass = request.getParameter("num_ass");
				
				String[] num_que = request.getParameterValues("num_que");
				int a=0;
				while(a<num_que.length)
				{
					System.out.println("num_que["+a+"] is "+num_que[a]);
					a++;
				}
				try{
					int p=0;
					int n=0;
					int m=0;
					while(p<num_que.length){
						
					String content = request.getParameter("content"+(p+1));
						
					String[] cilo = request.getParameterValues("cilo"+(p+1));
						
					String[] level = request.getParameterValues("level"+(p+1));
					
					String score = request.getParameter("score"+(p+1));
						
					mySQLAccess.InsertNewQuestion(course_id,type,num_ass,num_que[p],content,score);
					System.out.println("p is "+p);
					System.out.println("content is "+content);
					
					n=0;
					while(n<cilo.length){
					mySQLAccess.InsertNewQuestion_cilo(course_id,type,num_ass,num_que[p],cilo[n]);
					n++;
					}
					m=0;
					while(m<level.length){
					mySQLAccess.InsertNewQuestion_level(course_id,type,num_ass,num_que[p],level[m]);
	
					m++;
					}
					p++;
					}
					
					
					forward=Course+"?id="+url_id;
				
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
