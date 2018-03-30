package by.gsu.epamlab.control.servlets.post.file;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.FileOperations;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.control.servlets.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import by.gsu.epamlab.model.task.Task;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
public class UploadFileServlet extends AbstractNonGetController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String connectionType = request.getParameter(ParameterConstants.FROM_PARAMETER);

            HttpSession session =  request.getSession();

            String userId = (String) session.getAttribute(ParameterConstants.USER_ID_PARAMETER);

            String userLogin = (String) session.getAttribute(ParameterConstants.LOGIN_PARAMETER);

            String taskId = request.getParameter(ParameterConstants.TASK_ID_PARAMETER);

            String taskName = request.getParameter(ParameterConstants.TASK_NAME_PARAMETER);

            Part part = request.getPart(ParameterConstants.FILE_PARAMETER);

            String fileName = FileOperations.uploadFile(part, userLogin, taskName);

            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();

            Task task = new Task(Constants.ZERO, taskName, Constants.EMPTY_STRING, new Date(Constants.ZERO), fileName);

            iTaskDAO.updateFileName(userId, task);

            if(ParameterConstants.AJAX_PARAMETER.equals(connectionType)) {

                JSONObject <String, String> jsonObject = new JSONObject<>();

                jsonObject.put(ParameterConstants.TASK_ID_PARAMETER, taskId);

                jsonObject.put(ParameterConstants.TASK_NAME_PARAMETER, taskName);

                jsonObject.put(ParameterConstants.NEW_FILE_NAME, fileName);

                jsonObject.put(ParameterConstants.OLD_FILE_NAME, Constants.NO_FILE);

                response.getWriter().write(jsonObject.toJSONString());

            }
            else {

                sendRedirectToPrintTaskServlet(request, response);

            }


        } catch (DaoException e) {

            throw new ServletException(e);

        }

    }
}
