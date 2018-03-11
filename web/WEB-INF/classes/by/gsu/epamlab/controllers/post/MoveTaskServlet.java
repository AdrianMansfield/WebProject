package by.gsu.epamlab.controllers.post;


import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.controllers.enums.TaskLocationTypes;
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
            String connectionType = request.getParameter("from");
            String  taskId = request.getParameter("taskId");
            String typeLocation = request.getParameter(Constants.LOCATION_TYPE_PARAMETER);
            TaskLocationTypes.valueOf(typeLocation.toUpperCase()).moveTask(taskId);
            if("ajax".equals(connectionType)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("taskId",taskId);
                response.getWriter().write(jsonObject.toJSONString());
            }
            else {
                jumpPage(Constants.PRINT_TASK_SERVLET_URL, request, response);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }
}
