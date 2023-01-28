package codingmentor.javabackend.k3.servlet;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.repository.AssignmentRepository;
import codingmentor.javabackend.k3.repository.ProfessorRepository;

@WebServlet(urlPatterns = {
		UrlUtils.COURSES_PATH_ALL,
		UrlUtils.NEW_COURSE_PATH,
})
public class AssignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AssignmentRepository assignmentRepository = null;



}
