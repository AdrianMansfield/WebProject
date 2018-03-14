package by.gsu.epamlab.model.interfaces;

import by.gsu.epamlab.model.user.User;
import by.gsu.epamlab.exceptions.DaoException;

public interface IUserDAO {
    User getUser(String login, String password) throws DaoException;
    boolean setUser(String login, String password) throws DaoException;
}
