package by.gsu.epamlab.controllers.post.file;

import by.gsu.epamlab.beans.FileOperations;
import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.FileConstants;
import by.gsu.epamlab.constants.ParameterConstants;
import by.gsu.epamlab.controllers.post.AbstractNonGetController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class DownloadFileServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String taskName = request.getParameter(ParameterConstants.TASK_NAMES_PARAMETER);

            String fileName = request.getParameter(ParameterConstants.FILE_NAMES_PARAMETER);

            Task task = new Task(Constants.ZERO, taskName, Constants.EMPTY_STRING, new Date(Constants.ZERO), fileName);

            String userLogin = (String) request.getSession().getAttribute(ParameterConstants.LOGIN_PARAMETER);

            request.setCharacterEncoding(Constants.UTF8_CHARACTER_ENCODING);

            response.setCharacterEncoding(Constants.UTF8_CHARACTER_ENCODING);

            FileOperations.downloadFile(response, userLogin, task);
        }
        catch (IOException e) {

            e.printStackTrace();

        }

    }
}
