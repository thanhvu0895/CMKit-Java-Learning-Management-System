package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
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

@WebServlet(urlPatterns = { 
		UrlUtils.USERS_PATH, 
		UrlUtils.USER_EDIT_SELF_PATH, 
		UrlUtils.NOTIFICATION_SETTINGS_PATH,
		UrlUtils.CHANGE_PASSWORD_PATH, 
		UrlUtils.USERS_ALL_PATH, 
		UrlUtils.CREATE_USER_INVITE_PATH,
		UrlUtils.SHOW_REQUEST_PASSWORD_RESET_PATH,
		UrlUtils.SHOW_USER_PASSWORD_RESET_PATH
})
public class UserServlet extends HttpServlet {
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
		case UrlUtils.SHOW_USER_PASSWORD_RESET_PATH:
			getShowUserPasswordResetPage(req, resp);
			break;
		case UrlUtils.SHOW_REQUEST_PASSWORD_RESET_PATH:
			req.getRequestDispatcher(JspUtils.PASSWORD_RESET_SHOW_PASSWORD_RESET_REQUEST)
				.forward(req, resp);
			break;
		case UrlUtils.USER_EDIT_SELF_PATH:
			req.getRequestDispatcher(JspUtils.USERS_EDIT_SELF).forward(req, resp);
			break;
		case UrlUtils.NOTIFICATION_SETTINGS_PATH:
			req.getRequestDispatcher(JspUtils.USERS_NOTIFICATION_SETTINGS)
				.forward(req, resp);
			break;
		case UrlUtils.CHANGE_PASSWORD_PATH:
			req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD).forward(req, resp);
			break;
		case UrlUtils.USERS_PATH:
			String pathInfo = req.getPathInfo();
			if (pathInfo == null || pathInfo.equals("/")) { // If Request is /users/ or /users
				getUserIndex(req, resp);
				return;
			}
			
			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;
			
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1])) { // If request pattern is /users/:id/*
				int id = Integer.parseInt(pathParts[1]);
				switch (pathParts[2]) {
				case "edit_admin": // If request is /users/:id/edit_admin
					User user = userRepository.findUserById(id);
					req.setAttribute("user", user.filterParams());
					req.getRequestDispatcher(JspUtils.USERS_EDIT_ADMIN).forward(req, resp);
					break;
				case "set_up": // If request is /users/:id/set_up
					getSetUpUserPage(req, resp, id);
					break;
				}
				return;
			}
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}

	private void getUserIndex (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<User> users = userRepository.getUsers();
			for (User user : users) {
				user.filterParams();
			}
			req.setAttribute("users", users);
			req.getRequestDispatcher(JspUtils.USERS_INDEX).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getShowUserPasswordResetPage (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String token = req.getParameter("token");
			String userid = req.getParameter("user");
			if (userid == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			if (UrlUtils.isInteger(userid)) {
				int id = Integer.parseInt(userid);
				User requestedUser = userRepository.findUserById(id);
				if (requestedUser != null && requestedUser.validateResetToken(token)) {
					req.setAttribute("token", token);
					req.setAttribute("userid", id);
					req.getRequestDispatcher(JspUtils.PASSWORD_RESET_SHOW_USE_PASSWORD_RESET)
						.forward(req, resp);
					
				}
				return;
			}
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
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
				req.setAttribute("user", user.filterParams());
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
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlUtils.SHOW_USER_PASSWORD_RESET_PATH:
			postUserPasswordReset(req, resp);
			break;
		case UrlUtils.SHOW_REQUEST_PASSWORD_RESET_PATH:
			postRequestPaswordReset(req, resp);
			break;
		case UrlUtils.CHANGE_PASSWORD_PATH:
			postChangePassword(req, resp);
			break;
		case UrlUtils.USER_EDIT_SELF_PATH:
			postUpdateUserPreferredName(req, resp);
			break;
		case UrlUtils.CREATE_USER_INVITE_PATH:		
			postSendInvite(req, resp);
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
					postDeleteUser(req, resp, userid);
					break;
				case "edit_admin":
					postEditAdmin(req, resp, userid);
					break;
				}
			}
			
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1])) { // If request pattern is PATH: /users/:id/*
				int id = Integer.parseInt(pathParts[1]);
				switch (pathParts[2]) {
				case "accept_invite": // FROM_PATH: SHOW_USER_INVITE_PATH | TO_PATH: ACCEPT_USER_INVITE_PATH  |  JSP: USERS_SHOW_INVITE
					postSetupUser(req, resp, id);
					break;
				case "resend_invite":
					postResendInvite(req, resp, id);
					break;
				}
			}
		}
	}
	
	/**
	 * postUserPasswordReset
	 */
	private void postUserPasswordReset(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String new_password = req.getParameter("new_password");
			String new_password_confirmation = req.getParameter("new_password_confirmation");
			String token = req.getParameter("token");
			String userid = req.getParameter("user_id");
			
			User user = userRepository.findUserById(Integer.parseInt(userid));
			
			String baseUrl = req.getRequestURL().substring(0, req.getRequestURL().length() - req.getRequestURI().length()) + req.getContextPath();
			String currentPath = baseUrl + UrlUtils.SHOW_USER_PASSWORD_RESET_PATH + "?token=" 
					+ token
					+  "&user=" + userid;
			
			if (new_password == "" || new_password_confirmation == "") {
				req.getSession(false).setAttribute("alert", "New Password cannot be empty");
				resp.sendRedirect(currentPath);
				return;
			}
			
			if (!new_password.equals(new_password_confirmation)) {
				req.getSession(false).setAttribute("alert", "New password and confirmation must match.");
				resp.sendRedirect(currentPath);
				return;
			}
			
			if (user != null) {
				userRepository.updatePassword(new_password_confirmation, user);
				req.getSession(false).setAttribute("notice", "Password reset.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.LOGIN_PATH);
				return;
			}
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * postRequestPaswordReset
	 */
	
	private void postRequestPaswordReset(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String email = req.getParameter("email").toLowerCase();
			if (email == "") {
				req.setAttribute("alert", "Email cannot be empty.");
				req.getRequestDispatcher(JspUtils.PASSWORD_RESET_SHOW_PASSWORD_RESET_REQUEST)
				.forward(req, resp);
				return;
			}
			
			String token = RandomUtils.unique64();
			User user = userRepository.findUserByEmail(email);
			
			if (user != null && user.isDeleted()) {
				req.setAttribute("alert", "This account has been deleted. Please contact your administrator to regain access to your account with all of its data intact.");
				req.getRequestDispatcher(JspUtils.PASSWORD_RESET_SHOW_PASSWORD_RESET_REQUEST)
					.forward(req, resp);
				return;
			}
			
			if (user != null && !user.isDeleted() && user.isSet_up()) {
				int id = user.getId();
				userRepository.updateResetDigest(id, RandomUtils.SHA256Base64(token));
				userRepository.updateResetExpires(id, LocalDateTime.now().plusHours(1));
				AccountsMailer.passwordResetEmail(req, user, token);
			}
			
			req.getSession(false).setAttribute("notice", "If a valid email has been supplied, an email with further instructions will be sent shortly.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.LOGIN_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	
	private void postResendInvite(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
		try {
			String token = RandomUtils.unique64();
			User user = userRepository.findUserById(id);
			userRepository.updateResetDigest(id, RandomUtils.SHA256Base64(token));
			AccountsMailer.inviteUserEmail(req, user, token);
			resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
			req.getSession(false).setAttribute("notice", "Invitation email resent.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Implement processCreateUserInvite 1/16/2023
	 */
	private void postSendInvite(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			String token = RandomUtils.unique64();
			String email = req.getParameter("email");
			User user = userRepository.findUserByEmail(email);
			boolean admin = (req.getParameterValues("admin").length == 2);
			if (email == "") {
				req.getSession().setAttribute("alert", "User not invited: Email can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
				return;
			}
			
			if (user == null) {
				userRepository.createUserSendInvite(email, admin);
				user =  userRepository.findUserByEmail(email);
				int new_user_id = user.getId();
				userRepository.updateResetDigest(new_user_id, RandomUtils.SHA256Base64(token));
				boolean updateResetDigest = userRepository.updateResetDigest(user.getId(), RandomUtils.SHA256Base64(token));
				if (updateResetDigest) {
					AccountsMailer.inviteUserEmail(req, user, token);
					req.getSession(false).setAttribute("notice", "User invited");
					resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
				}
				return;		
			}
			
			if (user != null && !user.isDeleted()) {
				req.getSession().setAttribute("alert", "User not invited: Email has already been taken");
				resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
				return;
			} 
			

			
			if (user.isDeleted()) {
				userRepository.recoverUser(user.getId());
				userRepository.updateResetDigest(user.getId(), RandomUtils.SHA256Base64(token));
				AccountsMailer.inviteUserEmail(req, user, token);
				req.getSession(false).setAttribute("notice", "User had been removed previously, so we restored user and made a new invitation request.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
				return;
			}
			
			userRepository.updateResetDigest(user.getId(), RandomUtils.SHA256Base64(token));
			AccountsMailer.inviteUserEmail(req, user, token);
			req.getSession(false).setAttribute("notice", "User invited");
			resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	private void postSetupUser(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
		try {
			String token = req.getParameter("user[token]");
			String password = req.getParameter("user[password]");
			String password_confirmation = req.getParameter("user[password_confirmation]");
			String preferred_name = req.getParameter("user[preferred_name]");
			String first_name = req.getParameter("user[first_name]");
			String last_name = req.getParameter("user[last_name]");
			
			
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	/***
	 * Implement processDeletePassword method Date: 1/14/2023
	 */
	private void postDeleteUser(HttpServletRequest req, HttpServletResponse resp, int userid)
			throws IOException, ServletException {
		try {
			User current_user = (User) req.getSession(false).getAttribute("current_user");
			int current_user_id = current_user.getId();
			if (current_user_id == userid) {
				req.getSession(false).setAttribute("alert", "Admin cannot remove themselves!.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
				return;
			}
			userRepository.deleteUser(userid);
			req.getSession(false).setAttribute("notice", "User was successfully deleted.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Implement processChangePassword method Date: 1/14/2023
	 */
	private void postChangePassword(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		try {
			User current_user = (User) req.getSession(false).getAttribute("current_user");
			String email = current_user.getEmail();
			current_user = userRepository.findUserByEmail(email);
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
			
			if (new_password.equals(old_password)) {
				req.setAttribute("alert", "New password and old password cannot be the same.");
				req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD).forward(req, resp);
				return;
			}
			
			if (!new_password.equals(new_password_confirmation)) {
				req.setAttribute("alert", "New password and confirmation must match.");
				req.getRequestDispatcher(JspUtils.USERS_CHANGE_PASSWORD).forward(req, resp);
				return;
			}

			userRepository.updatePassword(new_password_confirmation, current_user);
			HttpSession session = req.getSession();
			session.setAttribute("notice", "Password changed.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.ROOT_PATH + "/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Implement updateUserPreferredName method Date: 1/14/2023
	 */
	private void postUpdateUserPreferredName(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		try {
			String preferred_name = req.getParameter("user[preferred_name]");
			
			User current_user = (User) req.getSession(false).getAttribute("current_user");
			int userid = current_user.getId();
	
			userRepository.updatePreferredNameById(preferred_name, userid);
	
			current_user = userRepository.findUserById(userid);
			req.getSession(false).setAttribute("current_user", current_user.filterParams());
			
			if (current_user.isAdmin()) {
				req.getSession(false).setAttribute("notice", "User was successfully updated.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);
				return;
			} 
			
			req.getSession(false).setAttribute("notice", "Your settings have been successfully updated.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.ROOT_PATH + "/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Implement processEditAdmin method Date: 1/14/2023
	 */
	private void postEditAdmin(HttpServletRequest req, HttpServletResponse resp, int userid)
			throws IOException, ServletException {
		try {	
			User user = userRepository.findUserById(userid);
			
			if (user == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return; 
			}
			
			String first_name = req.getParameter("user[first_name]");
			String last_name = req.getParameter("user[last_name]");
			String preferred_name = req.getParameter("user[preferred_name]");
			boolean admin = (req.getParameterValues("user[admin]").length == 2);
			boolean disabled = (req.getParameterValues("user[disabled]").length == 2);

			if (first_name == "" || last_name == "") {
				req.setAttribute("alert", "First name and last name must not be blank");
				req.setAttribute("user", user.filterParams());
				req.getRequestDispatcher(JspUtils.USERS_EDIT_ADMIN).forward(req, resp);
				return;
			}

			userRepository.updateUserEditAdmin(first_name, last_name, preferred_name, admin, disabled, userid);
			req.getSession(false).setAttribute("notice", "User was successfully updated.");

			User current_user = (User) req.getSession(false).getAttribute("current_user");
			int current_user_id = current_user.getId();
			if (current_user_id == userid) {
				current_user = userRepository.findUserById(current_user_id);
				req.getSession(false).setAttribute("current_user", current_user.filterParams());
			}

			resp.sendRedirect(req.getContextPath() + UrlUtils.USERS_PATH);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
