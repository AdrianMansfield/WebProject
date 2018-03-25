package by.gsu.epamlab.exceptions;

public class FileProblemException extends RuntimeException {

    public FileProblemException() {
        super();
    }

    public FileProblemException(String message) {
        super(message);
    }

    public FileProblemException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileProblemException(Throwable cause) {
        super(cause);
    }
}
