package com.hp.fortebank.models;

import java.util.List;

public class HistoryModel {

    /**
     * status : success
     * Transaction_History : [{"Transaction_ID":"1","Debited_On":"Sat, 18 Jan 20 22:47:26 -0700","Debited_Amount":"100","Amount_After_Debited":"79900","Current_balance":"79900"},{"Transaction_ID":"2","Debited_On":"Sat, 18 Jan 20 22:47:36 -0700","Debited_Amount":"100","Amount_After_Debited":"79800","Current_balance":"79800"},{"Transaction_ID":"3","Debited_On":"Sat, 18 Jan 20 22:48:09 -0700","Debited_Amount":"100","Amount_After_Debited":"79700","Current_balance":"79700"},{"Transaction_ID":"4","Debited_On":"Sat, 18 Jan 20 22:49:27 -0700","Debited_Amount":"100","Amount_After_Debited":"79600","Current_balance":"79600"},{"Transaction_ID":"5","Debited_On":"Sat, 18 Jan 20 22:53:01 -0700","Debited_Amount":"100","Amount_After_Debited":"79500","Current_balance":"79500"},{"Transaction_ID":"6","Debited_On":"Sun, 19 Jan 20 22:39:15 -0700","Debited_Amount":"100","Amount_After_Debited":"79400","Current_balance":"79400"},{"Transaction_ID":"10","Debited_On":"Mon, 20 Jan 20 01:44:32 -0700","Debited_Amount":"100","Amount_After_Debited":"79300","Current_balance":"79300"},{"Transaction_ID":"11","Debited_On":"Mon , 20 Jan 20 1:51:35","Debited_Amount":"400","Amount_After_Debited":"78900","Current_balance":"78900"},{"Transaction_ID":"12","Debited_On":"Mon , 20 Jan 20 7:25:14","Debited_Amount":"100","Amount_After_Debited":"78800","Current_balance":"78800"},{"Transaction_ID":"13","Debited_On":"Mon , 20 Jan 20 1:57:24","Debited_Amount":"2000","Amount_After_Debited":"76800","Current_balance":"76800"},{"Transaction_ID":"14","Debited_On":"Mon , 20 Jan 20 1:58:53","Debited_Amount":"100","Amount_After_Debited":"76700","Current_balance":"76700"},{"Transaction_ID":"15","Debited_On":"Mon , 20 Jan 20 1:32:27","Debited_Amount":"400","Amount_After_Debited":"76300","Current_balance":"76300"},{"Transaction_ID":"16","Debited_On":"Mon , 20 Jan 20 2:32:51","Debited_Amount":"100","Amount_After_Debited":"76200","Current_balance":"76200"}]
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
         * Debited_On : Sat, 18 Jan 20 22:47:26 -0700
         * Debited_Amount : 100
         * Amount_After_Debited : 79900
         * Current_balance : 79900
         */

        private String Transaction_ID;
        private String Debited_On;
        private String Debited_Amount;
        private String Amount_After_Debited;
        private String Current_balance;

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

        public String getDebited_Amount() {
            return Debited_Amount;
        }

        public void setDebited_Amount(String Debited_Amount) {
            this.Debited_Amount = Debited_Amount;
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
    }
}
