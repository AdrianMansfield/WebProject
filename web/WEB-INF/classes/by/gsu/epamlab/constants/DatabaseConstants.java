package by.gsu.epamlab.constants;

public class DatabaseConstants {
    public static final String SQL_SELECT_USER = "Select * From user " +
            " Where login = ? and password = ?";
    public static final String SQL_INSERT_USER = "Insert Into user(login, password, role) values(?, ?, ?)";
    public static final int USER_QUERY_INDEX = 1;
    public static final int PASSWORD_QUERY_INDEX = 2;
    public static final int ROLE_QUERY_INDEX = 3;

    public static final int ID_TABLE_INDEX = 1;
    public static final int USER_TABLE_INDEX = 2;
    public static final int ROLE_TABLE_INDEX = 4;

    public static final String SQL_SELECT_TODAY_TASKS = "Select * from task Where userId = ? and date < ? and location = 'MAIN'";

    public static final String SQL_SELECT_TOMORROW_TASKS = "Select * from task Where userId = ? and date = ? and location = 'MAIN'";

    public static final String SQL_SELECT_SOMEDAY_TASKS = "Select * from task Where userId = ? and date > ? and location = 'MAIN'";

    public static final String SQL_SELECT_FIXED_TASKS = "Select * from task Where userId = ? and location = 'FIXED'";

    public static final String SQL_SELECT_BASKET_TASKS = "Select * from task Where userId = ? and location = 'BASKET'";

    public static final int DATE_QUERY_INDEX = 2;


    public static final int TASK_ID_TABLE_INDEX = 1;
    public static final int TASK_NAME_TABLE_INDEX = 3;
    public static final int TASK_DEPARTMENT_TABLE_INDEX = 4;
    public static final int TASK_DATE_TABLE_INDEX = 5;
    public static final int TASK_FILE_NAME_TABLE_INDEX = 6;

    public static final String SQL_INSERT_INTO_TASK = "INSERT INTO task(userId, name, department, date, fileName) VALUES(?, ?, ?, ?, ?)";
    public static final int TASK_USER_ID_QUERY_INDEX = 1;
    public static final int TASK_NAME_QUERY_INDEX = 2;
    public static final int TASK_DEPARTMENT_QUERY_INDEX = 3;
    public static final int TASK_DATE_QUERY_INDEX = 4;
    public static final int TASK_FILE_NAME_QUERY_INDEX = 5;

    public static final String SQL_UPDATE_FILE_NAME = "Update task Set fileName = ? Where userId = ? and name = ?";
    public static final int FILE_NAME_QUERY_INDEX = 1;
    public static final int FILE_NAME_USER_ID_QUERY_INDEX = 2;
    public static final int FILE_NAME_TASK_NAME_QUERY_INDEX = 3;

    public static final String SQL_THROW_INTO_BASKET = "Update task Set location = 'BASKET' " +
            "Where id = ?";

    public static final String SQL_THROW_INTO_FIXED = "Update task Set location = 'FIXED' " +
            "Where id = ?";
    public static final String SQL_GET_FOR_MAIN = "Update task Set location = 'MAIN' " +
            "Where id = ?";
    public static final int TASK_ID_INDEX = 1;

    public static final String SQL_DELETE_TASK = "Delete from task Where id = ?";


}
