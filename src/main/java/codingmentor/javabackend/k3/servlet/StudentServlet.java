package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.model.Student;


@WebServlet(urlPatterns = {
		"/StudentServlet"
})
public class StudentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Student[] students = {new Student("Kien", 40), new Student("LeLe", 38), new Student("Yen", 39)};
		req.setAttribute("students", students);
		req.getRequestDispatcher("s.jsp").forward(req, resp);
	
	}
}
