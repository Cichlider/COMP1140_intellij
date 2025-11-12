package ws12a.exceptions;

/**
 * An exception signalling that a user tried to get a refund
 * by trying to "pay" a negative amount.
 * @apiNote PROVIDED TO YOU - DO NOT CHANGE
 */
public class NoRefundsException extends Exception {
    /**
     * Creates a new NoRefundsException
     */
    public NoRefundsException() {
        super("No refunds!");
    }
}
