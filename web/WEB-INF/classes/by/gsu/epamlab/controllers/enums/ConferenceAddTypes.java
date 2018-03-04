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

public enum ConferenceAddTypes {
    TODAY  {

        private Date getTodayDate() {
            Calendar calendar = Calendar.getInstance();
            return new Date(calendar.getTime().getTime());
        }

        @Override
        public void addConference(String userId, Conference conference) throws DaoException {
            conference.setDate(getTodayDate());
            I_CONFERENCE_DAO.addConference(userId, conference);
        }
    }, TOMORROW {


        private Date getTomorrowDay() {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);
            return new Date(calendar.getTime().getTime());
        }

        @Override
        public void addConference(String userId, Conference conference) throws DaoException {
            conference.setDate(getTomorrowDay());
            I_CONFERENCE_DAO.addConference(userId, conference);
        }

    }, SOMEDAY  {

        @Override
        public void addConference(String userId, Conference conference) throws DaoException {
            I_CONFERENCE_DAO.addConference(userId, conference);
        }

    };


    protected static final IConferenceDAO I_CONFERENCE_DAO = new ConferenceDatabaseImplementation();

    public abstract void addConference(String userId, Conference conference) throws DaoException;


}
