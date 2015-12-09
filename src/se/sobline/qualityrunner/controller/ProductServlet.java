package se.sobline.qualityrunner.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import se.sobline.qualityrunner.model.Product;
import se.sobline.qualityrunner.model.Review;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public final class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Controller controller = getController(request);
		HttpSession session = request.getSession();

		if (controller.getProducts() != null) {
			session.setAttribute("products", controller.getProducts());
			getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Controller controller = new Controller();
		HttpSession session = request.getSession();
		
		String productName = request.getParameter("productname");

		if (currentProduct(controller, productName, session) != null) {
			Product product = currentProduct(controller, productName, session);
			List<Review> productReviews = product.getReviews();
			session.setAttribute("productReviews", productReviews);
			getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	private Product currentProduct(Controller controller, String productName, HttpSession session) {
		for (Product product : controller.getProducts()) {
			if (product.getName().equals(productName)) {
				session.setAttribute("currentProduct", product);
				return product;
			}
		}
		return null;
	}

	private Controller getController(HttpServletRequest request) {

		if (request.getAttribute("controller") == null) {
			Controller controller = new Controller();
			request.setAttribute("controller", controller);
			return controller;
		} else {
			return (Controller) request.getAttribute("controller");
		}
	}
}
