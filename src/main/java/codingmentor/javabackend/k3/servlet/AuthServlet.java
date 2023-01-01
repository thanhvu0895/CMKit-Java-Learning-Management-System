package codingmentor.javabackend.k3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import codingmentor.javabackend.k3.DAO.LoginDAO;
import codingmentor.javabackend.k3.DAO.LoginDAOImpl;
import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;


@WebServlet(name = "authServlet", urlPatterns = {
	UrlUtils.SIGN_IN,
	UrlUtils.SIGN_UP,
	UrlUtils.SIGN_OUT,
})
public class AuthServlet extends HttpServlet{

	private static final long serialVersionUID = -3801412244941307670L;
    private LoginDAOImpl loginDAOImpl = null;
    
    public AuthServlet() {
        loginDAOImpl = new LoginDAOImpl();
    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.SIGN_IN:
			req.getRequestDispatcher(JspUtils.SIGN_IN)
				.forward(req, resp);
			break;
		case UrlUtils.SIGN_UP:
			req.getRequestDispatcher(JspUtils.SIGN_UP)
				.forward(req,  resp);
			break;
		case UrlUtils.SIGN_OUT:
			req.getRequestDispatcher(JspUtils.SIGN_IN)
				.forward(req,  resp);
			break;
		default:
			req.getRequestDispatcher(req.getContextPath() + UrlUtils.NOT_FOUND)
            .forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.SIGN_IN:
			processLogin(req, resp);	
		}
	}
	
	private void processLogin(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        LoginDAO loginDAO = new LoginDAO(email, password);
        
        try {
            if (loginDAOImpl.validate(loginDAO)) {
                HttpSession session = req.getSession();
                 session.setAttribute("email",email);
                resp.sendRedirect(req.getContextPath() + UrlUtils.HOME);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("email", email);
                resp.sendRedirect(req.getContextPath() + UrlUtils.SIGN_IN);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
}
