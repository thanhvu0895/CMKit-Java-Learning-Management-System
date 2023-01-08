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
		UrlUtils.EDIT_SELF,
		UrlUtils.NOTIFICATION_SETTINGS
	})
public class UsersServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8801001997853031448L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.EDIT_SELF:
			req.getRequestDispatcher(JspUtils.EDIT_SELF)
			.forward(req, resp);
			break;
		case UrlUtils.NOTIFICATION_SETTINGS:
			req.getRequestDispatcher(JspUtils.NOTIFICATION_SETTINGS)
			.forward(req, resp);
			break;
		}
	}
}
