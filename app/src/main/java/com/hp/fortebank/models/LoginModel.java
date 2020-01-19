package com.hp.fortebank.models;

public class LoginModel {

    /**
     * status : Success
     * User_data : {"id":"1","name":"Athira","phone":"8157073329","pin":"12345","account_num":"56789012","qr_code":"sjWFw","otp":"38776","balance":"79500"}
     */

    private String status;
    private UserDataBean User_data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDataBean getUser_data() {
        return User_data;
    }

    public void setUser_data(UserDataBean User_data) {
        this.User_data = User_data;
    }

    public static class UserDataBean {
        /**
         * id : 1
         * name : Athira
         * phone : 8157073329
         * pin : 12345
         * account_num : 56789012
         * qr_code : sjWFw
         * otp : 38776
         * balance : 79500
         */

        private String id;
        private String name;
        private String phone;
        private String pin;
        private String account_num;
        private String qr_code;
        private String otp;
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

        public String getQr_code() {
            return qr_code;
        }

        public void setQr_code(String qr_code) {
            this.qr_code = qr_code;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }
    }
}
