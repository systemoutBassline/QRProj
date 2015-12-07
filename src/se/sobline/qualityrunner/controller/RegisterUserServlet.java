package se.sobline.qualityrunner.controller;

import java.io.IOException;

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
		HttpSession session = request.getSession();
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		
		if(controller.userExists(username) != null) {			
			User user = controller.createUser(username, password);
			session.setAttribute("user", user);
		} else {
			
		}
	}
}
