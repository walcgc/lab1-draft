package transaction;

import transaction.types.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionProcessing {

    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    final private static Logger logger = Logger.getLogger(TransactionProcessing.class.getName());

    public static void main(String[] args) {
        TransactionProcessing tp = TransactionProcessing.init();
        TransactionProcessing.listTransactionObjects(tp);
        TransactionProcessing.listTransactionsByType(tp, TransactionTypes.ADD_ACCOUNT_CREDITS);
    }

    public static TransactionProcessing init() {
        TransactionProcessing tp = new TransactionProcessing();

        //Money Transfer - generating 5 random objects
        int countdown = 5;
        while (countdown > 0) {
            tp.transactions.add(Randomizer.randomMTObject());
            countdown--;
        }

        //Bill - generating 3 random objects
        countdown = 3;
        while (countdown > 0) {
            tp.transactions.add(Randomizer.randomBillObject());
            countdown--;
        }

        //Buy Load - generating 5 random objects
        countdown = 5;
        while (countdown > 0) {
            tp.transactions.add(Randomizer.randomBLObject());
            countdown--;
        }

        //Add Account Credit - generating 2 random objects
        countdown = 2;
        while (countdown > 0) {
            tp.transactions.add(Randomizer.randomAACObject());
            countdown--;
        }

        //Add Game Credit - generating 5 random objects
        countdown = 5;
        while (countdown > 0) {
            tp.transactions.add(Randomizer.randomAGCObject());
            countdown--;
        }

        return tp;

    }

    public static void listTransactionObjects(TransactionProcessing tp) {
        String transactionsList = "";
        for (Transaction i : tp.transactions) {
            transactionsList += "\n" + i;
        }
        logger.log(Level.INFO, transactionsList);
    }

    public static void listTransactionsByType(TransactionProcessing tp, TransactionTypes type) {
        switch (type) {
            case MONEY_TRANSFER: {
                String infoList = "";
                for (Transaction i : tp.transactions) {
                    if (i instanceof MoneyTransfer) {
                        infoList += "\n" + i.toString();

                    }
                }
                logger.log(Level.INFO, infoList);
                break;
            }
            case BILLS_PAYMENT: {
                String infoList = "";
                for (Transaction i : tp.transactions) {
                    if (i instanceof Bill) {
                        infoList += "\n" + i.toString();

                    }
                }
                logger.log(Level.INFO, infoList);
                break;
            }
            case BUY_LOAD: {
                String infoList = "";
                for (Transaction i : tp.transactions) {
                    if (i instanceof BuyLoad) {
                        infoList += "\n" + i.toString();

                    }
                }
                logger.log(Level.INFO, infoList);
                break;
            }
            case ADD_ACCOUNT_CREDITS: {
                String infoList = "";
                for (Transaction i : tp.transactions) {
                    if (i instanceof AddAccountCredit) {
                        infoList += "\n" + i.toString();

                    }
                }
                logger.log(Level.INFO, infoList);
                break;
            }
            case ADD_GAME_CREDITS: {
                String infoList = "";
                for (Transaction i : tp.transactions) {
                    if (i instanceof AddGameCredit) {
                        infoList += "\n" + i.toString();

                    }
                }
                logger.log(Level.INFO, infoList);
                break;
            }
            default: {
                throw new IllegalArgumentException("Can't yet handle " + type);

            }
        }
    }

}
