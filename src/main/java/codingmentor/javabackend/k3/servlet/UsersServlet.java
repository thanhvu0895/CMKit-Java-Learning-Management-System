package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.StringUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.service.UserService;
import codingmentor.javabackend.k3.service.Impl.UserServiceImpl;

@WebServlet(urlPatterns = {
		UrlUtils.USERS_PATH,
		UrlUtils.USER_EDIT_SELF_PATH,
		UrlUtils.NOTIFICATION_SETTINGS_PATH,
		UrlUtils.CHANGE_PASSWORD_PATH,
		UrlUtils.USERS_ALL_PATH
	})
public class UsersServlet extends HttpServlet{
	private static final long serialVersionUID = -8801001997853031448L;
	private UserService userService = null;
	
	@Override
	public void init() throws ServletException {
	    super.init();
	    userService = UserServiceImpl.getInstance();
	}

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
			
			System.out.println("req.getServletPath():  " + req.getServletPath());
			String pathInfo = req.getPathInfo();
			System.out.println("pathInfo: " +pathInfo);
			
			// IS GET REQUEST /user/
			if (pathInfo == null || pathInfo.equals("/") ) {
				List<User> users = userService.getUsers();
				req.setAttribute("users", users);
				req.getRequestDispatcher(JspUtils.USERS_INDEX)
					.forward(req, resp);
				return;
			}
			
			String[] pathParts =  pathInfo.split("/");
			
			int length = pathParts.length;
			
			// IS GET REQUEST LONG /users/:id/*
			if (length == 3 && StringUtils.isInteger(pathParts[1])) {
				switch (pathParts[2]) {
				case "edit_admin":
					int id = Integer.parseInt(pathParts[1]);
					req.setAttribute("user", userService.findUserById(id));
					
					req.getRequestDispatcher(JspUtils.USERS_EDIT_ADMIN)
						.forward(req, resp);
					break;
				}
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.USERS_PATH:
			String pathInfo = req.getPathInfo();
			String[] pathParts =  pathInfo.split("/");
			int length = pathParts.length;
			if (length == 2 && StringUtils.isInteger(pathParts[1])) {
				String first_name = req.getParameter("user[first_name]");
				String last_name = req.getParameter("user[last_name]");
				String preferred_name = req.getParameter("user[preferred_name]");
				boolean admin = "1".equals(req.getParameter("user[admin]"));
				boolean disabled = "1".equals(req.getParameter("user[disabled]"));
				int id = Integer.parseInt(pathParts[1]);
				
				boolean success = userService.updateUser(first_name, last_name, preferred_name, admin, disabled, id);
				
				if (success) {
					HttpSession session = req.getSession(false);
					session.setAttribute("notice", "User was successfully updated.");
					resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
					break;
				}
			}
			break;		
		}
	}
}
