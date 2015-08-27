

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bloguser;
import model.Bullhorn;
import customTools.DBUtil;

/**
 * Servlet implementation class ServletBullhorn
 */
@WebServlet("/ServletBullhorn")
public class ServletBullhorn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBullhorn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String post = request.getParameter("post");
		HttpSession session = request.getSession();
		Bloguser bUser= (Bloguser) session.getAttribute("bloguser");
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		model.Bullhorn postObj = new model.Bullhorn();
		System.out.println(post);
		postObj.setPost(post);
		postObj.setBloguser(bUser);
		System.out.println(bUser.getEmail());
		Database.insert(postObj);
		List<Bullhorn> blogList = Database.getAllPost();
		
		String tableData ="";
		try {
			tableData="\r<table class=table border=1 align=center>";
			
	
			for(Bullhorn b : blogList){
				tableData += "<tr class='info'>";				

				tableData += "<td>";
				tableData +=b.getPost();
				tableData += "</td>";
				tableData+="</tr>\r";
			}
			tableData+="</table>\r";			
			
			
		} catch (Exception e){
			System.out.println(e);
		} finally {
			em.close();
			System.out.println("Done!");
		}
		
		//tableData +="<br><br><a href= 'ServletBullhorn' class='btn btn-primary'>Go Back</a>";
		request.setAttribute("message", tableData);
		getServletContext().getRequestDispatcher("/UserAccount.jsp").forward(request, response);
		
		
	}

}
