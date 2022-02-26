import database.DatabaseHandler;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SmsManager implements SmsManagerInterface {
    private Sms sms;
    private static Connection con;
    final private static Logger logger = Logger.getLogger(SmsManager.class.getName());

    @Override
    public boolean insertSms() {

        return false;
    }

    public static boolean checkRegistrationValidity(String sender_msisdn, String receiver_msisdn, String body) {

        Statement statement = null;
        ResultSet resultSet = null;
        boolean valid = false;

        con = DatabaseHandler.connect(con);

        try {
            if (!con.isClosed()) {
                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM sms " +
                                "WHERE " +
                                "sender_msisdn = ? AND " +
                                "receiver_msisdn = ? AND " +
                                "body = ? AND " +
                                "transaction_time between ? AND ? " +
                                "ORDER BY transaction_time DESC " +
                                "LIMIT 1 ");

                ps.setString(1, sender_msisdn);
                ps.setString(2, receiver_msisdn);
                ps.setString(3, body);

                //for the time
                LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
                LocalDateTime m15BeforeNow = LocalDateTime.now(ZoneOffset.UTC).minus(15, ChronoUnit.MINUTES);
                DateTimeFormatter formattedNow = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                DateTimeFormatter formattedHBN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                ps.setString(4, m15BeforeNow.format(formattedNow));
                ps.setString(5, now.format(formattedHBN));

                ResultSet rs = ps.executeQuery();
                System.out.println(ps.toString());

                if (rs.isBeforeFirst()) {
                    System.out.println(rs);
                    valid = true;
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
        }

        return valid;
    }


    @Override
    public ResultSet retrieveSmsByDate(LocalDateTime dateTime) {
        Statement statement = null;
        ResultSet resultSet = null;

        con = DatabaseHandler.connect(con);

        PreparedStatement ps = null;
        ResultSet rs = null;

        DateTimeFormatter formatOne = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
        DateTimeFormatter formatTwo = DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59");


        try{

            ps = con.prepareStatement("SELECT * FROM sms \n" +
                    "WHERE \n" +
                    "transaction_time between ? AND ?\n" +
                    "ORDER BY transaction_time DESC\n" +
                    "LIMIT 1;");
            ps.setString(1, dateTime.format(formatOne));
            ps.setString(2, dateTime.format(formatTwo));


            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return rs;
    }

    @Override
    public ResultSet retrieveSmsByPromoCode(String promoCode) {

        Statement statement = null;
        ResultSet resultSet = null;

        con = DatabaseHandler.connect(con);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            ps = con.prepareStatement("SELECT * \n" +
                    "FROM promo\n" +
                    "LEFT JOIN sms ON promo.short_code=sms.receiver_msisdn\n" +
                    "WHERE promo.promo_code=?\n" +
                    "UNION\n" +
                    "SELECT * \n" +
                    "FROM promo\n" +
                    "LEFT JOIN sms ON promo.short_code=sms.sender_msisdn\n" +
                    "WHERE promo.promo_code=?");
            ps.setString(1, promoCode);
            ps.setString(2, promoCode);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return rs;

    }

    @Override
    public ResultSet retrieveSmsByMsisdn(String msisdn) {

        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Promo> promoList = new ArrayList<>();

        con = DatabaseHandler.connect(con);

        String selectQuery = "SELECT * FROM sms WHERE sender_msisdn = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            ps = con.prepareStatement("SELECT * FROM sms WHERE sender_msisdn = ?");
            ps.setString(1, msisdn);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return rs;
    }

    @Override
    public ResultSet retrieveSmsByMsisdn(String[] msisdns) {

        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Promo> promoList = new ArrayList<>();

        con = DatabaseHandler.connect(con);

        String selectQuery = "SELECT * FROM sms WHERE sender_msisdn IN (?)";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            StringBuffer list = new StringBuffer();
            ps = con.prepareStatement("SELECT * FROM sms WHERE sender_msisdn IN (?)");

            for(int i = 0; i < msisdns.length; i++){
                msisdns[i] = "'" + msisdns[i] + "'";
                int x = i+1;
                if (x == msisdns.length){

                }else{
                    msisdns[i] = msisdns[i] + ",";
                }

                list.append(msisdns[i]);
            }

            String fullList = list.toString();
            ps.setString(1, fullList);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return rs;
    }

    @Override
    public ResultSet retrieveSmsBySent(String msisdn) {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Promo> promoList = new ArrayList<>();

        con = DatabaseHandler.connect(con);

        String selectQuery = "SELECT * FROM sms WHERE sender_msisdn = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            ps = con.prepareStatement("SELECT * FROM sms WHERE sender_msisdn = ?");
            ps.setString(1, msisdn);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return rs;
    }

    @Override
    public ResultSet retrieveSmsByReceive(String msisdn) {
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Promo> promoList = new ArrayList<>();

        con = DatabaseHandler.connect(con);

        String selectQuery = "SELECT * FROM sms WHERE receiver_msisdn = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{

            ps = con.prepareStatement("SELECT * FROM sms WHERE receiver_msisdn = ?");
            ps.setString(1, msisdn);

            rs = ps.executeQuery();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return rs;
    }


}
