package com.formbean.filter;

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

import com.formbean.entity.LoginEntity;
import com.formbean.session.SessionManager;
import com.formbean.session.UserSession;

/**
 * Servlet Filter implementation class AccesControl
 */
@WebFilter("/*")
public class AccesControl implements Filter {

	/**
	 * Default constructor.
	 */
	public AccesControl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		SessionManager sManager = new SessionManager();
		UserSession uSession = sManager.getUserSession(req);

		/*
		 * HttpSession session = req.getSession(false); System.out.println("session: " +
		 * session); LoginEntity le = (LoginEntity) session.getAttribute("usersession");
		 */
		
		System.out.println("Servlet path: " + req.getServletPath());
		System.out.println("Session: " + uSession);
		
		if(req.getServletPath().startsWith("/assets")) {
			chain.doFilter(request, response);
			return;
		}
		
		if(req.getServletPath().startsWith("/register")) {
			chain.doFilter(request, response);
			return;
		}
		
		if(req.getServletPath().startsWith("/images")) {
			//System.out.println("pasando");
			chain.doFilter(request, response);
			return;
		}
		
		
		if (req.getServletPath().equals("/") || req.getServletPath().equals("/userform") || req.getServletPath().equals("/login")) {

			if (uSession != null) {
				res.sendRedirect("feed");
				return;
			}

			chain.doFilter(request, response);
			return;
		}
		
		System.out.println("User session: " + uSession);
		
		if(uSession == null) {
			res.sendRedirect("login");
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
