package com.xcy.fzb.project_side.modle;


//TODO 详情页财务数据类
public class FinanceBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"accessingNumber":0,"tradeNumber":0,"reportNumber":1,"isIslandNumber":0,"earnestMoneyNumber":0,"reportOk":0,"landingNumber":0,"InvalidNum":1}
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
         * accessingNumber : 0
         * tradeNumber : 0
         * reportNumber : 1
         * isIslandNumber : 0
         * earnestMoneyNumber : 0
         * reportOk : 0
         * landingNumber : 0
         * InvalidNum : 1
         */

        private int accessingNumber;
        private int tradeNumber;
        private int reportNumber;
        private int isIslandNumber;
        private int earnestMoneyNumber;
        private int reportOk;
        private int landingNumber;
        private int InvalidNum;

        public int getAccessingNumber() {
            return accessingNumber;
        }

        public void setAccessingNumber(int accessingNumber) {
            this.accessingNumber = accessingNumber;
        }

        public int getTradeNumber() {
            return tradeNumber;
        }

        public void setTradeNumber(int tradeNumber) {
            this.tradeNumber = tradeNumber;
        }

        public int getReportNumber() {
            return reportNumber;
        }

        public void setReportNumber(int reportNumber) {
            this.reportNumber = reportNumber;
        }

        public int getIsIslandNumber() {
            return isIslandNumber;
        }

        public void setIsIslandNumber(int isIslandNumber) {
            this.isIslandNumber = isIslandNumber;
        }

        public int getEarnestMoneyNumber() {
            return earnestMoneyNumber;
        }

        public void setEarnestMoneyNumber(int earnestMoneyNumber) {
            this.earnestMoneyNumber = earnestMoneyNumber;
        }

        public int getReportOk() {
            return reportOk;
        }

        public void setReportOk(int reportOk) {
            this.reportOk = reportOk;
        }

        public int getLandingNumber() {
            return landingNumber;
        }

        public void setLandingNumber(int landingNumber) {
            this.landingNumber = landingNumber;
        }

        public int getInvalidNum() {
            return InvalidNum;
        }

        public void setInvalidNum(int InvalidNum) {
            this.InvalidNum = InvalidNum;
        }
    }
}
