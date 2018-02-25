package by.gsu.epamlab.controllers.enums;

import by.gsu.epamlab.beans.Conference;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.DatabaseConstants;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.implementations.ConferenceDatabaseImplementation;
import by.gsu.epamlab.interfaces.IConferenceDAO;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public enum ConferenceTypes {
    TODAY   {

        @Override
        public List<Conference> getTasks(String userId) throws DaoException {
            return I_CONFERENCE_DAO.getTasks(userId, DatabaseConstants.SQL_SELECT_TODAY_TASKS, getTomorrowDay());
        }

        private Date getTodayDate() {
            Calendar calendar = Calendar.getInstance();
            return new Date(calendar.getTime().getTime());
        }

        @Override
        public void addTask(String userId, Conference task) throws DaoException {
            task.setDate(getTodayDate());
            I_CONFERENCE_DAO.addTask(userId, task);
        }
    }, TOMORROW {

        @Override
        public List<Conference> getTasks(String userId) throws DaoException {
            return I_CONFERENCE_DAO.getTasks(userId, DatabaseConstants.SQL_SELECT_TOMORROW_TASKS, getTomorrowDay());
        }

        @Override
        public void addTask(String userId, Conference task) throws DaoException {
            task.setDate(getTomorrowDay());
            I_CONFERENCE_DAO.addTask(userId, task);
        }

    }, SOMEDAY  {

        @Override
        public List<Conference> getTasks(String userId) throws DaoException {
            return I_CONFERENCE_DAO.getTasks(userId, DatabaseConstants.SQL_SELECT_SOMEDAY_TASKS, getTomorrowDay());
        }

        @Override
        public void addTask(String userId, Conference task) throws DaoException {
            I_CONFERENCE_DAO.addTask(userId, task);
        }

    };

    protected static final IConferenceDAO I_CONFERENCE_DAO = new ConferenceDatabaseImplementation();


    public abstract List<Conference> getTasks(String userId) throws DaoException;

    public abstract void addTask(String userId, Conference task) throws DaoException;

    protected Date getTomorrowDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, Constants.DIFFERENCE_DAY);
        return new Date(calendar.getTime().getTime());
    }
}
