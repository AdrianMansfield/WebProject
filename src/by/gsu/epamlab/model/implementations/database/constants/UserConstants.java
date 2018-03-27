package by.gsu.epamlab.model.implementations.database.constants;

public final class UserConstants {

    private UserConstants() {}

    public static final String SQL_SELECT_USER = "Select * From user " +
            " Where login = ? and password = ?";

    public static final String SQL_INSERT_USER = "Insert Into user(login, password, role) values(?, ?, ?)";

    public static final int ID_INDEX = 1;

    public static final int USER_QUERY_INDEX = 1;

    public static final int PASSWORD_QUERY_INDEX = 2;

    public static final int ROLE_QUERY_INDEX = 3;


    public static final int USER_TABLE_INDEX = 2;

    public static final int ROLE_TABLE_INDEX = 4;
}
