package by.gsu.epamlab.model;

import by.gsu.epamlab.model.task.Task;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ExceptionConstants;
import by.gsu.epamlab.constants.FileConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.exceptions.FileProblemException;
import by.gsu.epamlab.model.factories.TaskDAOFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;
import by.gsu.epamlab.control.listeners.ApplicationContextParameter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class FileOperations {

    private FileOperations() {}

    public static String uploadFile(Part part, String userLogin, String taskName) throws IOException {
        String fileName = part.getSubmittedFileName();
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
                while((i=bufferedInputStream.read())!=-1){ //need a constant
                    outputStream.write(i);
                }
            }
        }

        return exists;


    }

    public static void deleteFile(String userId, Task task, String userLogin) throws DaoException {
        ITaskDAO iTaskDAO = TaskDAOFactory.getTaskDAOFromFactory();
        String string = ApplicationContextParameter.getFilesDirectory() + File.separator + userLogin + File.separator + task.getName() +
                FileConstants.FILE_DELIMITER + task.getFileName();
        File file = new File(string);

        boolean isExist = file.exists();

        if(isExist) {
            boolean isDeleted = file.delete();
            if(isDeleted) {
                iTaskDAO.updateFileName(userId, Constants.NO_FILE, task.getName());
            }
            else {
                throw new FileProblemException(ExceptionConstants.DELETE_FILE__EXCEPTION_MESSAGE);
            }
        }
    }

    public static void deleteFiles(String userId, List<Task> taskList, String userLogin) throws DaoException {

        for(Task task : taskList) {
            deleteFile(userId, task, userLogin);
        }
    }
}
