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
		UrlUtils.KLASSES,	
		UrlUtils.SSH_KEYS,
		UrlUtils.KLASSES_DETAILS,
		UrlUtils.CHANGE_PASSWORD
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
		case UrlUtils.KLASSES:
			req.getRequestDispatcher(JspUtils.KLASSES)
				.forward(req, resp);
			break;
		case UrlUtils.SSH_KEYS:
			req.getRequestDispatcher(JspUtils.SSH_KEYS_INDEX)
				.forward(req, resp);
			break;
		case UrlUtils.KLASSES_DETAILS:
			req.getRequestDispatcher(JspUtils.KLASS_DETAILS)
				.forward(req, resp);
			break;
		case UrlUtils.CHANGE_PASSWORD:
			req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD)
				.forward(req, resp);
			break;
		}	
	}
}
