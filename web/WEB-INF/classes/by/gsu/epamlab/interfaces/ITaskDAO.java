package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.Event;
import by.gsu.epamlab.beans.Task;
import by.gsu.epamlab.exceptions.DaoException;
import javax.servlet.http.Part;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface ITaskDAO {
    List<Task> getTasks(String userId, String query, Date date) throws DaoException;
    List<Event> getEvents(String conferenceName) throws DaoException;
    void addTask(String userId, Task task) throws DaoException;
}
