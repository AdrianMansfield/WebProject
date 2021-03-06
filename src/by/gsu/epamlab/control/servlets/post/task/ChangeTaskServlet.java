package by.gsu.epamlab.control.servlets.post.task;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.control.servlets.post.AbstractNonGetController;
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

public class ChangeTaskServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String connectionType = request.getParameter(ParameterConstants.FROM_PARAMETER);

            String taskId = request.getParameter(ParameterConstants.TASK_ID_PARAMETER);

            String taskAttribute = request.getParameter(ParameterConstants.TASK_ATTRIBUTE_PARAMETER).trim();

            String infoType = request.getParameter(ParameterConstants.INFO_TYPE).toUpperCase();

            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();

            if (infoType.equals(ParameterConstants.DATE_PARAMETER.toUpperCase())) {

                long time = new SimpleDateFormat(Constants.PRINT_DATE_FORMAT).parse(taskAttribute).getTime();

                Date date = new Date(time);

                iTaskDAO.changeTaskInfo(taskId, infoType, date.toString());

                taskAttribute = date.toString();

            } else {

                iTaskDAO.changeTaskInfo(taskId, infoType, taskAttribute);

            }

            if(ParameterConstants.AJAX_PARAMETER.equals(connectionType)) {
                JSONObject<String, String> jsonObject = new JSONObject<>();

                jsonObject.put(ParameterConstants.INFO_TYPE, infoType);

                jsonObject.put(ParameterConstants.TASK_ATTRIBUTE_PARAMETER, taskAttribute);

                jsonObject.put(ParameterConstants.TASK_ID_PARAMETER, taskId);

                response.getWriter().write(jsonObject.toJSONString());

            }
            else {

                sendRedirectToPrintTaskServlet(request, response);

            }

        } catch (DaoException | ParseException e) {

            throw new ServletException(e);

        }
    }
}
