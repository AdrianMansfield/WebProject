package by.gsu.epamlab.control.filters.task;

import by.gsu.epamlab.constants.ErrorConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.control.filters.AbstractFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeTaskFilter extends AbstractFilter {

    @Override
    protected void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String taskAttribute = request.getParameter(ParameterConstants.TASK_ATTRIBUTE_PARAMETER).trim();

        if(badValues(taskAttribute)) {
            jumpError(UrlConstants.MAIN_URL, ErrorConstants.ADD_TASK_ERROR, request, response);
            return;
        }

        request.getRequestDispatcher(UrlConstants.CHANGE_TASK_SERVLET);
    }

}
