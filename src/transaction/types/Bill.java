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

    @Override
    public String toString() {

        String output =
                "\n[BILL] " +
                        "\nTransaction ID: " + this.transaction_id +
                        "\nAccount ID: " + this.account_id +
                        "\nStore ID: " + this.store_id +
                        "\nCompany Name: " + this.company_name +
                        "\nAmount: " + this.amount +
                        "\nBill: " + this.bills_charge +
                        "\nTimestamp: " + this.timestamp;

        return output;
    }

    @Override
    public boolean equals(Object object) {
        Bill obj = (Bill) object;
        if (obj == this) {
            return true;
        }

        if (
                Integer.compare(this.store_id, obj.store_id) == 0 &&
                        Integer.compare(this.transaction_id, obj.transaction_id) == 0 &&
                        Integer.compare(this.account_id, obj.account_id) == 0 &&
                        Double.compare(this.amount, obj.amount) == 0 &&
                        Double.compare(this.bills_charge, obj.bills_charge) == 0 &&
                        this.company_name.equals(obj.company_name)

        ) {
            return true;
        }

        return false;
    }
}
