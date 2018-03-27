package by.gsu.epamlab.model.database;

import by.gsu.epamlab.control.listeners.ApplicationContextParameter;
import by.gsu.epamlab.exceptions.NoDatabaseConnection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private final static DataSource dataSource;

    static {

        try {

            InitialContext initialContext =  new InitialContext();

            dataSource = (DataSource) initialContext.lookup(ApplicationContextParameter.getResourceName());

        } catch (NamingException e) {

            throw new NoDatabaseConnection(e);

        }
    }

    public static Connection getConnection() throws SQLException {

        return dataSource.getConnection();

    }

}
