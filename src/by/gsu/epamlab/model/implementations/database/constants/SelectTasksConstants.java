package by.gsu.epamlab.model.implementations.database.constants;

public final class SelectTasksConstants {

    private SelectTasksConstants() {}

    public static final String SQL_SELECT_TODAY_TASKS = "Select * from task Where userId = ? and date < ? and location = 'MAIN'";

    public static final String SQL_SELECT_TOMORROW_TASKS = "Select * from task Where userId = ? and date = ? and location = 'MAIN'";

    public static final String SQL_SELECT_SOMEDAY_TASKS = "Select * from task Where userId = ? and date > ? and location = 'MAIN'";

    public static final String SQL_SELECT_FIXED_TASKS = "Select * from task Where userId = ? and date <> ? and location = 'FIXED'";

    public static final String SQL_SELECT_BASKET_TASKS = "Select * from task Where userId = ? and date <> ? and location = 'BASKET'";

    public static final int USER_ID_INDEX = 1;
    public static final int DATE_INDEX = 2;

    public static final int TASK_ID_TABLE_INDEX = 1;
    public static final int TASK_NAME_TABLE_INDEX = 3;
    public static final int TASK_DEPARTMENT_TABLE_INDEX = 4;
    public static final int TASK_DATE_TABLE_INDEX = 5;
    public static final int TASK_FILE_NAME_TABLE_INDEX = 6;

}
