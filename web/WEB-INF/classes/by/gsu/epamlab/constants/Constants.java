package by.gsu.epamlab.constants;


public class Constants {

    public static final String RESOURCE_NAME = "java:comp/env/jdbc/webproject";

    public static final String IMPLEMENTATION = "implementation";

    public final static String NON_GET_EXCEPTION_MESSAGE = "Method Get not supported in that servlet";

    public static final String KEY_ERROR_MESSAGE = "errorMessage";

    public static final String EMPTY_DATA = "Empty login or password!";

    public static final String INVALID_LOGIN_OR_PASSWORD = "Invalid login or password!";

    public static final int ZERO = 0;

    public static final int COOKIE_AGE = 60 * 60 * 24 * 365;

    public static final String EMPTY_STRING = "";

    public static final String UTF8_CHARACTER_ENCODING = "UTF-8";

    public static final String JSON_CONTENT_TYPE = "application/x-json";

    public static final String SERVER_ERROR = "Sorry. Server temporary is not available";


    public static final String PRINT_DATE_FORMAT = "dd.MM.yyyy";

    public static final String USUAL_DATE_FORMAT = "yyyy-MM-dd";

    public static final int DIFFERENCE_DAY = 1;


    public static final String CLASSES_DIRECTORY = Constants.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1,
            Constants.class.getProtectionDomain().getCodeSource().getLocation().getPath().length());

    public static final String FILES_DIRECTORY = CLASSES_DIRECTORY + "by/gsu/epamlab/files";

    public static final String SIGN_EQUAL = "=";

    public static final String ADD_TASK_ERROR_MESSAGE = "Invalid parameter(s) of task";

    public static final String DUPLICATE_TASK_NAME_ERROR_MESSAGE = "This task already exists";

    public static final String NO_FILE = "No file";

}
