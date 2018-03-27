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

            String connectionType = request.getParameter("from");

            HttpSession session =  request.getSession();

            String userId = (String) session.getAttribute(ParameterConstants.USER_ID_PARAMETER);

            String userLogin = (String) session.getAttribute(ParameterConstants.LOGIN_PARAMETER);

            String taskName = request.getParameter(ParameterConstants.TASK_NAME_PARAMETER);

            Part part = request.getPart(ParameterConstants.FILE_PARAMETER);

            String fileName = FileOperations.uploadFile(part, userLogin, taskName);

            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();

            Task task = new Task(Constants.ZERO, taskName, Constants.EMPTY_STRING, new Date(Constants.ZERO), fileName);

            iTaskDAO.updateFileName(userId, task);

            if(ParameterConstants.AJAX_PARAMETER.equals(connectionType)) {
                JSONObject jsonObject = new JSONObject();
                String taskId = request.getParameter(ParameterConstants.TASK_ID_PARAMETER);//-----------------
                jsonObject.put("taskId", taskId);
                jsonObject.put("taskName", taskName);
                jsonObject.put("newFileName", fileName);
                jsonObject.put("oldFileName", Constants.NO_FILE);
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
