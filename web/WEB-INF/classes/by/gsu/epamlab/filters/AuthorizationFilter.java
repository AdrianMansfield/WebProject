package by.gsu.epamlab.filters;

import by.gsu.epamlab.constants.ErrorConstants;
import by.gsu.epamlab.model.user.Role;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthorizationFilter extends AbstractFilter {


    @Override
    public void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String role = (String) request.getSession().getAttribute(ParameterConstants.ROLE_PARAMETER);
        if(!Role.USER.toString().equals(role) && !Role.ADMIN.toString().equals(role)) {
            jumpError(UrlConstants.INDEX_URL, ErrorConstants.AUTHORISATION_ERROR, request, response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
