package by.gsu.epamlab.constants.database;

public final class SelectTaskById {

    private SelectTaskById() {}

    public static final String SELECT_TASK_BY_ID = "Select * From task Where userId = ? and id = ?";

    public static final String SELECT_TASK_BY_IDS_HEAD = "Select * From task Where userId = ? and id in (";

    public static final String SELECT_TASK_BY_IDS_TAIL = ")";

    public static final int USER_ID = 1;

    public static final int TASK_ID = 2;

    public static final int TASK_ID_COLUMN_INDEX = 1;

    public static final int TASK_NAME_COLUMN_INDEX = 3;

    public static final int TASK_DESCRIPTION_COLUMN_INDEX = 4;

    public static final int TASK_DATE_COLUMN_INDEX = 5;

    public static final int TASK_FILE_NAME_COLUMN_INDEX = 6;
}
