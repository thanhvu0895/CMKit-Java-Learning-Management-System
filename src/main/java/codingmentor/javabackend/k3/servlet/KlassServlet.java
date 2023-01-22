package codingmentor.javabackend.k3.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.Utils.JspUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;


@WebServlet(urlPatterns = {
	UrlUtils.KLASSES_ALL_PATH,
	
})
public class KlassServlet extends HttpServlet {

	private static final long serialVersionUID = -290278653216172056L;

	@Override
	public void init() throws ServletException {
		super.init();
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
					getKlassAssignmentsIndex(req, resp, klassId);
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
			}
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
			req.getRequestDispatcher(JspUtils.KLASSES_SHOW)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassEdit(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.COURSES_EDIT)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassAssignmentsIndex(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.ASSIGNMENTS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassFiles(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.KLASSES_FILES)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassGradeBook(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.KLASSES_GRADE_BOOK)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassGradeBookCSV(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.KLASSES_GRADE_BOOK)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassGradersIndex(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.GRADERS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassStudentsIndex(HttpServletRequest req, HttpServletResponse resp, int klassId) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.STUDENTS_INDEX)
				.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void getKlassNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.getRequestDispatcher(JspUtils.KLASSES_NEW)
				.forward(req, resp);
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
				case "PUT":
					putKlassUpdate(req, resp, klassId);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void patchKlassUpdate(HttpServletRequest req, HttpServletResponse resp, int klassId) throws IOException {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void putKlassUpdate(HttpServletRequest req, HttpServletResponse resp, int klassId) throws IOException {
		try {
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
