package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.Department;
import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;


@WebServlet(urlPatterns = {
		UrlUtils.DEPARTMENTS_PATH,
		UrlUtils.NEW_DEPARTMENT_PATH,
})
public class DepartmentServlet extends HttpServlet{
	private static final long serialVersionUID = 1515497142397284883L;
	private DepartmentRepository departmentRepository = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		departmentRepository = DepartmentRepositoryImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.DEPARTMENTS_PATH:
			List<Department> departments = departmentRepository.getDepartments();
			for (Department department : departments) {
				System.out.println("Value of department.title is: " + department.getTitle());
				System.out.println("Link to department: " + UrlUtils.putIdInPath(UrlUtils.DEPARTMENTS_PATH, department.getId()));
			}
			
			
			req.setAttribute("departments", departments);
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_INDEX)
				.forward(req, resp);
			break;
		case UrlUtils.NEW_DEPARTMENT_PATH:
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_NEW)
				.forward(req, resp);
			break;
		}	
	}
}
