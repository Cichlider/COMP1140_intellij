package ws12a.exceptions;

/**
 * An exception signalling that a cinema is already in use during
 * a particular time.
 * @apiNote PROVIDED TO YOU - DO NOT CHANGE
 */
public class CinemaInUseException extends Exception {
    /**
     * Creates a new CinemaInUseExceptions
     * @param message a message to display with the exception
     */
    public CinemaInUseException(String message) {
        super(message);
    }
}
