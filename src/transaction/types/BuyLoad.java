package transaction.types;

import transaction.Transaction;

public class BuyLoad extends Transaction {

    private Long msisdn;

    public BuyLoad(int store_id, int transaction_id, int account_id, Double amount, Long msisdn) {
        super(store_id, transaction_id, account_id, amount);
        this.msisdn = msisdn;
    }

    public Long getMsisdn() {
        return msisdn;
    }
}
