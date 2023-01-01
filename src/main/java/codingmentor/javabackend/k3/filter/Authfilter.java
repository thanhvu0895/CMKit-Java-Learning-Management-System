package codingmentor.javabackend.k3.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import codingmentor.javabackend.k3.Utils.UrlUtils;

@WebFilter(urlPatterns = "/*")
public class Authfilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) request;
		
		req.setAttribute("origin", req.getRequestURI());
		
		if(!req.getRequestURI().
				contains("login") && req.getSession(false) == null) {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			req.getRequestDispatcher(UrlUtils.SIGN_IN).forward(req, resp);
			return;
		}
		chain.doFilter(req, resp);
	}	
}
