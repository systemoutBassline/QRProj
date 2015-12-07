package se.sobline.qualityrunner.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		FrontController controller = getController(request);

		if (controller.getProducts() != null) {
			request.setAttribute("products", controller.getProducts());
			getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FrontController controller = getController(request);

		String productName = request.getParameter("productname");

		if (currentProduct(controller, productName) != null) {
			Product product = currentProduct(controller, productName);
			List<Review> productReviews = product.getReviews();
			request.setAttribute("product", product);
			request.setAttribute("productReviews", productReviews);
			getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	private Product currentProduct(FrontController controller, String productName) {
		for (Product product : controller.getProducts()) {
			if (product.getName().equals(productName)) {
				return product;
			}
		}
		return null;
	}

	private FrontController getController(HttpServletRequest request) {

		if (request.getAttribute("controller") == null) {
			FrontController controller = new FrontController();
			request.setAttribute("controller", controller);
			return controller;
		} else {
			return (FrontController) request.getAttribute("controller");
		}
	}
}
