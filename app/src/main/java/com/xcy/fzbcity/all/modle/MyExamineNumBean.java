package com.xcy.fzbcity.all.modle;

public class MyExamineNumBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"trade":0,"documents":0,"orderSheet":1,"withdraw":0}
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
         * trade : 0
         * documents : 0
         * orderSheet : 1
         * withdraw : 0
         */

        private int trade;
        private int documents;
        private int orderSheet;
        private int withdraw;

        public int getTrade() {
            return trade;
        }

        public void setTrade(int trade) {
            this.trade = trade;
        }

        public int getDocuments() {
            return documents;
        }

        public void setDocuments(int documents) {
            this.documents = documents;
        }

        public int getOrderSheet() {
            return orderSheet;
        }

        public void setOrderSheet(int orderSheet) {
            this.orderSheet = orderSheet;
        }

        public int getWithdraw() {
            return withdraw;
        }

        public void setWithdraw(int withdraw) {
            this.withdraw = withdraw;
        }
    }
}
