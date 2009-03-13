package no.knowit.couchdb;

import org.jcouchdb.document.BaseDocument;

/**
 * @author <a href="jmh@knowit.no">Jon Marius Håkedal</a>
 */
public class Address extends BaseDocument {

    private static final long serialVersionUID = 1L;

    private final String type = "address";

    private String street;

    private String number;

    private String postalcode;

    public String getType() {
        return type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

}
