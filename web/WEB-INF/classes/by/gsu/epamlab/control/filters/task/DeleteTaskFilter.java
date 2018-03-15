package by.gsu.epamlab.control.filters.task;

import by.gsu.epamlab.constants.Constants;
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

public class DeleteTaskFilter extends AbstractFilter {

    @Override
    protected void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String [] taskIds = request.getParameterValues(ParameterConstants.TASK_IDS_PARAMETER);

        if(taskIds == null || taskIds.length == Constants.ZERO) {
            jumpError(UrlConstants.MAIN_URL, ErrorConstants.DID_NOT_CHOOSE_TASK_ERROR, request, response);
        }

        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

}
