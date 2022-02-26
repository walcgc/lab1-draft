import database.DatabaseHandler;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.random.RandomGenerator;

public class Test {

    private static Connection con;
    final private static Logger logger = Logger.getLogger(Test.class.getName());

    public static void main(String[] args) {


        RandomGenerator generator = RandomGenerator.getDefault();

        for(int i = 3; i <= 35; i++){

            int randNum1 = generator.nextInt(0, 499);
            int randNum2 = generator.nextInt(0, 499);
            String fullName = Names.getRandom_Full_name(randNum1, randNum2);
            System.out.println(fullName);

            Promo.registerPromo(String.valueOf(i), "4438", "PROMO");
            Promo.registerPromo(String.valueOf(i), "4438", "REGISTER");
            Promo.registerPromo(String.valueOf(i), "4438", fullName);
            System.out.println(fullName + "4438");

            Promo.registerPromo(String.valueOf(i), "1234555", "PROMO");
            Promo.registerPromo(String.valueOf(i), "1234555", "REGISTER");
            Promo.registerPromo(String.valueOf(i), "1234555", fullName);
            System.out.println(fullName + "1234555");

            Promo.registerPromo(String.valueOf(i), "777", "PROMO");
            Promo.registerPromo(String.valueOf(i), "777", "REGISTER");
            Promo.registerPromo(String.valueOf(i), "777", fullName);
            System.out.println(fullName + "777");

        }

    }
}
