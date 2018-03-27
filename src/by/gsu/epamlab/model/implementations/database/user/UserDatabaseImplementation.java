package by.gsu.epamlab.model.implementations.database.user;

import by.gsu.epamlab.model.implementations.database.constants.UserConstants;
import by.gsu.epamlab.model.database.DatabaseConnection;
import by.gsu.epamlab.model.interfaces.IUserDAO;
import by.gsu.epamlab.model.user.Role;
import by.gsu.epamlab.model.user.User;
import by.gsu.epamlab.exceptions.DaoException;

import java.sql.*;

public final class UserDatabaseImplementation implements IUserDAO {

    private static final UserDatabaseImplementation userDatabaseImplementation = new UserDatabaseImplementation();

    private UserDatabaseImplementation() {}

    @Override
    public User getUser(String login, char [] password) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UserConstants.SQL_SELECT_USER))
        {
            User user = null;
            preparedStatement.setString(UserConstants.USER_QUERY_INDEX, login);
            preparedStatement.setString(UserConstants.PASSWORD_QUERY_INDEX, new String(password));
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    user = new User(resultSet.getInt(UserConstants.ID_INDEX),
                            resultSet.getString(UserConstants.USER_TABLE_INDEX),
                            Role.valueOf(resultSet.getString(UserConstants.ROLE_TABLE_INDEX)));
                }
            }
            return user;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean setUser(String login, char [] password) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UserConstants.SQL_INSERT_USER))
        {
            boolean isAdded = false;
            User user = getUser(login, password);
            synchronized (this) {
                if(user == null) {
                    preparedStatement.setString(UserConstants.USER_QUERY_INDEX, login);
                    preparedStatement.setString(UserConstants.PASSWORD_QUERY_INDEX, new String(password));
                    preparedStatement.setString(UserConstants.ROLE_QUERY_INDEX, Role.USER.toString());
                    preparedStatement.execute();
                    isAdded = true;
                }
            }
            return isAdded;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static UserDatabaseImplementation getUserRAMImplementation() {
        return userDatabaseImplementation;
    }

}
