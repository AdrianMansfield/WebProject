package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.FileConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UploadFileServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute(Constants.LOGIN);
        String userDirectory = Constants.FILES_DIRECTORY + File.separator + login;
        Path path = Paths.get(userDirectory);
        System.out.println(path.getRoot());
        if(!Files.exists(path)) {
            new File(userDirectory).mkdir();
        }
        Part part = request.getPart(Constants.FILE_PARAMETER);
        String fileName = extractFileName(part);
        String filePath = userDirectory + new File(fileName).getName();
        part.write(filePath);
    }


    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader(FileConstants.HTTP_HEADER_CONTENT_DISPOSITION);
        String[] items = contentDisposition.split(Constants.DELIMITER);
        for (String s : items) {
            if (s.trim().startsWith(FileConstants.HTTP_PARAMETER_FILE_NAME)) {
                return s.substring(s.indexOf(Constants.SIGN_EQUAL) + 2, s.length()-1);
            }
        }
        return Constants.EMPTY_STRING;
    }
}
