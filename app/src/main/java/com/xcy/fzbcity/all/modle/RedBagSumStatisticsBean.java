package com.xcy.fzbcity.all.modle;

public class RedBagSumStatisticsBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"listData":{"totalConvertedAmount":"2.95","totalCustomer":"6","totalProvideAmount":"5.90"}}
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
         * listData : {"totalConvertedAmount":"2.95","totalCustomer":"6","totalProvideAmount":"5.90"}
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
             * totalConvertedAmount : 2.95
             * totalCustomer : 6
             * totalProvideAmount : 5.90
             */

            private String totalConvertedAmount;
            private String totalCustomer;
            private String totalProvideAmount;

            public String getTotalConvertedAmount() {
                return totalConvertedAmount;
            }

            public void setTotalConvertedAmount(String totalConvertedAmount) {
                this.totalConvertedAmount = totalConvertedAmount;
            }

            public String getTotalCustomer() {
                return totalCustomer;
            }

            public void setTotalCustomer(String totalCustomer) {
                this.totalCustomer = totalCustomer;
            }

            public String getTotalProvideAmount() {
                return totalProvideAmount;
            }

            public void setTotalProvideAmount(String totalProvideAmount) {
                this.totalProvideAmount = totalProvideAmount;
            }
        }
    }
}
