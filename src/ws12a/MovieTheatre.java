package ws12a;

import ws12a.exceptions.*;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a movie theatre and the necessary data for its
 * reservation system.
 */
public class MovieTheatre {

    /**
     * The next unique identifier for a new movie to be added
     * to the theatre
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    private int nextMovieId=1;
    /**
     * A map from unique identifiers to movies.
     * @implSpec Invariant: if an integer maps to a Movie object,
     *                that object's id field matches the integer
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    private final Map<Integer, Movie> movies=new HashMap<>();

    /**
     * The next unique identifier for a new cinema to be added
     * to the theatre
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    private int nextCinemaId=1;
    /**
     * A map from unique identifiers to cinemas.
     * @implSpec Invariant: if an integer maps to a Cinema object,
     *                that object's id field matches the integer
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    private final Map<Integer, Cinema> cinemas=new HashMap<>();

    /**
     * The next unique identifier for a new showing to be added
     * to the theatre
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    private int nextShowingId=1;
    /**
     * A map from unique identifiers to showings.
     * @implSpec Invariant: if an integer maps to a Showing object,
     *                that object's id field matches the integer
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    private final Map<Integer, Showing> showings=new HashMap<>();

    //如果不用private final的话，会有什么事情发生使得程序出现问题？

    private int nextMemberId = 1;


    private final Map<Integer,Member> members=new HashMap();


    /**
     * Creates a new movie theatre object
     */
    public MovieTheatre() {
        // FIXME complete method
    }

    public int addMember(String name, int id, ClubMemberStatus status){
        Member member = new Member(name, nextMemberId++ ,status , 0 , 0);
        members.put(member.id, member);
        return member.id;
    }

    public Member getMemberById(int id) throws InvalidIdentifierException{
        if(members.containsKey(id)){
            return members.get(id);
        }
        throw new InvalidIdentifierException("Member", id);
    }


    /**
     * Adds a new movie to the movie theatre's database
     * @param name the name of the movie
     * @param genre the genre of the movie
     * @param length the length of the movie in minutes
     * @return the unique ID of the new movie entry
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    public int addMovie(String name, String genre, int length) {
        Movie movie=new Movie(nextMovieId++, name, genre, length);
        movies.put(movie.id, movie);
        return movie.id;
    }

    /**
     * Looks up a movie by its unique identifier
     * @param id the identifier of the movie
     * @return the Movie object representing the movie in the theatre's data
     * @throws InvalidIdentifierException the given identifier does not match
     *    any movie in the movie theatres' data
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    Movie getMovieById(int id) throws InvalidIdentifierException {
        if(movies.containsKey(id)) {
            return movies.get(id);
        }
        throw new InvalidIdentifierException("Movie", id);
    }

    /**
     * Adds a new cinema to the movie theatre
     * @param name the name of the cinema
     * @param seatRates an array of arrays of integers, representing the rates
     *                  of the seats in the cinema, in percentages.
     *                  The first level of the array represents rows of seats,
     *                  while the second level represents each seat in a particular
     *                  row (different rows can have different numbers of seats).
     *                  If an entry for a particular seat is 100, then a ticket for
     *                  that seat costs exactly the base ticket price, while if the
     *                  entry is 120, the ticket costs 20% more than the base ticket
     *                  price.
     * @return the unique identifier for the new cinema
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    public int addCinema(String name, int[][] seatRates) {
        Cinema cinema =new Cinema(nextCinemaId++, name, seatRates);
        cinemas.put(cinema.id, cinema);
        return cinema.id;
    }

    /**
     * Looks up a cinema by its unique identifier
     * @param id the identifier of the cinema
     * @return the Cinema object representing the cinema in the theatre's data
     * @throws InvalidIdentifierException the given identifier does not match
     *    any cinema in the movie theatres' data
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    Cinema getCinemaById(int id) throws InvalidIdentifierException {
        if(cinemas.containsKey(id)) {
            return cinemas.get(id);
        }
        throw new InvalidIdentifierException("Cinema", id);
    }

    /**
     * Tries to schedule a new showing
     * @param movieId the identifier of the movie to be shown
     * @param cinemaId the identifier of the cinema in which the movie should be shown
     * @param startTime the starting time of the showing
     * @return the unique identifier for the new showing
     * @throws CinemaInUseException if the cinema is already scheduled for a different
     *     showing for at least part of the duration of the proposed showing.
     *     The duration of a showing is from the start time the length of the shown
     *     movie in minutes later. The start time of a showing must be strictly later
     *     than the end time of any previous showing.
     * @throws InvalidIdentifierException if any of the given identifiers does not
     *     refer to a valid object of the respective kind
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    public int addShowing(int movieId, int cinemaId, ZonedDateTime startTime) throws CinemaInUseException, InvalidIdentifierException {
        Showing showing = getCinemaById(cinemaId).addShowing(nextShowingId, getMovieById(movieId), startTime);
        nextShowingId++;
        showings.put(showing.id, showing);
        return showing.id;
    }

    /**
     * Looks up a showing by its unique identifier
     * @param id the identifier of the showing
     * @return the Showing object representing the showing in the theatre's data
     * @throws InvalidIdentifierException the given identifier does not match
     *    any showing in the movie theatres' data (including when the showing
     *    existed before, but has been cancelled)
     * @apiNote PROVIDED TO YOU - DO NOT CHANGE
     */
    Showing getShowingById(int id) throws InvalidIdentifierException {
        if(showings.containsKey(id)) {
            return showings.get(id);
        }
        throw new InvalidIdentifierException("Showing", id);
    }

