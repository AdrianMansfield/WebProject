package by.gsu.epamlab.model.database;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.NoDatabaseConnection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private static  DataSource dataSource;

    static {

        try {

            InitialContext initialContext =  new InitialContext();

            dataSource = (DataSource) initialContext.lookup(Constants.RESOURCE_NAME); //may be move this parameter to context

        } catch (NamingException e) {

            throw new NoDatabaseConnection(e);

        }
    }

    public static Connection getConnection() throws SQLException {

        return dataSource.getConnection();

    }

}
