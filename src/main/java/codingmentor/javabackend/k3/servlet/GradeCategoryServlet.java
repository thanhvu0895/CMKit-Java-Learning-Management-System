package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.Course;
import codingmentor.javabackend.k3.model.Department;
import codingmentor.javabackend.k3.model.GradeCategory;
import codingmentor.javabackend.k3.repository.CourseRepository;
import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.GradeCategoryRepository;
import codingmentor.javabackend.k3.repository.Impl.CourseRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.GradeCategoryRepositoryImpl;


@WebServlet(urlPatterns = {
		UrlUtils.GRADE_CATEGORIES_ALL_PATH,
		UrlUtils.NEW_GRADE_CATEGORY_PATH,
})
public class GradeCategoryServlet extends HttpServlet{
	private static final long serialVersionUID = 1515497142397284883L;
	private GradeCategoryRepository gradeCategoryRepository = null;
	private CourseRepository courseRepository = null;
	private DepartmentRepository departmentRepository = null;
//	private RepoRepository repoRepository = null;
//	private UserRepository userRepository = null;
//	private GradeCategoryProfessorRepository gradeCategoryProfessorRepository = null;
//	private KlassRepository klassRepository = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		gradeCategoryRepository = GradeCategoryRepositoryImpl.getInstance();
		departmentRepository = DepartmentRepositoryImpl.getInstance();
//		repoRepository = RepoRepositoryImpl.getInstance();
		courseRepository = CourseRepositoryImpl.getInstance();
//		gradeCategoryProfessorRepository = GradeCategoryProfessorRepositoryImpl.getInstance();
//		userRepository = UserRepositoryImpl.getInstance();
//		klassRepository = KlassRepositoryImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.GRADE_CATEGORIES_PATH:
			System.out.println("Value of req.getServletPath() is: " + req.getServletPath());
			String pathInfo = req.getPathInfo();
			if (pathInfo == null || pathInfo.equals("/")) {
				getGradeCategoriesIndex(req, resp);
				return;
			}
			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;	
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1])) { 
				int gradeCategoryId = Integer.parseInt(pathParts[1]);
				switch (pathParts[2]) {
				case "edit":
					getGradeCategoryEdit(req, resp, gradeCategoryId);			
					break;
				case "courses":
					getGradeCategoryCourses(req, resp, gradeCategoryId);
					break;
				case "files":
					getGradeCategoryFiles(req, resp, gradeCategoryId);
					break;
				case "klasses":
					getGradeCategoryKlasses(req, resp, gradeCategoryId);
					break;
				}
			}
			break;
		case UrlUtils.NEW_GRADE_CATEGORY_PATH: 					
			getGradeCategoriesNew(req, resp);
			break;
		}	
	}
	
	private void getGradeCategoriesIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
