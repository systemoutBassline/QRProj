package se.sobline.qualityrunner.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginUserServlet
 */
@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserServlet() {
        super();
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/QR/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FrontController controller = new FrontController();
		HttpSession session = request.getSession();

		RequestDispatcher rd;
		PrintWriter out;
		
		session.setMaxInactiveInterval(10080 * 60);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		getServletContext();
		
		if(controller.checkLogin(username, password)) {
			session.setAttribute("username", username);
			session.setAttribute("Authenticated", new Boolean(true));
			response.sendRedirect("home.jsp");
		} else {
			rd = getServletContext().getRequestDispatcher("/index.jsp");
            out = response.getWriter();
            out.println("<h1><center><font color=red family=Trebuchent ms>Either username or password is wrong.</font></center></h1>");
            rd.include(request, response);
		}
	}
}
