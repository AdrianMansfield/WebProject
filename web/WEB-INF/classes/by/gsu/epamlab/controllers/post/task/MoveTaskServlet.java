package by.gsu.epamlab.controllers.post.task;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.controllers.enums.TaskLocationTypes;
import by.gsu.epamlab.controllers.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import org.json.simple.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MoveTaskServlet extends AbstractNonGetController {
    
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String connectionType = request.getParameter(ParameterConstants.FROM_PARAMETER);

            String  taskId = request.getParameter(ParameterConstants.TASK_ID_PARAMETER);

            String typeLocation = request.getParameter(ParameterConstants.LOCATION_TYPE_PARAMETER);

            TaskLocationTypes.valueOf(typeLocation.toUpperCase()).moveTask(taskId);

            if(ParameterConstants.AJAX_PARAMETER.equals(connectionType)) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put(ParameterConstants.TASK_ID_PARAMETER,taskId);

                response.getWriter().write(jsonObject.toJSONString());

            }
            else {

                jumpPage(UrlConstants.PRINT_TASK_SERVLET_URL, request, response);

            }
        } catch (DaoException e) {

            e.printStackTrace();

        }

    }
}
