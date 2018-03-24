package by.gsu.epamlab.exceptions;

public class NonHttpMethodException extends RuntimeException {

    public NonHttpMethodException() {
        super();
    }

    public NonHttpMethodException(String message) {
        super(message);
    }

    public NonHttpMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonHttpMethodException(Throwable cause) {
        super(cause);
    }
}
