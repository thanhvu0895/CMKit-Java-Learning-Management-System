package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.service.UserService;
import codingmentor.javabackend.k3.service.Impl.UserServiceImpl;

@WebServlet(urlPatterns = {
		"/link",
		"/form"
	})
public class LinkToServlet extends HttpServlet{
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
		case "/link":
			List<User> users = userService.getUsers();
			req.setAttribute("users", users);
			req.getRequestDispatcher("/link_to.jsp")
				.forward(req, resp);
			return;
		case "/form":
			User user = userService.findUserById(1);
			req.setAttribute("user", user);
			req.getRequestDispatcher("/form.jsp")
			.forward(req, resp);
			return;
		}
	}
}
