package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.task.Task;
import by.gsu.epamlab.exceptions.DaoException;

import java.sql.*;
import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(String userId, String query, Date date) throws DaoException;

    void addTask(String userId, Task task) throws DaoException;

    void moveTask(String taskId, String query) throws DaoException;

    void removeTasks(String [] taskIds) throws DaoException;

    List<Task> getTasks(String userId, String query) throws DaoException;

    void updateFileName(String userId, String fileName, String taskName) throws DaoException;
}
