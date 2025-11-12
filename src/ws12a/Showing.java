package ws12a;

import java.time.ZonedDateTime;

/**
 * Represents a single showing of a movie at a movie theatre
 * @apiNote PROVIDED TO YOU - DO NOT CHANGE
 */
public class Showing {

    /**
     * A unique identifier for the showing within the movie theatre that contains it.
     * Identifiers are always greater than 0.
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    public final int id;
    /**
     * The movie that is to be shown
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    public final Movie movie;
    /**
     * The cinema in which the movie will be shown
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    public final Cinema cinema;
    /**
     * The start time of the showing
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    public ZonedDateTime startTime;

    /**
     * Creates a new Showing instance
     * @param id the identifier of the new showing
     * @param movie the movie that will be shown
     * @param cinema the cinema in which the movie will be shown
     * @param startTime the start time of the showing
     */
    public Showing(int id, Movie movie, Cinema cinema, ZonedDateTime startTime) {
        this.id = id;
        this.movie = movie;
        this.cinema = cinema;
        this.startTime = startTime;
        // FIXME complete method
    }

    /**
     * Tries to find some available seats for a particular showing,
     * given a maximum rate for each seat
     * @param seatCount the number of seats that should be next to each other,
     *                  within a single row
     * @param maxRate the maximum rate for any seat in the block. Solutions
     *                that include more expensive seats should not be considered
     * @return the first seat in a block of available seats (all in the same row,
     *         all with a higher or equal number as the first seat, and all
     *         consecutive numbers), all of which have a rate less than or equal
     *         to the maxRate, or null if no such block exists.
     *         For maximum marks, optimize the result such that it is optimal for
     *         the movie theatre, i.e. the combined rates of the seats in the
     *         blocks are as high as possible while staying within the maxRate
     *         constraint.
     */
    public Seat findFreeSeats(int seatCount, int maxRate) {
        return null; // FIXME complete method
    }
}
