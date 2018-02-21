package by.gsu.epamlab.controllers.filters;

import by.gsu.epamlab.constants.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.getRequestDispatcher(Constants.MAIN_SERVLET_URL).forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
