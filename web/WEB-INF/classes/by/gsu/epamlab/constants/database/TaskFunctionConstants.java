package by.gsu.epamlab.constants.database;

public class TaskFunctionConstants {

    public static final String SQL_THROW_INTO_BASKET = "Update task Set location = 'BASKET' " +
            "Where id = ?";

    public static final String SQL_THROW_INTO_FIXED = "Update task Set location = 'FIXED' " +
            "Where id = ?";

    public static final String SQL_GET_FOR_MAIN = "Update task Set location = 'MAIN' " +
            "Where id = ?";

    public static final String SQL_DELETE_TASK = "Delete from task Where id = ?";

    public static final int ID_INDEX = 1;
}
