package ws12a;

import ws12a.exceptions.CinemaInUseException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room for showing movies in a movie theatre
 */
public class Cinema {
    /**
     * A unique identifier for the cinema within the movie theatre that contains it.
     * Identifiers are always greater than 0.
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    public final int id;
    /**
     * A name for the cinema by which movie goers know where to go
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    public final String name;
    /**
     * A jagged array of arrays representing the value of the seats in the cinema,
     * in percent (i.e. any seat whose value is 100 has the standard ticket price,
     * while a seat with value 120 costs 20% more than the standard ticket price).
     * The first dimension represents the rows of seats, and the second dimension
     * the seats in a particular row - different rows may have different numbers
     * of seats.
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    public final int[][] seatRates;


    private List<Showing> showings = new ArrayList<>();  // 存储所有场次

    /**
     * Creates a new Cinema object.
     * @param id the identifier for the cinema > 0
     * @param name a name for the cinema
     * @param seatRates the rates for the seats as described in the seatRates
     *                  field above
     */
    public Cinema(int id, String name, int[][] seatRates) {
        this.name = name;
        this.seatRates = seatRates;
        this.id = id;
        this.showings = new ArrayList<>();
    }

    /**
     * Adds a showing for a given movie to the given Cinema
     * @param nextShowingId an identifier to use for the new showing if it can be created
     * @param movie the movie that should be shown
     * @param startTime the start time of the showing
     * @return a new Showing object representing the desired showing
     * @throws CinemaInUseException if there is already another showing planned at
     *   this cinema whose showtime overlaps with the proposed new showing. This is
     *   calculated by taking the starting times of each showing and considering
     *   the length of each movie in minutes. The start time of a showing must be
     *   strictly later than the end time of any previous showing.
     */
    public Showing addShowing(int nextShowingId, Movie movie, ZonedDateTime startTime) throws CinemaInUseException {
        return new Showing(nextShowingId,movie,this,startTime);
    }
}
