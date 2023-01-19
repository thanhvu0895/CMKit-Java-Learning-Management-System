package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
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
import codingmentor.javabackend.k3.Utils.PBKDF2Hasher;
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
	private PBKDF2Hasher hasher = null;

	@Override
	public void init() throws ServletException {
		super.init();
		userRepository = UserRepositoryImpl.getInstance();
    	hasher = new PBKDF2Hasher();
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
			int pathInfoLength = pathParts.length;
			
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1])) { // If request pattern is /users/:id/*
				int id = Integer.parseInt(pathParts[1]);
				switch (pathParts[2]) {
				case "edit_admin": // If request is /users/:id/edit_admin
					req.setAttribute("user", userRepository.findUserById(id));
					req.getRequestDispatcher(JspUtils.USERS_EDIT_ADMIN).forward(req, resp);
					break;
				case "set_up": // If request is /users/:id/set_up
					getSetUpUserPage(req, resp, id);
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
			postSetUpUserPage(req, resp);
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
			
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1])) { // If request pattern is PATH: /users/:id/*
				System.out.println("Value of req.getRequestURL() is: " + req.getRequestURL());
				System.out.println("Value of req.getServletPath() is: " + req.getServletPath());
				int id = Integer.parseInt(pathParts[1]);
				switch (pathParts[2]) {
				case "accept_invite": // FROM_PATH: SHOW_USER_INVITE_PATH | TO_PATH: ACCEPT_USER_INVITE_PATH  |  JSP: USERS_SHOW_INVITE
					String token = req.getParameter("user[token]");
					String password = req.getParameter("user[password]");
					String password_confirmation = req.getParameter("user[password_confirmation]");
					String preferred_name = req.getParameter("user[preferred_name]");
					String first_name = req.getParameter("user[first_name]");
					String last_name = req.getParameter("user[last_name]");
					
					System.out.println("Value of token is: " + token);
					System.out.println("Value of first_name is: " + first_name);
					System.out.println("Value of last_name is: " + last_name);
					System.out.println("Value of preferred_name is: " + preferred_name);
					System.out.println("Value of password is: " + password);
					System.out.println("Value of password_confirmation is: " + password_confirmation);
					
					if (first_name == "" || last_name == "") {
						req.getSession(false).setAttribute("alert", "Failed to create account: First name and last name are required.");
						resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.SHOW_USER_INVITE_PATH, id) + "?token=" + token);
						return;
					}
					
					if (password == "" || password_confirmation == "") {
						req.getSession(false).setAttribute("alert", "Please create a password for your account.");
						resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.SHOW_USER_INVITE_PATH, id) + "?token=" + token);
						return;
					}
					
					
					if (!password.equals(password_confirmation)) {
						req.getSession(false).setAttribute("alert", "Failed to create account: Password confirmation doesn't match Password.");
						resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.SHOW_USER_INVITE_PATH, id) + "?token=" + token);
						return;
					}
					
					boolean set_up = userRepository.updateUserInviteParams(id, first_name, last_name, preferred_name, password_confirmation);
					
					if (!set_up) {
						req.getSession(false).setAttribute("alert", "Failed to create account");
						resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.SHOW_USER_INVITE_PATH, id) + "?token=" + token);
						return;
					}
					
					userRepository.updateSetUpUser(id);
					req.getSession(false).setAttribute("notice", "Accout created!");
					resp.sendRedirect(req.getContextPath() + UrlUtils.ROOT_PATH + "/");

					// PSEUDO CODE FOR THIS
					// If password and password_confirmation is empty
					// 		set session attribute "Please create a password for your account."
					//		redirect_to SHOW_USER_INVITE_PATH with ?token = 
					// 		stop function
					// If password and password confirmation is not equal
					//		set session attribute "Failed to create account: Password confirmation doesn't match Password."
					//		redirect_to SHOW_USER_INVITE_PATH with ?token =
					// If First Name or Last Name is empty
					// 		set session attribute "set req attribute "Failed to create account: First name and last name are required."
					//		redirect_to SHOW_USER_INVITE_PATH with ?token =
					// Send update request to user with params: first_name, last_name, preferred_name, password
					// if (update request is successful) 
					//		Set_up = true
					//		redirect_to root 
					break;
				}
			}
		}
	}
	
	
	/**
	 * Implement processSetUpUser 1/16/2023
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getSetUpUserPage (HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {
		try {
			String token = req.getParameter("token");
			User user = userRepository.findUserById(id);
			if (user != null && user.validateInviteToken(token)) {
				req.setAttribute("userid", id);
				req.setAttribute("token", token);
				req.setAttribute("user", user);
				req.getRequestDispatcher(JspUtils.USERS_SHOW_INVITE).forward(req, resp);
				return;
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Implement processCreateUserInvite 1/16/2023
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void postSetUpUserPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String token = RandomUtils.unique64();
		String email = req.getParameter("email");
		boolean admin = (req.getParameterValues("admin").length == 2);
		if (email == "") {
			req.getSession().setAttribute("alert", "User not invited: Email can't be blank");
			resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
			return;
		}
		if (userRepository.findUserByEmail(email) != null) {
			req.getSession().setAttribute("alert", "User not invited: Email has already been taken");
			resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
			return;
		}
		userRepository.createUserSendInvite(email, admin);
		User user =  userRepository.findUserByEmail(email);
		int new_user_id = user.getId();
		try {
			userRepository.updateResetDigest(new_user_id, RandomUtils.SHA256Base64(token));
			AccountsMailer.invite_user_email(req, user, token);
		} catch (Exception e) {
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
		
		if (!hasher.checkPassword(old_password.toCharArray(), current_user.getPassword_digest())) {
			req.setAttribute("alert", "Incorrect old password");
			req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD).forward(req, resp);
			return;
		}
		
		if (new_password == "" || new_password_confirmation == "") {
			req.setAttribute("alert", "New Password cannot be empty");
			req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD).forward(req, resp);
			return;
		}
		
		if (!new_password.equals(old_password)) {
			req.setAttribute("alert", "New password and old password cannot be the same.");
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
			return;
		} 
		
		req.getSession(false).setAttribute("notice", "Your settings have been successfully updated.");
		resp.sendRedirect(req.getContextPath() + UrlUtils.ROOT_PATH + "/");
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

		userRepository.updateUserEditAdmin(first_name, last_name, preferred_name, admin, disabled, userid);
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
