package by.gsu.epamlab.model.implementations.database.constants;

public final class TaskFunctionConstants {

    private TaskFunctionConstants() {}

    public static final String SQL_THROW_INTO_BASKET = "Update task Set location = 'BASKET' " +
            "Where id = ?";

    public static final String SQL_THROW_INTO_FIXED = "Update task Set location = 'FIXED' " +
            "Where id = ?";

    public static final String SQL_THROW_INTO_MAIN = "Update task Set location = 'MAIN' " +
            "Where id = ?";

    public static final String SQL_DELETE_TASK = "Delete from task Where id = ?";

    public static final int ID_INDEX = 1;
}
