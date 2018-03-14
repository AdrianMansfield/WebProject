package by.gsu.epamlab.controllers.post.task;

import by.gsu.epamlab.model.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ErrorConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.factories.TaskDAOFactory;
import by.gsu.epamlab.controllers.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;

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

            taskType = taskType.toUpperCase();

            String userId = (String) request.getSession().getAttribute(ParameterConstants.USER_ID_PARAMETER);

            String taskName = request.getParameter(ParameterConstants.TASK_NAME_PARAMETER);

            String description = request.getParameter(ParameterConstants.DESCRIPTION_PARAMETER);

            String date = request.getParameter(ParameterConstants.DATE_PARAMETER);

            String fileName = Constants.NO_FILE;

            if(!goodValues(userId, taskName, description)) {
                jumpError(UrlConstants.MAIN_URL, ErrorConstants.ADD_TASK_ERROR, request, response);
                return;
            }

            if(Constants.EMPTY_STRING.equals(date) && Constants.SOMEDAY.equals(taskType)) {
                jumpError(UrlConstants.MAIN_URL, ErrorConstants.EMPTY_DATE_ERROR, request, response);
                return;
            }

            Date usualDate = null;

            if(!Constants.EMPTY_STRING.equals(date)) {
                long time = new SimpleDateFormat(Constants.PRINT_DATE_FORMAT).parse(date).getTime();
                usualDate = new Date(time);
            }

            Task task = new Task(Constants.ZERO, taskName, description, usualDate, fileName);

            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();

            boolean isAdded = iTaskDAO.addTask(userId, task, taskType);

            if(!isAdded) {
                jumpError(UrlConstants.MAIN_URL, ErrorConstants.DUPLICATE_TASK_NAME_ERROR, request, response);
                return;
            }

            sendRedirectToPrintTaskServlet(request, response);

        } catch (DaoException | ParseException e) {
            e.printStackTrace();
        }
    }



}
