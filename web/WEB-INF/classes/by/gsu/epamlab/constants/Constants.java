package by.gsu.epamlab.constants;


public class Constants {
    public static final String RESOURCE_NAME = "java:comp/env/jdbc/webproject";
    public static final String IMPLEMENTATION = "implementation";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String REGISTRATION_URL = "/registration";
    public static final String MAIN_URL = "/main";
    public static final String LOGIN_URL = "/login";
    public final static String INDEX_URL = "/index";
    public final static String NON_GET_EXCEPTION_MESSAGE = "Method Get not supported in that servlet";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String EMPTY_DATA = "Empty login or password!";
    public static final String INVALID_LOGIN_OR_PASSWORD = "Invalid login or password!";
    public static final String ROLE = "role";

    public static final String PRINT_CONFERENCE_SERVLET_URL = "/PrintConferenceServlet";

    public static final String CONFERENCE_LIST_NAME = "conferences";

    public static final String ID = "id";

    public static final int ZERO = 0;

    public static final int COOKIE_AGE = 60 * 60 * 24 * 365;

    public static final String EMPTY_STRING = "";

    public static final String FILE_PARAMETER = "file";

    public static final String DATE_TYPE_PARAMETER = "dateType";

    public static final String DATE_PARAMETER = "date";

    public static final String SERVER_ERROR = "Sorry. Server temporary is not available";

    public static final String EVENTS_ATTRIBUTE = "events";

    public static final String CURRENT_CONFERENCE_PARAMETER = "currentTask";

    public static final String PRINT_DATE_FORMAT = "dd.MM.yyyy";

    public static final String USUAL_DATE_FORMAT = "yyyy-MM-dd";

    public static final int DIFFERENCE_DAY = 1;


    public static final String CLASSES_DIRECTORY = Constants.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(1,
            Constants.class.getProtectionDomain().getCodeSource().getLocation().getPath().length());

    public static final String FILES_DIRECTORY = CLASSES_DIRECTORY + "by/gsu/epamlab/files";

    public static final String SIGN_EQUAL = "=";

    public static final String CONFERENCE_NAME_PARAMETER = "taskName";

    public static final String DEPARTMENT_PARAMETER = "department";

    public static final String FILE_MAP_PARAMETER = "fileMap";

    public static final String ADD_CONFERENCE_ERROR_MESSAGE = "Invalid parameter(s) of conference";


}
