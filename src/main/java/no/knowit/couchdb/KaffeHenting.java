package no.knowit.couchdb;

import java.util.Date;

/**
 * @author jhg
 * @since Mar 13, 2009 5:13:31 PM
 */
public class KaffeHenting {
	Person person;
	Date sisteTidspunkt;
	int antallKaffe;


	public Date getSisteTidspunkt() {
		return sisteTidspunkt;
	}

	public void setSisteTidspunkt(Date sisteTidspunkt) {
		this.sisteTidspunkt = sisteTidspunkt;
	}

	public int getAntallKaffe() {
		return antallKaffe;
	}

	public void setAntallKaffe(int antallKaffe) {
		this.antallKaffe = antallKaffe;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
