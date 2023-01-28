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
import codingmentor.javabackend.k3.model.GradeCategory;
import codingmentor.javabackend.k3.model.Klass;
import codingmentor.javabackend.k3.repository.AssignmentRepository;
import codingmentor.javabackend.k3.repository.CourseRepository;
import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.GradeCategoryRepository;
import codingmentor.javabackend.k3.repository.KlassRepository;
import codingmentor.javabackend.k3.repository.RepoRepository;
import codingmentor.javabackend.k3.repository.Impl.AssignmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.CourseRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.GradeCategoryRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.KlassRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.RepoRepositoryImpl;

@WebServlet(urlPatterns = {
		UrlUtils.ASSIGNMENT_ALL_PATH,
		UrlUtils.NEW_ASSIGNMENT_PATH,
		UrlUtils.SHOW_COPY_ASSIGNMENT_PATH,
})
public class AssignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AssignmentRepository assignmentRepository = null;
	private CourseRepository courseRepository = null;
	private DepartmentRepository departmentRepository = null;
	private KlassRepository klassRepository = null;
	private GradeCategoryRepository gradeCategoryRepository = null;
	private RepoRepository repoRepository = null;

	@Override
	public void init() throws ServletException {
		super.init();
		courseRepository = CourseRepositoryImpl.getInstance();
		assignmentRepository = AssignmentRepositoryImpl.getInstance();
		departmentRepository = DepartmentRepositoryImpl.getInstance();
		klassRepository = KlassRepositoryImpl.getInstance();
		gradeCategoryRepository = GradeCategoryRepositoryImpl.getInstance();
		repoRepository = RepoRepositoryImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.NEW_ASSIGNMENT_PATH:
			getAssignmentNew(req, resp);
			break;
		case UrlUtils.SHOW_COPY_ASSIGNMENT_PATH:
			getShowCopyAssignment(req, resp);
			break;
		case UrlUtils.ASSIGNMENT_PATH:
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return; 
			}
			
			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;

			if (pathInfoLength == 2 && UrlUtils.isInteger(pathParts[1])) { 
				int assignmentId = Integer.parseInt(pathParts[1]); 
				getAsssignmentShow(req, resp, assignmentId);
				break;
			}
			
			break;
		}
	}

	private void getAssignmentNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String courseIdString = req.getParameter("course");
			
			if (UrlUtils.isInteger(courseIdString)) {
				int courseId = Integer.parseInt(courseIdString);
				Course course = courseRepository.getCourseById(courseId);
				if (course != null) {
					List<Assignment> assignmentsList = assignmentRepository.getAssignmentsByCourseId(courseId);
					List<GradeCategory> gradeCategoriesList = gradeCategoryRepository.getGradeCategoriesByCourseId(courseId);
					Department department = departmentRepository.getDepartmentByCourseId(courseId);
					req.setAttribute("departmentid", department.getId());
					req.setAttribute("assignments", assignmentsList);
					req.setAttribute("course", course);
					req.setAttribute("assignment_grade_categories", gradeCategoriesList);
					req.getRequestDispatcher(JspUtils.ASSIGNMENTS_NEW)
						.forward(req, resp);
					return;
				}
			}
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getShowCopyAssignment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String courseIdString = req.getParameter("course");
			if(courseIdString != "" && UrlUtils.isInteger(courseIdString)) {
				int courseId = Integer.parseInt(courseIdString);
				Course course = courseRepository.getCourseById(courseId);
				
				if (course == null) {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
				
				Department department = departmentRepository.getDepartmentByCourseId(courseId);

				List<Assignment> assignmentsList = assignmentRepository.getAssignmentsByCourseId(courseId);
				req.setAttribute("assignments", assignmentsList);
				req.setAttribute("course", course);
				req.setAttribute("departmentid", department.getId());
				req.setAttribute("params", ":copy");
				req.getRequestDispatcher(JspUtils.ASSIGNMENTS_SHOW_COPY_ASSIGNMENT)
					.forward(req, resp);
				return;
			}
			
			String classIdString = req.getParameter("class");
			if(classIdString != "" && UrlUtils.isInteger(classIdString)) {
				int klassId = Integer.parseInt(classIdString);
				Klass klass = klassRepository.getKlassById(klassId);
				if (klass == null) {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
					
				Course course = courseRepository.getCourseByKlassId(klassId);
				Department department = departmentRepository.getDepartmentByCourseId(course.getId());
				req.setAttribute("course", course);
				req.setAttribute("department", department);
				req.setAttribute("klass", klass);
				req.getRequestDispatcher(JspUtils.ASSIGNMENTS_SHOW_COPY_ASSIGNMENT)
					.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void getAsssignmentShow(HttpServletRequest req, HttpServletResponse resp, int assignmentId) throws ServletException, IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			if (assignment == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			Course course = courseRepository.getCourseById(assignment.getCourse_id());
			Department department = departmentRepository.getDepartmentByCourseId(course.getId());
			
			req.setAttribute("course", course);
			req.setAttribute("department", department);
			req.setAttribute("assignment", assignment);
			req.getRequestDispatcher(JspUtils.ASSIGNMENTS_SHOW)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.ASSIGNMENT_PATH:
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) { // if request is /users/ or /users
				postAssignmentCreate(req, resp);
				return;
			}
		}
	}
	
	private void postAssignmentCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String title = req.getParameter("assignment[title]");
		
		String courseIdString = req.getParameter("assignment[course_id]");
		int course_id = Integer.parseInt(courseIdString);
		
		if (title == "") {
			req.getSession(false).setAttribute("alert", "Title can't be blank");
			resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_ASSIGNMENT_PATH + "?course=" + courseIdString);
			return;
		}

		String gradeCategoryIdString = req.getParameter("assignment[grade_category_id]");
		int grade_category_id = Integer.parseInt(gradeCategoryIdString);
		
		int files_repo_id = repoRepository.insertRepo();
		
		String assignmentTypeString = req.getParameter("assignment[assignment_type]");
		int assignment_type = Integer.parseInt(assignmentTypeString); 
		
		String permitted_filetypes = req.getParameter("assignment[permitted_filetypes]");
		
		String description = req.getParameter("assignment[description]");
		
		String fileOrLinkString = req.getParameter("assignment[file_or_link]");
		int file_or_link = Integer.parseInt(fileOrLinkString); 
		

		
		String fileLimitString = req.getParameter("assignment[file_limit]");
		int file_limit = Integer.parseInt(fileLimitString);
		
		int assignmentId = 0;
		
		// if assignment type is student type requiring a template repository to be created
		if (assignment_type == 1) {
			int template_repo_id = repoRepository.insertRepo();
			assignmentId = assignmentRepository.insertStudentRepoAssignment(title, course_id, grade_category_id, files_repo_id, template_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link);
			
			
			return;
		}
		
		assignmentId = assignmentRepository.insertAssignment(title, course_id, grade_category_id, files_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link);
	}
}