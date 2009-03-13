package no.knowit.rfid;

import com.phidgets.PhidgetException;
import com.phidgets.event.TagGainEvent;
import com.phidgets.event.TagGainListener;
import no.knowit.couchdb.KaffeHenting;
import no.knowit.couchdb.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Listens for RFID chips in proximity of RFID reader.
 *
 * @author <a href="tto@knowit.no">Tobias K Torrissen</a>
 * @author <a href="jmh@knowit.no">Jon Marius H�kedal</a>
 */
public class TagListener implements TagGainListener {


	static private Map tildelte = new HashMap();
	static private Stack ferske = new Stack();
	static private int antallKopperTotalt = 0;
	static private Person[] ppl;
	private final transient Log LOG = LogFactory.getLog(this.getClass());
	static private List<KaffeHenting> personerSomHarKommetForAaDrikkeKaffe = new ArrayList();


	static {
		try {
			new Reader().initialize();
		} catch (PhidgetException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}

		ppl = new Person[5];
		ppl[0] = new Person();
		ppl[0].setName("Erik Amundrud");
		ppl[0].setPhoto("1.jpg");
		ppl[1] = new Person();
		ppl[1].setName("Erik Nylund");
		ppl[1].setPhoto("2.jpg");
		ppl[2] = new Person();
		ppl[2].setName("Bjørg Haaland");
		ppl[2].setPhoto("3.jpg");
		ppl[3] = new Person();
		ppl[3].setName("Lars Ivar Næss");
		ppl[3].setPhoto("4.jpg");
		ppl[4] = new Person();
		ppl[4].setName("Sverre Tinnen");
		ppl[4].setPhoto("5.jpg");

		ferske.push(ppl[0]);
		ferske.push(ppl[1]);
		ferske.push(ppl[2]);
		ferske.push(ppl[3]);
		ferske.push(ppl[4]);

	}

	public static List<KaffeHenting> getKaffeDrikkere() {
		return personerSomHarKommetForAaDrikkeKaffe;
	}

	public static String antallKopperTotalt() {
		return String.valueOf(antallKopperTotalt);
	}

	/**
	 * This method is called with the event data when a new event arrives (here:
	 * a RFID chip moves into the range of the reader).
	 */
	public void tagGained(TagGainEvent event) {
		String tag = event.getValue();
		LOG.info("Gained tag with value: " + tag);

		final Person funnet;
		if (!tildelte.containsKey(tag)) {
			funnet = (Person) ferske.pop();
			funnet.setId(tag);
			tildelte.put(tag, funnet);
		} else {
			funnet = (Person) tildelte.get(tag);
					
		}

		funnet.hentetKaffe();
		antallKopperTotalt++;
		KaffeHenting kh = new KaffeHenting();
		kh.setPerson(funnet);
		kh.setSisteTidspunkt(new Date());
		kh.setAntallKaffe(funnet.getAntallKopper());
		personerSomHarKommetForAaDrikkeKaffe.add(0, kh);

		Thread t = new Thread() {
			public void run() {
				Twitter twitter = new Twitter("blameitknowit", "blameit22");
				try {
					String enhet = funnet.getAntallKopper() > 1 ? " kopp" : " kopper";
					Status status = twitter.update(funnet.getName() + " drakk en kopp kaffe, har nå drukket " + funnet.getAntallKopper() + enhet);
					System.out.println ("Twitter status id = " + status.getId());
				} catch (TwitterException e) {
					e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
				}
			}
		};
		t.start();

//         Note: Before you use this code, make sure that the DatabaseFactory is
//         set up correctly (DATABASE_HOST, DATABASE_NAME, etc.).
//         
//         RFIDInformationDao dao = DatabaseFactory.getRFIDInformationDao();
//         RFIDInformation information = dao.queryByTag(tag);
//         LOG.info(information.getPerson().getName() + " just swiped his/hers card!");

	}
}
