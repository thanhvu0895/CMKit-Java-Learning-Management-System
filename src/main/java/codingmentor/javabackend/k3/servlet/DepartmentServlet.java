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
import codingmentor.javabackend.k3.model.Course;
import codingmentor.javabackend.k3.model.Department;
import codingmentor.javabackend.k3.repository.CourseRepository;
import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.RepoRepository;
import codingmentor.javabackend.k3.repository.Impl.CourseRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.RepoRepositoryImpl;


@WebServlet(urlPatterns = {
		UrlUtils.DEPARTMENTS_PATH,
		UrlUtils.NEW_DEPARTMENT_PATH,
		UrlUtils.DEPARTMENTS_ALL_PATH
})
public class DepartmentServlet extends HttpServlet{
	private static final long serialVersionUID = 1515497142397284883L;
	private DepartmentRepository departmentRepository = null;
	private RepoRepository repoRepository = null;
	private CourseRepository courseRepository = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		departmentRepository = DepartmentRepositoryImpl.getInstance();
		repoRepository = RepoRepositoryImpl.getInstance();
		courseRepository = CourseRepositoryImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.DEPARTMENTS_PATH:
			String pathInfo = req.getPathInfo();
			if (pathInfo == null || pathInfo.equals("/")) { 		
				getDepartmentsIndex(req, resp);
				return;
			}
			
			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1])) { 
				int departmentId = Integer.parseInt(pathParts[1]);
				switch (pathParts[2]) {
				case "edit":
					getDepartmentEdit(req, resp, departmentId);			
					break;
				case "courses":
					getDepartmentCourses(req, resp, departmentId);
					break;
				case "files":
					getDepartmentFiles(req, resp, departmentId);
					break;
				case "klasses":
					getDepartmentKlasses(req, resp, departmentId);
					break;
				}
			}
		case UrlUtils.NEW_DEPARTMENT_PATH: 					
			getDepartmentsNew(req, resp);
			break;
		}	
	}
	
	private void getDepartmentsIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			List<Department> departments = departmentRepository.getDepartments();
			req.setAttribute("departments", departments);
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDepartmentCourses(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws ServletException, IOException {
		try {
			Department department = departmentRepository.getDepartmentById(departmentId);
			List<Course> courses = courseRepository.getCourseByDepartmentId(departmentId);
			req.setAttribute("department", department);
			req.setAttribute("courses", courses);
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_COURSES)
				.forward(req, resp);
			
			System.out.println("Value of department id is: " + departmentId);
			System.out.println("GET DEPARTMENT BY departmentId"); // save to department
			System.out.println("Get all department_professors where department_id = 12"); // save to courses list
			System.out.println("Get all courses from courses table where department_id = 12"); // save to courses list
			System.out.println("Get department_professors where department id = 12 and user_id = 3 and admin = true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDepartmentEdit(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_EDIT)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	
	private void getDepartmentFiles(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_FILES)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDepartmentKlasses(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_KLASSES)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDepartmentsNew(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_NEW) 
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.DEPARTMENTS_PATH:
			postDepartmentsCreate(req, resp);
			break;
		}
	}
	
	private void postDepartmentsCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String title = req.getParameter("department[title]");
			if (title == "") {
				req.setAttribute("alert", "Title can't be blank");
				req.getRequestDispatcher(JspUtils.DEPARTMENTS_NEW)
					.forward(req, resp);
				return;
			}
			
			if (departmentRepository.existedByTitle(title)) {
				req.setAttribute("alert", "Department with the same title already existed");
				req.getRequestDispatcher(JspUtils.DEPARTMENTS_NEW)
					.forward(req, resp);
				return;
			}
			
			int repoId = repoRepository.insertRepo();
			
			if (repoId != -1) {
				int departmentId = departmentRepository.insertDepartment(title, repoId);
				req.getSession(false).setAttribute("notice", "Department was successfully created.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.DEPARTMENT_COURSES_PATH, departmentId));
				return;
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