//			List<GradeCategory> gradeCategories = gradeCategoryRepository.getGradeCategories();
//			req.setAttribute("gradeCategories", gradeCategories);
//			req.getRequestDispatcher(JspUtils.DEPARTMENTS_INDEX)
//				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getGradeCategoryCourses(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId) throws ServletException, IOException {
		try {
//			GradeCategory gradeCategory = gradeCategoryRepository.getGradeCategoryById(gradeCategoryId);
//			List<Course> courses = courseRepository.getCoursesByGradeCategoryId(gradeCategoryId);
//			req.setAttribute("gradeCategory", gradeCategory);
//			req.setAttribute("courses", courses);
//			req.getRequestDispatcher(JspUtils.DEPARTMENTS_COURSES)
//				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getGradeCategoryEdit(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId) throws ServletException, IOException {
		try {
			GradeCategory gradeCategory = gradeCategoryRepository.getGradeCategoryById(gradeCategoryId);
			System.out.println(gradeCategory == null);
			Course course = courseRepository.getCourseByGradeCategoryId(gradeCategoryId);
			
			if (gradeCategory == null || course == null) {
				System.out.println("COURSE OR GRADE ID DOES NOT EXIST");
//				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			req.setAttribute("grade_category", gradeCategory);
			req.setAttribute("course", course);
			req.getRequestDispatcher(JspUtils.GRADE_CATEGORIES_EDIT)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	
	private void getGradeCategoryFiles(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId) throws ServletException, IOException {
		try {
//			GradeCategory gradeCategory = gradeCategoryRepository.getGradeCategoryById(gradeCategoryId);
//			req.setAttribute("gradeCategory", gradeCategory);	
//			req.getRequestDispatcher(JspUtils.DEPARTMENTS_FILES)
//				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getGradeCategoryKlasses(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId) throws ServletException, IOException {
		try {
//			GradeCategory gradeCategory = gradeCategoryRepository.getGradeCategoryById(gradeCategoryId);
//			List<Course> coursesByGradeCategories = courseRepository.getCoursesWithKlassByGradeCategoryId(gradeCategoryId);
//			List<Klass> courseKlasses = klassRepository.getKlassesFromGradeCategoryId(gradeCategoryId);
//			req.setAttribute("gradeCategory", gradeCategory);
//			req.setAttribute("klasses", courseKlasses);
//			req.setAttribute("courses", coursesByGradeCategories);	
//			req.getRequestDispatcher(JspUtils.DEPARTMENTS_KLASSES)
//				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getGradeCategoriesNew(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String courseIdString = req.getParameter("course");
			if (UrlUtils.isInteger(courseIdString)) {
				int courseId = Integer.parseInt(courseIdString);
				Course course = courseRepository.getCourseById(courseId);
				Department department = departmentRepository.getDepartmentByCourseId(courseId);
				if (course != null && department != null) {
					req.setAttribute("course", course);
					req.setAttribute("departmentid", department.getId());
					req.getRequestDispatcher(JspUtils.GRADE_CATEGORIES_NEW) 
						.forward(req, resp);
					return;
				}
			}
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.GRADE_CATEGORIES_PATH:
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) { // if request is /users/ or /users
				postGradeCategoriesCreate(req, resp);
				break;
			}

			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;
			
			if (pathInfoLength == 2 && UrlUtils.isInteger(pathParts[1])) {
				int gradeCategoryId = Integer.parseInt(pathParts[1]);
				switch (req.getParameter("method")) {
				case "PATCH":
					patchGradeCategoryUpdate(req, resp, gradeCategoryId);
					break;
				case "PUT":
					putGradeCategoryUpdate(req, resp, gradeCategoryId);
					break;
				case "DELETE":
					deleteGradeCategoryDestroy(req, resp, gradeCategoryId);
					break;
				}
			}
			
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("gradeCategory_professors")) {
				int gradeCategoryId = Integer.parseInt(pathParts[1]);
				postGradeCategoryProfessors(req, resp, gradeCategoryId);
				return;
				
			}
			
			if (pathInfoLength == 4 && UrlUtils.isInteger(pathParts[1]) && pathParts[2].equals("gradeCategory_professors") && UrlUtils.isInteger(pathParts[3])) { 
				int gradeCategoryId = Integer.parseInt(pathParts[1]);
				int gradeCategoryProfessorId = Integer.parseInt(pathParts[3]);
				switch (req.getParameter("method")) {
				case "PATCH":
					patchGradeCategoryProfessorUpdate(req, resp, gradeCategoryId, gradeCategoryProfessorId);
					break;
				case "PUT":
					putGradeCategoryProfessorUpdate(req, resp, gradeCategoryId, gradeCategoryProfessorId);
					break;
				case "DELETE":
					deleteGradeCategoryProfessorDestroy(req, resp, gradeCategoryId, gradeCategoryProfessorId);
					break;
				}
			}
		}
	}
	
	private void postGradeCategoriesCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String title = req.getParameter("grade_category[title]");
			String gradeCategoryWeightString = req.getParameter("grade_category[weight]");
			String courseIdString = req.getParameter("grade_category[course_id]");
			int courseId = Integer.parseInt(courseIdString);
			if (title == "") {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.NEW_GRADE_CATEGORY_PATH	 + "?course=:id", courseId));
				return;
			}
			
			if (gradeCategoryWeightString == "") {
				req.getSession(false).setAttribute("alert", "Grade Category can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.NEW_GRADE_CATEGORY_PATH	 + "?course=:id", Integer.parseInt(courseIdString)));
				return;
			}
			
			if (UrlUtils.isDouble(gradeCategoryWeightString)) {
				Double gradeCategoryWeight = UrlUtils.convertDoubleFromStringWithPrecision(gradeCategoryWeightString, 4);
				gradeCategoryRepository.insertGradeCategory(title, courseId, gradeCategoryWeight);
				req.getSession(false).setAttribute("notice", "Grade category was successfully created.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.COURSE_GRADE_CATEGORIES_PATH, courseId));
				return;
			}
			
			req.getSession(false).setAttribute("alert", "Invalid value for grade category.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.COURSE_GRADE_CATEGORIES_PATH, courseId));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void patchGradeCategoryUpdate(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId) throws IOException {
		try {
			String title = req.getParameter("grade_category[title]");
			String gradeCategoryWeightString = req.getParameter("grade_category[weight]");
			String courseIdString = req.getParameter("grade_category[course_id]");
			int courseId = Integer.parseInt(courseIdString);
			if (title == "") {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.NEW_GRADE_CATEGORY_PATH	 + "?course=:id", courseId));
				return;
			}
			
			if (gradeCategoryWeightString == "") {
				req.getSession(false).setAttribute("alert", "Grade Category can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.NEW_GRADE_CATEGORY_PATH	 + "?course=:id", Integer.parseInt(courseIdString)));
				return;
			}
			
			if (UrlUtils.isDouble(gradeCategoryWeightString)) {
				Double gradeCategoryWeight = UrlUtils.convertDoubleFromStringWithPrecision(gradeCategoryWeightString, 4);
				gradeCategoryRepository.updateGradeCategoryById(title, gradeCategoryWeight, gradeCategoryId);
				req.getSession(false).setAttribute("notice", "Grade category was successfully updated.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.COURSE_GRADE_CATEGORIES_PATH, courseId));
				return;
			}
			
			req.getSession(false).setAttribute("alert", "Invalid value for grade category.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.COURSE_GRADE_CATEGORIES_PATH, courseId));
			
			
//			gradeCategoryRepository.updateGradeCategoryTitleById(title, gradeCategoryId);
//			req.getSession(false).setAttribute("notice", "GradeCategory was successfully updated.");
//			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.DEPARTMENT_COURSES_PATH, gradeCategoryId));
//			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void putGradeCategoryUpdate(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteGradeCategoryDestroy(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void postGradeCategoryProfessors(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId) throws IOException {
		try {
			//#Counters for success message
//			List<String> added = new ArrayList<>();
//			List<String> invited = new ArrayList<>();
//			List<String> failed = new ArrayList<>();
//			String token = RandomUtils.unique64();
//			
//			String[] gradeCategoryProfessorEmailList = req.getParameter("gradeCategory_professor[emails]")
//					.replaceAll("[\\s]","")
//					.split(",");
//			boolean admin = (req.getParameterValues("gradeCategory_professor[admin]").length == 2);
//			for (String email : gradeCategoryProfessorEmailList) {
//				email = email.toLowerCase();
//				User u = userRepository.findUserByEmail(email);
//				if (u != null  && !u.isDeleted() && !userRepository.isGradeCategoryProfessorByGradeCategoryId(u.getId(), gradeCategoryId)) {
//					// User already exists and not a gradeCategory professor
//					int newDpId = gradeCategoryProfessorRepository.insertGradeCategoryProfessor(u.getId(), gradeCategoryId, admin);
//					if (newDpId == 0) {
//						failed.add(email);
//					} else {
//						added.add(email);
//					}
//					break;
//				}
//				
//				if (u == null) {
//					boolean inviteSent = userRepository.createUserSendInvite(email, admin);
//					u =  userRepository.findUserByEmail(email);
//					int new_user_id = u.getId();
//					userRepository.updateResetDigest(new_user_id, RandomUtils.SHA256Base64(token));
//					AccountsMailer.inviteUserEmail(req, u, token);
//					if (inviteSent) {
//						int newDpId = gradeCategoryProfessorRepository.insertGradeCategoryProfessor(new_user_id, gradeCategoryId, admin);
//						if (newDpId != 0) {
//							invited.add(email);
//						} else {
//							failed.add(email);
//						}
//					} else {
//						failed.add(email);
//					}
//					break;		
//				}
//				
//				if (u.isDeleted()) {
//					boolean recoverUser = userRepository.recoverUser(u.getId());
//					if (recoverUser) {
//						userRepository.updateResetDigest(u.getId(), RandomUtils.SHA256Base64(token));
//						AccountsMailer.inviteUserEmail(req, u, token);
//						invited.add(email);
//						if (!userRepository.isGradeCategoryProfessorByGradeCategoryId(u.getId(), gradeCategoryId)) {
//							int newDpId = gradeCategoryProfessorRepository.insertGradeCategoryProfessor(u.getId(), gradeCategoryId, admin);
//							if (newDpId != 0) {
//								invited.add(email);	
//							} else {
//								failed.add(email);
//							}
//						}
//					} else {
//						failed.add(email);
//					}
//					break;
//				}
//			}
//			req.getSession(false).setAttribute("notice", added.size() + " professors added; " + invited.size() + " professors invited");
//			req.getSession(false).setAttribute("alert", "Failed to add " + failed.size() + " professors");
//			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_DEPARTMENT_PATH, gradeCategoryId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void patchGradeCategoryProfessorUpdate(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId, int gradeCategoryProfessorId) throws IOException {
		try {
//			boolean admin = (req.getParameterValues("gradeCategory_professor[admin]").length == 2);
//			gradeCategoryProfessorRepository.updateAdminByGradeCategoryProfessorId(admin, gradeCategoryProfessorId);
//			req.getSession(false).setAttribute("notice", "Professor updated.");
//			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_DEPARTMENT_PATH, gradeCategoryId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void putGradeCategoryProfessorUpdate(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId, int gradeCategoryProfessorId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteGradeCategoryProfessorDestroy(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId, int gradeCategoryProfessorId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}