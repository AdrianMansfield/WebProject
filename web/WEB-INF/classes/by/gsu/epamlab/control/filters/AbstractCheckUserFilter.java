package by.gsu.epamlab.control.filters;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractCheckUserFilter extends AbstractFilter {
    protected void checkAttributesFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain, String url)
            throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String login = request.getParameter(ParameterConstants.LOGIN_PARAMETER);

        String password = request.getParameter(ParameterConstants.PASSWORD_PARAMETER);

        if(badValues(login, password)) {

            jumpError(url, Constants.EMPTY_DATA, request, response);

        }
        else {

            filterChain.doFilter(servletRequest, servletResponse);

        }
    }
}
