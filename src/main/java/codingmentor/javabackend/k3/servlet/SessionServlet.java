package codingmentor.javabackend.k3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.UserRepository;
import codingmentor.javabackend.k3.repository.Impl.UserRepositoryImpl;
import codingmentor.javabackend.k3.service.UserService;
import codingmentor.javabackend.k3.service.Impl.UserServiceImpl;
import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.PBKDF2Hasher;
import codingmentor.javabackend.k3.Utils.UrlUtils;


@WebServlet(name = "authServlet", urlPatterns = { 
	UrlUtils.LOGIN_PATH, 
	UrlUtils.LOGOUT_PATH, 
	UrlUtils.ENABLE_FILE_VIEWER_PATH,
	UrlUtils.FILE_VIEWER_SETTINGS_PATH 
})
public class SessionServlet extends HttpServlet {

	private static final long serialVersionUID = -3801412244941307670L;
	private UserService userService = null;
	private PBKDF2Hasher hasher = null;
	private UserRepository userRepository;

	@Override
	public void init() throws ServletException {
	    super.init();
		this.userRepository = UserRepositoryImpl.getInstance();
    	hasher = new PBKDF2Hasher();
	    userService = UserServiceImpl.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlUtils.LOGIN_PATH:
			req.getRequestDispatcher(JspUtils.SESSIONS_NEW)
				.forward(req, resp);
			break;
		case UrlUtils.LOGOUT_PATH:
			processLogout(req, resp);
			break;
		case UrlUtils.ENABLE_FILE_VIEWER_PATH:
			req.getRequestDispatcher(JspUtils.SESSIONS_FILE_VIEWER_SETTINGS).forward(req, resp);
			break;
		default:
			resp.sendRedirect(req.getContextPath() + UrlUtils.LOGIN_PATH);
		}
	}
	
	/***
	 * Implement processLogout method Date: 1/1/2023
	 */

	private void processLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		
		if (session != null) {
			session.invalidate();
		}
		
		session = req.getSession(true);
		
		session.setAttribute("notice", "You are now logged out. Have a nice day!");
		resp.sendRedirect(req.getContextPath() + UrlUtils.LOGIN_PATH);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlUtils.LOGIN_PATH:
			processLogin(req, resp);
			break;
		}
	}

	/***
	 * Implement processLogin method Date: 31/12/2022
	 */
	private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		User current_user = userService.validateLogin(email, password);
		
		if (current_user != null && current_user.isDisabled()) {
			//Re-render page with error
			req.setAttribute("alert", "Your account has been disabled. Please contact your system administrator.");
			req.getRequestDispatcher(JspUtils.SESSIONS_NEW).forward(req, resp);
		}
		
		// not null and password correct, log in
		if (current_user != null && current_user.isSet_up() && !current_user.isDisabled()) {
			current_user.setPassword_digest("[FILTERED]");
	    	req.getSession(false).setAttribute("current_user", current_user);
	    	req.getSession(false).setAttribute("notice",
					"Logged in! Welcome, " + current_user.getPreferred_first_name() + "!");
			resp.sendRedirect(req.getContextPath() + UrlUtils.ROOT_PATH + "/");
		} else {
			//Re-render page with error
			req.setAttribute("alert", "Invalid email or password, please try again.");
			req.getRequestDispatcher(JspUtils.SESSIONS_NEW).forward(req, resp);
		}
	}
	

}
