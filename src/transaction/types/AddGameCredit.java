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
}
