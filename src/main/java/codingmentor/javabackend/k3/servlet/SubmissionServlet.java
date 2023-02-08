package codingmentor.javabackend.k3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.Assigned;
import codingmentor.javabackend.k3.model.Assignment;
import codingmentor.javabackend.k3.model.Course;
import codingmentor.javabackend.k3.model.Klass;
import codingmentor.javabackend.k3.repository.AssignedRepository;
import codingmentor.javabackend.k3.repository.AssignmentRepository;
import codingmentor.javabackend.k3.repository.CourseRepository;
import codingmentor.javabackend.k3.repository.KlassRepository;
import codingmentor.javabackend.k3.repository.Impl.AssignedRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.AssignmentRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.CourseRepositoryImpl;
import codingmentor.javabackend.k3.repository.Impl.KlassRepositoryImpl;



@WebServlet(urlPatterns = {
		UrlUtils.SUBMISSIONS_ALL_PATH
})
public class SubmissionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private KlassRepository klassRepository = null;
	private CourseRepository courseRepository = null;
	private AssignedRepository assignedRepository = null;
	private AssignmentRepository assignmentRepository = null;
	private final Logger logger =  LogManager.getLogger("codingmentor");
	
	@Override
	public void init() throws ServletException {
		super.init();
		klassRepository = KlassRepositoryImpl.getInstance();
		assignedRepository = AssignedRepositoryImpl.getInstance();
		assignmentRepository = AssignmentRepositoryImpl.getInstance();
		courseRepository = CourseRepositoryImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.SUBMISSIONS_PATH:
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) {
				String assignedIdString = req.getParameter("assigned");

				if(assignedIdString != null && UrlUtils.isInteger(assignedIdString)) {
					int assignedId = Integer.parseInt(assignedIdString);
					getSubmissionsAssignedIndex(req, resp, assignedId);
					return;
				}
				
				resp.sendRedirect(req.getContextPath() + "/");
				return;
			}			
		}	
	}
	
	private void getSubmissionsAssignedIndex(HttpServletRequest req, HttpServletResponse resp, int assignedId) throws IOException {
		try {
			Assigned assigned = assignedRepository.getAssignedById(assignedId);
			
			if (assigned == null) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			Assignment assignment = assignmentRepository.getAssignmentById(assigned.getAssignment_id());
			Klass klass = klassRepository.getKlassById(assigned.getKlass_id());
			Course course = courseRepository.getCourseById(klass.getCourse_id());
			
			req.setAttribute("assignment", assignment);
			req.setAttribute("assigned", assigned);
			req.setAttribute("course", course);
			req.setAttribute("klass", klass);
			
			req.getRequestDispatcher(JspUtils.SUBMISSIONS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.SUBMISSIONS_PATH:
			String pathInfo = req.getPathInfo();
			
			if (pathInfo == null || pathInfo.equals("/")) { // if request is /users/ or /users
				postSubmissionsCreate(req, resp);
				break;
			}
		}
	}
	
	private void postSubmissionsCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
	
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}
