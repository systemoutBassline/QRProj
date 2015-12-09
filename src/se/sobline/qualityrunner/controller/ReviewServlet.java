package se.sobline.qualityrunner.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import se.sobline.qualityrunner.model.Product;
import se.sobline.qualityrunner.model.Review;

/**
 * Servlet implementation class ReviewServlet
 * @author Charlotte & Joel
 * @since 09.12.2015
 * @version 1.0
 */
@WebServlet("/ReviewServlet")
public final class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Controller controller = getController(request);

		HttpSession session = request.getSession();

		String title = request.getParameter("title");
		String reviewText = request.getParameter("reviewText");
		String gradeString = request.getParameter("grade");
		int grade = Integer.parseInt(gradeString);

		if ((controller.userExists((String) session.getAttribute("username")) != null)
				&& (session.getAttribute("currentProduct") != null)) {
			Review review = new Review(controller.userExists((String) session.getAttribute("username")), 
										reviewText, grade, title,
										(Product) session.getAttribute("currentProduct"));
			controller.createReview(review);

			getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/***
	 * create or get controller
	 * 
	 * @param request
	 * @return new controller if not initiated, else return initiated controller
	 */
	protected Controller getController(HttpServletRequest request) {
		if (request.getAttribute("controller") == null) {
			Controller controller = new Controller();
			request.setAttribute("controller", controller);
			return controller;
		} else {
			return (Controller) request.getAttribute("controller");
		}
	}
}
