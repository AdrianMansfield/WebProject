package by.gsu.epamlab.controllers.post;

import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.controllers.enums.TaskAddTypes;
import by.gsu.epamlab.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddTaskServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String taskType = request.getParameter(Constants.TASK_TYPE);
            String userId = (String) request.getSession().getAttribute(Constants.ID);
            String taskName = request.getParameter(Constants.TASK_NAME_PARAMETER);
            String description = request.getParameter(Constants.DESCRIPTION_PARAMETER);
            String date = request.getParameter(Constants.DATE_PARAMETER);
            String fileName = "No file";
            if(!goodValues(userId, taskName, description)) {
                jumpError(Constants.MAIN_URL, Constants.ADD_TASK_ERROR_MESSAGE, request, response);
                return;
            }
            Date usualDate = null;
            if(date != null && !Constants.EMPTY_STRING.equals(date)) {
                usualDate = new Date(new SimpleDateFormat(Constants.PRINT_DATE_FORMAT)
                        .parse(date).getTime());
            }
            Task task = new Task(Constants.ZERO, taskName, description, usualDate, fileName);
            TaskAddTypes.valueOf(taskType.toUpperCase()).addTask(userId, task);
            jumpPage("/UploadFileServlet", request, response);
        } catch (DaoException | ParseException e) {
            e.printStackTrace();
        }
    }



}
