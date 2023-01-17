package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import codingmentor.javabackend.k3.Utils.AccountsMailer;
import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.RandomUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.UserRepository;
import codingmentor.javabackend.k3.repository.Impl.UserRepositoryImpl;

@WebServlet(urlPatterns = { UrlUtils.USERS_PATH, UrlUtils.USER_EDIT_SELF_PATH, UrlUtils.NOTIFICATION_SETTINGS_PATH,
		UrlUtils.CHANGE_PASSWORD_PATH, UrlUtils.USERS_ALL_PATH, UrlUtils.CREATE_USER_INVITE_PATH })
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = -8801001997853031448L;
	private UserRepository userRepository = null;

	@Override
	public void init() throws ServletException {
		super.init();
		userRepository = UserRepositoryImpl.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlUtils.USER_EDIT_SELF_PATH:
			req.getRequestDispatcher(JspUtils.USERS_EDIT_SELF).forward(req, resp);
			break;
		case UrlUtils.NOTIFICATION_SETTINGS_PATH:
			req.getRequestDispatcher(JspUtils.USERS_NOTIFICATION_SETTINGS).forward(req, resp);
			break;
		case UrlUtils.CHANGE_PASSWORD_PATH:
			req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD).forward(req, resp);
			break;
		case UrlUtils.USERS_PATH:
			String pathInfo = req.getPathInfo();
			if (pathInfo == null || pathInfo.equals("/")) { // If Request is /users/ or /users
				List<User> users = userRepository.getUsers();
				req.setAttribute("users", users);
				req.getRequestDispatcher(JspUtils.USERS_INDEX).forward(req, resp);
				return;
			}
			
			String[] pathParts = pathInfo.split("/");
			int length = pathParts.length;
			
			if (length == 3 && UrlUtils.isInteger(pathParts[1])) { // If request pattern is /users/:id/*
				int id = Integer.parseInt(pathParts[1]);
				switch (pathParts[2]) {
				case "edit_admin": // If request is /users/:id/edit_admin
					req.setAttribute("user", userRepository.findUserById(id));
					req.getRequestDispatcher(JspUtils.USERS_EDIT_ADMIN).forward(req, resp);
					break;
				case "set_up": // If request is /users/:id/set_up
					processSetUpUser(req, resp, id);
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
			break;
		case UrlUtils.USER_EDIT_SELF_PATH:
			updateUserPreferredName(req, resp);
			break;
		case UrlUtils.CREATE_USER_INVITE_PATH:		
			processCreateUserInvite(req, resp);
			break;
		case UrlUtils.USERS_PATH:
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) { // if request is /users/ or /users
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return; 
			}
			
			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;

			if (pathInfoLength == 2 && UrlUtils.isInteger(pathParts[1])) { //  If request is /users/:id
				int userid = Integer.parseInt(pathParts[1]);
				switch (req.getParameter("method")) {
				case "delete":
					processDeleteUser(req, resp, userid);
					break;
				case "edit_admin":
					processEditAdmin(req, resp, userid);
					return;
				}
			} 
		}
	}
	
	
	/**
	 * Implement processSetUpUser 1/16/2023
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void processSetUpUser (HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {
		try {
			String token = req.getParameter("token");
			User user = userRepository.findUserById(id);
			if (user != null && userRepository.findUserById(id).validateInviteToken(token)) {
				req.getRequestDispatcher(JspUtils.USERS_SHOW_INVITE).forward(req, resp);
				return;
			} else {
				System.out.println("token does not match or user id does not exist");
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Implement processCreateUserInvite 1/16/2023
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void processCreateUserInvite(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String token = RandomUtils.unique64();
		System.out.println(token);
		String email = req.getParameter("email");
		boolean admin = (req.getParameterValues("admin").length == 2);

		if (userRepository.findUserByEmail(email) != null) {
			req.getSession().setAttribute("alert", "User not invited: Email has already been taken");
			resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
			return;
		}

		userRepository.createUser(email, admin);
		User user =  userRepository.findUserByEmail(email);
		int new_user_id = user.getId();
		try {
			userRepository.updateResetDigest(new_user_id, RandomUtils.SHA256Base64(token));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		try {
			AccountsMailer.invite_user_email(req, user, token);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession(false).setAttribute("notice", "User invited");
		resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
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
	private void processDeleteUser(HttpServletRequest req, HttpServletResponse resp, int userid)
			throws IOException, ServletException {
		User current_user = (User) req.getSession(false).getAttribute("current_user");
		int current_user_id = current_user.getId();
		if (current_user_id == userid) {
			req.getSession(false).setAttribute("alert", "Admin cannot remove themselves!.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
		}
		userRepository.deleteUser(userid);
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
	private void processChangePassword(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		User current_user = (User) req.getSession(false).getAttribute("current_user");
		int userid = current_user.getId();
		current_user = userRepository.findUserById(userid);
		String old_password = req.getParameter("old_password");
		String new_password = req.getParameter("new_password");
		String new_password_confirmation = req.getParameter("new_password_confirmation");

		if (!current_user.getPassword_digest().equals(old_password)) {
			req.setAttribute("alert", "Incorrect old password");
			req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD).forward(req, resp);
			return;
		}

		if (!new_password.equals(new_password_confirmation)) {
			req.setAttribute("alert", "New password and confirm password must match.");
			req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD).forward(req, resp);
			return;
		}

		userRepository.updatePassword(new_password_confirmation, current_user);
		HttpSession session = req.getSession();
		session.setAttribute("notice", "Password changed.");
		resp.sendRedirect(req.getContextPath() + UrlUtils.ROOT_PATH + "/");
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
	private void updateUserPreferredName(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		User current_user = (User) req.getSession(false).getAttribute("current_user");
		int userid = current_user.getId();
		String preferred_name = req.getParameter("user[preferred_name]");
		userRepository.updatePreferredNameById(preferred_name, userid);
		current_user = userRepository.findUserById(userid);
		current_user.setPassword_digest("[FILTERED]");
		req.getSession(false).setAttribute("current_user", current_user);
		if (current_user.isAdmin()) {
			req.getSession(false).setAttribute("notice", "User was successfully updated.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
		} else {
			req.getSession(false).setAttribute("notice", "Your settings have been successfully updated.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.ROOT_PATH + "/");
		}
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
	private void processEditAdmin(HttpServletRequest req, HttpServletResponse resp, int userid)
			throws IOException, ServletException {
		String first_name = req.getParameter("user[first_name]");
		String last_name = req.getParameter("user[last_name]");

		if (first_name == "" || last_name == "") {
			req.setAttribute("alert", "First name and last name must not be blank");
			req.setAttribute("user", userRepository.findUserById(userid));
			req.getRequestDispatcher(JspUtils.USERS_EDIT_ADMIN).forward(req, resp);
		}

		String preferred_name = req.getParameter("user[preferred_name]");

		boolean admin = (req.getParameterValues("user[admin]").length == 2);
		boolean disabled = (req.getParameterValues("user[disabled]").length == 2);

		userRepository.updateUser(first_name, last_name, preferred_name, admin, disabled, userid);
		req.getSession(false).setAttribute("notice", "User was successfully updated.");

		User current_user = (User) req.getSession(false).getAttribute("current_user");
		int current_user_id = current_user.getId();
		if (current_user_id == userid) {
			current_user = userRepository.findUserById(current_user_id);
			current_user.setPassword_digest("[FILTERED]");
			req.getSession(false).setAttribute("current_user", current_user);
		}

		resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
	}
}
