package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.Event;
import by.gsu.epamlab.beans.Conference;
import by.gsu.epamlab.exceptions.DaoException;

import java.sql.Date;
import java.util.List;

public interface IConferenceDAO {
    List<Conference> getTasks(String userId, String query, Date date) throws DaoException;
    List<Event> getEvents(String conferenceName) throws DaoException;
    void addTask(String userId, Conference task) throws DaoException;
}
