package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;


@WebServlet(urlPatterns = {
		UrlUtils.HOME,	
		UrlUtils.SSH_KEYS,
		UrlUtils.KLASSES,
})
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.HOME:
			req.getRequestDispatcher(JspUtils.HOME)
				.forward(req, resp);
			break;
		case UrlUtils.SSH_KEYS:
			req.getRequestDispatcher(JspUtils.SSH_KEYS)
				.forward(req, resp);
			break;
		case UrlUtils.KLASSES:
			req.getRequestDispatcher(JspUtils.KLASSES)
				.forward(req, resp);
			break;
		}	
	}
}
