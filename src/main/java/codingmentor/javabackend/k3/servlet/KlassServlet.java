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
	UrlUtils.KLASSES_PATH,
	
})
public class KlassServlet extends HttpServlet {

	private static final long serialVersionUID = -290278653216172056L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.KLASSES_PATH:
			req.getRequestDispatcher(JspUtils.KLASSES_INDEX)
				.forward(req, resp);
			break;
		}
	}
}
