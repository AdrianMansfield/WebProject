package by.gsu.epamlab.control.post.file;

import by.gsu.epamlab.model.FileOperations;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.control.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
public class UploadFileServlet extends AbstractNonGetController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            HttpSession session =  request.getSession();

            String userId = (String) session.getAttribute(ParameterConstants.USER_ID_PARAMETER);

            String userLogin = (String) session.getAttribute(ParameterConstants.LOGIN_PARAMETER);

            String taskName = request.getParameter(ParameterConstants.TASK_NAME_PARAMETER);

            Part part = request.getPart(ParameterConstants.FILE_PARAMETER);

            String fileName = FileOperations.uploadFile(part, userLogin, taskName);

            ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();

            iTaskDAO.updateFileName(userId, fileName, taskName);

            sendRedirectToPrintTaskServlet(request, response);

        } catch (DaoException e) {

            e.printStackTrace();

        }



    }
}
