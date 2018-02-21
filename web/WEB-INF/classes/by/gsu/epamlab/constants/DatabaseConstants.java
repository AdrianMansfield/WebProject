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

    public static final String SQL_SELECT_TODAY_TASKS = "Select * from conference Where userId = ? and date < ?";

    public static final String SQL_SELECT_TOMORROW_TASKS = "Select * from conference Where userId = ? and date = ?";

    public static final String SQL_SELECT_SOMEDAY_TASKS = "Select * from conference Where userId = ? and date > ?";

    public static final int DATE_QUERY_INDEX = 2;

    public static final String SQL_SELECT_EVENTS = "Select * from events Where conferenceId = ?";

    public static final int EVENT_QUERY_INDEX = 1;


    public static final int EVENT_ID_TABLE_INDEX = 1;
    public static final int EVENT_NAME_TABLE_INDEX = 3;
    public static final int EVENT_TIME_TABLE_INDEX = 4;

    public static final int CONFERENCE_ID_TABLE_INDEX = 1;
    public static final int CONFERENCE_NAME_TABLE_INDEX = 3;
    public static final int CONFERENCE_DEPARTMENT_TABLE_INDEX = 4;
    public static final int CONFERENCE_DATE_TABLE_INDEX = 5;

    public static final String SQL_INSERT_INTO_CONFERENCE = "INSERT INTO conference(userId, name, department, date) VALUES(?, ?, ?, ?)";
    public static final int CONFERENCE_USER_ID_QUERY_INDEX = 1;
    public static final int CONFERENCE_NAME_QUERY_INDEX = 2;
    public static final int CONFERENCE_DEPARTMENT_QUERY_INDEX = 3;
    public static final int CONFERENCE_DATE_QUERY_INDEX = 4;

    public static final String SQL_SELECT_CURRENT_CONFERENCE_ID = "SELECT max(id) FROM conference";

    public static final String SQL_INSERT_INTO_EVENTS = "INSERT INTO `events`(conferenceId, name, time) VALUES (?,?,?)";
    public static final int EVENT_CONFERENCE_ID_QUERY_INDEX = 1;
    public static final int EVENT_NAME_QUERY_INDEX = 2;
    public static final int EVENT_TIME_QUERY_INDEX = 3;

}
