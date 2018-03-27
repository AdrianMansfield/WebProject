package by.gsu.epamlab.control.get;

import by.gsu.epamlab.model.JsonOperations;
import by.gsu.epamlab.model.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrintTaskServlet extends AbstractNonPostController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {

            String connectionType = request.getParameter(ParameterConstants.FROM_PARAMETER);
            HttpSession session = request.getSession();
            String taskType = request.getParameter(ParameterConstants.TASK_TYPE_PARAMETER);

            //That check needs for version without js
            if(taskType == null) {
                taskType = (String) session.getAttribute(ParameterConstants.TASK_TYPE_PARAMETER);
            }

            taskType = taskType.toUpperCase();
            String userId = (String)session.getAttribute(ParameterConstants.USER_ID_PARAMETER);
            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();
            List<Task> taskList = iTaskDAO.getTasks(userId, taskType);

            if(ParameterConstants.AJAX_PARAMETER.equals(connectionType)) {
                JSONObject <String, Object> jsonObject = new JSONObject<>();

                jsonObject.put(ParameterConstants.TASK_LIST_NAME_PARAMETER, JsonOperations.getJsonArray(taskList));

                jsonObject.put(ParameterConstants.TASK_TYPE_PARAMETER, taskType);
                response.setContentType(Constants.JSON_CONTENT_TYPE);
                response.setCharacterEncoding(Constants.UTF8_CHARACTER_ENCODING);
                response.getWriter().write(jsonObject.toJSONString());
            }
            else {
                request.getRequestDispatcher(UrlConstants.MAIN_URL).forward(request, response);
            }


        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


}
