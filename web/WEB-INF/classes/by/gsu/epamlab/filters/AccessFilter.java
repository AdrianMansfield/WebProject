package by.gsu.epamlab.filters;

import by.gsu.epamlab.model.user.Role;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AccessFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String role = (String) request.getSession().getAttribute(ParameterConstants.ROLE_PARAMETER);
        if(!Role.USER.toString().equals(role) && !Role.ADMIN.toString().equals(role)) {
            response.sendRedirect(UrlConstants.REGISTRATION_URL);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
