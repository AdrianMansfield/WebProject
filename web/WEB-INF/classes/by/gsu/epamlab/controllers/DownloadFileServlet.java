package by.gsu.epamlab.controllers;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.FileConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadFileServlet extends AbstractNonGetController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter(Constants.FILE_PARAMETER);
        String login = (String) request.getSession().getAttribute(Constants.LOGIN);

        request.setCharacterEncoding(FileConstants.UTF8_CHARACTER_ENCODING);
        response.setCharacterEncoding(FileConstants.UTF8_CHARACTER_ENCODING);

        response.addHeader(FileConstants.HTTP_HEADER_CONTENT_DISPOSITION,FileConstants.HTTP_PARAMETER_ATTACHMENT
                + Constants.DELIMITER + FileConstants.HTTP_PARAMETER_FILE_NAME + Constants.SIGN_EQUAL + fileName);
        File file = new File( Constants.FILES_DIRECTORY + File.separator + login + File.separator + fileName);
        long length = file.length();
        response.addHeader(FileConstants.HTTP_PARAMETER_CONTENT_LENGTH, String.valueOf(length));
        response.setContentType(FileConstants.CONTENT_TYPE);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        int i;
        while((i=bufferedInputStream.read())!=-1){
            response.getOutputStream().write(i);
        }
        bufferedInputStream.close();
        fileInputStream.close();
        response.getOutputStream().close();
    }
}
