package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.exceptions.DaoException;

public interface IUserDAO {
    User getUser(String login, String password) throws DaoException;
    User setUser(String login, String password) throws DaoException;
}
