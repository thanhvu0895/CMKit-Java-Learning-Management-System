package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.Utils.DateValidatorDateTimeFormatter;
import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.Course;
import codingmentor.javabackend.k3.model.Department;
import codingmentor.javabackend.k3.model.Klass;
import codingmentor.javabackend.k3.model.Professor;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.CourseRepository;
import codingmentor.javabackend.k3.repository.DepartmentRepository;
import codingmentor.javabackend.k3.repository.KlassRepository;
import codingmentor.javabackend.k3.repository.ProfessorRepository;
import codingmentor.javabackend.k3.repository.RepoRepository;
import codingmentor.javabackend.k3.repository.UserRepository;
import codingmentor.javabackend.k3.repository.Impl.CourseRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.DepartmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.KlassRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.ProfessorRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.RepoRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.UserRepositoryImpl;


@WebServlet(urlPatterns = {
	UrlUtils.KLASSES_ALL_PATH,
})
public class KlassServlet extends HttpServlet {

	private static final long serialVersionUID = -290278653216172056L;
	private KlassRepository klassRepository = null;
	private RepoRepository repoRepository = null;
	private CourseRepository courseRepository = null;
	private DepartmentRepository departmentRepository = null;
	private ProfessorRepository professorRepository = null;
	private UserRepository userRepository = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		klassRepository = KlassRepositoryImpl.getInstance();
		repoRepository = RepoRepositoryImpl.getInstance();
		courseRepository = CourseRepositoryImpl.getInstance();
		departmentRepository = DepartmentRepositoryImpl.getInstance();
		professorRepository = ProfessorRepositoryImpl.getInstance();
		userRepository = UserRepositoryImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		switch(req.getServletPath()) {
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
		case UrlUtils.NEW_KLASS_PATH:
			getKlassNew(req, resp);
			break;
		}
	}
	
	private void getKlassIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.KLASSES_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassShow(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			Klass klass = klassRepository.getKlassById(klassId);
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			req.getRequestDispatcher(JspUtils.KLASSES_SHOW)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			req.setAttribute("klass", klass);
			req.setAttribute("course", course);
			req.getRequestDispatcher(JspUtils.ASSIGNMENTS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassFiles(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			req.getRequestDispatcher(JspUtils.KLASSES_FILES)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassGradeBook(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			req.getRequestDispatcher(JspUtils.KLASSES_GRADE_BOOK)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
	
	private void getKlassGradersIndex(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			req.getRequestDispatcher(JspUtils.GRADERS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassStudentsIndex(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			Klass klass = klassRepository.getKlassById(klassId);
			
			if (klass == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			req.getRequestDispatcher(JspUtils.STUDENTS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String courseIdString = req.getParameter("course");
			if (UrlUtils.isInteger(courseIdString)) {
				int courseId = Integer.parseInt(courseIdString);
				Course course = courseRepository.getCourseById(courseId);
				if (course != null) {
					req.setAttribute("course", course);
					req.setAttribute("departmentid", course.getDepartment_id());
					req.getRequestDispatcher(JspUtils.KLASSES_NEW)
						.forward(req, resp);
					return;
				}
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
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
			
			if (klassSemester == "") {
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
			e.printStackTrace();
		}
	}
	
	private void patchKlassUpdate(HttpServletRequest req, HttpServletResponse resp, int klassId) throws IOException {
		try {
			System.out.println("HIT");
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
			
			if (klassSemester == "") {
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
			e.printStackTrace();
		}
	}
	
	
	private void deleteKlassDestroy(HttpServletRequest req, HttpServletResponse resp, int klassId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
