package by.gsu.epamlab.beans;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.FileConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public final class FileOperations {
    public static void uploadFile(HttpServletRequest request, String taskName) throws ServletException, IOException {
        Part part = request.getPart(Constants.FILE_PARAMETER);
        String fileName = extractFileName(part);
        if(Constants.EMPTY_STRING.equals(fileName)) {
            return;
        }
        String login = (String) request.getSession().getAttribute(Constants.LOGIN);
        String userDirectory = Constants.FILES_DIRECTORY + File.separator + login;
        Path path = Paths.get(userDirectory);
        System.out.println(path.getRoot());
        if(!Files.exists(path)) {
            new File(userDirectory).mkdir();
        }
        String filePath = userDirectory + File.separator + taskName + FileConstants.FILE_DELIMITER + new File(fileName).getName();
        part.write(filePath);
    }


    private static String extractFileName(Part part) {
        String fileName = Constants.EMPTY_STRING;
        String contentDisposition = part.getHeader(FileConstants.HTTP_HEADER_CONTENT_DISPOSITION);
        String[] items = contentDisposition.split(FileConstants.FILE_DELIMITER);
        for (String s : items) {
            if (s.trim().startsWith(FileConstants.HTTP_PARAMETER_FILE_NAME)) {
                fileName = s.substring(s.indexOf(Constants.SIGN_EQUAL) + 2, s.length()-1);
            }
        }
        return fileName;
    }

    public static Map<String, String> getFileForTask(HttpServletRequest request) {
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

    public static void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fullFileName = request.getParameter(Constants.FILE_PARAMETER);
        String login = (String) request.getSession().getAttribute(Constants.LOGIN);

        request.setCharacterEncoding(FileConstants.UTF8_CHARACTER_ENCODING);
        response.setCharacterEncoding(FileConstants.UTF8_CHARACTER_ENCODING);

        String simpleFileName = fullFileName.split(FileConstants.FILE_DELIMITER)[FileConstants.FILE_NAME_INDEX];

        response.addHeader(FileConstants.HTTP_HEADER_CONTENT_DISPOSITION,FileConstants.HTTP_PARAMETER_ATTACHMENT
                + FileConstants.FILE_DELIMITER + FileConstants.HTTP_PARAMETER_FILE_NAME + Constants.SIGN_EQUAL + simpleFileName);

        File file = new File( Constants.FILES_DIRECTORY + File.separator + login + File.separator + fullFileName);
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
