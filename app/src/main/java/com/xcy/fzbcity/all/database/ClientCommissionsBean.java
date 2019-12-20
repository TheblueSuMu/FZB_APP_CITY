package com.xcy.fzbcity.all.database;

public class ClientCommissionsBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"totalAmount":"0","myCustomerCount":"29","myIntegral":"0"}
     */

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * totalAmount : 0
         * myCustomerCount : 29
         * myIntegral : 0
         */

        private String totalAmount;
        private String myCustomerCount;
        private String myIntegral;

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getMyCustomerCount() {
            return myCustomerCount;
        }

        public void setMyCustomerCount(String myCustomerCount) {
            this.myCustomerCount = myCustomerCount;
        }

        public String getMyIntegral() {
            return myIntegral;
        }

        public void setMyIntegral(String myIntegral) {
            this.myIntegral = myIntegral;
        }
    }
}