    /**
     * Cancels a given showing, crediting the cost for all reservations
     * made for it. This is essentially the same effect as if all reservations
     * for that showing had been cancelled before the showing itself was removed.
     * @param showingId the identifier of the showing to be cancelled
     * @throws InvalidIdentifierException if the given identifier does not refer
     *   to a valid (and still uncancelled) showing
     */
    public void cancelShowing(int showingId) throws InvalidIdentifierException {
        // FIXME complete method
    }

    /**
     * Registers a new movie theatre club member
     * @param name the name of the new club member
     * @param initialStatus the initial status of the club member. By default, this
     *                      should be ClubMemberStatus.BRONZE, but there may be special
     *                      events at which higher-level statuses might be given out
     *                      as a bonus (or, really, we just want to test higher statuses
     *                      more easily :) )
     * @return the unique identifier of the new club member
     */
    public int registerClubMember(String name, ClubMemberStatus initialStatus) {
        Member a = new Member(name,nextMemberId++,initialStatus, 0, 0);
        members.put(a.id,a);
        return a.id;
    }

    /**
     * Tries to find some available seats for a particular showing,
     * given a maximum rate for each seat. A seat is free if it
     * exist in the cinema (by having a seatRate) in which the movie
     * is shown and no uncancelled reservation that would reserve the
     * seat exists.
     * @param showingId the identifier of the showing for which to find seats
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
     * @throws InvalidIdentifierException if the given showing identifier does not
     *         refer to a valid showing
     */
    public Seat findFreeSeats(int showingId, int seatCount, int maxRate) throws InvalidIdentifierException {
        return getShowingById(showingId).findFreeSeats(seatCount, maxRate);
    }

    /**
     * Tries to reserve a number of seats for a particular showing
     * @param memberId the identifier of the club member making the reservation, or -1 for an anonymous reservation
     * @param showingId the identifier of the showing for which the reservation should be made
     * @param startSeat the first seat in a block of contiguous seats in a single row that should be reserved
     * @param seatCount the number of seats that should be reserved
     * @return a unique identifier for the new reservation, if it was made successfully (otherwise, see exceptions below)
     * @throws SeatsUnavailableException if any of the seats specified by the startSeat and seatCount parameters either
     *                                   does not exist or is already reserved
     * @throws InvalidIdentifierException if any of the given identifiers is invalid for its particular data type
     * @throws UnpaidPreviousReservationException if the reservation is for a club member, but the club member has
     *                                            an outstanding balance from an unpaid previous reservation
     */
    public int reserveTickets(int memberId, int showingId, Seat startSeat, int seatCount) throws SeatsUnavailableException, InvalidIdentifierException, UnpaidPreviousReservationException {
        return 0; // FIXME complete method
    }

    /**
     * Cancels a given reservation, giving credit for any money that was already paid for it (no refunds!).
     * Credit will be applied to the cost of future or existing unpaid reservations
     * @param reservationId the identifier of the reservation to be cancelled
     * @throws InvalidIdentifierException if the identifier does not refer to an existing uncancelled reservation
     */
    public void cancelReservation(int reservationId) throws InvalidIdentifierException {
        // FIXME complete method
    }

    /**
     * Makes a payment towards a club member's balance, which can both cover the cost of existing
     * unpaid reservations or create credit for future reservations. Payments may also affect
     * a user's status level, as described in the README.
     * @param memberId the identifier of the member to whose account the money should be paid
     * @param amount the amount of money that should be paid - must be positive
     * @return the new balance of the club member's account, in cents (as in getClubMemberBalance below)
     * @throws InvalidIdentifierException if the memberId did not reference a valid club member
     * @throws NoRefundsException if the amount parameter was negative
     */
    public int payTowardsClubMemberBalance(int memberId, int amount)
            throws InvalidIdentifierException, NoRefundsException {

        if (amount < 0) {  // 不允许负数
            throw new NoRefundsException();
        }

        Member m = members.get(memberId);
        if (m == null) {
            throw new InvalidIdentifierException("Member", memberId);
        }

        int newTotalPay = m.totalpay + amount;
        int newBalance = m.balance + amount;

        // 检查是否升级
        ClubMemberStatus newStatus = m.status;
        if (newTotalPay >= 100000) newStatus = ClubMemberStatus.GOLD;
        else if (newTotalPay >= 10000) newStatus = ClubMemberStatus.SILVER;

        // 更新记录
        Member updated = new Member(m.name, m.id, newStatus, newTotalPay, newBalance);
        members.put(m.id, updated);

        return newBalance;
    }


    /**
     * Returns the balance of a club member's account, in cents.
     * A positive balance represents a credit that will be applied to future reservations.
     * A negative balance represents outstanding debt from existing unpaid reservations.
     * @param memberId the identifier of a club member
     * @return the balance of the club member's account, in cents
     * @throws InvalidIdentifierException if the memberId did not reference a valid club member
     */
    public int getClubMemberBalance(int memberId) throws InvalidIdentifierException {
        if(members.containsKey(memberId)){
            return members.get(memberId).balance;
        }else{
            throw new InvalidIdentifierException("Member",memberId);
        }
    }

    /**
     * Returns the status level of the given club member, which affects their discounts
     * @param memberId the identifier of a club member
     * @return the status level of the given club member
     * @throws InvalidIdentifierException if the memberId did not reference a valid club member
     */
    public ClubMemberStatus getClubMemberStatus(int memberId) throws InvalidIdentifierException {
        if(members.containsKey(memberId)){
            return members.get(memberId).status;
        }else{
            throw new InvalidIdentifierException("Member",memberId);
        }
    }
}
