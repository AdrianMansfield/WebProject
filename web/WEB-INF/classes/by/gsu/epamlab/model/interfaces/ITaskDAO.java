package by.gsu.epamlab.model.interfaces;

import by.gsu.epamlab.model.task.Task;
import by.gsu.epamlab.exceptions.DaoException;

import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(String userId, String taskType) throws DaoException;

    boolean addTask(String userId, Task task, String taskType) throws DaoException;

    void moveTask(String taskId, String locationType) throws DaoException;

    void removeTasks(String [] taskIds) throws DaoException;

    void updateFileName(String userId, Task task) throws DaoException;

    List<Task> getTasksById(String userId, String [] taskIds) throws DaoException;

    Task getTaskById(String userId, String taskId) throws DaoException;

    void changeTaskInfo(String taskId, String infoType, String taskAttribute) throws DaoException;

    //void updateFilesName(String userId, List<Task> taskList) throws DaoException;

    Task getTaskByName(String userId, String taskName) throws DaoException;
}
