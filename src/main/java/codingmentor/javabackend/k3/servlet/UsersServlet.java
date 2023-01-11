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
		UrlUtils.USERS_PATH,
		UrlUtils.USER_EDIT_SELF_PATH,
		UrlUtils.NOTIFICATION_SETTINGS_PATH,
		UrlUtils.CHANGE_PASSWORD_PATH,
		"/users/*"
	})
public class UsersServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8801001997853031448L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.USER_EDIT_SELF_PATH:
			req.getRequestDispatcher(JspUtils.USERS_EDIT_SELF)
			.forward(req, resp);
			break;
		case UrlUtils.NOTIFICATION_SETTINGS_PATH:
			req.getRequestDispatcher(JspUtils.USERS_NOTIFICATION_SETTINGS)
			.forward(req, resp);
			break;
		case UrlUtils.CHANGE_PASSWORD_PATH:
			req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD)
				.forward(req, resp);
			break;
		case UrlUtils.USERS_PATH:
			// LENGTH MAX IS 3
			// if length is greater than 3 => 404
			// If Path[2] != edit+admin || other valid paths, redirect to 404.
			// IF PATH[2] = edit_admin, get pathParts[1]
			// 	if pathParts[1] is not a number, sends 404
			// 		If user ID exists in db, save id in session then redirect to next edit_admin page
			// 		If user ID does not exists, says user id is not found
			
			
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) { // path is users/
				System.out.println("Path info is: " + pathInfo);
				req.getRequestDispatcher(JspUtils.USERS_INDEX)
				.forward(req, resp);
				return;
			}
			String[] pathParts =  pathInfo.split("/");
			int length = pathParts.length;
			if (pathParts[2].equals("edit_admin")) {
				resp.getWriter().format("Length is %d", length);
			}
			
			int index = 0;
			
			for (String string : pathParts) {
				System.out.println(string + (index++));
			}
			break;
		}
	}
}
