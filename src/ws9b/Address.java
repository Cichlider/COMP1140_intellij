package ws9b;

import java.util.Random;
/**
 * This class represents a street address within the Australian Capital Territory,
 * for example "108 North Road 2601".
 *
 */
public class Address {
    /**
     * The street number of the address (must be greater than zero) e.g. 108.
     */
    int streetNumber;

    /**
     * The street name is a string of ASCII characters e.g. "North Road"
     */
    String streetName;

    /**
     * The postcode is a number from 0 to 9999.
     */
    int postCode;

    public Address(int streetNumber, String streetName, int postCode) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postCode = postCode;
    }

    /**
     * @return a hash code value for this object.
     * In implementing this method, you may *not* use the default Java
     * implementations in Object.hashCode() or Objects.hash().
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = streetNumber;

        result = 31 * result + (streetName != null ? streetName.hashCode() : 0);

        result = 31 *result + postCode;

        return result;
    }

    /**
     * @return true if this address is equal to the provided object
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object object) {
        if(this == object) return true;

        if(object == null || getClass() != object.getClass()) return false;

        Address address =(Address) object;

        if(streetNumber != address.streetNumber) return false;
        if(postCode != address.postCode) return false;

        return streetName != null ? streetName.equals(address.streetName) :address.streetName ==null;
    }

    @Override
    public String toString() {
        return streetNumber + " " + streetName + " " + postCode;
    }

    // DO NOT DELETE OR CALL THIS METHOD
    int passThroughHash() {
        return super.hashCode();
    }
}

