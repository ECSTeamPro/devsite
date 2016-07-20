package vn.ecs.team.devsite.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import vn.ecs.team.model.User;

@WebFilter(filterName="requestFilter")
public class RequestFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Inside filter one.");
		User u = (User) request.getServletContext().getAttribute("user");
		if(u == null){
			request.getRequestDispatcher("/index.html").forward(request, response);
			chain.doFilter(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
	}
} 