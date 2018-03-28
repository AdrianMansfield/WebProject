package by.gsu.epamlab.control.filters;

import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.exceptions.DaoException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter extends AbstractFilter {
    @Override
    protected void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {

            filterChain.doFilter(servletRequest, servletResponse);

        }

        catch (ServletException e) {

            HttpServletRequest request = (HttpServletRequest) servletRequest;

            HttpServletResponse response = (HttpServletResponse) servletResponse;

            jumpError(UrlConstants.ERROR_PAGE, e.getMessage(), request, response);

        }
    }
}
