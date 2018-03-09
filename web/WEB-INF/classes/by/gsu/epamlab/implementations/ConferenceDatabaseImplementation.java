package by.gsu.epamlab.implementations;

import by.gsu.epamlab.beans.conference.Conference;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.database.DatabaseConnection;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.IConferenceDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConferenceDatabaseImplementation implements IConferenceDAO {

    private List<Conference> getConferenceList(PreparedStatement preparedStatement) throws SQLException {
        List<Conference> conferenceList = new ArrayList<>();
        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                int id = resultSet.getInt(DatabaseConstants.CONFERENCE_ID_TABLE_INDEX);
                String name = resultSet.getString(DatabaseConstants.CONFERENCE_NAME_TABLE_INDEX);
                String department = resultSet.getString(DatabaseConstants.CONFERENCE_DEPARTMENT_TABLE_INDEX);
                String stringDate = resultSet.getString(DatabaseConstants.CONFERENCE_DATE_TABLE_INDEX);
                Conference task = new Conference(id, name, department, stringDate);
                conferenceList.add(task);
            }
            return conferenceList;
        }
    }

    @Override
    public List<Conference> getConferences(String userId, String query, Date date) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        )
        {
            preparedStatement.setString(DatabaseConstants.USER_QUERY_INDEX, userId);
            preparedStatement.setDate(DatabaseConstants.DATE_QUERY_INDEX, date);
            return getConferenceList(preparedStatement);
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public List<Conference> getConferences(String userId, String query) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        )
        {
            preparedStatement.setString(DatabaseConstants.USER_QUERY_INDEX, userId);
            return getConferenceList(preparedStatement);
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addConference(String userId, Conference conference) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement conferencePreparedStatement = connection.prepareStatement(DatabaseConstants.SQL_INSERT_INTO_CONFERENCE)
        )
        {
            conferencePreparedStatement.setString(DatabaseConstants.CONFERENCE_USER_ID_QUERY_INDEX, userId);
            conferencePreparedStatement.setString(DatabaseConstants.CONFERENCE_NAME_QUERY_INDEX, conference.getName());
            conferencePreparedStatement.setString(DatabaseConstants.CONFERENCE_DEPARTMENT_QUERY_INDEX, conference.getDescription());
            conferencePreparedStatement.setDate(DatabaseConstants.CONFERENCE_DATE_QUERY_INDEX, conference.getDate());
            conferencePreparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void moveConference(String conferenceId, String query) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(DatabaseConstants.CONFERENCE_ID_INDEX, conferenceId);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }



    @Override
    public void removeConferences(String [] conferenceIds) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.SQL_DELETE_CONFERENCE)) {
            for(String conferenceId : conferenceIds) {
                preparedStatement.setString(DatabaseConstants.CONFERENCE_ID_INDEX, conferenceId);
                preparedStatement.executeUpdate();
            }

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
