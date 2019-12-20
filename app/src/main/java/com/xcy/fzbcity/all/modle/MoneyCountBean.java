package com.xcy.fzbcity.all.modle;

public class MoneyCountBean {

    /**
     * code : 1
     * msg : 成功
     * data : {"MoneyData":{"invoiceMoney":"0","surplusMoney":"0","backMoney":"0","amountUnit":"万","receivableMoney":"0"}}
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
         * MoneyData : {"invoiceMoney":"0","surplusMoney":"0","backMoney":"0","amountUnit":"万","receivableMoney":"0"}
         */

        private MoneyDataBean MoneyData;

        public MoneyDataBean getMoneyData() {
            return MoneyData;
        }

        public void setMoneyData(MoneyDataBean MoneyData) {
            this.MoneyData = MoneyData;
        }

        public static class MoneyDataBean {
            /**
             * invoiceMoney : 0
             * surplusMoney : 0
             * backMoney : 0
             * amountUnit : 万
             * receivableMoney : 0
             */

            private String invoiceMoney;
            private String surplusMoney;
            private String backMoney;
            private String amountUnit;
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

            public String getAmountUnit() {
                return amountUnit;
            }

            public void setAmountUnit(String amountUnit) {
                this.amountUnit = amountUnit;
            }

            public String getReceivableMoney() {
                return receivableMoney;
            }

            public void setReceivableMoney(String receivableMoney) {
                this.receivableMoney = receivableMoney;
            }
        }
    }
}