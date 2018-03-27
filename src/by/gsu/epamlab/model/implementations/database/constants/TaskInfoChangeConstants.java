package by.gsu.epamlab.model.implementations.database.constants;

public class TaskInfoChangeConstants {

    private TaskInfoChangeConstants(){}

    public static final String SQL_CHANGE_DESCRIPTION = "Update task set department = ? Where id = ?";
    public static final String SQL_CHANGE_DATE = "Update task set date = ? Where id = ?";

    public static final int TASK_INFO_INDEX = 1;
    public static final int ID_INDEX = 2;
}
