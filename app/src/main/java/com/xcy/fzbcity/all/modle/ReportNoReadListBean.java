package com.xcy.fzbcity.all.modle;

public class ReportNoReadListBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"reports":"0","accessing":"1","trade":"3","lose":"2","earnest":"0","isIsland":"3"}
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
         * reports : 0
         * accessing : 1
         * trade : 3
         * lose : 2
         * earnest : 0
         * isIsland : 3
         */

        private String reports;
        private String accessing;
        private String trade;
        private String lose;
        private String earnest;
        private String isIsland;

        public String getReports() {
            return reports;
        }

        public void setReports(String reports) {
            this.reports = reports;
        }

        public String getAccessing() {
            return accessing;
        }

        public void setAccessing(String accessing) {
            this.accessing = accessing;
        }

        public String getTrade() {
            return trade;
        }

        public void setTrade(String trade) {
            this.trade = trade;
        }

        public String getLose() {
            return lose;
        }

        public void setLose(String lose) {
            this.lose = lose;
        }

        public String getEarnest() {
            return earnest;
        }

        public void setEarnest(String earnest) {
            this.earnest = earnest;
        }

        public String getIsIsland() {
            return isIsland;
        }

        public void setIsIsland(String isIsland) {
            this.isIsland = isIsland;
        }
    }
}
