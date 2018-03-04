package by.gsu.epamlab.controllers.post;

import by.gsu.epamlab.beans.FileOperations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFileServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FileOperations.deleteFile(request, response);
    }
}
