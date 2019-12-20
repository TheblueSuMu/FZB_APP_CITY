package com.xcy.fzbcity.all.database;

import java.util.List;

public class MyTeamBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"dataStatistics":{"accessingNumber":0,"tradeNumber":11,"reportNumber":1,"isIslandNumber":2,"earnestMoneyNumber":1,"landingNumber":0,"InvalidNum":5},"counselorNum":"9","salesNum":"9","moneyData":{"totalAmount":"2.75","notAmount":"1.52","alreadyAmount":"1.23"},"dailyTurnover":[{"num":3,"name":"圈层测试12","photo":"/fangfang/static/common/images/flat-avatar.png","id":"d775f2b0b3ca44d99cef757a4d7c1abf"},{"num":2,"name":"圈层测试13","photo":"/fangfang/static/common/images/flat-avatar.png","id":"14dec90ca0ec42e6a082c845b95fdc96"},{"num":1,"name":"圈层测试14","photo":"/fangfang/static/common/images/flat-avatar.png","id":"c238eb4020a0443eb15c1c8e98b81ce2"},{"num":1,"name":"圈层测试11","photo":"/fangfang/static/common/images/flat-avatar.png","id":"b4c2c74240f9499e84daf13b02e0a88c"}]}
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
         * dataStatistics : {"accessingNumber":0,"tradeNumber":11,"reportNumber":1,"isIslandNumber":2,"earnestMoneyNumber":1,"landingNumber":0,"InvalidNum":5}
         * counselorNum : 9
         * salesNum : 9
         * moneyData : {"totalAmount":"2.75","notAmount":"1.52","alreadyAmount":"1.23"}
         * dailyTurnover : [{"num":3,"name":"圈层测试12","photo":"/fangfang/static/common/images/flat-avatar.png","id":"d775f2b0b3ca44d99cef757a4d7c1abf"},{"num":2,"name":"圈层测试13","photo":"/fangfang/static/common/images/flat-avatar.png","id":"14dec90ca0ec42e6a082c845b95fdc96"},{"num":1,"name":"圈层测试14","photo":"/fangfang/static/common/images/flat-avatar.png","id":"c238eb4020a0443eb15c1c8e98b81ce2"},{"num":1,"name":"圈层测试11","photo":"/fangfang/static/common/images/flat-avatar.png","id":"b4c2c74240f9499e84daf13b02e0a88c"}]
         */

        private DataStatisticsBean dataStatistics;
        private String counselorNum;
        private String salesNum;
        private MoneyDataBean moneyData;
        private List<DailyTurnoverBean> dailyTurnover;

        public DataStatisticsBean getDataStatistics() {
            return dataStatistics;
        }

        public void setDataStatistics(DataStatisticsBean dataStatistics) {
            this.dataStatistics = dataStatistics;
        }

        public String getCounselorNum() {
            return counselorNum;
        }

        public void setCounselorNum(String counselorNum) {
            this.counselorNum = counselorNum;
        }

        public String getSalesNum() {
            return salesNum;
        }

        public void setSalesNum(String salesNum) {
            this.salesNum = salesNum;
        }

        public MoneyDataBean getMoneyData() {
            return moneyData;
        }

        public void setMoneyData(MoneyDataBean moneyData) {
            this.moneyData = moneyData;
        }

        public List<DailyTurnoverBean> getDailyTurnover() {
            return dailyTurnover;
        }

        public void setDailyTurnover(List<DailyTurnoverBean> dailyTurnover) {
            this.dailyTurnover = dailyTurnover;
        }

        public static class DataStatisticsBean {
            /**
             * accessingNumber : 0
             * tradeNumber : 11
             * reportNumber : 1
             * isIslandNumber : 2
             * earnestMoneyNumber : 1
             * landingNumber : 0
             * InvalidNum : 5
             */

            private int accessingNumber;
            private int tradeNumber;
            private int reportNumber;
            private int isIslandNumber;
            private int earnestMoneyNumber;
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

        public static class MoneyDataBean {
            /**
             * totalAmount : 2.75
             * notAmount : 1.52
             * alreadyAmount : 1.23
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

        public static class DailyTurnoverBean {
            /**
             * num : 3
             * name : 圈层测试12
             * photo : /fangfang/static/common/images/flat-avatar.png
             * id : d775f2b0b3ca44d99cef757a4d7c1abf
             */

            private int num;
            private String name;
            private String photo;
            private String id;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}