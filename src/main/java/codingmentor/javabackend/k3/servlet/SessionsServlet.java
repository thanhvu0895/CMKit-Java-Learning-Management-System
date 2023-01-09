package codingmentor.javabackend.k3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.DAO.LoginDAOImpl;
import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;


@WebServlet(name = "authServlet", urlPatterns = { 
	UrlUtils.SIGN_IN, 
	UrlUtils.SIGN_OUT, 
	"/enable_file_viewer",
	"/disable_file_viewer" 
})
public class SessionsServlet extends HttpServlet {

	private static final long serialVersionUID = -3801412244941307670L;
	private LoginDAOImpl loginDAOImpl = null;

	public SessionsServlet() {
		loginDAOImpl = new LoginDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlUtils.SIGN_IN:
			req.getRequestDispatcher(JspUtils.SESSIONS_NEW)
				.forward(req, resp);
			break;
		case UrlUtils.SIGN_OUT:
			processLogout(req, resp);
			break;
		case "/file_viewer_settings":
			req.getRequestDispatcher(JspUtils.SESSIONS_FILE_VIEWER_SETTINGS).forward(req, resp);
			break;
		default:
			resp.sendRedirect(req.getContextPath() + UrlUtils.SIGN_IN);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlUtils.SIGN_IN:
			processLogin(req, resp);
			break;
		case "/enable_file_viewer":
			req.getRequestDispatcher(JspUtils.SESSIONS_FILE_VIEWER_SETTINGS)
				.forward(req, resp);
			break;
		case "/disable_file_viewer":
			req.getRequestDispatcher(JspUtils.SESSIONS_FILE_VIEWER_SETTINGS)
				.forward(req, resp);
			break;
		}
	}

	/***
	 * Implement processLogin method Date: 31/12/2022
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		User loginDAO = new User(email, password);

		try {
			if (loginDAOImpl.validate(loginDAO)) {
				HttpSession session = req.getSession();
				session.setAttribute("current_user", loginDAOImpl.getFirstNameFromUser(loginDAO));

				/**
				 * Test sending Welcome message to Home Comment out
				 * session.setAttribute("notice", ""); In home.jsp, comment out Result: Should
				 * show Welcome message + name Step 1: input login user name + pw Step 2: Look
				 * at home to see "Welcome John"
				 */

				session.setAttribute("notice",
						"Logged in! Welcome, " + loginDAOImpl.getFirstNameFromUser(loginDAO) + "!");
				resp.sendRedirect("./");
			} else {
				req.setAttribute("alert", "Invalid email or password, please try again.");
				req.getRequestDispatcher(JspUtils.SESSIONS_NEW).forward(req, resp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void processLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		session = req.getSession(true);

		// TODO: IMPROVE LOG OUT USING SESSION OR COOKIES TO DISPLAY MESSAGE
		session.setAttribute("notice", "You are now logged out. Have a nice day!");
		resp.sendRedirect(req.getContextPath() + UrlUtils.SIGN_IN);
	}
}
