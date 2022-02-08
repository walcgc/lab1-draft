package transaction.types;
import transaction.Transaction;

public class MoneyTransfer extends Transaction {
    private String recipient;

    public MoneyTransfer(int store_id, int transaction_id, int account_id, Double amount, String recipient) {
        super(store_id, transaction_id, account_id, amount);
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }
}
