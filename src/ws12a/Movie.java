package ws12a;

/**
 * Represents a movie to be shown at a movie theatre
 * @apiNote PROVIDED TO YOU - DO NOT CHANGE
 */
public class Movie {
    /**
     * A unique identifier for the movie within the movie theatre that contains it.
     * Identifiers are always greater than 0.
     */
    public final int id;
    /**
     * The name of the movie
     */
    public final String name;
    /**
     * The genre of the movie
     */
    public final String genre;
    /**
     * The length of the movie in minutes
     */
    public final int length;

    /**
     * Creates a new Movie instance
     * @param id the identifier for the new movie
     * @param name the name of the movie
     * @param genre the genre of the movie
     * @param length the length of the movie in minutes
     */
    public Movie(int id, String name, String genre, int length) {
        this.name = name;
        this.genre = genre;
        this.length = length;
        this.id = id;
    }
}
