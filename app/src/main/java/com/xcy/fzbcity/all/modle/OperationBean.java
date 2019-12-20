
package com.xcy.fzbcity.all.modle;

//TODO 详情页运营数据类
public class OperationBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"invoiceMoney":"0","surplusMoney":"0","backMoney":"0","receivableMoney":"0"}
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
         * invoiceMoney : 0
         * surplusMoney : 0
         * backMoney : 0
         * receivableMoney : 0
         */

        private String invoiceMoney;
        private String surplusMoney;
        private String backMoney;
        private String receivableMoney;

        public String getInvoiceMoney() {
            return invoiceMoney;
        }

        public void setInvoiceMoney(String invoiceMoney) {
            this.invoiceMoney = invoiceMoney;
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

        public String getReceivableMoney() {
            return receivableMoney;
        }

        public void setReceivableMoney(String receivableMoney) {
            this.receivableMoney = receivableMoney;
        }
    }
}