package transaction;

import java.util.ArrayList;

public class TransactionProcessing {

    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        TransactionProcessing tp = new TransactionProcessing();

        //Money Transfer - generating 5 random objects
        int countdown = 5;
        while (countdown > 0){
            tp.transactions.add(Randomizer.randomMTObject());
            countdown--;
        }

        //Bill - generating 3 random objects
        countdown = 3;
        while (countdown > 0){
            tp.transactions.add(Randomizer.randomBillObject());
            countdown--;
        }

        //Buy Load - generating 5 random objects
        countdown = 5;
        while (countdown > 0){
            tp.transactions.add(Randomizer.randomBLObject());
            countdown--;
        }

        //Add Account Credit - generating 2 random objects
        countdown = 2;
        while (countdown > 0){
            tp.transactions.add(Randomizer.randomAACObject());
            countdown--;
        }

        //Add Game Credit - generating 5 random objects
        countdown = 5;
        while (countdown > 0){
            tp.transactions.add(Randomizer.randomAGCObject());
            countdown--;
        }

        //just testing
        for (Transaction i : tp.transactions) {
            System.out.println(i);
        }

    }



}
