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
 * Servlet implementation class ReviewServlet
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
		FrontController controller = getController(request);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		Product product = (Product) request.getAttribute("product");
		out.print("<html><body>" + product.getName() + "</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FrontController controller = getController(request);

	}

	/***
	 * create or get controller
	 * 
	 * @param request
	 * @return new controller if not initiated, else return initiated controller
	 */
	protected FrontController getController(HttpServletRequest request) {
		if (request.getAttribute("controller") == null) {
			FrontController controller = new FrontController();
			request.setAttribute("controller", controller);
			return controller;
		} else {
			return (FrontController) request.getAttribute("controller");
		}
	}
}
