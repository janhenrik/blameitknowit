package no.knowit.couchdb;

import org.jcouchdb.document.BaseDocument;

import java.util.Date;

/**
 * @author <a href="jmh@knowit.no">Jon Marius H�kedal</a>
 */
public class Person extends BaseDocument {

    private static final long serialVersionUID = 1L;

    private final String type = "person";
    
    private String name;
        
    private Address address;
    
    private String email;
    
    private String company;
    
    private String phonenumber;

	private String photo;

	private int antallKopper = 0;

	private Date lastDrunk;

	public void hentetKaffe() {
		antallKopper++;
		lastDrunk = new Date();
	}

	public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public int getAntallKopper() {
		return antallKopper;
	}

	public void setAntallKopper(int antallKopper) {
		this.antallKopper = antallKopper;
	}

	public Date getLastDrunk() {
		return lastDrunk;
	}

	public void setLastDrunk(Date lastDrunk) {
		this.lastDrunk = lastDrunk;
	}
}
