package com.xcy.fzbcity.all.database;

public class MyDataBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"myAmount":"0","tradeNum":"0","myTeamNum":1}
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
         * myAmount : 0
         * tradeNum : 0
         * myTeamNum : 1
         */

        private String myAmount;
        private String tradeNum;
        private int myTeamNum;

        public String getMyAmount() {
            return myAmount;
        }

        public void setMyAmount(String myAmount) {
            this.myAmount = myAmount;
        }

        public String getTradeNum() {
            return tradeNum;
        }

        public void setTradeNum(String tradeNum) {
            this.tradeNum = tradeNum;
        }

        public int getMyTeamNum() {
            return myTeamNum;
        }

        public void setMyTeamNum(int myTeamNum) {
            this.myTeamNum = myTeamNum;
        }
    }
}