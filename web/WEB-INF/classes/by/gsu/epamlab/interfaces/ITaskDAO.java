package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.model.task.Task;
import by.gsu.epamlab.exceptions.DaoException;

import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(String userId, String taskType) throws DaoException;

    boolean addTask(String userId, Task task, String taskType) throws DaoException;

    void moveTask(String taskId, String locationType) throws DaoException;

    void removeTasks(String [] taskIds) throws DaoException;

    void updateFileName(String userId, String fileName, String taskName) throws DaoException;
}
