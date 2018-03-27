package by.gsu.epamlab.control.post.file;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.FileOperations;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.control.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import by.gsu.epamlab.model.task.Task;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteFileServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {

            String connectionType = request.getParameter(ParameterConstants.FROM_PARAMETER);

            String userId = (String) request.getSession().getAttribute(ParameterConstants.USER_ID_PARAMETER);

            String taskId = request.getParameter(ParameterConstants.TASK_ID_PARAMETER);

            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();

            Task task =  iTaskDAO.getTaskById(userId, taskId);

            String userLogin = (String) request.getSession().getAttribute(ParameterConstants.LOGIN_PARAMETER);

            FileOperations.deleteFile(task, userLogin);

            String oldFileName = task.getFileName();

            task.setFileName(Constants.NO_FILE);

            iTaskDAO.updateFileName(userId, task);

            if(ParameterConstants.AJAX_PARAMETER.equals(connectionType)) {
                JSONObject <String, String> jsonObject = new JSONObject<>();
                jsonObject.put("taskId", taskId);
                jsonObject.put("taskName", task.getName());
                jsonObject.put("newFileName", Constants.NO_FILE);
                jsonObject.put("oldFileName", oldFileName);
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
