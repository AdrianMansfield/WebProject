package by.gsu.epamlab.model.implementations.database.constants;

public final class SelectTaskNameConstants {

    private SelectTaskNameConstants() {}

    public static final String SQL_SELECT_SELECT_TASK_NAME = "Select name from task Where userId = ? and name = ?";

    public static final int USER_ID_INDEX = 1;

    public static final int TASK_NAME_INDEX = 2;

}
