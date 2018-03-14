package by.gsu.epamlab.controllers.post.file;

import by.gsu.epamlab.beans.FileOperations;
import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.constants.UrlConstants;
import by.gsu.epamlab.controllers.post.AbstractNonGetController;
import by.gsu.epamlab.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DeleteFileServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String userId = (String) request.getSession().getAttribute(ParameterConstants.USER_ID_PARAMETER);

            String [] taskNames = request.getParameterValues(ParameterConstants.TASK_NAMES_PARAMETER);

            String [] fileNames = request.getParameterValues(ParameterConstants.FILE_NAMES_PARAMETER);

            String userLogin = (String) request.getSession().getAttribute(ParameterConstants.LOGIN_PARAMETER);

            FileOperations.deleteFile(userId, taskNames, fileNames, userLogin);

            sendRedirectToPrintTaskServlet(request, response);


        } catch (DaoException e) {

            e.printStackTrace();

        }
    }

}
