package com.xcy.fzbcity.all.database;

public class TeamCommissionsBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"totalAmount":"149.14","notAmount":"143.96","alreadyAmount":"5.28"}
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
         * totalAmount : 149.14
         * notAmount : 143.96
         * alreadyAmount : 5.28
         */

        private String totalAmount;
        private String notAmount;
        private String alreadyAmount;

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getNotAmount() {
            return notAmount;
        }

        public void setNotAmount(String notAmount) {
            this.notAmount = notAmount;
        }

        public String getAlreadyAmount() {
            return alreadyAmount;
        }

        public void setAlreadyAmount(String alreadyAmount) {
            this.alreadyAmount = alreadyAmount;
        }
    }
}