package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import codingmentor.javabackend.k3.model.DepartmentProfessor;
import codingmentor.javabackend.k3.model.Klass;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.CourseRepository;
import codingmentor.javabackend.k3.repository.DepartmentProfessorRepository;
import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.KlassRepository;
import codingmentor.javabackend.k3.repository.RepoRepository;
import codingmentor.javabackend.k3.repository.UserRepository;
import codingmentor.javabackend.k3.repository.Impl.CourseRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.DepartmentProfessorRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.KlassRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.RepoRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.UserRepositoryImpl;


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
	private UserRepository userRepository = null;
	private DepartmentProfessorRepository departmentProfessorRepository = null;
	private KlassRepository klassRepository = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		departmentRepository = DepartmentRepositoryImpl.getInstance();
		repoRepository = RepoRepositoryImpl.getInstance();
		courseRepository = CourseRepositoryImpl.getInstance();
		departmentProfessorRepository = DepartmentProfessorRepositoryImpl.getInstance();
		userRepository = UserRepositoryImpl.getInstance();
		klassRepository = KlassRepositoryImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.DEPARTMENTS_PATH:
			System.out.println("Value of req.getServletPath() is: " + req.getServletPath());
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
			break;
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
//			System.out.println("Value of department id is: " + departmentId);
//			System.out.println("GET DEPARTMENT BY departmentId"); // save to department
//			System.out.println("Get all department_professors where department_id = 12"); // save to courses list
//			System.out.println("Get all courses from courses table where department_id = 12"); // save to courses list
//			System.out.println("Get department_professors where department id = 12 and user_id = 3 and admin = true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDepartmentEdit(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws ServletException, IOException {
		try {
			Department department = departmentRepository.getDepartmentById(departmentId);
			List<DepartmentProfessor> departmentProfessors = departmentProfessorRepository.getDepartmentProfessorsByDepartmentId(departmentId);
			ArrayList<String> userIds = new ArrayList<String>();
			
			for (DepartmentProfessor dp : departmentProfessors) {
				userIds.add(String.valueOf(dp.getUser_id()));
			}
			
			List<User> departmentProfessorUsers = userRepository.getUserFromIdList(userIds);
			
			for (User user : departmentProfessorUsers) {
				user.setPassword_digest("FILTERED");
			}
			
			req.setAttribute("department", department);	
			req.setAttribute("department_professors", departmentProfessors);
			req.setAttribute("department_professor_users", departmentProfessorUsers);
			
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_EDIT)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	
	private void getDepartmentFiles(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws ServletException, IOException {
		try {
			Department department = departmentRepository.getDepartmentById(departmentId);
			req.setAttribute("department", department);	
			req.getRequestDispatcher(JspUtils.DEPARTMENTS_FILES)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getDepartmentKlasses(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws ServletException, IOException {
		try {
			Department department = departmentRepository.getDepartmentById(departmentId);
			List<Course> coursesByDepartments = courseRepository.getCourseByDepartmentId(departmentId);
			ArrayList<String> courseIds = new ArrayList<String>();
			
			for (Course c : coursesByDepartments) {
				courseIds.add(String.valueOf(c.getId()));
			}
			
			List<Klass> courseKlasses = klassRepository.getKlassFromCourseIdList(courseIds);
			req.setAttribute("department", department);
			req.setAttribute("courses", coursesByDepartments);	
			req.setAttribute("klasses", courseKlasses);
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
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) { // if request is /users/ or /users
				postDepartmentsCreate(req, resp);
				break;
			}

			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;
			
			if (pathInfoLength == 2 && UrlUtils.isInteger(pathParts[1])) {
				int departmentId = Integer.parseInt(pathParts[1]);
				switch (req.getParameter("method")) {
				case "PATCH":
					patchDepartmentUpdate(req, resp, departmentId);
					break;
				case "PUT":
					putDepartmentUpdate(req, resp, departmentId);
					break;
				case "DELETE":
					deleteDepartmentDestroy(req, resp, departmentId);
					break;
				}
			}
			
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("department_professors")) {
				int departmentId = Integer.parseInt(pathParts[1]);
				postDepartmentProfessors(req, resp, departmentId);
				
				return;
				
			}
			
			
			if (pathInfoLength == 4 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("department_professors") && UrlUtils.isInteger(pathParts[3])) { 
				int departmentId = Integer.parseInt(pathParts[1]);
				int departmentProfessorId = Integer.parseInt(pathParts[3]);
				switch (req.getParameter("method")) {
				case "PATCH":
					patchDepartmentProfessorUpdate(req, resp, departmentId, departmentProfessorId);
					break;
				case "PUT":
					putDepartmentProfessorUpdate(req, resp, departmentId, departmentProfessorId);
					break;
				case "DELETE":
					deleteDepartmentProfessorDestroy(req, resp, departmentId, departmentProfessorId);
					break;
				}
			}
		}
	}
	
	private void postDepartmentsCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String title = req.getParameter("department[title]");
			if (title == "") {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_DEPARTMENT_PATH);
				return;
			}
			
			if (departmentRepository.existedByTitle(title)) {
				req.getSession(false).setAttribute("alert", "Department with the same title already existed");
				resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_DEPARTMENT_PATH);
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
	
	private void patchDepartmentUpdate(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws IOException {
		try {
			String title = req.getParameter("department[title]");
			Department department = departmentRepository.getDepartmentById(departmentId);
			List<Course> courses = courseRepository.getCourseByDepartmentId(departmentId);
			if (title == "") {
				req.getSession(false).setAttribute("department", department);
				req.getSession(false).setAttribute("courses", courses);
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_DEPARTMENT_PATH, departmentId));
				return;
			}
			departmentRepository.updateDepartmentTitleById(title, departmentId);
			req.getSession(false).setAttribute("department", department);
			req.getSession(false).setAttribute("courses", courses);
			req.getSession(false).setAttribute("notice", "Department was successfully updated.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.DEPARTMENT_COURSES_PATH, departmentId));
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void putDepartmentUpdate(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteDepartmentDestroy(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void postDepartmentProfessors(HttpServletRequest req, HttpServletResponse resp, int departmentId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void patchDepartmentProfessorUpdate(HttpServletRequest req, HttpServletResponse resp, int departmentId, int departmentProfessorId) throws IOException {
		try {
			boolean admin = (req.getParameterValues("department_professor[admin]").length == 2);
			departmentProfessorRepository.updateAdminByDepartmentProfessorId(admin, departmentProfessorId);
			Department department = departmentRepository.getDepartmentById(departmentId);
			List<DepartmentProfessor> departmentProfessors = departmentProfessorRepository.getDepartmentProfessorsByDepartmentId(departmentId);
			ArrayList<String> userIds = new ArrayList<String>();
			
			for (DepartmentProfessor dp : departmentProfessors) {
				userIds.add(String.valueOf(dp.getUser_id()));
			}
			
			List<User> departmentProfessorUsers = userRepository.getUserFromIdList(userIds);
			
			for (User user : departmentProfessorUsers) {
				user.setPassword_digest("FILTERED");
			}
		
			req.getSession(false).setAttribute("department", department);	
			req.getSession(false).setAttribute("department_professors", departmentProfessors);
			req.getSession(false).setAttribute("department_professor_users", departmentProfessorUsers);
			req.getSession(false).setAttribute("notice", "Professor updated.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_DEPARTMENT_PATH, departmentId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void putDepartmentProfessorUpdate(HttpServletRequest req, HttpServletResponse resp, int departmentId, int departmentProfessorId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteDepartmentProfessorDestroy(HttpServletRequest req, HttpServletResponse resp, int departmentId, int departmentProfessorId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
