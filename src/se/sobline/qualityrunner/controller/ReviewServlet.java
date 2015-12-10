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
 * Servlet for adding a review to a product
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

		Controller controller = new Controller();

		HttpSession session = request.getSession();

		String title = controller.cleanInput(request.getParameter("title"));
		String reviewText = controller.cleanInput(request.getParameter("reviewText"));
		String gradeString = controller.cleanInput(request.getParameter("grade"));
		Double grade = Double.parseDouble(gradeString);
		
		if ((controller.userExists((String) session.getAttribute("username")) != null)
				&& (session.getAttribute("currentProduct") != null)) {
			Review review = new Review(controller.userExists((String) session.getAttribute("username")), 
										reviewText, title,
										(Product) session.getAttribute("currentProduct"));
			controller.createReview(review, grade);

			getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	
}
