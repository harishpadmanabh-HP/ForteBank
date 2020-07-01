package com.hp.fortebank.models;

import java.util.List;

public class HistoryModel {

    /**
     * status : success
     * Transaction_History : [{"Transaction_ID":"1","Debited_On":"Wed , 1 Jul 20 07:34:49 am","Amount":"100","Amount_After_Debited":"50100","Current_balance":"50100","status":"Credit"}]
     */

    private String status;
    private List<TransactionHistoryBean> Transaction_History;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TransactionHistoryBean> getTransaction_History() {
        return Transaction_History;
    }

    public void setTransaction_History(List<TransactionHistoryBean> Transaction_History) {
        this.Transaction_History = Transaction_History;
    }

    public static class TransactionHistoryBean {
        /**
         * Transaction_ID : 1
         * Debited_On : Wed , 1 Jul 20 07:34:49 am
         * Amount : 100
         * Amount_After_Debited : 50100
         * Current_balance : 50100
         * status : Credit
         */

        private String Transaction_ID;
        private String Debited_On;
        private String Amount;
        private String Amount_After_Debited;
        private String Current_balance;
        private String status;

        public String getTransaction_ID() {
            return Transaction_ID;
        }

        public void setTransaction_ID(String Transaction_ID) {
            this.Transaction_ID = Transaction_ID;
        }

        public String getDebited_On() {
            return Debited_On;
        }

        public void setDebited_On(String Debited_On) {
            this.Debited_On = Debited_On;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String Amount) {
            this.Amount = Amount;
        }

        public String getAmount_After_Debited() {
            return Amount_After_Debited;
        }

        public void setAmount_After_Debited(String Amount_After_Debited) {
            this.Amount_After_Debited = Amount_After_Debited;
        }

        public String getCurrent_balance() {
            return Current_balance;
        }

        public void setCurrent_balance(String Current_balance) {
            this.Current_balance = Current_balance;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
