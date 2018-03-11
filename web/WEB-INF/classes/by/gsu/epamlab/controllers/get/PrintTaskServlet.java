package by.gsu.epamlab.controllers.get;

import by.gsu.epamlab.beans.JsonOperations;
import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
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

            String connectionType = request.getParameter("from");
            HttpSession session = request.getSession();
            String taskType = request.getParameter(Constants.TASK_TYPE);
            taskType = taskType.toUpperCase();
            String id = (String)session.getAttribute(Constants.ID);
            List<Task> taskList = TaskPrintTypes.valueOf(taskType).getTasks(id);

            if("ajax".equals(connectionType)) {
                JSONArray jsonArrayTaskList = JsonOperations.getJsonArray(taskList);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("taskList", jsonArrayTaskList);
                jsonObject.put(Constants.TASK_TYPE, taskType);
                session.setAttribute(Constants.TASK_LIST_NAME, new ArrayList<>());
                response.setContentType("application/x-json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(jsonObject.toJSONString());
            }
            else {
                session.setAttribute(Constants.TASK_LIST_NAME, taskList);
                session.setAttribute(Constants.TASK_TYPE, taskType);
                response.sendRedirect(Constants.MAIN_URL);
            }


        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


}
