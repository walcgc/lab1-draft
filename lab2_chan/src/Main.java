import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    final private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        /*
        Sms sms = new Sms();
        sms.sendSms("+639185640239","+639208569874", "Buy Doritos");
         */

        /*
        Promo.createPromo("Piso Pizza", "1234555",
                "Pizza for a peso!", "2022-02-01 10:00:00",
                "2022-06-30 23:59:00");




        boolean a = Promo.checkShortCode("4438");
        System.out.println(a);
        */

        // Scanner for user input
        Scanner input = new Scanner(System.in);

        // program runs while programOn is true
        boolean programOn = true;
        String sender_msisdn = "";

        while (programOn) {

            logger.log(Level.INFO, "Start app");
            System.out.println("SMS Dev App\n");

            // SMS App
            while (sender_msisdn.isEmpty())
            {
                System.out.println("Enter your phone number: ");
                sender_msisdn = input.nextLine();
            }


            System.out.println("Enter message: ");
            String message = input.nextLine();

            System.out.println("Send to: ");
            String receiver_msisdn = input.nextLine();

            boolean isShortCode = Promo.checkShortCode(receiver_msisdn);
            if (isShortCode) {
                Promo.registerPromo(sender_msisdn, receiver_msisdn, message);
            } else {
                Sms.sendSms(sender_msisdn, receiver_msisdn, message);
            }


        }


    }

}
