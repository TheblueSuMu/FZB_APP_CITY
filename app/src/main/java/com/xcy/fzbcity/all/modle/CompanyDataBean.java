package com.xcy.fzbcity.all.modle;

public class CompanyDataBean {


    /**
     * code : 1
     * msg : 成功
     * data : {"agentNum":0,"storeNum":0}
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
         * agentNum : 0
         * storeNum : 0
         */

        private int agentNum;
        private int storeNum;

        public int getAgentNum() {
            return agentNum;
        }

        public void setAgentNum(int agentNum) {
            this.agentNum = agentNum;
        }

        public int getStoreNum() {
            return storeNum;
        }

        public void setStoreNum(int storeNum) {
            this.storeNum = storeNum;
        }
    }
}