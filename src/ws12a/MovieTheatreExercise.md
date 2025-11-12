## Movie Theatre Reservation System Exercise - Exceptions, packages and more

The goal of the exercise is to develop the missing parts of a movie
theatre's reservation system. The scaffold source files provided along with
this specification rely on programmer-defined exceptions (i.e., classes
extending the `Exception` class from the Java API), as well
as Java packages. The programmer-defined exceptions are declared within the
[`exceptions`](./exceptions/) folder. Apart from evaluating your understanding
about exceptions and packages, the exercise touches on and combines in an
integrated way other course contents as well.

As a first step in this exercise, you need to figure out 
how to distribute the Java source files given into folders
so that the code compiles with the current split of classes,
and other data definitions into packages.

The main interface of the movie theatre's reservation system 
with the outside world is the `MovieTheatre` class.
You may add new classes and new members to the existing classes, as well
as change the implementation of the methods annotated with an `"// FIXME"` comment, 
but should leave the signatures of all existing members untouched.

Monetary values are expressed in cents.
The base ticket price for any movie is 10 dollars, which may be adjusted
in two ways:
* each seat in a cinema has a "rate", which is a percentage (by default 100)
  by which the base ticket price should be multiplied to get the actual price
  for a ticket reserving that seat.
* the rate above may be adjusted by the status level of a club member. A
  `SILVER`-level club member reduces the rate by 10 (this is just additive, so
  a base rate of 100 becomes 90, and a base rate of 120 becomes 110), and
  a `GOLD`-level club member reduces the rate by 20. What counts for the price
  of a reservation is the level of the member making the reservation, regardless
  of how many seats are reserved - the same discount is applied to all seats,
  though the seats themselves may have different rates. For example, a `SILVER`
  member reserving two seats, one of which has a base rate of 100 and another
  which has a base rate of 70 would have to pay 9+6=15 dollars for the whole
  reservation.

Club member status is mostly dependent on how much money they have paid to the
movie theatre - though they are created at a certain level, so through special
promotions, some members may already be at `SILVER` or `GOLD` without having spent
any money. By default, however, any member at lower tier gets promoted to:
* `SILVER` once they have paid a total of 100 dollars to the movie theatre
* `GOLD` once they have paid a total of 1000 dollars to the movie theatre

**Note**: the important thing here is having paid the money, not having made any
reservations. Members may pay 1000 dollars upfront to receive movie credit and
receive cheaper tickets right from the start. Conversely, they might make a
reservation worth more than 1000 dollars, but still won't have their status
upgraded until they actually pay that money. The price of a reservation is
fixed when it is made, so later status upgrades don't make earlier reservations
cheaper.

Reservations can either be made by club members, in which case it counts towards
their account balance calculations, or they can be made anonymously, in which
case just the seats get reserved. Your code is just concerned with club members'
balances, so you do not need to do anything money-related for anonymous reservations.
All other details are given in comments in the code. Implement relevant methods in

- [`MovieTheatre.java`](MovieTheatre.java)
- [`Cinema.java`](Cinema.java)
- [`Showing.java`](Showing.java)

and add any additional data definitions as needed.

In order to help you out, comprehensive Junit tests for the movie theatre reservation system
are provided in the [`MovieTheatreTests.java`](tests/MovieTheatreTests.java) file.