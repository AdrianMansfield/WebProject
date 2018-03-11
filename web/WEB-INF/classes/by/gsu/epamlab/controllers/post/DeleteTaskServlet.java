package by.gsu.epamlab.controllers.post;

import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteTaskServlet extends AbstractNonGetController {

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String [] taskIds = request.getParameterValues("taskIds");

            ITaskDAO iTaskDAO = new TaskDatabaseImplementation();

            iTaskDAO.removeTasks(taskIds);

            request.getRequestDispatcher("/DeleteFileServlet").forward(request, response);

        } catch (DaoException e) {

            e.printStackTrace();

        }
    }
}
