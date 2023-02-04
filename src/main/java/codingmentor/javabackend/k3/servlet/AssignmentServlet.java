package codingmentor.javabackend.k3.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.Utils.EnumUtils;
import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.Assignment;
import codingmentor.javabackend.k3.model.Course;
import codingmentor.javabackend.k3.model.Department;
import codingmentor.javabackend.k3.model.GradeCategory;
import codingmentor.javabackend.k3.model.Klass;
import codingmentor.javabackend.k3.model.Problem;
import codingmentor.javabackend.k3.model.ReusableComment;
import codingmentor.javabackend.k3.model.RubricItem;
import codingmentor.javabackend.k3.repository.AssignmentRepository;
import codingmentor.javabackend.k3.repository.CourseRepository;
import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.GradeCategoryRepository;
import codingmentor.javabackend.k3.repository.KlassRepository;
import codingmentor.javabackend.k3.repository.ProblemRepository;
import codingmentor.javabackend.k3.repository.RepoRepository;
import codingmentor.javabackend.k3.repository.ReusableCommentRepository;
import codingmentor.javabackend.k3.repository.RubricItemRepository;
import codingmentor.javabackend.k3.repository.Impl.AssignmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.CourseRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.GradeCategoryRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.KlassRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.ProblemRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.RepoRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.ReusableCommentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.RubricItemRepositoryImpl;

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
	private ProblemRepository problemRepository = null;
	private RubricItemRepository rubricItemRepository = null;
	private ReusableCommentRepository reusableCommentRepository = null;

	@Override
	public void init() throws ServletException {
		super.init();
		courseRepository = CourseRepositoryImpl.getInstance();
		assignmentRepository = AssignmentRepositoryImpl.getInstance();
		departmentRepository = DepartmentRepositoryImpl.getInstance();
		klassRepository = KlassRepositoryImpl.getInstance();
		gradeCategoryRepository = GradeCategoryRepositoryImpl.getInstance();
		repoRepository = RepoRepositoryImpl.getInstance();
		problemRepository = ProblemRepositoryImpl.getInstance();
		rubricItemRepository = RubricItemRepositoryImpl.getInstance();
		reusableCommentRepository = ReusableCommentRepositoryImpl.getInstance();
		
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
				return;
			}
			
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1])) {				
				int assignmentId = Integer.parseInt(pathParts[1]);
				switch (pathParts[2]) {
				case "edit":
					getAssignmentEdit(req, resp, assignmentId);
					break;
				case "problems":
					getAssignmentProblemsIndex(req, resp, assignmentId);
					break;
				case "assign":
					getAssignmentAssignedsNew(req, resp, assignmentId);
					break;
				}
				return;
			}
			
			if (pathInfoLength == 4 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("problems")) {
				int assignmentId = Integer.parseInt(pathParts[1]);
				
				if (pathParts[3].equals("new")) {
					getAssignmentProblemsNew(req, resp, assignmentId);	
				}
				return;
			}
			
			
			if (pathInfoLength == 5 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("problems")) {
				int assignmentId = Integer.parseInt(pathParts[1]);
				if (UrlUtils.isInteger(pathParts[3]) && pathParts[4].equals("edit")) {
					int problemId = Integer.parseInt(pathParts[3]);
					getAssignmentProblemsEdit(req, resp, assignmentId, problemId);
				}
				return;
			}
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}

	private void getAssignmentNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String courseIdString = req.getParameter("course");
			String klasIdString = req.getParameter("class");
			
			if (klasIdString != "" && UrlUtils.isInteger(klasIdString)) {
				int klassId = Integer.parseInt(klasIdString);
				Klass klass = klassRepository.getKlassById(klassId);
				
				String copyIdString = req.getParameter("copy");
				
				if (klass == null) {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
				
				if (copyIdString != "" && UrlUtils.isInteger(copyIdString)) {
					Assignment assignment = assignmentRepository.getAssignmentById(Integer.parseInt(copyIdString));
					
					if (assignment == null) {
						resp.sendError(HttpServletResponse.SC_NOT_FOUND);
						return;
					}
					
					Course course = courseRepository.getCourseById(klass.getCourse_id());
					List<GradeCategory> gradeCategoriesList = gradeCategoryRepository.getGradeCategoriesByCourseId(klass.getCourse_id());	
					req.setAttribute("assignment_grade_categories", gradeCategoriesList);
					req.setAttribute("assignment", assignment);
					req.setAttribute("klass", klass);
					req.setAttribute("course", course);
					req.getRequestDispatcher(JspUtils.ASSIGNMENTS_NEW)
					.forward(req, resp);
					return;
				}
				
				Course course = courseRepository.getCourseByKlassId(klassId);
				List<GradeCategory> gradeCategoriesList = gradeCategoryRepository.getGradeCategoriesByCourseId(klass.getCourse_id());
				req.setAttribute("assignment_grade_categories", gradeCategoriesList);
				req.setAttribute("course", course);
				req.setAttribute("klass", klass);
				req.getRequestDispatcher(JspUtils.ASSIGNMENTS_NEW)
					.forward(req, resp);
				
				
				return;
			}
			
			
			if (courseIdString != "" && UrlUtils.isInteger(courseIdString)) {
				
				String copyIdString = req.getParameter("copy");
				
				if (copyIdString != "" && UrlUtils.isInteger(copyIdString)) {
					Assignment assignment = assignmentRepository.getAssignmentById(Integer.parseInt(copyIdString));
					
					if (assignment == null) {
						resp.sendError(HttpServletResponse.SC_NOT_FOUND);
						return;
					}
					
					int courseId = Integer.parseInt(courseIdString);
					
					Course course = courseRepository.getCourseById(courseId);
					List<GradeCategory> gradeCategoriesList = gradeCategoryRepository.getGradeCategoriesByCourseId(courseId);
					
					req.setAttribute("assignment_grade_categories", gradeCategoriesList);
					req.setAttribute("departmentid", course.getDepartment_id());
					req.setAttribute("assignment", assignment);
					req.setAttribute("course", course);
					req.getRequestDispatcher(JspUtils.ASSIGNMENTS_NEW)
					.forward(req, resp);
					return;
				}
				
				int courseId = Integer.parseInt(courseIdString);
				Course course = courseRepository.getCourseById(courseId);
				
				if (course != null) {
					List<GradeCategory> gradeCategoriesList = gradeCategoryRepository.getGradeCategoriesByCourseId(courseId);
					req.setAttribute("departmentid", course.getDepartment_id());
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
				
				req.setAttribute("course", course);
				req.setAttribute("departmentid", course.getDepartment_id());
				req.getRequestDispatcher(JspUtils.ASSIGNMENTS_SHOW_COPY_ASSIGNMENT)
					.forward(req, resp);
				return;
			}
			
			String classIdString = req.getParameter("klass");
			
			if(classIdString != "" && UrlUtils.isInteger(classIdString)) {
				int klassId = Integer.parseInt(classIdString);
				Klass klass = klassRepository.getKlassById(klassId);
				if (klass == null) {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
					
				Course course = courseRepository.getCourseByKlassId(klassId);
				req.setAttribute("course", course);
				req.setAttribute("departmentid", course.getDepartment_id());
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
			Assignment assignment = assignmentRepository.getAssignmentByIdWithTotalPoint(assignmentId);
			if (assignment == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			if (assignment.getCourse_id() > 0) { 
				Course course = courseRepository.getCourseById(assignment.getCourse_id());
				req.setAttribute("course", course);
				Department department = departmentRepository.getDepartmentByCourseId(course.getId());
				req.setAttribute("department", department);
			}
			
			if (assignment.getKlass_id() > 0) {
				Klass klass = klassRepository.getKlassById(assignment.getKlass_id());
				Course course = courseRepository.getCourseByKlassId(klass.getId());
				req.setAttribute("course", course);
				req.setAttribute("klass", klass);
			}
			
			
			GradeCategory gradeCategory = gradeCategoryRepository.getGradeCategoryByAssignmentId(assignmentId);
			req.setAttribute("assignment", assignment);
			req.setAttribute("grade_category", gradeCategory);
			req.getRequestDispatcher(JspUtils.ASSIGNMENTS_SHOW)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getAssignmentEdit(HttpServletRequest req, HttpServletResponse resp, int assignmentId) throws ServletException, IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			if (assignment == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			List<GradeCategory> gradeCategoriesList = null;
			if (assignment.getCourse_id() > 0) {
				Course course = courseRepository.getCourseById(assignment.getCourse_id());
				req.setAttribute("course", course);
				Department department = departmentRepository.getDepartmentByCourseId(course.getId());
				req.setAttribute("department", department);
				gradeCategoriesList = gradeCategoryRepository.getGradeCategoriesByCourseId(course.getId());
			}
			
			if (assignment.getKlass_id() > 0) {
				Klass klass = klassRepository.getKlassById(assignment.getKlass_id());
				Course course = courseRepository.getCourseByKlassId(klass.getId());
				gradeCategoriesList = gradeCategoryRepository.getGradeCategoriesByCourseId(klass.getCourse_id());
				req.setAttribute("course", course);
				req.setAttribute("klass", klass);
			}
			
			
			GradeCategory gradeCategory = gradeCategoryRepository.getGradeCategoryByAssignmentId(assignmentId);
			
			req.setAttribute("assignment", assignment);
			req.setAttribute("grade_category", gradeCategory);
			req.setAttribute("assignment_grade_categories", gradeCategoriesList);
			req.getRequestDispatcher(JspUtils.ASSIGNMENTS_EDIT)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getAssignmentProblemsIndex(HttpServletRequest req, HttpServletResponse resp, int assignmentId) throws ServletException, IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			
			if (assignment == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			if (assignment.getCourse_id() > 0) {
				Course course = courseRepository.getCourseById(assignment.getCourse_id());
				req.setAttribute("course", course);
				Department department = departmentRepository.getDepartmentByCourseId(course.getId());
				req.setAttribute("department", department);
			}
			
			if (assignment.getKlass_id() > 0) {
				Klass klass = klassRepository.getKlassById(assignment.getKlass_id());
				Course course = courseRepository.getCourseByKlassId(klass.getId());
				req.setAttribute("course", course);
				req.setAttribute("klass", klass);
			}
			
			List<Problem> problemsList = problemRepository.getProblemsByAssignmentIdOrderByLocationAsc(assignmentId);


			req.setAttribute("assignment", assignment);
			req.setAttribute("problems", problemsList);
			req.getRequestDispatcher(JspUtils.PROBLEMS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getAssignmentProblemsNew(HttpServletRequest req, HttpServletResponse resp, int assignmentId) throws ServletException, IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			
			if (assignment == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			if (assignment.getCourse_id() > 0) {
				Course course = courseRepository.getCourseById(assignment.getCourse_id());
				req.setAttribute("course", course);
				Department department = departmentRepository.getDepartmentByCourseId(course.getId());
				req.setAttribute("department", department);
			}
			
			if (assignment.getKlass_id() > 0) {
				Klass klass = klassRepository.getKlassById(assignment.getKlass_id());
				Course course = courseRepository.getCourseByKlassId(klass.getId());
				req.setAttribute("course", course);
				req.setAttribute("klass", klass);
			}
			
			
			req.setAttribute("assignment", assignment);
			req.getRequestDispatcher(JspUtils.PROBLEMS_NEW)
				.forward(req, resp);
			
		} catch (Exception e) {

		}
	}

	private void getAssignmentProblemsEdit(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId) throws ServletException, IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			Problem problem = problemRepository.getProblemById(problemId);
			
			if (assignment == null || problem == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			

			if (assignment.getCourse_id() > 0) {
				Course course = courseRepository.getCourseById(assignment.getCourse_id());
				req.setAttribute("course", course);
				Department department = departmentRepository.getDepartmentByCourseId(course.getId());
				req.setAttribute("department", department);
			}
			
			if (assignment.getKlass_id() > 0) {
				Klass klass = klassRepository.getKlassById(assignment.getKlass_id());
				Course course = courseRepository.getCourseByKlassId(klass.getId());
				req.setAttribute("course", course);
				req.setAttribute("klass", klass);
			}
			
	
			
			List<RubricItem> rubricItemsList = rubricItemRepository.getRubricItemsByProblemIdOrderByLocationAsc(problemId);
			List<ReusableComment> reusableComments = reusableCommentRepository.getReusableCommentsByProblemId(problemId);
			req.setAttribute("assignment", assignment);
			req.setAttribute("problem", problem);
			req.setAttribute("rubric_items", rubricItemsList);
			req.setAttribute("reusable_comments", reusableComments);
			req.getRequestDispatcher(JspUtils.PROBLEMS_EDIT)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getAssignmentAssignedsNew (HttpServletRequest req, HttpServletResponse resp, int assignmentId) throws ServletException, IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			
			if (assignment == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			String klassIdString = req.getParameter("class");
			
			if (UrlUtils.isInteger(klassIdString)) {
				
				int klassId = Integer.parseInt(klassIdString);
				
				Klass klass = klassRepository.getKlassById(klassId);
				Course course = courseRepository.getCourseByKlassId(klassId);
				
				req.setAttribute("course", course);
				req.setAttribute("klass", klass);
				req.setAttribute("assignment", assignment);
				
				req.getRequestDispatcher(JspUtils.ASSIGNED_NEW)
					.forward(req, resp);
				return;
			}

			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.ASSIGNMENT_PATH:
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) { // if request is /assignments/ or /assignments
				String copyAssignmentIdString = req.getParameter("copy");
				if (copyAssignmentIdString != "" && UrlUtils.isInteger(copyAssignmentIdString)) {
					int copyAssignmentId = Integer.parseInt(copyAssignmentIdString);
					postAssignmentCopy(req, resp, copyAssignmentId);
					return;
				}
				
				postAssignmentCreate(req, resp);
				return;
			}
			
			
			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;
			
			if (pathInfoLength == 2 && UrlUtils.isInteger(pathParts[1])) {
				int assignmentId = Integer.parseInt(pathParts[1]);
				switch (req.getParameter("method")) {
				case "PATCH":
					patchAssignmentUpdate(req, resp, assignmentId);
					break;
				case "DELETE":
					deleteAssignmentDestroy(req, resp, assignmentId);
					break;
				}
			}
			
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("problems")) {
				int assignmentId = Integer.parseInt(pathParts[1]);
				postAssignmentProblemCreate(req, resp, assignmentId);
				return;
				
			}
			
			if (pathInfoLength == 4 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("problems") && UrlUtils.isInteger(pathParts[3])) { 
				int assignmentId = Integer.parseInt(pathParts[1]);
				int problemId = Integer.parseInt(pathParts[3]);
				switch (req.getParameter("method")) {
				case "PATCH":
					patchAssignmentProblemUpdate(req, resp, assignmentId, problemId);
					break;
				case "DELETE":
					deleteAssignmentProblemDestroy(req, resp, assignmentId, problemId);
					break;
				}
				return;
			}
			
			if (pathInfoLength == 5 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("problems") && UrlUtils.isInteger(pathParts[3])) {
				int assignmentId = Integer.parseInt(pathParts[1]);
				int problemId = Integer.parseInt(pathParts[3]);
				switch (pathParts[4]) {
				case "move_up":
					patchAssignmentProblemMoveUp(req, resp, assignmentId, problemId);
					break;
				case "move_down":
					patchAssignmentProblemMoveDown(req, resp, assignmentId, problemId);
					break;
				case "rubric_items":
					postAssignmentProblemRubricItemCreate(req, resp, assignmentId, problemId);
					break;
				case "reusable_comments":
					postAssignmentProblemReusableCommentCreate(req, resp, assignmentId, problemId);
				}
				return;
			}
			
			if (pathInfoLength == 6 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("problems") && UrlUtils.isInteger(pathParts[3] ) && pathParts[4].equals("rubric_items") && UrlUtils.isInteger(pathParts[5])) {
				int assignmentId = Integer.parseInt(pathParts[1]);
				int problemId = Integer.parseInt(pathParts[3]);
				int rubricItemId = Integer.parseInt(pathParts[5]);
				
				switch (req.getParameter("method")) {
				case "PATCH":
					patchAssignmentProblemRubricItemUpdate(req, resp, assignmentId, problemId, rubricItemId);
					break;
				case "DELETE":
					deleteAssignmentProblemRubricItemDestroy(req, resp, assignmentId, problemId, rubricItemId);
					break;
				}
				return;
			}
			
			if (pathInfoLength == 7 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("problems") && UrlUtils.isInteger(pathParts[3]) && pathParts[4].equals("rubric_items") && UrlUtils.isInteger(pathParts[5])) {
				int assignmentId = Integer.parseInt(pathParts[1]);
				int problemId = Integer.parseInt(pathParts[3]);
				int rubricItemId = Integer.parseInt(pathParts[5]);
				switch (pathParts[6]) {
				case "move_up":
					patchAssignmentProblemRubricItemMoveUp(req, resp, assignmentId, problemId, rubricItemId);
					break;
				case "move_down":
					patchAssignmentProblemRubricItemMoveDown(req, resp, assignmentId, problemId, rubricItemId);
					break;
				}
			}
			return;
		}
	}
	
	private void postAssignmentCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String title = req.getParameter("assignment[title]");
		String courseIdString = req.getParameter("assignment[course_id]");
		String klassIdString = req.getParameter("assignment[klass_id]");	
		
		int course_id = Integer.parseInt(courseIdString);
		
		if (klassIdString != null) {
			
			int klass_id = Integer.parseInt(klassIdString);
			
			if (title == "") {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_ASSIGNMENT_PATH + "?class=" + klassIdString);
				return;
			}
			
			Integer grade_category_id = null;
			String gradeCategoryIdString = req.getParameter("assignment[grade_category_id]");
			if (gradeCategoryIdString != null) {
				grade_category_id = Integer.parseInt(gradeCategoryIdString);
			}
			
			int files_repo_id = repoRepository.insertRepo();
			
			String assignmentTypeString = req.getParameter("assignment[assignment_type]");
			int assignment_type = EnumUtils.assignment_typeEnum.valueOf(assignmentTypeString).ordinal(); 
			
			String permitted_filetypes = req.getParameter("assignment[permitted_filetypes]");
			
			String description = req.getParameter("assignment[description]");
			
			String fileOrLinkString = req.getParameter("assignment[file_or_link]");
			int file_or_link = EnumUtils.file_or_linkEnum.valueOf(fileOrLinkString).ordinal();
			
			String fileLimitString = req.getParameter("assignment[file_limit]");
			
			if (fileLimitString == "") {
				req.getSession(false).setAttribute("alert", "File limit can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_ASSIGNMENT_PATH + "?class=" + klassIdString);
				return;
			}
			
			int file_limit = Integer.parseInt(fileLimitString);
			
			int assignmentId = 0;
			
			// if assignment type is student type requiring a template repository to be created
			if (assignment_type == 0) {
				int template_repo_id = repoRepository.insertRepo();
				assignmentId = assignmentRepository.insertStudentRepoAssignmentKlass(title, klass_id, grade_category_id, files_repo_id, template_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link);				
				req.getSession(false).setAttribute("notice", "Assignment was successfully created.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.ASSIGNMENT_PATH + "/" + assignmentId);
				return;
			}
			
			assignmentId = assignmentRepository.insertAssignmentKlass(title,klass_id , grade_category_id, files_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link);
			req.getSession(false).setAttribute("notice", "Assignment was successfully created.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.ASSIGNMENT_PATH + "/" + assignmentId);
			return;
		}
		
		
		
		if (title == "") {
			req.getSession(false).setAttribute("alert", "Title can't be blank");
			resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_ASSIGNMENT_PATH + "?course=" + courseIdString);
			return;
		}
		
		Integer grade_category_id = null;
		String gradeCategoryIdString = req.getParameter("assignment[grade_category_id]");
		if (gradeCategoryIdString != null) {
			grade_category_id = Integer.parseInt(gradeCategoryIdString);
		}
		
		int files_repo_id = repoRepository.insertRepo();
		
		String assignmentTypeString = req.getParameter("assignment[assignment_type]");
		int assignment_type = EnumUtils.assignment_typeEnum.valueOf(assignmentTypeString).ordinal(); 
		
		String permitted_filetypes = req.getParameter("assignment[permitted_filetypes]");
		
		String description = req.getParameter("assignment[description]");
		
		String fileOrLinkString = req.getParameter("assignment[file_or_link]");
		int file_or_link = EnumUtils.file_or_linkEnum.valueOf(fileOrLinkString).ordinal();
		
		String fileLimitString = req.getParameter("assignment[file_limit]");
		
		if (fileLimitString == "") {
			req.getSession(false).setAttribute("alert", "File limit can't be blank");
			resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_ASSIGNMENT_PATH + "?course=" + courseIdString);
			return;
		}
		
		int file_limit = Integer.parseInt(fileLimitString);
		
		int assignmentId = 0;
		
		// if assignment type is student type requiring a template repository to be created
		if (assignment_type == 0) {
			int template_repo_id = repoRepository.insertRepo();
			assignmentId = assignmentRepository.insertStudentRepoAssignment(title, course_id, grade_category_id, files_repo_id, template_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link);
			req.getSession(false).setAttribute("notice", "Assignment was successfully created.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.ASSIGNMENT_PATH + "/" + assignmentId);
			return;
		}
		
		assignmentId = assignmentRepository.insertAssignment(title, course_id, grade_category_id, files_repo_id, assignment_type, permitted_filetypes, description, file_limit, file_or_link);
		req.getSession(false).setAttribute("notice", "Assignment was successfully created.");
		resp.sendRedirect(req.getContextPath() + UrlUtils.ASSIGNMENT_PATH + "/" + assignmentId);
	}
	
	
	private void postAssignmentCopy(HttpServletRequest req, HttpServletResponse resp, int copyAssignmentId) throws IOException {
		try {
			String title = req.getParameter("assignment[title]");
			String courseIdString = req.getParameter("assignment[course_id]");
			String klassIdString = req.getParameter("assignment[klass_id]");	
			String description = req.getParameter("assignment[description]");
			String gradeCategoryIdString = req.getParameter("assignment[grade_category_id]");
			
			Integer grade_category_id = null;
			
			if (gradeCategoryIdString != null) {
				grade_category_id = Integer.parseInt(gradeCategoryIdString);
			}
			
			Assignment assignment = assignmentRepository.getAssignmentById(copyAssignmentId);
			
			// IF COPYING ASSIGNMENT TO A KLASS
			if (klassIdString != "") {
				int klass_id = Integer.parseInt(klassIdString);
				
				if (title == "") {
					req.getSession(false).setAttribute("alert", "Title can't be blank");
					resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_ASSIGNMENT_PATH + "?class=" + klassIdString);
					return;
				}
				
				int files_repo_id = repoRepository.insertRepo();
							
				int assignmentId = 0;
				
				// if assignment type is student type requiring a template repository to be created
				if (assignment.getAssignment_type() == 0) {
					String permitted_filetypes = req.getParameter("assignment[permitted_filetypes]");
					
					String fileOrLinkString = req.getParameter("assignment[file_or_link]");
					int file_or_link = EnumUtils.file_or_linkEnum.valueOf(fileOrLinkString).ordinal();
		
					String fileLimitString = req.getParameter("assignment[file_limit]");
					
					if (fileLimitString == "") {
						req.getSession(false).setAttribute("alert", "File limit can't be blank");
						resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_ASSIGNMENT_PATH + "?class=" + klassIdString);
						return;
					}
					
					int file_limit = Integer.parseInt(fileLimitString);
		
					int template_repo_id = repoRepository.insertRepo();
					assignmentId = assignmentRepository.insertStudentRepoAssignmentKlass(title, klass_id, grade_category_id, files_repo_id, template_repo_id, assignment.getAssignment_type(), permitted_filetypes, description, file_limit, file_or_link);
					
					List<Problem> problemsList = problemRepository.getProblemsByAssignmentId(copyAssignmentId);
					
					for (Problem problem : problemsList) {
						int newProblemId = problemRepository.insertProblem(assignmentId, problem.getTitle(), problem.getPoints(), problem.getLocation(), problem.getGrader_notes());
			
						List<RubricItem> rubricItems = rubricItemRepository.getRubricItemsByProblemId(problem.getId());
			
						for (RubricItem r : rubricItems) {
							rubricItemRepository.insertRubricItem(newProblemId, r.getTitle(), r.getPoints(), r.getLocation());
						}
					}
					
					req.getSession(false).setAttribute("notice", "Assignment was successfully created.");
					resp.sendRedirect(req.getContextPath() + UrlUtils.ASSIGNMENT_PATH + "/" + assignmentId);
					return;
				}
				
				List<Problem> problemsList = problemRepository.getProblemsByAssignmentId(copyAssignmentId);
				
				for (Problem problem : problemsList) {
					int newProblemId = problemRepository.insertProblem(assignmentId, problem.getTitle(), problem.getPoints(), problem.getLocation(), problem.getGrader_notes());
		
					List<RubricItem> rubricItems = rubricItemRepository.getRubricItemsByProblemId(problem.getId());
		
					for (RubricItem r : rubricItems) {
						rubricItemRepository.insertRubricItem(newProblemId, r.getTitle(), r.getPoints(), r.getLocation());
					}
				}
				
				assignmentId = assignmentRepository.insertAssignmentKlass(title,klass_id , grade_category_id, files_repo_id, assignment.getAssignment_type(), assignment.getPermitted_filetypes(), description, assignment.getFile_limit(), assignment.getFile_or_link());
				req.getSession(false).setAttribute("notice", "Assignment was successfully created.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.ASSIGNMENT_PATH + "/" + assignmentId);
				return;
			}
			
			if (courseIdString != "") {
				int course_id = Integer.parseInt(courseIdString);
				
				if (title == "") {
					req.getSession(false).setAttribute("alert", "Title can't be blank");
					resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_ASSIGNMENT_PATH + "?course=" + courseIdString);
					return;
				}
				
				int files_repo_id = repoRepository.insertRepo();
					
				int assignmentId = 0;
				
				// if assignment type is student type requiring a template repository to be created
				if (assignment.getAssignment_type() == 0) {
					String permitted_filetypes = req.getParameter("assignment[permitted_filetypes]");
					
					String fileOrLinkString = req.getParameter("assignment[file_or_link]");
					int file_or_link = EnumUtils.file_or_linkEnum.valueOf(fileOrLinkString).ordinal();
					
					String fileLimitString = req.getParameter("assignment[file_limit]");
					
					if (fileLimitString == "") {
						req.getSession(false).setAttribute("alert", "File limit can't be blank");
						resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_ASSIGNMENT_PATH + "?course=" + courseIdString);
						return;
					}
					
					int file_limit = Integer.parseInt(fileLimitString);
					int template_repo_id = repoRepository.insertRepo();
					assignmentId = assignmentRepository.insertStudentRepoAssignment(title, course_id, grade_category_id, files_repo_id, template_repo_id, assignment.getAssignment_type(), permitted_filetypes, description, file_limit, file_or_link);
					
					List<Problem> problemsList = problemRepository.getProblemsByAssignmentId(copyAssignmentId);
					
					for (Problem problem : problemsList) {
						int newProblemId = problemRepository.insertProblem(assignmentId, problem.getTitle(), problem.getPoints(), problem.getLocation(), problem.getGrader_notes());
			
						List<RubricItem> rubricItems = rubricItemRepository.getRubricItemsByProblemId(problem.getId());
			
						for (RubricItem r : rubricItems) {
							rubricItemRepository.insertRubricItem(newProblemId, r.getTitle(), r.getPoints(), r.getLocation());
						}
					}
					
					req.getSession(false).setAttribute("notice", "Assignment was successfully created.");
					resp.sendRedirect(req.getContextPath() + UrlUtils.ASSIGNMENT_PATH + "/" + assignmentId);
					return;
				}
				
				
				List<Problem> problemsList = problemRepository.getProblemsByAssignmentId(copyAssignmentId);
				
				for (Problem problem : problemsList) {
					int newProblemId = problemRepository.insertProblem(assignmentId, problem.getTitle(), problem.getPoints(), problem.getLocation(), problem.getGrader_notes());
		
					List<RubricItem> rubricItems = rubricItemRepository.getRubricItemsByProblemId(problem.getId());
		
					for (RubricItem r : rubricItems) {
						rubricItemRepository.insertRubricItem(newProblemId, r.getTitle(), r.getPoints(), r.getLocation());
					}
				}
				
				assignmentId = assignmentRepository.insertAssignmentKlass(title, course_id, grade_category_id, files_repo_id, assignment.getAssignment_type(), assignment.getPermitted_filetypes(), description, assignment.getFile_limit(), assignment.getFile_or_link());
				req.getSession(false).setAttribute("notice", "Assignment was successfully created.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.ASSIGNMENT_PATH + "/" + assignmentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void patchAssignmentUpdate(HttpServletRequest req, HttpServletResponse resp, int assignmentId) throws IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			
			if (assignment == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			String title = req.getParameter("assignment[title]");
			Integer grade_category_id = null;
			String gradeCategoryIdString = req.getParameter("assignment[grade_category_id]");
			String description = req.getParameter("assignment[description]");
			
			if (title == "") {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_ASSIGNMENT_PATH, assignmentId));
				return;
			}
			
			if (gradeCategoryIdString != null) {
				grade_category_id = Integer.parseInt(gradeCategoryIdString);
			}
			
			if (assignment.getAssignment_type() == 0) {
				String permitted_filetypes = req.getParameter("assignment[permitted_filetypes]");
				String fileOrLinkString = req.getParameter("assignment[file_or_link]");
				int file_or_link = EnumUtils.file_or_linkEnum.valueOf(fileOrLinkString).ordinal();
				
				String fileLimitString = req.getParameter("assignment[file_limit]");
				
				if (fileLimitString == "") {
					req.getSession(false).setAttribute("alert", "File limit can't be blank");
					resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_ASSIGNMENT_PATH, assignmentId));
					return;
				}
				
				int file_limit = Integer.parseInt(fileLimitString);
				assignmentRepository.updateStudentFileAssignmentById(title, description, grade_category_id, file_or_link, permitted_filetypes, file_limit, assignmentId);
				req.getSession(false).setAttribute("notice", "Assignment was successfully updated.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.ASSIGNMENT_PATH  + "/:id", assignmentId));
				return;
			}
			 
			assignmentRepository.updateAssignmentById(title, description, grade_category_id, assignmentId);
			req.getSession(false).setAttribute("notice", "Assignment was successfully updated.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.ASSIGNMENT_PATH  + "/:id", assignmentId));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteAssignmentDestroy(HttpServletRequest req, HttpServletResponse resp, int klassId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void postAssignmentProblemCreate(HttpServletRequest req, HttpServletResponse resp, int assignmentId) throws IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			
			if (assignment == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			String title = req.getParameter("problem[title]");
			String pointsString = req.getParameter("problem[points]");
			String grader_notes = req.getParameter("problem[grader_notes]");
			
			if (title == "") {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putSecondInPath(UrlUtils.NEW_ASSIGNMENT_PROBLEM_PATH, assignmentId));
				return;
			}
			
			if (pointsString == "") {
				req.getSession(false).setAttribute("alert", "Points can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putSecondInPath(UrlUtils.NEW_ASSIGNMENT_PROBLEM_PATH, assignmentId));
				return;
			}
			
			if (UrlUtils.isDouble(pointsString)) {
				Double points = UrlUtils.convertDoubleFromStringWithPrecision(pointsString, 4);
				int location = problemRepository.getProblemsByAssignmentId(assignmentId).size();
				int newProblemId = problemRepository.insertProblem(assignmentId, title, points, location, grader_notes);
				rubricItemRepository.insertRubricItem(newProblemId, "Full Credit", points, 0);
				rubricItemRepository.insertRubricItem(newProblemId, "No Credit", 0, 1);
				req.getSession(false).setAttribute("notice", "Problem was successfully created. Two example options were also created. Replace these with your rubric for this problem.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), newProblemId)) ;
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void patchAssignmentProblemUpdate(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId) throws IOException {
		try {
			String title = req.getParameter("problem[title]");
			String pointsString = req.getParameter("problem[points]");
			String grader_notes = req.getParameter("problem[grader_notes]");
			
			if (title == "") {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId)) ;
				return;
			}
			
			if (pointsString == "") {
				req.getSession(false).setAttribute("alert", "Points can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId)) ;
				return;
			}
			
			if (UrlUtils.isDouble(pointsString)) {
				Double points = UrlUtils.convertDoubleFromStringWithPrecision(pointsString, 4);
				problemRepository.updateProblemById(title, points, grader_notes, problemId);
				req.getSession(false).setAttribute("notice", "Problem was successfully updated.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId)) ;
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void patchAssignmentProblemMoveUp(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId) throws IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			Problem problem = problemRepository.getProblemById(problemId);
			
			if (problem == null || assignment == null) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;	
			}
			
			int location = problem.getLocation();

			
			if (location  > 0) {
				location -= 1;
				
				//Move current location holder down
				Problem above = problemRepository.getProblemByLocationAndAssignmentId(location, assignmentId);

				// Save Problem
				
				problemRepository.updateProblemLocationById(location, problemId);
				problemRepository.updateProblemLocationById(above.getLocation() + 1, above.getId());
			}
			
			resp.sendRedirect(req.getContextPath() + UrlUtils.putSecondInPath(UrlUtils.ASSIGNMENT_PROBLEMS_PATH, assignmentId));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private void patchAssignmentProblemMoveDown(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId) throws IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			Problem problem = problemRepository.getProblemById(problemId);
			
			if (problem == null || assignment == null) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;	
			}
			
			int location = problem.getLocation();
			if (location  < problemRepository.getMaxProblemByAssignmentId(assignmentId).getLocation()) {
				location += 1;
				//Move current location holder down
				Problem below = problemRepository.getProblemByLocationAndAssignmentId(location, assignmentId);
				
				// Save Problem
				problemRepository.updateProblemLocationById(location, problemId);
				problemRepository.updateProblemLocationById(below.getLocation() - 1, below.getId());
			}
			
			resp.sendRedirect(req.getContextPath() + UrlUtils.putSecondInPath(UrlUtils.ASSIGNMENT_PROBLEMS_PATH, assignmentId));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	
	
	private void deleteAssignmentProblemDestroy(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId) throws IOException {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void postAssignmentProblemRubricItemCreate(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId) throws IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			Problem problem = problemRepository.getProblemById(problemId);
			if (problem == null || assignment == null) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;	
			}
			
			String title = req.getParameter("title");
			String pointsString = req.getParameter("points");
			
			if (title == "") {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId)) ;
				return;
			}
			
			if (UrlUtils.isDouble(pointsString)) {
				RubricItem maxRubricItem = rubricItemRepository.getMaxRubricItemByProblemId(problemId);
				Double points = UrlUtils.convertDoubleFromStringWithPrecision(pointsString, 4);
				rubricItemRepository.insertRubricItem(problemId, title, points, maxRubricItem.getLocation() + 1);			
				req.getSession(false).setAttribute("notice", "Rubric option added.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void patchAssignmentProblemRubricItemUpdate(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId, int rubricItemId) throws IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			Problem problem = problemRepository.getProblemById(problemId);
			RubricItem rubricItem = rubricItemRepository.getRubricItemById(rubricItemId);
			
			if (problem == null || assignment == null || rubricItem == null) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;	
			}
			
			String title = req.getParameter("title");
			String pointsString = req.getParameter("points");
			
			if (title == "") {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId)) ;
				return;
			}
			
			if (pointsString == "") {
				req.getSession(false).setAttribute("alert", "Points can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId)) ;
				return;
			}
			
			if (UrlUtils.isDouble(pointsString)) {
				Double points = UrlUtils.convertDoubleFromStringWithPrecision(pointsString, 4);
				rubricItemRepository.updateRubricItemById(title, points, rubricItemId);
				req.getSession(false).setAttribute("notice", "Rubric option was successfully updated.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private void patchAssignmentProblemRubricItemMoveUp(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId, int rubricItemId) throws IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			Problem problem = problemRepository.getProblemById(problemId);
			RubricItem rubricItem = rubricItemRepository.getRubricItemById(rubricItemId);
			
			if (problem == null || assignment == null || rubricItem == null) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;	
			}
			
			int location = rubricItem.getLocation();

			
			if (location  > 0) {
				location -= 1;
				
				
				//Move current location holder down
				RubricItem above = rubricItemRepository.getRubricItemByLocationAndProblemId(location, problemId);

				// Save Problem
				rubricItemRepository.updateRubricItemLocationById(location, rubricItemId);
				rubricItemRepository.updateRubricItemLocationById(above.getLocation() + 1, above.getId());
			}
			
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void patchAssignmentProblemRubricItemMoveDown(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId, int rubricItemId) throws IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			Problem problem = problemRepository.getProblemById(problemId);
			RubricItem rubricItem = rubricItemRepository.getRubricItemById(rubricItemId);
			
			if (problem == null || assignment == null || rubricItem == null) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;	
			}
			
			int location = rubricItem.getLocation();
			
			if (location  < rubricItemRepository.getMaxRubricItemByProblemId(problemId).getLocation()) {
				location += 1;
				//Move current location holder down
				RubricItem below = rubricItemRepository.getRubricItemByLocationAndProblemId(location, problemId) ;
				
				// Save Problem
				rubricItemRepository.updateRubricItemLocationById(location, rubricItemId);
				rubricItemRepository.updateRubricItemLocationById(below.getLocation() - 1, below.getId());
			}
			
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void deleteAssignmentProblemRubricItemDestroy(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId, int rubricItemId) throws IOException {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void postAssignmentProblemReusableCommentCreate(HttpServletRequest req, HttpServletResponse resp, int assignmentId, int problemId) throws IOException {
		try {
			Assignment assignment = assignmentRepository.getAssignmentById(assignmentId);
			Problem problem = problemRepository.getProblemById(problemId);
			
			if (problem == null || assignment == null) {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;	
			}
			
			String comment = req.getParameter("reusable_comment[comment]");
			
			if (comment == "") {
				req.getSession(false).setAttribute("alert", "Failed to save reusable comment: comment is too short (minimum is 1 character).");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId));
				return;
			}
			
			if (reusableCommentRepository.existedByCommentAndProblemId(comment, problemId)) {
				req.getSession(false).setAttribute("alert", "Failed to save reusable comment: comment has already been taken");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId));
				return;
			}
			
			reusableCommentRepository.insertReusableComment(problemId, comment);
			req.getSession(false).setAttribute("notice", "Reusable Comment Added!");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.putSecondInPath(UrlUtils.EDIT_ASSIGNMENT_PROBLEM_PATH, assignmentId), problemId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}