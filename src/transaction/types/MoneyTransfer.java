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

    @Override
    public String toString() {

        String output =
                "\n[MONEY TRANSFER] " +
                        "\nTransaction ID: " + this.transaction_id +
                        "\nAccount ID: " + this.account_id +
                        "\nStore ID: " + this.store_id +
                        "\nRecipient: " + this.recipient +
                        "\nAmount: " + this.amount +
                        "\nTimestamp: " + this.timestamp;

        return output;
    }
}
