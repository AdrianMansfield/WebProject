package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.event.Event;
import by.gsu.epamlab.beans.conference.Conference;
import by.gsu.epamlab.exceptions.DaoException;

import java.sql.*;
import java.util.List;

public interface IConferenceDAO {
    List<Conference> getConferences(String userId, String query, Date date) throws DaoException;

    List<Event> getEvents(String conferenceName) throws DaoException;

    void addConference(String userId, Conference conference) throws DaoException;

    void workWithBasket(String [] conferenceIds, String query) throws DaoException;

    void addEvent(String conferenceId, Event event) throws DaoException;

    void removeEvents(String [] eventIds) throws DaoException;

    void removeConferences(String [] conferenceIds) throws DaoException;

    void getOutOfBasket(String [] conferenceIds) throws DaoException;

    public List<Conference> getConferences(String userId, String query) throws DaoException;
}
