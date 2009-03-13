package no.knowit.rfid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.phidgets.event.AttachListener;
import com.phidgets.event.AttachEvent;
import com.phidgets.RFIDPhidget;
import com.phidgets.PhidgetException;

/**
 * Listens for RFID-readers in proximity.
 * 
 * @author <a href="tto@knowit.no">Tobias K Torrissen</a>
 * @author <a href="jmh@knowit.no">Jon Marius Håkedal</a>
 */
public class DeviceListener implements AttachListener {
    
    private final transient Log LOG = LogFactory.getLog(this.getClass()); 

    /**
     * This method is called with the event data when a new event arrives (here:
     * when the Phidget RFID reader connected to a USB-port is attached).
     */
    public void attached(AttachEvent event) {
        try {
            RFIDPhidget attached = (RFIDPhidget) event.getSource();
            LOG.info("Setting antenna to 'on' state.");
            attached.setAntennaOn(true);
            attached.setLEDOn(true);
            LOG.info("Setting LED to 'on' state.");
        } catch (PhidgetException e) {
            throw new RuntimeException("Failed to set antenna/LED to 'on' state.", e);
        }
    }
}
