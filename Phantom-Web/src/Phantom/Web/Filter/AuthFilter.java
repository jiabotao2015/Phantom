package Phantom.Web.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {
	
	private static final String Login_URI = "/Login";
	private static final String Resource_URI = ".";
	private static final String KAPTCHA_URI = "Kaptcha";

	@Override
	public void destroy() {
	}

	/**
	 * doFilter 应该放过css  js  jpg 等静态资源  以及 websocket
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;  
        HttpServletResponse res = (HttpServletResponse)response;
		//获取URI
       // String URI = req.getRequestURI();
		String requestURI = req.getRequestURI().substring(req.getRequestURI().indexOf("/",1),req.getRequestURI().length()); 
		if(requestURI.contains(Resource_URI)||requestURI.contains(Login_URI)||requestURI.contains(KAPTCHA_URI)){
			chain.doFilter(req, res); 
		}else{
			//取session
			Object obj = req.getSession().getAttribute("user");
			if(obj==null){
				res.sendRedirect(req.getContextPath() + "/Login");
			}else{
				chain.doFilter(req, res); 
			}
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
