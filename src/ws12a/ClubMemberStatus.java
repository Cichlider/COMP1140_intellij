package ws12a;

/**
 * Represents the status level of a movie theatre club member
 * @apiNote PROVIDED TO YOU - DO NOT CHANGE
 */
public enum ClubMemberStatus {
    /**
     * The default membership status of a new club member.
     * Corresponds to no special discounts, just an opportunity to pay for tickets later.
     */
    BRONZE,

    /**
     * An improved membership status - grants a 10% discount from the base ticket price.
     */
    SILVER,

    /**
     * The top membership status - grants a 20% discount from the base ticket price.
     */
    GOLD
}
