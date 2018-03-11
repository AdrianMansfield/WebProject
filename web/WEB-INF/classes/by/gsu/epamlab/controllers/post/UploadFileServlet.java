package by.gsu.epamlab.controllers.post;

import by.gsu.epamlab.beans.FileOperations;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

public class UploadFileServlet extends AbstractNonGetController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session =  request.getSession();

            String userId = (String) session.getAttribute(Constants.ID);

            String userLogin = (String) session.getAttribute(Constants.LOGIN);

            String taskName = request.getParameter(Constants.TASK_NAME_PARAMETER);

            Part part = request.getPart(Constants.FILE_PARAMETER);

            String fileName = FileOperations.uploadFile(part, userLogin, taskName);

            ITaskDAO iTaskDAO = new TaskDatabaseImplementation();

            iTaskDAO.updateFileName(userId, fileName, taskName);

            jumpPage(Constants.PRINT_TASK_SERVLET_URL, request, response);

        } catch (DaoException e) {

            e.printStackTrace();

        }
    }
}
