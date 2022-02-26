import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

import database.DatabaseHandler;

public class Sms {

    //protected String msisdn;
    protected String recipient;
    protected String sender;
    protected String short_code;
    protected String transaction_id;
    protected LocalDateTime timestamp;

    private static Connection con;
    final private static Logger logger = Logger.getLogger(Sms.class.getName());


    public Sms() {

    }

    public Sms(String recipient, String sender, String short_code, String transaction_id, LocalDateTime timestamp) {
        this.recipient = recipient;
        this.sender = sender;
        this.short_code = short_code;
        this.transaction_id = transaction_id;
        this.timestamp = timestamp;
    }

    public Sms(String recipient, String sender, String short_code, String transaction_id) {

        this.recipient = recipient;
        this.sender = sender;
        this.short_code = short_code;
        this.transaction_id = transaction_id;
        this.timestamp = LocalDateTime.now();
    }


    public String getRecipient() {
        return recipient;
    }

    public String getSender() {
        return sender;
    }

    public String getShort_code() {
        return short_code;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean sms_checker(HashMap<String, String> map1, HashMap<String, String> map2) {

        if (!map1.containsKey("Mobile number") || !map2.containsKey("Mobile number") ||
                !map1.containsKey("Message") || !map2.containsKey("Message") ||
                !map1.containsKey("Short Code") || !map2.containsKey("Short Code")) {
            return false;
        }

        return map1.equals(map2);
    }

    public static boolean sendSms(String sender_msisdn, String receiver_msisdn, String text) {
        boolean sent = false;
        try {
            // Connect to the database
            con = DatabaseHandler.connect(con);

            // Check if connected to database
            if (!con.isClosed()) {
                logger.log(Level.INFO, "Connected to database");

                // prepare statement
                PreparedStatement sqlQuery = con.prepareStatement("INSERT INTO sms (sender_msisdn, receiver_msisdn, body) VALUES (?,?,?)");
                sqlQuery.setString(1, sender_msisdn);
                sqlQuery.setString(2, receiver_msisdn);
                sqlQuery.setString(3, text);

                // execute statement
                int row = sqlQuery.executeUpdate();
                if (row > 0) {
                    logger.log(Level.INFO, "SMS sent");
                    sent = true;
                } else {
                    logger.log(Level.WARNING, "SMS FAILED TO SEND");
                }

            } else {
                logger.log(Level.SEVERE, "Connection to the database FAILED");
            }

            // Disconnect from the database
            DatabaseHandler.disconnect(con);
            if (con.isClosed()) {
                logger.log(Level.INFO, "Disconnected from the database");
            } else {
                logger.log(Level.WARNING, "Connection to database might still be open");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.INFO, "Connected to database", e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return sent;
        }

    }
}
