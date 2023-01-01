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
        UrlUtils.NOT_FOUND,
        UrlUtils.INTERNAL_ERROR
})

public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			switch (req.getServletPath()) {
				case UrlUtils.NOT_FOUND -> resp.sendRedirect(JspUtils.NOT_FOUND);
				case UrlUtils.INTERNAL_ERROR -> resp.sendRedirect(JspUtils.INTERNAL_ERROR);
			}
		}
}
