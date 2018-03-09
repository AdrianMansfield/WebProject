package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.conference.Conference;
import by.gsu.epamlab.exceptions.DaoException;

import java.sql.*;
import java.util.List;

public interface IConferenceDAO {
    List<Conference> getConferences(String userId, String query, Date date) throws DaoException;

    void addConference(String userId, Conference conference) throws DaoException;

    void moveConference(String conferenceId, String query) throws DaoException;

    void removeConferences(String [] conferenceIds) throws DaoException;

    List<Conference> getConferences(String userId, String query) throws DaoException;
}
