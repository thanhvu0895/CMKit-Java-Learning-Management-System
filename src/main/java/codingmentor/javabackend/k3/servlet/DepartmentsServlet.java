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
		UrlUtils.DEPARTMENTS_PATH,
})
public class DepartmentsServlet extends HttpServlet{
	private static final long serialVersionUID = 1515497142397284883L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.DEPARTMENTS_PATH:
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_INDEX)
			.forward(req, resp);
			break;
		}	
	}
}
