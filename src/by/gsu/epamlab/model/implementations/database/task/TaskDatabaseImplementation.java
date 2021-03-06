package by.gsu.epamlab.model.implementations.database.task;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.model.implementations.database.task.enums.*;
import by.gsu.epamlab.model.task.Task;
import by.gsu.epamlab.model.implementations.database.constants.*;
import by.gsu.epamlab.model.database.DatabaseConnection;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.interfaces.ITaskDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class TaskDatabaseImplementation implements ITaskDAO {

    private static final TaskDatabaseImplementation taskDatabaseImplementation = new TaskDatabaseImplementation();

    private TaskDatabaseImplementation() {}


    @Override
    public List<Task> getTasks(String userId, String taskType) throws DaoException {

        try(Connection connection = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SelectTaskTypes.valueOf(taskType).getSelectTaskQuery())

        )

        {

            preparedStatement.setString(SelectTasksConstants.USER_ID_INDEX, userId);

            preparedStatement.setDate(SelectTasksConstants.DATE_INDEX, DateTypes.valueOf(taskType).getDate());

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

        catch (SQLException e) {

            throw new DaoException(e);

        }
    }

    @Override
    public Task getTaskById(String userId, String taskId) throws DaoException {

        try(Connection connection = DatabaseConnection.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(SelectTask.SELECT_TASK_BY_ID))

        {

            preparedStatement.setString(SelectTask.USER_ID, userId);

            preparedStatement.setString(SelectTask.TASK_PARAMETER, taskId);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                String taskName = null;

                String description = null;

                String stringDate = null;

                String fileName = null;

                if(resultSet.next()) {

                    taskName = resultSet.getString(SelectTask.TASK_NAME_COLUMN_INDEX);

                    description = resultSet.getString(SelectTask.TASK_DESCRIPTION_COLUMN_INDEX);

                    stringDate = resultSet.getString(SelectTask.TASK_DATE_COLUMN_INDEX);

                    fileName = resultSet.getString(SelectTask.TASK_FILE_NAME_COLUMN_INDEX);

                }

                return new Task(Integer.parseInt(taskId), taskName, description, stringDate, fileName);

            }
        }

        catch (SQLException e) {

            throw new DaoException(e);
        }
    }

    private String getParameterListForOperatorIn(int count) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(Constants.QUESTION_MARK);

        for(int i = 1; i < count; i++) {

            stringBuilder.append(Constants.COMMA).append(Constants.QUESTION_MARK);

        }

        return stringBuilder.toString();
    }

    public List<Task> getTasksById(String userId, String [] taskIds) throws DaoException {

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SelectTask.SELECT_TASK_BY_IDS_HEAD
                + getParameterListForOperatorIn(taskIds.length) + SelectTask.SELECT_TASK_BY_IDS_TAIL))

        {

            preparedStatement.setString(SelectTask.USER_ID, userId);

            int i = SelectTask.USER_ID + 1;

            for(String taskId : taskIds) {

                preparedStatement.setString(i++, taskId);

            }

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                List<Task> taskList = new ArrayList<>();

                while(resultSet.next()) {

                    int taskId = resultSet.getInt(SelectTask.TASK_ID_COLUMN_INDEX);

                    String taskName = resultSet.getString(SelectTask.TASK_NAME_COLUMN_INDEX);

                    String description = resultSet.getString(SelectTask.TASK_DESCRIPTION_COLUMN_INDEX);

                    String stringDate = resultSet.getString(SelectTask.TASK_DATE_COLUMN_INDEX);

                    String fileName = resultSet.getString(SelectTask.TASK_FILE_NAME_COLUMN_INDEX);

                    Task task = new Task(taskId, taskName, description, stringDate, fileName);

                    taskList.add(task);

                }

                return taskList;
            }

        }
        catch (SQLException e) {

            throw new DaoException(e);

        }
    }


    @Override
    public boolean addTask(String userId, Task task, String taskType) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement taskPreparedStatement = connection.prepareStatement(InsertTaskConstants.SQL_INSERT_INTO_TASK)
        )
        {
            boolean isNotExists = isNotExists(connection, userId, task.getName());

            if(isNotExists) {

                AddTaskDateTypes.valueOf(taskType).setTaskDate(task);

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
        try(PreparedStatement preparedStatement = connection.prepareStatement(SelectTaskNameConstants
                .SQL_SELECT_SELECT_TASK_NAME))
        {

            preparedStatement.setString(SelectTaskNameConstants.USER_ID_INDEX, userId);

            preparedStatement.setString(SelectTaskNameConstants.TASK_NAME_INDEX, taskName);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                return !resultSet.next();

            }
        }
    }

    @Override
    public void moveTask(String taskId, String locationType) throws DaoException {
        try(Connection connection = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(LocationChangeTypes.valueOf(locationType)
                    .getMoveTaskQuery())) {

            preparedStatement.setString(TaskFunctionConstants.ID_INDEX, taskId);

            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {

            throw new DaoException(e);

        }
    }

    @Override
    public void changeTaskInfo(String taskId, String infoType, String taskAttribute) throws DaoException {

        try(Connection connection = DatabaseConnection.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(TaskInfoChangeType.valueOf(infoType).getChangeTaskInfoQuery())){

            preparedStatement.setString(TaskInfoChangeConstants.ID_INDEX, taskId);

            preparedStatement.setString(TaskInfoChangeConstants.TASK_INFO_INDEX, taskAttribute);

            preparedStatement.executeUpdate();


        } catch (SQLException e){

            throw new DaoException(e);

        }

    }

    @Override
    public void removeTasks(String [] taskIds) throws DaoException {

        try(Connection connection = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(TaskFunctionConstants.SQL_DELETE_TASK)) {

            connection.setAutoCommit(false);

            for(String taskId : taskIds) {

                preparedStatement.setString(TaskFunctionConstants.ID_INDEX, taskId);

                preparedStatement.addBatch();

            }

            preparedStatement.executeBatch();

            connection.commit();
        }

        catch (SQLException e) {

            throw new DaoException(e);

        }
    }

    @Override
    public void updateFileName(String userId, Task task) throws DaoException {

        try(Connection connection = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(UpdateFileNameConstants.SQL_UPDATE_FILE_NAME)) {


            preparedStatement.setString(UpdateFileNameConstants.FILE_NAME_QUERY_INDEX, task.getFileName());

            preparedStatement.setString(UpdateFileNameConstants.FILE_NAME_USER_ID_QUERY_INDEX, userId);

            preparedStatement.setString(UpdateFileNameConstants.FILE_NAME_TASK_NAME_QUERY_INDEX, task.getName());

            preparedStatement.executeUpdate();

        }

        catch (SQLException e) {

            throw new DaoException(e);

        }
    }


    @Override
    public Task getTaskByName(String userId, String taskName) throws DaoException {

        try(Connection connection = DatabaseConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SelectTask.SELECT_TASK_BY_NAME))

        {

            preparedStatement.setString(SelectTask.USER_ID, userId);

            preparedStatement.setString(SelectTask.TASK_PARAMETER, taskName);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                int taskId = Constants.ZERO;

                String description = null;

                String stringDate = null;

                String fileName = null;

                if(resultSet.next()) {

                    taskId = resultSet.getInt(SelectTask.TASK_ID_COLUMN_INDEX);

                    description = resultSet.getString(SelectTask.TASK_DESCRIPTION_COLUMN_INDEX);

                    stringDate = resultSet.getString(SelectTask.TASK_DATE_COLUMN_INDEX);

                    fileName = resultSet.getString(SelectTask.TASK_FILE_NAME_COLUMN_INDEX);
                }

                return new Task(taskId, taskName, description, stringDate, fileName);

            }
        }

        catch (SQLException e) {

            throw new DaoException(e);

        }
    }


    public static TaskDatabaseImplementation getTaskDatabaseImplementation() {
        return taskDatabaseImplementation;
    }
}
