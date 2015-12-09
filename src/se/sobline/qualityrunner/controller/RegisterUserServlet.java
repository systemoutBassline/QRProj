package se.sobline.qualityrunner.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * Servlet for registration
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 */
@WebServlet("/RegisterUserServlet")
public final class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Controller controller = new Controller();
		String username = controller.cleanInput(request.getParameter("username"));
		String password = controller.cleanInput(request.getParameter("password"));
		String password2 = controller.cleanInput(request.getParameter("password2"));
		
		RequestDispatcher rd;
		PrintWriter out;

		getServletContext();
		
		if (controller.userExists(username) != null) {

			rd = getServletContext().getRequestDispatcher("/index.jsp");
			out = response.getWriter();

			out.println("<h1><center><font color=red family=Trebuchent ms>User already exists!</font></center></h1>");

		} else {

			rd = getServletContext().getRequestDispatcher("/index.jsp");
			out = response.getWriter();

			if (password.equals(password2)) {
				controller.createUser(username, controller.cleanInput(password));

				out.println(
						"<h1><center><font color=red family=Trebuchent ms>Registration complete!</font></center></h1>");

				rd.include(request, response);
			} else {
				rd = getServletContext().getRequestDispatcher("/index.jsp");
				out = response.getWriter();
				out.println("<h1><center><font color=red family=Trebuchent ms>Passwords ain't matchin'!</font></center></h1>");

				rd.include(request, response);
			}
			// getServletContext().getRequestDispatcher("/index.jsp").forward(request,
			// response);
		}
	}
}
