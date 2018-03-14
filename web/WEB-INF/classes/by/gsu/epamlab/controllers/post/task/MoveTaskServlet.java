package by.gsu.epamlab.controllers.post.task;


import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.factories.TaskDAOFactory;
import by.gsu.epamlab.implementations.database.task.enums.LocationChangeTypes;
import by.gsu.epamlab.controllers.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;
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

            String locationType = request.getParameter(ParameterConstants.LOCATION_TYPE_PARAMETER);

            locationType = locationType.toUpperCase();

            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();

            iTaskDAO.moveTask(taskId, locationType);

            if(ParameterConstants.AJAX_PARAMETER.equals(connectionType)) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put(ParameterConstants.TASK_ID_PARAMETER,taskId);

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
