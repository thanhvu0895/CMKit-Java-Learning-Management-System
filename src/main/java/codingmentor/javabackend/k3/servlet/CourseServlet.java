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
import codingmentor.javabackend.k3.model.Assignment;
import codingmentor.javabackend.k3.model.Course;
import codingmentor.javabackend.k3.model.Department;
import codingmentor.javabackend.k3.repository.AssignmentRepository;
import codingmentor.javabackend.k3.repository.CourseRepository;
import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.RepoRepository;
import codingmentor.javabackend.k3.repository.Impl.AssignmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.CourseRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.RepoRepositoryImpl;


@WebServlet(urlPatterns = {
		UrlUtils.COURSES_PATH_ALL,
		UrlUtils.NEW_COURSE_PATH,
})
public class CourseServlet extends HttpServlet{
	private static final long serialVersionUID = 1515497142397284883L;
	private RepoRepository repoRepository = null;
	private CourseRepository courseRepository = null;
	private DepartmentRepository departmentRepository = null;
	private AssignmentRepository assignmentRepository = null;
	@Override
	public void init() throws ServletException {
		super.init();
		repoRepository = RepoRepositoryImpl.getInstance();
		courseRepository = CourseRepositoryImpl.getInstance();
		departmentRepository = DepartmentRepositoryImpl.getInstance();
		assignmentRepository = AssignmentRepositoryImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.COURSES_PATH:
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return; 
			}
			
			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;

			if (pathInfoLength == 2 && UrlUtils.isInteger(pathParts[1])) { 
				int courseId = Integer.parseInt(pathParts[1]); 
				getCourseShow(req, resp, courseId);
				break;
			}
			
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1])) { 
				int courseId = Integer.parseInt(pathParts[1]);
				switch (pathParts[2]) {
				case "edit":
					getCourseEdit(req, resp, courseId);
					break;
				case "files":
					break;
				case "grade_categories":
					break;
				}
			}
			break;
		case UrlUtils.NEW_COURSE_PATH:
			getCourseNew(req, resp);
			break;
		}
	}
	
	
	private void getCourseShow(HttpServletRequest req, HttpServletResponse resp, int courseId) throws ServletException, IOException {
		try {
			Course course = courseRepository.getCourseById(courseId);
			Department department = departmentRepository.getDepartmentById(course.getDepartment_id());
			List<Assignment> assignments = assignmentRepository.getAssignmentsByCourseId(courseId);
			req.setAttribute("course", course);
			req.setAttribute("department", department);
			req.setAttribute("assignments", assignments);
			req.getRequestDispatcher(JspUtils.COURSES_SHOW)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getCourseEdit(HttpServletRequest req, HttpServletResponse resp, int courseId) throws ServletException, IOException {
		try {
			
			Department department = departmentRepository.getDepartmentByCourseId(courseId);
			Course course = courseRepository.getCourseById(courseId);
			req.setAttribute("department", department);
			req.setAttribute("course", course);
			req.getRequestDispatcher(JspUtils.COURSES_EDIT)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getCourseNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String departmentIdString = req.getParameter("department");
			if (UrlUtils.isInteger(departmentIdString)) {
				int departmentId = Integer.parseInt(departmentIdString);
				Department department = departmentRepository.getDepartmentById(departmentId);
				if (department != null) {
					req.setAttribute("department", department);
					req.getRequestDispatcher(JspUtils.COURSES_NEW)
						.forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.COURSES_PATH:
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) { // if request is /users/ or /users
				postCourseCreate(req, resp);
				return;
			}

			
			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;
			
			if (pathInfoLength == 2 && UrlUtils.isInteger(pathParts[1])) {
				int courseId = Integer.parseInt(pathParts[1]);
				switch (req.getParameter("method")) {
				case "PATCH":
					patchCourseUpdate(req, resp, courseId);
					break;
				case "PUT":
					putCourseUpdate(req, resp, courseId);
					break;
				case "DELETE":
					deleteCourseDestroy(req, resp, courseId);
					break;
				}
			}
		}	
	}
	
	private void postCourseCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String departmentIdString = req.getParameter("department");
			int departmentId = Integer.parseInt(departmentIdString);
			String courseTitle = req.getParameter("course[title]");
			String courseCode = req.getParameter("course[course_code]");
			boolean courseActive = (req.getParameterValues("course[active]").length == 2);
			
			if (courseTitle == "" || courseCode == "") {
				req.getSession(false).setAttribute("alert", "Title and course code can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_COURSE_PATH);
				return;
			}
			
			int repoId = repoRepository.insertRepo();
			if (repoId != 0) {
				int courseId = courseRepository.insertCourse(courseTitle, courseCode, repoId, departmentId, courseActive);
				req.getSession(false).setAttribute("department", departmentRepository.getDepartmentById(departmentId));
				req.getSession(false).setAttribute("course", courseRepository.getCourseById(courseId));
				req.getSession(false).setAttribute("notice", "Course was successfully created.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.COURSES_PATH +"/:id", courseId));
				return;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private void patchCourseUpdate(HttpServletRequest req, HttpServletResponse resp, int courseId) throws IOException {
		try {
			String title = req.getParameter("course[title]");
			String course_code = req.getParameter("course[course_code]");
			boolean active = (req.getParameterValues("course[active]").length == 2);
			if (title == "" || course_code == "") {
				req.getSession(false).setAttribute("alert", "Title and course code can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_COURSE_PATH, courseId));
				return;
			}
			courseRepository.updateCourseById(title, course_code, active, courseId);
			Department department = departmentRepository.getDepartmentByCourseId(courseId);
			Course course = courseRepository.getCourseById(courseId);
			req.getSession(false).setAttribute("department", department);
			req.getSession(false).setAttribute("course", course);
			req.getSession(false).setAttribute("notice", "Course was successfully updated.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_COURSE_PATH, courseId));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void putCourseUpdate(HttpServletRequest req, HttpServletResponse resp, int courseId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteCourseDestroy(HttpServletRequest req, HttpServletResponse resp, int courseId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}