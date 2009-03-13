package no.knowit.couchdb;

import java.lang.reflect.Field;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.jcouchdb.db.Database;
import org.jcouchdb.db.Server;
import org.jcouchdb.db.ServerImpl;

/**
 * @author <a href="jmh@knowit.no">Jon Marius Håkedal</a>
 */
public class DatabaseFactory {

    private static String DATABASE_HOST = "localhost";

    private static int DATABASE_PORT = Server.DEFAULT_PORT;

    private static String DATABASE_NAME = "rfid_db";

    private static String DATABASE_USERNAME = "xxx";

    private static String DATABASE_PASSWORD = "xxx";

    private static RFIDInformationDao rfidInformationDao = null;

    public static RFIDInformationDao getRFIDInformationDao() {
        if (rfidInformationDao == null) {
            Server server = new ServerImpl(DATABASE_HOST, DATABASE_PORT);
            setCredentials(server);
            Database database = new Database(server, DATABASE_NAME);
            rfidInformationDao = new RFIDInformationDao(database);
        }
        return rfidInformationDao;
    }

    /*
     * jcouchdb dosn't support authentication - use reflection to set it
     */
    private static void setCredentials(Server server) {
        try {
            Field field = server.getClass().getDeclaredField("httpClient");
            field.setAccessible(true);
            HttpClient httpClient = (HttpClient) field.get(server);
            Credentials credentials = new UsernamePasswordCredentials(DATABASE_USERNAME, DATABASE_PASSWORD);
            httpClient.getState().setCredentials(new AuthScope(DATABASE_HOST, DATABASE_PORT, AuthScope.ANY_REALM), credentials);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set credentials", e);
        }
    }

}
