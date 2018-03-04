package by.gsu.epamlab.implementations;

import by.gsu.epamlab.database.DatabaseConnection;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.beans.user.Role;
import by.gsu.epamlab.beans.user.User;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.exceptions.DaoException;

import java.sql.*;

public class UserDatabaseImplementation implements IUserDAO {

    @Override
    public User getUser(String login, String password) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.SQL_SELECT_USER))
        {
            User user = null;
            preparedStatement.setString(DatabaseConstants.USER_QUERY_INDEX, login);
            preparedStatement.setString(DatabaseConstants.PASSWORD_QUERY_INDEX, password);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    user = new User(resultSet.getInt(DatabaseConstants.ID_TABLE_INDEX),
                            resultSet.getString(DatabaseConstants.USER_TABLE_INDEX),
                            Role.valueOf(resultSet.getString(DatabaseConstants.ROLE_TABLE_INDEX)));
                }
            }
            return user;
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public User setUser(String login, String password) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.SQL_INSERT_USER))
        {
            User user = getUser(login, password);
            synchronized (this) {
                if(user == null) {
                    preparedStatement.setString(DatabaseConstants.USER_QUERY_INDEX, login);
                    preparedStatement.setString(DatabaseConstants.PASSWORD_QUERY_INDEX, password);
                    preparedStatement.setString(DatabaseConstants.ROLE_QUERY_INDEX, Role.USER.toString());
                    preparedStatement.execute();
                    user = getUser(login, password);
                }
                else {
                    user = null;
                }
            }
            return user; //this requires correction
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
