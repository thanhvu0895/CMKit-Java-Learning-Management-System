//package codingmentor.javabackend.k3.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//import codingmentor.javabackend.k3.Utils.UrlUtils;
//
//@WebFilter(urlPatterns = {UrlUtils.ALL})
//public class AuthFilter implements Filter{
//   /*
//    process before the request get in servlet
//    chain.doFilter(request,response)
//    process response from servlet
//   */
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		
//		if (!(request instanceof HttpServletRequest)) {
//            throw new ServletException("Can only process HttpServletRequest");
//        }
//
//        if (!(response instanceof HttpServletResponse)) {
//            throw new ServletException("Can only process HttpServletResponse");
//        }
//		
//        HttpServletRequest req = (HttpServletRequest) request;
//	    HttpServletResponse resp = (HttpServletResponse) response;
//	    
//	    if (isInSession(req) && isLoginPage(req)) {
//			resp.sendRedirect("./");
//			return;
//		}
//	    
//		if (isInSession(req) || isLoginPage(req) || isResourceRequest(req)) {
//			if (!isResourceRequest(req)) { // Prevent restricted pages from being cached.
//				resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//				resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//				resp.setDateHeader("Expires", 0); // Proxies.
//			}
//
//			chain.doFilter(request, response);
//		} else {
//			// If not at home page nor logged in, send to /login
//			resp.sendRedirect(req.getContextPath() + UrlUtils.SIGN_IN);
//			
//			// we return here so the original servlet is not processed
//			return;
//		}
//	}	
//	
//	private boolean isLoginPage(HttpServletRequest request) { // if url = /login
//        String path = request.getServletPath();
//        return path.startsWith(UrlUtils.SIGN_IN) || path.startsWith(UrlUtils.REQUEST_PASSWORD_RESET);
//    }
//	
//	private boolean isInSession (HttpServletRequest req) { // if no user logged in yet
//		return req.getSession().getAttribute("current_user") != null;
//	}
//	
//	private boolean isResourceRequest(HttpServletRequest request) { // if request to /assets
//        String path = request.getRequestURI();
//        return path.startsWith(request.getContextPath() + "/assets");
//    }
//}
