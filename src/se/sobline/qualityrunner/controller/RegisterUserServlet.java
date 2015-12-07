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

import se.sobline.qualityrunner.model.User;

/**
 * Servlet implementation class RegisterUserServlet
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
		FrontController controller = new FrontController();
		String username = request.getParameter("userName");
		String password = request.getParameter("password");

		RequestDispatcher rd;
		PrintWriter out;

		getServletContext();

		if (controller.userExists(username) != null) {
			System.out.println("eh?");
			controller.createUser(username, password);

			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

		} else {
			rd = getServletContext().getRequestDispatcher("/index.jsp");
			out = response.getWriter();

			out.println("<h1><center><font color=red family=Trebuchent ms>User already exists!</font></center></h1>");

			rd.include(request, response);
		}
	}
}
