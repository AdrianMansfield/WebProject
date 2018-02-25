package by.gsu.epamlab.controllers;

import by.gsu.epamlab.beans.FileOperations;
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
        try {
            FileOperations.downloadFile(request, response);
        }
        catch (IOException e) {
            e.printStackTrace();
            //FileException
        }

    }
}
