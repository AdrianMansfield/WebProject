package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.beans.conference.Conference;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.ConferenceDatabaseImplementation;
import by.gsu.epamlab.interfaces.IConferenceDAO;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public enum ConferencePrintTypes {
    TODAY   {

        @Override
        public List<Conference> getConferences(String userId) throws DaoException {
            return I_CONFERENCE_DAO.getConferences(userId, DatabaseConstants.SQL_SELECT_TODAY_TASKS, getTomorrowDay());
        }

    }, TOMORROW {

        @Override
        public List<Conference> getConferences(String userId) throws DaoException {
            return I_CONFERENCE_DAO.getConferences(userId, DatabaseConstants.SQL_SELECT_TOMORROW_TASKS, getTomorrowDay());
        }


    }, SOMEDAY  {

        @Override
        public List<Conference> getConferences(String userId) throws DaoException {
            return I_CONFERENCE_DAO.getConferences(userId, DatabaseConstants.SQL_SELECT_SOMEDAY_TASKS, getTomorrowDay());
        }

    }, FIXED {

        @Override
        public List<Conference> getConferences(String userId) throws DaoException {
            return I_CONFERENCE_DAO.getConferences(userId, DatabaseConstants.SQL_SELECT_FIXED_TASKS);
        }
    }, BASKET {

        @Override
        public List<Conference> getConferences(String userId) throws DaoException {
            return I_CONFERENCE_DAO.getConferences(userId, DatabaseConstants.SQL_SELECT_BASKET_TASKS);
        }
    };

    protected static final IConferenceDAO I_CONFERENCE_DAO = new ConferenceDatabaseImplementation();


    public abstract List<Conference> getConferences(String userId) throws DaoException;


    protected Date getTomorrowDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);
        return new Date(calendar.getTime().getTime());
    }
}
