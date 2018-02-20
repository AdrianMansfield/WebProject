package by.gsu.epamlab.exceptions;

public class NonGetException extends RuntimeException {
    public NonGetException() {
        super();
    }

    public NonGetException(String message) {
        super(message);
    }

    public NonGetException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonGetException(Throwable cause) {
        super(cause);
    }
}
