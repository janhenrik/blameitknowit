package no.knowit.couchdb;

import org.jcouchdb.document.BaseDocument;

/**
 * @author <a href="mailto:jmh@knowit.no">Jon Marius H�kedal</a>
 */
public class RFIDInformation extends BaseDocument {

    private static final long serialVersionUID = 1L;

    private final String type = "rfid";
    
    private String rfidtag;
    
    private Person person;

    public RFIDInformation() {
        // needed by svenson (the JSON library)
    }

    public RFIDInformation(String rfidtag) {
        this.rfidtag = rfidtag;
    }

    public String getType() {
        return type;
    }

    public String getRfidtag() {
        return rfidtag;
    }

    public void setRfidtag(String rfidtag) {
        this.rfidtag = rfidtag;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
}


