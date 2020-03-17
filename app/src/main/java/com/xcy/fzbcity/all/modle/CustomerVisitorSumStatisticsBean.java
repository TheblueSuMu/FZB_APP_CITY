package com.xcy.fzbcity.all.modle;

public class CustomerVisitorSumStatisticsBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"listData":{"all":8,"week":6,"today":1}}
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
         * listData : {"all":8,"week":6,"today":1}
         */

        private ListDataBean listData;

        public ListDataBean getListData() {
            return listData;
        }

        public void setListData(ListDataBean listData) {
            this.listData = listData;
        }

        public static class ListDataBean {
            /**
             * all : 8
             * week : 6
             * today : 1
             */

            private int all;
            private int week;
            private int today;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }

            public int getWeek() {
                return week;
            }

            public void setWeek(int week) {
                this.week = week;
            }

            public int getToday() {
                return today;
            }

            public void setToday(int today) {
                this.today = today;
            }
        }
    }
}
