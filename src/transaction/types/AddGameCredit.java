package transaction.types;

import transaction.Transaction;

public class AddGameCredit extends Transaction {

    private String company_name;

    public AddGameCredit(int store_id, int transaction_id, int account_id, Double amount, String company_name) {
        super(store_id, transaction_id, account_id, amount);
        this.company_name = company_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    @Override
    public String toString() {

        String output =
                "\n[ADD GAME CREDIT] " +
                        "\nTransaction ID: " + this.transaction_id +
                        "\nAccount ID: " + this.account_id +
                        "\nStore ID: " + this.store_id +
                        "\nCompany Name: " + this.company_name +
                        "\nAmount: " + this.amount +
                        "\nTimestamp: " + this.timestamp;

        return output;
    }
}
