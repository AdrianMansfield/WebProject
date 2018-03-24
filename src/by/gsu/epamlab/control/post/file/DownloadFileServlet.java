package by.gsu.epamlab.control.post.file;

import by.gsu.epamlab.model.FileOperations;
import by.gsu.epamlab.model.task.Task;
import by.gsu.epamlab.constants.*;
import by.gsu.epamlab.control.post.AbstractNonGetController;

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

            boolean isExist = FileOperations.downloadFile(response, userLogin, task);

            if(isExist) {
                jumpPage(UrlConstants.MAIN_URL, request, response);
            }
            else {
                jumpError(UrlConstants.MAIN_URL, ErrorConstants.FILE_DOES_NOT_EXIST_ERROR, request, response);
            }
        }
        catch (IOException e) {

            e.printStackTrace();

        }

    }
}
