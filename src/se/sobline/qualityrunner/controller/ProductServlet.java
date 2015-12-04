package se.sobline.qualityrunner.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import se.sobline.qualityrunner.model.Product;

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
		request.setAttribute("products", controller.getAllProducts());

		System.out.println("size: " + controller.getAllProducts().size());

		getServletContext().getRequestDispatcher("/products.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Controller controller = getController(request);

		String productname = request.getParameter("productname");

		for (Product p : controller.getAllProducts()) {
			if (p.getName().equals(productname)) {
				request.setAttribute("product", p);
			}
		}
		
		getServletContext().getRequestDispatcher("/product.jsp").forward(request, response);
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
