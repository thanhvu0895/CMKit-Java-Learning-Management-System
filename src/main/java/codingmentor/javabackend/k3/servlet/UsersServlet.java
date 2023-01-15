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
			String pathInfo = req.getPathInfo();
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
		
		switch (req.getServletPath()) {
		case UrlUtils.CHANGE_PASSWORD_PATH:
			processChangePassword(req, resp);
			resp.sendRedirect("./");
			break;
		case UrlUtils.USERS_PATH:
			String pathInfo = req.getPathInfo();

			if (pathInfo == null || pathInfo.equals("/") ) { // AVOID pathInfo.spilit error
				return; // TODO: RETURN 404
			}

			String[] pathParts =  pathInfo.split("/");
			int pathInfoLength = pathParts.length;
			
			if (pathInfoLength == 2 && StringUtils.isInteger(pathParts[1])) {
				int userid = Integer.parseInt(pathParts[1]);
				switch (req.getParameter("method")) {
				case "delete":
					processDeleteUser(req, resp, userid);
					break;
				case "update_preferred_name":
					updateUserPreferredName(req, resp, userid);
					break;
				case "edit_admin":
					processEditAdmin(req, resp, userid);
					return;
				}	
			}		
		}
	}
	

	/***
	 * Implement processDeletePassword method Date: 1/14/2023
	 * 
	 * @param req
	 * @param resp
	 * @param userid user's id
	 * @throws IOException
	 * @throws ServletException
	 */
	private void processDeleteUser(HttpServletRequest req, HttpServletResponse resp, int userid) throws IOException, ServletException {
		userService.deleteUser(userid);
		req.getSession(false).setAttribute("notice", "User was successfully deleted.");
		resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
	}

	/***
	 * Implement processChangePassword method Date: 1/14/2023
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void processChangePassword(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		User current_user = (User) req.getSession(false).getAttribute("current_user");
		int userid = current_user.getId();
		current_user = userService.findUserById(userid);
		String old_password = req.getParameter("old_password");
		String new_password = req.getParameter("new_password");
		String new_password_confirmation = req.getParameter("new_password_confirmation");
		
		if (!current_user.getPassword_digest().equals(old_password)) {
			req.setAttribute("alert", "Incorrect old password");
			req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD)
				.forward(req, resp);
			return;
		}
		
		if (!new_password.equals(new_password_confirmation)) {
			req.setAttribute("alert", "New password and confirm password must match.");
			req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD)
				.forward(req, resp);
			return;
		}
		
		userService.updatePassword(new_password_confirmation, current_user);
		HttpSession session = req.getSession();
		session.setAttribute("notice", "Password changed.");
	}
	
	/***
	 * Implement updateUserPreferredName method Date: 1/14/2023
	 * 
	 * @param req
	 * @param resp
	 * @param userid user'id
	 * @throws IOException
	 * @throws ServletException
	 */
	private void updateUserPreferredName(HttpServletRequest req, HttpServletResponse resp, int userid) throws IOException, ServletException {
		String preferred_name = req.getParameter("user[preferred_name]");
		userService.updatePreferredNameById(preferred_name, userid);

		req.getSession(false).setAttribute("current_user", userService.findUserById(userid));
		req.getSession(false).setAttribute("notice", "User was successfully updated.");
		resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
	}
	
	/***
	 * Implement processEditAdmin method Date: 1/14/2023
	 * 
	 * @param req
	 * @param resp
	 * @param userid user'id
	 * @throws IOException
	 * @throws ServletException
	 */
	private void processEditAdmin(HttpServletRequest req, HttpServletResponse resp, int userid) throws IOException, ServletException {
		String first_name = req.getParameter("user[first_name]");
		String last_name = req.getParameter("user[last_name]");
		
		if (first_name == "" || last_name == "")  {
			req.setAttribute("alert", "First name and last name must not be blank");
			req.setAttribute("user", userService.findUserById(userid));
			req.getRequestDispatcher(JspUtils.USERS_EDIT_ADMIN)
				.forward(req, resp);
		}
		
		String preferred_name = req.getParameter("user[preferred_name]");
		
		boolean admin = (req.getParameterValues("user[admin]").length == 2);
		boolean disabled = (req.getParameterValues("user[disabled]").length == 2);

		userService.updateUser(first_name, last_name, preferred_name, admin, disabled, userid);
		req.getSession(false).setAttribute("notice", "User was successfully updated.");
		resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
	}
}
