package by.gsu.epamlab.control.post.task;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.control.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ChangeTaskInfoServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String connectionType = request.getParameter(ParameterConstants.FROM_PARAMETER);
            String taskId = request.getParameter(ParameterConstants.TASK_ID_PARAMETER);
            String taskAttribute = request.getParameter(ParameterConstants.TASK_ATTRIBUTE_PARAMETER).trim();
            String infoType = request.getParameter(ParameterConstants.INFO_TYPE).toUpperCase();
            if (infoType.equals("DATE")) {
                long time = new SimpleDateFormat(Constants.PRINT_DATE_FORMAT).parse(taskAttribute).getTime();
                Date date = new Date(time);
                ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();
                iTaskDAO.changeTaskInfo(taskId, infoType, date.toString());
                sendRedirectToPrintTaskServlet(request, response);
            } else {
                ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();
                iTaskDAO.changeTaskInfo(taskId, infoType, taskAttribute);
                sendRedirectToPrintTaskServlet(request, response);
            }
        } catch (DaoException | ParseException e) {
            e.printStackTrace();
        }
    }
}
