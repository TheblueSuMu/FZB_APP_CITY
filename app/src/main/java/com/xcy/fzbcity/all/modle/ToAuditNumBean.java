package com.xcy.fzbcity.all.modle;

public class ToAuditNumBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"access":0,"trade":0,"report":8,"earnest":0}
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
         * access : 0
         * trade : 0
         * report : 8
         * earnest : 0
         */

        private int access;
        private int trade;
        private int report;
        private int earnest;

        public int getAccess() {
            return access;
        }

        public void setAccess(int access) {
            this.access = access;
        }

        public int getTrade() {
            return trade;
        }

        public void setTrade(int trade) {
            this.trade = trade;
        }

        public int getReport() {
            return report;
        }

        public void setReport(int report) {
            this.report = report;
        }

        public int getEarnest() {
            return earnest;
        }

        public void setEarnest(int earnest) {
            this.earnest = earnest;
        }
    }
}
