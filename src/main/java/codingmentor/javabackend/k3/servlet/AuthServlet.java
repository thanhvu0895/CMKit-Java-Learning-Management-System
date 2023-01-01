package codingmentor.javabackend.k3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;

@WebServlet(name = "authServlet", urlPatterns = {
	UrlUtils.SIGN_IN,
	UrlUtils.SIGN_UP
})
public class AuthServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3801412244941307670L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.SIGN_IN:
			req.getRequestDispatcher(JspUtils.SIGN_IN)
				.forward(req, resp);
			break;
		case UrlUtils.SIGN_UP:
			req.getRequestDispatcher(JspUtils.SIGN_UP)
				.forward(req,  resp);
			break;
		}
		
	}
}
