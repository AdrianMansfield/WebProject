package by.gsu.epamlab.model.implementations.database.constants;

public final class SelectTask {

    private SelectTask() {}

    public static final String SELECT_TASK_BY_ID = "Select * From task Where userId = ? and id = ?";

    public static final String SELECT_TASK_BY_IDS_HEAD = "Select * From task Where userId = ? and id in (";

    public static final String SELECT_TASK_BY_IDS_TAIL = ")";

    public static final String SELECT_TASK_BY_NAME = "Select * From task Where userId = ? and name = ?";

    public static final int USER_ID = 1;

    public static final int TASK_PARAMETER = 2;

    public static final int TASK_ID_COLUMN_INDEX = 1;

    public static final int TASK_NAME_COLUMN_INDEX = 3;

    public static final int TASK_DESCRIPTION_COLUMN_INDEX = 4;

    public static final int TASK_DATE_COLUMN_INDEX = 5;

    public static final int TASK_FILE_NAME_COLUMN_INDEX = 6;
}
