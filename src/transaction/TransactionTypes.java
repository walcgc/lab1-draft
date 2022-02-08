package transaction;

public enum TransactionTypes {
    MONEY_TRANSFER("Money Transfer"),
    BILLS_PAYMENT("Bill Payment"),
    BUY_LOAD("Buy Load"),
    ADD_ACCOUNT_CREDITS("Add Account Credits"),
    ADD_GAME_CREDITS("Add Game Credits");

    final String title;

    TransactionTypes(String title){
        this.title = title;
    }

}
