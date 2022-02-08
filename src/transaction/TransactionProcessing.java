package transaction;

import java.util.ArrayList;
import transaction.types.*;
import java.util.random.RandomGenerator;

public class TransactionProcessing {

    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        TransactionProcessing tp = new TransactionProcessing();

        //Countdown money transfer. Only add 5
        int countdownMT = 5;
        while (countdownMT > 0){
            tp.transactions.add(TransactionProcessing.randomMTObject());
            countdownMT--;
        }

        //just testing
        for (Transaction i : tp.transactions) {
            System.out.println(i);
        }

    }

    //Generate Random Money Transfer Objects
    private static MoneyTransfer randomMTObject(){
        RandomGenerator generator = RandomGenerator.getDefault();
        int store_id = generator.nextInt(1, 200000);
        int transaction_id = generator.nextInt(1, 1000000);
        int account_id = generator.nextInt(1, 900000);
        double amount = generator.nextDouble(1, 3000000);

        int randNum1 = generator.nextInt(0, 499);
        int randNum2 = generator.nextInt(0, 499);
        String recipient = Names.getRandom_Full_name(randNum1, randNum2);

        MoneyTransfer obj = new MoneyTransfer(store_id,transaction_id,account_id, amount, recipient);
        return obj;
    }

}
