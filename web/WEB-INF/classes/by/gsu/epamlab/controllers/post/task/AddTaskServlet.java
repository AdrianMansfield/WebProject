package by.gsu.epamlab.controllers.post.task;

import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.controllers.enums.TaskAddTypes;
import by.gsu.epamlab.controllers.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddTaskServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String taskType = request.getParameter(ParameterConstants.TASK_TYPE_PARAMETER);
            String userId = (String) request.getSession().getAttribute(ParameterConstants.USER_ID_PARAMETER);
            String taskName = request.getParameter(ParameterConstants.TASK_NAME_PARAMETER);
            String description = request.getParameter(ParameterConstants.DESCRIPTION_PARAMETER);
            String date = request.getParameter(ParameterConstants.DATE_PARAMETER);
            String fileName = Constants.NO_FILE;
            if(!goodValues(userId, taskName, description)) {
                jumpError(UrlConstants.MAIN_URL, Constants.ADD_TASK_ERROR_MESSAGE, request, response);
                return;
            }
            Date usualDate = null;
            if(date != null && !Constants.EMPTY_STRING.equals(date)) {
                usualDate = new Date(new SimpleDateFormat(Constants.PRINT_DATE_FORMAT)
                        .parse(date).getTime());
            }
            Task task = new Task(Constants.ZERO, taskName, description, usualDate, fileName);
            if(!TaskAddTypes.valueOf(taskType.toUpperCase()).addTask(userId, task)) {
                jumpError(UrlConstants.MAIN_URL, Constants.DUPLICATE_TASK_NAME_ERROR_MESSAGE, request, response);
                return;
            }
            jumpPage(UrlConstants.UPLOAD_SERVLET_URL, request, response);
        } catch (DaoException | ParseException e) {
            e.printStackTrace();
        }
    }



}
