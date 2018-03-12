package by.gsu.epamlab.controllers.get;

import by.gsu.epamlab.beans.JsonOperations;
import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.controllers.AbstractBaseController;
import by.gsu.epamlab.controllers.enums.TaskPrintTypes;
import by.gsu.epamlab.exceptions.DaoException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintTaskServlet extends AbstractBaseController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {

            String connectionType = request.getParameter(ParameterConstants.FROM_PARAMETER);
            HttpSession session = request.getSession();
            String taskType = request.getParameter(ParameterConstants.TASK_TYPE_PARAMETER);
            taskType = taskType.toUpperCase();
            String id = (String)session.getAttribute(ParameterConstants.USER_ID_PARAMETER);
            List<Task> taskList = TaskPrintTypes.valueOf(taskType).getTasks(id);

            if(ParameterConstants.AJAX_PARAMETER.equals(connectionType)) {
                JSONArray jsonArrayTaskList = JsonOperations.getJsonArray(taskList);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(ParameterConstants.TASK_LIST_NAME_PARAMETER, jsonArrayTaskList);
                jsonObject.put(ParameterConstants.TASK_TYPE_PARAMETER, taskType);
                session.setAttribute(ParameterConstants.TASK_LIST_NAME_PARAMETER, new ArrayList<>());
                response.setContentType(Constants.JSON_CONTENT_TYPE);
                response.setCharacterEncoding(Constants.UTF8_CHARACTER_ENCODING);
                response.getWriter().write(jsonObject.toJSONString());
            }
            else {
                session.setAttribute(ParameterConstants.TASK_LIST_NAME_PARAMETER, taskList);
                session.setAttribute(ParameterConstants.TASK_TYPE_PARAMETER, taskType);
                response.sendRedirect(UrlConstants.MAIN_URL);
            }


        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


}
