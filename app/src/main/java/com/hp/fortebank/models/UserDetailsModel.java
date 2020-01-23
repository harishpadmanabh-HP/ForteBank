package com.hp.fortebank.models;

import java.util.List;

public class UserDetailsModel {

    /**
     * status : success
     * User_Details : [{"id":"5","name":"harish padmanabh","phone":"7012069385","pin":"12345","account_num":"1234567890","balance":"48450"}]
     */

    private String status;
    private List<UserDetailsBean> User_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserDetailsBean> getUser_Details() {
        return User_Details;
    }

    public void setUser_Details(List<UserDetailsBean> User_Details) {
        this.User_Details = User_Details;
    }

    public static class UserDetailsBean {
        /**
         * id : 5
         * name : harish padmanabh
         * phone : 7012069385
         * pin : 12345
         * account_num : 1234567890
         * balance : 48450
         */

        private String id;
        private String name;
        private String phone;
        private String pin;
        private String account_num;
        private String balance;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public String getAccount_num() {
            return account_num;
        }

        public void setAccount_num(String account_num) {
            this.account_num = account_num;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }
}
