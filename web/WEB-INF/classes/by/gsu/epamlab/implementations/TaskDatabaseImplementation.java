package by.gsu.epamlab.implementations;

import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.database.DatabaseConnection;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDatabaseImplementation implements ITaskDAO {

    private List<Task> getTaskList(PreparedStatement preparedStatement) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                int id = resultSet.getInt(DatabaseConstants.TASK_ID_TABLE_INDEX);
                String name = resultSet.getString(DatabaseConstants.TASK_NAME_TABLE_INDEX);
                String department = resultSet.getString(DatabaseConstants.TASK_DEPARTMENT_TABLE_INDEX);
                String stringDate = resultSet.getString(DatabaseConstants.TASK_DATE_TABLE_INDEX);
                String fileName = resultSet.getString(DatabaseConstants.TASK_FILE_NAME_TABLE_INDEX);
                Task task = new Task(id, name, department, stringDate, fileName);
                taskList.add(task);
            }
            return taskList;
        }
    }

    @Override
    public List<Task> getTasks(String userId, String query, Date date) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        )
        {
            preparedStatement.setString(DatabaseConstants.USER_QUERY_INDEX, userId);
            preparedStatement.setDate(DatabaseConstants.DATE_QUERY_INDEX, date);
            return getTaskList(preparedStatement);
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public List<Task> getTasks(String userId, String query) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        )
        {
            preparedStatement.setString(DatabaseConstants.USER_QUERY_INDEX, userId);
            return getTaskList(preparedStatement);
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addTask(String userId, Task task) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement taskPreparedStatement = connection.prepareStatement(DatabaseConstants.SQL_INSERT_INTO_TASK)
        )
        {
            taskPreparedStatement.setString(DatabaseConstants.TASK_USER_ID_QUERY_INDEX, userId);
            taskPreparedStatement.setString(DatabaseConstants.TASK_NAME_QUERY_INDEX, task.getName());
            taskPreparedStatement.setString(DatabaseConstants.TASK_DEPARTMENT_QUERY_INDEX, task.getDescription());
            taskPreparedStatement.setDate(DatabaseConstants.TASK_DATE_QUERY_INDEX, task.getDate());
            taskPreparedStatement.setString(DatabaseConstants.TASK_FILE_NAME_QUERY_INDEX, task.getFileName());
            taskPreparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void moveTask(String taskId, String query) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(DatabaseConstants.TASK_ID_INDEX, taskId);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }



    @Override
    public void removeTasks(String [] taskIds) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.SQL_DELETE_TASK)) {
            for(String taskId : taskIds) {
                preparedStatement.setString(DatabaseConstants.TASK_ID_INDEX, taskId);
                preparedStatement.executeUpdate();
            }

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateFileName(String userId, String fileName, String taskName) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DatabaseConstants.SQL_UPDATE_FILE_NAME)) {

            preparedStatement.setString(DatabaseConstants.FILE_NAME_QUERY_INDEX, fileName);
            preparedStatement.setString(DatabaseConstants.FILE_NAME_USER_ID_QUERY_INDEX, userId);
            preparedStatement.setString(DatabaseConstants.FILE_NAME_TASK_NAME_QUERY_INDEX, taskName);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
