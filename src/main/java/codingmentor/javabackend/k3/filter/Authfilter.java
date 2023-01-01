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

@WebFilter(urlPatterns = {UrlUtils.ALL})
public class Authfilter implements Filter{
   /*
    process before the request get in servlet
    chain.doFilter(request,response)
    process response from servlet
   */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse resp = (HttpServletResponse) response;
		
		if (isLoginRequest(req) || isLoggedIn(req) || isResourceRequest(req)) {
			if (!isResourceRequest(req)) { // Prevent restricted pages from being cached.
				resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
				resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				resp.setDateHeader("Expires", 0); // Proxies.
			}
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + UrlUtils.SIGN_IN);
		}
	}	
	
	private boolean isLoginRequest(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith(UrlUtils.SIGN_IN);
    }
	
	private boolean isLoggedIn (HttpServletRequest req) {
		return req.getSession().getAttribute("LOGIN_USER") != null;
	}
	
	private boolean isResourceRequest(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith(request.getContextPath() + "/assets");
    }
}
