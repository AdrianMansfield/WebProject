package by.gsu.epamlab.model.implementations.database.constants;

public final class UpdateFileNameConstants {

    private UpdateFileNameConstants() {}

    public static final String SQL_UPDATE_FILE_NAME = "Update task Set fileName = ? Where userId = ? and name = ?";

    public static final int FILE_NAME_QUERY_INDEX = 1;

    public static final int FILE_NAME_USER_ID_QUERY_INDEX = 2;

    public static final int FILE_NAME_TASK_NAME_QUERY_INDEX = 3;
}
