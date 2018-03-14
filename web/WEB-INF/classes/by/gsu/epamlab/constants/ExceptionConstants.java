package by.gsu.epamlab.constants;

public final class ExceptionConstants {

    private ExceptionConstants() {}

    public static final String DELETE_FILE__EXCEPTION_MESSAGE = "Failed to delete some files. Perhaps these files are occupied by another process.";

    public static final String CREATE_FOLDER_EXCEPTION_MESSAGE = "Could not create folder. " +
            "Perhaps you do not have permission to access this directory or it is not exist.";

    public final static String NON_GET_EXCEPTION_MESSAGE = "Method GET not supported in that servlet";

    public final static String NON_POST_EXCEPTION_MESSAGE = "Method POST not supported in that servlet";

}
