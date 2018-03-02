package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.ConferenceDatabaseImplementation;
import by.gsu.epamlab.interfaces.IConferenceDAO;

public enum  PlaceType {
    FIXED {
        @Override
        public void throwConferencesToBasket(String [] conferenceIds) throws DaoException {
            I_CONFERENCE_DAO.workWithBasket(conferenceIds, DatabaseConstants.SQL_INSERT_INTO_BASKET_FROM_FIXED);
        }
    },

    CONFERENCE {
        @Override
        public void throwConferencesToBasket(String [] conferenceIds) throws DaoException {
            I_CONFERENCE_DAO.workWithBasket(conferenceIds, DatabaseConstants.SQL_INSERT_INTO_BASKET_FROM_CONFERENCE);
        }
    };

    public abstract void throwConferencesToBasket(String [] conferenceIds) throws DaoException;

    private static final IConferenceDAO I_CONFERENCE_DAO = new ConferenceDatabaseImplementation();
}
