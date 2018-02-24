package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.FileConstants;
import by.gsu.epamlab.controllers.enums.TaskTypes;
import by.gsu.epamlab.exceptions.DaoException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainServlet extends AbstractBaseController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {

            HttpSession session = request.getSession();
            session.removeAttribute(Constants.TASK_LIST_NAME);
            session.removeAttribute(Constants.FILE_MAP_PARAMETER);
            String date = request.getParameter(Constants.DATE_TYPE_PARAMETER);
            if(date == null) {
                date = "TODAY"; // CORRECT
            }
            date = date.toUpperCase();
            String id = (String)session.getAttribute(Constants.ID);
            List<Task> taskList = TaskTypes.valueOf(date).getTasks(id);
            session.setAttribute(Constants.TASK_LIST_NAME, taskList);
            session.setAttribute(Constants.FILE_MAP_PARAMETER, getFileForTask(request));
            response.sendRedirect(Constants.MAIN_URL);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getFileForTask(HttpServletRequest request) {
        String login = (String) request.getSession().getAttribute(Constants.LOGIN);
        Map<String, String> fileMap = new HashMap<>();
        File directory = new File(Constants.FILES_DIRECTORY + File.separator + login);
        if(directory.isDirectory()) {
            for(File file : directory.listFiles()) {
                String [] fileNameParts = file.getName().split(FileConstants.FILE_DELIMITER);
                String taskName = fileNameParts[FileConstants.TASK_INDEX];
                String fileName = fileNameParts[FileConstants.FILE_NAME_INDEX];
                fileMap.put(taskName, fileName);
            }
        }
        return fileMap;
    }
}
