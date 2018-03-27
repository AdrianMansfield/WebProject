package by.gsu.epamlab.control.post.task;

import by.gsu.epamlab.model.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ErrorConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.control.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class AddTaskServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String connectionType = request.getParameter(ParameterConstants.FROM_PARAMETER);

            String taskType = request.getParameter(ParameterConstants.TASK_TYPE_PARAMETER);

            taskType = taskType.toUpperCase();

            String userId = (String) request.getSession().getAttribute(ParameterConstants.USER_ID_PARAMETER);

            String taskName = request.getParameter(ParameterConstants.TASK_NAME_PARAMETER);

            String description = request.getParameter(ParameterConstants.DESCRIPTION_PARAMETER);

            Date date = (Date) request.getAttribute(ParameterConstants.DATE_PARAMETER);

            String fileName = Constants.NO_FILE;

            Task task = new Task(Constants.ZERO, taskName, description, date, fileName);

            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();

            boolean isAdded = iTaskDAO.addTask(userId, task, taskType);

            if(!isAdded) {
                jumpError(UrlConstants.MAIN_URL, ErrorConstants.DUPLICATE_TASK_NAME_ERROR, request, response);
                return;
            }

            if(ParameterConstants.AJAX_PARAMETER.equals(connectionType)) {
                task = iTaskDAO.getTaskByName(userId, taskName);
                JSONObject <String, JSONObject> jsonObject = new JSONObject<>();
                jsonObject.put("task", task.toJson());
                JSONObject<String, String> jsonObjectString = new JSONObject<>();
                jsonObjectString.put(ParameterConstants.TASK_TYPE_PARAMETER, taskType);
                jsonObject.put(ParameterConstants.TASK_TYPE_PARAMETER, jsonObjectString);
                response.getWriter().write(jsonObject.toJSONString());
            }
            else {
                sendRedirectToPrintTaskServlet(request, response);
            }

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }



}
