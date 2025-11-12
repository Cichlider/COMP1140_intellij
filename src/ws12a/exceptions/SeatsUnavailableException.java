package ws12a.exceptions;

import ws12a.Seat;

import java.util.List;

/**
 * An exception signalling that some seats involved in a reservation
 * request are unavailable, either because they are already reserved
 * or do not exist.
 * @apiNote PROVIDED TO YOU - DO NOT CHANGE
 */
public class SeatsUnavailableException extends Exception {
    /**
     * The list of seats that are unavailable, may be a subset of
     * the seats involved in the original request
     */
    public final List<Seat> unavailableSeats;

    /**
     * Creates a new SeatsUnavailableException
     * @param unavailableSeats the list of the seats that are unavailable
     *                         that caused the exception
     */
    public SeatsUnavailableException(List<Seat> unavailableSeats) {
        super("Seats unavailable: " + unavailableSeats);
        this.unavailableSeats = unavailableSeats;
    }
}
