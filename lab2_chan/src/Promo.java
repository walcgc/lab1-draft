import database.DatabaseHandler;

import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.temporal.ChronoUnit;

public class Promo {

    protected String promo_code;
    protected String details;
    protected String short_code;
    protected LocalDateTime start_date;
    protected LocalDateTime end_date;

    private static Connection con;
    final private static Logger logger = Logger.getLogger(Promo.class.getName());

    protected static ArrayList<Promo> promos = retrievePromoList();
    protected static ArrayList<Promo> valid_promos = retrieveValidPromoList();
    protected static ArrayList<String> promo_codes = retrievePromoCodeList();
    protected static ArrayList<String> short_codes = retrieveShortCodeList();

    public Promo() {
    }

    public Promo(String promo_code, String details, String short_code, LocalDateTime start_date, LocalDateTime end_date) {
        this.promo_code = promo_code;
        this.details = details;
        this.short_code = short_code;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public static void createPromo(String promo_code, String short_code, String details, String start_date, String end_date) {
        try {
            // Connect to the database
            con = DatabaseHandler.connect(con);

            // Check if connected to database
            if (!con.isClosed()) {
                logger.log(Level.INFO, "Connected to database");

                // prepare statement
                PreparedStatement sqlQuery = con.prepareStatement("INSERT INTO promo " +
                        "(promo_code, short_code, start_date, end_date, details, promo_msisdn)\n" +
                        "VALUES\n" +
                        "(?, ?, ?, ?, ?, ?)");
                sqlQuery.setString(1, promo_code);
                sqlQuery.setString(2, short_code);
                sqlQuery.setString(3, start_date);
                sqlQuery.setString(4, end_date);
                sqlQuery.setString(5, details);
                sqlQuery.setString(6, short_code);

                // execute statement
                int row = sqlQuery.executeUpdate();
                if (row > 0) {
                    logger.log(Level.INFO, "Promo made");
                } else {
                    logger.log(Level.WARNING, "Failed to create promo, promo code might exist already");
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
    }

    public String getPromo_code() {
        return promo_code;
    }

    public String getShort_code() {
        return short_code;
    }

    public String getDetails() {
        return details;
    }

    public static ArrayList<String> getPromo_codes() {
        return promo_codes;
    }

    public static ArrayList<String> getShort_codes() {
        return short_codes;
    }

    public static boolean checkShortCode(String receiver_msisdn) {
        return short_codes.contains(receiver_msisdn);
    }

    private static ArrayList<Promo> retrievePromoList() {

        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Promo> promoList = new ArrayList<>();

        String selectQuery = "SELECT * FROM promo";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // query from database
            con = DatabaseHandler.connect(con);
            ps = con.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            // turn to object and put in arraylist
            while (rs.next()) {

                // convert string to LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime sd = LocalDateTime.parse(rs.getString("start_date"), formatter);
                LocalDateTime ed = LocalDateTime.parse(rs.getString("end_date"), formatter);

                Promo promo = new Promo(rs.getString("promo_code"), rs.getString("details"),
                        rs.getString("short_code"), sd, ed);
                promoList.add(promo);
            }

            con.close();
            logger.log(Level.INFO, "Retrieved : {0}", promoList);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return promoList;

    }

    private static ArrayList<Promo> retrieveValidPromoList() {

        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Promo> promoList = new ArrayList<>();

        String selectQuery = "SELECT * FROM promo WHERE NOW() BETWEEN start_date AND end_date";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // query from database
            con = DatabaseHandler.connect(con);
            ps = con.prepareStatement(selectQuery);
            rs = ps.executeQuery();

            // turn to object and put in arraylist
            while (rs.next()) {

                // convert string to LocalDateTime
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime sd = LocalDateTime.parse(rs.getString("start_date"), formatter);
                LocalDateTime ed = LocalDateTime.parse(rs.getString("end_date"), formatter);

                Promo promo = new Promo(rs.getString("promo_code"), rs.getString("details"),
                        rs.getString("short_code"), sd, ed);
                promoList.add(promo);
            }

            con.close();
            logger.log(Level.INFO, "Retrieved : {0}", promoList);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        }

        return promoList;

    }

    private static ArrayList<String> retrievePromoCodeList() {

        ArrayList<String> codeList = new ArrayList<String>();
        for (Promo promo : promos) {
            codeList.add(promo.getPromo_code());
        }
        return codeList;
    }

    private static ArrayList<String> retrieveShortCodeList() {

        ArrayList<String> shortCodeList = new ArrayList<String>();
        for (Promo promo : promos) {
            shortCodeList.add(promo.getShort_code());
        }
        return shortCodeList;
    }

    private static Promo findValidPromo(String short_code) {
        for (Promo promo : valid_promos) {
            if (promo.getShort_code().equals(short_code)) {
                return promo;
            }
        }
        return null;
    }

    public static void registerPromo(String sender_msisdn, String short_code, String text) {

        // If text contains name (checked by , character) switch case to NAME and use name
        String name = null;
        if (text.contains(",")) {
            name = text;
            text = "NAME";
        }
        // Just getting the promo code name and details from the short code to
        // show the user what they're applying for
        Promo promo = findValidPromo(short_code);
        String promoText;
        boolean userTextPromo = false;
        boolean sendPromoAlert = false;

        // Promo and register will have a 15 minute timeout
        switch (text) {

            case "PROMO":

                if (promo == null) {
                    logger.log(Level.SEVERE, "PROMO ERROR");
                    break;
                }

                promoText = "You are about to register to Promo: " + promo.getPromo_code() +
                        "\n" + promo.getDetails() + "\n" +
                        "Please text REGISTER to " + short_code + " within 15 minutes to continue registration.";

                //send sms
                userTextPromo = Sms.sendSms(sender_msisdn, short_code, text);
                sendPromoAlert = Sms.sendSms(short_code, sender_msisdn, promoText);

                if (userTextPromo) {
                    String logMessage = MessageFormat.format("User sent PROMO to short code {0} -  Promo code {1}",
                            short_code, promo.getPromo_code());
                    logger.log(Level.INFO, logMessage);
                }

                if (sendPromoAlert) {
                    logger.log(Level.INFO, "System sent SMS");
                    System.out.println(promoText);
                } else {
                    logger.log(Level.SEVERE, "System FAILED to send SMS");
                }

                break;

            case "REGISTER":

                if (promo == null) {
                    logger.log(Level.SEVERE, "REGISTER ERROR");
                    break;
                }

                // Check if user was able to send REGISTER within an hour of sending PROMO
                LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
                LocalDateTime hourBeforeNow = LocalDateTime.now(ZoneOffset.UTC).minus(15, ChronoUnit.MINUTES);

                promoText = "You are almost done registering promo: " + promo.getPromo_code() +
                        "\n" + promo.getDetails() + "\n" +
                        "Please text your name with the format - Last name, First name to " + short_code +
                        " within 15 minutes to finish registration.\n" +
                        "Ex. Dela Cruz, Juan";

                //send sms
                userTextPromo = Sms.sendSms(sender_msisdn, short_code, text);

                boolean validRegister = SmsManager.checkRegistrationValidity(sender_msisdn, short_code, "PROMO");

                if (userTextPromo) {
                    String logMessage = MessageFormat.format("User sent REGISTER to short code {0} -  Promo code {1}",
                            short_code, promo.getPromo_code());
                    logger.log(Level.INFO, logMessage);
                }

                if (validRegister) {
                    sendPromoAlert = Sms.sendSms(short_code, sender_msisdn, promoText);
                    if (sendPromoAlert) {
                        logger.log(Level.INFO, "System sent SMS");
                        System.out.println(promoText);
                    } else {
                        logger.log(Level.SEVERE, "System FAILED to send SMS");
                    }
                } else {
                    promoText = "Registration failed. Validity expired or you haven't texted PROMO.";
                    logger.log(Level.INFO, "REGISTER FAILED");
                    System.out.println(promoText);
                }

                break;
            case "NAME":
                if (promo == null) {
                    logger.log(Level.SEVERE, "NAME ERROR");
                    break;
                }

                String[] fullNameAr = name.split(",");
                String fullName = fullNameAr[0] + " " + fullNameAr[1];

                promoText = fullName + ", You are now registered to: " + promo.getPromo_code();

                //send sms
                userTextPromo = Sms.sendSms(sender_msisdn, short_code, name);

                boolean registerComplete = SmsManager.checkRegistrationValidity(sender_msisdn, short_code, "REGISTER");

                if (userTextPromo) {
                    String logMessage = MessageFormat.format("User sent NAME to short code {0} -  Promo code {1}",
                            short_code, promo.getPromo_code());
                    logger.log(Level.INFO, logMessage);
                }

                if (registerComplete) {
                    sendPromoAlert = Sms.sendSms(short_code, sender_msisdn, promoText);
                    if (sendPromoAlert) {
                        logger.log(Level.INFO, "System sent SMS");
                        System.out.println(promoText);
                        linkPromo(sender_msisdn, short_code, fullNameAr[1], fullNameAr[0]);

                    } else {
                        logger.log(Level.SEVERE, "System FAILED to send SMS");
                    }
                } else {
                    promoText = "Registration failed. Validity expired or you haven't texted REGISTER.";
                    logger.log(Level.INFO, "NAME FAILED");
                    System.out.println(promoText);
                }

                break;

            default:
                logger.log(Level.WARNING, "404 Invalid text sent - \"" + text + "\"");

        }
    }

    public static boolean linkPromo(String sender_msisdn, String receiver_msisdn, String first_name, String last_name) {

        Statement statement = null;
        ResultSet resultSet = null;
        boolean valid = false;
        String sms_id = null;
        String promo_id = null;

        con = DatabaseHandler.connect(con);

        //retrieving data for linking
        try {

            PreparedStatement psa = con.prepareStatement(
                    "SELECT promo.id AS promo_id, promo.short_code, sms.id AS sms_id\n" +
                            "FROM promo\n" +
                            "LEFT JOIN sms ON promo.short_code=sms.receiver_msisdn\n" +
                            "WHERE \n" +
                            "sms.sender_msisdn = ? AND \n" +
                            "sms.receiver_msisdn = ? AND\n" +
                            "sms.body LIKE '%\\,%'\n" +
                            "ORDER BY sms.transaction_time DESC\n" +
                            "LIMIT 1;");

            psa.setString(1, sender_msisdn);
            psa.setString(2, receiver_msisdn);
            ResultSet rs = psa.executeQuery();

            sms_id = null;
            promo_id = null;


            if (rs.next()) {

                sms_id = String.valueOf(rs.getInt("sms_id"));
                promo_id = String.valueOf(rs.getInt("promo_id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            logger.log(Level.INFO, "Connected to database", e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (!con.isClosed()) {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO promo_transaction (sms_id, promo_id, approved, first_name, last_name) " +
                                "VALUES (?,?,?,?,?)");

                ps.setString(1, sms_id);
                ps.setString(2, promo_id);
                ps.setString(3, "1");
                ps.setString(4, first_name);
                ps.setString(5, last_name);

                // execute statement
                int row = ps.executeUpdate();
                if (row > 0) {
                    logger.log(Level.INFO, "Promo Linked");
                    valid = true;
                } else {
                    logger.log(Level.WARNING, "Promo linking FAILED");
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
}
