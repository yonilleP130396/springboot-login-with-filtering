package test.mydemo.Filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if user is authenticated (you can change this condition based on your authentication logic)
        boolean isAuthenticated = httpRequest.getSession().getAttribute("user") != null;

        // Allow login and static resources
        String path = httpRequest.getRequestURI();
        if (isAuthenticated || path.equals("/login") || path.startsWith("/css/") || path.startsWith("/js/")) {
            chain.doFilter(request, response); // Continue to the requested resource
        } else {
            httpResponse.sendRedirect("/login"); // Redirect to login page
        }
    }
    @Override
    public void destroy() {
        // Cleanup logic, if needed
    }

}


