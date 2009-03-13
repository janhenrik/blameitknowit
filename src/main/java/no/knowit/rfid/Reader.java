package no.knowit.rfid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.phidgets.Phidget;
import com.phidgets.PhidgetException;
import com.phidgets.RFIDPhidget;
import com.phidgets.event.TagLossEvent;
import com.phidgets.event.TagLossListener;

/**
 * Main-class of the RFID.
 * <p/>
 * Note: The java.library.path must be set so that it points to the native
 * library (a *.dll, *.so, or *.jnilib) that is located in the 'lib' folder. 
 * <br/>
 * In eclipse this can be done by setting the VM arguments in the 'Run
 * Configurations...' to: <code>-Djava.library.path=${project_loc}/lib</code>
 * 
 * @author <a href="tto@knowit.no">Tobias K Torrissen</a>
 * @author <a href="jmh@knowit.no">Jon Marius H�kedal</a>
 * 
 */
public class Reader extends JPanel {
    
    private static final long serialVersionUID = 1L;

    private final transient Log LOG = LogFactory.getLog(this.getClass());
    
    private RFIDPhidget rfid = null;
    
    public Reader() {

		
		JButton button = new JButton("QUIT");
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    shutdown(rfid);
                    rfid.close();
                    LOG.info("GOODBYE.");
                    System.exit(1);
                } catch (PhidgetException e) {
                    throw new RuntimeException("Failed to do a shutdown.", e);
                }
            }
        });
        add(button);
    }
    
    /**
     * Starts to listen for an attached RFID-readers and tag-events (tags
     * entering or leaving the range of the RFID-reader.
     * 
     * @throws IOException
     * @throws PhidgetException
     */
    public void initialize() throws PhidgetException, IOException {
        JFrame frame = new JFrame("Reader");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setContentPane(this);
        frame.pack();
        frame.setVisible(true);
        
        LOG.info("Initializing Phidget (" + Phidget.getLibraryVersion() + ").");
        this.rfid = new RFIDPhidget();
        
        rfid.addAttachListener(new DeviceListener());
        rfid.addTagGainListener(new TagListener());
        rfid.addTagLossListener(new TagLossListener() {

            public void tagLost(TagLossEvent event) {
                LOG.info("Lost tag with value: " + event.getValue());
            }
        });
        
        rfid.openAny();
        LOG.info("Waiting for RFID attachment.");
        // wait one second...
        rfid.waitForAttachment(1000);
        
        LOG.info("Phidget has started. Time to relax.");
    }
    
    /*
     * Sets antenna and LED to 'off' state.
     */
    private void shutdown(RFIDPhidget attached) throws PhidgetException {
        LOG.info("Setting antenna to 'off' state.");
        attached.setAntennaOn(false);
        attached.setLEDOn(false);
        LOG.info("Setting LED to 'off' state.");
    }

    public static void main(String[] args) throws IOException, PhidgetException {
        new Reader().initialize();
    }

}
