package no.knowit.couchdb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jcouchdb.db.Database;
import org.jcouchdb.document.ValueRow;
import org.jcouchdb.document.ViewResult;

/**
 * A simple DAO that can read & update a {@link RFIDDocument}.
 * </p>
 * All the queries use an ad-hoc view (which is slow but easy to implement).
 * <p/>
 * The queries are performed by creating a java-script function that is passed to the database.
 * A function that queries for all documents of a certain type (see the <code>type</code> field in the domain
 * classes) looks like:
 * <pre>
 * function(doc) {
 *   if(doc.type == 'rfid')
 *      emit(null, doc);
 * }
 * </pre>
 * While a more complex query can be written as:
 * <pre>
 * function(doc) {
 *   if(doc.type == 'rfid' && doc.person)
 *     if(doc.person.name = 'Jean-Luc Picard')
 *       emit(null, doc);
 * }
 * </pre>
 * 
 * @author <a href="mailto:jmh@knowit.no">Jon Marius Håkedal</a> 
 */
public class RFIDInformationDao {

    private final Database database;
    
    public RFIDInformationDao(Database database) {
        this.database = database;
    }
    
    /**
     * Queries for all {@link RFIDInformation} documents (documents with
     * <code>type=rfid</code>).
     * 
     * @return a {@link List} of {@link RFIDInformation} documents, which is
     *         empty if no documents are found
     */
    public List<RFIDInformation> queryAll() {
        String function = "{ \"map\" : \"function(doc) { if (doc.type == 'rfid') emit(null, doc);  } \" }";
        ViewResult<RFIDInformation> result = database.queryAdHocView(RFIDInformation.class, function, null, null);
        List<ValueRow<RFIDInformation>> rows = result.getRows();
        if (rows.size() == 0) {
            Collections.<RFIDInformation> emptyList();
        }
        List<RFIDInformation> documents = new ArrayList<RFIDInformation>();
        for (ValueRow<RFIDInformation> row : rows) {
            documents.add(row.getValue());
        }
        return documents;
    }
    
    /**
     * Queries for all {@link RFIDInformation} documents with a RFID tag that
     * matches the passed value.
     * 
     * @param tag
     *            the RFID tag to query for
     * @return a {@link RFIDInformation} document, or null if not found
     */
    public RFIDInformation queryByTag(String tag) {
        String function = "{ \"map\" : \"function(doc) { if (doc.type == 'rfid' && doc.rfidtag == '" + tag + "') emit(null, doc);  } \" }";
        ViewResult<RFIDInformation> result = database.queryAdHocView(RFIDInformation.class, function, null, null);
        List<ValueRow<RFIDInformation>> rows = result.getRows();
        if (rows.size() == 0) {
            return null;
        }
        // pick the first one :)
        return rows.get(0).getValue();
    }
    
    /**
     * Updates the given {@link RFIDInformation} document.
     */
    public void updateRFIDDocument(RFIDInformation document) {
        database.updateDocument(document);
    }
    
    /**
     * Convenient method used during testing.
     */
    protected Database getDatabase() {
        return database;
    }

}
