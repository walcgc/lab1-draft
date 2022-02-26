package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler {

    final private static Logger logger = Logger.getLogger(DatabaseHandler.class.getName());


    /*
    public static void main(String[] args) {

        DatabaseHandler.connect();
        //ArrayList<String> data = DatabaseConnect.retrieveData();
        //DatabaseConnect.insertData("");

        ArrayList<String> data = DatabaseHandler.retrieveData();
        for(String record: data){
            logger.info("Record : " + record);
        }

        DatabaseHandler.retrieveData();
        DatabaseHandler.disconnect();


    }
    */


    public static Connection connect(Connection con) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/Dq41pHZbDx?useTimezone=true&serverTimezone=UTC", "Dq41pHZbDx", "NuLh0BhDzw");
            logger.info("Connected");
            return con;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Not Connected", e);
            return null;
        }
    }

    public static boolean disconnect(Connection con) {
        try {
            if (con != null) {
                con.close();
                logger.info("Disconnected");
                return true;
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Not Connected", e);
        }
        return false;
    }

    /*
    public static void insertData(String data) {
        String insertQuery = "Insert into datatable (data) values ('" + data + "')";
        Statement statement = null;
        int result = 0;

        try {
            statement = con.createStatement();
            result = statement.executeUpdate(insertQuery);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }
        }
        logger.log(Level.INFO, "Inserted : {0}", result);
    }
    */

    /*
    public static ArrayList<String> retrieveData() {
        String selectQuery = "SELECT * FROM datatable";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                logger.log(Level.INFO, resultSet.getString(1) + " : " + resultSet.getString(2));
                result.add(resultSet.getInt(1) + " : " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    statement.close();
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "ERROR IN CLOSING", e);
            }

        }
        logger.log(Level.INFO, "Retrieved : {0}", result);
        return result;

    }
    */
}
