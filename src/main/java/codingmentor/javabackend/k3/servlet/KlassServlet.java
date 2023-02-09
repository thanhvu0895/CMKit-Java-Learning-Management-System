package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import codingmentor.javabackend.k3.Utils.AccountsMailer;
import codingmentor.javabackend.k3.Utils.DateValidatorDateTimeFormatter;
import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.RandomUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.Assigned;
import codingmentor.javabackend.k3.model.Assignment;
import codingmentor.javabackend.k3.model.Course;
import codingmentor.javabackend.k3.model.Department;
import codingmentor.javabackend.k3.model.GradeCategory;
import codingmentor.javabackend.k3.model.Grader;
import codingmentor.javabackend.k3.model.Klass;
import codingmentor.javabackend.k3.model.Professor;
import codingmentor.javabackend.k3.model.Student;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.impl.AssignedRepository;
import codingmentor.javabackend.k3.repository.impl.AssignmentRepository;
import codingmentor.javabackend.k3.repository.impl.CourseRepository;
import codingmentor.javabackend.k3.repository.impl.DepartmentRepository;
import codingmentor.javabackend.k3.repository.impl.GradeCategoryRepository;
import codingmentor.javabackend.k3.repository.impl.GraderRepository;
import codingmentor.javabackend.k3.repository.impl.KlassRepository;
import codingmentor.javabackend.k3.repository.impl.ProfessorRepository;
import codingmentor.javabackend.k3.repository.impl.RepoRepository;
import codingmentor.javabackend.k3.repository.impl.StudentRepository;
import codingmentor.javabackend.k3.repository.impl.UserRepository;


@WebServlet(urlPatterns = {
	UrlUtils.KLASSES_ALL_PATH,
	UrlUtils.NEW_KLASS_PATH,
	UrlUtils.GRADERS_ALL_PATH,
	UrlUtils.STUDENTS_ALL_PATH,
	UrlUtils.ROOT_PATH
})
public class KlassServlet extends HttpServlet {

	private static final long serialVersionUID = -290278653216172056L;
	private KlassRepository klassRepository = null;
	private RepoRepository repoRepository = null;
	private CourseRepository courseRepository = null;
	private DepartmentRepository departmentRepository = null;
	private ProfessorRepository professorRepository = null;
	private UserRepository userRepository = null;
	private AssignmentRepository assignmentRepository = null;
	private GradeCategoryRepository gradeCategoryRepository = null;
	private GraderRepository graderRepository = null;
	private StudentRepository studentRepository = null;
	private AssignedRepository assignedRepository = null;
	private final Logger logger =  LogManager.getLogger("codingmentor");
	
