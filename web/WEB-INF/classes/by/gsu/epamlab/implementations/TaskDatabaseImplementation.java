package by.gsu.epamlab.implementations;

import by.gsu.epamlab.beans.Event;
import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.database.DatabaseConnection;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskDatabaseImplementation implements ITaskDAO {
    @Override
    public List<Task> getTasks(String userId, String query, Date date) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        )
        {
            preparedStatement.setString(DatabaseConstants.USER_QUERY_INDEX, userId);
            preparedStatement.setDate(DatabaseConstants.DATE_QUERY_INDEX, date);
            List<Task> taskList = new ArrayList<>();
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()) {
                    int id = resultSet.getInt(DatabaseConstants.CONFERENCE_ID_TABLE_INDEX);
                    String name = resultSet.getString(DatabaseConstants.CONFERENCE_NAME_TABLE_INDEX);
                    String department = resultSet.getString(DatabaseConstants.CONFERENCE_DEPARTMENT_TABLE_INDEX);
                    String stringDate = resultSet.getString(DatabaseConstants.CONFERENCE_DATE_TABLE_INDEX);
                    Task task = new Task(id, name, department, stringDate);
                    taskList.add(task);
                }
            }
            return taskList;
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
    public void addTask(String userId, Task task) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement conferencePreparedStatement = connection.prepareStatement(DatabaseConstants.SQL_INSERT_INTO_CONFERENCE)
        //    PreparedStatement eventPreparedStatement = connection.prepareStatement(DatabaseConstants.SQL_INSERT_INTO_EVENTS)
        )
        {
            conferencePreparedStatement.setString(DatabaseConstants.CONFERENCE_USER_ID_QUERY_INDEX, userId);
            conferencePreparedStatement.setString(DatabaseConstants.CONFERENCE_NAME_QUERY_INDEX, task.getName());
            conferencePreparedStatement.setString(DatabaseConstants.CONFERENCE_DEPARTMENT_QUERY_INDEX, task.getDepartment());
            conferencePreparedStatement.setDate(DatabaseConstants.CONFERENCE_DATE_QUERY_INDEX, task.getDate());
            conferencePreparedStatement.executeUpdate();
            /*
            while(scanner.hasNext()) {
                String eventName = scanner.nextLine();
                String time = scanner.nextLine();
                eventPreparedStatement.setInt(DatabaseConstants.EVENT_CONFERENCE_ID_QUERY_INDEX, getCurrentConferenceId(connection));
                eventPreparedStatement.setString(DatabaseConstants.EVENT_NAME_QUERY_INDEX, eventName);
                eventPreparedStatement.setString(DatabaseConstants.EVENT_TIME_QUERY_INDEX, time);
                eventPreparedStatement.executeUpdate();
            }*/

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
