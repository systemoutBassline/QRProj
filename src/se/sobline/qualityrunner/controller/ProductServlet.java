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
		Controller controller = getController(request);
		
		if (controller.getAllProducts() != null) {
			request.setAttribute("products", controller.getAllProducts());
			getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/ops.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Controller controller = getController(request);
		String productName = request.getParameter("productname");

		if (getCurrentProduct(controller, productName) != null) {
			Product product =getCurrentProduct(controller, productName);
//			List<Review> productReviews = product.getReviews();	
			request.setAttribute("product", product);
//			request.setAttribute("productReviews", productReviews);
//			getServletContext().getRequestDispatcher("/ops.jsp").forward(request, response);	
		}
		getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
	}

	private Product getCurrentProduct(Controller controller, String productName) {
		for (Product product : controller.getAllProducts()) {
			if (product.getName().equals(productName)) {
				return product;
			}
		}
		return null;
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
