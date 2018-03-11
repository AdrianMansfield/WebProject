package by.gsu.epamlab.controllers.post;

import by.gsu.epamlab.beans.FileOperations;
import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.FileConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class DownloadFileServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String taskName = request.getParameter("taskNames");

            String fileName = request.getParameter("fileNames");

            Task task = new Task(0, taskName, Constants.EMPTY_STRING, new Date(0), fileName);

            String userLogin = (String) request.getSession().getAttribute(Constants.LOGIN);

            request.setCharacterEncoding(FileConstants.UTF8_CHARACTER_ENCODING);

            response.setCharacterEncoding(FileConstants.UTF8_CHARACTER_ENCODING);

            FileOperations.downloadFile(response, userLogin, task);
        }
        catch (IOException e) {

            e.printStackTrace();

        }

    }
}
