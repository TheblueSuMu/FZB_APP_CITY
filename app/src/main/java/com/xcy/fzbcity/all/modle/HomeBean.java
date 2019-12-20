package com.xcy.fzbcity.all.modle;

public class HomeBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"accessingNoRead":1,"tradeNumber":51,"applyNoRead":"1","invoiceMoney":"0","applyCount":5,"loseEfficacy":72,"reportNumber":43,"earnestMoneyNumber":4,"surplusMoney":"232.41","backMoney":"1.10","loseNoRead":1,"auditNoRead":"1","receivableMoney":"233.52","tradeNoRead":1,"accessingNumber":8,"reportNoRead":1,"prodjectCount":22,"isIslandNumber":6,"isIslandNoRead":1,"earnestMoneyNoRead":1,"auditNumber":31}
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
         * accessingNoRead : 1
         * tradeNumber : 51
         * applyNoRead : 1
         * invoiceMoney : 0
         * applyCount : 5
         * loseEfficacy : 72
         * reportNumber : 43
         * earnestMoneyNumber : 4
         * surplusMoney : 232.41
         * backMoney : 1.10
         * loseNoRead : 1
         * auditNoRead : 1
         * receivableMoney : 233.52
         * tradeNoRead : 1
         * accessingNumber : 8
         * reportNoRead : 1
         * prodjectCount : 22
         * isIslandNumber : 6
         * isIslandNoRead : 1
         * earnestMoneyNoRead : 1
         * auditNumber : 31
         */

        private int accessingNoRead;
        private int tradeNumber;
        private String applyNoRead;
        private String invoiceMoney;
        private int applyCount;
        private int loseEfficacy;
        private int reportNumber;
        private int earnestMoneyNumber;
        private String surplusMoney;
        private String backMoney;
        private int loseNoRead;
        private String auditNoRead;
        private String receivableMoney;
        private int tradeNoRead;
        private int accessingNumber;
        private int reportNoRead;
        private int prodjectCount;
        private int isIslandNumber;
        private int isIslandNoRead;
        private int earnestMoneyNoRead;
        private int auditNumber;

        public int getAccessingNoRead() {
            return accessingNoRead;
        }

        public void setAccessingNoRead(int accessingNoRead) {
            this.accessingNoRead = accessingNoRead;
        }

        public int getTradeNumber() {
            return tradeNumber;
        }

        public void setTradeNumber(int tradeNumber) {
            this.tradeNumber = tradeNumber;
        }

        public String getApplyNoRead() {
            return applyNoRead;
        }

        public void setApplyNoRead(String applyNoRead) {
            this.applyNoRead = applyNoRead;
        }

        public String getInvoiceMoney() {
            return invoiceMoney;
        }

        public void setInvoiceMoney(String invoiceMoney) {
            this.invoiceMoney = invoiceMoney;
        }

        public int getApplyCount() {
            return applyCount;
        }

        public void setApplyCount(int applyCount) {
            this.applyCount = applyCount;
        }

        public int getLoseEfficacy() {
            return loseEfficacy;
        }

        public void setLoseEfficacy(int loseEfficacy) {
            this.loseEfficacy = loseEfficacy;
        }

        public int getReportNumber() {
            return reportNumber;
        }

        public void setReportNumber(int reportNumber) {
            this.reportNumber = reportNumber;
        }

        public int getEarnestMoneyNumber() {
            return earnestMoneyNumber;
        }

        public void setEarnestMoneyNumber(int earnestMoneyNumber) {
            this.earnestMoneyNumber = earnestMoneyNumber;
        }

        public String getSurplusMoney() {
            return surplusMoney;
        }

        public void setSurplusMoney(String surplusMoney) {
            this.surplusMoney = surplusMoney;
        }

        public String getBackMoney() {
            return backMoney;
        }

        public void setBackMoney(String backMoney) {
            this.backMoney = backMoney;
        }

        public int getLoseNoRead() {
            return loseNoRead;
        }

        public void setLoseNoRead(int loseNoRead) {
            this.loseNoRead = loseNoRead;
        }

        public String getAuditNoRead() {
            return auditNoRead;
        }

        public void setAuditNoRead(String auditNoRead) {
            this.auditNoRead = auditNoRead;
        }

        public String getReceivableMoney() {
            return receivableMoney;
        }

        public void setReceivableMoney(String receivableMoney) {
            this.receivableMoney = receivableMoney;
        }

        public int getTradeNoRead() {
            return tradeNoRead;
        }

        public void setTradeNoRead(int tradeNoRead) {
            this.tradeNoRead = tradeNoRead;
        }

        public int getAccessingNumber() {
            return accessingNumber;
        }

        public void setAccessingNumber(int accessingNumber) {
            this.accessingNumber = accessingNumber;
        }

        public int getReportNoRead() {
            return reportNoRead;
        }

        public void setReportNoRead(int reportNoRead) {
            this.reportNoRead = reportNoRead;
        }

        public int getProdjectCount() {
            return prodjectCount;
        }

        public void setProdjectCount(int prodjectCount) {
            this.prodjectCount = prodjectCount;
        }

        public int getIsIslandNumber() {
            return isIslandNumber;
        }

        public void setIsIslandNumber(int isIslandNumber) {
            this.isIslandNumber = isIslandNumber;
        }

        public int getIsIslandNoRead() {
            return isIslandNoRead;
        }

        public void setIsIslandNoRead(int isIslandNoRead) {
            this.isIslandNoRead = isIslandNoRead;
        }

        public int getEarnestMoneyNoRead() {
            return earnestMoneyNoRead;
        }

        public void setEarnestMoneyNoRead(int earnestMoneyNoRead) {
            this.earnestMoneyNoRead = earnestMoneyNoRead;
        }

        public int getAuditNumber() {
            return auditNumber;
        }

        public void setAuditNumber(int auditNumber) {
            this.auditNumber = auditNumber;
        }
    }
}