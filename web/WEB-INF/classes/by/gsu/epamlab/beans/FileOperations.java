package by.gsu.epamlab.beans;

import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.FileConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.factories.TaskDAOFactory;
import by.gsu.epamlab.implementations.TaskDatabaseImplementation;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class FileOperations {

    public static String uploadFile(Part part, String userLogin, String taskName) throws IOException {
        String fileName = extractFileName(part);
        if(Constants.EMPTY_STRING.equals(fileName)) {
            fileName = "No file";
        }
        else {
            String userDirectory = Constants.FILES_DIRECTORY + File.separator + userLogin;
            Path path = Paths.get(userDirectory);
            if(!Files.exists(path)) {
                new File(userDirectory).mkdir();
            }
            String filePath = userDirectory + File.separator + taskName + FileConstants.FILE_DELIMITER + new File(fileName).getName();
            part.write(filePath);
        }
        return fileName;
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

    public static void downloadFile(HttpServletResponse response, String userLogin, Task task) throws IOException {

        response.addHeader(FileConstants.HTTP_HEADER_CONTENT_DISPOSITION,FileConstants.HTTP_PARAMETER_ATTACHMENT
                + FileConstants.FILE_DELIMITER + FileConstants.HTTP_PARAMETER_FILE_NAME + Constants.SIGN_EQUAL + task.getFileName());

        File file = new File( Constants.FILES_DIRECTORY + File.separator + userLogin + File.separator + task.getName() + FileConstants.FILE_DELIMITER +
                task.getFileName());

        long length = file.length();

        response.addHeader(FileConstants.HTTP_PARAMETER_CONTENT_LENGTH, String.valueOf(length));
        response.setContentType(FileConstants.CONTENT_TYPE);

        try(FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            OutputStream outputStream = response.getOutputStream())
        {

            int i;
            while((i=bufferedInputStream.read())!=-1){
                outputStream.write(i);
            }
        }
    }

    public static void deleteFile(String userId, List<Task> taskList, String userLogin) throws DaoException {

        ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();
        for(Task task : taskList) {
            String string = Constants.FILES_DIRECTORY + File.separator + userLogin + File.separator + task.getName() +
                    FileConstants.FILE_DELIMITER + task.getFileName();
            new File(string).delete();
            iTaskDAO.updateFileName(userId, "No file", task.getName());
        }

    }
}
