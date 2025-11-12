package ws12a.tests;

import static org.junit.jupiter.api.Assertions.*;

import ws12a.ClubMemberStatus;
import ws12a.MovieTheatre;
import ws12a.Seat;
import ws12a.exceptions.*;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieTheatreTests {

    private static final ZoneId ZONE = ZoneId.of("Australia/Sydney");

    record MT(MovieTheatre theatre, int cinema1, int cinema2, int raya, int arma, int hiker){}

    private int getBadIdentifier(MT mt, int... ints) {
        Set<Integer> idents=new HashSet<>();
        idents.add(mt.arma);
        idents.add(mt.cinema1);
        idents.add(mt.cinema2);
        idents.add(mt.raya);
        idents.add(mt.hiker);
        for(int i:ints) {
            idents.add(i);
        }

        int bad=10;
        while(idents.contains(bad)) {
            bad++;
        }
        return bad;
    }

    private MT makeMovieTheatre() {
        MovieTheatre movieTheatre = new MovieTheatre();

        return new MT(movieTheatre, movieTheatre.addCinema("Cinema 1", new int[][] {new int[]{80, 100, 80}, new int[]{500, 1000, 500}, new int[]{5000, 10000, 5000}}),
            movieTheatre.addCinema("Cinema 2", new int[][] {new int[]{100, 120, 100}, new int[]{200, 240, 200}, new int[]{1000, 1000}}),

            movieTheatre.addMovie("Raya and the Last Dragon", "Animation", 107),
            movieTheatre.addMovie("Armageddon", "Action", 151),
            movieTheatre.addMovie("The Hitchhiker's Guide to the Galaxy", "Comedy", 109));
    }

    @Test
    public void testAddShowing() {
        var mt = makeMovieTheatre();
        assertDoesNotThrow(() -> mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE)));
    }

    @Test
    public void testRemoveShowing() throws CinemaInUseException, InvalidIdentifierException {
        var mt = makeMovieTheatre();
        var showing = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertDoesNotThrow(()->mt.theatre.cancelShowing(showing));
    }

    @Test
    public void testAddShowingInvalidMovie() {
        var mt = makeMovieTheatre();
        assertThrows(InvalidIdentifierException.class, ()->mt.theatre.addShowing(getBadIdentifier(mt), mt.cinema1, ZonedDateTime.of(2025,8, 18, 19, 0,0,0,ZONE)));
    }

    @Test
    public void testAddShowingInvalidCinema() {
        var mt = makeMovieTheatre();
        assertThrows(InvalidIdentifierException.class, ()->mt.theatre.addShowing(mt.raya, getBadIdentifier(mt), ZonedDateTime.of(2025,8, 18, 19, 0,0,0,ZONE)));
    }


    @Test
    public void testRemoveShowingInvalidShowing() throws CinemaInUseException, InvalidIdentifierException {
        var mt = makeMovieTheatre();
        var showing = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertThrows(InvalidIdentifierException.class, ()->mt.theatre.cancelShowing(getBadIdentifier(mt, showing)));
    }

    @Test
    public void testAddSecondShowingBefore() throws CinemaInUseException, InvalidIdentifierException {
        var mt = makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertDoesNotThrow(()->mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 17, 0, 0, 0, ZONE)));
    }
    @Test
    public void testAddSecondShowingAfter() throws CinemaInUseException, InvalidIdentifierException {
        var mt = makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertDoesNotThrow(()->mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 19, 19, 0, 0, 0, ZONE)));
    }
    @Test
    public void testAddSecondShowingBeforeClose() throws CinemaInUseException, InvalidIdentifierException {
        var mt = makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertDoesNotThrow(()->mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 17, 12, 0, 0, ZONE)));
    }
    @Test
    public void testAddSecondShowingAfterClose() throws CinemaInUseException, InvalidIdentifierException {
        var mt = makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertDoesNotThrow(()->mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 20, 48, 0, 0, ZONE)));
    }

    @Test
    public void reAddShowingAfterCancellation() throws CinemaInUseException, InvalidIdentifierException {
        var mt = makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        mt.theatre.cancelShowing(showing1);
        assertDoesNotThrow(()->mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE)));
    }

    @Test
    public void addShowingInOtherCinema() throws CinemaInUseException, InvalidIdentifierException {
        var mt = makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertDoesNotThrow(()->mt.theatre.addShowing(mt.hiker, mt.cinema2, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE)));
    }

    @Test
    public void addParallelShowingInOtherCinema() throws CinemaInUseException, InvalidIdentifierException {
        var mt = makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertDoesNotThrow(()->mt.theatre.addShowing(mt.raya, mt.cinema2, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE)));
    }

    @Test
    public void overlappingShowingFront() throws CinemaInUseException, InvalidIdentifierException {
        var mt= makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertThrows(CinemaInUseException.class, ()->mt.theatre.addShowing(mt.hiker, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 18, 0, 0, 0, ZONE)));
    }
    @Test
    public void overlappingShowingBack() throws CinemaInUseException, InvalidIdentifierException {
        var mt= makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertThrows(CinemaInUseException.class, ()->mt.theatre.addShowing(mt.hiker, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 20, 0, 0, 0, ZONE)));
    }

    @Test
    public void overlappingShowingFrontClose() throws CinemaInUseException, InvalidIdentifierException {
        var mt= makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertThrows(CinemaInUseException.class, ()->mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 17, 13, 0, 0, ZONE)));
    }
    @Test
    public void overlappingShowingBackClose() throws CinemaInUseException, InvalidIdentifierException {
        var mt= makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertThrows(CinemaInUseException.class, ()->mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 20, 47, 0, 0, ZONE)));
    }
    @Test
    public void overlappingShowingOuter() throws CinemaInUseException, InvalidIdentifierException {
        var mt= makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.raya, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertThrows(CinemaInUseException.class, ()->mt.theatre.addShowing(mt.arma, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 18, 30, 0, 0, ZONE)));
    }
    @Test
    public void overlappingShowingInner() throws CinemaInUseException, InvalidIdentifierException {
        var mt= makeMovieTheatre();
        var showing1 = mt.theatre.addShowing(mt.arma, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertThrows(CinemaInUseException.class, ()->mt.theatre.addShowing(mt.hiker, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 30, 0, 0, ZONE)));
    }

    @Test
    public void findFreeSeatsNotNull() throws InvalidIdentifierException, CinemaInUseException {
        var mt= makeMovieTheatre();
        var showing = mt.theatre.addShowing(mt.arma, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        assertNotNull(mt.theatre.findFreeSeats(showing, 1, 100000));
    }

    @Test
    public void reservationBlocksFutureReservations() throws CinemaInUseException, InvalidIdentifierException, SeatsUnavailableException, UnpaidPreviousReservationException {
        var mt = makeMovieTheatre();
        var showing = mt.theatre.addShowing(mt.arma, mt.cinema1, ZonedDateTime.of(2025, 8, 18, 19, 0, 0, 0, ZONE));
        var reservation = mt.theatre.reserveTickets(-1, showing, new Seat(1, 1), 1);
        assertThrows(SeatsUnavailableException.class, ()->mt.theatre.reserveTickets(-1, showing, new Seat(1, 1), 1));
    }

    @Test
    public void testCreateGoldMember() throws InvalidIdentifierException {
        var mt = makeMovieTheatre();
        var member = mt.theatre.registerClubMember("Fabian", ClubMemberStatus.GOLD);
        assertEquals(ClubMemberStatus.GOLD, mt.theatre.getClubMemberStatus(member));
    }

    @Test
    public void testPayTowardsMemberBalance() throws InvalidIdentifierException, NoRefundsException {
        var mt = makeMovieTheatre();
        var member = mt.theatre.registerClubMember("Donald", ClubMemberStatus.BRONZE);
        assertEquals(500, mt.theatre.payTowardsClubMemberBalance(member,500));
    }

    @Test
    public void testMemberBalance() throws InvalidIdentifierException, NoRefundsException {
        var mt = makeMovieTheatre();
        var member = mt.theatre.registerClubMember("Donald", ClubMemberStatus.BRONZE);
        assertEquals(500, mt.theatre.payTowardsClubMemberBalance(member,500));
        assertEquals(500, mt.theatre.getClubMemberBalance(member));
    }


    @Test
    void emptyMovieTheatreTests() {
        MovieTheatre theatre = new MovieTheatre();
        assertThrows(InvalidIdentifierException.class, () -> theatre.cancelShowing(1));
        assertThrows(InvalidIdentifierException.class, () -> theatre.addShowing(1, 1, ZonedDateTime.now()));
        assertThrows(InvalidIdentifierException.class, () -> theatre.cancelReservation(15));
        assertThrows(InvalidIdentifierException.class, () -> theatre.getClubMemberStatus(1));
    }

    @Test
    void anonymousReservationTests() throws CinemaInUseException, InvalidIdentifierException, SeatsUnavailableException, UnpaidPreviousReservationException {
        MovieTheatre theatre = new MovieTheatre();
        int toyStory = theatre.addMovie("Toy Story", "Animation", 81);
        int cinema1 = theatre.addCinema("Cinema 1", new int[][] {{100, 100, 100, 100}, {100, 100, 100, 100}, {120, 120, 120, 120}});
        int showing1 = theatre.addShowing(toyStory, cinema1, ZonedDateTime.of(2025, 6, 6, 14, 0, 0, 0, ZoneId.of("Australia/Sydney")));
        int reservation = theatre.reserveTickets(-1, showing1, new Seat(1, 2), 2);
        assertTrue(reservation > 0, "Reservation IDs should be positive");
        assertThrows(SeatsUnavailableException.class, () -> theatre.reserveTickets(-1, showing1, new Seat(1,1), 2), "Overlapping reservations should be impossible");
        try {
            theatre.reserveTickets(-1, showing1, new Seat(1,1), 2);
            fail("Reservation should have thrown exception!");
        } catch(SeatsUnavailableException e) {
            assertEquals(List.of(new Seat(1,2)), e.unavailableSeats, "Exactly seat (1,2) should be unavailable");
        }
        theatre.cancelReservation(reservation);
        reservation = theatre.reserveTickets(-1, showing1, new Seat(1,1), 2);
        assertTrue(reservation > 0, "Reservation IDs should be positive");
    }

    @Test
    void memberReservationTests() throws CinemaInUseException, InvalidIdentifierException, SeatsUnavailableException, UnpaidPreviousReservationException, NoRefundsException {
        MovieTheatre theatre = new MovieTheatre();
        int toyStory = theatre.addMovie("Toy Story", "Animation", 81);
        int cinema1 = theatre.addCinema("Cinema 1", new int[][] {{100, 100, 100, 100}, {100, 100, 100, 100}, {100, 120, 120, 100}});
        int showing1 = theatre.addShowing(toyStory, cinema1, ZonedDateTime.of(2025, 6, 6, 14, 0, 0, 0, ZoneId.of("Australia/Sydney")));
        int member1 = theatre.registerClubMember("Woody", ClubMemberStatus.BRONZE);
        int member2 = theatre.registerClubMember("Buzz", ClubMemberStatus.GOLD);
        assertEquals(0, theatre.getClubMemberBalance(member1), "Balance should be zero");
        assertEquals(0, theatre.getClubMemberBalance(member2), "Balance should be zero");
        assertEquals(ClubMemberStatus.BRONZE, theatre.getClubMemberStatus(member1), "Woody's status should be BRONZE");
        assertEquals(ClubMemberStatus.GOLD, theatre.getClubMemberStatus(member2), "Buzz's status should be GOLD");
        int reservation1 = theatre.reserveTickets(member1, showing1, new Seat(1, 0), 4);
        int reservation2 = theatre.reserveTickets(member2, showing1, new Seat(2, 0), 2);
        assertTrue(reservation1 > 0, "Reservation IDs should be positive");
        assertTrue(reservation2 > 0, "Reservation IDs should be positive");
        assertEquals(-4000, theatre.getClubMemberBalance(member1), "Woody's tickets would have cost 40 dollars");
        assertEquals(-1800, theatre.getClubMemberBalance(member2), "Buzz's tickets would have cost 18 dollars");
        assertThrows(UnpaidPreviousReservationException.class, () -> theatre.reserveTickets(member1, showing1, new Seat(0, 0), 4), "Reserving tickets with a negative balance should fail");
        assertEquals(0, theatre.payTowardsClubMemberBalance(member1, 4000), "Paying off the balance should return a new 0 balance");
        assertEquals(0, theatre.getClubMemberBalance(member1), "Balance should be zero");
        int reservation3 = theatre.reserveTickets(member1, showing1, new Seat(0, 0), 4);
        assertEquals(ClubMemberStatus.BRONZE, theatre.getClubMemberStatus(member1), "Woody's status should be BRONZE");
        assertEquals(-4000, theatre.getClubMemberBalance(member1), "Woody's tickets would have cost 40 dollars");
        assertEquals(-2000, theatre.payTowardsClubMemberBalance(member1, 2000), "Paying off the balance partially return a larger balance");
        assertEquals(-2000, theatre.getClubMemberBalance(member1), "Woody's remaining balance would be -20 dollars");
        assertThrows(UnpaidPreviousReservationException.class, () -> theatre.reserveTickets(member1, showing1, new Seat(2, 2), 2), "Reserving tickets with a negative balance should fail");
        assertEquals(ClubMemberStatus.BRONZE, theatre.getClubMemberStatus(member1), "Woody's status should still be BRONZE");
        assertEquals(2000, theatre.payTowardsClubMemberBalance(member1, 4000), "Paying off more than the balance should result in a credit");
        assertEquals(2000, theatre.getClubMemberBalance(member1), "Woody should now have a credit of 20 dollars");
        assertEquals(ClubMemberStatus.SILVER, theatre.getClubMemberStatus(member1), "Woody's status should now be SILVER, since he has paid 100 dollars in total");
        int reservation4 = theatre.reserveTickets(member1, showing1, new Seat(2, 2), 2);
        assertEquals(0, theatre.getClubMemberBalance(member1), "The last reservation should have cost 20 dollars, resulting in a balance of 0");
    }
}