	@Override
	public void init() throws ServletException {
		super.init();
		klassRepository = KlassRepository.getInstance();
		repoRepository = RepoRepository.getInstance();
		courseRepository = CourseRepository.getInstance();
		departmentRepository = DepartmentRepository.getInstance();
		professorRepository = ProfessorRepository.getInstance();
		userRepository = UserRepository.getInstance();
		assignmentRepository = AssignmentRepository.getInstance();
		gradeCategoryRepository = GradeCategoryRepository.getInstance();
		graderRepository = GraderRepository.getInstance();
		studentRepository = StudentRepository.getInstance();
		assignedRepository = AssignedRepository.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.ROOT_PATH:
			getKlassIndex(req, resp);
			break;
		case UrlUtils.NEW_KLASS_PATH:
			getKlassNew(req, resp);
			break;
		case UrlUtils.KLASSES_PATH:
			String pathInfo = req.getPathInfo();
			if (pathInfo == null || pathInfo.equals("/")) {
				getKlassIndex(req, resp);
				return;
			}
			
			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;

			if (pathInfoLength == 2 && UrlUtils.isInteger(pathParts[1])) {
				int klassId = Integer.parseInt(pathParts[1]);
				getKlassShow(req, resp, klassId);
				return;
			}
			
			if (pathInfoLength == 3 && UrlUtils.isInteger(pathParts[1])) { 
				int klassId = Integer.parseInt(pathParts[1]);
				switch (pathParts[2]) {
				case "edit":
					getKlassEdit(req, resp, klassId);
					break;
				case "assignments":
					getKlassAssignmentIndex(req, resp, klassId);
					break;
				case "files":
					getKlassFiles(req, resp, klassId);
					break;
				case "gradebook":
					getKlassGradeBook(req, resp, klassId);
					break;
				case "gradebook_csv":
					getKlassGradeBookCSV(req, resp, klassId);
					break;
				case "graders":
					getKlassGradersIndex(req, resp, klassId);
					break;
				case "students":
					getKlassStudentsIndex(req, resp, klassId);
					break;
				}
				
				return;
			}
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;	

		}
	}
	
	private void getKlassIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User current_user = (User) req.getSession(false).getAttribute("current_user");
			
			List<Klass> studentKlassesList = klassRepository.getStudentKlassesByUserId(current_user.getId());
						
			if (!studentKlassesList.isEmpty()) {
				List<Course> studentCoursesList = courseRepository.getStudentCoursesByUserId(current_user.getId());
				req.setAttribute("student_klasses", studentKlassesList);
				req.setAttribute("student_courses", studentCoursesList);
			}
			
			List<Klass> graderKlassesList = klassRepository.getGraderKlassesByUserId(current_user.getId());
			
			if (!graderKlassesList.isEmpty()) {
				List<Course> graderCoursesList = courseRepository.getGraderCoursesByUserId(current_user.getId());
				req.setAttribute("grader_klasses", graderKlassesList);
				req.setAttribute("grader_courses", graderCoursesList);
			}
			
			
			List<Klass> professorKlassesList = klassRepository.getProfessorKlassesByUserId(current_user.getId());
			
			if (!professorKlassesList.isEmpty()) {
				List<Course> professorCoursesList = courseRepository.getProfessorCoursesByUserIdWithStudentsCount(current_user.getId());
				req.setAttribute("professor_klasses", professorKlassesList);
				req.setAttribute("professor_courses", professorCoursesList);
			}			
			
			
			
			if (current_user.isAdmin()) {
				List<Klass> adminKlassesList = klassRepository.getklasses();
				List<Course> adminCoursesList = courseRepository.getAdminCourses();
				req.setAttribute("admin_klasses", adminKlassesList);
				req.setAttribute("admin_courses", adminCoursesList);
			}

			req.getRequestDispatcher(JspUtils.KLASSES_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void getKlassShow(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			Course course = courseRepository.getCourseByKlassId(klassId);
			
			User current_user = (User) req.getSession(false).getAttribute("current_user");
			
			if (klass.isKlassGrader(current_user)) {
				logger.info("I WAS HERE");
				List<Assigned> assignedsList = assignedRepository.getAssignedsByUserInKlassOrderByDueDate(current_user.getId(), klassId);
				List<Assignment> assignmentsList = assignmentRepository.getAssignedAssignmentsByUserInKlassOrderByDueDate(current_user.getId(), klassId);
				req.setAttribute("grader_assigneds", assignedsList);
				req.setAttribute("grader_assignments", assignmentsList);
			}
			
			
			List<GradeCategory> gradeCategoriesList = gradeCategoryRepository.getGradeCategoriesByCourseId(klass.getCourse_id());
			
			req.setAttribute("grade_categories", gradeCategoriesList);
			req.setAttribute("course", course);
			req.setAttribute("klass", klass);
			
			req.getRequestDispatcher(JspUtils.KLASSES_SHOW)
				.forward(req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void getKlassEdit(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			Klass klass = klassRepository.getKlassById(klassId);
			List<User> klassProfessorUsers = userRepository.getUsersFromKlassId(klassId);
			List<Professor> klassProfessors = professorRepository.getProfessorsByKlassId(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			Course course = courseRepository.getCourseByKlassId(klassId);
			Department klassDepartment = departmentRepository.getDepartmentByCourseId(course.getId());
			
			req.setAttribute("course", course);
			req.setAttribute("klass", klass);
			req.setAttribute("department", klassDepartment);
			req.setAttribute("klass_professor_users", klassProfessorUsers);
			req.setAttribute("klass_professors", klassProfessors);
			req.getRequestDispatcher(JspUtils.KLASSES_EDIT)
				.forward(req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void getKlassAssignmentIndex(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			Course course = courseRepository.getCourseByKlassId(klassId);
			
			List<Assignment> klassAssignmentsList = assignmentRepository.getAssignmentsWithGradersListByKlassId(klassId);
			List<Assignment> courseAssignmentList = assignmentRepository.getAssignmentsWithGradersListByCourseId(klass.getCourse_id());
			List<GradeCategory> klassGradeCategoriesList = gradeCategoryRepository.getGradeCategoriesUsedByAssignmentsInKlass(klassId);
			List<GradeCategory> courseGradeCategoriesList = gradeCategoryRepository.getGradeCategoriesUsedByAssignmentsInCourse(klass.getCourse_id());
			List<Assigned> klassAssignedsList = assignedRepository.getAssignedsByAssignmentsInKlass(klassId);
			List<Assigned> courseAssignedsList = assignedRepository.getAssignedsByAssignmentsInCourse(klass.getCourse_id(), klassId);
			
			req.setAttribute("klass", klass);
			req.setAttribute("course", course);
			req.setAttribute("klass_assigneds", klassAssignedsList);
			req.setAttribute("course_assigneds", courseAssignedsList);
			req.setAttribute("klass_grade_categories", klassGradeCategoriesList);
			req.setAttribute("course_grade_categories", courseGradeCategoriesList);
			req.setAttribute("klass_assignments", klassAssignmentsList);
			req.setAttribute("course_assignments", courseAssignmentList);
			
			req.getRequestDispatcher(JspUtils.ASSIGNMENTS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void getKlassFiles(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			Course course = courseRepository.getCourseByKlassId(klassId);
			
			req.setAttribute("klass", klass);
			req.setAttribute("course", course);
			
			req.getRequestDispatcher(JspUtils.KLASSES_FILES)
				.forward(req, resp);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void getKlassGradeBook(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			Course course = courseRepository.getCourseByKlassId(klassId);
			List<GradeCategory> klassGradeCategoriesList = gradeCategoryRepository.getGradeCategoriesUsedByAssignmentsInKlass(klassId);
			
			req.setAttribute("klass_grade_categories", klassGradeCategoriesList);
			req.setAttribute("klass", klass);
			req.setAttribute("course", course);
			req.getRequestDispatcher(JspUtils.KLASSES_GRADE_BOOK)
				.forward(req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void getKlassGradeBookCSV(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			req.getRequestDispatcher(JspUtils.KLASSES_GRADE_BOOK)
				.forward(req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void getKlassGradersIndex(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			Klass klass = klassRepository.getKlassById(klassId);
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			Course course = courseRepository.getCourseByKlassId(klassId);
			List<Grader> gradersList = graderRepository.getGradersByKlassId(klassId);
			List<User> graderUsersList = userRepository.getGraderUsersByKlassIdWithAssignedAssignmentCount(klassId);
			
			req.setAttribute("course", course);
			req.setAttribute("klass", klass);
			req.setAttribute("graders", gradersList);
			req.setAttribute("grader_users", graderUsersList);
			
			req.getRequestDispatcher(JspUtils.GRADERS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void getKlassStudentsIndex(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			Course course = courseRepository.getCourseByKlassId(klassId);
			
			List<Student> studentsList = studentRepository.getStudentsByKlassId(klassId);
			List<User> studentUsersList = userRepository.getStudentUsersByKlassId(klassId);
			List<String> studentEmailsList = new ArrayList<>();
			
			for (User user : studentUsersList) {
				studentEmailsList.add(user.getEmail()); 
			}
			
			String studentEmailsString = String.join(",", studentEmailsList);
			
			req.setAttribute("course", course);
			req.setAttribute("klass", klass);
			req.setAttribute("students", studentsList);
			req.setAttribute("student_users", studentUsersList);
			req.setAttribute("student_emails", studentEmailsString);
			
			req.getRequestDispatcher(JspUtils.STUDENTS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void getKlassNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String courseIdString = req.getParameter("course");
			
			if (UrlUtils.isInteger(courseIdString)) {
				int courseId = Integer.parseInt(courseIdString);
				Course course = courseRepository.getCourseById(courseId);

				if (course == null) {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
				
				req.setAttribute("course", course);
				req.setAttribute("departmentid", course.getDepartment_id());
				req.getRequestDispatcher(JspUtils.KLASSES_NEW)
					.forward(req, resp);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.KLASSES_PATH:
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) {
				postKlassCreate(req, resp);
				return;
			}
			
			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;
			
			if (pathInfoLength == 2 && UrlUtils.isInteger(pathParts[1])) {
				int klassId = Integer.parseInt(pathParts[1]);
				switch (req.getParameter("method")) {
				case "PATCH":
					patchKlassUpdate(req, resp, klassId);
					break;
				case "DELETE":
					deleteKlassDestroy(req, resp, klassId);
					break;
				}
			}
		break;
		case UrlUtils.GRADERS_PATH:
			postGraderCreate(req, resp);
			break;
		case UrlUtils.STUDENTS_PATH:
			postStudentCreate(req, resp);
			break;			
		}
	}
	
	private void postKlassCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			String courseIdString = req.getParameter("course");
			String klassCourseIdString = req.getParameter("klass[course_id]");
			String klassSemester = req.getParameter("klass[semester]");
			String klassSectionString = req.getParameter("klass[section]");
			String klassStartYearString = req.getParameter("klass[start_date(1i)]");
			String klassStartMonthString = req.getParameter("klass[start_date(2i)]");
			String klassStartDayString = req.getParameter("klass[start_date(3i)]");
			String klassEndYearString = req.getParameter("klass[end_date(1i)]");
			String klassEndMonthString = req.getParameter("klass[end_date(2i)]");
			String klassEndDayString = req.getParameter("klass[end_date(3i)]");
			
			int klassCourseId = Integer.parseInt(klassCourseIdString);
			Integer klassSection = null; 
			int klassStartYear = Integer.parseInt(klassStartYearString);
			int klassStartMonth = Integer.parseInt(klassStartMonthString);
			int klassStartDay = Integer.parseInt(klassStartDayString);
			int klassEndYear = Integer.parseInt(klassEndYearString);
			int klassEndMonth = Integer.parseInt(klassEndMonthString);
			int klassEndDay = Integer.parseInt(klassEndDayString);

			boolean isValidKlassStartDate = DateValidatorDateTimeFormatter.isValid(klassStartYearString, klassStartMonthString, klassStartDayString);
			boolean isValidKlassEndDate = DateValidatorDateTimeFormatter.isValid(klassEndYearString, klassEndMonthString, klassEndDayString);
			
			if (klassSemester.isEmpty()) {
				req.getSession().setAttribute("alert", "Semester can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_KLASS_PATH + "?course=" + courseIdString);
				return;
			}
			
			if (klassSectionString != "") {
				klassSection = Integer.parseInt(klassSectionString);
			}
			
			if (!isValidKlassStartDate) {
				req.getSession().setAttribute("alert", "Start Date must be valid");
				resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_KLASS_PATH + "?course=" + courseIdString);
				return;
			}
			
			if (!isValidKlassEndDate) {
				req.getSession().setAttribute("alert", "End Date must be valid");
				resp.sendRedirect(req.getContextPath() + UrlUtils.NEW_KLASS_PATH + "?course=" + courseIdString);
				return;
			}
			
			LocalDate klassStartDate = LocalDate.of(klassStartYear, klassStartMonth, klassStartDay);
			LocalDate klassEndDate = LocalDate.of(klassEndYear, klassEndMonth, klassEndDay);
			
			int repoId = repoRepository.insertRepo();
			if (repoId != -1) {
				int klassId = klassRepository.insertKlass(klassCourseId, repoId, klassSemester, klassSection, klassStartDate, klassEndDate);
				User current_user = (User) req.getSession(false).getAttribute("current_user");
				professorRepository.insertProfessor(current_user.getId(), klassId);
				req.getSession(false).setAttribute("notice", "Class was successfully created.");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putSecondInPath(UrlUtils.KLASS_ASSIGNMENTS_PATH, klassId));
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void patchKlassUpdate(HttpServletRequest req, HttpServletResponse resp, int klassId) throws IOException {
		try {
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return; 
			}
			
			String klassSemester = req.getParameter("klass[semester]");
			String klassSectionString = req.getParameter("klass[section]");
			String klassStartYearString = req.getParameter("klass[start_date(1i)]");
			String klassStartMonthString = req.getParameter("klass[start_date(2i)]");
			String klassStartDayString = req.getParameter("klass[start_date(3i)]");
			String klassEndYearString = req.getParameter("klass[end_date(1i)]");
			String klassEndMonthString = req.getParameter("klass[end_date(2i)]");
			String klassEndDayString = req.getParameter("klass[end_date(3i)]");
			
			if (klassSemester.isEmpty()) {
				req.getSession().setAttribute("alert", "Semester can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_KLASS_PATH, klassId));
				return;
			}
			
			Integer klassSection = null; 
			int klassStartYear = Integer.parseInt(klassStartYearString);
			int klassStartMonth = Integer.parseInt(klassStartMonthString);
			int klassStartDay = Integer.parseInt(klassStartDayString);
			int klassEndYear = Integer.parseInt(klassEndYearString);
			int klassEndMonth = Integer.parseInt(klassEndMonthString);
			int klassEndDay = Integer.parseInt(klassEndDayString);

			boolean isValidKlassStartDate = DateValidatorDateTimeFormatter.isValid(klassStartYearString, klassStartMonthString, klassStartDayString);
			boolean isValidKlassEndDate = DateValidatorDateTimeFormatter.isValid(klassEndYearString, klassEndMonthString, klassEndDayString);
			
			if (klassSectionString != "") {
				klassSection = Integer.parseInt(klassSectionString);
			}
			
			if (!isValidKlassStartDate) {
				req.getSession().setAttribute("alert", "Start Date must be valid");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_KLASS_PATH, klassId));
				return;
			}
			
			if (!isValidKlassEndDate) {
				req.getSession().setAttribute("alert", "End Date must be valid");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_KLASS_PATH, klassId));
				return;
			}
			
			LocalDate klassStartDate = LocalDate.of(klassStartYear, klassStartMonth, klassStartDay);
			LocalDate klassEndDate = LocalDate.of(klassEndYear, klassEndMonth, klassEndDay);
			
			klassRepository.updateKlassById(klassSemester, klassSection, klassStartDate, klassEndDate, klassId);
			req.getSession().setAttribute("notice", "Class was successfully updated.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_KLASS_PATH, klassId));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	
	private void deleteKlassDestroy(HttpServletRequest req, HttpServletResponse resp, int klassId) throws IOException {
		try {
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void postGraderCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			//#Counters for success message
			List<String> added = new ArrayList<>();
			List<String> invited = new ArrayList<>();
			List<String> failed = new ArrayList<>();
			String token = RandomUtils.unique64();
			
			String[] graderEmailsList = req.getParameter("emails")
					.replaceAll("\\s","")
					.split(",");
			
			String klassIdString = req.getParameter("klass_id");
			int klassId = Integer.parseInt(klassIdString);
			
			for (String email : graderEmailsList) {
				if (email.isEmpty()) {
					continue;
				}
				
				email = email.toLowerCase();
				User u = userRepository.findUserByEmail(email);
				
				if (u != null  && !u.isDeleted() && !userRepository.isKlassGraderByKlassId(u.getId(), klassId)) {
					// User already exists and not a klass grader
					int newKlassGraderId = graderRepository.insertGrader(u.getId(), klassId);
					
					if (newKlassGraderId == 0) {
						failed.add(email);
					} else {
						added.add(email);
					}
					
					continue;
				}
				
				if (u == null) {
					boolean inviteSent = userRepository.createUserSendInvite(email, false);
					u =  userRepository.findUserByEmail(email);
					int new_user_id = u.getId();
					userRepository.updateResetDigest(new_user_id, RandomUtils.SHA256Base64(token));
					AccountsMailer.inviteUserEmail(req, u, token);
					if (inviteSent) {
						int newGraderId = graderRepository.insertGrader(new_user_id, klassId);
						if (newGraderId != 0) {
							invited.add(email);
						} else {
							failed.add(email);
						}
					} else {
						failed.add(email);
					}
					continue;		
				}
				
				
				if (u.isDeleted()) {
					boolean recoverUser = userRepository.recoverUser(u.getId());
					if (recoverUser) {
						userRepository.updateResetDigest(u.getId(), RandomUtils.SHA256Base64(token));
						AccountsMailer.inviteUserEmail(req, u, token);
						invited.add(email);
						if (!userRepository.isKlassGraderByKlassId(u.getId(), klassId)) {
							int newGraderId = graderRepository.insertGrader(u.getId(), klassId); 
							if (newGraderId != 0) {
								invited.add(email);	
							} else {
								failed.add(email);
							}
						}
					} else {
						failed.add(email);
					}
					continue;
				}
			}
			
			req.getSession(false).setAttribute("notice", added.size() + " graders added: " + String.join(",", added) + ";" 
					+ invited.size() + " graders invited: " + String.join(",", invited) + ";");
			req.getSession(false).setAttribute("alert", "Failed to add " + failed.size() + " graders: " + String.join(",", failed) + ";");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putSecondInPath(UrlUtils.KLASS_GRADERS_PATH, klassId));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	private void postStudentCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			//#Counters for success message
			List<String> added = new ArrayList<>();
			List<String> invited = new ArrayList<>();
			List<String> failed = new ArrayList<>();
			String token = RandomUtils.unique64();
			
			String[] studentEmailsList = req.getParameter("emails")
					.replaceAll("\\s","")
					.split(",");
			
			String klassIdString = req.getParameter("klass_id");
			int klassId = Integer.parseInt(klassIdString);
			
			for (String email : studentEmailsList) {
				if (email.isEmpty()) {
					continue;
				}
				
				email = email.toLowerCase();
				User u = userRepository.findUserByEmail(email);
				
				if (u != null  && !u.isDeleted() && !userRepository.isKlassStudentByKlassId(u.getId(), klassId)) {
					// User already exists and not a klass student
					int newKlassStudentId = studentRepository.insertStudent(u.getId(), klassId);
					
					if (newKlassStudentId == 0) {
						failed.add(email);
					} else {
						added.add(email);
					}
					
					continue;
				}
				
				if (u == null) {
					boolean inviteSent = userRepository.createUserSendInvite(email, false);
					u =  userRepository.findUserByEmail(email);
					int new_user_id = u.getId();
					userRepository.updateResetDigest(new_user_id, RandomUtils.SHA256Base64(token));
					AccountsMailer.inviteUserEmail(req, u, token);
					if (inviteSent) {
						int newStudentId = studentRepository.insertStudent(new_user_id, klassId);
						if (newStudentId != 0) {
							invited.add(email);
						} else {
							failed.add(email);
						}
					} else {
						failed.add(email);
					}
					continue;		
				}
				
				
				if (u.isDeleted()) {
					boolean recoverUser = userRepository.recoverUser(u.getId());
					if (recoverUser) {
						userRepository.updateResetDigest(u.getId(), RandomUtils.SHA256Base64(token));
						AccountsMailer.inviteUserEmail(req, u, token);
						invited.add(email);
						if (!userRepository.isKlassStudentByKlassId(u.getId(), klassId)) {
							int newStudentId = studentRepository.insertStudent(u.getId(), klassId); 
							if (newStudentId != 0) {
								invited.add(email);	
							} else {
								failed.add(email);
							}
						}
					} else {
						failed.add(email);
					}
					continue;
				}
			}
			
			req.getSession(false).setAttribute("notice", added.size() + " students added: " + String.join(",", added) + ";" 
					+ invited.size() + " students invited: " + String.join(",", invited) + ";");
			req.getSession(false).setAttribute("alert", "Failed to add " + failed.size() + " students: " + String.join(",", failed) + ";");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putSecondInPath(UrlUtils.KLASS_STUDENTS_PATH, klassId));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}
