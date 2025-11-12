package ws12a.exceptions;

/**
 * An exception signalling that an identifier was not
 * valid for a particular kind of data
 * @apiNote PROVIDED TO YOU - DO NOT CHANGE
 */
public class InvalidIdentifierException extends Exception{
    /**
     * A String describing the kind of data for which an
     * identifier was used. For example, "Cinema", "Movie",
     * or "Showing".
     */
    public final String objectKind;
    /**
     * The invalid identifier
     */
    public final int identifier;

    /**
     * Creates a new InvalidIdentifierException
     * @param objectKind a String describing the kind of data for which
     *                   the identifier was used
     * @param identifier the invalid identifier
     */
    public InvalidIdentifierException(String objectKind, int identifier){
        super("Invalid "+objectKind+" identifier :" + identifier);
        this.objectKind = objectKind;
        this.identifier = identifier;
    }
}
