import database.DatabaseHandler;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Report {

    public static void main(String[] args) {

        //Report.retrieveListOfPeopleInPromo("SUNSHINE");
        Report.totalCountSent("10");
        Report.totalCountReceived("10");

    }


    private static Connection con;
    final private static Logger logger = Logger.getLogger(Sms.class.getName());

    public static ResultSet retrieveListOfPeopleInPromo(String promoCode) {
        Statement statement = null;
        ResultSet resultSet = null;

        con = DatabaseHandler.connect(con);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            ps = con.prepareStatement("SELECT promo_transaction.first_name, \n" +
                    "promo_transaction.last_name, promo.promo_code\n" +
                    "FROM promo_transaction\n" +
                    "LEFT JOIN promo ON promo_transaction.promo_id=promo.id\n" +
                    "WHERE promo.promo_code=?;");
            ps.setString(1, promoCode);
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++ ) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }

            while(rs.next()){
                System.out.println("");
                for (int i = 1; i <= rsmd.getColumnCount(); i++ ) {
                    System.out.print(rs.getString(rsmd.getColumnName(i)) + "\t");
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return rs;
    }

    public static ResultSet totalCountSent(String senderMsisdn) {
        Statement statement = null;
        ResultSet resultSet = null;

        con = DatabaseHandler.connect(con);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            ps = con.prepareStatement("SELECT COUNT(body) FROM sms WHERE sender_msisdn = ?" );
            ps.setString(1, senderMsisdn);
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++ ) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }

            while(rs.next()){
                System.out.println("");
                for (int i = 1; i <= rsmd.getColumnCount(); i++ ) {
                    System.out.print(rs.getString(rsmd.getColumnName(i)) + "\t");
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return rs;
    }

    public static ResultSet totalCountReceived(String receiverMsisdn) {
        Statement statement = null;
        ResultSet resultSet = null;

        con = DatabaseHandler.connect(con);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            ps = con.prepareStatement("SELECT COUNT(body) FROM sms WHERE receiver_msisdn = ?" );
            ps.setString(1, receiverMsisdn);
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++ ) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }

            while(rs.next()){
                System.out.println("");
                for (int i = 1; i <= rsmd.getColumnCount(); i++ ) {
                    System.out.print(rs.getString(rsmd.getColumnName(i)) + "\t");
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return rs;
    }
}
