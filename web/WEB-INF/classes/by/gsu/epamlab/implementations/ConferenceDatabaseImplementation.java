package by.gsu.epamlab.implementations;

import by.gsu.epamlab.beans.event.Event;
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
    public List<Event> getEvents(String conferenceId) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.SQL_SELECT_EVENTS))
        {
            preparedStatement.setString(DatabaseConstants.EVENT_QUERY_INDEX, conferenceId);
            List<Event> eventList = new ArrayList<>();
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(DatabaseConstants.EVENT_ID_TABLE_INDEX);
                    String name = resultSet.getString(DatabaseConstants.EVENT_NAME_TABLE_INDEX);
                    String time = resultSet.getString(DatabaseConstants.EVENT_TIME_TABLE_INDEX);
                    eventList.add(new Event(id, name, time));
                }
            }
            return eventList;
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
            conferencePreparedStatement.setString(DatabaseConstants.CONFERENCE_DEPARTMENT_QUERY_INDEX, conference.getDepartment());
            conferencePreparedStatement.setDate(DatabaseConstants.CONFERENCE_DATE_QUERY_INDEX, conference.getDate());
            conferencePreparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void workWithBasket(String [] conferenceIds, String query) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for(String conferenceId : conferenceIds) {
                preparedStatement.setString(DatabaseConstants.CONFERENCE_ID_INDEX, conferenceId);
                preparedStatement.executeUpdate();
            }

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    private void removeConferenceEvents(String conferenceId) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.SQL_DELETE_CONFERENCE_EVENTS)) {

            preparedStatement.setString(DatabaseConstants.EVENTS_ID_INDEX, conferenceId);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void addEvent(String conferenceId, Event event) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.SQL_INSERT_INTO_EVENTS)) {

                preparedStatement.setString(DatabaseConstants.EVENT_CONFERENCE_ID_QUERY_INDEX, conferenceId);
                preparedStatement.setString(DatabaseConstants.EVENT_NAME_QUERY_INDEX, event.getName());
                preparedStatement.setString(DatabaseConstants.EVENT_TIME_QUERY_INDEX, event.getTime());
                preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void removeEvents(String [] eventIds) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.SQL_DELETE_FROM_EVENTS)) {

            for(String id : eventIds) {
                preparedStatement.setString(DatabaseConstants.EVENTS_ID_INDEX, id);
                preparedStatement.executeUpdate();
            }

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
                removeConferenceEvents(conferenceId);
                preparedStatement.setString(DatabaseConstants.CONFERENCE_ID_INDEX, conferenceId);
                preparedStatement.executeUpdate();
            }

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void getOutOfBasket(String [] conferenceIds) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.SQL_GET_FOR_MAIN)) {
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
