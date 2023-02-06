package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.Course;
import codingmentor.javabackend.k3.model.GradeCategory;
import codingmentor.javabackend.k3.repository.CourseRepository;
import codingmentor.javabackend.k3.repository.GradeCategoryRepository;
import codingmentor.javabackend.k3.repository.Impl.CourseRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.GradeCategoryRepositoryImpl;


@WebServlet(urlPatterns = {
		UrlUtils.GRADE_CATEGORIES_ALL_PATH,
		UrlUtils.NEW_GRADE_CATEGORY_PATH,
})
public class GradeCategoryServlet extends HttpServlet{
	private static final long serialVersionUID = 1515497142397284883L;
	private GradeCategoryRepository gradeCategoryRepository = null;
	private CourseRepository courseRepository = null;
	
	@Override
	public void init() throws ServletException {
		super.init();
		gradeCategoryRepository = GradeCategoryRepositoryImpl.getInstance();
		courseRepository = CourseRepositoryImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.GRADE_CATEGORIES_PATH:

			String pathInfo = req.getPathInfo();
			if (pathInfo == null || pathInfo.equals("/")) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
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
				}
				return;
			}
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		case UrlUtils.NEW_GRADE_CATEGORY_PATH: 					
			getGradeCategoriesNew(req, resp);
			break;
		}	
	}
	
	
	private void getGradeCategoryEdit(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId) throws ServletException, IOException {
		try {
			GradeCategory gradeCategory = gradeCategoryRepository.getGradeCategoryById(gradeCategoryId);

			if (gradeCategory == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}

			Course course = courseRepository.getCourseByGradeCategoryId(gradeCategoryId);
			req.setAttribute("grade_category", gradeCategory);
			req.setAttribute("course", course);
			req.getRequestDispatcher(JspUtils.GRADE_CATEGORIES_EDIT)
				.forward(req, resp);
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
				if (course != null) {
					req.setAttribute("course", course);
					req.setAttribute("departmentid", course.getDepartment_id());
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
				case "DELETE":
					deleteGradeCategoryDestroy(req, resp, gradeCategoryId);
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
			if (title .isEmpty()) {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.NEW_GRADE_CATEGORY_PATH	 + "?course=:id", courseId));
				return;
			}
			
			if (gradeCategoryWeightString .isEmpty()) {
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
			if (title .isEmpty()) {
				req.getSession(false).setAttribute("alert", "Title can't be blank");
				resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.NEW_GRADE_CATEGORY_PATH	 + "?course=:id", courseId));
				return;
			}
			
			if (gradeCategoryWeightString .isEmpty()) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	private void deleteGradeCategoryDestroy(HttpServletRequest req, HttpServletResponse resp, int gradeCategoryId) throws IOException {
		try {
			GradeCategory gradeCategory = gradeCategoryRepository.getGradeCategoryById(gradeCategoryId); 
			gradeCategoryRepository.deleteGradeCategory(gradeCategoryId);
			req.getSession(false).setAttribute("notice", "Grade category was successfully deleted.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.COURSE_GRADE_CATEGORIES_PATH, gradeCategory.getCourse_id()));		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}