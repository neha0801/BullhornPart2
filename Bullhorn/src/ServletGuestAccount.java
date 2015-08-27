

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;
import model.Bullhorn;

/**
 * Servlet implementation class ServletGuestAccount
 */
@WebServlet("/GuestAccount")
public class ServletGuestAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGuestAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
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
		
		tableData +="<br><br><a href= 'LoginForm' class='btn btn-primary'>Go Back</a>";
		request.setAttribute("message", tableData);
		getServletContext().getRequestDispatcher("/GuestAccount.jsp").forward(request, response);
	}

}
