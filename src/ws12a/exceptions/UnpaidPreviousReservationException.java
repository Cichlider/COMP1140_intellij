package ws12a.exceptions;

/**
 * An exception signalling that a club member may not make a new reservation
 * because they still have an outstanding balance from making but not
 * paying for a previous reservation.
 * @apiNote PROVIDED TO YOU - DO NOT CHANGE
 */
public class UnpaidPreviousReservationException extends Exception {
    /**
     * The current balance of the club member, a negative value
     */
    public final int balance;

    /**
     * Creates a new UnpaidPreviousReservationException
     * @param balance the current balance of a club member, a negative value,
     *                which caused the exception
     */
    public UnpaidPreviousReservationException(int balance) {
        super("There are unpaid previous reservations! Balance: "+balance);
        this.balance = balance;
    }
}
