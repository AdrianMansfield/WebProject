package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.Event;
import by.gsu.epamlab.beans.Conference;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.database.DatabaseConnection;
import by.gsu.epamlab.exceptions.DaoException;

import java.sql.*;
import java.util.List;

public interface IConferenceDAO {
    List<Conference> getConferences(String userId, String query, Date date) throws DaoException;

    List<Event> getEvents(String conferenceName) throws DaoException;

    void addConference(String userId, Conference conference) throws DaoException;

    void workWithBasket(String [] conferenceIds, String query) throws DaoException;

    void removeConferenceEvents(String conferenceId) throws DaoException;

    void addEvent(String conferenceId, Event event) throws DaoException;

    void removeEvents(String [] eventIds) throws DaoException;

    void removeConferences(String [] conferenceIds) throws DaoException;
}
