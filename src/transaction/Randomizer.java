package transaction;

import transaction.types.*;

import java.util.random.RandomGenerator;

public class Randomizer {

    //Generate Random Money Transfer Objects
    public static MoneyTransfer randomMTObject() {
        RandomGenerator generator = RandomGenerator.getDefault();
        int store_id = generator.nextInt(1, 200000);
        int transaction_id = generator.nextInt(1, 1000000);
        int account_id = generator.nextInt(1, 900000);
        double amount = generator.nextDouble(1, 3000000);

        int randNum1 = generator.nextInt(0, 499);
        int randNum2 = generator.nextInt(0, 499);
        String recipient = Names.getRandom_Full_name(randNum1, randNum2);

        MoneyTransfer obj = new MoneyTransfer(store_id, transaction_id, account_id, amount, recipient);
        return obj;
    }

    public static Bill randomBillObject() {
        RandomGenerator generator = RandomGenerator.getDefault();
        int store_id = generator.nextInt(1, 200000);
        int transaction_id = generator.nextInt(1, 1000000);
        int account_id = generator.nextInt(1, 900000);
        double amount = generator.nextDouble(1, 3000000);

        int randNum = generator.nextInt(0, 499);
        String company_name = Names.getRandom_Company_name(randNum);
        Double bills_charge = generator.nextDouble(1, 3000000);

        Bill obj = new Bill(store_id, transaction_id, account_id, amount, company_name, bills_charge);
        return obj;

    }

    public static BuyLoad randomBLObject() {
        RandomGenerator generator = RandomGenerator.getDefault();
        int store_id = generator.nextInt(1, 200000);
        int transaction_id = generator.nextInt(1, 1000000);
        int account_id = generator.nextInt(1, 900000);
        double amount = generator.nextDouble(1, 3000000);

        long msisdn = generator.nextLong(10_000_000_000L, 99_999_999_999L);

        BuyLoad obj = new BuyLoad(store_id, transaction_id, account_id, amount, msisdn);
        return obj;

    }

    public static AddAccountCredit randomAACObject() {
        RandomGenerator generator = RandomGenerator.getDefault();
        int store_id = generator.nextInt(1, 200000);
        int transaction_id = generator.nextInt(1, 1000000);
        int account_id = generator.nextInt(1, 900000);
        double amount = generator.nextDouble(1, 3000000);

        long msisdn = generator.nextLong(10_000_000_000L, 99_999_999_999L);

        AddAccountCredit obj = new AddAccountCredit(store_id, transaction_id, account_id, amount, msisdn);
        return obj;

    }

    public static AddGameCredit randomAGCObject() {
        RandomGenerator generator = RandomGenerator.getDefault();
        int store_id = generator.nextInt(1, 200000);
        int transaction_id = generator.nextInt(1, 1000000);
        int account_id = generator.nextInt(1, 900000);
        double amount = generator.nextDouble(1, 3000000);

        int randNum = generator.nextInt(0, 499);
        String company_name = Names.getRandom_Company_name(randNum);

        AddGameCredit obj = new AddGameCredit(store_id, transaction_id, account_id, amount, company_name);
        return obj;

    }

}
