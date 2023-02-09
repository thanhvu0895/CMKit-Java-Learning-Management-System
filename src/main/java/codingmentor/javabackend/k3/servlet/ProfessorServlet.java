package codingmentor.javabackend.k3.servlet;

import java.io.IOException;
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
import codingmentor.javabackend.k3.Utils.RandomUtils;
import codingmentor.javabackend.k3.Utils.UrlUtils;
import codingmentor.javabackend.k3.model.Professor;
import codingmentor.javabackend.k3.model.User;
import codingmentor.javabackend.k3.repository.impl.ProfessorRepository;
import codingmentor.javabackend.k3.repository.impl.UserRepository;

@WebServlet(urlPatterns = {
		UrlUtils.PROFESSORS_ALL_PATH,
	})
public class ProfessorServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ProfessorRepository professorRepository = null;
	private UserRepository userRepository = null;
	private final Logger logger =  LogManager.getLogger("codingmentor");
	
	@Override
	public void init() throws ServletException {
		super.init();
		professorRepository = ProfessorRepository.getInstance();
		userRepository = UserRepository.getInstance();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch(req.getServletPath()) {
		case UrlUtils.PROFESSORS_PATH:
			String pathInfo = req.getPathInfo();
			if (pathInfo == null || pathInfo.equals("/")) {
				postProfessorCreate(req, resp);
				return;
			}

			String[] pathParts = pathInfo.split("/");
			int pathInfoLength = pathParts.length;

			if (pathInfoLength == 2 && UrlUtils.isInteger(pathParts[1])) {
				int professorId = Integer.parseInt(pathParts[1]);
				postProfessorDelete(req, resp, professorId);
				return;
			}
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}
	
	private void postProfessorCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {			
			//#Counters for success message
			List<String> added = new ArrayList<>();
			List<String> invited = new ArrayList<>();
			List<String> failed = new ArrayList<>();
			String token = RandomUtils.unique64();
			
			String[] professorEmailList = req.getParameter("emails")
					.replaceAll("\\s","")
					.split(",");
			
			String klassIdString = req.getParameter("klass_id");
			int klassId = Integer.parseInt(klassIdString);
			
			for (String email : professorEmailList) {
				if (email.isEmpty()) {
					continue;
				}
				email = email.toLowerCase();
				User u = userRepository.findUserByEmail(email);
				
				if (u != null  && !u.isDeleted() && !userRepository.isKlassProfessorByKlassId(u.getId(), klassId)) {
					// User already exists and not a klass professor
					int newKlassProfessorId = professorRepository.insertProfessor(u.getId(), klassId); 
					if (newKlassProfessorId == 0) {
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
						int newDpId = professorRepository.insertProfessor(new_user_id, klassId);
						if (newDpId != 0) {
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
						if (!userRepository.isKlassProfessorByKlassId(u.getId(), klassId)) {
							int newKlassProfessorId = professorRepository.insertProfessor(u.getId(), klassId); 
							if (newKlassProfessorId != 0) {
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
			
			req.getSession(false).setAttribute("notice", added.size() + " professors added: " + String.join(",", added) + ";" 
					+ invited.size() + " professors invited: " + String.join(",", invited) + ";");
			req.getSession(false).setAttribute("alert", "Failed to add " + failed.size() + " professors: " + String.join(",", failed) + ";");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_KLASS_PATH, klassId));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	
	private void postProfessorDelete(HttpServletRequest req, HttpServletResponse resp, int professorId) throws IOException {
		try {
			Professor professor = professorRepository.getProfessorById(professorId);		
			professorRepository.deleteProfessor(professorId);
			req.getSession(false).setAttribute("notice", "Professor was successfully removed.");
			resp.sendRedirect(req.getContextPath() + UrlUtils.putIdInPath(UrlUtils.EDIT_KLASS_PATH, professor.getKlass_id()));
		} catch (Exception e) {
				logger.error(e.getMessage());
		}
	}
}
	
