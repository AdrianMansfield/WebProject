package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.user.User;
import by.gsu.epamlab.exceptions.DaoException;

public interface IUserDAO {
    User getUser(String login, String password) throws DaoException;
    boolean setUser(String login, String password) throws DaoException;
}
