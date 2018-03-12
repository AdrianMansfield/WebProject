package by.gsu.epamlab.implementations;

import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.constants.database.*;
import by.gsu.epamlab.database.DatabaseConnection;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.interfaces.ITaskDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDatabaseImplementation implements ITaskDAO {

    private static final TaskDatabaseImplementation taskDatabaseImplementation = new TaskDatabaseImplementation();

    private TaskDatabaseImplementation() {}

    private List<Task> getTaskList(PreparedStatement preparedStatement) throws SQLException {
        List<Task> taskList = new ArrayList<>();
        try(ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                int id = resultSet.getInt(SelectTasksConstants.TASK_ID_TABLE_INDEX);
                String name = resultSet.getString(SelectTasksConstants.TASK_NAME_TABLE_INDEX);
                String department = resultSet.getString(SelectTasksConstants.TASK_DEPARTMENT_TABLE_INDEX);
                String stringDate = resultSet.getString(SelectTasksConstants.TASK_DATE_TABLE_INDEX);
                String fileName = resultSet.getString(SelectTasksConstants.TASK_FILE_NAME_TABLE_INDEX);
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
            preparedStatement.setString(SelectTasksConstants.USER_ID_INDEX, userId);
            preparedStatement.setDate(SelectTasksConstants.DATE_INDEX, date);
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
            preparedStatement.setString(SelectTasksConstants.USER_ID_INDEX, userId);
            return getTaskList(preparedStatement);
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addTask(String userId, Task task) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement taskPreparedStatement = connection.prepareStatement(InsertTaskConstants.SQL_INSERT_INTO_TASK)
        )
        {
            boolean isNotExists = isNotExists(connection, userId, task.getName());
            if(isNotExists) {
                taskPreparedStatement.setString(InsertTaskConstants.USER_ID_INDEX, userId);
                taskPreparedStatement.setString(InsertTaskConstants.TASK_NAME_INDEX, task.getName());
                taskPreparedStatement.setString(InsertTaskConstants.TASK_DESCRIPTION_INDEX, task.getDescription());
                taskPreparedStatement.setDate(InsertTaskConstants.DATE_INDEX, task.getDate());
                taskPreparedStatement.setString(InsertTaskConstants.FILE_NAME_INDEX, task.getFileName());
                taskPreparedStatement.executeUpdate();
            }
            return isNotExists;

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private boolean isNotExists(Connection connection, String userId, String taskName) throws SQLException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SelectTaskNameConstants.SQL_SELECT_SELECT_TASK_NAME)) {
            preparedStatement.setString(SelectTaskNameConstants.USER_ID_INDEX, userId);
            preparedStatement.setString(SelectTaskNameConstants.TASK_NAME_INDEX, taskName);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return !resultSet.next();
            }
        }
    }

    @Override
    public void moveTask(String taskId, String query) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(TaskFunctionConstants.ID_INDEX, taskId);
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }



    @Override
    public void removeTasks(String [] taskIds) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TaskFunctionConstants.SQL_DELETE_TASK)) {
            for(String taskId : taskIds) {
                preparedStatement.setString(TaskFunctionConstants.ID_INDEX, taskId);
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
            PreparedStatement preparedStatement = connection.prepareStatement(UpdateFileNameConstants.SQL_UPDATE_FILE_NAME)) {

            preparedStatement.setString(UpdateFileNameConstants.FILE_NAME_QUERY_INDEX, fileName);
            preparedStatement.setString(UpdateFileNameConstants.FILE_NAME_USER_ID_QUERY_INDEX, userId);
            preparedStatement.setString(UpdateFileNameConstants.FILE_NAME_TASK_NAME_QUERY_INDEX, taskName);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static TaskDatabaseImplementation getTaskDarabaseImplementation() {
        return taskDatabaseImplementation;
    }
}
