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
		UrlUtils.REQUEST_PASSWORD_RESET
})
public class PasswordResetServlet extends HttpServlet{

	private static final long serialVersionUID = 391478459100214707L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlUtils.REQUEST_PASSWORD_RESET:
			req.getRequestDispatcher(JspUtils.PASSWORD_RESET_SHOW_PASSWORD_RESET_REQUEST)
				.forward(req, resp);
			break;
		}
	}
}
