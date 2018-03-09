package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.ConferenceDatabaseImplementation;
import by.gsu.epamlab.interfaces.IConferenceDAO;

public enum ConferenceLocationTypes {
    MAIN {

        @Override
        public void moveConference(String conferenceId) throws DaoException {
            I_CONFERENCE_DAO.moveConference(conferenceId, DatabaseConstants.SQL_GET_FOR_MAIN);
        }

    }, FIXED {

        @Override
        public void moveConference(String conferenceId) throws DaoException {
            I_CONFERENCE_DAO.moveConference(conferenceId, DatabaseConstants.SQL_THROW_INTO_FIXED);
        }

    }, BASKET {

        @Override
        public void moveConference(String conferenceId) throws DaoException {
            I_CONFERENCE_DAO.moveConference(conferenceId, DatabaseConstants.SQL_THROW_INTO_BASKET);
        }

    };

    public abstract void moveConference(String conferenceId) throws DaoException;

    protected static final IConferenceDAO I_CONFERENCE_DAO = new ConferenceDatabaseImplementation();
}
