package by.gsu.epamlab.exceptions;

public class NoDatabaseConnection extends RuntimeException {

    public NoDatabaseConnection() {
        super();
    }

    public NoDatabaseConnection(String message) {
        super(message);
    }

    public NoDatabaseConnection(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDatabaseConnection(Throwable cause) {
        super(cause);
    }

}
