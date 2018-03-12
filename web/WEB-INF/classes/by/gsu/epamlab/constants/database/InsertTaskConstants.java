package by.gsu.epamlab.constants.database;

public class InsertTaskConstants {

    public static final String SQL_INSERT_INTO_TASK = "INSERT INTO task(userId, name, department, date, fileName) VALUES(?, ?, ?, ?, ?)";
    public static final int USER_ID_INDEX = 1;
    public static final int TASK_NAME_INDEX = 2;
    public static final int TASK_DESCRIPTION_INDEX = 3;
    public static final int DATE_INDEX = 4;
    public static final int FILE_NAME_INDEX = 5;

}
