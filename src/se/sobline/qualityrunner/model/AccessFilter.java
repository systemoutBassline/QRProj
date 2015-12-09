package se.sobline.qualityrunner.model;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Charlotte & Joel
 * Servlet for making pages secure with filter
 * @since 09.12.2015
 * @version 1.0
 */
@WebFilter("/AccessFilter")
public class AccessFilter implements Filter {
	
	private String contextPath;

    /**
     * Default constructor. 
     */
    public AccessFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		System.out.println("In do filter");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		System.out.println("Requested resource: " + uri);
		
		HttpSession session = req.getSession(false);
		Boolean authenticated = new Boolean(false);
		
		if(null != session) {
			authenticated = (Boolean)session.getAttribute("Authenticated");
			if(null == authenticated) {
				authenticated = new Boolean(false);
			}
		}
		System.out.println("Session: " + session);
		System.out.println("Authenticated: " + authenticated);
		
		if((session == null) || (!authenticated)) {
			System.out.println("Unauthorized access request");
			res.sendRedirect(contextPath + "/index.jsp");
		}
		else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

		contextPath = fConfig.getServletContext().getContextPath();
	}
}
