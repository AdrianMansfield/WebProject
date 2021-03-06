package by.gsu.epamlab.model;

import by.gsu.epamlab.model.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.constants.FileConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.FileProblemException;
import by.gsu.epamlab.control.listeners.ApplicationContextParameter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class FileOperations {

    private static final int END_OF_STREAM = -1;


    private FileOperations() {}

    public static String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

    public static String uploadFile(Part part, String userLogin, String taskName) throws IOException {
        String fileName = extractFileName(part);
        if(Constants.EMPTY_STRING.equals(fileName)) {
            fileName = Constants.NO_FILE;
        }
        else {
            String userDirectory = ApplicationContextParameter.getFilesDirectory() + File.separator + userLogin;
            Path path = Paths.get(userDirectory);
            if(!Files.exists(path)) {
                boolean isCreated = new File(userDirectory).mkdir();
                if(!isCreated) {
                    throw new FileProblemException(ExceptionConstants.CREATE_FOLDER_EXCEPTION_MESSAGE);
                }
            }
            String filePath = userDirectory + File.separator + taskName + FileConstants.FILE_DELIMITER + new File(fileName).getName();
            part.write(filePath);
        }
        return fileName;
    }


    public static boolean downloadFile(HttpServletResponse response, String userLogin, Task task) throws IOException {


        File file = new File( ApplicationContextParameter.getFilesDirectory() + File.separator + userLogin + File.separator + task.getName() + FileConstants.FILE_DELIMITER +
                task.getFileName());
        boolean exists = file.exists();
        if(exists) {

            response.addHeader(FileConstants.HTTP_HEADER_CONTENT_DISPOSITION,FileConstants.HTTP_PARAMETER_ATTACHMENT
                    + FileConstants.FILE_DELIMITER + FileConstants.HTTP_PARAMETER_FILE_NAME + Constants.SIGN_EQUAL + task.getFileName());

            long length = file.length();

            response.addHeader(FileConstants.HTTP_PARAMETER_CONTENT_LENGTH, String.valueOf(length));
            response.setContentType(FileConstants.CONTENT_TYPE);

            try(FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                OutputStream outputStream = response.getOutputStream())
            {
                int i;
                while((i=bufferedInputStream.read()) != END_OF_STREAM)  {
                    outputStream.write(i);
                }
            }
        }

        return exists;


    }

    public static void deleteFile(Task task, String userLogin)  {

        String string = ApplicationContextParameter.getFilesDirectory() + File.separator + userLogin + File.separator + task.getName() +
                FileConstants.FILE_DELIMITER + task.getFileName();

        File file = new File(string);

        boolean isExist = file.exists();

        if(isExist) {

            boolean isDeleted = file.delete();

            if(!isDeleted) {
                throw new FileProblemException(ExceptionConstants.DELETE_FILE__EXCEPTION_MESSAGE);
            }
        }
    }

    public static void deleteFiles(List<Task> taskList, String userLogin) {

        for(Task task : taskList) {
            deleteFile(task, userLogin);
        }
    }
}
