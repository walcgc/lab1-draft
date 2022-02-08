package transaction.types;

import transaction.Transaction;

public class Bill extends Transaction {

    private String company_name;
    private Double bills_charge;

    public Bill(int store_id, int transaction_id, int account_id, Double amount, String company_name, Double bills_charge) {
        super(store_id, transaction_id, account_id, amount);
        this.company_name = company_name;
        this.bills_charge = bills_charge;
    }

    public String getCompany_name() {
        return company_name;
    }

    public Double getBills_charge() {
        return bills_charge;
    }
}
