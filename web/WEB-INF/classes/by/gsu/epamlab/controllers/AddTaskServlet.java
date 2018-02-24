package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.FileConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddTaskServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = (String) request.getSession().getAttribute(Constants.ID);
            String taskName = request.getParameter(Constants.TASK_NAME_PARAMETER);
            String department = request.getParameter(Constants.DEPARTMENT_PARAMETER);
            String date = request.getParameter(Constants.DATE_PARAMETER);
            Part part = request.getPart(Constants.FILE_PARAMETER); //Correct

            if(!"".equals(extractFileName(part))) {
                uploadFile(request, taskName);
            }
            Date trueDate = new Date(new SimpleDateFormat(Constants.PRINT_DATE_FORMAT)
                        .parse(date).getTime());

            Task task = new Task(0, taskName, department, trueDate);
            ITaskDAO iTaskDAO = new TaskDatabaseImplementation();
            iTaskDAO.addTask(userId, task);
            request.getRequestDispatcher(Constants.MAIN_SERVLET_URL).forward(request, response);
        } catch (DaoException | ParseException e) {
            e.printStackTrace();
        }
    }


    private void uploadFile(HttpServletRequest request, String taskName) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute(Constants.LOGIN);
        String userDirectory = Constants.FILES_DIRECTORY + File.separator + login;
        Path path = Paths.get(userDirectory);
        System.out.println(path.getRoot());
        if(!Files.exists(path)) {
            new File(userDirectory).mkdir();
        }
        Part part = request.getPart(Constants.FILE_PARAMETER);
        String fileName = extractFileName(part);
        String filePath = userDirectory + File.separator + taskName + FileConstants.FILE_DELIMITER + new File(fileName).getName();
        part.write(filePath);
    }


    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader(FileConstants.HTTP_HEADER_CONTENT_DISPOSITION);
        String[] items = contentDisposition.split(FileConstants.FILE_DELIMITER);
        for (String s : items) {
            if (s.trim().startsWith(FileConstants.HTTP_PARAMETER_FILE_NAME)) {
                return s.substring(s.indexOf(Constants.SIGN_EQUAL) + 2, s.length()-1);
            }
        }
        return Constants.EMPTY_STRING;
    }
}
