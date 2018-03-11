package by.gsu.epamlab.controllers.post;

import by.gsu.epamlab.beans.FileOperations;
import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
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
            String userId = (String) request.getSession().getAttribute(Constants.ID);


            String [] taskNames = request.getParameterValues("taskNames");

            String [] fileNames = request.getParameterValues("fileNames");

            String userLogin = (String) request.getSession().getAttribute(Constants.LOGIN);

            List<Task> taskList = getTaskList(taskNames, fileNames);

            FileOperations.deleteFile(userId, taskList, userLogin);

            jumpPage(Constants.PRINT_TASK_SERVLET_URL, request, response);

        } catch (DaoException e) {

            e.printStackTrace();

        }
    }

    private List<Task> getTaskList(String [] taskNames, String [] fileNames) {
        List<Task> taskList = new ArrayList<>();
        for(int i = 0; i<taskNames.length; i++) {
            Task task = new Task( 0, taskNames[i], Constants.EMPTY_STRING, new Date(0), fileNames[i]);
            taskList.add(task);
        }
        return taskList;
    }
}
