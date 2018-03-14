package by.gsu.epamlab.filters;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ErrorConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.model.DateOperations;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddTaskFilter extends AbstractFilter {

    @Override
    public void filter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {

            HttpServletRequest request = (HttpServletRequest) servletRequest;

            HttpServletResponse response = (HttpServletResponse) servletResponse;

            String taskType = request.getParameter(ParameterConstants.TASK_TYPE_PARAMETER);

            taskType = taskType.toUpperCase();

            String userId = (String) request.getSession().getAttribute(ParameterConstants.USER_ID_PARAMETER);

            String taskName = request.getParameter(ParameterConstants.TASK_NAME_PARAMETER);

            String description = request.getParameter(ParameterConstants.DESCRIPTION_PARAMETER);

            String stringDate = request.getParameter(ParameterConstants.DATE_PARAMETER);

            if(badValues(userId, taskName, description)) {
                jumpError(UrlConstants.MAIN_URL, ErrorConstants.ADD_TASK_ERROR, request, response);
                return;
            }

            boolean isSomeday = Constants.SOMEDAY.equals(taskType);

            if(Constants.EMPTY_STRING.equals(stringDate) && isSomeday) {
                jumpError(UrlConstants.MAIN_URL, ErrorConstants.EMPTY_DATE_ERROR, request, response);
                return;
            }

            if(!Constants.EMPTY_STRING.equals(stringDate) && isSomeday) {

                long time = new SimpleDateFormat(Constants.PRINT_DATE_FORMAT).parse(stringDate).getTime();

                Date date = new Date(time);

                Date tomorrow = DateOperations.getTomorrowDay();

                if(date.after(tomorrow)) {

                    request.setAttribute(ParameterConstants.DATE_PARAMETER, date);

                }
                else {

                    jumpError(UrlConstants.MAIN_URL, ErrorConstants.WRONG_DATE_ERROR, request, response);
                    return;

                }
            }

            filterChain.doFilter(servletRequest, servletResponse);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



}
